package net.xelbayria.gems_realm.api;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.api.*;
import net.mehvahdjukaar.every_compat.misc.ModelConfiguration;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.xelbayria.gems_realm.misc.SpriteHelper;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.*;

import static net.xelbayria.gems_realm.api.set.VanillaRockChildKeys.BRICKS;

@SuppressWarnings("unused")
public class GemsRealmEntrySet<T extends BlockType, B extends Block> extends SimpleEntrySet<T, B> {


    protected GemsRealmEntrySet(Class<T> type, String name, @Nullable String prefix, Function<T, B> blockSupplier,
                                Supplier<B> baseBlock, Supplier<T> baseType,
                                @NotNull Supplier<ResourceKey<CreativeModeTab>> tab,
                                TabAddMode tabMode, LootTableMode lootMode,
                                @Nullable TriFunction<T, B, Item.Properties, Item> itemFactory,
                                @Nullable ITileHolder tileFactory, @Nullable Object renderType,
                                @Nullable BiFunction<T, ResourceManager, PaletteStrategy.PaletteAndAnimation> paletteSupplier,
                                @Nullable Consumer<BlockTypeResTransformer<T>> extraTransform,
                                boolean mergedPalette,  boolean copyTint, Predicate<T> condition,
                                ModelConfiguration modelConfig
    ) {

        super(type, name, prefix, blockSupplier, baseBlock, baseType, tab, tabMode, lootMode, itemFactory, tileFactory,
                renderType, paletteSupplier, extraTransform, mergedPalette, copyTint, condition, modelConfig);
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
        String nameBaseRock = baseType.get().getTypeName();
        BlockTypeResTransformer<T> transformer = BlockTypeResTransformer.create(module.getModId(), manager);
        if (Objects.nonNull(extraModelTransform)) extraModelTransform.accept(transformer);

        return transformer
                //TEXTURES: these need to be run first
                .replaceWithTextureFromChild("minecraft:block/anvil", "block")
                .replaceWithTextureFromChild("minecraft:block/" + nameBaseRock + "_block", "block")
                .replaceWithTextureFromChild("minecraft:block/raw_" + nameBaseRock + "_block", "raw_block")
                .replaceWithTextureFromChild("minecraft:block/" + nameBaseRock + "_bricks", "bricks")
                .replaceWithTextureFromChild("minecraft:block/smooth_" + nameBaseRock, "smooth")
                .replaceWithTextureFromChild("minecraft:block/polished_" + nameBaseRock, "polished")
                .replaceWithTextureFromChild("minecraft:block/mossy_" + nameBaseRock + "_bricks", "mossy_bricks")
                .replaceWithTextureFromChild("minecraft:block/budding_" + nameBaseRock, "budding")
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

        return Utils.getID(Objects.requireNonNull(stoneType.getBlockOfThis(childkey))).withPrefix("block/").toString();
    }


    //!! SUB-CLASS
    @SuppressWarnings("DataFlowIssue") // McMetaFile is nullable
    public static class Builder<T extends BlockType, B extends Block> extends SimpleEntrySet.Builder<T, B> {

        protected Builder(Class<T> type, String name, @Nullable String prefix, Supplier<T> baseType, Supplier<B> baseBlock, Function<T, B> blockFactory) {
            super(type, name, prefix, baseType, baseBlock, blockFactory);
        }

        /// @deprecated new method haven't been implemented yet
        @Deprecated(forRemoval = true)
        public GemsRealmEntrySet.Builder<T, B> createPaletteFromBlock() {
            return (Builder<T, B>) createPaletteFromChild("block");
        }

        /// @deprecated new method haven't been implemented yet
        @Deprecated(forRemoval = true)
        public GemsRealmEntrySet.Builder<T, B> createPaletteFromBricks() {
            this.setPalette((blockType, manager) -> {
                if (blockType.getChild(BRICKS) != null) {
                    var paletteAnimation = PaletteStrategies.makePaletteFromChild(blockType, manager, BRICKS, null, p -> {});
                    return Pair.of(paletteAnimation.palette(), paletteAnimation.animation());
                }
                var paletteAnimation = PaletteStrategies.makePaletteFromMainChild(blockType, manager);
                return Pair.of(paletteAnimation.palette(), paletteAnimation.animation());
            });
            return this;
        }

        /// @deprecated new method haven't been implemented yet
        @Deprecated(forRemoval = true)
        public GemsRealmEntrySet.Builder<T, B> createPaletteFromRockChild(String childKey) {
            this.setPalette((blockType, manager) -> {
                if (blockType.getChild(BRICKS) != null) {
                    var paletteAnimation = PaletteStrategies.makePaletteFromChild(blockType, manager, childKey, null, p -> {});
                    return Pair.of(paletteAnimation.palette(), paletteAnimation.animation());
                }
                var paletteAnimation = PaletteStrategies.makePaletteFromMainChild(blockType, manager);
                return Pair.of(paletteAnimation.palette(), paletteAnimation.animation());
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
                        this.useMergedPalette, this.copyTint,
                        this.condition, this.modelConfig);
                entry.recipeLocations.addAll(this.recipes);
                entry.tags.putAll(this.tags);
                entry.textures.addAll(this.textures);

                return entry;
            }
        }

    }

}
