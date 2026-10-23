package net.xelbayria.gems_realm.modules.rechiseled;

import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.xelbayria.gems_realm.api.set.gem.GemType;
import net.xelbayria.gems_realm.api.set.gem.GemTypeRegistry;
import net.xelbayria.gems_realm.api.set.gem.VanillaGemTypes;
import net.xelbayria.gems_realm.common_classes.BlockSetEntries;

import java.util.function.Consumer;

//See RechiseledModuleAbstract's SUPPORTED VERSION
public class RechiseledModuleG extends RechiseledModuleAbstract {

    public final BlockSetEntries<GemType> block_bordered_crossesSet,
            block_bordered_platingSet,
            block_chiseledSet,
            block_cloversSet,
            block_crystalSet,
            block_patternedSet,
            block_patterned_squaresSet,
            block_pillarSet,
            block_polishedSet,
            block_stripedSet,
            block_waxedSet,
            block_crossesSet,
            block_platingSet,
            block_smoothSet;

    public RechiseledModuleG(String modId) {
        super(modId);

        block_bordered_crossesSet = RechiseledSetEntries.of2BlockDefault(
                        this, GemType.class, VanillaGemTypes.EMERALD,
                        "emerald_block_bordered_crosses",
                        "emerald_block_bordered_crosses_connecting",
                        TAB
                )
                .defaultBlockTexture()
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_bordered_crossesSet);

        block_bordered_platingSet = RechiseledSetEntries.of2BlockDefault(
                        this, GemType.class, VanillaGemTypes.EMERALD,
                        "emerald_block_bordered_plating",
                        "emerald_block_bordered_plating_connecting",
                        TAB
                )
                .defaultBlockTexture()
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_bordered_platingSet);

        block_chiseledSet = RechiseledSetEntries.of2BlockDefault(
                        this, GemType.class, VanillaGemTypes.EMERALD,
                        "emerald_block_chiseled",
                        "emerald_block_chiseled_connecting",
                        TAB
                )
                .defaultBlockTexture()
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_chiseledSet);

        block_cloversSet = RechiseledSetEntries.of2BlockDefault(
                        this, GemType.class, VanillaGemTypes.EMERALD,
                        "emerald_block_clovers",
                        "emerald_block_clovers_connecting",
                        TAB
                )
                .defaultBlockTexture()
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_cloversSet);

        block_crystalSet = RechiseledSetEntries.of2BlockDefault(
                        this, GemType.class, VanillaGemTypes.EMERALD,
                        "emerald_block_crystal",
                        "emerald_block_crystal_connecting",
                        TAB
                )
                .defaultBlockTexture()
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_crystalSet);

        block_patternedSet = RechiseledSetEntries.of2BlockDefault(
                        this, GemType.class, VanillaGemTypes.EMERALD,
                        "emerald_block_patterned",
                        "emerald_block_patterned_connecting",
                        TAB
                )
                .defaultBlockTexture()
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_patternedSet);

        block_patterned_squaresSet = RechiseledSetEntries.of2BlockDefault(
                        this, GemType.class, VanillaGemTypes.EMERALD,
                        "emerald_block_patterned_squares",
                        "emerald_block_patterned_squares_connecting",
                        TAB
                )
                .defaultBlockTexture()
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_patterned_squaresSet);

        block_pillarSet = RechiseledSetEntries.of2PillarBlockDefault(
                        this, GemType.class, VanillaGemTypes.EMERALD,
                        "emerald_block_pillar",
                        "emerald_block_pillar_connecting",
                        TAB
                )
                .defaultBlockTexture("_side")
                .defaultBlockTextureTop("_end")
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_pillarSet);

        block_polishedSet = RechiseledSetEntries.of2BlockDefault(
                        this, GemType.class, VanillaGemTypes.EMERALD,
                        "emerald_block_polished",
                        "emerald_block_polished_connecting",
                        TAB
                )
                .defaultBlockTexture()
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_polishedSet);

        block_stripedSet = RechiseledSetEntries.of2BlockDefault(
                        this, GemType.class, VanillaGemTypes.EMERALD,
                        "emerald_block_striped",
                        "emerald_block_striped_connecting",
                        TAB
                )
                .defaultBlockTexture()
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_stripedSet);

        block_waxedSet = RechiseledSetEntries.of2BlockDefault(
                        this, GemType.class, VanillaGemTypes.EMERALD,
                        "emerald_block_waxed",
                        "emerald_block_waxed_connecting",
                        TAB
                )
                .defaultBlockTexture()
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_waxedSet);

        block_crossesSet = RechiseledSetEntries.ofBlockDefault(
                        this, GemType.class, VanillaGemTypes.EMERALD,
                        "emerald_block_crosses",
                        TAB
                )
                .defaultBlockTexture()
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_crossesSet);

        block_platingSet = RechiseledSetEntries.ofBlockDefault(
                        this, GemType.class, VanillaGemTypes.EMERALD,
                        "emerald_block_plating",
                        TAB
                )
                .defaultBlockTexture()
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_platingSet);

        block_smoothSet = RechiseledSetEntries.ofBlockDefault(
                        this, GemType.class, VanillaGemTypes.EMERALD,
                        "emerald_block_smooth",
                        TAB
                )
                .defaultBlockTexture()
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_smoothSet);

    }

    @Override
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);
        executor.accept((manager, sink) ->
                createChiselingRecipe(this, GemTypeRegistry.INSTANCE, sink, "block_(smooth|plating|crosses)")
        );
    }
}