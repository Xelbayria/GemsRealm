package net.xelbayria.gems_realm.modules.rechiseled;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.xelbayria.gems_realm.api.GemsRealmEntrySet;
import net.xelbayria.gems_realm.api.set.dust.DustType;
import net.xelbayria.gems_realm.api.set.dust.DustTypeRegistry;
import net.xelbayria.gems_realm.api.set.dust.VanillaDustTypes;

import java.util.function.Consumer;

//See RechiseledModuleAbstract's SUPPORTED VERSION
public class RechiseledModuleD extends RechiseledModuleAbstract {

    public final SimpleEntrySet<DustType, Block> block_bordered, block_bordered_connecting;
    public final SimpleEntrySet<DustType, Block> block_bricks, block_bricks_connecting;
    public final SimpleEntrySet<DustType, Block> block_chiseled_clovers, block_chiseled_clovers_connecting;
    public final SimpleEntrySet<DustType, Block> block_circles, block_circles_connecting;
    public final SimpleEntrySet<DustType, Block> block_compressed, block_compressed_connecting;
    public final SimpleEntrySet<DustType, Block> block_diagonal_tiles, block_diagonal_tiles_connecting;
    public final SimpleEntrySet<DustType, Block> block_patterned, block_patterned_connecting;
    public final SimpleEntrySet<DustType, Block> block_paving, block_paving_connecting;
    public final SimpleEntrySet<DustType, Block> block_pillar, block_pillar_connecting;
    public final SimpleEntrySet<DustType, Block> block_polished, block_polished_connecting;
    public final SimpleEntrySet<DustType, Block> block_scales, block_scales_connecting;
    public final SimpleEntrySet<DustType, Block> block_small_tiles, block_small_tiles_connecting;
    public final SimpleEntrySet<DustType, Block> block_brick_bordered;
    public final SimpleEntrySet<DustType, Block> block_crushed;
    public final SimpleEntrySet<DustType, Block> block_smooth;

    public RechiseledModuleD(String modId) {
        super(modId);

        block_bordered = GemsRealmEntrySet.of(DustType.class, "block_bordered",
                        getModBlock("redstone_block_bordered"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(dustType.block))
                )
                .addTexture(modRes("block/redstone_block_bordered"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_bordered);

        block_bordered_connecting = GemsRealmEntrySet.of(DustType.class, "block_bordered_connecting",
                        getModBlock("redstone_block_bordered_connecting"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(dustType.block))
                )
                //TEXTURES: redstone_block_bordered
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_bordered_connecting);

        block_bricks = GemsRealmEntrySet.of(DustType.class, "block_bricks",
                        getModBlock("redstone_block_bricks"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(dustType.block))
                )
                .addTexture(modRes("block/redstone_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_bricks);

        block_bricks_connecting = GemsRealmEntrySet.of(DustType.class, "block_bricks_connecting",
                        getModBlock("redstone_block_bricks_connecting"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(dustType.block))
                )
                //TEXTURES: redstone_block_bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_bricks_connecting);

        block_chiseled_clovers = GemsRealmEntrySet.of(DustType.class, "block_chiseled_clovers",
                        getModBlock("redstone_block_chiseled_clovers"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(dustType.block))
                )
                .addTexture(modRes("block/redstone_block_chiseled_clovers"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_chiseled_clovers);

        block_chiseled_clovers_connecting = GemsRealmEntrySet.of(DustType.class, "block_chiseled_clovers_connecting",
                        getModBlock("redstone_block_chiseled_clovers_connecting"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(dustType.block))
                )
                //TEXTURES: redstone_block_chiseled_clovers
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_chiseled_clovers_connecting);

        block_circles = GemsRealmEntrySet.of(DustType.class, "block_circles",
                        getModBlock("redstone_block_circles"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(dustType.block))
                )
                .addTexture(modRes("block/redstone_block_circles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_circles);

        block_circles_connecting = GemsRealmEntrySet.of(DustType.class, "block_circles_connecting",
                        getModBlock("redstone_block_circles_connecting"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(dustType.block))
                )
                //TEXTURES: redstone_block_circles
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_circles_connecting);

        block_compressed = GemsRealmEntrySet.of(DustType.class, "block_compressed",
                        getModBlock("redstone_block_compressed"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(dustType.block))
                )
                .addTexture(modRes("block/redstone_block_compressed"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_compressed);

        block_compressed_connecting = GemsRealmEntrySet.of(DustType.class, "block_compressed_connecting",
                        getModBlock("redstone_block_compressed_connecting"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(dustType.block))
                )
                //TEXTURES: redstone_block_compressed
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_compressed_connecting);

        block_diagonal_tiles = GemsRealmEntrySet.of(DustType.class, "block_diagonal_tiles",
                        getModBlock("redstone_block_diagonal_tiles"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(dustType.block))
                )
                .addTexture(modRes("block/redstone_block_diagonal_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_diagonal_tiles);

        block_diagonal_tiles_connecting = GemsRealmEntrySet.of(DustType.class, "block_diagonal_tiles_connecting",
                        getModBlock("redstone_block_diagonal_tiles_connecting"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(dustType.block))
                )
                //TEXTURES: redstone_block_diagonal_tiles
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_diagonal_tiles_connecting);

        block_patterned = GemsRealmEntrySet.of(DustType.class, "block_patterned",
                        getModBlock("redstone_block_patterned"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(dustType.block))
                )
                .addTexture(modRes("block/redstone_block_patterned"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_patterned);

        block_patterned_connecting = GemsRealmEntrySet.of(DustType.class, "block_patterned_connecting",
                        getModBlock("redstone_block_patterned_connecting"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(dustType.block))
                )
                //TEXTURES: redstone_block_patterned
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_patterned_connecting);

        block_paving = GemsRealmEntrySet.of(DustType.class, "block_paving",
                        getModBlock("redstone_block_paving"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(dustType.block))
                )
                .addTexture(modRes("block/redstone_block_paving"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_paving);

        block_paving_connecting = GemsRealmEntrySet.of(DustType.class, "block_paving_connecting",
                        getModBlock("redstone_block_paving_connecting"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(dustType.block))
                )
                //TEXTURES: redstone_block_paving
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_paving_connecting);

        block_pillar = GemsRealmEntrySet.of(DustType.class, "block_pillar",
                        getModBlock("redstone_block_pillar"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new CompatRechiseledPillarBlock(false, Utils.copyPropertySafe(dustType.block))
                )
                .addTexture(modRes("block/redstone_block_pillar_side"))
                .addTexture(modRes("block/redstone_block_pillar_end"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_pillar);

        block_pillar_connecting = GemsRealmEntrySet.of(DustType.class, "block_pillar_connecting",
                        getModBlock("redstone_block_pillar_connecting"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new CompatRechiseledPillarBlock(true, Utils.copyPropertySafe(dustType.block))
                )
                //TEXTURES: redstone_block_pillar
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_pillar_connecting);

        block_polished = GemsRealmEntrySet.of(DustType.class, "block_polished",
                        getModBlock("redstone_block_polished"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(dustType.block))
                )
                .addTexture(modRes("block/redstone_block_polished"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_polished);

        block_polished_connecting = GemsRealmEntrySet.of(DustType.class, "block_polished_connecting",
                        getModBlock("redstone_block_polished_connecting"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(dustType.block))
                )
                //TEXTURES: redstone_block_polished
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_polished_connecting);

        block_scales = GemsRealmEntrySet.of(DustType.class, "block_scales",
                        getModBlock("redstone_block_scales"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(dustType.block))
                )
                .addTexture(modRes("block/redstone_block_scales"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_scales);

        block_scales_connecting = GemsRealmEntrySet.of(DustType.class, "block_scales_connecting",
                        getModBlock("redstone_block_scales_connecting"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(dustType.block))
                )
                //TEXTURES: redstone_block_scales
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_scales_connecting);

        block_small_tiles = GemsRealmEntrySet.of(DustType.class, "block_small_tiles",
                        getModBlock("redstone_block_small_tiles"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(dustType.block))
                )
                .addTexture(modRes("block/redstone_block_small_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_small_tiles);

        block_small_tiles_connecting = GemsRealmEntrySet.of(DustType.class, "block_small_tiles_connecting",
                        getModBlock("redstone_block_small_tiles_connecting"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(dustType.block))
                )
                //TEXTURES: redstone_block_small_tiles
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_small_tiles_connecting);

        block_brick_bordered = GemsRealmEntrySet.of(DustType.class, "block_brick_bordered",
                        getModBlock("redstone_block_brick_bordered"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(dustType.block))
                )
                .addTexture(modRes("block/redstone_block_brick_bordered"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_brick_bordered);

        block_crushed = GemsRealmEntrySet.of(DustType.class, "block_crushed",
                        getModBlock("redstone_block_crushed"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(dustType.block))
                )
                .addTexture(modRes("block/redstone_block_crushed"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_crushed);

        block_smooth = GemsRealmEntrySet.of(DustType.class, "block_smooth",
                        getModBlock("redstone_block_smooth"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(dustType.block))
                )
                .addTexture(modRes("block/redstone_block_smooth"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_smooth);

    }

    @Override
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);
        executor.accept((manager, sink) ->
                createChiselingRecipe(this, DustTypeRegistry.INSTANCE, sink, "block_(smooth|crushed|brick_bordered)")
        );
    }
}