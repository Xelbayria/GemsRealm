package net.xelbayria.gems_realm.modules.neoforge.lapidarist;

import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.xelbayria.gems_realm.api.set.gem.GemType;
import net.xelbayria.gems_realm.api.set.gem.VanillaGemTypes;
import net.xelbayria.gems_realm.common_classes.BlockSetEntries;

//See LapidaristModuleAbstract's SUPPORTED VERSION
public class LapidaristModuleG extends LapidaristModuleAbstract {

    public final BlockSetEntries<GemType> bricksSet,
            cutSet,
            tilesSet,
            chiseledBlock,
            pillarBlock;

    public LapidaristModuleG(String modId) {
        super(modId);

        bricksSet = BlockSetEntries.of3DefaultProperty(
                        this, GemType.class, VanillaGemTypes.DIAMOND,
                        "diamond_bricks", this::copyPropertiesSafe,
                        "diamond_brick_slab", this::copyPropertiesSafe,
                        "diamond_brick_stairs", this::copyPropertiesSafe,
                        tabSupplier
                )
                .block(builder -> builder
                        .addTag(BlockTags.BEACON_BASE_BLOCKS, Registries.BLOCK)
                        .defaultRecipe()
                        .addRecipe(modRes("diamond_bricks_stonecutting"))
                ).defaultBlockTexture()
                .slab(builder -> builder
                        //TEXTURES: bricks
                        .addTag(BlockTags.STAIRS, Registries.BLOCK)
                        .addRecipe(modRes("diamond_brick_slab_from_diamond_block"))
                        .addRecipe(modRes("diamond_brick_slab_from_diamond_bricks"))
                )
                .stairs(builder -> builder
                        //TEXTURES: bricks
                        .addTag(BlockTags.STAIRS, Registries.BLOCK)
                        .addRecipe(modRes("diamond_brick_stairs_from_diamond_block"))
                        .addRecipe(modRes("diamond_brick_stairs_from_diamond_bricks"))
                )
                .pickaxeTag()
                .defaultRecipe()
                .build();
        this.addEntries(bricksSet);

        cutSet = BlockSetEntries.of3DefaultProperty(
                        this, GemType.class, VanillaGemTypes.DIAMOND,
                        "cut_diamond", this::copyPropertiesSafe,
                        "cut_diamond_slab", this::copyPropertiesSafe,
                        "cut_diamond_stairs", this::copyPropertiesSafe,
                        tabSupplier
                )
                .block(builder -> builder
                        .requiresFromMap(bricksSet.block.blocks) //REASON: recipes
                        .addTag(BlockTags.BEACON_BASE_BLOCKS, Registries.BLOCK)
                        .defaultRecipe()
                        .addRecipe(modRes("cut_diamond_from_diamond_block"))
                        .addRecipe(modRes("cut_diamond_from_diamond_bricks"))
                ).defaultBlockTexture()
                .slab(builder -> builder
                        //TEXTURES: cut
                        .addTag(BlockTags.STAIRS, Registries.BLOCK)
                        .addRecipe(modRes("cut_diamond_slab_from_diamond_block"))
                        .addRecipe(modRes("cut_diamond_slab_from_cut_diamond"))
                )
                .stairs(builder -> builder
                        //TEXTURES: cut
                        .addTag(BlockTags.STAIRS, Registries.BLOCK)
                        .addRecipe(modRes("cut_diamond_stairs_from_diamond_block"))
                        .addRecipe(modRes("cut_diamond_stairs_from_cut_diamond"))
                )
                .pickaxeTag()
                .defaultRecipe()
                .build();
        this.addEntries(cutSet);

        tilesSet = BlockSetEntries.of3DefaultProperty(
                        this, GemType.class, VanillaGemTypes.DIAMOND,
                        "diamond_tiles", this::copyPropertiesSafe,
                        "diamond_tile_slab", this::copyPropertiesSafe,
                        "diamond_tile_stairs", this::copyPropertiesSafe,
                        tabSupplier
                )
                .block(builder -> builder
                        .requiresFromMap(cutSet.block.blocks) //REASON: recipes
                        .addTag(BlockTags.BEACON_BASE_BLOCKS, Registries.BLOCK)
                        .defaultRecipe()
                        .addRecipe(modRes("diamond_tiles_from_diamond_block"))
                        .addRecipe(modRes("diamond_tiles_from_diamond_bricks"))
                        .addRecipe(modRes("diamond_tiles_from_cut_diamond"))
                ).defaultBlockTexture()
                .slab(builder -> builder
                        //TEXTURES: tiles
                        .addTag(BlockTags.STAIRS, Registries.BLOCK)
                        .addRecipe(modRes("diamond_tile_slab_from_diamond_block"))
                        .addRecipe(modRes("diamond_tile_slab_from_diamond_tiles"))
                )
                .stairs(builder -> builder
                        //TEXTURES: tiles
                        .addTag(BlockTags.STAIRS, Registries.BLOCK)
                        .defaultRecipe()
                        .addRecipe(modRes("diamond_tile_stairs_from_diamond_block"))
                        .addRecipe(modRes("diamond_tile_stairs_from_diamond_tiles"))
                )
                .pickaxeTag()
                .defaultRecipe()
                .build();
        this.addEntries(tilesSet);

        chiseledBlock = BlockSetEntries.ofBlockProperty(
                        this, GemType.class, VanillaGemTypes.DIAMOND,
                        "chiseled_diamond", this::copyPropertiesSafe,
                        tabSupplier
                )
                .block(builder -> builder
                        .requiresFromMap(bricksSet.slabOrExtra != null ? bricksSet.slabOrExtra.blocks : null) //REASON: recipes
                        .addTag(BlockTags.BEACON_BASE_BLOCKS, Registries.BLOCK)
                        .defaultRecipe()
                        .addRecipe(modRes("chiseled_diamond_from_diamond_block"))
                        .addRecipe(modRes("chiseled_diamond_from_diamond_bricks"))
                )
                .defaultBlockTexture()
                .IronToolTag()
                .build();
        this.addEntry(chiseledBlock.block);

        pillarBlock = BlockSetEntries.ofBlockFactory(
                        this, GemType.class, VanillaGemTypes.DIAMOND,
                        "diamond_pillar",
                        type -> new RotatedPillarBlock(copyPropertiesSafe(type)),
                        tabSupplier
                )
                .block(builder -> builder
                        .addTexture(modRes("block/diamond_pillar_top"))
                        .addTag(BlockTags.BEACON_BASE_BLOCKS, Registries.BLOCK)
                        .defaultRecipe()
                        .addRecipe(modRes("diamond_pillar_stonecutting"))
                )
                .defaultBlockTexture()
                .IronToolTag()
                .build();
        this.addEntry(pillarBlock.block);

    }

    public BlockBehaviour.Properties copyPropertiesSafe(GemType gemType) {
        return Utils.copyPropertySafe(gemType.block)
                .sound(SoundType.METAL)
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops();
    }
}