package ca.teamdman.sfm.common.item;

import ca.teamdman.sfm.client.render.FormItemExtensions;
import ca.teamdman.sfm.common.registry.SFMItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class FormItem extends Item {
    public FormItem() {
        super(new Item.Properties().tab(SFMItems.TAB));
    }

    public static ItemStack getForm(ItemStack stack) {
        var formStack = new ItemStack(SFMItems.FORM_ITEM.get());
        formStack.getOrCreateTag().put("reference", stack.serializeNBT());
        return formStack;
    }

    public static ItemStack getReference(ItemStack stack) {
        return ItemStack.of(stack.getOrCreateTag().getCompound("reference"));
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new FormItemExtensions());
    }

    @Override
    public void appendHoverText(
            ItemStack pStack,
            @Nullable Level pLevel,
            List<Component> pTooltipComponents,
            TooltipFlag pIsAdvanced
    ) {
        if (pStack.hasTag()) {
            var reference = getReference(pStack);
            if (!reference.isEmpty()) {
                pTooltipComponents.add(reference.getHoverName());
                reference.getItem().appendHoverText(reference, pLevel, pTooltipComponents, pIsAdvanced);
            }
        }
    }
}
