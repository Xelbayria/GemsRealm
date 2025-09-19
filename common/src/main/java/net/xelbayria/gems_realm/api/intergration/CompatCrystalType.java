package net.xelbayria.gems_realm.api.intergration;

import net.minecraft.resources.ResourceLocation;
import net.xelbayria.gems_realm.api.set.crystal.CrystalTypeRegistry;
import org.jetbrains.annotations.ApiStatus;

import static net.xelbayria.gems_realm.api.set.crystal.VanillaCrystalChildKeys.*;

/**
 * Put all undetected CrystalType here. The following reasons can be seen via Definition of REASONS
 * And a few examples
 **/
// Put all undetected CrystalTypes here
@ApiStatus.Internal
public class CompatCrystalType extends CompatBlockType {

    /* Defintion of REASONS:
     *
     * Spelling-Convention: a typo in the Id, no underscore
     *
     * Naming-Convention: Children has unique names that doesn't have "_cluster", "_shard", has different affix, or no affex
     *
     * Undetected-Children: CrystalType's children wasn't detected with Naming-Convention
     *
     * Vanilla-Children: CrystalType's Children is from Minecraft
     *
     * ModId-Children: CrystsalType's Children is from another mod aside its own mod
     *
     * 2-Words: The name of CrystalType is 2-Words instead of 1-Word
     */
    public static void init() {}

    static {

        CrystalTypeRegistry crystalReg = CrystalTypeRegistry.INSTANCE;

        // The Outer End - REASON: Undetected-Children
        crystalReg.addSimpleFinder("outer_end:rose_crystal")
                .crystalBlockSuffix("");
        crystalReg.addSimpleFinder("outer_end:mint_crystal")
                .crystalBlockSuffix("");
        crystalReg.addSimpleFinder("outer_end:cobalt_crystal")
                .crystalBlockSuffix("");

        // Excessive Building - REASON: Undetected-Children
        crystalReg.addSimpleFinder("excessive_building:prismarine_crystal")
                .childItem(SHARD, new ResourceLocation("minecraft:prismarine_crystals"));

        // Divine RPG - REASON: Undetected-Children
        crystalReg.addSimpleFinder("divinerpg", "olivine")
                .childItem(SHARD, "olivine");

        // Biomes O' Plenty - REASON: 2-Words
        crystalReg.addSimpleFinder("biomesoplenty:rose_quartz");

        // CrystalCraft-Unlimited-Java - REASON: 2-Words
        crystalReg.addSimpleFinder("crystalcraft_unlimited_java:aura_quartz")
                .childBlock(CLUSTER, "aura_crystal");

        // More Geodes Reforge
        crystalReg.addSimpleFinder("geodes:echo")
                .childItem(SHARD, new ResourceLocation("minecraft:echo_shard")) //REASON: Vanilla-Children
                .childBlock(BUDDING, "budding_echo_block"); //REASON: Undetected-Children
        crystalReg.addSimpleFinder("geodes:lapis_crystal") //REASON: Undetected-Children
                .childBlock(CLUSTER, "lapis_cluster"); //REASON: 2-Words, Undetected-Children
        crystalReg.addSimpleFinder("geodes:gypsum_crystal"); //REASON: 2-Words
        crystalReg.addSimpleFinder("geodes:diamond_crystal"); //REASON: 2-Words
        crystalReg.addSimpleFinder("geodes:quartz_crystal"); //REASON: 2-Words
        crystalReg.addSimpleFinder("geodes:emerald_crystal"); //REASON: 2-Words
    }
}
