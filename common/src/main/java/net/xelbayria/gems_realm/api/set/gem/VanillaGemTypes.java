package net.xelbayria.gems_realm.api.set.gem;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

@SuppressWarnings("unused")
public class VanillaGemTypes {

    public static final GemType EMERALD = GemTypeRegistry.INSTANCE.register(
            new GemType(ResourceLocation.withDefaultNamespace("emerald"), Blocks.EMERALD_BLOCK)
    );
    public static final GemType DIAMOND = GemTypeRegistry.INSTANCE.register(
            new GemType(ResourceLocation.withDefaultNamespace("diamond"), Blocks.DIAMOND_BLOCK)
    );
    public static final GemType LAPIS = GemTypeRegistry.INSTANCE.register(
            new GemType(ResourceLocation.withDefaultNamespace("lapis"), Blocks.LAPIS_BLOCK)
    );
    public static final GemType QUARTZ = GemTypeRegistry.INSTANCE.register(
            new GemType(ResourceLocation.withDefaultNamespace("quartz"), Blocks.QUARTZ_BLOCK)
    );
}
