package net.xelbayria.gems_realm.misc;

import net.mehvahdjukaar.candlelight.api.ClientOnly;
import net.mehvahdjukaar.moonlight.api.client.ICustomItemRendererProvider;
import net.mehvahdjukaar.moonlight.api.client.ItemStackRenderer;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class AllGemsItem extends Item implements ICustomItemRendererProvider {

    public AllGemsItem() {
        super(new Item.Properties());
    }

    @Override
    @ClientOnly
    public Supplier<ItemStackRenderer> getRendererFactory() {
        return GemTypeCycleItemRenderer::new;
    }
}
