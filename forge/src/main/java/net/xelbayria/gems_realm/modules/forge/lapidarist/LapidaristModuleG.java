package net.xelbayria.gems_realm.modules.forge.lapidarist;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.xelbayria.gems_realm.api.GemsRealmEntrySet;
import net.xelbayria.gems_realm.api.GemsRealmModule;
import net.xelbayria.gems_realm.api.set.gem.GemType;
import net.xelbayria.gems_realm.api.set.gem.VanillaGemTypes;

//SUPPORT: v3.3+
public class LapidaristModuleG extends GemsRealmModule {

    public final SimpleEntrySet<GemType, Block> bricks;
    public final SimpleEntrySet<GemType, Block> brick_stairs;
    public final SimpleEntrySet<GemType, Block> brick_slab;
    public final SimpleEntrySet<GemType, Block> cut;
    public final SimpleEntrySet<GemType, Block> cut_stairs;
    public final SimpleEntrySet<GemType, Block> cut_slab;
    public final SimpleEntrySet<GemType, Block> tiles;
    public final SimpleEntrySet<GemType, Block> tile_stairs;
    public final SimpleEntrySet<GemType, Block> tile_slab;
    public final SimpleEntrySet<GemType, Block> chiseled;
    public final SimpleEntrySet<GemType, Block> pillar;

    public LapidaristModuleG(String modId) {
        super(modId, "lpd");
        ResourceLocation tab = modRes("lapidary_tab");

        bricks = GemsRealmEntrySet.of(GemType.class, "bricks",
                        getModBlock("diamond_bricks"), () -> VanillaGemTypes.DIAMOND,
                        gemType -> new Block(copyPropertiesSafe(gemType))
                )
                .addTexture(modRes("block/diamond_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .addTag(BlockTags.BEACON_BASE_BLOCKS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("diamond_bricks_stonecutting"))
                .build();
        this.addEntry(bricks);

        brick_stairs = GemsRealmEntrySet.of(GemType.class, "brick_stairs",
                        getModBlock("diamond_brick_stairs"), () -> VanillaGemTypes.DIAMOND,
                        gemType -> new StairBlock(bricks.blocks.get(gemType).defaultBlockState(),
                                copyPropertiesSafe(gemType)
                        )
                )
                .requiresFromMap(bricks.blocks) //REASON: recipes, textures
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("diamond_brick_stairs_from_diamond_block"))
                .addRecipe(modRes("diamond_brick_stairs_from_diamond_bricks"))
                .build();
        this.addEntry(brick_stairs);

        brick_slab = GemsRealmEntrySet.of(GemType.class, "brick_slab",
                        getModBlock("diamond_brick_slab"), () -> VanillaGemTypes.DIAMOND,
                        gemType -> new SlabBlock(copyPropertiesSafe(gemType))
                )
                .requiresFromMap(bricks.blocks) //REASON: recipes, textures
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("diamond_brick_slab_from_diamond_block"))
                .addRecipe(modRes("diamond_brick_slab_from_diamond_bricks"))
                .build();
        this.addEntry(brick_slab);

        cut = GemsRealmEntrySet.of(GemType.class, "", "cut",
                        getModBlock("cut_diamond"), () -> VanillaGemTypes.DIAMOND,
                        gemType -> new Block(copyPropertiesSafe(gemType))
                )
                .requiresFromMap(bricks.blocks) //REASON: recipesrr
                .addTexture(modRes("block/cut_diamond"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .addTag(BlockTags.BEACON_BASE_BLOCKS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("cut_diamond_stonecutting"))
                .build();
        this.addEntry(cut);

        cut_stairs = GemsRealmEntrySet.of(GemType.class, "stairs", "cut",
                        getModBlock("cut_diamond_stairs"), () -> VanillaGemTypes.DIAMOND,
                        gemType -> new StairBlock(cut.blocks.get(gemType).defaultBlockState(),
                                copyPropertiesSafe(gemType)
                        )
                )
                .requiresFromMap(cut.blocks) //REASON: recipes, textures
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("cut_diamond_stairs_from_diamond_block"))
                .addRecipe(modRes("cut_diamond_stairs_from_cut_diamond"))
                .build();
        this.addEntry(cut_stairs);

        cut_slab = GemsRealmEntrySet.of(GemType.class, "slab", "cut",
                        getModBlock("cut_diamond_slab"), () -> VanillaGemTypes.DIAMOND,
                        gemType -> new SlabBlock(copyPropertiesSafe(gemType))
                )
                .requiresFromMap(cut.blocks) //REASON: recipes, textures
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("cut_diamond_slab_from_diamond_block"))
                .addRecipe(modRes("cut_diamond_slab_from_cut_diamond"))
                .build();
        this.addEntry(cut_slab);

        tiles = GemsRealmEntrySet.of(GemType.class, "tiles",
                        getModBlock("diamond_tiles"), () -> VanillaGemTypes.DIAMOND,
                        gemType -> new Block(copyPropertiesSafe(gemType))
                )
                .requiresFromMap(cut.blocks) //REASON: recipes
                .addTexture(modRes("block/diamond_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .addTag(BlockTags.BEACON_BASE_BLOCKS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("diamond_tiles_stonecutting"))
                .build();
        this.addEntry(tiles);

        tile_stairs = GemsRealmEntrySet.of(GemType.class, "tile_stairs",
                        getModBlock("diamond_tile_stairs"), () -> VanillaGemTypes.DIAMOND,
                        gemType -> new StairBlock(tiles.blocks.get(gemType).defaultBlockState(),
                                copyPropertiesSafe(gemType)
                        )
                )
                .requiresFromMap(tiles.blocks) //REASON: recipes, textures
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("diamond_tile_stairs_from_diamond_block"))
                .addRecipe(modRes("diamond_tile_stairs_from_diamond_tiles"))
                .build();
        this.addEntry(tile_stairs);

        tile_slab = GemsRealmEntrySet.of(GemType.class, "tile_slab",
                        getModBlock("diamond_tile_slab"), () -> VanillaGemTypes.DIAMOND,
                        gemType -> new SlabBlock(copyPropertiesSafe(gemType))
                )
                .requiresFromMap(tiles.blocks) //REASON: recipes, textures
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("diamond_tile_slab_from_diamond_block"))
                .addRecipe(modRes("diamond_tile_slab_from_diamond_tiles"))
                .build();
        this.addEntry(tile_slab);

        chiseled = GemsRealmEntrySet.of(GemType.class, "", "chiseled",
                        getModBlock("chiseled_diamond"), () -> VanillaGemTypes.DIAMOND,
                        gemType -> new Block(copyPropertiesSafe(gemType))
                )
                .requiresFromMap(brick_slab.blocks) //REASON: recipes
                .addTexture(modRes("block/chiseled_diamond"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .addTag(BlockTags.BEACON_BASE_BLOCKS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("chiseled_diamond_stonecutting"))
                .build();
        this.addEntry(chiseled);

        pillar = GemsRealmEntrySet.of(GemType.class, "pillar",
                        getModBlock("diamond_pillar"), () -> VanillaGemTypes.DIAMOND,
                        gemType -> new RotatedPillarBlock(copyPropertiesSafe(gemType))
                )
                .addTexture(modRes("block/diamond_pillar"))
                .addTexture(modRes("block/diamond_pillar_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .addTag(BlockTags.BEACON_BASE_BLOCKS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("diamond_pillar_stonecutting"))
                .build();
        this.addEntry(pillar);

    }

    public BlockBehaviour.Properties copyPropertiesSafe(GemType gemType) {
        return Utils.copyPropertySafe(gemType.block)
                .sound(SoundType.METAL)
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops();
    }
}