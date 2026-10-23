package net.xelbayria.gems_realm.modules.neoforge.lapidarist;

import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.xelbayria.gems_realm.api.set.dust.DustType;
import net.xelbayria.gems_realm.api.set.dust.VanillaDustTypes;
import net.xelbayria.gems_realm.common_classes.BlockSetEntries;
import org.jetbrains.annotations.NotNull;

import static net.xelbayria.gems_realm.common_classes.BlockSetEntries.getParentBlockstate;

//See LapidaristModuleAbstract's SUPPORTED VERSION
public class LapidaristModuleD extends LapidaristModuleAbstract {

    public final BlockSetEntries<DustType> bricksSet,
            cutSet,
            tilesSet,
            chiseled,
            pillar;

    public LapidaristModuleD(String modId) {
        super(modId);

        bricksSet = BlockSetEntries.of3BlockFactory(
                        this, DustType.class, VanillaDustTypes.REDSTONE,
                        "redstone_bricks", dustType ->
                                new PoweredBlock(copyPropertiesSafe(dustType)),
                        "redstone_brick_slab", dustType ->
                                new CompatRedstoneSlab(copyPropertiesSafe(dustType)),
                        "redstone_brick_stairs", dustType ->
                                new CompatRedstoneStair(getParentBlockstate(VanillaDustTypes.REDSTONE, this, "bricks"), copyPropertiesSafe(dustType)),
                        tabSupplier
                )
                .block(builder -> builder
                        .addTag(BlockTags.BEACON_BASE_BLOCKS, Registries.BLOCK)
                        .addTag(ResourceLocation.parse("minecraft:needs_wooden_tool"), Registries.BLOCK)
                        .addRecipe(modRes("redstone_bricks_stonecutting"))
                )
                .slab(builder -> builder
                        //TEXTURES: bricks
                        .addTag(BlockTags.SLABS, Registries.BLOCK)
                        .addTag(ResourceLocation.parse("minecraft:needs_wooden_tool"), Registries.BLOCK)
                        .addRecipe(modRes("redstone_brick_slab_from_redstone_block"))
                        .addRecipe(modRes("redstone_brick_slab_from_redstone_bricks"))
                )
                .stairs(builder-> builder
                        //TEXTURES: bricks
                        .addTag(BlockTags.STAIRS, Registries.BLOCK)
                        .addTag(ResourceLocation.parse("minecraft:needs_wooden_tool"), Registries.BLOCK)
                        .addRecipe(modRes("redstone_brick_stairs_from_redstone_block"))
                        .addRecipe(modRes("redstone_brick_stairs_from_redstone_bricks"))
                )
                .defaultBlockTexture()
                .pickaxeTag()
                .defaultRecipe()
                .build();
        this.addEntries(bricksSet);

        cutSet = BlockSetEntries.of3BlockFactory(
                        this, DustType.class, VanillaDustTypes.REDSTONE,
                        "cut_redstone", dustType ->
                                new PoweredBlock(copyPropertiesSafe(dustType)),
                        "cut_redstone_slab", dustType ->
                                new CompatRedstoneSlab(copyPropertiesSafe(dustType)),
                        "cut_redstone_stairs", dustType ->
                                new CompatRedstoneStair(getParentBlockstate(VanillaDustTypes.REDSTONE, this, "bricks"), copyPropertiesSafe(dustType)),
                        tabSupplier
                )
                .block(builder -> builder
                        .addTag(BlockTags.BEACON_BASE_BLOCKS, Registries.BLOCK)
                        .addTag(ResourceLocation.parse("minecraft:needs_wooden_tool"), Registries.BLOCK)
                        .addRecipe(modRes("redstone_bricks_stonecutting"))
                )
                .slab(builder -> builder
                        //TEXTURES: bricks
                        .addTag(BlockTags.SLABS, Registries.BLOCK)
                        .addTag(ResourceLocation.parse("minecraft:needs_wooden_tool"), Registries.BLOCK)
                        .addRecipe(modRes("cut_redstone_slab_from_redstone_block"))
                        .addRecipe(modRes("cut_redstone_slab_from_cut_redstone"))
                )
                .stairs(builder-> builder
                        //TEXTURES: bricks
                        .addTag(BlockTags.STAIRS, Registries.BLOCK)
                        .addTag(ResourceLocation.parse("minecraft:needs_wooden_tool"), Registries.BLOCK)
                        .addRecipe(modRes("cut_redstone_stairs_from_redstone_block"))
                        .addRecipe(modRes("cut_redstone_stairs_from_cut_redstone"))
                )
                .defaultBlockTexture()
                .pickaxeTag()
                .defaultRecipe()
                .build();
        this.addEntries(cutSet);

        tilesSet = BlockSetEntries.of3BlockFactory(
                        this, DustType.class, VanillaDustTypes.REDSTONE,
                        "redstone_tiles", dustType ->
                                new PoweredBlock(copyPropertiesSafe(dustType)),
                        "redstone_tile_slab", dustType ->
                                new CompatRedstoneSlab(copyPropertiesSafe(dustType)),
                        "redstone_tile_stairs", dustType ->
                                new CompatRedstoneStair(getParentBlockstate(VanillaDustTypes.REDSTONE, this, "bricks"), copyPropertiesSafe(dustType)),
                        tabSupplier
                )
                .block(builder -> builder
                        .requiresFromMap(cutSet.block.blocks) //REASON: recipes
                        .addTag(BlockTags.BEACON_BASE_BLOCKS, Registries.BLOCK)
                        .addTag(ResourceLocation.parse("minecraft:needs_wooden_tool"), Registries.BLOCK)
                        .addRecipe(modRes("redstone_tiles_from_redstone_block"))
                        .addRecipe(modRes("redstone_tiles_from_redstone_bricks"))
                        .addRecipe(modRes("redstone_tiles_from_cut_redstone"))
                )
                .slab(builder -> builder
                        //TEXTURES: bricks
                        .addTag(BlockTags.SLABS, Registries.BLOCK)
                        .addTag(ResourceLocation.parse("minecraft:needs_wooden_tool"), Registries.BLOCK)
                        .addRecipe(modRes("redstone_tile_slab_from_redstone_block"))
                        .addRecipe(modRes("redstone_tile_slab_from_redstone_tiles"))
                )
                .stairs(builder-> builder
                        //TEXTURES: bricks
                        .addTag(BlockTags.STAIRS, Registries.BLOCK)
                        .addTag(ResourceLocation.parse("minecraft:needs_wooden_tool"), Registries.BLOCK)
                        .addRecipe(modRes("redstone_tile_stairs_from_redstone_block"))
                        .addRecipe(modRes("redstone_tile_stairs_from_redstone_tiles"))
                )
                .defaultBlockTexture()
                .pickaxeTag()
                .defaultRecipe()
                .build();
        this.addEntries(tilesSet);

        chiseled = BlockSetEntries.ofBlockProperty(
                        this, DustType.class, VanillaDustTypes.REDSTONE,
                        "chiseled_redstone", this::copyPropertiesSafe,
                        tabSupplier
                )
                .block(builder -> builder
                        .requiresFromMap(tilesSet.block.blocks) //REASON: recipes
                        .addTag(BlockTags.BEACON_BASE_BLOCKS, Registries.BLOCK)
                        .addTag(ResourceLocation.parse("minecraft:needs_wooden_tool"), Registries.BLOCK)
                        .addRecipe(modRes("chiseled_redstone_from_redstone_block"))
                        .addRecipe(modRes("chiseled_redstone_from_redstone_bricks"))
                )
                .defaultBlockTexture()
                .pickaxeTag()
                .defaultRecipe()
                .build();
        this.addEntries(chiseled);

        pillar = BlockSetEntries.ofBlockFactory(
                        this, DustType.class, VanillaDustTypes.REDSTONE,
                        "redstone_pillar", metalType -> new RotatedPillarBlock(copyPropertiesSafe(metalType)),
                        tabSupplier
                )
                .block(builder -> builder
                        .requiresFromMap(tilesSet.block.blocks) //REASON: recipes
                        .addTag(BlockTags.BEACON_BASE_BLOCKS, Registries.BLOCK)
                        .addTag(ResourceLocation.parse("minecraft:needs_wooden_tool"), Registries.BLOCK)
                        .addRecipe(modRes("redstone_pillar_stonecutting"))
                )
                .defaultBlockTexture()
                .defaultBlockTextureTop()
                .pickaxeTag()
                .defaultRecipe()
                .build();
        this.addEntries(pillar);

    }

    //      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
    //      │                                          Utilities                                           │
    //      └──────────────────────────────────────────────────────────────────────────────────────────────┘

    public BlockBehaviour.Properties copyPropertiesSafe(DustType dustType) {
        return Utils.copyPropertySafe(dustType.block)
                .sound(SoundType.METAL)
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops();
    }

    //      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
    //      │                                           CLASSES                                            │
    //      └──────────────────────────────────────────────────────────────────────────────────────────────┘

//    public static class CompatRedstoneBlock extends PoweredBlock {
//        public static final EnumProperty<Direction.Axis> AXIS;
//
//        public CompatRedstoneBlock(Properties properties) {
//            super(properties);
//        }
//
//        static {
//            AXIS = BlockStateProperties.AXIS;
//        }
//    }

    public static class CompatRedstoneStair extends StairBlock {
        public static final EnumProperty<Direction.Axis> AXIS;

        public CompatRedstoneStair(BlockState block, Properties properties) {
            super(block, properties);
        }

        public boolean isSignalSource(@NotNull BlockState blockState) {
            return true;
        }

        public int getSignal(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull Direction direction) {
            return 15;
        }

        static {
            AXIS = BlockStateProperties.AXIS;
        }
    }

    public static class CompatRedstoneSlab extends SlabBlock {
        public static final EnumProperty<Direction.Axis> AXIS;

        public CompatRedstoneSlab(Properties properties) {
            super(properties);
        }

        public boolean isSignalSource(@NotNull BlockState blockState) {
            return true;
        }

        public int getSignal(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull Direction direction) {
            return 15;
        }

        static {
            AXIS = BlockStateProperties.AXIS;
        }
    }
}