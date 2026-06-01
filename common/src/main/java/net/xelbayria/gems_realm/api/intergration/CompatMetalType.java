package net.xelbayria.gems_realm.api.intergration;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
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

        // Tech Reborn
        metalReg.addSimpleFinder("techreborn", "iridium_reinforced_tungstensteel")
                .metalBlockSuffix("_storage_block")
                .childBlockSuffix(SLAB, "_storage_block_" + SLAB)
                .childBlockSuffix(STAIRS, "_storage_block_" + STAIRS)
                .childBlockSuffix(WALL, "_storage_block_" + WALL);

        // 'Dustrial Decor - REASON: Naming-Convention
        metalReg.addSimpleFinder("dustrial_decor", "cast_iron")
                .childItemSuffix(INGOT, "_billet");
        metalReg.addSimpleFinder("dustrial_decor", "industrial_iron")
                .childItemSuffix(INGOT, "_billet");

        // More Ores More Gems - REASON: Undetected-Children - MetalBlockId has "blockof_" or "block_of_"
        metalReg.addSimpleFinder("more_ores_more_gems:adamantite");
        metalReg.addSimpleFinder("more_ores_more_gems:aetherium");
        metalReg.addSimpleFinder("more_ores_more_gems:antimony");
        metalReg.addSimpleFinder("more_ores_more_gems:bromine");
        metalReg.addSimpleFinder("more_ores_more_gems:crimsonite")
                .childItemSuffix(INGOT, "");
        metalReg.addSimpleFinder("more_ores_more_gems:electrum");
        metalReg.addSimpleFinder("more_ores_more_gems:lead");
        metalReg.addSimpleFinder("more_ores_more_gems:magnesium");
        metalReg.addSimpleFinder("more_ores_more_gems:monel");
        metalReg.addSimpleFinder("more_ores_more_gems:neptunium");
        metalReg.addSimpleFinder("more_ores_more_gems:nickel");
        metalReg.addSimpleFinder("more_ores_more_gems:nitrol");
        metalReg.addSimpleFinder("more_ores_more_gems:osmium");
        metalReg.addSimpleFinder("more_ores_more_gems:platinum");
        metalReg.addSimpleFinder("more_ores_more_gems:rhodium");
        metalReg.addSimpleFinder("more_ores_more_gems:shadowite"); //REASON: Id-Block
        metalReg.addSimpleFinder("more_ores_more_gems:shadowsteel");
        metalReg.addSimpleFinder("more_ores_more_gems:sliver");
        metalReg.addSimpleFinder("more_ores_more_gems:skysteel")
                .childItemSuffix(INGOT, "");
        metalReg.addSimpleFinder("more_ores_more_gems:steel");
        metalReg.addSimpleFinder("more_ores_more_gems:thalassium");
        metalReg.addSimpleFinder("more_ores_more_gems:thorium");
        metalReg.addSimpleFinder("more_ores_more_gems:tin");
        metalReg.addSimpleFinder("more_ores_more_gems:titanium");
        metalReg.addSimpleFinder("more_ores_more_gems:titanium_quartz")
                .childItemSuffix(INGOT, "");
        metalReg.addSimpleFinder("more_ores_more_gems:tungsten")
                .childItemSuffix(INGOT, "");
        metalReg.addSimpleFinder("more_ores_more_gems:uranium");
        metalReg.addSimpleFinder("more_ores_more_gems:uranium_234");
        metalReg.addSimpleFinder("more_ores_more_gems:uranium_238");
        metalReg.addSimpleFinder("more_ores_more_gems:urantherium");
        metalReg.addSimpleFinder("more_ores_more_gems:volcagmium"); //REASON: Id-Block

        // Unusual End
        metalReg.addSimpleFinder("unusualend:pearlescent"); //REASON: ???

        // Sea Dwellers
        metalReg.addSimpleFinder("seadwellers:depth"); //REASON: ???

        // Mo' Shiz
        metalReg.addSimpleFinder("ms", "refined_quartz")  //REASON: 2-Words
                .metalBlock("resources/refined_quartz_block");
        metalReg.addSimpleFinder("ms", "cast_iron")  //REASON: 2-Words
                .metalBlock("resources/cast_iron_block");

        // CrystalCraft-Unlimited-Java
        String crystalcraft = "crystalcraft_unlimited_java";

        metalReg.addSimpleFinder(crystalcraft, "radium") //REASON: Undetected-Children, Id-Block
                .metalBlock("radium_reactor")
                .childItem(INGOT, "radium");
        metalReg.addSimpleFinder(crystalcraft, "neon_meteorite") //REASON: 2-Words, Id-Block, Undetected-Children
                .metalBlock("neon")
                .childItem(INGOT, "neon_meteorite");
        metalReg.addSimpleFinder(crystalcraft, "withered_steel") //REASON: Undetected-Children, Id-Block
                .metalBlock("wither_block")
                .childItem(INGOT, "withered_steel");
        metalReg.addSimpleFinder(crystalcraft, "meteorite") //REASON: Undetected-Children, Id-Block
                .metalBlock("meteorite");

        //REASON: Undetected-Children
        metalReg.addSimpleFinder(crystalcraft, "orichalcum")
                .childItem(INGOT, "orichalcum");
        metalReg.addSimpleFinder(crystalcraft, "aluminium")
                .childItem(INGOT, "aluminium");
        metalReg.addSimpleFinder(crystalcraft, "titanium")
                .childItem(INGOT, "titanium");
        metalReg.addSimpleFinder(crystalcraft, "uranium")
                .childItem(INGOT, "uranium");
        metalReg.addSimpleFinder(crystalcraft, "bronze")
                .childItem(INGOT, "bronze");
        metalReg.addSimpleFinder(crystalcraft, "silver")
                .childItem(INGOT, "silver");
        metalReg.addSimpleFinder(crystalcraft, "platinum")
                .childItem(INGOT, "platinum");
        metalReg.addSimpleFinder(crystalcraft, "mythril")
                .childItem(INGOT, "mythril");
        metalReg.addSimpleFinder(crystalcraft, "adamantite")
                .childItem(INGOT, "adamantite");
        metalReg.addSimpleFinder(crystalcraft, "plutonium")
                .childItem(INGOT, "plutonium");
        metalReg.addSimpleFinder(crystalcraft, "unoptanium")
                .childItem(INGOT, "unoptanium");
        metalReg.addSimpleFinder(crystalcraft, "crimson_gold")
                .childItem(INGOT, "crimson_gold");
        metalReg.addSimpleFinder(crystalcraft, "tin")
                .childItem(INGOT, "tin");
        metalReg.addSimpleFinder(crystalcraft, "iridium")
                .childItem(INGOT, "iridium");
        metalReg.addSimpleFinder(crystalcraft, "steel")
                .childItem(INGOT, "steel");
        metalReg.addSimpleFinder(crystalcraft, "nickel")
                .childItem(INGOT, "nickel");
        metalReg.addSimpleFinder(crystalcraft, "zinc")
                .childItem(INGOT, "zinc");
        metalReg.addSimpleFinder(crystalcraft, "lead")
                .childItem(INGOT, "lead");
        metalReg.addSimpleFinder(crystalcraft, "brass")
                .childItem(INGOT, "brass");
        metalReg.addSimpleFinder(crystalcraft, "tungsten")
                .childItem(INGOT, "tungsten");
        metalReg.addSimpleFinder(crystalcraft, "rose_gold")
                .childItem(INGOT, "rose_gold");
        metalReg.addSimpleFinder(crystalcraft, "chrome")
                .childItem(INGOT, "chrome");
        metalReg.addSimpleFinder(crystalcraft, "magnesium")
                .childItem(INGOT, "magnesium");
        metalReg.addSimpleFinder(crystalcraft, "bismuth")
                .childItem(INGOT, "bismuth");
        metalReg.addSimpleFinder(crystalcraft, "electrum")
                .childItem(INGOT, "electrum");
        metalReg.addSimpleFinder(crystalcraft, "matizium")
                .childItem(INGOT, "matizium");
        metalReg.addSimpleFinder(crystalcraft, "yurium")
                .childItem(INGOT, "yurium");
        metalReg.addSimpleFinder(crystalcraft, "xernium")
                .childItem(INGOT, "xernium");
        metalReg.addSimpleFinder(crystalcraft, "palladium")
                .childItem(INGOT, "palladium");
        metalReg.addSimpleFinder(crystalcraft, "purple_gold")
                .childItem(INGOT, "purple_gold");
        metalReg.addSimpleFinder(crystalcraft, "green_gold")
                .childItem(INGOT, "green_gold");
        metalReg.addSimpleFinder(crystalcraft, "cupronickel")
                .childItem(INGOT, "cupronickel");
        metalReg.addSimpleFinder(crystalcraft, "pelenium")
                .childItem(INGOT, "pelenium");
        metalReg.addSimpleFinder(crystalcraft, "maradonyx")
                .childItem(INGOT, "maradonyx");
        metalReg.addSimpleFinder(crystalcraft, "palintinium")
                .childItem(INGOT, "palintinium");
        metalReg.addSimpleFinder(crystalcraft, "lithium")
                .childItem(INGOT, "lithium");
        metalReg.addSimpleFinder(crystalcraft, "silicium")
                .childItem(INGOT, "silicium");
        metalReg.addSimpleFinder(crystalcraft, "technetium")
                .childItem(INGOT, "technetium");
        metalReg.addSimpleFinder(crystalcraft, "ruthenium")
                .childItem(INGOT, "ruthenium");
        metalReg.addSimpleFinder(crystalcraft, "rhodium")
                .childItem(INGOT, "rhodium");
        metalReg.addSimpleFinder(crystalcraft, "cadmium")
                .childItem(INGOT, "cadmium");
        metalReg.addSimpleFinder(crystalcraft, "tantalum")
                .childItem(INGOT, "tantalum");
        metalReg.addSimpleFinder(crystalcraft, "holmium")
                .childItem(INGOT, "holmium");
        metalReg.addSimpleFinder(crystalcraft, "osmium")
                .childItem(INGOT, "osmium");
        metalReg.addSimpleFinder(crystalcraft, "neptunium")
                .childItem(INGOT, "neptunium");
        metalReg.addSimpleFinder(crystalcraft, "galaxite")
                .childItem(INGOT, "galaxite");
        metalReg.addSimpleFinder(crystalcraft, "pyrite")
                .childItem(INGOT, "pyrite");
        metalReg.addSimpleFinder(crystalcraft, "seaborgium")
                .childItem(INGOT, "seaborgium");
        metalReg.addSimpleFinder(crystalcraft, "ilmenite")
                .childItem(INGOT, "ilmenite");
        metalReg.addSimpleFinder(crystalcraft, "cobalt")
                .childItem(INGOT, "cobalt");
        metalReg.addSimpleFinder(crystalcraft, "carnotite")
                .childItem(INGOT, "carnotite");
        metalReg.addSimpleFinder(crystalcraft, "antimony")
                .childItem(INGOT, "antimony");
        //TEMP: Recently Added.
        metalReg.addSimpleFinder(crystalcraft, "alnico")
                .childItem(INGOT, "alnico");
        metalReg.addSimpleFinder(crystalcraft, "aluminium_bronze")
                .childItem(INGOT, "aluminium_bronze");
        metalReg.addSimpleFinder(crystalcraft, "amalgam")
                .childItem(INGOT, "amalgam");
        metalReg.addSimpleFinder(crystalcraft, "cast_iron")
                .childItem(INGOT, "cast_iron");
        metalReg.addSimpleFinder(crystalcraft, "duralumin")
                .childItem(INGOT, "duralumin");
        metalReg.addSimpleFinder(crystalcraft, "german_silver")
                .childItem(INGOT, "german_silver");
        metalReg.addSimpleFinder(crystalcraft, "hss")
                .childItem(INGOT, "hss");
        metalReg.addSimpleFinder(crystalcraft, "inconel")
                .childItem(INGOT, "inconel");
        metalReg.addSimpleFinder(crystalcraft, "nordic_gold")
                .childItem(INGOT, "nordic_gold");
        metalReg.addSimpleFinder(crystalcraft, "pewter")
                .childItem(INGOT, "pewter");
        metalReg.addSimpleFinder(crystalcraft, "rene_41")
                .childItem(INGOT, "rene_41");
        metalReg.addSimpleFinder(crystalcraft, "stainless_steel")
                .childItem(INGOT, "stainless_steel");
        metalReg.addSimpleFinder(crystalcraft, "stellite")
                .childItem(INGOT, "stellite");
        metalReg.addSimpleFinder(crystalcraft, "titanium_alloy")
                .childItem(INGOT, "titanium_alloy");
        metalReg.addSimpleFinder(crystalcraft, "zamak")
                .childItem(INGOT, "zamak");
        metalReg.addSimpleFinder(crystalcraft, "zircaloy")
                .childItem(INGOT, "zircaloy");


        metalReg.addSimpleFinder(crystalcraft, "hydro_pottasium"); //REASON: 2-Words
        metalReg.addSimpleFinder(crystalcraft, "americium"); //REASON: ???
        metalReg.addSimpleFinder(crystalcraft, "thorium"); //REASON: ???
        metalReg.addSimpleFinder(crystalcraft, "pottasium"); //REASON: ???
        metalReg.addSimpleFinder(crystalcraft, "indium"); //REASON: ???
        metalReg.addSimpleFinder(crystalcraft, "scandium"); //REASON: ???
        metalReg.addSimpleFinder(crystalcraft, "vanadium"); //REASON: ???
        metalReg.addSimpleFinder(crystalcraft, "manganese"); //REASON: ???
        metalReg.addSimpleFinder(crystalcraft, "yttrium"); //REASON: ???
        metalReg.addSimpleFinder(crystalcraft, "zirconium"); //REASON: ???
        metalReg.addSimpleFinder(crystalcraft, "niobium"); //REASON: ???
        metalReg.addSimpleFinder(crystalcraft, "molybdenum"); //REASON: ???
        metalReg.addSimpleFinder(crystalcraft, "europium"); //REASON: ???
        metalReg.addSimpleFinder(crystalcraft, "calcium"); //REASON: ???
    }

}
