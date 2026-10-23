package net.xelbayria.gems_realm.modules.rechiseled;

import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.xelbayria.gems_realm.api.set.dust.DustType;
import net.xelbayria.gems_realm.api.set.dust.DustTypeRegistry;
import net.xelbayria.gems_realm.api.set.dust.VanillaDustTypes;
import net.xelbayria.gems_realm.common_classes.BlockSetEntries;

import java.util.function.Consumer;

//See RechiseledModuleAbstract's SUPPORTED VERSION
public class RechiseledModuleD extends RechiseledModuleAbstract {

    public final BlockSetEntries<DustType> block_borderedSet,
            block_bricksSet,
            block_chiseled_cloversSet,
            block_circlesSet,
            block_compressedSet,
            block_diagonal_tilesSet,
            block_patternedSet,
            block_pavingSet,
            block_pillarSet,
            block_polishedSet,
            block_scalesSet,
            block_small_tilesSet,
            block_brick_borderedSet,
            block_crushedSet,
            block_smoothSet;

    public RechiseledModuleD(String modId) {
        super(modId);

        block_borderedSet = RechiseledSetEntries.of2BlockDefault(
                        this, DustType.class, VanillaDustTypes.REDSTONE,
                        "redstone_block_bordered",
                        "redstone_block_bordered_connecting",
                        TAB
                )
                .defaultBlockTexture()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_borderedSet);

        block_bricksSet = RechiseledSetEntries.of2BlockDefault(
                        this, DustType.class, VanillaDustTypes.REDSTONE,
                        "redstone_block_bricks",
                        "redstone_block_bricks_connecting",
                        TAB
                )
                .defaultBlockTexture()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_bricksSet);

        block_chiseled_cloversSet = RechiseledSetEntries.of2BlockDefault(
                        this, DustType.class, VanillaDustTypes.REDSTONE,
                        "redstone_block_chiseled_clovers",
                        "redstone_block_chiseled_clovers_connecting",
                        TAB
                )
                .defaultBlockTexture()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_chiseled_cloversSet);

        block_circlesSet = RechiseledSetEntries.of2BlockDefault(
                        this, DustType.class, VanillaDustTypes.REDSTONE,
                        "redstone_block_circles",
                        "redstone_block_circles_connecting",
                        TAB
                )
                .defaultBlockTexture()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_circlesSet);

        block_compressedSet = RechiseledSetEntries.of2BlockDefault(
                        this, DustType.class, VanillaDustTypes.REDSTONE,
                        "redstone_block_compressed",
                        "redstone_block_compressed_connecting",
                        TAB
                )
                .defaultBlockTexture()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_compressedSet);

        block_diagonal_tilesSet = RechiseledSetEntries.of2BlockDefault(
                        this, DustType.class, VanillaDustTypes.REDSTONE,
                        "redstone_block_diagonal_tiles",
                        "redstone_block_diagonal_tiles_connecting",
                        TAB
                )
                .defaultBlockTexture()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_diagonal_tilesSet);

        block_patternedSet = RechiseledSetEntries.of2BlockDefault(
                        this, DustType.class, VanillaDustTypes.REDSTONE,
                        "redstone_block_patterned",
                        "redstone_block_patterned_connecting",
                        TAB
                )
                .defaultBlockTexture()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_patternedSet);

        block_pavingSet = RechiseledSetEntries.of2BlockDefault(
                        this, DustType.class, VanillaDustTypes.REDSTONE,
                        "redstone_block_paving",
                        "redstone_block_paving_connecting",
                        TAB
                )
                .defaultBlockTexture()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_pavingSet);

        block_pillarSet = RechiseledSetEntries.of2PillarBlockDefault(
                        this, DustType.class, VanillaDustTypes.REDSTONE,
                        "redstone_block_pillar",
                        "redstone_block_pillar_connecting",
                        TAB
                )
                .defaultBlockTexture("_side")
                .defaultBlockTextureTop("_end")
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_pillarSet);

        block_polishedSet = RechiseledSetEntries.of2BlockDefault(
                        this, DustType.class, VanillaDustTypes.REDSTONE,
                        "redstone_block_polished",
                        "redstone_block_polished_connecting",
                        TAB
                )
                .defaultBlockTexture()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_polishedSet);

        block_scalesSet = RechiseledSetEntries.of2BlockDefault(
                        this, DustType.class, VanillaDustTypes.REDSTONE,
                        "redstone_block_scales",
                        "redstone_block_scales_connecting",
                        TAB
                )
                .defaultBlockTexture()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_scalesSet);

        block_small_tilesSet = RechiseledSetEntries.of2BlockDefault(
                        this, DustType.class, VanillaDustTypes.REDSTONE,
                        "redstone_block_small_tiles",
                        "redstone_block_small_tiles_connecting",
                        TAB
                )
                .defaultBlockTexture()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_small_tilesSet);

        block_brick_borderedSet = RechiseledSetEntries.ofBlockDefault(
                        this, DustType.class, VanillaDustTypes.REDSTONE,
                        "redstone_block_brick_bordered",
                        TAB
                )
                .defaultBlockTexture()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_brick_borderedSet);

        block_crushedSet = RechiseledSetEntries.ofBlockDefault(
                        this, DustType.class, VanillaDustTypes.REDSTONE,
                        "redstone_block_crushed",
                        TAB
                )
                .defaultBlockTexture()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_crushedSet);

        block_smoothSet = RechiseledSetEntries.ofBlockDefault(
                        this, DustType.class, VanillaDustTypes.REDSTONE,
                        "redstone_block_smooth",
                        TAB
                )
                .defaultBlockTexture()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_smoothSet);

    }

    @Override
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);
        executor.accept((manager, sink) ->
                createChiselingRecipe(this, DustTypeRegistry.INSTANCE, sink, "block_(smooth|crushed|brick_bordered)")
        );
    }
}