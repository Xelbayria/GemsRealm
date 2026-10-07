package net.xelbayria.gems_realm.neoforge.mixins;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.simibubi.create.content.logistics.funnel.FunnelBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.xelbayria.gems_realm.modules.create.CreateModuleAbstract;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FunnelBlockEntity.class)
public abstract class FunnelBlockEntityMixin {

    @ModifyReturnValue(
            method = "supportsFiltering",
            at = @At("RETURN")
    )
    private boolean extendedSupportsFiltering(boolean original, @Local(name = "blockState") BlockState blockState) {
        if (CreateModuleAbstract.supportsFilteringList.contains(blockState.getBlock())) return true;
        return original;
    }
}
