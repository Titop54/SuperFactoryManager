#![feature(try_blocks)]

use std::{
    collections::{HashMap, VecDeque},
    fs::{self, File},
    io::{Read, Write},
    path::{Path, PathBuf},
};

use rayon::prelude::*;
use serde::{Deserialize, Serialize};
use uuid::Uuid;

#[derive(Debug, Deserialize)]
#[allow(dead_code)]
pub struct Item {
    id: String,
    data: Option<String>,
    tags: Vec<String>,
    tooltip: String,
    emc: Option<f32>,
}

#[derive(Debug, Clone)]
struct HistoryEntry {
    recipe_id: String,
    recipe_category: String,
    ingredients: Vec<String>,
    resulting_emc: f32,
}

#[derive(Debug, Deserialize, Serialize, Clone)]
#[allow(dead_code)]
pub struct Ingredient {
    role: String,
    #[serde(rename = "ingredientType")]
    ingredient_type: String,
    #[serde(rename = "ingredientAmount")]
    ingredient_amount: i32,
    #[serde(rename = "ingredientId")]
    ingredient_id: String,
    tags: Vec<String>,
    ingredient: String,
}

#[derive(Debug, Deserialize)]
#[allow(dead_code)]
pub struct Recipe {
    category: String,
    #[serde(rename = "categoryTitle")]
    category_title: String,
    #[serde(rename = "recipeTypeId")]
    recipe_type_id: String,
    #[serde(rename = "recipeClass")]
    recipe_class: String,
    #[serde(rename = "recipeObject")]
    recipe_object: String,
    ingredients: Vec<Ingredient>,
}

#[derive(Debug, Serialize, Deserialize)]
struct ProcessedRecipe {
    recipe_id: String,
    category_title: String,
    inputs: Vec<Ingredient>,
    outputs: Vec<Ingredient>,
}

fn get_emc_from_tooltip(number_words: &HashMap<&str, i64>, tooltip: &str) -> Option<f32> {
    for line in tooltip.lines() {
        if let Some(emc) = try {
            let x = line.strip_prefix("EMC: ")?;
            let x = x.trim_end_matches(" (✗)");
            let x = x.replace(",", "");
            let mut words = x.split_whitespace().collect::<VecDeque<&str>>();
            let number = words.pop_front()?;
            let mut number = number.parse::<f32>().ok()?;
            if let Some(number_word) = words.pop_front() {
                if let Some(&multiplier) = number_words.get(number_word) {
                    number *= multiplier as f32
                }
            }
            number
        } {
            return Some(emc);
        }
    }
    return None;
}

fn get_number_words() -> HashMap<&'static str, i64> {
    [
        ("Million", 1_000_000i64),
        ("Billion", 1_000_000_000i64),
        ("Trillion", 1_000_000_000_000i64),
        ("Quadrillion", 1_000_000_000_000_000i64),
        ("Quintillion", 1_000_000_000_000_000_000i64),
    ]
    .into_iter()
    .collect()
}

fn calculate_emc(items: &mut Vec<Item>) {
    let number_words = get_number_words();
    for item in items.iter_mut() {
        if item.emc.is_none() {
            item.emc = get_emc_from_tooltip(&number_words, &item.tooltip);
        }
    }
}

fn load_recipes() -> Vec<Recipe> {
    let jei_folder = PathBuf::from("../jei");

    jei_folder
        .read_dir()
        .unwrap()
        .par_bridge()
        .filter_map(|entry| {
            let path = entry.ok()?.path();
            std::fs::read_to_string(&path)
                .ok()
                .and_then(|content| serde_json::from_str::<Vec<Recipe>>(&content).ok())
        })
        .flatten()
        .collect()
}

fn process_recipes(recipes: &[Recipe]) -> Vec<ProcessedRecipe> {
    recipes
        .iter()
        .map(|recipe| {
            let inputs = recipe
                .ingredients
                .iter()
                .filter(|ingredient| ingredient.role == "INPUT")
                .cloned()
                .collect();
            let outputs = recipe
                .ingredients
                .iter()
                .filter(|ingredient: &&Ingredient| ingredient.role == "OUTPUT")
                .cloned()
                .collect();
            ProcessedRecipe {
                recipe_id: recipe.recipe_object.clone(),
                category_title: recipe.category_title.clone(),
                inputs,
                outputs,
            }
        })
        .collect()
}

fn save_processed_recipes(recipes: &[ProcessedRecipe], path: &Path) {
    let encoded: Vec<u8> = bincode::serialize(recipes).unwrap();
    let mut file = File::create(path).unwrap();
    file.write_all(&encoded).unwrap();
}

fn load_processed_recipes(path: &Path) -> Vec<ProcessedRecipe> {
    let mut file = File::open(path).unwrap();
    let mut buffer = Vec::new();
    file.read_to_end(&mut buffer).unwrap();
    bincode::deserialize(&buffer).unwrap()
}

fn explore_recipe(
    recipe: &ProcessedRecipe,
    item_map: &HashMap<String, f32>,
    recipes: &[ProcessedRecipe],
    output_dir: &Path,
) {
    let start_emc: f32 = recipe
        .inputs
        .iter()
        .filter_map(|ingredient| {
            item_map
                .get(&ingredient.ingredient_id)
                .map(|&emc| emc * ingredient.ingredient_amount as f32)
        })
        .sum();

    let initial_buffer = recipe
        .inputs
        .iter()
        .map(|ing| ing.ingredient_id.clone())
        .collect::<Vec<String>>();

    let mut visited_recipes = Vec::new();
    let mut history = Vec::new();

    let result = traverse_recipes(
        start_emc,
        initial_buffer,
        &mut visited_recipes,
        &mut history,
        recipes,
        item_map,
        0,
        1, // Set max depth to 1
    );

    if let Some(history) = result {
        let output_file = output_dir.join(format!("{}.txt", Uuid::new_v4()));
        let mut file = File::create(output_file).expect("Unable to create file");
        for entry in history {
            writeln!(
                file,
                "[{}] Recipe: {}, Ingredients: {:?}, Resulting EMC: {}",
                entry.recipe_category, entry.recipe_id, entry.ingredients, entry.resulting_emc
            )
            .expect("Unable to write to file");
        }
    }
}

fn traverse_recipes(
    current_emc: f32,
    buffer: Vec<String>,
    visited_recipes: &mut Vec<String>,
    history: &mut Vec<HistoryEntry>,
    recipes: &[ProcessedRecipe],
    item_map: &HashMap<String, f32>,
    depth: usize,
    max_depth: usize,
) -> Option<Vec<HistoryEntry>> {
    if depth >= max_depth {
        return None;
    }

    for recipe in recipes.iter() {
        if visited_recipes.contains(&recipe.recipe_id) {
            continue;
        }

        let input_items: Vec<&Ingredient> = recipe
            .inputs
            .iter()
            .filter(|ingredient| buffer.contains(&ingredient.ingredient_id))
            .collect();

        if !input_items.is_empty() {
            visited_recipes.push(recipe.recipe_id.clone());

            let mut buffer_copy = buffer.clone();
            let enough_items = input_items.iter().all(|ingredient| {
                let count = buffer_copy.iter().filter(|id| *id == &ingredient.ingredient_id).count();
                if count >= ingredient.ingredient_amount as usize {
                    for _ in 0..ingredient.ingredient_amount {
                        if let Some(index) = buffer_copy.iter().position(|id| id == &ingredient.ingredient_id) {
                            buffer_copy.remove(index);
                        }
                    }
                    true
                } else {
                    false
                }
            });

            if !enough_items {
                continue;
            }

            let total_input_emc: f32 = input_items.iter().map(|ingredient| {
                item_map.get(&ingredient.ingredient_id)
                    .map(|&emc| emc * ingredient.ingredient_amount as f32)
                    .unwrap_or(0.0)
            }).sum();

            let mut total_output_emc = 0.0;
            for output in &recipe.outputs {
                if let Some(&emc) = item_map.get(&output.ingredient_id) {
                    total_output_emc += emc * output.ingredient_amount as f32;
                    buffer_copy.push(output.ingredient_id.clone());
                }
            }

            history.push(HistoryEntry {
                recipe_id: recipe.recipe_id.clone(),
                recipe_category: recipe.category_title.clone(),
                ingredients: input_items.iter().map(|ing| ing.ingredient_id.clone()).collect(),
                resulting_emc: total_output_emc,
            });

            if total_output_emc > total_input_emc {
                return Some(history.clone());
            }

            if depth + 1 < max_depth {
                if let Some(result) = traverse_recipes(
                    total_output_emc,
                    buffer_copy,
                    visited_recipes,
                    history,
                    recipes,
                    item_map,
                    depth + 1,
                    max_depth,
                ) {
                    return Some(result);
                }
            }

            visited_recipes.pop();
            history.pop();
        }
    }

    None
}

fn main() {
    let processed_file = PathBuf::from("processed.bin");
    let output_dir = PathBuf::from("outputs");

    fs::create_dir_all(&output_dir).expect("Unable to create output directory");

    let recipes: Vec<ProcessedRecipe> = if processed_file.exists() {
        load_processed_recipes(&processed_file)
    } else {
        let raw_recipes = load_recipes();
        let processed_recipes = process_recipes(&raw_recipes);
        save_processed_recipes(&processed_recipes, &processed_file);
        processed_recipes
    };

    let items_json = include_str!("../../items.json");
    let mut items: Vec<Item> = serde_json::from_str(items_json).unwrap();
    calculate_emc(&mut items);

    let item_map: HashMap<String, f32> = items
        .iter()
        .filter_map(|item| item.emc.map(|emc| (item.id.clone(), emc)))
        .collect();

    recipes
        .par_iter()
        .filter(|recipe| recipe.inputs.len() <= 9)
        .for_each(|recipe| explore_recipe(recipe, &item_map, &recipes, &output_dir));
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn first_25() {
        let items_json = include_str!("../../items.json");
        let mut items: Vec<Item> = serde_json::from_str(items_json).unwrap();
        calculate_emc(&mut items);

        for item in items.iter().filter(|x| x.emc.is_some()).take(25) {
            println!("{}: {}", item.id, item.emc.unwrap());
        }
    }

    #[test]
    fn test_load_recipes() {
        let recipes = load_recipes();
        assert!(recipes.len() > 10_000);
    }

    #[test]
    fn test_get_emc_from_tooltip() {
        let number_words = get_number_words();

        let tooltip = "EMC: 2";
        let emc = get_emc_from_tooltip(&number_words, tooltip).unwrap();
        assert_eq!(emc, 2f32);

        let tooltip = "a\nb\nEMC: 2,048 (✗)\nc\nd";
        let emc = get_emc_from_tooltip(&number_words, tooltip).unwrap();
        assert_eq!(emc, 2048f32);

        let tooltip = "a\nb\nEMC: 1.61 Billion (✗)\nc\nd";
        let emc = get_emc_from_tooltip(&number_words, tooltip).unwrap();
        assert_eq!(emc, 1_610_000_000f32);
    }
}
