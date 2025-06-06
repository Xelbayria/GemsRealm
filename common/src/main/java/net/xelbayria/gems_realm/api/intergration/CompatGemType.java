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

/**
 * Undetected GemType that doesn't met 2 requirements:
**/
// Put all undetected GemTypes here
public class CompatGemType {

    public static void init() {

        // CrystalCraft-Unlimited-Java
        for (int num = 1; num < 15; num++) {
            String zircon = "zircon_";
            switch (num) {
                case 1, 4, 6, 9 -> advancedGemFinder("crystalcraft_unlimited_java", zircon + num, zircon + "block_" + num);
                default -> simpleGemFinder("crystalcraft_unlimited_java", "zircon_" + num);
            }
        }
        simpleGemFinder("crystalcraft_unlimited_java", "black_pearl");
        simpleGemFinder("crystalcraft_unlimited_java", "blue_pearl");
        simpleGemFinder("crystalcraft_unlimited_java", "brown_pearl");
        simpleGemFinder("crystalcraft_unlimited_java", "dark_blue_pearl");
        simpleGemFinder("crystalcraft_unlimited_java", "green_pearl");
        simpleGemFinder("crystalcraft_unlimited_java", "olive_pearl");
        simpleGemFinder("crystalcraft_unlimited_java", "orange_pearl");
        simpleGemFinder("crystalcraft_unlimited_java", "pink_pearl");
        simpleGemFinder("crystalcraft_unlimited_java", "purple_pearl");
        simpleGemFinder("crystalcraft_unlimited_java", "red_pearl");
        simpleGemFinder("crystalcraft_unlimited_java", "white_pearl");
        simpleGemFinder("crystalcraft_unlimited_java", "yellow_pearl");
        mediumGemFinder("crystalcraft_unlimited_java", "pearl_item", "pearl_block");
        simpleGemFinder("crystalcraft_unlimited_java", "yellow_catseye");
        simpleGemFinder("crystalcraft_unlimited_java", "cyber_crystal");
        simpleGemFinder("crystalcraft_unlimited_java", "corrupted_cyber_crystal");
        simpleGemFinder("crystalcraft_unlimited_java", "shadow_diamond");
        mediumGemFinder("crystalcraft_unlimited_java", "solar_diamond", "solar_block");
        mediumGemFinder("crystalcraft_unlimited_java", "eclipse_diamond", "eclipse_block");
        simpleGemFinder("crystalcraft_unlimited_java", "raspberry_diamond");
        simpleGemFinder("crystalcraft_unlimited_java", "maroon_diamond");
        mediumGemFinder("crystalcraft_unlimited_java", "dark_green_star_sapphire", "");
        simpleGemFinder("crystalcraft_unlimited_java", "anti_humoranium");
        simpleGemFinder("crystalcraft_unlimited_java", "sunset_jasper");
        simpleGemFinder("crystalcraft_unlimited_java", "zebra_jasper");
        simpleGemFinder("crystalcraft_unlimited_java", "pitambari_neelam");
        mediumGemFinder("crystalcraft_unlimited_java", "snowflake_obsidian", "snowflake_block");
        mediumGemFinder("crystalcraft_unlimited_java", "matrix_opal", "matrix_block");
        simpleGemFinder("crystalcraft_unlimited_java", "peacock_ore");
        simpleGemFinder("crystalcraft_unlimited_java", "smoky_quartz");
        simpleGemFinder("crystalcraft_unlimited_java", "soul_quartz");
        simpleGemFinder("crystalcraft_unlimited_java", "ghoul_quartz");
        simpleGemFinder("crystalcraft_unlimited_java", "blood_quartz");
        mediumGemFinder("crystalcraft_unlimited_java", "medusa_quartz", "medusa_block");
        mediumGemFinder("crystalcraft_unlimited_java", "dragon_scale", "dragon_block");
        simpleGemFinder("crystalcraft_unlimited_java", "peacock_topaz");
        mediumGemFinder("crystalcraft_unlimited_java", "watermelon_tourmaline", "watermelon_block");
        mediumGemFinder("crystalcraft_unlimited_java", "australian_sapphire", "rare_sapphire_block");
        mediumGemFinder("crystalcraft_unlimited_java", "saphire", "sapphire_block");
        mediumGemFinder("crystalcraft_unlimited_java", "sapphire", "blue_sapphire_block");
        mediumGemFinder("crystalcraft_unlimited_java", "green_star_sapphire", "green_star_sapphire_block_2");
        mediumGemFinder("crystalcraft_unlimited_java", "dark_green_star_sapphire", "green_star_sapphire_block");
        mediumGemFinder("crystalcraft_unlimited_java", "rare_sapphire", "rare_sapphire_block_recipe");
    }

    //!! GENERAL METHODS
    public static void simpleGemFinder(String modId, String nameGemType, String... nameChildren) {
        advancedGemFinder(modId, nameGemType, nameGemType + "_block", nameChildren);
    }

    public static void mediumGemFinder(String modId, String nameGemType, String nameGemBlock, String... nameChildren) {
        advancedGemFinder(modId, nameGemType, nameGemBlock, nameChildren);
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

    protected static final Set<String> childKeySafe = Set.of(
            //GENERAL
                //BLOCK
            "block", "stairs", "slab", "wall", "button", "pressure_plate", "smooth_stone",
            "polished", "polished_stairs", "polished_slab",
            "bricks", "brick_stairs", "brick_slab", "brick_wall", "cracked_bricks", "brick_tiles",
            "mossy_bricks", "mossy_brick_slab", "mossy_brick_stairs", "mossy_brick_wall",
                //ITEM
            "ingot", "nugget",

            //CrystalType
            "cluster",

                //ITEM
            "shard"
    );

}
