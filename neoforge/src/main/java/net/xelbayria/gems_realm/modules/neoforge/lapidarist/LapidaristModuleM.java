package net.xelbayria.gems_realm.modules.neoforge.lapidarist;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.xelbayria.gems_realm.api.GemsRealmEntrySet;
import net.xelbayria.gems_realm.api.GemsRealmModule;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.api.set.metal.VanillaMetalTypes;

//SUPPORT: v3.3+
public class LapidaristModuleM extends GemsRealmModule {

    public final SimpleEntrySet<MetalType, Block> bricks;
    public final SimpleEntrySet<MetalType, Block> brick_stairs;
    public final SimpleEntrySet<MetalType, Block> brick_slab;
    public final SimpleEntrySet<MetalType, Block> cut;
    public final SimpleEntrySet<MetalType, Block> cut_stairs;
    public final SimpleEntrySet<MetalType, Block> cut_slab;
    public final SimpleEntrySet<MetalType, Block> tiles;
    public final SimpleEntrySet<MetalType, Block> tile_stairs;
    public final SimpleEntrySet<MetalType, Block> tile_slab;
    public final SimpleEntrySet<MetalType, Block> chiseled;
    public final SimpleEntrySet<MetalType, Block> pillar;

    public LapidaristModuleM(String modId) {
        super(modId, "lpd");
        ResourceLocation tab = modRes("lapidary_tab");

        bricks = GemsRealmEntrySet.of(MetalType.class, "bricks",
                        getModBlock("gold_bricks"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new Block(copyPropertiesSafe(metalType))
                )
                .addTexture(modRes("block/gold_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .addTag(BlockTags.BEACON_BASE_BLOCKS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("gold_bricks_stonecutting"))
                .build();
        this.addEntry(bricks);

        brick_stairs = GemsRealmEntrySet.of(MetalType.class, "brick_stairs",
                        getModBlock("gold_brick_stairs"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new StairBlock(bricks.blocks.get(metalType).defaultBlockState(),
                                copyPropertiesSafe(metalType)
                        )
                )
                .requiresFromMap(bricks.blocks) //REASON: recipes, textures
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("gold_brick_stairs_from_gold_block"))
                .addRecipe(modRes("gold_brick_stairs_from_gold_bricks"))
                .build();
        this.addEntry(brick_stairs);

        brick_slab = GemsRealmEntrySet.of(MetalType.class, "brick_slab",
                        getModBlock("gold_brick_slab"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new SlabBlock(copyPropertiesSafe(metalType))
                )
                .requiresFromMap(bricks.blocks) //REASON: recipes, textures
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("gold_brick_slab_from_gold_block"))
                .addRecipe(modRes("gold_brick_slab_from_gold_bricks"))
                .build();
        this.addEntry(brick_slab);

        cut = GemsRealmEntrySet.of(MetalType.class, "", "cut",
                        getModBlock("cut_gold"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new Block(copyPropertiesSafe(metalType))
                )
                .requiresFromMap(bricks.blocks) //REASON: recipesrr
                .addTexture(modRes("block/cut_gold"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .addTag(BlockTags.BEACON_BASE_BLOCKS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("cut_gold_stonecutting"))
                .build();
        this.addEntry(cut);

        cut_stairs = GemsRealmEntrySet.of(MetalType.class, "stairs", "cut",
                        getModBlock("cut_gold_stairs"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new StairBlock(cut.blocks.get(metalType).defaultBlockState(),
                                copyPropertiesSafe(metalType)
                        )
                )
                .requiresFromMap(cut.blocks) //REASON: recipes, textures
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("cut_gold_stairs_from_gold_block"))
                .addRecipe(modRes("cut_gold_stairs_from_cut_gold"))
                .build();
        this.addEntry(cut_stairs);

        cut_slab = GemsRealmEntrySet.of(MetalType.class, "slab", "cut",
                        getModBlock("cut_gold_slab"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new SlabBlock(copyPropertiesSafe(metalType))
                )
                .requiresFromMap(cut.blocks) //REASON: recipes, textures
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("cut_gold_slab_from_gold_block"))
                .addRecipe(modRes("cut_gold_slab_from_cut_gold"))
                .build();
        this.addEntry(cut_slab);

        tiles = GemsRealmEntrySet.of(MetalType.class, "tiles",
                        getModBlock("gold_tiles"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new Block(copyPropertiesSafe(metalType))
                )
                .requiresFromMap(cut.blocks) //REASON: recipes
                .addTexture(modRes("block/gold_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .addTag(BlockTags.BEACON_BASE_BLOCKS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("gold_tiles_stonecutting"))
                .build();
        this.addEntry(tiles);

        tile_stairs = GemsRealmEntrySet.of(MetalType.class, "tile_stairs",
                        getModBlock("gold_tile_stairs"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new StairBlock(tiles.blocks.get(metalType).defaultBlockState(),
                                copyPropertiesSafe(metalType)
                        )
                )
                .requiresFromMap(tiles.blocks) //REASON: recipes, textures
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("gold_tile_stairs_from_gold_block"))
                .addRecipe(modRes("gold_tile_stairs_from_gold_tiles"))
                .build();
        this.addEntry(tile_stairs);

        tile_slab = GemsRealmEntrySet.of(MetalType.class, "tile_slab",
                        getModBlock("gold_tile_slab"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new SlabBlock(copyPropertiesSafe(metalType))
                )
                .requiresFromMap(tiles.blocks) //REASON: recipes, textures
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("gold_tile_slab_from_gold_block"))
                .addRecipe(modRes("gold_tile_slab_from_gold_tiles"))
                .build();
        this.addEntry(tile_slab);

        chiseled = GemsRealmEntrySet.of(MetalType.class, "", "chiseled",
                        getModBlock("chiseled_gold"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new Block(copyPropertiesSafe(metalType))
                )
                .requiresFromMap(brick_slab.blocks) //REASON: recipes
                .addTexture(modRes("block/chiseled_gold"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .addTag(BlockTags.BEACON_BASE_BLOCKS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("chiseled_gold_stonecutting"))
                .build();
        this.addEntry(chiseled);

        pillar = GemsRealmEntrySet.of(MetalType.class, "pillar",
                        getModBlock("gold_pillar"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new RotatedPillarBlock(copyPropertiesSafe(metalType))
                )
                .addTexture(modRes("block/gold_pillar"))
                .addTexture(modRes("block/gold_pillar_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .addTag(BlockTags.BEACON_BASE_BLOCKS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("gold_pillar_stonecutting"))
                .build();
        this.addEntry(pillar);

    }

    public BlockBehaviour.Properties copyPropertiesSafe(MetalType MetalType) {
        return Utils.copyPropertySafe(MetalType.block)
                .sound(SoundType.METAL)
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops();
    }
}