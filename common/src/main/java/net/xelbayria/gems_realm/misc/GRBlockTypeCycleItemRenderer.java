package net.xelbayria.gems_realm.misc;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.PoseStack;
import net.mehvahdjukaar.candlelight.api.ClientOnly;
import net.mehvahdjukaar.moonlight.api.client.ItemStackRenderer;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.api.set.crystal.CrystalType;
import net.xelbayria.gems_realm.api.set.dust.DustType;
import net.xelbayria.gems_realm.api.set.gem.GemType;
import net.xelbayria.gems_realm.api.set.metal.MetalType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import static net.mehvahdjukaar.every_compat.configs.ModEntriesConfigs.getBlockTypeConfigs;
import static net.mehvahdjukaar.every_compat.configs.ModEntriesConfigs.getChildConfigs;
import static net.xelbayria.gems_realm.api.set.VanillaRockChildKeys.BLOCK;

@ClientOnly
public abstract class GRBlockTypeCycleItemRenderer extends ItemStackRenderer {

    private final List<String> childKeys = new ArrayList<>();
    private final List<BlockType> moddedBlockTypes = new ArrayList<>();
    private final Class<? extends BlockType>[] typeClass;
    private ItemStack currentStack = Items.BARRIER.getDefaultInstance();
    private int lastIndex = 0;
    private int lastTime = 0;
    private int typeIndex = 0;
    private boolean initialized;

    @SafeVarargs
    public GRBlockTypeCycleItemRenderer(Class<? extends BlockType>... tClass) {
        super();
        this.typeClass = tClass;
    }

    private void initialize() {
        for (Class<? extends BlockType> currentType : typeClass) {
            BlockTypeRegistry<?> typeReg = BlockSetAPI.getTypeRegistry(currentType);
            if (typeReg != null) {
                String typeName = typeReg.typeName();

                if (isChildTypeEnabled(typeName, BLOCK) && !childKeys.contains(BLOCK)) childKeys.add(BLOCK);

                for (BlockType blockType : typeReg.getValues())
                    if (!HardcodedBlockType.isKnownVanillaBlockType(blockType) && isBlockTypeEnabled(blockType))
                        moddedBlockTypes.add(blockType);
            }
        }

        if (moddedBlockTypes.isEmpty()) childKeys.clear();
        Collections.shuffle(moddedBlockTypes);
    }
    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext transformType,
                             PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {

        if (!this.initialized) {
            this.initialize();
            this.initialized = true;
        }
        ItemStack item = getAnyItem();

        var itemRenderer = Minecraft.getInstance().getItemRenderer();

        matrixStack.pushPose();
        matrixStack.translate(0.5D, 0.5D, 0.5D);
        BakedModel bakedmodel = itemRenderer.getModel(item, null, null, 0);
        itemRenderer.render(item, transformType, false, matrixStack, buffer, combinedLight, combinedOverlay, bakedmodel);
        if (!bakedmodel.isGui3d()) Lighting.setupForFlatItems();
        //forces rendering now with flat lighting
        if (buffer instanceof MultiBufferSource.BufferSource bu) {
            bu.endBatch();
        }
        Lighting.setupFor3DItems();
        matrixStack.popPose();

    }


    public ItemStack getAnyItem() {
        int size = childKeys.size();
        if (size == 0) return Items.BARRIER.getDefaultInstance();
        int time = (int) (Util.getMillis() / 350L);
        int tm = time % (size+1);
        if (tm != lastTime) {

            ItemLike itemLike = null;
            do {
                var l = (this.lastIndex + 1) % size;
                // this.woodIndex = (this.woodIndex + 1);
                if (l < lastIndex || size == 1) this.typeIndex = (this.typeIndex + 1) % moddedBlockTypes.size();
                this.lastIndex = l;
                String key = childKeys.get(lastIndex);
                var vv = moddedBlockTypes.get(typeIndex % moddedBlockTypes.size()).getChild(key);
                if (vv instanceof ItemLike il) {
                    itemLike = il;
                }
            } while (itemLike == null);

            this.currentStack = itemLike.asItem().getDefaultInstance();
        }
        this.lastTime = tm;
        return currentStack;
    }

    // Below is a null check & ensure that the code is executed properly.

    public boolean isBlockTypeEnabled(BlockType blockType) {
        Supplier<Boolean> blockTypeConfig = switch (blockType.getRegistry().typeName()) {
            case "metal_type" -> getBlockTypeConfigs(MetalType.class).get(blockType.getId().toString());
            case "gem_type" -> getBlockTypeConfigs(GemType.class).get(blockType.getId().toString());
            case "crystal_type" -> getBlockTypeConfigs(CrystalType.class).get(blockType.getId().toString());
            case "dust_type" -> getBlockTypeConfigs(DustType.class).get(blockType.getId().toString());
            default -> {
                GemsRealm.LOGGER.error("isBlockTypeEnabled: Failed to get config for {} - {}", blockType.getRegistry().typeName(), blockType.getId());
                yield () -> false;
            }
        };

        if (blockTypeConfig != null) return blockTypeConfig.get();
        else return true;
    }

    public boolean isChildTypeEnabled(String blockType, String currentChildKey) {
        Supplier<Boolean> childConfig = switch (blockType) {
            case "metal_type" -> getChildConfigs(MetalType.class).get(currentChildKey);
            case "gem_type" -> getChildConfigs(GemType.class).get(currentChildKey);
            case "crystal_type" -> getChildConfigs(CrystalType.class).get(currentChildKey);
            case "dust_type" -> getChildConfigs(DustType.class).get(currentChildKey);
            default -> {
                GemsRealm.LOGGER.error("isChildTypeEnabled: Failed to get config for {} - {}", blockType, currentChildKey);
                yield () -> false;
            }
        };

        if (childConfig != null) return childConfig.get();
        else return true;
    }
}