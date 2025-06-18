package net.xelbayria.gems_realm.misc;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mehvahdjukaar.moonlight.api.client.ICustomItemRendererProvider;
import net.mehvahdjukaar.moonlight.api.client.ItemStackRenderer;
import net.minecraft.world.item.Item;
import net.xelbayria.gems_realm.api.set.GemType;

import java.util.function.Supplier;

public class AllGemsItem extends Item implements ICustomItemRendererProvider {

    public AllGemsItem() {
        super(new Item.Properties());
    }

    @Override
    @Environment(EnvType.CLIENT)
    public Supplier<ItemStackRenderer> getRendererFactory() {
        return GemTypeCycleItemRenderer::new;
    }
}
