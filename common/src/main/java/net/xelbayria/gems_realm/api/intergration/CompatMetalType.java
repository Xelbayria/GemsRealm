package net.xelbayria.gems_realm.api.intergration;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.minecraft.resources.ResourceLocation;
import net.xelbayria.gems_realm.api.set.metal.MetalTypeRegistry;
import org.jetbrains.annotations.ApiStatus;

import static net.xelbayria.gems_realm.api.set.VanillaRockChildKeys.*;
import static net.xelbayria.gems_realm.api.set.metal.VanillaMetalChildKeys.INGOT;
import static net.xelbayria.gems_realm.api.set.metal.VanillaMetalChildKeys.NUGGET;

/**
 * Put all undetected MetalTypes here. The following reasons can be seen via Definition of REASONS
 * And a few examples
 **/
@ApiStatus.Internal
public class CompatMetalType extends CompatBlockType {

    /* Defintion of REASONS:
     *
     * Id-Block: The Id of the block is not same as MetalType's name or has no "_block"
     *
     * Spelling-Convention: a typo in the Id, no underscore
     *
     * Naming-Convention: blocks has unique names that doesn't have "_block" or has different affix, has numbers
     *
     * Undetected-Children: MetalType's children wasn't detected with Naming-Convention
     *
     * Vanilla-Children: MetalType's Children is from Minecraft
     *
     * ModId-Children: MetalType's Children is from another mod aside its own mod
     *
     * 2-Words: The name of MetalType is 2-Words instead of 1-Word
     *
     */
    public static void init() {}

    static {

        MetalTypeRegistry metalReg = MetalTypeRegistry.INSTANCE;

        // Minecraft - REASON: ModId-Children
        if (PlatHelper.isModLoaded("oreganized"))
            metalReg.addSimpleFinder("minecraft:netherite")
                .childItem(NUGGET, "oreganized:netherite_nugget");
        if (PlatHelper.isModLoaded("caverns_and_chasms"))
            metalReg.addSimpleFinder("minecraft:netherite")
                .childItem(NUGGET, "caverns_and_chasms:netherite_nugget");

        // -------------------- Spelunkery & Etcetera - REASON: Spelunkery's bismuth_nugget are used to craft Etcetera's bismuth_ingot
        if (PlatHelper.isModLoaded("spelunkery"))
            metalReg.addSimpleFinder("etcetera", "bismuth")
                    .childItem(NUGGET, ResourceLocation.parse("spelunkery:bismuth_nugget"));

        // -------------------- Architect's Palette - REASON: Undetected-Children
        metalReg.addSimpleFinder("architects_palette", "unobtanium")
                .childItem(INGOT, "unobtanium");

        metalReg.addSimpleFinder("architects_palette", "sunmetal")
                .childItem(INGOT, "sunmetal_brick");

        metalReg.addSimpleFinder("architects_palette", "entwine")
                .childItem(INGOT, "sunmetal_rod");

        // -------------------- Create Aquatic Ambitions - REASON: Undetected-Children
        metalReg.addSimpleFinder("create_aquatic_ambitions", "prismarine_alloy")
                .childItem(INGOT, "prismarine_alloy");

        // -------------------- Tech Reborn
        metalReg.addSimpleFinder("techreborn", "iridium_reinforced_tungstensteel")
                .metalBlockSuffix("_storage_block")
                .childBlockSuffix(SLAB, "_storage_block_" + SLAB)
                .childBlockSuffix(STAIRS, "_storage_block_" + STAIRS)
                .childBlockSuffix(WALL, "_storage_block_" + WALL);

        // -------------------- 'Dustrial Decor - REASON: Naming-Convention
        metalReg.addSimpleFinder("dustrial_decor", "cast_iron")
                .childItemSuffix(INGOT, "_billet");
        metalReg.addSimpleFinder("dustrial_decor", "industrial_iron")
                .childItemSuffix(INGOT, "_billet");

        // -------------------- More Ores More Gems - REASON: Undetected-Children - MetalBlockId has "blockof_" or "block_of_"
        String momGems_id = "more_ores_more_gems";

        metalReg.addSimpleFinder(momGems_id, "adamantite");
        metalReg.addSimpleFinder(momGems_id, "aetherium");
        metalReg.addSimpleFinder(momGems_id, "antimony");
        metalReg.addSimpleFinder(momGems_id, "bromine");
        metalReg.addSimpleFinder(momGems_id, "crimsonite")
                .childItemSuffix(INGOT, "");
        metalReg.addSimpleFinder(momGems_id, "electrum");
        metalReg.addSimpleFinder(momGems_id, "lead");
        metalReg.addSimpleFinder(momGems_id, "magnesium");
        metalReg.addSimpleFinder(momGems_id, "monel");
        metalReg.addSimpleFinder(momGems_id, "neptunium");
        metalReg.addSimpleFinder(momGems_id, "nickel");
        metalReg.addSimpleFinder(momGems_id, "nitrol");
        metalReg.addSimpleFinder(momGems_id, "osmium");
        metalReg.addSimpleFinder(momGems_id, "platinum");
        metalReg.addSimpleFinder(momGems_id, "rhodium");
        metalReg.addSimpleFinder(momGems_id, "shadowite");
        metalReg.addSimpleFinder(momGems_id, "shadowsteel");
        metalReg.addSimpleFinder(momGems_id, "sliver");
        metalReg.addSimpleFinder(momGems_id, "skysteel")
                .childItemSuffix(INGOT, "");
        metalReg.addSimpleFinder(momGems_id, "steel");
        metalReg.addSimpleFinder(momGems_id, "thalassium");
        metalReg.addSimpleFinder(momGems_id, "thorium");
        metalReg.addSimpleFinder(momGems_id, "tin");
        metalReg.addSimpleFinder(momGems_id, "titanium");
        metalReg.addSimpleFinder(momGems_id, "titanium_quartz")
                .childItemSuffix(INGOT, "");
        metalReg.addSimpleFinder(momGems_id, "tungsten")
                .childItemSuffix(INGOT, "");
        metalReg.addSimpleFinder(momGems_id, "uranium");
        metalReg.addSimpleFinder(momGems_id, "uranium_234");
        metalReg.addSimpleFinder(momGems_id, "uranium_238");
        metalReg.addSimpleFinder(momGems_id, "urantherium");
        metalReg.addSimpleFinder(momGems_id, "volcagmium");

        // -------------------- Unusual End
        metalReg.addSimpleFinder("unusualend:pearlescent"); //REASON: ???

        // -------------------- Sea Dwellers
        metalReg.addSimpleFinder("seadwellers:depth"); //REASON: ???

        // -------------------- Mo' Shiz
        metalReg.addSimpleFinder("ms", "refined_quartz")  //REASON: 2-Words
                .metalBlock("resources/refined_quartz_block");
        metalReg.addSimpleFinder("ms", "cast_iron")  //REASON: 2-Words
                .metalBlock("resources/cast_iron_block");

        // -------------------- CrystalCraft-Unlimited-Java
        String crystalcraft_id = "crystalcraft_unlimited_java";

        metalReg.addSimpleFinder(crystalcraft_id, "radium") //REASON: Undetected-Children, Id-Block
                .metalBlock("radium_reactor")
                .childItem(INGOT, "radium");
        metalReg.addSimpleFinder(crystalcraft_id, "neon_meteorite") //REASON: 2-Words, Id-Block, Undetected-Children
                .metalBlock("neon")
                .childItem(INGOT, "neon_meteorite");
        metalReg.addSimpleFinder(crystalcraft_id, "withered_steel") //REASON: Undetected-Children, Id-Block
                .metalBlock("wither_block")
                .childItem(INGOT, "withered_steel");
        metalReg.addSimpleFinder(crystalcraft_id, "meteorite") //REASON: Undetected-Children, Id-Block
                .metalBlock("meteorite");

        //REASON: Undetected-Children
        String[] cc_undetectedIngots = {
                "adamantite", "alnico", "aluminium", "aluminium_bronze", "amalgam", "antimony",
                "bismuth", "brass", "bronze",
                "cadmium", "carnotite", "cast_iron", "chrome", "cobalt", "crimson_gold", "cupronickel",
                "duralumin",
                "electrum",
                "galaxite", "german_silver", "green_gold",
                "holmium", "hss",
                "ilmenite", "inconel", "iridium",
                "lead", "lithium",
                "magnesium", "maradonyx", "matizium", "mythril",
                "neptunium", "nickel", "nordic_gold",
                "orichalcum", "osmium",
                "palintinium", "palladium", "pelenium", "pewter", "platinum", "plutonium", "purple_gold", "pyrite",
                "rene_41", "rhodium", "rose_gold", "ruthenium",
                "seaborgium", "silicium", "silver", "stainless_steel", "steel", "stellite",
                "tantalum", "technetium", "tin", "titanium", "titanium_alloy", "tungsten",
                "unoptanium", "uranium",
                "xernium",
                "yurium",
                "zamak", "zinc", "zircaloy",
        };
        for (String current : cc_undetectedIngots) {
            metalReg.addSimpleFinder(crystalcraft_id, current).childItemSuffix(INGOT, "");
        }

        String[] cc_undetectedMetalTypes = {
                //REASON: ???
                "americium", "calcium", "europium",
                "indium", "manganese", "molybdenum", "niobium", "pottasium",
                "scandium", "thorium", "vanadium", "yttrium",
                "zirconium",

                //REASON: 2-Words
                "hydro_pottasium"
        };
        for (String current : cc_undetectedMetalTypes) metalReg.addSimpleFinder(crystalcraft_id, current);

    }

}
