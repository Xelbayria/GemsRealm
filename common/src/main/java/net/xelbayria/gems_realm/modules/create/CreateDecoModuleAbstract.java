package net.xelbayria.gems_realm.modules.create;

import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.api.GemsRealmEntrySet;
import net.xelbayria.gems_realm.api.GemsRealmModule;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.api.set.metal.MetalTypeRegistry;
import net.xelbayria.gems_realm.api.set.metal.VanillaMetalTypes;

import java.util.function.Supplier;

import static net.xelbayria.gems_realm.api.set.metal.VanillaMetalChildKeys.*;

@SuppressWarnings("CommentedOutCode")
///SUPPORT: v2.1.3-NEOFORGE
public abstract class CreateDecoModuleAbstract extends GemsRealmModule {

    public final SimpleEntrySet<MetalType, Block> window;
    public final SimpleEntrySet<MetalType, Block> window_pane;
    public final SimpleEntrySet<MetalType, Block> bars_overlay;
    public final SimpleEntrySet<MetalType, Block> bars;
    public final SimpleEntrySet<MetalType, Block> mesh_fence;
    public final SimpleEntrySet<MetalType, Block> catwalk;
    public final SimpleEntrySet<MetalType, Block> catwalk_stairs;
    public final SimpleEntrySet<MetalType, Block> catwalk_railing;
    public final SimpleEntrySet<MetalType, Block> support;
    public final SimpleEntrySet<MetalType, Block> support_wedge;
//    public final SimpleEntrySet<MetalType, Block> facade; // Not available anymore
    public final SimpleEntrySet<MetalType, Block> ladder;
    public final SimpleEntrySet<MetalType, Block> hull;
    public final SimpleEntrySet<MetalType, Block> sheet_metal;
    public final SimpleEntrySet<MetalType, Block> yellow_lamp;
    public final SimpleEntrySet<MetalType, Block> red_lamp;
    public final SimpleEntrySet<MetalType, Block> green_lamp;
    public final SimpleEntrySet<MetalType, Block> blue_lamp;

    public CreateDecoModuleAbstract(String modId) {
        super(modId, "cd");
        Supplier<CreativeModeTab> tab = getModTab("props_tab");

        window = GemsRealmEntrySet.of(MetalType.class, "window",
                        getModBlock("iron_window"), () -> VanillaMetalTypes.IRON,
                        this::newWindowBlock
                )
                .requiresChildren(INGOT) //REASON: recipes
                .addTextureM(modRes("block/palettes/windows/iron_window"),
                        GemsRealm.res("block/cd/windows/iron_window_m"))
                .addTextureM(modRes("block/palettes/windows/iron_window_connected"),
                        GemsRealm.res("block/cd/windows/iron_window_connected_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(window);

        window_pane = GemsRealmEntrySet.of(MetalType.class, "window_pane",
                        getModBlock("iron_window_pane"), () -> VanillaMetalTypes.IRON,
                        this::newConnectedGlassPaneBlock
                )
                .requiresFromMap(window.blocks) //REASON: textures, recipes
                //TEXTURES: iron_window, iron_window_connected
                .addTexture(modRes("block/palettes/windows/iron_window_end"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(window_pane);

        bars = GemsRealmEntrySet.of(MetalType.class, "bars",
                        getModBlock("brass_bars"), () -> MetalTypeRegistry.getMetalType("create:brass"),
                        this::newIronBarsBlock
                )
                .requiresChildren(INGOT) //REASON: recipes
                .addTexture(modRes("block/palettes/metal_bars/brass_bars"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(ResourceLocation.withDefaultNamespace("bars"), Registries.BLOCK)
                .addTag(modRes("fan_transparent"), Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addRecipe(ResourceLocation.withDefaultNamespace("brass_bars_from_stonecutting"))
                .build();
        this.addEntry(bars);

        bars_overlay = GemsRealmEntrySet.of(MetalType.class, "bars_overlay",
                        getModBlock("brass_bars_overlay"), () -> MetalTypeRegistry.getMetalType("create:brass"),
                        this::newIronBarsBlock
                )
                //INGREDIENTS: create:sheet
                //TEXTURES: brass_bars
                .addTexture(modRes("block/palettes/metal_bars/brass_bars_overlay"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(ResourceLocation.withDefaultNamespace("bars"), Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addRecipe(ResourceLocation.withDefaultNamespace("brass_bars_overlay"))
                .addRecipe(ResourceLocation.withDefaultNamespace("brass_bars_overlay_from_stonecutting"))
                //Recipe minecraft:copper_bars_overlay_from_stonecutting
                .build();
        this.addEntry(bars_overlay);

        mesh_fence = GemsRealmEntrySet.of(MetalType.class, "mesh_fence",
                        getModBlock("iron_mesh_fence"), () -> VanillaMetalTypes.IRON,
                        this::newMeshFenceBlock
                )
                //INGREDIENTS: create:sheet
                .addTexture(modRes("block/palettes/sheet_metal/iron_sheet_metal"))
                .addTexture(modRes("block/palettes/chain_link_fence/iron_chain_link"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .defaultRecipe()
                .build();
        this.addEntry(mesh_fence);

        catwalk = GemsRealmEntrySet.of(MetalType.class, "catwalk",
                        getModBlock("iron_catwalk"), () -> VanillaMetalTypes.IRON,
                        this::newCatwalkBlock
                )
                //INGREDIENTS: create:sheet, minecraft:bars
                .requiresChildren(BARS, INGOT) //REASON: recipes, INGOT used for crafting CREATE:SHEET
//                .requiresChildren("create:sheet") //REASON: recipes
                .addTexture(modRes("block/palettes/catwalks/iron_catwalk"))
                .addTexture(modRes("block/palettes/catwalks/iron_catwalk_connected"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addRecipe(ResourceLocation.withDefaultNamespace("iron_catwalk_forge"))
                .addRecipe(ResourceLocation.withDefaultNamespace("iron_catwalk_from_stonecutting"))
                .addCustomItem((ignored, block, properties) -> newCatwalkBlockItem(block, properties))
                .build();
        this.addEntry(catwalk);

        catwalk_stairs = GemsRealmEntrySet.of(MetalType.class, "catwalk_stairs",
                        getModBlock("iron_catwalk_stairs"), () -> VanillaMetalTypes.IRON,
                        this::newCatwalkStairBlock
                )
                .requiresFromMap(catwalk.blocks) //REASON: recipes
                .requiresChildren(BARS, INGOT) //REASON: recipes, INGOT for stonecutting
                .addTexture(modRes("block/palettes/catwalks/iron_catwalk_stairs"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .defaultRecipe()
                .addRecipe(ResourceLocation.withDefaultNamespace("iron_catwalk_stairs_from_stonecutting"))
                .addCustomItem((ignored, block, properties) -> newCatwalkStairBlockItem(block, properties))
                .build();
        this.addEntry(catwalk_stairs);

        catwalk_railing = GemsRealmEntrySet.of(MetalType.class, "catwalk_railing",
                        getModBlock("iron_catwalk_railing"), () -> VanillaMetalTypes.IRON,
                        this::newCatwalkRailingBlock
                )
                //INGREDIENTS: create:sheet, minecraft:bars
                .requiresChildren(BARS, INGOT) //REASON: recipes, INGOT used for crafting CREATE:SHEET
                .addTexture(modRes("block/palettes/catwalks/iron_catwalk_rail"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .addRecipe(ResourceLocation.withDefaultNamespace("iron_catwalk_railing_forge"))
                .addRecipe(ResourceLocation.withDefaultNamespace("iron_catwalk_railing_from_stonecutting"))
                .build();
        this.addEntry(catwalk_railing);

        support = GemsRealmEntrySet.of(MetalType.class, "support",
                        getModBlock("iron_support"), () -> VanillaMetalTypes.IRON,
                        this::newSupportBlock
                )
                .requiresChildren(INGOT) //REASON: recipes
                .addTexture(modRes("block/palettes/support/iron_support"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .defaultRecipe()
                .build();
        this.addEntry(support);

        support_wedge = GemsRealmEntrySet.of(MetalType.class, "support_wedge",
                        getModBlock("iron_support_wedge"), () -> VanillaMetalTypes.IRON,
                        this::newSupportWedgeBlock
                )
                //INGREDIENTS: create:sheet
                .requiresChildren(INGOT) //REASON: recipes, INGOT used for crafting CREATE:SHEET
                .addTexture(modRes("block/palettes/support_wedges/iron_support_wedge"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .defaultRecipe()
                .build();
        this.addEntry(support_wedge);

//        facade = GemsRealmEntrySet.of(MetalType.class, "facade",
//                        getModBlock("iron_facade"), () -> VanillaMetalTypes.IRON,
//                        this::newFacadeBlock
//                )
//                .requiresChildren(INGOT) //REASON: recipes
//                .addTexture(modRes("block/blanks_9"))
//                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
//                .setTab(tab)
//                .setRenderType(RenderLayer.TRANSLUCENT)
//                .defaultRecipe()
//                .build();
//        this.addEntry(facade);

        ladder = GemsRealmEntrySet.of(MetalType.class, "ladder",
                        getModBlock("iron_ladder"), () -> VanillaMetalTypes.IRON,
                        this::newMetalLadderBlock
                )
                .requiresChildren(INGOT) //REASON: recipes
                .addTexture(modRes("block/palettes/ladders/ladder_iron"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .addRecipe(ResourceLocation.withDefaultNamespace("iron_ladder_from_stonecutting"))
                .build();
        this.addEntry(ladder);

        hull = GemsRealmEntrySet.of(MetalType.class, "hull",
                        getModBlock("iron_hull"), () -> VanillaMetalTypes.IRON,
                        this::newHullBlock
                )
                //INGREDIENTS: create:sheet
                .requiresChildren(INGOT) //REASON: recipes, INGOT used for crafting CREATE:SHEET
                //RECIPES: block
                .addTexture(modRes("block/palettes/hull/iron_hull_front"))
                .addTexture(modRes("block/palettes/hull/iron_hull_side"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .addRecipe(ResourceLocation.withDefaultNamespace("iron_hull_from_stonecutting"))
                .build();
        this.addEntry(hull);

        sheet_metal = GemsRealmEntrySet.of(MetalType.class, "sheet_metal",
                        getModBlock("iron_sheet_metal"), () -> VanillaMetalTypes.IRON,
                        this::newSheetMetalBlock
                )
                //INGREDIENTS: create:sheet
                .addTexture(modRes("block/palettes/sheet_metal/iron_sheet_metal"))
                .addTexture(modRes("block/palettes/sheet_metal/iron_sheet_metal_top"))
                .addTexture(modRes("block/palettes/sheet_metal/iron_sheet_metal_connected"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .build();
        this.addEntry(sheet_metal);

        yellow_lamp = GemsRealmEntrySet.of(MetalType.class, "lamp", "yellow",
                        getModBlock("yellow_iron_lamp"), () -> VanillaMetalTypes.IRON,
                        this::newCageLampBlock
                )
                .requiresChildren(NUGGET, INGOT) //REASON: recipes, INGOT used for crafting CREATE:SHEET
                //INGREDIENTS: create:sheet, minecraft:nugget
                .addTexture(modRes("block/palettes/cage_lamp/iron_lamp"))
                .addTexture(modRes("block/palettes/cage_lamp/light_default"))
                .addTexture(modRes("block/palettes/cage_lamp/light_default_off"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(yellow_lamp);

        red_lamp = GemsRealmEntrySet.of(MetalType.class, "lamp", "red",
                        getModBlock("red_iron_lamp"), () -> VanillaMetalTypes.IRON,
                        this::newCageLampBlock
                )
                .requiresChildren(NUGGET, INGOT) //REASON: recipes, INGOT used for crafting CREATE:SHEET
                //INGREDIENTS: create:sheet, minecraft:nugget
                //TEXTURES: iron_lamp
                .addTexture(modRes("block/palettes/cage_lamp/light_redstone"))
                .addTexture(modRes("block/palettes/cage_lamp/light_redstone_off"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(red_lamp);

        green_lamp = GemsRealmEntrySet.of(MetalType.class, "lamp", "green",
                        getModBlock("green_iron_lamp"), () -> VanillaMetalTypes.IRON,
                        this::newCageLampBlock
                )
                .requiresChildren(NUGGET, INGOT) //REASON: recipes, INGOT used for crafting CREATE:SHEET
                //INGREDIENTS: create:sheet, minecraft:nugget
                //TEXTURES: iron_lamp
                .addTexture(modRes("block/palettes/cage_lamp/light_green"))
                .addTexture(modRes("block/palettes/cage_lamp/light_green_off"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(green_lamp);

        blue_lamp = GemsRealmEntrySet.of(MetalType.class, "lamp", "blue",
                        getModBlock("blue_iron_lamp"), () -> VanillaMetalTypes.IRON,
                        this::newCageLampBlock
                )
                .requiresChildren(NUGGET, INGOT) //REASON: recipes, INGOT used for crafting CREATE:SHEET
                //INGREDIENTS: create:sheet, minecraft:nugget
                //TEXTURES: iron_lamp
                .addTexture(modRes("block/palettes/cage_lamp/light_soul"))
                .addTexture(modRes("block/palettes/cage_lamp/light_soul_off"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(blue_lamp);

    }

    protected abstract Block newWindowBlock(MetalType metalType);
    protected abstract Block newConnectedGlassPaneBlock(MetalType metalType);
    protected abstract Block newIronBarsBlock(MetalType metalType);
    protected abstract Block newMeshFenceBlock(MetalType metalType);

    protected abstract Block newCatwalkBlock(MetalType metalType);
    protected abstract Item newCatwalkBlockItem(Block block, Item.Properties properties);
    protected abstract Block newCatwalkStairBlock(MetalType metalType);
    protected abstract Item newCatwalkStairBlockItem(Block block, Item.Properties properties);
    protected abstract Block newCatwalkRailingBlock(MetalType metalType);
    protected abstract Block newSupportWedgeBlock(MetalType metalType);
    protected abstract Block newFacadeBlock(MetalType metalType);
    protected abstract Block newMetalLadderBlock(MetalType metalType);
    protected abstract Block newHullBlock(MetalType metalType);
    protected abstract Block newSupportBlock(MetalType metalType);
    protected abstract Block newCageLampBlock(MetalType metalType); // Special

    protected abstract Block newSheetMetalBlock(MetalType metalType); // sheet_metal
}