package net.xelbayria.gems_realm.api.set.gem;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

@SuppressWarnings("unused")
public class VanillaGemTypes {

    public static final GemType EMERALD = GemTypeRegistry.INSTANCE.register(
            new GemType(new ResourceLocation("emerald"), Blocks.EMERALD_BLOCK)
    );
    public static final GemType DIAMOND = GemTypeRegistry.INSTANCE.register(
            new GemType(new ResourceLocation("diamond"), Blocks.DIAMOND_BLOCK)
    );
    public static final GemType LAPIS = GemTypeRegistry.INSTANCE.register(
            new GemType(new ResourceLocation("lapis"), Blocks.LAPIS_BLOCK)
    );
    public static final GemType QUARTZ = GemTypeRegistry.INSTANCE.register(
            new GemType(new ResourceLocation("quartz"), Blocks.QUARTZ_BLOCK)
    );
}
