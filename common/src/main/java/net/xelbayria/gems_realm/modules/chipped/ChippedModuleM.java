package net.xelbayria.gems_realm.modules.chipped;

import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.xelbayria.gems_realm.api.GemsRealmEntrySet;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.api.set.metal.MetalTypeRegistry;
import net.xelbayria.gems_realm.api.set.metal.VanillaMetalTypes;

import java.util.Objects;
import java.util.function.Consumer;

import static net.xelbayria.gems_realm.api.set.VanillaRockChildKeys.BLOCK;
import static net.xelbayria.gems_realm.api.set.metal.VanillaMetalChildKeys.BARS;
import static net.xelbayria.gems_realm.api.set.metal.VanillaMetalChildKeys.INGOT;

//See ChippedModuleAbstract's SUPPORTED VERSION
/// Block for MetalType
public class ChippedModuleM extends ChippedModuleAbstract {

    /// Gold
    public final SimpleEntrySet<MetalType, Block> block_panels,
            ancient_block,
            chipped_block,
            embossed_block,
            engraved_block,
            layered_block,
            plated_block,
            pressed_block,
            reinforced_block,
            sheet_block,
            shuttered_block,
            stacked_block;

    /// Iron
    public final SimpleEntrySet<MetalType, Block> bars_top,
            barbed_bars,
            bolted_bars,
            chained_bars,
            crossbolted_bars,
            crossed_bars,
            floral_bars,
            harp_bars,
            linked_bars,
            loose_bars,
            piked_bars,
            railed_bars,
            sighthole_bars,
            stepped_bars,
            sturdy_bars,
            supported_bars,
            thorned_bars,
            vined_bars,
            woven_bars,
            zippered_bars,
            victorian_bars_pikes;

    public ChippedModuleM(String modId) {
        super(modId);

/// ──────────────────────────────────────────────────── Gold ─────────────────────────────────────────────────────
        block_panels = GemsRealmEntrySet.of(MetalType.class, "block_panels",
                        getModBlock("gold_block_panels"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/gold_block/gold_block_panels"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_panels);

        ancient_block = GemsRealmEntrySet.of(MetalType.class, "block", "ancient",
                        getModBlock("ancient_gold_block"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/gold_block/ancient_gold_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(ancient_block);

        chipped_block = GemsRealmEntrySet.of(MetalType.class, "block", "chipped",
                        getModBlock("chipped_gold_block"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/gold_block/chipped_gold_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(chipped_block);

        embossed_block = GemsRealmEntrySet.of(MetalType.class, "block", "embossed",
                        getModBlock("embossed_gold_block"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/gold_block/embossed_gold_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(embossed_block);

        engraved_block = GemsRealmEntrySet.of(MetalType.class, "block", "engraved",
                        getModBlock("engraved_gold_block"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/gold_block/engraved_gold_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(engraved_block);

        layered_block = GemsRealmEntrySet.of(MetalType.class, "block", "layered",
                        getModBlock("layered_gold_block"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/gold_block/layered_gold_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(layered_block);

        plated_block = GemsRealmEntrySet.of(MetalType.class, "block", "plated",
                        getModBlock("plated_gold_block"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/gold_block/plated_gold_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(plated_block);

        pressed_block = GemsRealmEntrySet.of(MetalType.class, "block", "pressed",
                        getModBlock("pressed_gold_block"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/gold_block/pressed_gold_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(pressed_block);

        reinforced_block = GemsRealmEntrySet.of(MetalType.class, "block", "reinforced",
                        getModBlock("reinforced_gold_block"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/gold_block/reinforced_gold_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(reinforced_block);

        sheet_block = GemsRealmEntrySet.of(MetalType.class, "block", "sheet",
                        getModBlock("sheet_gold_block"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/gold_block/sheet_gold_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(sheet_block);

        shuttered_block = GemsRealmEntrySet.of(MetalType.class, "block", "shuttered",
                        getModBlock("shuttered_gold_block"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/gold_block/shuttered_gold_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(shuttered_block);

        stacked_block = GemsRealmEntrySet.of(MetalType.class, "block", "stacked",
                        getModBlock("stacked_gold_block"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/gold_block/stacked_gold_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(stacked_block);

/// ───────────────────────────────────────────────────── Iron ──────────────────────────────────────────────────────

        bars_top = GemsRealmEntrySet.of(MetalType.class, "bars_top",
                        getModBlock("iron_bars_top"), () -> VanillaMetalTypes.IRON,
                metalType -> new IronBarsBlock(copyBarsSafe(metalType))
                )
                .requiresChildren(INGOT) //REASON: recipes for minecraft:bars
                .addTexture(modRes("block/iron_bars/iron_bars_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(bars_top);

        barbed_bars = GemsRealmEntrySet.of(MetalType.class, "bars", "barbed",
                        getModBlock("barbed_iron_bars"), () -> VanillaMetalTypes.IRON,
                metalType -> new IronBarsBlock(copyBarsSafe(metalType))
                )
                .requiresChildren(INGOT) //REASON: recipes for minecraft:bars
                .addTexture(modRes("block/iron_bars/barbed_iron_bars"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(barbed_bars);

        bolted_bars = GemsRealmEntrySet.of(MetalType.class, "bars", "bolted",
                        getModBlock("bolted_iron_bars"), () -> VanillaMetalTypes.IRON,
                metalType -> new IronBarsBlock(copyBarsSafe(metalType))
                )
                .requiresChildren(INGOT) //REASON: recipes for minecraft:bars
                .addTexture(modRes("block/iron_bars/bolted_iron_bars"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(bolted_bars);

        chained_bars = GemsRealmEntrySet.of(MetalType.class, "bars", "chained",
                        getModBlock("chained_iron_bars"), () -> VanillaMetalTypes.IRON,
                metalType -> new IronBarsBlock(copyBarsSafe(metalType))
                )
                .requiresChildren(INGOT) //REASON: recipes for minecraft:bars
                .addTexture(modRes("block/iron_bars/chained_iron_bars"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(chained_bars);

        crossbolted_bars = GemsRealmEntrySet.of(MetalType.class, "bars", "crossbolted",
                        getModBlock("crossbolted_iron_bars"), () -> VanillaMetalTypes.IRON,
                metalType -> new IronBarsBlock(copyBarsSafe(metalType))
                )
                .requiresChildren(INGOT) //REASON: recipes for minecraft:bars
                .addTexture(modRes("block/iron_bars/crossbolted_iron_bars"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(crossbolted_bars);

        crossed_bars = GemsRealmEntrySet.of(MetalType.class, "bars", "crossed",
                        getModBlock("crossed_iron_bars"), () -> VanillaMetalTypes.IRON,
                metalType -> new IronBarsBlock(copyBarsSafe(metalType))
                )
                .requiresChildren(INGOT) //REASON: recipes for minecraft:bars
                .addTexture(modRes("block/iron_bars/crossed_iron_bars"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(crossed_bars);

        floral_bars = GemsRealmEntrySet.of(MetalType.class, "bars", "floral",
                        getModBlock("floral_iron_bars"), () -> VanillaMetalTypes.IRON,
                metalType -> new IronBarsBlock(copyBarsSafe(metalType))
                )
                .requiresChildren(INGOT) //REASON: recipes for minecraft:bars
                .addTexture(modRes("block/iron_bars/floral_iron_bars"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(floral_bars);

        harp_bars = GemsRealmEntrySet.of(MetalType.class, "bars", "harp",
                        getModBlock("harp_iron_bars"), () -> VanillaMetalTypes.IRON,
                metalType -> new IronBarsBlock(copyBarsSafe(metalType))
                )
                .requiresChildren(INGOT) //REASON: recipes for minecraft:bars
                .addTexture(modRes("block/iron_bars/harp_iron_bars"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(harp_bars);

        linked_bars = GemsRealmEntrySet.of(MetalType.class, "bars", "linked",
                        getModBlock("linked_iron_bars"), () -> VanillaMetalTypes.IRON,
                metalType -> new IronBarsBlock(copyBarsSafe(metalType))
                )
                .requiresChildren(INGOT) //REASON: recipes for minecraft:bars
                .addTexture(modRes("block/iron_bars/linked_iron_bars"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(linked_bars);

        loose_bars = GemsRealmEntrySet.of(MetalType.class, "bars", "loose",
                        getModBlock("loose_iron_bars"), () -> VanillaMetalTypes.IRON,
                metalType -> new IronBarsBlock(copyBarsSafe(metalType))
                )
                .requiresChildren(INGOT) //REASON: recipes for minecraft:bars
                .addTexture(modRes("block/iron_bars/loose_iron_bars"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(loose_bars);

        piked_bars = GemsRealmEntrySet.of(MetalType.class, "bars", "piked",
                        getModBlock("piked_iron_bars"), () -> VanillaMetalTypes.IRON,
                metalType -> new IronBarsBlock(copyBarsSafe(metalType))
                )
                .requiresChildren(INGOT) //REASON: recipes for minecraft:bars
                .addTexture(modRes("block/iron_bars/piked_iron_bars"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(piked_bars);

        railed_bars = GemsRealmEntrySet.of(MetalType.class, "bars", "railed",
                        getModBlock("railed_iron_bars"), () -> VanillaMetalTypes.IRON,
                metalType -> new IronBarsBlock(copyBarsSafe(metalType))
                )
                .requiresChildren(INGOT) //REASON: recipes for minecraft:bars
                .addTexture(modRes("block/iron_bars/railed_iron_bars"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(railed_bars);

        sighthole_bars = GemsRealmEntrySet.of(MetalType.class, "bars", "sighthole",
                        getModBlock("sighthole_iron_bars"), () -> VanillaMetalTypes.IRON,
                metalType -> new IronBarsBlock(copyBarsSafe(metalType))
                )
                .requiresChildren(INGOT) //REASON: recipes for minecraft:bars
                .addTexture(modRes("block/iron_bars/sighthole_iron_bars"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(sighthole_bars);

        stepped_bars = GemsRealmEntrySet.of(MetalType.class, "bars", "stepped",
                        getModBlock("stepped_iron_bars"), () -> VanillaMetalTypes.IRON,
                metalType -> new IronBarsBlock(copyBarsSafe(metalType))
                )
                .requiresChildren(INGOT) //REASON: recipes for minecraft:bars
                .addTexture(modRes("block/iron_bars/stepped_iron_bars"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(stepped_bars);

        sturdy_bars = GemsRealmEntrySet.of(MetalType.class, "bars", "sturdy",
                        getModBlock("sturdy_iron_bars"), () -> VanillaMetalTypes.IRON,
                metalType -> new IronBarsBlock(copyBarsSafe(metalType))
                )
                .requiresChildren(INGOT) //REASON: recipes for minecraft:bars
                .addTexture(modRes("block/iron_bars/sturdy_iron_bars"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(sturdy_bars);

        supported_bars = GemsRealmEntrySet.of(MetalType.class, "bars", "supported",
                        getModBlock("supported_iron_bars"), () -> VanillaMetalTypes.IRON,
                metalType -> new IronBarsBlock(copyBarsSafe(metalType))
                )
                .requiresChildren(INGOT) //REASON: recipes for minecraft:bars
                .addTexture(modRes("block/iron_bars/supported_iron_bars"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(supported_bars);

        thorned_bars = GemsRealmEntrySet.of(MetalType.class, "bars", "thorned",
                        getModBlock("thorned_iron_bars"), () -> VanillaMetalTypes.IRON,
                metalType -> new IronBarsBlock(copyBarsSafe(metalType))
                )
                .requiresChildren(INGOT) //REASON: recipes for minecraft:bars
                .addTexture(modRes("block/iron_bars/thorned_iron_bars"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(thorned_bars);

        vined_bars = GemsRealmEntrySet.of(MetalType.class, "bars", "vined",
                        getModBlock("vined_iron_bars"), () -> VanillaMetalTypes.IRON,
                metalType -> new IronBarsBlock(copyBarsSafe(metalType))
                )
                .requiresChildren(INGOT) //REASON: recipes for minecraft:bars
                .addTexture(modRes("block/iron_bars/vined_iron_bars"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(vined_bars);

        woven_bars = GemsRealmEntrySet.of(MetalType.class, "bars", "woven",
                        getModBlock("woven_iron_bars"), () -> VanillaMetalTypes.IRON,
                metalType -> new IronBarsBlock(copyBarsSafe(metalType))
                )
                .requiresChildren(INGOT) //REASON: recipes for minecraft:bars
                .addTexture(modRes("block/iron_bars/woven_iron_bars"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(woven_bars);

        zippered_bars = GemsRealmEntrySet.of(MetalType.class, "bars", "zippered",
                        getModBlock("zippered_iron_bars"), () -> VanillaMetalTypes.IRON,
                metalType -> new IronBarsBlock(copyBarsSafe(metalType))
                )
                .requiresChildren(INGOT) //REASON: recipes for minecraft:bars
                .addTexture(modRes("block/iron_bars/zippered_iron_bars"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(zippered_bars);

        victorian_bars_pikes = GemsRealmEntrySet.of(MetalType.class, "bars_pikes", "victorian",
                        getModBlock("victorian_iron_bars_pikes"), () -> VanillaMetalTypes.IRON,
                metalType -> new IronBarsBlock(copyBarsSafe(metalType))
                )
                .requiresChildren(INGOT) //REASON: recipes for minecraft:bars
                .addTexture(modRes("block/iron_bars/victorian_iron_bars_pikes"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(victorian_bars_pikes);

    }

    private BlockBehaviour.Properties copyBarsSafe(MetalType metalType) {
        Block bars = metalType.getBlockOfThis(BARS);
        return (Objects.nonNull(bars)) ? Utils.copyPropertySafe(bars) : Utils.copyPropertySafe(Blocks.IRON_BARS);
    }

    @Override
    // RECIPES
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) -> {
            createWorkbenchRecipe(BLOCK, MetalTypeRegistry.INSTANCE, Workbench.ALCHEMY_BENCH, sink);
            createWorkbenchRecipe(BARS, MetalTypeRegistry.INSTANCE, Workbench.TINKERING_TABLE, sink);
        });
    }
}