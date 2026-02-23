package net.xelbayria.gems_realm.common_classes;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.api.set.metal.MetalTypeRegistry;
import net.xelbayria.gems_realm.misc.HardcodedBlockType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class CompatChestBlockRenderer extends ChestRenderer<CompatChestBlockEntity> {
    public static final ResourceLocation CHEST_SHEET = ResourceLocation.withDefaultNamespace("textures/atlas/chest.png");
    private final Map<MetalType, Material> single = new HashMap<>();
    private final Map<MetalType, Material> left = new HashMap<>();
    private final Map<MetalType, Material> right = new HashMap<>();
    private final Map<MetalType, Material> trapped = new HashMap<>();
    private final Map<MetalType, Material> trapped_left = new HashMap<>();
    private final Map<MetalType, Material> trapped_right = new HashMap<>();

    //assumes standard naming here. Generalize if needed
    public CompatChestBlockRenderer(BlockEntityRendererProvider.Context context, String shortenedId) {
        super(context);
        for (MetalType metalType : MetalTypeRegistry.INSTANCE) {
            if (HardcodedBlockType.isKnownVanillaMetal(metalType)) continue;
            String path = "entity/chest/" + shortenedId + "/" + metalType.getAppendableId() + "_chest";
            String trapped_path = "entity/chest/" + shortenedId + "/" + metalType.getAppendableId() + "_trapped_chest";
            if (!metalType.isVanilla()) {
                single.put(metalType, new Material(CHEST_SHEET, GemsRealm.res(path)));
                left.put(metalType, new Material(CHEST_SHEET, GemsRealm.res(path + "_left")));
                right.put(metalType, new Material(CHEST_SHEET, GemsRealm.res(path + "_right")));
                trapped.put(metalType, new Material(CHEST_SHEET, GemsRealm.res(trapped_path)));
                trapped_left.put(metalType, new Material(CHEST_SHEET, GemsRealm.res(trapped_path + "_left")));
                trapped_right.put(metalType, new Material(CHEST_SHEET, GemsRealm.res(trapped_path + "_right")));
            }
        }
    }

    protected @NotNull Material getMaterial(CompatChestBlockEntity blockEntity, ChestType chestType) {
        MetalType w = blockEntity.getStoneType();
        if (blockEntity.isTrapped()) {
            return switch (chestType) {
                case LEFT -> trapped_left.get(w);
                case RIGHT -> trapped_right.get(w);
                default -> trapped.get(w);
            };
        } else {
            return switch (chestType) {
                case LEFT -> left.get(w);
                case RIGHT -> right.get(w);
                default -> single.get(w);
            };
        }
    }


    //copy pasted from ChestRenderer
    @Override
    public void render(CompatChestBlockEntity blockEntity, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight, int packedOverlay) {
        Level level = blockEntity.getLevel();
        boolean flag = level != null;
        BlockState blockstate = flag ? blockEntity.getBlockState() : Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);
        ChestType chesttype = blockstate.hasProperty(ChestBlock.TYPE) ? blockstate.getValue(ChestBlock.TYPE) : ChestType.SINGLE;
        Block flag1 = blockstate.getBlock();
        if (flag1 instanceof AbstractChestBlock<?> abstractchestblock) {
            boolean flag1x = chesttype != ChestType.SINGLE;
            poseStack.pushPose();
            float f = (blockstate.getValue(ChestBlock.FACING)).toYRot();
            poseStack.translate(0.5F, 0.5F, 0.5F);
            poseStack.mulPose(Axis.YP.rotationDegrees(-f));
            poseStack.translate(-0.5F, -0.5F, -0.5F);
            DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> neighborcombineresult;
            if (flag) {
                neighborcombineresult = abstractchestblock.combine(blockstate, level, blockEntity.getBlockPos(), true);
            } else {
                neighborcombineresult = DoubleBlockCombiner.Combiner::acceptNone;
            }

            float f1 = neighborcombineresult.apply(ChestBlock.opennessCombiner(blockEntity)).get(partialTick);
            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            int i = neighborcombineresult.apply(new BrightnessCombiner<>()).applyAsInt(packedLight);
            Material material = this.getMaterial(blockEntity, chesttype);
            VertexConsumer vertexconsumer = material.buffer(buffer, RenderType::entityCutout);
            var color = blockEntity.getTint();
            if (flag1x) {
                if (chesttype == ChestType.LEFT) {
                    this.render(poseStack, vertexconsumer, this.doubleLeftLid, this.doubleLeftLock, this.doubleLeftBottom, f1, i, packedOverlay);
                } else {
                    this.render(poseStack, vertexconsumer, this.doubleRightLid, this.doubleRightLock, this.doubleRightBottom, f1, i, packedOverlay);
                }
            } else {
                this.render(poseStack, vertexconsumer, this.lid, this.lock, this.bottom, f1, i, packedOverlay);
            }

            poseStack.popPose();
        }
    }

    private void render(PoseStack poseStack, VertexConsumer consumer, ModelPart lidPart, ModelPart lockPart, ModelPart bottomPart,
                        float lidAngle, int packedLight, int packedOverlay) {
        lidPart.xRot = -(lidAngle * 1.5707964F);
        lockPart.xRot = lidPart.xRot;
        lidPart.render(poseStack, consumer, packedLight, packedOverlay);
        lockPart.render(poseStack, consumer, packedLight, packedOverlay);
        bottomPart.render(poseStack, consumer, packedLight, packedOverlay);
    }

    public static void register(ClientHelper.BlockEntityRendererEvent event, BlockEntityType<CompatChestBlockEntity> tile, String s) {
        event.register(tile, c -> new CompatChestBlockRenderer(c, s));
    }

}
