package net.xelbayria.gems_realm.api.set.metal;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

@SuppressWarnings("unused")
public class VanillaMetalTypes {

    public static final MetalType IRON = MetalTypeRegistry.INSTANCE.register(
            new MetalType(new ResourceLocation("iron"), Blocks.IRON_BLOCK)
    );

    public static final MetalType GOLD = MetalTypeRegistry.INSTANCE.register(
            new MetalType(new ResourceLocation("gold"), Blocks.GOLD_BLOCK)
    );

    public static final MetalType COPPER = MetalTypeRegistry.INSTANCE.register(
            new MetalType(new ResourceLocation("copper"), Blocks.COPPER_BLOCK)
    );
}
