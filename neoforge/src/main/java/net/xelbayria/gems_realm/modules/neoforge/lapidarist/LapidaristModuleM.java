package net.xelbayria.gems_realm.modules.neoforge.lapidarist;

import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.api.set.metal.VanillaMetalTypes;
import net.xelbayria.gems_realm.common_classes.BlockSetEntries;

//See LapidaristModuleAbstract's SUPPORTED VERSION
public class LapidaristModuleM extends LapidaristModuleAbstract {

    public final BlockSetEntries<MetalType> bricksSet,
            cutSet,
            tilesSet,
            chiseled,
            pillar;

    public LapidaristModuleM(String modId) {
        super(modId);

        bricksSet = BlockSetEntries.of3DefaultProperty(
                this, MetalType.class, VanillaMetalTypes.GOLD,
                "gold_bricks", this::copyPropertiesSafe,
                "gold_brick_slab", this::copyPropertiesSafe,
                "gold_brick_stairs", this::copyPropertiesSafe,
                        tabSupplier
                )
                .block(builder -> builder
                        .addTag(BlockTags.BEACON_BASE_BLOCKS, Registries.BLOCK)
                        .addRecipe(modRes("gold_bricks_stonecutting"))
                )
                .slab(builder -> builder
                        //TEXTURES: bricks
                        .addTag(BlockTags.SLABS, Registries.BLOCK)
                        .addRecipe(modRes("gold_brick_slab_from_gold_block"))
                        .addRecipe(modRes("gold_brick_slab_from_gold_bricks"))
                )
                .stairs(builder-> builder
                        //TEXTURES: bricks
                        .addTag(BlockTags.STAIRS, Registries.BLOCK)
                        .addRecipe(modRes("gold_brick_stairs_from_gold_block"))
                        .addRecipe(modRes("gold_brick_stairs_from_gold_bricks"))
                )
                .defaultBlockTexture()
                .pickaxeTag()
                .IronToolTag()
                .defaultRecipe()
                .build();
        this.addEntries(bricksSet);

        cutSet = BlockSetEntries.of3DefaultProperty(
                this, MetalType.class, VanillaMetalTypes.GOLD,
                "cut_gold", this::copyPropertiesSafe,
                "cut_gold_slab", this::copyPropertiesSafe,
                "cut_gold_stairs", this::copyPropertiesSafe,
                        tabSupplier
                )
                .block(builder -> builder
                        .requiresFromMap(bricksSet.block.blocks) //REASON: recipes
                        .addTag(BlockTags.BEACON_BASE_BLOCKS, Registries.BLOCK)
                        .addRecipe(modRes("gold_bricks_stonecutting"))
                )
                .slab(builder -> builder
                        //TEXTURES: bricks
                        .addTag(BlockTags.SLABS, Registries.BLOCK)
                        .addRecipe(modRes("cut_gold_slab_from_gold_block"))
                        .addRecipe(modRes("cut_gold_slab_from_cut_gold"))
                )
                .stairs(builder-> builder
                        //TEXTURES: bricks
                        .addTag(BlockTags.STAIRS, Registries.BLOCK)
                        .addRecipe(modRes("cut_gold_stairs_from_gold_block"))
                        .addRecipe(modRes("cut_gold_stairs_from_cut_gold"))
                )
                .defaultBlockTexture()
                .pickaxeTag()
                .IronToolTag()
                .defaultRecipe()
                .build();
        this.addEntries(cutSet);

        tilesSet = BlockSetEntries.of3DefaultProperty(
                this, MetalType.class, VanillaMetalTypes.GOLD,
                "gold_tiles", this::copyPropertiesSafe,
                "gold_tile_slab", this::copyPropertiesSafe,
                "gold_tile_stairs", this::copyPropertiesSafe,
                        tabSupplier
                )
                .block(builder -> builder
                        .requiresFromMap(cutSet.block.blocks) //REASON: recipes
                        .addTag(BlockTags.BEACON_BASE_BLOCKS, Registries.BLOCK)
                        .addRecipe(modRes("gold_bricks_stonecutting"))
                )
                .slab(builder -> builder
                        //TEXTURES: bricks
                        .addTag(BlockTags.SLABS, Registries.BLOCK)
                        .addRecipe(modRes("gold_tile_slab_from_gold_block"))
                        .addRecipe(modRes("gold_tile_slab_from_gold_tiles"))
                )
                .stairs(builder-> builder
                        //TEXTURES: bricks
                        .addTag(BlockTags.STAIRS, Registries.BLOCK)
                        .addRecipe(modRes("gold_tile_stairs_from_gold_block"))
                        .addRecipe(modRes("gold_tile_stairs_from_gold_tiles"))
                )
                .defaultBlockTexture()
                .pickaxeTag()
                .IronToolTag()
                .defaultRecipe()
                .build();
        this.addEntries(tilesSet);

        chiseled = BlockSetEntries.ofBlockProperty(
                this, MetalType.class, VanillaMetalTypes.GOLD,
                "chiseled_gold", this::copyPropertiesSafe,
                        tabSupplier
                )
                .block(builder -> builder
                        .requiresFromMap(tilesSet.block.blocks) //REASON: recipes
                        .addTag(BlockTags.BEACON_BASE_BLOCKS, Registries.BLOCK)
                        .addRecipe(modRes("chiseled_gold_from_gold_block"))
                        .addRecipe(modRes("chiseled_gold_from_gold_bricks"))
                )
                .defaultBlockTexture()
                .pickaxeTag()
                .IronToolTag()
                .defaultRecipe()
                .build();
        this.addEntries(chiseled);

        pillar = BlockSetEntries.ofBlockFactory(
                this, MetalType.class, VanillaMetalTypes.GOLD,
                "gold_pillar", metalType -> new RotatedPillarBlock(copyPropertiesSafe(metalType)),
                        tabSupplier
                )
                .block(builder -> builder
                        .requiresFromMap(tilesSet.block.blocks) //REASON: recipes
                        .addTag(BlockTags.BEACON_BASE_BLOCKS, Registries.BLOCK)
                        .addRecipe(modRes("gold_pillar_stonecutting"))
                )
                .defaultBlockTexture()
                .defaultBlockTextureTop()
                .pickaxeTag()
                .IronToolTag()
                .defaultRecipe()
                .build();
        this.addEntries(pillar);

    }

    public BlockBehaviour.Properties copyPropertiesSafe(MetalType MetalType) {
        return Utils.copyPropertySafe(MetalType.block)
                .sound(SoundType.METAL)
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops();
    }
}