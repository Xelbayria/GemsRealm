package net.xelbayria.gems_realm.configs;

import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigBuilder;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigType;
import net.mehvahdjukaar.moonlight.api.platform.configs.ModConfigHolder;
import net.xelbayria.gems_realm.GemsRealm;

import java.util.List;
import java.util.function.Supplier;

public class UnsafeDisablerConfigs {

    public static Supplier<List<String>> crystalTypeList;
    public static Supplier<List<String>> dustTypeList;
    public static Supplier<List<String>> gemTypeList;
    public static Supplier<List<String>> metalTypeList;
    public static Supplier<List<String>> entrySetList;

    public static ModConfigHolder CONFIG_SPEC;

    public static void init() {}

    static {

        ConfigBuilder builder = ConfigBuilder.create(GemsRealm.res("hazardous"), ConfigType.COMMON);

        String comment = """
                    ═════════════════════════ Attention ═════════════════════════
                    Don't use this if you don't know what you are doing
                        REASON:
                    This file is a conditional registration. This is harmless in Singleplayer World,
                    but harmful in SERVER because you won't able to join.

                    ══════════════════════════ Detail ═══════════════════════════
                    This file allow you to exclude StoneTypes, MudType, EntrySet, or a Module
                    1) You can find their names for StoneTypes, MudType, or EntrySet in `everycomp-entries.toml`
                    2) Leave a value empty to disable that rule.

                    Module - is a Supported Mod, just a modId is sufficient.
                    EntrySet - is a FurnitureType or DecorativeType, it is either Block or Item.


                    NOTE: blacklisting a Module will be applied to Wood-Good, Stone-Zone, Gems-Realm - if you want to blacklist, then use everycomp-hazardous.toml

                    ─────────────────────────── Other ───────────────────────────
                    (Abbreviation) RegEx <=> Regular Expression
                """;
        builder.comment(comment);

        builder.push("crystaltype");
        String crystalTypeExample = """
                    EXAMPLE: blacklist = [
                        "forestry:.*fireproof.*",\t\tCOMMENT: .* is an RegEx, it exclude all of CrystalType containing "fireproof" from Forestry
                        "biomesoplenty:.*",\t\t\t\tCOMMENT: .* is a RegEx/Advanced-WildCard, it exclude all of CrystalType from Wood Mod for any Module
                        "biomesoplenty:redwood"\t\tCOMMENT: exclude redwood from Wood Mod for any module
                    ]
                """;
        crystalTypeList = builder.comment("Exclude one or all of Supported-Mod for CrystalType \n"+crystalTypeExample).define("blacklist", List.of());
        builder.pop();

        builder.push("dusttype");
        dustTypeList = builder.comment("Exclude one or all of Supported-Mod for DustType\n\tThe example is same as CrystalType's").define("blacklist", List.of());
        builder.pop();

        builder.push("gemtype");
        gemTypeList = builder.comment("Exclude one or all of Supported-Mod for GemType\n\tThe example is same as CrystalType's").define("blacklist", List.of());
        builder.pop();

        builder.push("metaltype");
        metalTypeList = builder.comment("Exclude one or all of Supported-Mod for MetalType\n\tThe example is same as CrystalType's").define("blacklist", List.of());
        builder.pop();

        builder.push("entryset");
        String entrysetExample = """
                    This will only Exclude one specific or all of Gems-Realm's Supported-Blocks or Supported-Items
                    EXAMPLE: blacklist = [
                        "chipped:checkered_trapdoor",\tCOMMENT: chipped:checkered_oak_trapdoor without "oak"
                        "variantvanillablocks:chest",\tCOMMENT: variantvanillablocks:oak_chest without "oak"
                        "chipped:.*"\t\t\t\t\tCOMMENT: .* is a RegEx/Advanced-WildCard which will exclude all of EntrySets from one Module
                    ]
                """;
        entrySetList = builder.comment("Exclude EntrySet from the module for All of CrystalType, DustType, GemType, MetalType\n"+entrysetExample).define("blacklist", List.of());
        builder.pop();

        CONFIG_SPEC = builder.build();

        CONFIG_SPEC.forceLoad();

        // Warning Message
        if (!crystalTypeList.get().isEmpty() || !dustTypeList.get().isEmpty() || !gemTypeList.get().isEmpty()
                ||!metalTypeList.get().isEmpty() || !entrySetList.get().isEmpty()) {
            GemsRealm.LOGGER.warn("""
                            \n
                            ============================================================
                            |                                                          |
                            |                        ATTENTION                         |
                            |  You are using conditional registration via Gems-Realm.  |
                            |  Proceed at your own risk and do not complain if you     |
                            |  CANNOT connect to servers                               |
                            |                                                          |
                            ============================================================
                    """
            );
        }

    }

}
