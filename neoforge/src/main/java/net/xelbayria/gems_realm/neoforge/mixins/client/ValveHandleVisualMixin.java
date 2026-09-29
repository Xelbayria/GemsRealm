package net.xelbayria.gems_realm.neoforge.mixins.client;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.simibubi.create.content.kinetics.crank.HandCrankBlockEntity;
import com.simibubi.create.content.kinetics.crank.ValveHandleVisual;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.xelbayria.gems_realm.GemsRealm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static net.xelbayria.gems_realm.modules.create.CreateModuleAbstract.VALVE_HANDLES;

@Mixin(ValveHandleVisual.class)
public abstract class ValveHandleVisualMixin {

    @Definition(id = "VALVE_HANDLE", field = "Lcom/simibubi/create/AllPartialModels;VALVE_HANDLE:Ldev/engine_room/flywheel/lib/model/baked/PartialModel;", remap = false)
    @Expression("VALVE_HANDLE")
    @ModifyExpressionValue(method = "<init>", at = @At("MIXINEXTRAS:EXPRESSION"))
    private PartialModel gemsrealm$notJustCopperValve(PartialModel original, @Local(argsOnly = true) HandCrankBlockEntity blockEntity) {
        PartialModel replacement = VALVE_HANDLES.get(Utils.getID(blockEntity.getBlockState().getBlock()));
        GemsRealm.LOGGER.warn("VALVE_CHECKER: passed - {}", replacement.modelLocation());
        return replacement != null ? replacement : original;
    }

}

