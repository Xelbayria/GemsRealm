package net.xelbayria.gems_realm.common_classes;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.api.set.metal.MetalTypeRegistry;
import net.xelbayria.gems_realm.api.set.metal.VanillaMetalTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CompatChestBlockEntity extends ChestBlockEntity {
    private final MetalType metalType;
    private final boolean trapped;
    private float[] tint = null;

    public CompatChestBlockEntity(BlockEntityType<?> arg, BlockPos pos, BlockState state) {
        super(arg, pos, state);
        MetalType stone = MetalTypeRegistry.INSTANCE.getBlockTypeOf(state.getBlock());
        this.metalType = stone == null ? VanillaMetalTypes.IRON : stone;
        this.trapped = state.getBlock() instanceof CompatTrappedChestBlock;
    }

    public float[] getTint() {
        if (this.tint == null) {
            int color = Minecraft.getInstance().getBlockColors().getColor(Objects.requireNonNull(metalType.getBlockOfThis("block")).defaultBlockState(), level, worldPosition, 0);
            this.tint = new float[]{
                    (color >> 16 & 255) / 255F,
                    (color >> 8 & 255) / 255F,
                    (color & 255) / 255F,
                    1.0F
            };
        }
        return this.tint;
    }

    public MetalType getStoneType() {
        return metalType;
    }

    @Override
    protected void signalOpenCount(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, int eventId, int eventParam) {
        super.signalOpenCount(level, pos, state, eventId, eventParam);
        if (trapped && eventId != eventParam) {
            Block block = state.getBlock();
            level.updateNeighborsAt(pos, block);
            level.updateNeighborsAt(pos.below(), block);
        }
    }

    public boolean isTrapped() {
        return trapped;
    }

}
