package vswe.stevesfactory;


import net.minecraft.util.StatCollector;

public enum Localization {
    TRIGGER_SHORT,
    TRIGGER_LONG,
    INPUT_SHORT,
    INPUT_LONG,
    OUTPUT_SHORT,
    OUTPUT_LONG,
    CONDITION_SHORT,
    CONDITION_LONG,
    FLOW_CONTROL_SHORT,
    FLOW_CONTROL_LONG,
    LIQUID_INPUT_SHORT,
    LIQUID_INPUT_LONG,
    LIQUID_OUTPUT_SHORT,
    LIQUID_OUTPUT_LONG,
    LIQUID_CONDITION_SHORT,
    LIQUID_CONDITION_LONG,
    REDSTONE_EMITTER_SHORT,
    REDSTONE_EMITTER_LONG,
    REDSTONE_CONDITION_SHORT,
    REDSTONE_CONDITION_LONG,
    CONTAINER_VARIABLE_SHORT,
    CONTAINER_VARIABLE_LONG,
    FOR_EACH_LOOP_SHORT,
    FOR_EACH_LOOP_LONG,
    AUTO_CRAFTER_SHORT,
    AUTO_CRAFTER_LONG,
    GROUP_SHORT,
    GROUP_LONG,
    NODE_SHORT,
    NODE_LONG,
    CAMOUFLAGE_SHORT,
    CAMOUFLAGE_LONG,
    SIGN_SHORT,
    SIGN_LONG ,

    CONNECTION_INPUT,
    CONNECTION_OUTPUT,
    CONNECTION_INTERVAL,
    CONNECTION_ON_HIGH_REDSTONE_PULSE,
    CONNECTION_ON_LOW_REDSTONE_PULSE,
    CONNECTION_WHILE_HIGH_REDSTONE,
    CONNECTION_WHILE_LOW_REDSTONE,
    CONNECTION_TRUE,
    CONNECTION_FALSE,
    CONNECTION_FOR_EACH,
    CONNECTION_ON_BLOCK_UPDATE,
    CONNECTION_ON_HIGH_BLOCK_PULSE,
    CONNECTION_WHILE_HIGH_BLOCK,
    CONNECTION_ON_LOW_BLOCK_PULSE,
    CONNECTION_WHILE_LOW_BLOCK,

    CONNECTION_SET_STANDARD,
    CONNECTION_SET_INTERVAL,
    CONNECTION_SET_REDSTONE,
    CONNECTION_SET_CONDITION,
    CONNECTION_SET_COLLECTOR_2,
    CONNECTION_SET_COLLECTOR_5,
    CONNECTION_SET_SPLIT_2,
    CONNECTION_SET_SPLIT_5,
    CONNECTION_SET_DECLARATION,
    CONNECTION_SET_FOR_EACH,
    CONNECTION_SET_BUD,
    CONNECTION_SET_OUTPUT_NODE,
    CONNECTION_SET_INPUT_NODE,
    CONNECTION_SET_DYNAMIC,
    CONNECTION_SET_CHAT,

    DETECTOR_MENU,
    REQUIRE_ALL_TARGETS,
    REQUIRE_ONE_TARGET,
    RUN_SHARED_ONCE,
    RUN_ONE_PER_TARGET,
    SELECTED,
    OVERFLOW_MENU,
    NO_DETECTOR_ERROR,
    NO_OVERFLOW_ERROR,
    OVERFLOW_INFO,
    CONTAINER_TYPE_MENU,
    CRAFTING_MENU,
    EMITTER_MENU,
    NO_EMITTER_ERROR,
    REQUIRES_ALL,
    IF_ANY,
    WHITE_LIST,
    BLACK_LIST,
    EMPTY_TANK,
    FILLED_TANK,
    STRONG_POWER,
    WEAK_POWER,
    SEQUENTIAL,
    SPLIT,
    INTERVAL_MENU,
    INTERVAL_INFO,
    SECOND,
    INVENTORY_MENU,
    NO_INVENTORY_ERROR,
    ITEM_MENU,
    DAMAGE_VALUE,
    NO_CONDITION_ERROR,
    BUCKETS,
    MILLI_BUCKETS,
    LIQUIDS_MENU,
    USE_ALL,
    REVERSED,
    LOOP_ORDER_MENU,
    VALUE_ORDER_MENU,
    REDSTONE_NODE_MENU,
    NO_NODE_ERROR,
    DO_EMIT_PULSE,
    SECONDS,
    TICKS,
    PULSE_MENU,
    RECEIVERS_MENU,
    NO_RECEIVER_ERROR,
    REDSTONE_OUTPUT_MENU,
    REDSTONE_STRENGTH,
    DIGITAL_TOGGLE,
    NO_REDSTONE_SIDES_ERROR,
    REDSTONE_SIDES_MENU,
    REDSTONE_SIDES_INFO,
    UPDATE_SIDES_INFO,
    REDSTONE_SIDES_MENU_TRIGGER,
    UPDATE_SIDES_MENU,
    NO_SIDES_ERROR,
    INVERT_SELECTION,
    REDSTONE_STRENGTH_MENU,
    REDSTONE_STRENGTH_INFO,
    THROUGH,
    INVALID_REDSTONE_RANGE_ERROR,
    REDUNDANT_REDSTONE_RANGE_ERROR,
    REDSTONE_STRENGTH_MENU_CONDITION,
    CONNECTIONS_MENU,
    FAIR_SPLIT,
    EMPTY_PINS,
    SPLIT_MENU,
    SPECIFY_AMOUNT,
    DELETE,
    DELETE_ITEM_SELECTION,
    GO_BACK,
    CANCEL,
    EMPTY_WHITE_LIST_ERROR,
    TANK_MENU,
    NO_TANK_ERROR,
    ACTIVATE,
    DEACTIVATE,
    ACTIVATE_LONG,
    DEACTIVATE_LONG,
    NO_DIRECTION_ERROR,
    ALL_SLOTS,
    ID_RANGE,
    ALL_SLOTS_LONG,
    ID_RANGE_LONG,
    INVALID_RANGE,
    ADVANCED_MODE,
    SIMPLE_MODE,
    ADVANCED_MODE_LONG,
    SIMPLE_MODE_LONG,
    USE_ID,
    INVERT,
    UPDATE_BLOCK_MENU,
    META,
    GLOBAL_VALUE_SET,
    VARIABLE_MENU,
    NOT_DECLARED_ERROR,
    ALREADY_DECLARED_ERROR,
    VARIABLE_CONTAINERS_MENU,
    VARIABLE_LIST,
    VARIABLE_ELEMENT,
    LOOP_VARIABLE_MENU,
    LIST_NOT_DECLARED,
    ELEMENT_NOT_DECLARED,
    VARIABLE_WHITE,
    VARIABLE_ORANGE,
    VARIABLE_MAGENTA,
    VARIABLE_LIGHT_BLUE,
    VARIABLE_YELLOW,
    VARiABLE_LIME,
    VARIABLE_PINK,
    VARIABLE_GRAY,
    VARIABLE_LIGHT_GRAY,
    VARIABLE_CYAN,
    VARIABLE_PURPLE,
    VARIABLE_BLUE,
    VARIABLE_BROWN,
    VARIABLE_GREEN,
    VARIABLE_RED,
    VARIABLE_BLACK,
    NO_LIQUID_SELECTED,
    CHANGE_LIQUID,
    EDIT_SETTING,
    NO_ITEM_SELECTED,
    CHANGE_ITEM,
    FULL_DESCRIPTION,
    DETECTION_PRECISE,
    DETECTION_NBT_FUZZY,
    DETECTION_FUZZY,
    DETECTION_ORE_DICTIONARY,
    ITEMS_FOUND,
    REDSTONE_EMITTER_SIDES_MENU,
    REDSTONE_EMITTER_SIDES_INFO,
    TYPE_INVENTORY,
    TYPE_TANK,
    TYPE_EMITTER,
    TYPE_RECEIVER,
    TYPE_NODE,
    TYPE_BUD,
    ORDER_STANDARD,
    ORDER_CABLE,
    ORDER_RANDOM,
    EXTEND_OLD,
    KEEP_ALL,
    KEEP_OLD,
    KEEP_NEW,
    FIXED,
    TOGGLE,
    MAX,
    MIN,
    INCREASE,
    DECREASE,
    FORWARD,
    BACKWARD,
    GLOBAL,
    LOCAL,
    ADD,
    REMOVE,
    SET,
    DOWN,
    UP,
    NORTH,
    SOUTH,
    WEST,
    EAST,
    GROUP_MENU,
    GROUP_INFO,
    FILTER_POSITION_LABEL,
    FILTER_DISTANCE_LABEL,
    FILTER_SELECTION_LABEL,
    FILTER_VARIABLE_LABEL,
    X,
    Y,
    Z,
    CABLE_DISTANCE,
    DISTANCE,
    ONLY_SELECTED,
    HIDE_SELECTED,
    RELOAD_ON_CHANGE,
    USE_UNUSED,
    USE_FILTER,
    ABSOLUTE_RANGES,
    FILTER_SHORT,
    FILTER_LONG,
    MULTI_SHORT,
    MULTI_LONG,
    SUB_MENU_SHORT,
    SUB_MENU_LONG,
    CLEAR_SHORT,
    CLEAR_LONG,
    SELECT_ALL_SHORT,
    SELECT_ALL_LONG,
    SELECT_NONE_SHORT,
    SELECT_NONE_LONG,
    SELECT_INVERT_SHORT,
    SELECT_INVERT_LONG,
    SELECTED_CONTAINERS,
    NO_MULTI_SETTING,
    SINGLE_SELECTED,
    RELATIVE_COORDINATES,
    BLOCK_AWAY,
    BLOCKS_AWAY,
    CABLE_AWAY,
    CABLES_AWAY,
    EMPTY_CLUSTER,
    TYPE_CAMOUFLAGE,
    CAMOUFLAGE_SIDES_INFO,
    CAMOUFLAGE_SIDES_NAME,
    CAMOUFLAGE_ITEM_MENU,
    CAMOUFLAGE_BLOCK_MENU,
    NO_CAMOUFLAGE_BLOCKS_ERROR,
    CLEAR_CAMOUFLAGE,
    SET_CAMOUFLAGE,
    CAMOUFLAGE_INFO,
    NO_CAMOUFLAGE_SETTING,
    SELECT_VARIABLE_SHORT,
    SELECT_VARIABLE_LONG,
    CAMOUFLAGE_ONLY_OUTSIDE,
    CAMOUFLAGE_ONLY_INSIDE,
    CAMOUFLAGE_OPPOSITE_INSIDE,
    CAMOUFLAGE_SAME_INSIDE,
    CAMOUFLAGE_NO_UPDATE,
    CAMOUFLAGE_BOUNDS_USE,
    CAMOUFLAGE_COLLISION_USE,
    CAMOUFLAGE_COLLISION_FULL,
    TO,
    BOUNDS_MENU,
    BOUNDS_WARNING,
    INSIDE_MENU,
    INSIDE_WARNING,
    MOD_GROUPING,
    ALL_ITEMS,
    TARGET_MENU,
    TYPE_SIGN,
    SIGNS,
    NO_SIGNS_ERROR,
    SIGN_TEXT,
    UPDATE_LINE,
    TOOLTIP_EXTRA_INFO,
    TOOLTIP_LOCK,
    TOOLTIP_UNLOCK,
    TOOLTIP_ADJACENT,

    DELETE_COMMAND,
    EXIT_GROUP,
    EXIT_GROUP_DROP,
    CREATE_COMMAND,
    MAXIMUM_COMPONENT_ERROR,
    COMMANDS,
    PREFERENCES,
    SETTINGS,
    CLOSE_GROUP_LABEL,
    OPEN_MENU_LARGE_HIT_BOX,
    OPEN_MENU_LARGE_HIT_BOX_MENU,
    OPEN_GROUP_QUICK,
    SHOW_COMMAND_TYPE,
    AUTO_SIDE,
    AUTO_BLACK_LIST,
    LIMITLESS,
    ENLARGE_INTERFACES,


    GIVE_PERMISSION,
    REVOKE_PERMISSION,
    ACTIVATE_USER,
    DEACTIVATE_USER,
    DELETE_USER,
    MAKE_EDITOR,
    REMOVE_EDITOR,
    SHOW_LIST_TO_ALL,
    SHOW_TO_EDITORS,
    ENABLE_CREATIVE_MODE,
    DISABLE_CREATIVE_MODE,
    PAGE,
    OF,
    NO_ACCESS,
    EDITOR_DESCRIPTION_SHORT,
    USER_DESCRIPTION_SHORT,
    EDITOR_DESCRIPTION_LONG,
    USER_DESCRIPTION_LONG ,
    PERMISSION_OWNER,
    PERMISSION_EDITOR,
    PERMISSION_USER,
    PERMISSION_RESTRICTED,
    PERMISSION_CREATIVE,
    PERMISSION_INVENTORY,
    PERMISSION_DENIED;



    private String name;

    private Localization() {
        String[] split = super.toString().split("_");
        this.name = "";
        for (String s : split) {
            this.name += s.charAt(0) + s.substring(1).toLowerCase();
        }
    }

    public String toString() {
        return StatCollector.translateToLocal("gui." + StevesFactoryManager.UNLOCALIZED_START + name);
    }

    public static Localization getForgeDirectionLocalization(int id) {
        switch (id) {
            case 0:
                return DOWN;
            case 1:
                return UP;
            case 2:
                return NORTH;
            case 3:
                return SOUTH;
            case 4:
                return WEST;
            default:
                return EAST;
        }
    }
}