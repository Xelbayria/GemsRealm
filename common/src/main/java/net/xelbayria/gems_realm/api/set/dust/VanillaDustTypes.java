package net.xelbayria.gems_realm.api.set.dust;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

public class VanillaDustTypes {

    public static final DustType REDSTONE = DustTypeRegistry.INSTANCE.register(
            new DustType(ResourceLocation.withDefaultNamespace("redstone"), Blocks.REDSTONE_BLOCK)
    );
}
