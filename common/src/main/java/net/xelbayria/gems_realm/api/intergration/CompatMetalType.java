package net.xelbayria.gems_realm.api.intergration;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.xelbayria.gems_realm.api.set.metal.MetalTypeRegistry;
import org.jetbrains.annotations.ApiStatus;

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
        metalReg.addSimpleFinder("more_ores_more_gems:electrum");
        metalReg.addSimpleFinder("more_ores_more_gems:jade");
        metalReg.addSimpleFinder("more_ores_more_gems:lead");
        metalReg.addSimpleFinder("more_ores_more_gems:magnesium");
        metalReg.addSimpleFinder("more_ores_more_gems:monel");
        metalReg.addSimpleFinder("more_ores_more_gems:nickel");
        metalReg.addSimpleFinder("more_ores_more_gems:osmium");
        metalReg.addSimpleFinder("more_ores_more_gems:platinum");
        metalReg.addSimpleFinder("more_ores_more_gems:rhodium");
        metalReg.addSimpleFinder("more_ores_more_gems:shadowite"); //REASON: Id-Block
        metalReg.addSimpleFinder("more_ores_more_gems:shadowsteel");
        metalReg.addSimpleFinder("more_ores_more_gems:skysteel"); //REASON: Id-Block
        metalReg.addSimpleFinder("more_ores_more_gems:sliver");
        metalReg.addSimpleFinder("more_ores_more_gems:steel");
        metalReg.addSimpleFinder("more_ores_more_gems:thalassium");
        metalReg.addSimpleFinder("more_ores_more_gems:thorium");
        metalReg.addSimpleFinder("more_ores_more_gems:tin");
        metalReg.addSimpleFinder("more_ores_more_gems:titanium");
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
        metalReg.addSimpleFinder(crystalcraft, "orichalcum") //REASON: Undetected-Children
                .childItem(INGOT, "orichalcum");
        metalReg.addSimpleFinder(crystalcraft, "aluminium") //REASON: Undetected-Children
                .childItem(INGOT, "aluminium");
        metalReg.addSimpleFinder(crystalcraft, "titanium") //REASON: Undetected-Children
                .childItem(INGOT, "titanium");
        metalReg.addSimpleFinder(crystalcraft, "uranium") //REASON: Undetected-Children
                .childItem(INGOT, "uranium");
        metalReg.addSimpleFinder(crystalcraft, "bronze") //REASON: Undetected-Children
                .childItem(INGOT, "bronze");
        metalReg.addSimpleFinder(crystalcraft, "silver") //REASON: Undetected-Children
                .childItem(INGOT, "silver");
        metalReg.addSimpleFinder(crystalcraft, "platinum") //REASON: Undetected-Children
                .childItem(INGOT, "platinum");
        metalReg.addSimpleFinder(crystalcraft, "mythril") //REASON: Undetected-Children
                .childItem(INGOT, "mythril");
        metalReg.addSimpleFinder(crystalcraft, "adamantite") //REASON: Undetected-Children
                .childItem(INGOT, "adamantite");
        metalReg.addSimpleFinder(crystalcraft, "plutonium") //REASON: Undetected-Children
                .childItem(INGOT, "plutonium");
        metalReg.addSimpleFinder(crystalcraft, "unoptanium") //REASON: Undetected-Children
                .childItem(INGOT, "unoptanium");
        metalReg.addSimpleFinder(crystalcraft, "crimson_gold") //REASON: Undetected-Children
                .childItem(INGOT, "crimson_gold");
        metalReg.addSimpleFinder(crystalcraft, "tin") //REASON: Undetected-Children
                .childItem(INGOT, "tin");
        metalReg.addSimpleFinder(crystalcraft, "iridium") //REASON: Undetected-Children
                .childItem(INGOT, "iridium");
        metalReg.addSimpleFinder(crystalcraft, "steel") //REASON: Undetected-Children
                .childItem(INGOT, "steel");
        metalReg.addSimpleFinder(crystalcraft, "nickel") //REASON: Undetected-Children
                .childItem(INGOT, "nickel");
        metalReg.addSimpleFinder(crystalcraft, "zinc") //REASON: Undetected-Children
                .childItem(INGOT, "zinc");
        metalReg.addSimpleFinder(crystalcraft, "lead") //REASON: Undetected-Children
                .childItem(INGOT, "lead");
        metalReg.addSimpleFinder(crystalcraft, "brass") //REASON: Undetected-Children
                .childItem(INGOT, "brass");
        metalReg.addSimpleFinder(crystalcraft, "tungsten") //REASON: Undetected-Children
                .childItem(INGOT, "tungsten");
        metalReg.addSimpleFinder(crystalcraft, "rose_gold") //REASON: Undetected-Children
                .childItem(INGOT, "rose_gold");
        metalReg.addSimpleFinder(crystalcraft, "chrome") //REASON: Undetected-Children
                .childItem(INGOT, "chrome");
        metalReg.addSimpleFinder(crystalcraft, "magnesium") //REASON: Undetected-Children
                .childItem(INGOT, "magnesium");
        metalReg.addSimpleFinder(crystalcraft, "bismuth") //REASON: Undetected-Children
                .childItem(INGOT, "bismuth");
        metalReg.addSimpleFinder(crystalcraft, "electrum") //REASON: Undetected-Children
                .childItem(INGOT, "electrum");
        metalReg.addSimpleFinder(crystalcraft, "matizium") //REASON: Undetected-Children
                .childItem(INGOT, "matizium");
        metalReg.addSimpleFinder(crystalcraft, "yurium") //REASON: Undetected-Children
                .childItem(INGOT, "yurium");
        metalReg.addSimpleFinder(crystalcraft, "xernium") //REASON: Undetected-Children
                .childItem(INGOT, "xernium");
        metalReg.addSimpleFinder(crystalcraft, "palladium") //REASON: Undetected-Children
                .childItem(INGOT, "palladium");
        metalReg.addSimpleFinder(crystalcraft, "purple_gold") //REASON: Undetected-Children
                .childItem(INGOT, "purple_gold");
        metalReg.addSimpleFinder(crystalcraft, "green_gold") //REASON: Undetected-Children
                .childItem(INGOT, "green_gold");
        metalReg.addSimpleFinder(crystalcraft, "cupronickel") //REASON: Undetected-Children
                .childItem(INGOT, "cupronickel");
        metalReg.addSimpleFinder(crystalcraft, "pelenium") //REASON: Undetected-Children
                .childItem(INGOT, "pelenium");
        metalReg.addSimpleFinder(crystalcraft, "maradonyx") //REASON: Undetected-Children
                .childItem(INGOT, "maradonyx");
        metalReg.addSimpleFinder(crystalcraft, "palintinium") //REASON: Undetected-Children
                .childItem(INGOT, "palintinium");
        metalReg.addSimpleFinder(crystalcraft, "lithium") //REASON: Undetected-Children
                .childItem(INGOT, "lithium");
        metalReg.addSimpleFinder(crystalcraft, "silicium") //REASON: Undetected-Children
                .childItem(INGOT, "silicium");
        metalReg.addSimpleFinder(crystalcraft, "technetium") //REASON: Undetected-Children
                .childItem(INGOT, "technetium");
        metalReg.addSimpleFinder(crystalcraft, "ruthenium") //REASON: Undetected-Children
                .childItem(INGOT, "ruthenium");
        metalReg.addSimpleFinder(crystalcraft, "rhodium") //REASON: Undetected-Children
                .childItem(INGOT, "rhodium");
        metalReg.addSimpleFinder(crystalcraft, "cadmium") //REASON: Undetected-Children
                .childItem(INGOT, "cadmium");
        metalReg.addSimpleFinder(crystalcraft, "tantalum") //REASON: Undetected-Children
                .childItem(INGOT, "tantalum");
        metalReg.addSimpleFinder(crystalcraft, "holmium") //REASON: Undetected-Children
                .childItem(INGOT, "holmium");
        metalReg.addSimpleFinder(crystalcraft, "osmium") //REASON: Undetected-Children
                .childItem(INGOT, "osmium");
        metalReg.addSimpleFinder(crystalcraft, "neptunium") //REASON: Undetected-Children
                .childItem(INGOT, "neptunium");
        metalReg.addSimpleFinder(crystalcraft, "galaxite") //REASON: Undetected-Children
                .childItem(INGOT, "galaxite");
        metalReg.addSimpleFinder(crystalcraft, "pyrite") //REASON: Undetected-Children
                .childItem(INGOT, "pyrite");
        metalReg.addSimpleFinder(crystalcraft, "seaborgium") //REASON: Undetected-Children
                .childItem(INGOT, "seaborgium");
        metalReg.addSimpleFinder(crystalcraft, "ilmenite") //REASON: Undetected-Children
                .childItem(INGOT, "ilmenite");
        metalReg.addSimpleFinder(crystalcraft, "cobalt") //REASON: Undetected-Children
                .childItem(INGOT, "cobalt");
        metalReg.addSimpleFinder(crystalcraft, "carnotite") //REASON: Undetected-Children
                .childItem(INGOT, "carnotite");
        metalReg.addSimpleFinder(crystalcraft, "antimony") //REASON: Undetected-Children
                .childItem(INGOT, "antimony");
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
