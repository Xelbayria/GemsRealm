package net.xelbayria.gems_realm.modules.rechiseled;

import com.supermartijn642.rechiseled.blocks.RechiseledBlock;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.PaletteStrategy;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.xelbayria.gems_realm.api.set.VanillaRockChildKeys;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.api.set.metal.MetalTypeRegistry;
import net.xelbayria.gems_realm.api.set.metal.VanillaMetalTypes;
import net.xelbayria.gems_realm.common_classes.BlockSetEntries;

import java.util.function.Consumer;

import static net.mehvahdjukaar.every_compat.api.PaletteStrategies.registerCached;

//See RechiseledModuleAbstract's SUPPORTED VERSION
public class RechiseledModuleM extends RechiseledModuleAbstract {

    public final BlockSetEntries<MetalType> block_borderedSet,
            block_chiseledSet,
            block_framedSet,
            block_gearsSet,
            block_linesSet,
            block_patternedSet,
            block_pipesSet,
            block_polishedSet,
            block_processedSet,
            block_small_bricksSet,
            block_platedSet,
            block_pulverizedSet,
            block_reinforcedSet,
            block_sheetsSet,
            block_smoothSet,
            block_connecting_connectingSet; //iron_block's connectingBlock

    public RechiseledModuleM(String modId) {
        super(modId);

        block_borderedSet = RechiseledSetEntries.of2BlockDefault(
                        this, MetalType.class, VanillaMetalTypes.IRON,
                        "iron_block_bordered",
                        "iron_block_bordered_connecting",
                        TAB
                )
                .defaultBlockTexture(BLOCK_LOW_CONTRAST)
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_borderedSet);

        block_chiseledSet = RechiseledSetEntries.of2BlockDefault(
                        this, MetalType.class, VanillaMetalTypes.IRON,
                        "iron_block_chiseled",
                        "iron_block_chiseled_connecting",
                        TAB
                )
                .defaultBlockTexture(BLOCK_LOW_CONTRAST)
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_chiseledSet);

        block_framedSet = RechiseledSetEntries.of2BlockDefault(
                        this, MetalType.class, VanillaMetalTypes.IRON,
                        "iron_block_framed",
                        "iron_block_framed_connecting",
                        TAB
                )
                .defaultBlockTexture(BLOCK_LOW_CONTRAST)
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_framedSet);

        block_gearsSet = RechiseledSetEntries.of2BlockDefault(
                        this, MetalType.class, VanillaMetalTypes.IRON,
                        "iron_block_gears",
                        "iron_block_gears_connecting",
                        TAB
                )
                .defaultBlockTexture(BLOCK_LOW_CONTRAST)
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_gearsSet);

        block_linesSet = RechiseledSetEntries.of2BlockDefault(
                        this, MetalType.class, VanillaMetalTypes.IRON,
                        "iron_block_lines",
                        "iron_block_lines_connecting",
                        TAB
                )
                .defaultBlockTexture(BLOCK_LOW_CONTRAST)
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_linesSet);

        block_patternedSet = RechiseledSetEntries.of2BlockDefault(
                        this, MetalType.class, VanillaMetalTypes.IRON,
                        "iron_block_patterned",
                        "iron_block_patterned_connecting",
                        TAB
                )
                .defaultBlockTexture(BLOCK_LOW_CONTRAST)
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_patternedSet);

        block_pipesSet = RechiseledSetEntries.of2BlockDefault(
                        this, MetalType.class, VanillaMetalTypes.IRON,
                        "iron_block_pipes",
                        "iron_block_pipes_connecting",
                        TAB
                )
                .defaultBlockTexture(BLOCK_LOW_CONTRAST)
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_pipesSet);

        block_polishedSet = RechiseledSetEntries.of2BlockDefault(
                        this, MetalType.class, VanillaMetalTypes.IRON,
                        "iron_block_polished",
                        "iron_block_polished_connecting",
                        TAB
                )
                .defaultBlockTexture(BLOCK_LOW_CONTRAST)
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_polishedSet);

        block_processedSet = RechiseledSetEntries.of2BlockDefault(
                        this, MetalType.class, VanillaMetalTypes.IRON,
                        "iron_block_processed",
                        "iron_block_processed_connecting",
                        TAB
                )
                .defaultBlockTexture(BLOCK_LOW_CONTRAST)
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_processedSet);

        block_small_bricksSet = RechiseledSetEntries.of2BlockDefault(
                        this, MetalType.class, VanillaMetalTypes.IRON,
                        "iron_block_small_bricks",
                        "iron_block_small_bricks_connecting",
                        TAB
                )
                .defaultBlockTexture(BLOCK_LOW_CONTRAST)
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_small_bricksSet);

        block_platedSet = RechiseledSetEntries.ofBlockDefault(
                        this, MetalType.class, VanillaMetalTypes.IRON,
                        "iron_block_plated",
                        TAB
                )
                .defaultBlockTexture(BLOCK_LOW_CONTRAST)
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_platedSet);

        block_pulverizedSet = RechiseledSetEntries.ofBlockDefault(
                        this, MetalType.class, VanillaMetalTypes.IRON,
                        "iron_block_pulverized",
                        TAB
                )
                .defaultBlockTexture(BLOCK_LOW_CONTRAST)
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_pulverizedSet);

        block_reinforcedSet = RechiseledSetEntries.ofBlockDefault(
                        this, MetalType.class, VanillaMetalTypes.IRON,
                        "iron_block_reinforced",
                        TAB
                )
                .defaultBlockTexture(BLOCK_LOW_CONTRAST)
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_reinforcedSet);

        block_sheetsSet = RechiseledSetEntries.ofBlockDefault(
                        this, MetalType.class, VanillaMetalTypes.IRON,
                        "iron_block_sheets",
                        TAB
                )
                .defaultBlockTexture(BLOCK_LOW_CONTRAST)
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_sheetsSet);

        block_smoothSet = RechiseledSetEntries.ofBlockDefault(
                        this, MetalType.class, VanillaMetalTypes.IRON,
                        "iron_block_smooth",
                        TAB
                )
                .defaultBlockTexture(BLOCK_LOW_CONTRAST)
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_smoothSet);

        ///iron_block's connectingBlock
        block_connecting_connectingSet = RechiseledSetEntries.ofBlockFactory(
                        this, MetalType.class, VanillaMetalTypes.IRON,
                        "iron_block_connecting_connecting", metalType ->
                                new RechiseledBlock(true, Utils.copyPropertySafe(metalType.block)),
                        TAB
                )
                .block(builder -> builder.addTexture(modRes("block/iron_block_connecting"), BLOCK_LOW_CONTRAST))
                .IronToolTag()
                //TAGS: MINEABLE_WITH_PICKAXE
                //RECIPES: manually created
                .build();
        this.addEntries(block_connecting_connectingSet);

    }

    public static final PaletteStrategy BLOCK_LOW_CONTRAST = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, VanillaRockChildKeys.BLOCK, null, p -> {
                //luminance step is the distance between 2 colors. Essentially contrast
                float averageStep = p.getAverageLuminanceStep();
                //lower step = lower contrast. Tweak as needed
                p.matchLuminanceStep(averageStep * 0.85f);
            }));

    @Override
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);
        executor.accept((manager, sink) ->
                createChiselingRecipe(this, MetalTypeRegistry.INSTANCE, sink, "block_(smooth|sheets|reinforced|plated|pulverized)")
        );
    }
}