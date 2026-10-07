package net.xelbayria.gems_realm.modules.neoforge.create.client;

import com.simibubi.create.content.decoration.RoofBlockCTBehaviour;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.xelbayria.gems_realm.api.set.metal.MetalType;

public class CompatRoofBlockCTBehaviour extends RoofBlockCTBehaviour {

    public final MetalType metalType;

    public CompatRoofBlockCTBehaviour(MetalType metaltype, CTSpriteShiftEntry shift) {
        super(shift);
        this.metalType = metaltype;
    }

    @Override
    protected boolean connects(BlockAndTintGetter reader, BlockPos pos, BlockState state, BlockState other) {
        double top = state.getCollisionShape(reader, pos).max(Direction.Axis.Y);

        double topOther = other.getSoundType() != metalType.getSound()
                ? 0
                : other.getCollisionShape(reader, pos).max(Direction.Axis.Y);

        return Mth.equal(top, topOther);
    }
}
