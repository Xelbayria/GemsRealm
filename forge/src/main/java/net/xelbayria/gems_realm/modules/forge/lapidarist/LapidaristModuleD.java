package net.xelbayria.gems_realm.modules.forge.lapidarist;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
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
import net.xelbayria.gems_realm.api.GemsRealmEntrySet;
import net.xelbayria.gems_realm.api.GemsRealmModule;
import net.xelbayria.gems_realm.api.set.DustType;
import net.xelbayria.gems_realm.api.set.DustTypeRegistry;
import org.jetbrains.annotations.NotNull;

//SUPPORT: v
public class LapidaristModuleD extends GemsRealmModule {

    public final SimpleEntrySet<DustType, Block> bricks;
    public final SimpleEntrySet<DustType, Block> brick_stairs;
    public final SimpleEntrySet<DustType, Block> brick_slab;
    public final SimpleEntrySet<DustType, Block> cut;
    public final SimpleEntrySet<DustType, Block> cut_stairs;
    public final SimpleEntrySet<DustType, Block> cut_slab;
    public final SimpleEntrySet<DustType, Block> tiles;
    public final SimpleEntrySet<DustType, Block> tile_stairs;
    public final SimpleEntrySet<DustType, Block> tile_slab;
    public final SimpleEntrySet<DustType, Block> chiseled;
    public final SimpleEntrySet<DustType, Block> pillar;

    public LapidaristModuleD(String modId) {
        super(modId, "lpd");
        ResourceLocation tab = modRes("lapidary_tab");

        bricks = GemsRealmEntrySet.of(DustType.class, "bricks",
                        getModBlock("redstone_bricks"), DustTypeRegistry::getRedstoneType,
                        dustType -> new PoweredBlock(copyPropertiesSafe(dustType))
                )
                .addTexture(modRes("block/redstone_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(new ResourceLocation("minecrafft:needs_wooden_tool"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("redstone_bricks_stonecutting"))
                .build();
        this.addEntry(bricks);

        brick_stairs = GemsRealmEntrySet.of(DustType.class, "brick_stairs",
                        getModBlock("redstone_brick_stairs"), DustTypeRegistry::getRedstoneType,
                        dustType -> new CompatRedstoneStair(bricks.blocks.get(dustType).defaultBlockState(),
                                copyPropertiesSafe(dustType)
                        )
                )
                .requiresFromMap(bricks.blocks) //REASON: recipes. textures
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .addTag(new ResourceLocation("minecrafft:needs_wooden_tool"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("redstone_brick_stairs_from_redstone_block"))
                .addRecipe(modRes("redstone_brick_stairs_from_redstone_bricks"))
                .build();
        this.addEntry(brick_stairs);

        brick_slab = GemsRealmEntrySet.of(DustType.class, "brick_slab",
                        getModBlock("redstone_brick_slab"), DustTypeRegistry::getRedstoneType,
                        dustType -> new CompatRedstoneSlab(copyPropertiesSafe(dustType))
                )
                .requiresFromMap(bricks.blocks) //REASON: recipes. textures
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .addTag(new ResourceLocation("minecrafft:needs_wooden_tool"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("redstone_brick_slab_from_redstone_block"))
                .addRecipe(modRes("redstone_brick_slab_from_redstone_bricks"))
                .build();
        this.addEntry(brick_slab);

        cut = GemsRealmEntrySet.of(DustType.class, "", "cut",
                        getModBlock("cut_redstone"), DustTypeRegistry::getRedstoneType,
                        dustType -> new PoweredBlock(copyPropertiesSafe(dustType))
                )
                .addTexture(modRes("block/cut_redstone"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(new ResourceLocation("minecrafft:needs_wooden_tool"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("cut_redstone_stonecutting"))
                .build();
        this.addEntry(cut);

        cut_stairs = GemsRealmEntrySet.of(DustType.class, "stairs", "cut",
                        getModBlock("cut_redstone_stairs"), DustTypeRegistry::getRedstoneType,
                        dustType -> new CompatRedstoneStair(cut.blocks.get(dustType).defaultBlockState(),
                                copyPropertiesSafe(dustType)
                        )
                )
                .requiresFromMap(cut.blocks) //REASON: recipes, textures
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .addTag(new ResourceLocation("minecrafft:needs_wooden_tool"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("cut_redstone_stairs_from_redstone_block"))
                .addRecipe(modRes("cut_redstone_stairs_from_cut_redstone"))
                .build();
        this.addEntry(cut_stairs);

        cut_slab = GemsRealmEntrySet.of(DustType.class, "slab", "cut",
                        getModBlock("cut_redstone_slab"), DustTypeRegistry::getRedstoneType,
                        dustType -> new CompatRedstoneSlab(copyPropertiesSafe(dustType))
                )
                .requiresFromMap(cut.blocks) //REASON: recipes, textures
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .addTag(new ResourceLocation("minecrafft:needs_wooden_tool"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("cut_redstone_slab_from_redstone_block"))
                .addRecipe(modRes("cut_redstone_slab_from_cut_redstone"))
                .build();
        this.addEntry(cut_slab);

        tiles = GemsRealmEntrySet.of(DustType.class, "tiles",
                        getModBlock("redstone_tiles"), DustTypeRegistry::getRedstoneType,
                        dustType -> new PoweredBlock(copyPropertiesSafe(dustType))
                )
                .addTexture(modRes("block/redstone_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(new ResourceLocation("minecrafft:needs_wooden_tool"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("redstone_tiles_stonecutting"))
                .build();
        this.addEntry(tiles);

        tile_stairs = GemsRealmEntrySet.of(DustType.class, "tile_stairs",
                        getModBlock("redstone_tile_stairs"), DustTypeRegistry::getRedstoneType,
                        dustType -> new CompatRedstoneStair(tiles.blocks.get(dustType).defaultBlockState(),
                                copyPropertiesSafe(dustType)
                        )
                )
                .requiresFromMap(tiles.blocks) //REASON: recipes, textures
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .addTag(new ResourceLocation("minecrafft:needs_wooden_tool"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("redstone_tile_stairs_from_redstone_block"))
                .addRecipe(modRes("redstone_tile_stairs_from_redstone_tiles"))
                .build();
        this.addEntry(tile_stairs);

        tile_slab = GemsRealmEntrySet.of(DustType.class, "tile_slab",
                        getModBlock("redstone_tile_slab"), DustTypeRegistry::getRedstoneType,
                        dustType -> new CompatRedstoneSlab(copyPropertiesSafe(dustType))
                )
                .requiresFromMap(tiles.blocks) //REASON: recipes, textures
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .addTag(new ResourceLocation("minecrafft:needs_wooden_tool"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("redstone_tile_slab_from_redstone_block"))
                .addRecipe(modRes("redstone_tile_slab_from_redstone_tiles"))
                .build();
        this.addEntry(tile_slab);

        chiseled = GemsRealmEntrySet.of(DustType.class, "", "chiseled",
                        getModBlock("chiseled_redstone"), DustTypeRegistry::getRedstoneType,
                        dustType -> new Block(Utils.copyPropertySafe(dustType.block))
                )
                .addTexture(modRes("block/chiseled_redstone"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(new ResourceLocation("minecrafft:needs_wooden_tool"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("chiseled_redstone_stonecutting"))
                .build();
        this.addEntry(chiseled);

        pillar = GemsRealmEntrySet.of(DustType.class, "pillar",
                        getModBlock("redstone_pillar"), DustTypeRegistry::getRedstoneType,
                        dustType -> new RotatedPillarBlock(Utils.copyPropertySafe(dustType.block))
                )
                .addTexture(modRes("block/redstone_pillar"))
                .addTexture(modRes("block/redstone_pillar_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(new ResourceLocation("minecrafft:needs_wooden_tool"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("redstone_pillar_stonecutting"))
                .build();
        this.addEntry(pillar);

    }

    public BlockBehaviour.Properties copyPropertiesSafe(DustType dustType) {
        return Utils.copyPropertySafe(dustType.block)
                .sound(SoundType.METAL)
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops();
    }

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

        public boolean isSignalSource(@NotNull BlockState p_55213_) {
            return true;
        }

        public int getSignal(@NotNull BlockState p_55208_, @NotNull BlockGetter p_55209_, @NotNull BlockPos p_55210_, @NotNull Direction p_55211_) {
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

        public boolean isSignalSource(@NotNull BlockState p_55213_) {
            return true;
        }

        public int getSignal(@NotNull BlockState p_55208_, @NotNull BlockGetter p_55209_, @NotNull BlockPos p_55210_, @NotNull Direction p_55211_) {
            return 15;
        }

        static {
            AXIS = BlockStateProperties.AXIS;
        }
    }
}