package net.xelbayria.gems_realm.api.intergration;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.minecraft.resources.ResourceLocation;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.api.set.MetalType;

import static net.xelbayria.gems_realm.api.intergration.CompatGemType.childKeySafe;
import static net.xelbayria.gems_realm.api.intergration.CompatGemType.getChildKeyFrom;

/** Undetected MetalType that doesn't met 2 requirements:
 * block - due to different name (important)
 * ingot
**/
// Put all undetected CrystalTypes here
public class CompatMetalType {

    public static void init() {

        // Unusual End
        simpleMetalFinder("unusualend", "pearlescent");

        // Sea Dwellers
        simpleMetalFinder("seadwellers", "depth");

        // Mo' Shiz
        advancedMetalFinder("ms", "refined_quartz", "resources/refined_quartz_block");
        advancedMetalFinder("ms", "cast_iron", "resources/cast_iron_block");

        // CrystalCraft-Unlimited-Java
        simpleMetalFinder("crystalcraft_unlimited_java", "orichalcum", "ingot-orichalcum");
        simpleMetalFinder("crystalcraft_unlimited_java", "aluminium", "ingot-aluminium");
        simpleMetalFinder("crystalcraft_unlimited_java", "titanium", "ingot-titanium");
        simpleMetalFinder("crystalcraft_unlimited_java", "uranium", "ingot-uranium");
        advancedMetalFinder("crystalcraft_unlimited_java", "withered_steel", "wither_block", "ingot-withered_steel");
        simpleMetalFinder("crystalcraft_unlimited_java", "bronze", "ingot-bronze");
        simpleMetalFinder("crystalcraft_unlimited_java", "silver", "ingot-silver");
        simpleMetalFinder("crystalcraft_unlimited_java", "platinum", "ingot-platinum");
        simpleMetalFinder("crystalcraft_unlimited_java", "mythril", "ingot-mythril");
        simpleMetalFinder("crystalcraft_unlimited_java", "adamantite", "ingot-adamantite");
        simpleMetalFinder("crystalcraft_unlimited_java", "plutonium", "ingot-plutonium");
        simpleMetalFinder("crystalcraft_unlimited_java", "unoptanium", "ingot-unoptanium");
        simpleMetalFinder("crystalcraft_unlimited_java", "crimson_gold", "ingot-crimson_gold");
        simpleMetalFinder("crystalcraft_unlimited_java", "tin", "ingot-tin");
        simpleMetalFinder("crystalcraft_unlimited_java", "iridium", "ingot-iridium");
        simpleMetalFinder("crystalcraft_unlimited_java", "steel", "ingot-steel");
        simpleMetalFinder("crystalcraft_unlimited_java", "nickel", "ingot-nickel");
        simpleMetalFinder("crystalcraft_unlimited_java", "zinc", "ingot-zinc");
        simpleMetalFinder("crystalcraft_unlimited_java", "lead", "ingot-lead");
        simpleMetalFinder("crystalcraft_unlimited_java", "brass", "ingot-brass");
        simpleMetalFinder("crystalcraft_unlimited_java", "tungsten", "ingot-tungsten");
        simpleMetalFinder("crystalcraft_unlimited_java", "rose_gold", "ingot-rose_gold");
        simpleMetalFinder("crystalcraft_unlimited_java", "chrome", "ingot-chrome");
        simpleMetalFinder("crystalcraft_unlimited_java", "magnesium", "ingot-magnesium");
        simpleMetalFinder("crystalcraft_unlimited_java", "bismuth", "ingot-bismuth");
        simpleMetalFinder("crystalcraft_unlimited_java", "electrum", "ingot-electrum");
        simpleMetalFinder("crystalcraft_unlimited_java", "matizium", "ingot-matizium");
        simpleMetalFinder("crystalcraft_unlimited_java", "yurium", "ingot-yurium");
        simpleMetalFinder("crystalcraft_unlimited_java", "xernium", "ingot-xernium");
        simpleMetalFinder("crystalcraft_unlimited_java", "palladium", "ingot-palladium");
        simpleMetalFinder("crystalcraft_unlimited_java", "purple_gold", "ingot-purple_gold");
        simpleMetalFinder("crystalcraft_unlimited_java", "green_gold", "ingot-green_gold");
        simpleMetalFinder("crystalcraft_unlimited_java", "cupronickel", "ingot-cupronickel");
        simpleMetalFinder("crystalcraft_unlimited_java", "pelenium", "ingot-pelenium");
        simpleMetalFinder("crystalcraft_unlimited_java", "maradonyx", "ingot-maradonyx");
        simpleMetalFinder("crystalcraft_unlimited_java", "palintinium", "ingot-palintinium");
        simpleMetalFinder("crystalcraft_unlimited_java", "lithium", "ingot-lithium");
        simpleMetalFinder("crystalcraft_unlimited_java", "silicium", "ingot-silicium");
        advancedMetalFinder("crystalcraft_unlimited_java", "radium", "radium_reactor", "ingot-radium");
        simpleMetalFinder("crystalcraft_unlimited_java", "technetium", "ingot-technetium");
        simpleMetalFinder("crystalcraft_unlimited_java", "ruthenium", "ingot-ruthenium");
        simpleMetalFinder("crystalcraft_unlimited_java", "rhodium", "ingot-rhodium");
        simpleMetalFinder("crystalcraft_unlimited_java", "cadmium", "ingot-cadmium");
        simpleMetalFinder("crystalcraft_unlimited_java", "tantalum", "ingot-tantalum");
        simpleMetalFinder("crystalcraft_unlimited_java", "holmium", "ingot-holmium");
        simpleMetalFinder("crystalcraft_unlimited_java", "osmium", "ingot-osmium");
        simpleMetalFinder("crystalcraft_unlimited_java", "neptunium", "ingot-neptunium");
        simpleMetalFinder("crystalcraft_unlimited_java", "galaxite", "ingot-galaxite");
        simpleMetalFinder("crystalcraft_unlimited_java", "pyrite", "ingot-pyrite");
        simpleMetalFinder("crystalcraft_unlimited_java", "seaborgium", "ingot-seaborgium");
        simpleMetalFinder("crystalcraft_unlimited_java", "ilmenite", "ingot-ilmenite");
        simpleMetalFinder("crystalcraft_unlimited_java", "cobalt", "ingot-cobalt");
        simpleMetalFinder("crystalcraft_unlimited_java", "carnotite", "ingot-carnotite");
        simpleMetalFinder("crystalcraft_unlimited_java", "antimony", "ingot-antimony");
        simpleMetalFinder("crystalcraft_unlimited_java", "hydro_pottasium");
        simpleMetalFinder("crystalcraft_unlimited_java", "americium");
        simpleMetalFinder("crystalcraft_unlimited_java", "thorium");
        simpleMetalFinder("crystalcraft_unlimited_java", "pottasium");
        simpleMetalFinder("crystalcraft_unlimited_java", "indium");
        simpleMetalFinder("crystalcraft_unlimited_java", "scandium");
        simpleMetalFinder("crystalcraft_unlimited_java", "vanadium");
        simpleMetalFinder("crystalcraft_unlimited_java", "manganese");
        simpleMetalFinder("crystalcraft_unlimited_java", "yttrium");
        simpleMetalFinder("crystalcraft_unlimited_java", "zirconium");
        simpleMetalFinder("crystalcraft_unlimited_java", "niobium");
        simpleMetalFinder("crystalcraft_unlimited_java", "molybdenum");
        simpleMetalFinder("crystalcraft_unlimited_java", "europium");
        simpleMetalFinder("crystalcraft_unlimited_java", "calcium");
        advancedMetalFinder("crystalcraft_unlimited_java", "meteorite", "meteorite");
        advancedMetalFinder("crystalcraft_unlimited_java", "neon_meteorite", "neon", "ingot-neon_meteorite");
    }

    //!! SIMPLE FINDER
    /**
     * @param modId - mod id of the mod
     * @param nameMetalType - name of MetalType without "_block" or "_ingot"
     * @param nameChildren - childkey-ID_of_the_children or nameMetalType_ingot
     */
    public static void simpleMetalFinder(String modId, String nameMetalType, String... nameChildren) {
        advancedMetalFinder(modId, nameMetalType, nameMetalType +"_block", nameChildren);
    }

    //!! ADVANCED FINDER
    public static void advancedMetalFinder(String modId, String nameMetalType, String nameBlockMetal, String... nameChildren) {
        if (PlatHelper.isModLoaded(modId)) {
            var metaltypeFinder = MetalType.Finder.simple(modId, nameMetalType, nameBlockMetal);

            for (String currentChild : nameChildren) {
                String childKey = getChildKeyFrom(currentChild);
                String blockId = currentChild.split("-")[1];
                ResourceLocation childId = (blockId.contains(":"))
                        ? new ResourceLocation(blockId)
                        : new ResourceLocation(modId, blockId);

                if (currentChild.contains("-") && childKeySafe.contains(childKey))
                    metaltypeFinder.addChild(childKey, childId);
                else if (childKeySafe.contains(childKey))
                    metaltypeFinder.addChild(childKey, currentChild);
                else
                    GemsRealm.LOGGER.warn("CompatMetalType: Incorrect childKey - {} for {}", childKey, currentChild);

            }

            BlockSetAPI.addBlockTypeFinder(MetalType.class, metaltypeFinder);
        }
    }

}
