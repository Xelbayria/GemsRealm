package net.xelbayria.gems_realm.api.intergration;

import net.minecraft.resources.ResourceLocation;
import net.xelbayria.gems_realm.api.set.dust.DustTypeRegistry;
import org.jetbrains.annotations.ApiStatus;

import static net.xelbayria.gems_realm.api.set.dust.VanillaDustChildKeys.DUST;

/**
 * Put all undetected DustTypes here. The following reasons can be seen via Definition of REASONS
 * And a few examples
**/
@ApiStatus.Internal
public class CompatDustType extends CompatBlockType {

    /* Defintion of REASONS:
     *
     * Spelling-Convention: a typo in the Id, no underscore
     *
     * Naming-Convention: blocks has unique names that doesn't have "_dust" or has different affix
     *
     * Undetected-Children: CrystalType's children wasn't detected with Naming-Convention
     *
     * Vanilla-Children: CrystalType's Children is from Minecraft
     *
     * ModId-Children: CrystsalType's Children is from another mod aside its own mod
     *
     * 2-Words: The name of CrystalType is 2-Words instead of 1-Word
     *
     */
    public static void init() {}

    static {

        DustTypeRegistry dustReg = DustTypeRegistry.INSTANCE;

        // -------------------- More Ores More Gems
        dustReg.addSimpleFinder("more_ores_more_gems:gunpowder")
                .childItem(DUST, new ResourceLocation("gunpowder")); //REASON: Naming-Convention, Vanilla-Children

        // -------------------- Atlantis (MysticPasta1) - REASON: 2-Words, Naming-Convention
        dustReg.addSimpleFinder("atlantis:aquatic_power")
                .dustBlockSuffix("_stone");

        // -------------------- CrystalCraft-Unlimited-Java - REASON: Undetected-Children
        dustReg.addSimpleFinder("crystalcraft_unlimited_java:ikegamini")
                .childItem(DUST, "ikegamini");
        dustReg.addSimpleFinder("crystalcraft_unlimited_java:ikegamonium")
                .childItem(DUST, "ikegamonium");
        dustReg.addSimpleFinder("crystalcraft_unlimited_java:ikegamium")
                .childItem(DUST, "ikegamium");
        dustReg.addSimpleFinder("crystalcraft_unlimited_java:simonium")
                .childItem(DUST, "simonium");
        dustReg.addSimpleFinder("crystalcraft_unlimited_java:simoganium")
                .childItem(DUST, "simoganium");
        dustReg.addSimpleFinder("crystalcraft_unlimited_java:chloronium")
                .childItem(DUST, "chloronium");
        dustReg.addSimpleFinder("crystalcraft_unlimited_java:whitestone")
                .childItem(DUST, "whitestone");
        dustReg.addSimpleFinder("crystalcraft_unlimited_java:blackstone")
                .childItem(DUST, "blackstone");
        dustReg.addSimpleFinder("crystalcraft_unlimited_java:sulfur")
                .childItem(DUST, "sulfur");
    }

}
