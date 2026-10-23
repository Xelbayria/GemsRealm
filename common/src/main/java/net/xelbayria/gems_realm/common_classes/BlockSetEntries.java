package net.xelbayria.gems_realm.common_classes;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.api.PaletteStrategy;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.api.GemsRealmEntrySet;
import net.xelbayria.gems_realm.api.GemsRealmModule;
import net.xelbayria.gems_realm.api.set.RockType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.xelbayria.gems_realm.api.set.VanillaRockChildKeys.BLOCK;

public class BlockSetEntries<BT extends BlockType> {

    public final SimpleEntrySet<BT, Block> block;
    public final @Nullable SimpleEntrySet<BT, Block> slabOrExtra;
    public final @Nullable SimpleEntrySet<BT, Block> stairs;
    public final @Nullable SimpleEntrySet<BT, Block> wall;

    protected BlockSetEntries(
            SimpleEntrySet<BT, Block> block,
            @Nullable SimpleEntrySet<BT, Block> slabOrExtra,
            @Nullable SimpleEntrySet<BT, Block> stairs,
            @Nullable SimpleEntrySet<BT, Block> wall)
    {
        this.block = block;
        this.slabOrExtra = slabOrExtra;
        this.stairs = stairs;
        this.wall = wall;
    }

    /**
     * The BlockFactory are: Block
     *
     * <p style="color: yellow;">The parameters with "Parent" is for to copy Block's property</p>
     *
     * @param getBlockPath BlockId's path for Block
     */
    public static <T extends RockType> BlockSetBuilder<T> ofBlockParent(
            GemsRealmModule module, Class<T> typeClass, T baseTypeId,
            String getBlockPath, Function<T, Block> blockParent,
            Supplier<CreativeModeTab> tabSupplier)
    {
        return of4BlockFactory(module, typeClass, baseTypeId,
                getBlockPath, type -> new Block(Utils.copyPropertySafe(blockParent.apply(type))),
                null, null,
                null, null,
                null, null,
                tabSupplier
        );
    }

    /**
     * The BlockFactory are: Block
     *
     * <p style="color: yellow;">The parameters with "Property" is for copying Block's BlockBehaviour.Properties</p>
     *
     * @param getBlockPath BlockId's path for Block
     */
    public static <T extends RockType> BlockSetBuilder<T> ofBlockProperty(
            GemsRealmModule module, Class<T> typeClass, T baseTypeId,
            String getBlockPath, Function<T, BlockBehaviour.Properties> blockProperty,
            Supplier<CreativeModeTab> tabSupplier)
    {
        return of4BlockFactory(module, typeClass, baseTypeId,
                getBlockPath, type -> new Block(blockProperty.apply(type)),
                null, null,
                null, null,
                null, null,
                tabSupplier
        );
    }

    /**
     * <p style="color: yellow;">The parameters with "Factory" can be applied with custom classes</p>
     *
     * @param getBlockPath BlockId's path for Block
     */
    public static <T extends RockType> BlockSetBuilder<T> ofBlockFactory(
            GemsRealmModule module, Class<T> typeClass, T baseTypeId,
            String getBlockPath, Function<T, Block> blockFactory,
            Supplier<CreativeModeTab> tabSupplier)
    {
        return of2BlockFactory(module, typeClass, baseTypeId,
                getBlockPath, blockFactory,
                null, null,
                tabSupplier
        );
    }

//      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
//      │                                           2 Blocks                                           │
//      └──────────────────────────────────────────────────────────────────────────────────────────────┘

    /**
     * <p style="color: yellow;">The parameters with "Factory" can be applied with custom classes</p>
     *
     * @param getBlockPath BlockId's path for Block
     * @param getExtraBlockPath BlockId's path for Slab or Extra Block
     */
    public static <T extends RockType> BlockSetBuilder<T> of2BlockFactory(
            GemsRealmModule module, Class<T> typeClass, T baseTypeId,
            String getBlockPath, Function<T, Block> blockFactory,
            String getExtraBlockPath, Function<T, Block> extraBlockFactory,
            Supplier<CreativeModeTab> tabSupplier)
    {
        return of4BlockFactory(module, typeClass, baseTypeId,
                getBlockPath, blockFactory,
                getExtraBlockPath, extraBlockFactory,
                null, null,
                null, null,
                tabSupplier
        );
    }

//      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
//      │                                           3 Blocks                                           │
//      └──────────────────────────────────────────────────────────────────────────────────────────────┘

    /**
     * The first 3 factories by default are: Block, SlabBlock, and StairBlock, BUT wall are excluded
     *
     * <p style="color: yellow;">The parameters with "Parent" is for to copy Block's property</p>
     *
     * @param getBlockPath BlockId's path for Block
     * @param getSlabPath BlockId's path for Slab or Extra-Block
     * @param getStairsPath BlockId's path for Stairs
     */
    public static <T extends RockType> BlockSetBuilder<T> of3DefaultParent(
            GemsRealmModule module, Class<T> typeClass, T baseTypeId,
            String getBlockPath, Function<T, Block> blockparent,
            String getSlabPath, Function<T, Block> slabParent,
            String getStairsPath, Function<T, Block> stairsParent,
            Supplier<CreativeModeTab> tabSupplier)
    {
        String childKeyPath = getChildKeyPath(getBlockPath, baseTypeId);

        return of4BlockFactory(module, typeClass, baseTypeId,
                getBlockPath, type -> new Block(Utils.copyPropertySafe(blockparent.apply(type))),
                getSlabPath, type -> new SlabBlock(Utils.copyPropertySafe(slabParent.apply(type))),
                getStairsPath, type ->
                    new StairBlock(getParentBlockstate(type, module, childKeyPath), Utils.copyPropertySafe(stairsParent.apply(type))),
                null, null,
                tabSupplier
        );
    }

    /**
     * The first 3 factories by default are: Block, SlabBlock, and StairBlock, BUT wall are excluded
     *
     * <p style="color: yellow;">The parameters with "Property" is for copying Block's BlockBehaviour.Properties</p>
     *
     * @param getBlockPath BlockId's path for Block
     * @param getSlabPath BlockId's path for Slab
     * @param getStairsPath BlockId's path for Stairs
     */
    public static <T extends RockType> BlockSetBuilder<T> of3DefaultProperty(
            GemsRealmModule module, Class<T> typeClass, T baseTypeId,
            String getBlockPath, Function<T, BlockBehaviour.Properties> blockProperty,
            String getSlabPath, Function<T, BlockBehaviour.Properties> slabProperty,
            String getStairsPath, Function<T, BlockBehaviour.Properties> stairsProperty,
            Supplier<CreativeModeTab> tabSupplier)
    {
        String childKeyPath = getChildKeyPath(getBlockPath, baseTypeId);

        return of4BlockFactory(module, typeClass, baseTypeId,
                getBlockPath, type -> new Block(blockProperty.apply(type)),
                getSlabPath, type -> new SlabBlock(slabProperty.apply(type)),
                getStairsPath, type ->
                    new StairBlock(getParentBlockstate(type, module, childKeyPath), stairsProperty.apply(type)),
                null, null,
                tabSupplier
        );
    }

    /**
     * <p style="color: yellow;">The parameters with "Factory" can be applied with custom classes</p>
     *
     * @param getBlockPath BlockId's path for Block
     * @param getExtraBlockPath BlockId's path for Slab or Extra Block
     * @param getStairsPath BlockId's path for Stairs
     */
    public static <T extends RockType> BlockSetBuilder<T> of3BlockFactory(
            GemsRealmModule module, Class<T> typeClass, T baseTypeId,
            String getBlockPath, Function<T, Block> blockFactory,
            String getExtraBlockPath, Function<T, Block> extraBlockFactory,
            String getStairsPath, Function<T, Block> stairsBlockFactory,
            Supplier<CreativeModeTab> tabSupplier)
    {
        return of4BlockFactory(module, typeClass, baseTypeId,
                getBlockPath, blockFactory,
                getExtraBlockPath, extraBlockFactory,
                getStairsPath, stairsBlockFactory,
                null, null,
                tabSupplier
        );
    }

    //      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
    //      │                                           4 Blocks                                           │
    //      └──────────────────────────────────────────────────────────────────────────────────────────────┘
    public static <T extends RockType> BlockSetBuilder<T> of4BlockFactory(
            GemsRealmModule module, Class<T> typeClass, T baseTypeId,
            String getBlockPath, Function<T, Block> blockFactory,
            @Nullable String getSlabOrExtraPath, @Nullable Function<T, Block> slabOrExtraFactory,
            @Nullable String getStairsPath, @Nullable Function<T, Block> stairsFactory,
            @Nullable String getWallPath, @Nullable Function<T, Block> wallFactory,
            Supplier<CreativeModeTab> tabSupplier)
    {
        return new BlockSetBuilder<>(typeClass, module, baseTypeId,
                getBlockPath, blockFactory,
                getSlabOrExtraPath, slabOrExtraFactory,
                getStairsPath, stairsFactory,
                getWallPath, wallFactory,
                tabSupplier
        );
    }

    //      ┌────────────────────────────────────────────────────────────────┐
    //      │                          Utilities                             │
    //      └────────────────────────────────────────────────────────────────┘
    public static <T extends RockType> BlockState getParentBlockstate(T stoneType, GemsRealmModule module, String path) {
        return Objects.requireNonNull(stoneType.getBlockOfThis(module.getModId() + ":" + path)).defaultBlockState();
    }
    public static <T extends RockType> String getChildKeyPath(String baseblockPath, T oldStoneType) {
        String typeName = oldStoneType.getTypeName();

        Pattern pattern = Pattern.compile("(?<prefix>(?:[a-z]+_)?[a-z]+)?_?"+typeName+"_?(?<suffix>\\w+)?");
        Matcher matcher = pattern.matcher(baseblockPath);

        if (matcher.find()) {
            String prefix = matcher.group("prefix");
            String suffix = matcher.group("suffix");

            if (prefix == null && suffix != null) return suffix;
            else if (prefix != null && suffix == null) return prefix;
            else return prefix +"_"+ suffix;
        }
        else {
            GemsRealm.LOGGER.error("BlockSetEntry - Failed to get DecorativeName for {} - Defaulting to BlockType.BLOCK", baseblockPath);
            return BLOCK;
        }
    }


//      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
//      │                                         SUB CLASSES                                          │
//      └──────────────────────────────────────────────────────────────────────────────────────────────┘
    @SuppressWarnings({"unused", "FieldMayBeFinal"}) // noSlab() or noStairs() will be used in a rare case
    public static class BlockSetBuilder<T extends BlockType> extends GemsRealmModule {

        private final T baseType;

        private final Class<T> typeClass;

        private final String blockPath;
        private final String slabPath;
        private final String stairsPath;
        private final String wallPath;
        private final Function<T, Block> slabFunction;
        private final Function<T, Block> stairsFunction;
        private final Function<T, Block> wallFunction;

        private final Supplier<CreativeModeTab> tabSupplier;

        private final SimpleEntrySet.Builder<T, Block> blockBuilder;

        private Consumer<GemsRealmEntrySet.Builder<T, Block>> stairsConfig =
                builder -> {};

        private Consumer<GemsRealmEntrySet.Builder<T, Block>> slabConfig =
                builder -> {};

        private Consumer<GemsRealmEntrySet.Builder<T, Block>> wallConfig =
                builder -> {};
        // ─────────────────────────────────────────────────────────────────────────────────────

        private boolean includeDefaultRecipe = false;

        private boolean includeBlockTexture = false;
        private String textureSideSuffix = "";
        private boolean includeBlockTextureTop = false;
        private String textureTopSuffix = "";

        private PaletteStrategy paletteStrategy = null;

        /// Adding the tags like #minecraft:slabs or #minecraft:stairs for slab, or stairs
        private boolean addSlabStairsAndWallTag = false;
        private boolean addPickaxeTag = false;
        private boolean addAxeTag = false;
        private boolean addIronToolTag = false;

        private @Nullable ResourceLocation tagResourceLocation = null;
        private ResourceKey<?>[] tagRegistries;


//      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
//      │                                         Constructor                                          │
//      └──────────────────────────────────────────────────────────────────────────────────────────────┘
        public BlockSetBuilder(Class<T> typeClass, GemsRealmModule module, T baseType,
                               String blockPath, Function<T, Block> blockFunction,
                               String slabPath, Function<T, Block> slabFunction,
                               String stairsPath, Function<T, Block> stairsFunction,
                               @Nullable String wallPath, @Nullable Function<T, Block> wallFunction,
                               Supplier<CreativeModeTab> tabSupplier
        ) {
            super(module.getModId(), module.shortenedId());

            this.typeClass = typeClass;
            this.baseType = baseType;

            this.blockPath = blockPath;
            this.slabPath = slabPath;
            this.stairsPath = stairsPath;
            this.wallPath = wallPath;

            this.slabFunction = slabFunction;
            this.stairsFunction = stairsFunction;
            this.wallFunction = wallFunction;

            this.tabSupplier = tabSupplier;

            /// ───────────────────────────── Blockbuilder ──────────────────────────────
            String prefix = getAffix(blockPath).getFirst();
            String suffix = getAffix(blockPath).getSecond();

            this.blockBuilder = GemsRealmEntrySet.of(typeClass, suffix, prefix,
                            getModBlock(blockPath), () -> baseType,
                            blockFunction
                    )
                    .setTab(tabSupplier);
        }

//      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
//      │                                            Build                                             │
//      └──────────────────────────────────────────────────────────────────────────────────────────────┘
        public BlockSetEntries<T> build() {
            SimpleEntrySet<T, Block> slab = null;
            SimpleEntrySet<T, Block> stairs = null;
            SimpleEntrySet<T, Block> wall = null;

            /// ───────────────────────────────── Block ─────────────────────────────────
            // Add Textures
            if (includeBlockTexture) {
                if (paletteStrategy != null) this.blockBuilder.addTexture(modRes("block/" + blockPath + textureSideSuffix), paletteStrategy);
                else this.blockBuilder.addTexture(modRes("block/" + blockPath + textureSideSuffix));
            }
            if (includeBlockTextureTop) {
                if (paletteStrategy != null) this.blockBuilder.addTexture(modRes("block/" + blockPath + textureTopSuffix), paletteStrategy);
                else this.blockBuilder.addTexture(modRes("block/" + blockPath + textureTopSuffix));
            }

            // Add Tags
            if (addPickaxeTag) this.blockBuilder.addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK);

            if (addAxeTag) this.blockBuilder.addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK);

            if (addIronToolTag) this.blockBuilder.addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK);

            if (tagResourceLocation != null) this.blockBuilder.addTag(tagResourceLocation, tagRegistries);

            // Add Recipes
            if (includeDefaultRecipe) blockBuilder.defaultRecipe();

            SimpleEntrySet<T, Block> block = blockBuilder.build();

            /// ───────────────────────────────── Slab ──────────────────────────────────
            if (slabFunction != null) {
                String prefix = getAffix(slabPath).getFirst();
                String suffix = getAffix(slabPath).getSecond();

                var slabBuilder = GemsRealmEntrySet.of(typeClass, suffix, prefix,
                                getModBlock(slabPath), () -> baseType,
                                slabFunction
                        )
                        .requiresFromMap(block.blocks) //REASON: Recipes, Textures
                        .setTab(tabSupplier)
                        .setRenderType(RenderLayer.CUTOUT_MIPPED);

                // Add Tags
                if (addPickaxeTag) slabBuilder.addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK);
                if (addAxeTag) slabBuilder.addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK);
                if (addSlabStairsAndWallTag) {
                    slabBuilder.addTag(BlockTags.SLABS, Registries.BLOCK)
                            .addTag(ItemTags.SLABS, Registries.ITEM);
                }
                if (addIronToolTag) slabBuilder.addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK);

                if (tagResourceLocation != null) slabBuilder.addTag(tagResourceLocation, tagRegistries);

                // Adding Recipes
                if (includeDefaultRecipe) slabBuilder.defaultRecipe();

                this.slabConfig.accept((GemsRealmEntrySet.Builder<T, Block>) slabBuilder);
                slab = (GemsRealmEntrySet<T, Block>) slabBuilder.build();
            }

            /// ──────────────────────────────── Stairs ─────────────────────────────────
            if (stairsFunction != null) {
                String prefix = getAffix(stairsPath).getFirst();
                String suffix = getAffix(stairsPath).getSecond();

                SimpleEntrySet.Builder<T, Block> stairsBuilder = GemsRealmEntrySet.of(typeClass, suffix, prefix,
                                getModBlock(stairsPath), () -> baseType,
                                stairsFunction
                        )
                        .requiresFromMap(block.blocks) //REASON: Recipes, Textures
                        .setTab(tabSupplier)
                        .setRenderType(RenderLayer.CUTOUT_MIPPED);

                // Add Tags
                if (addPickaxeTag) stairsBuilder.addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK);
                if (addAxeTag) stairsBuilder.addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK);
                if (addSlabStairsAndWallTag) {
                    stairsBuilder.addTag(BlockTags.STAIRS, Registries.BLOCK)
                            .addTag(ItemTags.STAIRS, Registries.ITEM);
                }
                if (addIronToolTag) stairsBuilder.addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK);

                if (tagResourceLocation != null) stairsBuilder.addTag(tagResourceLocation, tagRegistries);

                if (includeDefaultRecipe) stairsBuilder.defaultRecipe();

                this.stairsConfig.accept((GemsRealmEntrySet.Builder<T, Block>) stairsBuilder);
                stairs = (GemsRealmEntrySet<T, Block>) stairsBuilder.build();
            }

            /// ───────────────────────────────── Wall ──────────────────────────────────
            if (wallFunction != null) {
                String prefix = getAffix(wallPath).getFirst();
                String suffix = getAffix(wallPath).getSecond();

                var wallBuilder = GemsRealmEntrySet.of(typeClass, suffix, prefix,
                                getModBlock(wallPath), () -> baseType,
                                wallFunction
                        )
                        .requiresFromMap(block.blocks) //REASON: Recipes, Textures
                        .setTab(tabSupplier)
                        .setRenderType(RenderLayer.CUTOUT_MIPPED);

                if (addPickaxeTag) wallBuilder.addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK);
                if (addAxeTag) wallBuilder.addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK);
                if (addSlabStairsAndWallTag) {
                    wallBuilder.addTag(BlockTags.WALLS, Registries.BLOCK)
                            .addTag(ItemTags.WALLS, Registries.ITEM);
                }
                if (addIronToolTag) wallBuilder.addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK);

                if (tagResourceLocation != null) wallBuilder.addTag(tagResourceLocation, tagRegistries);

                if (includeDefaultRecipe) wallBuilder.defaultRecipe();

                wallConfig.accept((GemsRealmEntrySet.Builder<T, Block>) wallBuilder);
                wall = (GemsRealmEntrySet<T, Block>) wallBuilder.build();
            }

            return new BlockSetEntries<>(
                    block,
                    stairs,
                    slab,
                    wall
            );
        }

        //!! ─────────────────────────────────── Block ───────────────────────────────────
        public BlockSetBuilder<T> block(
                Consumer<SimpleEntrySet.Builder<T, Block>> config
        ) {
            config.accept(blockBuilder);
            return this;
        }

        /// the default ResourceLocation for texture is "block/getBlockPath"
        public BlockSetBuilder<T> defaultBlockTexture() {
            this.defaultBlockTexture("");
            return this;
        }

        /// the default ResourceLocation for texture is "block/getBlockPath" + sideSuffix
        public BlockSetBuilder<T> defaultBlockTexture(String sideSuffix) {
            this.includeBlockTexture = true;
            this.textureSideSuffix = sideSuffix;
            return this;
        }

        /// the default ResourceLocation for texture is "block/getBlockPath"
        public BlockSetBuilder<T> defaultBlockTexture(PaletteStrategy paletteStrategy) {
            this.includeBlockTexture = true;
            this.paletteStrategy = paletteStrategy;
            return this;
        }

        /// the default ResourceLocation for texture is "block/getBlockPath" + _top
        public BlockSetBuilder<T> defaultBlockTextureTop() {
            this.defaultBlockTextureTop("_top");
            return this;
        }

        /// the default ResourceLocation for texture is "block/getBlockPath" + topSuffix
        public BlockSetBuilder<T> defaultBlockTextureTop(String topSuffix) {
            this.includeBlockTextureTop = true;
            this.textureTopSuffix = topSuffix;
            return this;
        }

        //!! ────────────────────────────────── Stairs ───────────────────────────────────
        public BlockSetBuilder<T> stairs(Consumer<GemsRealmEntrySet.Builder<T, Block>> config) {
            this.stairsConfig = config;
            return this;
        }

        //!! ─────────────────────────────────── Slab ────────────────────────────────────
        public BlockSetBuilder<T> slab(Consumer<GemsRealmEntrySet.Builder<T, Block>> config) {
            this.slabConfig = config;
            return this;
        }

        //!! ─────────────────────────────────── Wall ────────────────────────────────────
        public BlockSetBuilder<T> wall(Consumer<GemsRealmEntrySet.Builder<T, Block>> config) {
            this.wallConfig = config;
            return this;
        }

        //      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
        //      │                                           OPTIONS                                            │
        //      └──────────────────────────────────────────────────────────────────────────────────────────────┘

        // ───────────────────────────────── Add Tags ──────────────────────────────────
        /// adding the tags like #minecraft;slabs or #minecraft:stairs for slab, or stairs. Wall is excluded, too
        public BlockSetBuilder<T> addSlabStairsWallTag() {
            this.addSlabStairsAndWallTag = true;
            return this;
        }

        /// Including MINEABLE_WITH_PICKAXE tag to all 4 blocks
        public BlockSetBuilder<T> pickaxeTag() {
            this.addPickaxeTag = true;
            return this;
        }

        /// Including MINEABLE_WITH_AXE tag to all 4 blocks
        public BlockSetBuilder<T> AxeTag() {
            this.addAxeTag = true;
            return this;
        }

        /// Including NEEDS_IRON_TOOL tag to all 4 blocks
        public BlockSetBuilder<T> IronToolTag() {
            this.addIronToolTag = true;
            return this;
        }
        /// Including NEEDS_IRON_TOOL tag to all 4 blocks
        public BlockSetBuilder<T> applyOneTag(ResourceLocation resourceLocation, ResourceKey<?>... registries) {
            this.tagResourceLocation = resourceLocation;
            this.tagRegistries = registries;
            return this;
        }

        /// Applying defaultRecipe() to all 4 blocks
        public BlockSetBuilder<T> defaultRecipe() {
            this.includeDefaultRecipe = true;
            return this;
        }

        //      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
        //      │                                          Utilities                                           │
        //      └──────────────────────────────────────────────────────────────────────────────────────────────┘
        public Pair<@Nullable String, @NotNull String> getAffix(String baseblockPath) {
            String typeName = baseType.getTypeName();

            Pattern pattern = Pattern.compile("(?<prefix>(?:[a-z]+_)?[a-z]+)?_?"+typeName+"_?(?<suffix>\\w+)?");
            Matcher matcher = pattern.matcher(baseblockPath);

            if (matcher.find()) {
                String prefix = matcher.group("prefix");
                String suffix = matcher.group("suffix");

                if (prefix == null && suffix != null) return Pair.of(null, suffix);
                else if (prefix != null && suffix == null) return Pair.of(prefix, "");
                else return Pair.of(prefix, suffix);
            }

            return Pair.of("", "");
        }
    }
}
