package net.xelbayria.gems_realm;


import net.mehvahdjukaar.every_compat.configs.ECConfigs;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.xelbayria.gems_realm.api.set.MetalType;
import net.xelbayria.gems_realm.api.set.MetalTypeRegistry;
import net.xelbayria.gems_realm.api.set.crystal.CrystalType;
import net.xelbayria.gems_realm.api.set.crystal.CrystalTypeRegistry;
import net.xelbayria.gems_realm.api.set.dust.DustType;
import net.xelbayria.gems_realm.api.set.dust.DustTypeRegistry;
import net.xelbayria.gems_realm.api.set.gem.GemType;
import net.xelbayria.gems_realm.api.set.gem.GemTypeRegistry;

import java.util.List;

public class GemsRealmClient {

    public static void onItemTooltip(ItemStack stack, TooltipFlag tooltipFlag, List<Component> components) {
        boolean modTooltip = ECConfigs.MOD_TOOPTIP.get();
        boolean blockTooltip = ECConfigs.BLOCK_TYPE_TOOLTIP.get();

        if (modTooltip || blockTooltip && (tooltipFlag.isAdvanced() || !ECConfigs.TOOLTIPS_ADVANCED.get())) {
            Item item = stack.getItem();
            var compatModule = GemsRealm.getModuleOfItem(item);
            if (compatModule != null) {
                if (blockTooltip) {
                    CrystalType crystalType = CrystalTypeRegistry.INSTANCE.getBlockTypeOf(item);
                    if (crystalType != null) {
                        components.add(Component.translatable("tooltip.gemsrealm.crystal_type", crystalType.toString()).withStyle(ChatFormatting.AQUA));
                    }
                    DustType dustType = DustTypeRegistry.INSTANCE.getBlockTypeOf(item);
                    if (dustType != null) {
                        components.add(Component.translatable("tooltip.gemsrealm.dust_type", dustType.toString()).withStyle(ChatFormatting.AQUA));
                    }
                    GemType gemType = GemTypeRegistry.INSTANCE.getBlockTypeOf(item);
                    if (gemType != null) {
                        components.add(Component.translatable("tooltip.gemsrealm.gem_type", gemType.toString()).withStyle(ChatFormatting.AQUA));
                    }
                    MetalType metalType = MetalTypeRegistry.INSTANCE.getBlockTypeOf(item);
                    if (metalType != null) {
                        components.add(Component.translatable("tooltip.gemsrealm.metal_type", metalType.toString()).withStyle(ChatFormatting.AQUA));
                    }
                }
            }
        }
    }
}
