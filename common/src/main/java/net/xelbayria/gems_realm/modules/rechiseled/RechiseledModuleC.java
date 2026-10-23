package net.xelbayria.gems_realm.modules.rechiseled;

import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.xelbayria.gems_realm.api.set.crystal.CrystalType;
import net.xelbayria.gems_realm.api.set.crystal.CrystalTypeRegistry;
import net.xelbayria.gems_realm.api.set.crystal.VanillaCrystalTypes;
import net.xelbayria.gems_realm.common_classes.BlockSetEntries;

import java.util.function.Consumer;

//See RechiseledModuleAbstract's SUPPORTED VERSION
public class RechiseledModuleC extends RechiseledModuleAbstract {

    public final BlockSetEntries<CrystalType> block_beamsSet,
            block_bordered_diagonal_tilesSet,
            block_bricksSet,
            block_cutSet,
            block_edgedSet,
            block_pillarSet,
            block_polishedSet,
            block_shinySet,
            block_tilesSet,
            block_diagonal_tilesSet,
            block_jewelSet,
            block_smoothSet;

    public RechiseledModuleC(String modId) {
        super(modId);

        block_beamsSet = RechiseledSetEntries.of2BlockDefault(
                this, CrystalType.class, VanillaCrystalTypes.AMETHYST,
                "amethyst_block_beams",
                "amethyst_block_beams_connecting",
                        TAB
                )
                .defaultBlockTexture()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_beamsSet);

        block_bordered_diagonal_tilesSet = RechiseledSetEntries.of2BlockDefault(
                this, CrystalType.class, VanillaCrystalTypes.AMETHYST,
                "amethyst_block_bordered_diagonal_tiles",
                "amethyst_block_bordered_diagonal_tiles_connecting",
                        TAB
                )
                .defaultBlockTexture()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_bordered_diagonal_tilesSet);

        block_bricksSet = RechiseledSetEntries.of2BlockDefault(
                this, CrystalType.class, VanillaCrystalTypes.AMETHYST,
                "amethyst_block_bricks",
                "amethyst_block_bricks_connecting",
                        TAB
                )
                .defaultBlockTexture()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_bricksSet);

        block_cutSet = RechiseledSetEntries.of2BlockDefault(
                this, CrystalType.class, VanillaCrystalTypes.AMETHYST,
                "amethyst_block_cut",
                "amethyst_block_cut_connecting",
                        TAB
                )
                .defaultBlockTexture()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_cutSet);

        block_edgedSet = RechiseledSetEntries.of2BlockDefault(
                this, CrystalType.class, VanillaCrystalTypes.AMETHYST,
                "amethyst_block_edged",
                "amethyst_block_edged_connecting",
                        TAB
                )
                .defaultBlockTexture()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_edgedSet);

        block_pillarSet = RechiseledSetEntries.of2PillarBlockDefault(
                this, CrystalType.class, VanillaCrystalTypes.AMETHYST,
                "amethyst_block_pillar",
                "amethyst_block_pillar_connecting",
                        TAB
                )
                .defaultBlockTexture("_side")
                .defaultBlockTextureTop("_end")
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_pillarSet);

        block_polishedSet = RechiseledSetEntries.of2BlockDefault(
                        this, CrystalType.class, VanillaCrystalTypes.AMETHYST,
                        "amethyst_block_polished",
                        "amethyst_block_polished_connecting",
                        TAB
                )
                .defaultBlockTexture()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_polishedSet);

        block_shinySet = RechiseledSetEntries.of2BlockDefault(
                        this, CrystalType.class, VanillaCrystalTypes.AMETHYST,
                        "amethyst_block_shiny",
                        "amethyst_block_shiny_connecting",
                        TAB
                )
                .defaultBlockTexture()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_shinySet);

        block_tilesSet = RechiseledSetEntries.of2BlockDefault(
                        this, CrystalType.class, VanillaCrystalTypes.AMETHYST,
                        "amethyst_block_tiles",
                        "amethyst_block_tiles_connecting",
                        TAB
                )
                .defaultBlockTexture()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_tilesSet);

        block_diagonal_tilesSet = RechiseledSetEntries.ofBlockDefault(
                        this, CrystalType.class, VanillaCrystalTypes.AMETHYST,
                        "amethyst_block_diagonal_tiles",
                        TAB
                )
                .defaultBlockTexture()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_diagonal_tilesSet);

        block_jewelSet = RechiseledSetEntries.ofBlockDefault(
                        this, CrystalType.class, VanillaCrystalTypes.AMETHYST,
                        "amethyst_block_jewel",
                        TAB
                )
                .defaultBlockTexture()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_jewelSet);

        block_smoothSet = RechiseledSetEntries.ofBlockDefault(
                        this, CrystalType.class, VanillaCrystalTypes.AMETHYST,
                        "amethyst_block_smooth",
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
            createChiselingRecipe(this, CrystalTypeRegistry.INSTANCE, sink, "block_(smooth|jewel|diagonal_tiles)")
        );
    }
}