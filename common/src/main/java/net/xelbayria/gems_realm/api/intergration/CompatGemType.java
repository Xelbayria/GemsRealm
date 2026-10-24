package net.xelbayria.gems_realm.api.intergration;

import net.xelbayria.gems_realm.api.set.gem.GemTypeRegistry;
import org.jetbrains.annotations.ApiStatus;

import static net.xelbayria.gems_realm.api.set.gem.VanillaGemChildKeys.GEM;

/**
 * Put all undetected GemTypes here. The following reasons can be seen via Definition of REASONS
 * And a few examples
 **/
@ApiStatus.Internal
public class CompatGemType extends CompatBlockType{

    /* Defintion of REASONS:
     *
     * Id-Block: The Id of the block is not same as GemType's name
     *
     * Spelling-Convention: a typo in the Id, no underscore
     *
     * Naming-Convention: blocks has unique names that doesn't have "_block" or has different affix, has numbers
     *
     * Undetected-Children: GemType's children wasn't detected with Naming-Convention
     *
     * Vanilla-Children: GemType's Children is from Minecraft
     *
     * ModId-Children: GemType's Children is from another mod aside its own mod
     *
     * 2-Words: The name of GemType is 2-Words instead of 1-Word
     *
     */
    public static void init() {}

    static {

        GemTypeRegistry gemReg = GemTypeRegistry.INSTANCE;

        // -------------------- More Ores More Gems - REASON: Undetected-Children - GemBlockId has "blockof_" or "block_of_"
        gemReg.addSimpleFinder("more_ores_more_gems:amethyst");
        gemReg.addSimpleFinder("more_ores_more_gems:ametrine");
        gemReg.addSimpleFinder("more_ores_more_gems:aquamarine");
        gemReg.addSimpleFinder("more_ores_more_gems:autunite_235")
                .childItemSuffix(GEM, "_gemstone");
        gemReg.addSimpleFinder("more_ores_more_gems:autunite_238")
                .childItemSuffix(GEM, "_gemstone");
        gemReg.addSimpleFinder("more_ores_more_gems:black_fluorite")
                .childItem(GEM, "fluorite_black_color");
        gemReg.addSimpleFinder("more_ores_more_gems:black_opal");
        gemReg.addSimpleFinder("more_ores_more_gems:blood_fluorite");
        gemReg.addSimpleFinder("more_ores_more_gems:carnelian");
        gemReg.addSimpleFinder("more_ores_more_gems:citrine");
        gemReg.addSimpleFinder("more_ores_more_gems:ekanite");
        gemReg.addSimpleFinder("more_ores_more_gems:fire_opal")
                .childItemSuffix(GEM, "_gemstone");
        gemReg.addSimpleFinder("more_ores_more_gems:fluorescent_fluorite");
        gemReg.addSimpleFinder("more_ores_more_gems:gray_opal");
        gemReg.addSimpleFinder("more_ores_more_gems:green_fluorite")
                .childItem(GEM, "fluorite_green_color");
        gemReg.addSimpleFinder("more_ores_more_gems:heliodor");
        gemReg.addSimpleFinder("more_ores_more_gems:jade");
        gemReg.addSimpleFinder("more_ores_more_gems:lemonite");
        gemReg.addSimpleFinder("more_ores_more_gems:luminous_gem");
        gemReg.addSimpleFinder("more_ores_more_gems:mysticrain_quartz");
        gemReg.addSimpleFinder("more_ores_more_gems:opalized_quartz");
        gemReg.addSimpleFinder("more_ores_more_gems:orange_fluorite")
                .childItem(GEM, "fluorite_orange_color");
        gemReg.addSimpleFinder("more_ores_more_gems:orange_pink_fluorite")
                .childItem(GEM, "fluorite_orange_pink");
        gemReg.addSimpleFinder("more_ores_more_gems:padparadscha");
        gemReg.addSimpleFinder("more_ores_more_gems:peridot");
        gemReg.addSimpleFinder("more_ores_more_gems:pink_fluorite")
                .childItem(GEM, "fluorite_pink_color");
        gemReg.addSimpleFinder("more_ores_more_gems:pink_opal");
        gemReg.addSimpleFinder("more_ores_more_gems:purple_fluorite")
                .childItem(GEM, "fluorite_purple_color");
        gemReg.addSimpleFinder("more_ores_more_gems:purple_green_fluorite")
                .childItem(GEM, "fluorite_purple_green");
        gemReg.addSimpleFinder("more_ores_more_gems:rainbow_fluorite")
                .childItem(GEM, "fluorite_rainbow_color");
        gemReg.addSimpleFinder("more_ores_more_gems:rare_sapphire");
        gemReg.addSimpleFinder("more_ores_more_gems:ruby_pack");
        gemReg.addSimpleFinder("more_ores_more_gems:sapphire");
        gemReg.addSimpleFinder("more_ores_more_gems:sunflare_gem");
        gemReg.addSimpleFinder("more_ores_more_gems:tanzanite");
        gemReg.addSimpleFinder("more_ores_more_gems:topaz");
        gemReg.addSimpleFinder("more_ores_more_gems:ussingite");
        gemReg.addSimpleFinder("more_ores_more_gems:weird_frost_opal")
                .childItem(GEM, "memory_opal");
        gemReg.addSimpleFinder("more_ores_more_gems:white_crystal");
        gemReg.addSimpleFinder("more_ores_more_gems:white_fluorite")
                .childItem(GEM, "fluorite_white_clear");
        gemReg.addSimpleFinder("more_ores_more_gems:white_opal");
        gemReg.addSimpleFinder("more_ores_more_gems:ytt_fluorite")
                .childItem(GEM, "fluorite_yttrium");

        // -------------------- Atlantis (MysticPasta1) - REASON: Naming-Convention
        gemReg.addSimpleFinder("atlantis", "aquamarine")
                .childItemSuffix(GEM, "_gem");

        // -------------------- Shadlowlands - REASON: Naming-Convention
        gemReg.addSimpleFinder("shadowlands", "neon")
                .gemBlock("neon_gem_block");
        gemReg.addSimpleFinder("shadowlands", "fire")
                .gemBlock("fire_gem_block");

        // -------------------- Crystal Unlimited Edition
        String crystalcraft_id = "crystalcraft_unlimited_java";

        for (int num = 1; num < 15; num++) { //REASON: Naming-Convention
            String zircon = "zircon_";
            if (num == 13) continue; // doesn't exist
            switch (num) {
                case 1, 4, 6, 9 -> gemReg.addSimpleFinder(crystalcraft_id, zircon + num)
                        .gemBlock(zircon + "block_" + num);
                default -> gemReg.addSimpleFinder(crystalcraft_id, zircon + num);
            }
        }

        gemReg.addSimpleFinder(crystalcraft_id, "bixbite") //REASON: Id-Block
                .gemBlock("bixite_block");
        gemReg.addSimpleFinder(crystalcraft_id, "pearl_item") //REASON: Id-Block, ???
                .gemBlock("pearl_block");
        gemReg.addSimpleFinder(crystalcraft_id, "pink_catseye") //REASON: Id-Block, 2-Words
                .gemBlock("pink_tigerseye_block");
        gemReg.addSimpleFinder(crystalcraft_id, "brown_catseye") //REASON: Id-Block, 2-Words
                .gemBlock("tigerseye_block");
        gemReg.addSimpleFinder(crystalcraft_id, "dark_blue_diamond") //REASON: Id-Block, 2-Words
                .gemBlock("blue_diamond");
        gemReg.addSimpleFinder(crystalcraft_id, "solar_diamond") //REASON: Id-Block, 2-Words
                .gemBlock("solar_block");
        gemReg.addSimpleFinder(crystalcraft_id, "eclipse_diamond") //REASON: Id-Block, 2-Words
                .gemBlock("eclipse_block");
        gemReg.addSimpleFinder(crystalcraft_id, "snowflake_obsidian") //REASON: Id-Block, 2-Words
                .gemBlock("snowflake_block");
        gemReg.addSimpleFinder(crystalcraft_id, "matrix_opal") //REASON: Id-Block, 2-Words
                .gemBlock("matrix_block");
        gemReg.addSimpleFinder(crystalcraft_id, "medusa_quartz") //REASON: Id-Block, 2-Words
                .gemBlock("medusa_block");
        gemReg.addSimpleFinder(crystalcraft_id, "dragon_scale") //REASON: Id-Block, 2-Words
                .gemBlock("dragon_block");
        gemReg.addSimpleFinder(crystalcraft_id, "watermelon_tourmaline") //REASON: Id-Block, 2-Words
                .gemBlock("watermelon_block");
        gemReg.addSimpleFinder(crystalcraft_id, "orange_star_sapphire") //REASON: Id-Block, 2-Words
                .gemBlock("orange_star_block");
        gemReg.addSimpleFinder(crystalcraft_id, "australian_sapphire") //REASON: Id-Block, 2-Words
                .gemBlock("rare_sapphire_block");
        gemReg.addSimpleFinder(crystalcraft_id, "saphire") //REASON: Id-Block, Spelling-Convention
                .gemBlock("sapphire_block");
        gemReg.addSimpleFinder(crystalcraft_id, "sapphire") //REASON: Id-Block
                .gemBlock("blue_sapphire_block");
        gemReg.addSimpleFinder(crystalcraft_id, "green_star_sapphire") //REASON: Id-Block, 3-Words
                .gemBlock("green_star_sapphire_block_2");
        gemReg.addSimpleFinder(crystalcraft_id, "dark_green_star_sapphire") //REASON: Id-Block, 4-Words
                .gemBlock("green_star_sapphire_block");
        gemReg.addSimpleFinder(crystalcraft_id, "rare_sapphire") //REASON: Id-Block, 2-Words
                .gemBlock("rare_sapphire_block_recipe");

        String[] cc_undetected = {
                //REASON: ???
                "angerite",
                "aquamorganite",
                "painite",
                "umbranova",

                //REASON: 2-Words+
                "black_pearl", "blue_pearl", "brown_pearl", "dark_blue_pearl", "green_pearl", "olive_pearl",
                "orange_pearl", "pink_pearl", "purple_pearl", "red_pearl", "white_pearl", "yellow_pearl",

                "purple_catseye", "white_catseye", "red_catseye", "orange_catseye", "yellow_catseye", "black_catseye", "blue_catseye",
                "crystal", "cyber_crystal", "corrupted_cyber_crystal",
                "yellow_diamond", "olive_diamond", "green_diamond", "brown_diamond", "shadow_diamond", "raspberry_diamond", "maroon_diamond",
                "anti_humoranium",
                "sunset_jasper", "zebra_jasper",
                "pitambari_neelam",
                "ice_opal", "rainbow_opal",
                "peacock", "peacock_topaz",
                "smoky_quartz", "soul_quartz", "ghoul_quartz", "blood_quartz",
                "star_sapphire", "rare_star_sapphire", "yellow_star_sapphire", "purple_star_sapphire", "pink_star_sapphire",
                "iris_agate",
                "rose_quartz",
                "star_ruby",
                "titanium_quartz",

                "blue_john_fluorite",
                "moss_agate",
                "mystic_topaz",
                "rare_tourmaline",
                "rainbow_fluorite",
        };
        for (String current : cc_undetected) {
            gemReg.addSimpleFinder(crystalcraft_id, current);
        }

    }

}
