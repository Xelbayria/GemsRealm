package net.xelbayria.gems_realm.api.set.crystal;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

public class VanillaCrystalTypes {

    public static final CrystalType AMETHYST = CrystalTypeRegistry.INSTANCE.register(
            new CrystalType(new ResourceLocation("amethyst"), Blocks.AMETHYST_BLOCK)
    );
}
