package net.xelbayria.gems_realm.api;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.api.AbstractSimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.api.TabAddMode;
import net.mehvahdjukaar.every_compat.misc.ModelConfiguration;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.moonlight.core.misc.McMetaFile;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.xelbayria.gems_realm.misc.SpriteHelper;
import net.xelbayria.gems_realm.misc.TintConfiguration;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.function.*;

@SuppressWarnings("unused")
public class GemsRealmEntrySet<T extends BlockType, B extends Block> extends SimpleEntrySet<T, B> {

    protected TintConfiguration tintConfiguration;

    protected GemsRealmEntrySet(Class<T> type, String name, @Nullable String prefix, Function<T, B> blockSupplier,
                                Supplier<B> baseBlock, Supplier<T> baseType,
                                @NotNull Supplier<ResourceKey<CreativeModeTab>> tab,
                                TabAddMode tabMode, LootTableMode lootMode,
                                @Nullable TriFunction<T, B, Item.Properties, Item> itemFactory,
                                @Nullable ITileHolder tileFactory, @Nullable Object renderType,
                                @Nullable BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable McMetaFile>> paletteSupplier,
                                @Nullable Consumer<BlockTypeResTransformer<T>> extraTransform,
                                boolean mergedPalette, TintConfiguration tintConfig, boolean copyTint,
                                Predicate<T> condition, ModelConfiguration modelConfig
    ) {

        super(type, name, prefix, blockSupplier, baseBlock, baseType, tab, tabMode, lootMode, itemFactory, tileFactory,
                renderType, paletteSupplier, extraTransform, mergedPalette, copyTint, condition, modelConfig);
        this.tintConfiguration = tintConfig;
        this.modelConfiguration = modelConfig;
    }

    public static <T extends BlockType, B extends Block> Builder<T, B> of(Class<T> type, String name, String prefix, Supplier<B> baseBlock, Supplier<T> baseType, Function<T, B> blockSupplier) {
        return new Builder<>(type, name, prefix, baseType, baseBlock, blockSupplier);
    }

    public static <T extends BlockType, B extends Block> Builder<T, B> of(Class<T> type, String name, Supplier<B> baseBlock, Supplier<T> baseType, Function<T, B> blockSupplier) {
        return new Builder<>(type, name, null, baseType, baseBlock, blockSupplier);
    }

    @Override
    protected BlockTypeResTransformer<T> makeModelTransformer(SimpleModule module, ResourceManager manager) {
        String nameBaseStone = baseType.get().getTypeName();
        BlockTypeResTransformer<T> transformer = BlockTypeResTransformer.create(module.getModId(), manager);
        if (Objects.nonNull(extraModelTransform)) extraModelTransform.accept(transformer);

        return transformer
                //TEXTURES: these need to be run first
                .replaceWithTextureFromChild("minecraft:block/anvil", "block")
                .replaceWithTextureFromChild("minecraft:block/" + nameBaseStone + "_block", "block")
                .replaceWithTextureFromChild("minecraft:block/raw_" + nameBaseStone + "_block", "raw_block")
                .replaceWithTextureFromChild("minecraft:block/" + nameBaseStone + "_bricks", "bricks")
                .replaceWithTextureFromChild("minecraft:block/smooth_" + nameBaseStone, "smooth")
                .replaceWithTextureFromChild("minecraft:block/polished_" + nameBaseStone, "polished")
                .replaceWithTextureFromChild("minecraft:block/mossy_" + nameBaseStone + "_bricks", "mossy_bricks")
                // Modifying parent & "elements" inside model files
//                .addModifier((s, blockId, blockType) -> {
//                    JsonObject jsonObject = GsonHelper.parse(s);
//                    ModelUtils.addTintIndexToModelAndReplaceParent(new ResourceLocation("none"), jsonObject, module, nameBaseStone, tintConfiguration);
//                    return jsonObject.toString();
//                })
                // Add modified model files to the resources
                .andThen(super.makeModelTransformer(module, manager));

    }

    @Override
    protected BlockTypeResTransformer<T> makeBlockStateTransformer(SimpleModule module, ResourceManager manager) {
        String nameBaseType = baseType.get().getTypeName();
        return BlockTypeResTransformer.<T>create(module.getModId(), manager)
//                .addModifier((s, blockId, stoneType) ->
//                        s.replace("minecraft:block/" + nameBaseType, getChildModelId("stone", stoneType, blockId)))
//                .addModifier((s, blockId, stoneType) ->
//                        s.replace("minecraft:block/" + nameBaseType + "_bricks", getChildModelId("bricks", stoneType, blockId)))
//                .addModifier((s, blockId, stoneType) ->
//                        s.replace("minecraft:block/smooth_" + nameBaseType, getChildModelId("smooth_stone", stoneType, blockId)))
                .andThen(super.makeBlockStateTransformer(module, manager));
    }

    private String getChildModelId(String childkey, T stoneType, ResourceLocation blockId) {
        if (SpriteHelper.modelID.containsKey(blockId)) return SpriteHelper.modelID.get(blockId);

        return Utils.getID(stoneType.getBlockOfThis(childkey)).withPrefix("block/").toString();
    }

    @Override
    public void generateModels(SimpleModule module, ResourceManager manager, ResourceSink sink) {
        makeBlockStateTransformer(module, manager);
        makeModelTransformer(module, manager);
        super.generateModels(module, manager, sink);
    }

//    @Override
//    public void generateTags(SimpleModule module, DynamicDataPack pack, ResourceManager manager) {
//        super.generateTags(module, pack, manager);
//
//    }


    //!! SUB-CLASS
    @SuppressWarnings("unused")
    public static class Builder<T extends BlockType, B extends Block> extends SimpleEntrySet.Builder<T, B> {

        protected TintConfiguration tintConfig = TintConfiguration.EMPTY;

        protected Builder(Class<T> type, String name, @Nullable String prefix, Supplier<T> baseType, Supplier<B> baseBlock, Function<T, B> blockFactory) {
            super(type, name, prefix, baseType, baseBlock, blockFactory);
        }

        public GemsRealmEntrySet.Builder<T, B> createPaletteFromBlock() {
            return (Builder<T, B>) createPaletteFromChild("block");
        }

        public GemsRealmEntrySet.Builder<T, B> createPaletteFromBricks() {
            this.setPalette((blockType, manager) -> {
                if (blockType.getChild("bricks") != null) {
                    return AbstractSimpleEntrySet.makePaletteFromChild(p -> {
                    }, "bricks", null, blockType, manager);
                }
                return AbstractSimpleEntrySet.makePaletteFromChild(p -> {
                }, "stone", null, blockType, manager);
            });
            return this;
        }

        /// Safe-fail: if a child is not found, then "block" will be used
        public GemsRealmEntrySet.Builder<T, B> createPaletteFromRockChild(String childKey) {
            this.setPalette((blockType, manager) -> {
                if (blockType.getChild(childKey) != null) {
                    return AbstractSimpleEntrySet.makePaletteFromChild(p -> {
                    }, childKey, null, blockType, manager);
                }
                return AbstractSimpleEntrySet.makePaletteFromChild(p -> {
                }, "block", null, blockType, manager);
            });
            return this;
        }

        @Override
        public GemsRealmEntrySet<T, B> build() {
            if (this.tab == null && PlatHelper.isDev()) {
                throw new IllegalStateException("Tab for module " + this.name + " was null!");
            } else {
                // all blocks could have tint as RockType could be tinted themselves
                this.copyParentTint();

                GemsRealmEntrySet<T, B> entry = new GemsRealmEntrySet<>(this.type, this.name, this.prefix, this.blockFactory, this.baseBlock,
                        this.baseType, this.tab, this.tabMode, this.lootMode, this.itemFactory,
                        this.tileHolder, this.renderType, this.palette, this.extraModelTransform,
                        this.useMergedPalette, this.tintConfig, this.copyTint,
                        this.condition, this.modelConfig);
                entry.recipeLocations.addAll(this.recipes);
                entry.tags.putAll(this.tags);
                entry.textures.addAll(this.textures);

                return entry;
            }
        }


        /// Exclude mutiple textures in one parent file
        public Builder<T, B> excludeMultipleTextureFromTinting(ResourceLocation parentId, String... textureKeys) {
            if (this.tintConfig == TintConfiguration.EMPTY) {
                this.tintConfig = TintConfiguration.createNew();
            }
            this.tintConfig.addParentAndTextureValues(parentId, textureKeys);
            return this;
        }

        /// Exclude multiple textures in all parent files
        public Builder<T, B> excludeTextureFromTinting(String... textureKeys) {
            if (this.tintConfig == TintConfiguration.EMPTY) {
                this.tintConfig = TintConfiguration.createNew();
            }
            this.tintConfig.addTextureValues(textureKeys);
            return this;
        }
    }

}
