package net.xelbayria.gems_realm.configs;

import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigBuilder;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigType;
import net.mehvahdjukaar.moonlight.api.platform.configs.ModConfigHolder;
import net.xelbayria.gems_realm.GemsRealm;

import java.util.function.Supplier;

public class GRConfigs {

    public static final ModConfigHolder SPEC;

    public static final Supplier<Boolean> TAB_ENABLED;
    public static final Supplier<Boolean> TAB_ITEM_SEARCH_ENABLED;
    public static final Supplier<Boolean> DISABLE_CYCLE_ITEM_RENDERER;
    public static final Supplier<String> CREATIVE_TAB_ICON;

    static {
        ConfigBuilder builder = ConfigBuilder.create(GemsRealm.MOD_ID, ConfigType.COMMON);

        builder.push("general");

        TAB_ENABLED = builder.comment("Puts all the added items into a new Every Compat tab instead of their own mod tabs. Be warned that if disabled it could cause some issue with some mods that have custom tabs")
                .define("creative_tab", true);
        TAB_ITEM_SEARCH_ENABLED = builder.comment("Allow the item_search or searchBar to be visible.")
                .define("tab_item_search", true);

        DISABLE_CYCLE_ITEM_RENDERER = builder.comment("Disable Cycle Item Renderer - The Gems-Realm's Creative Tab where it is cycling every item (The items from Gems-Realm). Game Restart is required for this to take effect.")
                .gameRestart()
                .define("disable_cycle_item_renderer", false);
        CREATIVE_TAB_ICON = builder.comment("You can choose a item to be Gems-Realm's creative tab icon when Cycle Item Renderer is disabled. The value must be STRING like minecraft:oak_hanging_sign. Game Restart is required for this to take effect.")
                .gameRestart()
                .define("creative_tab_icon", "minecraft:barrier");

        builder.pop();

        SPEC = builder.build();

        SPEC.forceLoad(); //manually load early
    }

    public static void init() {

    }
}
