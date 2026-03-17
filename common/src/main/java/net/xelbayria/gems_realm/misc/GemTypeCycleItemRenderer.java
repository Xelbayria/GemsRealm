package net.xelbayria.gems_realm.misc;

import net.mehvahdjukaar.every_compat.misc.BlockTypeCycleItemRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.configs.GRConfigs;

import java.util.Objects;

public class GemTypeCycleItemRenderer extends BlockTypeCycleItemRenderer<MetalType> {

    public GemTypeCycleItemRenderer() {
        super(MetalType.class);
    }

    @Override
    @SuppressWarnings("ConstantValue")
    public ItemStack getItemIcon() {
        var itemId = GRConfigs.CREATIVE_TAB_ICON.get();
        ItemStack item = BuiltInRegistries.ITEM.get(ResourceLocation.parse(itemId)).getDefaultInstance();

        if (Objects.nonNull(item)) return item;
        else return Items.BARRIER.getDefaultInstance();
    }

    @Override
    public boolean getDisableCycleItemRenderer() {
        return GRConfigs.DISABLE_CYCLE_ITEM_RENDERER.get();
    }
}
