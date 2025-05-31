package net.xelbayria.gems_realm.api.intergration;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.minecraft.resources.ResourceLocation;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.api.set.GemType;
import net.xelbayria.gems_realm.api.set.MetalType;

import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/// MetalType Detection detect a MetalType that met 2 requirements:
        /// BASS_DRUM (sound)
        /// blockID: nameStoneType_bricks - only 2 words
// Put all undetected StoneTypes (including hardcoded ones) from mods in here to be included
public class CompatRockType {

    public static void init() {
    //!! METALTYPE

        // Mo' Shiz
        mediumMetalFinder("ms", "refined_quartz", "resources/refined_quartz_block");
        mediumMetalFinder("ms", "cast_iron", "resources/cast_iron_block");


        // CrystalCraft (Unlimited Java)
        simpleMetalFinder("crystalcraft_unlimited_java", "titanium", "ingot-crystalcraft_unlimited_java:titanium");
        simpleMetalFinder("crystalcraft_unlimited_java", "uranium", "ingot-crystalcraft_unlimited_java:uranium");
        simpleMetalFinder("crystalcraft_unlimited_java", "withered_steel", "ingot-crystalcraft_unlimited_java:withered_steel");
        simpleMetalFinder("crystalcraft_unlimited_java", "bronze", "ingot-crystalcraft_unlimited_java:bronze");
        simpleMetalFinder("crystalcraft_unlimited_java", "silver", "ingot-crystalcraft_unlimited_java:silver");
        simpleMetalFinder("crystalcraft_unlimited_java", "platinum", "ingot-crystalcraft_unlimited_java:platinum");
        simpleMetalFinder("crystalcraft_unlimited_java", "mythril", "ingot-crystalcraft_unlimited_java:mythril");
        simpleMetalFinder("crystalcraft_unlimited_java", "adamantite", "ingot-crystalcraft_unlimited_java:adamantite");
        simpleMetalFinder("crystalcraft_unlimited_java", "plutonium", "ingot-crystalcraft_unlimited_java:plutonium");
        simpleMetalFinder("crystalcraft_unlimited_java", "unoptanium", "ingot-crystalcraft_unlimited_java:unoptanium");
        simpleMetalFinder("crystalcraft_unlimited_java", "crimson_gold", "ingot-crystalcraft_unlimited_java:crimson_gold");
        simpleMetalFinder("crystalcraft_unlimited_java", "tin", "ingot-crystalcraft_unlimited_java:tin");
        simpleMetalFinder("crystalcraft_unlimited_java", "iridium", "ingot-crystalcraft_unlimited_java:iridium");
        simpleMetalFinder("crystalcraft_unlimited_java", "steel", "ingot-crystalcraft_unlimited_java:steel");
        simpleMetalFinder("crystalcraft_unlimited_java", "nickel", "ingot-crystalcraft_unlimited_java:nickel");
        simpleMetalFinder("crystalcraft_unlimited_java", "zinc", "ingot-crystalcraft_unlimited_java:zinc");
        simpleMetalFinder("crystalcraft_unlimited_java", "lead", "ingot-crystalcraft_unlimited_java:lead");
        simpleMetalFinder("crystalcraft_unlimited_java", "brass", "ingot-crystalcraft_unlimited_java:brass");
        simpleMetalFinder("crystalcraft_unlimited_java", "tungsten", "ingot-crystalcraft_unlimited_java:tungsten");
        simpleMetalFinder("crystalcraft_unlimited_java", "rose_gold", "ingot-crystalcraft_unlimited_java:rose_gold");
        simpleMetalFinder("crystalcraft_unlimited_java", "chrome", "ingot-crystalcraft_unlimited_java:chrome");
        simpleMetalFinder("crystalcraft_unlimited_java", "magnesium", "ingot-crystalcraft_unlimited_java:magnesium");
        simpleMetalFinder("crystalcraft_unlimited_java", "bismuth", "ingot-crystalcraft_unlimited_java:bismuth");
        simpleMetalFinder("crystalcraft_unlimited_java", "electrum", "ingot-crystalcraft_unlimited_java:electrum");
        simpleMetalFinder("crystalcraft_unlimited_java", "matizium", "ingot-crystalcraft_unlimited_java:matizium");
        simpleMetalFinder("crystalcraft_unlimited_java", "yurium", "ingot-crystalcraft_unlimited_java:yurium");
        simpleMetalFinder("crystalcraft_unlimited_java", "xernium", "ingot-crystalcraft_unlimited_java:xernium");
        simpleMetalFinder("crystalcraft_unlimited_java", "palladium", "ingot-crystalcraft_unlimited_java:palladium");
        simpleMetalFinder("crystalcraft_unlimited_java", "purple_gold", "ingot-crystalcraft_unlimited_java:purple_gold");
//        simpleMetalFinder("crystalcraft_unlimited_java", "green_gold", "ingot-crystalcraft_unlimited_java:green_gold");
        simpleMetalFinder("crystalcraft_unlimited_java", "cupronickel", "ingot-crystalcraft_unlimited_java:cupronickel");
        simpleMetalFinder("crystalcraft_unlimited_java", "pelenium", "ingot-crystalcraft_unlimited_java:pelenium");
        simpleMetalFinder("crystalcraft_unlimited_java", "maradonyx", "ingot-crystalcraft_unlimited_java:maradonyx");
        simpleMetalFinder("crystalcraft_unlimited_java", "palintinium", "ingot-crystalcraft_unlimited_java:palintinium");
        simpleMetalFinder("crystalcraft_unlimited_java", "neon_meteorite", "ingot-crystalcraft_unlimited_java:neon_meteorite");
        simpleMetalFinder("crystalcraft_unlimited_java", "lithium", "ingot-crystalcraft_unlimited_java:lithium");
        simpleMetalFinder("crystalcraft_unlimited_java", "silicium", "ingot-crystalcraft_unlimited_java:silicium");
        simpleMetalFinder("crystalcraft_unlimited_java", "radium", "ingot-crystalcraft_unlimited_java:radium");
        simpleMetalFinder("crystalcraft_unlimited_java", "technetium", "ingot-crystalcraft_unlimited_java:technetium");
        simpleMetalFinder("crystalcraft_unlimited_java", "ruthenium", "ingot-crystalcraft_unlimited_java:ruthenium");
        simpleMetalFinder("crystalcraft_unlimited_java", "rhodium", "ingot-crystalcraft_unlimited_java:rhodium");
        simpleMetalFinder("crystalcraft_unlimited_java", "cadmium", "ingot-crystalcraft_unlimited_java:cadmium");
        simpleMetalFinder("crystalcraft_unlimited_java", "tantalum", "ingot-crystalcraft_unlimited_java:tantalum");
        simpleMetalFinder("crystalcraft_unlimited_java", "holmium", "ingot-crystalcraft_unlimited_java:holmium");
        simpleMetalFinder("crystalcraft_unlimited_java", "osmium", "ingot-crystalcraft_unlimited_java:osmium");
        simpleMetalFinder("crystalcraft_unlimited_java", "neptunium", "ingot-crystalcraft_unlimited_java:neptunium");
        simpleMetalFinder("crystalcraft_unlimited_java", "galaxite", "ingot-crystalcraft_unlimited_java:galaxite");
        simpleMetalFinder("crystalcraft_unlimited_java", "hydro_pottasium", "ingot-crystalcraft_unlimited_java:hydro_pottasium_ingot");
        simpleMetalFinder("crystalcraft_unlimited_java", "americium", "ingot-crystalcraft_unlimited_java:americium_ingot");
        simpleMetalFinder("crystalcraft_unlimited_java", "thorium", "ingot-crystalcraft_unlimited_java:thorium_ingot");
        simpleMetalFinder("crystalcraft_unlimited_java", "pottasium", "ingot-crystalcraft_unlimited_java:pottasium_ingot");
        simpleMetalFinder("crystalcraft_unlimited_java", "indium", "ingot-crystalcraft_unlimited_java:indium_ingot");
        simpleMetalFinder("crystalcraft_unlimited_java", "scandium", "ingot-crystalcraft_unlimited_java:scandium_ingot");
        simpleMetalFinder("crystalcraft_unlimited_java", "vanadium", "ingot-crystalcraft_unlimited_java:vanadium_ingot");
        simpleMetalFinder("crystalcraft_unlimited_java", "manganese", "ingot-crystalcraft_unlimited_java:manganese_ingot");
        simpleMetalFinder("crystalcraft_unlimited_java", "yttrium", "ingot-crystalcraft_unlimited_java:yttrium_ingot");
        simpleMetalFinder("crystalcraft_unlimited_java", "zirconium", "ingot-crystalcraft_unlimited_java:zirconium_ingot");
        simpleMetalFinder("crystalcraft_unlimited_java", "niobium", "ingot-crystalcraft_unlimited_java:niobium_ingot");
        simpleMetalFinder("crystalcraft_unlimited_java", "molybdenum", "ingot-crystalcraft_unlimited_java:molybdenum_ingot");
        simpleMetalFinder("crystalcraft_unlimited_java", "europium", "ingot-crystalcraft_unlimited_java:europium_ingot");
        simpleMetalFinder("crystalcraft_unlimited_java", "calcium", "ingot-crystalcraft_unlimited_java:calcium_ingot");
    }

//!! MetalType
    public static void simpleMetalFinder(String modId, String nameMetalType, String... nameChildren) {
        advancedMetalFinder(modId, nameMetalType, nameMetalType +"_block", nameChildren);
    }

    public static void mediumMetalFinder(String modId, String nameMetalType, String nameMetalBlock, String... nameChildren) {
        advancedMetalFinder(modId, nameMetalType, nameMetalBlock, nameChildren);
    }


//!! GemType
    public static void simpleGemFinder(String modId, String nameGemType) {
        var gemtypeFinder = GemType.Finder.simple(modId, nameGemType, nameGemType);

        BlockSetAPI.addBlockTypeFinder(GemType.class, gemtypeFinder);
    }


//!! ADVANCED FINDER
    public static void advancedMetalFinder(String modId, String nameMetalType, String nameMetalBlock,  String... nameChildren) {
        if (PlatHelper.isModLoaded(modId)) {
            var metaltypeFinder = MetalType.Finder.simple(modId, nameMetalType, nameMetalBlock);

            for (String currentChild : nameChildren) {
                String childKey = getChildKeyFrom(currentChild);
                ResourceLocation childId = new ResourceLocation(currentChild.split("-")[1]);

                if (currentChild.contains("-") && childKeySafe.contains(childKey)) {
                    metaltypeFinder.addChild(childKey, childId);
                }
                else if (childKeySafe.contains(childKey)) metaltypeFinder.addChild(childKey, currentChild);
                else GemsRealm.LOGGER.warn("CompatMetalType: Incorrect childKey - {} for {}", childKey, currentChild);

            }

            BlockSetAPI.addBlockTypeFinder(MetalType.class, metaltypeFinder);
        }
    }

    public static void advancedGemFinder(String modId, String nameGemType, String nameGemBlock,  String... nameChildren) {
        if (PlatHelper.isModLoaded(modId)) {
            var gemtypeFinder = GemType.Finder.simple(modId, nameGemType, nameGemBlock);

            for (String currentChild : nameChildren) {
                String childKey = getChildKeyFrom(currentChild);
                ResourceLocation childId = new ResourceLocation(currentChild.split("-")[1]);

                if (currentChild.contains("-") && childKeySafe.contains(childKey)) {
                    gemtypeFinder.addChild(childKey, childId);
                }
                else if (childKeySafe.contains(childKey)) gemtypeFinder.addChild(childKey, currentChild);
                else GemsRealm.LOGGER.warn("CompatGemType: Incorrect childKey - {} for {}", childKey, currentChild);

            }

            BlockSetAPI.addBlockTypeFinder(GemType.class, gemtypeFinder);
        }
    }

    /// Get the keyword from block: stone_bricks, key: bricks
    @SuppressWarnings("RegExpAnonymousGroup")
    public static String getChildKeyFrom(String childBlock) {
        if (childBlock.contains("-")) {
            return childBlock.split("-")[0];
        }

        String lastword = childBlock.substring(childBlock.lastIndexOf("_") + 1);

        // With "bricks"
        if (childBlock.matches("\\w+_bricks?(?:_[a-z]+)?")) {
            Pattern pattern = Pattern.compile("\\w+_(bricks?)(_[a-z]+)?");
            Matcher matcher = pattern.matcher(childBlock);
            if (matcher.find()) {
                String suffix = (Objects.isNull(matcher.group(2))) ? matcher.group(1) : matcher.group(1) + matcher.group(2);
                return switch (suffix) {
                    case "brick", "bricks" -> "bricks";
                    case "brick_slab", "bricks_slab" -> "brick_slab";
                    case "brick_stairs", "bricks_stairs" -> "brick_stairs";
                    case "brick_wall", "bricks_wall" -> "brick_wall";
                    default -> lastword;
                };
            }
        }
        // Default
        return lastword;
    }

    private static final Set<String> childKeySafe = Set.of(
            //BLOCK
            "block", "stairs", "slab", "wall", "button", "pressure_plate", "smooth_stone",
//            "cobblestone", "mossy_cobblestone",
            "polished", "polished_stairs", "polished_slab",
            "bricks", "brick_stairs", "brick_slab", "brick_wall", "cracked_bricks", "brick_tiles",
            "mossy_bricks", "mossy_brick_slab", "mossy_brick_stairs", "mossy_brick_wall",
            //ITEM
            "ingot", "nugget"
    );

}
