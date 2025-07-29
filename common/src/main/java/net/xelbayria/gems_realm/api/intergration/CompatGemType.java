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

/** Undetected GemType that doesn't met 2 requirements:
 * block - due to different name (important)
 * ore
**/
// Put all undetected GemTypes here
public class CompatGemType extends CompatBlockType{

    public static void init() {

        // CrystalCraft-Unlimited-Java
        for (int num = 1; num < 15; num++) {
            String zircon = "zircon_";
            switch (num) {
                case 1, 4, 6, 9 -> advancedGemFinder("crystalcraft_unlimited_java", zircon + num, zircon + "block_" + num);
                default -> simpleGemFinder("crystalcraft_unlimited_java", "zircon_" + num);
            }
        }
        advancedGemFinder("crystalcraft_unlimited_java", "bixbite", "bixite_block");
        simpleGemFinder("crystalcraft_unlimited_java", "angerite");
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
        advancedGemFinder("crystalcraft_unlimited_java", "pearl_item", "pearl_block");
        simpleGemFinder("crystalcraft_unlimited_java", "purple_catseye");
        advancedGemFinder("crystalcraft_unlimited_java", "pink_catseye", "pink_tigerseye_block");
        simpleGemFinder("crystalcraft_unlimited_java", "white_catseye");
        simpleGemFinder("crystalcraft_unlimited_java", "red_catseye");
        simpleGemFinder("crystalcraft_unlimited_java", "orange_catseye");
        simpleGemFinder("crystalcraft_unlimited_java", "yellow_catseye");
        simpleGemFinder("crystalcraft_unlimited_java", "black_catseye");
        simpleGemFinder("crystalcraft_unlimited_java", "blue_catseye");
        advancedGemFinder("crystalcraft_unlimited_java", "brown_catseye", "tigerseye_block");
        simpleGemFinder("crystalcraft_unlimited_java", "crystal");
        simpleGemFinder("crystalcraft_unlimited_java", "cyber_crystal");
        simpleGemFinder("crystalcraft_unlimited_java", "corrupted_cyber_crystal");
        advancedGemFinder("crystalcraft_unlimited_java", "dark_blue_diamond", "blue_diamond");
        simpleGemFinder("crystalcraft_unlimited_java", "yellow_diamond");
        simpleGemFinder("crystalcraft_unlimited_java", "olive_diamond");
        simpleGemFinder("crystalcraft_unlimited_java", "green_diamond");
        simpleGemFinder("crystalcraft_unlimited_java", "brown_diamond");
        simpleGemFinder("crystalcraft_unlimited_java", "shadow_diamond");
        advancedGemFinder("crystalcraft_unlimited_java", "solar_diamond", "solar_block");
        advancedGemFinder("crystalcraft_unlimited_java", "eclipse_diamond", "eclipse_block");
        simpleGemFinder("crystalcraft_unlimited_java", "raspberry_diamond");
        simpleGemFinder("crystalcraft_unlimited_java", "maroon_diamond");
        simpleGemFinder("crystalcraft_unlimited_java", "anti_humoranium");
        simpleGemFinder("crystalcraft_unlimited_java", "sunset_jasper");
        simpleGemFinder("crystalcraft_unlimited_java", "zebra_jasper");
        simpleGemFinder("crystalcraft_unlimited_java", "pitambari_neelam");
        advancedGemFinder("crystalcraft_unlimited_java", "snowflake_obsidian", "snowflake_block");
        simpleGemFinder("crystalcraft_unlimited_java", "ice_opal");
        advancedGemFinder("crystalcraft_unlimited_java", "matrix_opal", "matrix_block");
        simpleGemFinder("crystalcraft_unlimited_java", "peacock");
        simpleGemFinder("crystalcraft_unlimited_java", "peacock_topaz");
        simpleGemFinder("crystalcraft_unlimited_java", "smoky_quartz");
        simpleGemFinder("crystalcraft_unlimited_java", "soul_quartz");
        simpleGemFinder("crystalcraft_unlimited_java", "ghoul_quartz");
        simpleGemFinder("crystalcraft_unlimited_java", "blood_quartz");
        advancedGemFinder("crystalcraft_unlimited_java", "medusa_quartz", "medusa_block");
        advancedGemFinder("crystalcraft_unlimited_java", "dragon_scale", "dragon_block");
        advancedGemFinder("crystalcraft_unlimited_java", "watermelon_tourmaline", "watermelon_block");
        advancedGemFinder("crystalcraft_unlimited_java", "orange_star_sapphire", "orange_star_block");
        simpleGemFinder("crystalcraft_unlimited_java", "star_sapphire");
        simpleGemFinder("crystalcraft_unlimited_java", "rare_star_sapphire");
        simpleGemFinder("crystalcraft_unlimited_java", "yellow_star_sapphire");
        simpleGemFinder("crystalcraft_unlimited_java", "purple_star_sapphire");
        simpleGemFinder("crystalcraft_unlimited_java", "pink_star_sapphire");
        simpleGemFinder("crystalcraft_unlimited_java", "dark_green_star_sapphire");
        advancedGemFinder("crystalcraft_unlimited_java", "australian_sapphire", "rare_sapphire_block");
        advancedGemFinder("crystalcraft_unlimited_java", "saphire", "sapphire_block");
        advancedGemFinder("crystalcraft_unlimited_java", "sapphire", "blue_sapphire_block");
        advancedGemFinder("crystalcraft_unlimited_java", "green_star_sapphire", "green_star_sapphire_block_2");
        advancedGemFinder("crystalcraft_unlimited_java", "dark_green_star_sapphire", "green_star_sapphire_block");
        advancedGemFinder("crystalcraft_unlimited_java", "rare_sapphire", "rare_sapphire_block_recipe");
        simpleGemFinder("crystalcraft_unlimited_java", "iris_agate");
        simpleGemFinder("crystalcraft_unlimited_java", "painite");
        simpleGemFinder("crystalcraft_unlimited_java", "rainbow_opal");
        simpleGemFinder("crystalcraft_unlimited_java", "rose_quartz");
        simpleGemFinder("crystalcraft_unlimited_java", "star_ruby");
        simpleGemFinder("crystalcraft_unlimited_java", "titanium_quartz");
        simpleGemFinder("crystalcraft_unlimited_java", "umbranova");
    }

    //!! SIMPLE FINDER
    /**
     * @param modId - mod id of the mod
     * @param nameGemType - name of GemType without "_block"
     * @param nameChildren - childkey-ID_of_the_children or nameGemType_ingot
     */
    public static void simpleGemFinder(String modId, String nameGemType, String... nameChildren) {
        advancedGemFinder(modId, nameGemType, nameGemType + "_block", nameChildren);
    }

    //!! ADVANCED FINDER
    /**
     * @param modId - mod id of the mod
     * @param nameGemType - name of GemType without "_block"
     * @param nameBlockGem - name of block for GemType. Usually with "_block"
     * @param nameChildren - childkey-ID_of_the_children or nameGemType_nugget
     */
    public static void advancedGemFinder(String modId, String nameGemType, String nameBlockGem, String... nameChildren) {
        if (PlatHelper.isModLoaded(modId)) {
            var gemtypeFinder = GemType.Finder.simple(modId, nameGemType, nameBlockGem);

            for (String currentChild : nameChildren) {
                String childKey = getChildKeyFrom(currentChild);
                String blockId = currentChild.split("-")[1];
                ResourceLocation childId = (blockId.contains(":"))
                        ? new ResourceLocation(blockId)
                        : new ResourceLocation(modId, blockId);

                if (currentChild.contains("-") && childKeySafe.contains(childKey)) {
                    gemtypeFinder.addChild(childKey, childId);
                }
                else if (childKeySafe.contains(childKey)) gemtypeFinder.addChild(childKey, currentChild);
                else GemsRealm.LOGGER.warn("CompatGemType: Incorrect childKey - {} for {}", childKey, currentChild);

            }

            BlockSetAPI.addBlockTypeFinder(GemType.class, gemtypeFinder);
        }
    }

}
