package net.xelbayria.gems_realm.modules.create;

import com.mojang.datafixers.util.Pair;
import com.simibubi.create.content.decoration.MetalScaffoldingBlockItem;
import com.simibubi.create.content.decoration.encasing.EncasableBlock;
import com.simibubi.create.content.decoration.encasing.EncasedBlock;
import com.simibubi.create.content.decoration.encasing.EncasingRegistry;
import com.simibubi.create.content.kinetics.crank.ValveHandleBlock;
import com.simibubi.create.content.logistics.funnel.FunnelItem;
import com.simibubi.create.content.logistics.tunnel.BeltTunnelItem;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.createmod.catnip.data.Couple;
import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.misc.UtilityRecipe;
import net.mehvahdjukaar.every_compat.misc.UtilityTag;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.api.GemsRealmEntrySet;
import net.xelbayria.gems_realm.api.GemsRealmModule;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.api.set.metal.MetalTypeRegistry;
import net.xelbayria.gems_realm.api.set.metal.VanillaMetalTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.simibubi.create.AllPartialModels.FOLDING_DOORS;
import static net.mehvahdjukaar.every_compat.misc.UtilityTag.*;
import static net.xelbayria.gems_realm.api.set.VanillaRockChildKeys.BLOCK;
import static net.xelbayria.gems_realm.api.set.metal.VanillaMetalChildKeys.INGOT;

///SUPPORTED: v6.0.10+
public abstract class CreateModuleAbstract extends GemsRealmModule {

    public final ItemOnlyEntrySet<MetalType, Item> sheet;
    public final SimpleEntrySet<MetalType, Block> casing, encased_shaft, encased_cogwheel, encased_large_cogwheel,
            door,
            ladder,
            scaffolding,
            shingles, shingle_slab, shingle_stairs,
            tiles, tile_slab, tile_stairs,
            table_cloth,
            orante_window, ornate_window_pane;

    public final SimpleEntrySet<MetalType, ValveHandleBlock> valve_handle;
    public final static Map<ResourceLocation, PartialModel> VALVE_HANDLES = new HashMap<>(); //TODO: remove the mixin once Create v6.0.11 or newer

    public final SimpleEntrySet<MetalType, Block> funnel, belt_funnel;
    public final static ArrayList<Block> supportsFilteringList = new ArrayList<>();

    /// Required BeltBlockEntity to have a list of BlockType instead of ENUM that contains NONE, ANDESITE, BRASS
    /// This applied to CASING where it can be applied to belt_block, too.
//    public final SimpleEntrySet<MetalType, Block> tunnel/*, belt_funnel*/;
//    public final static ArrayList<Block> tunnelList = new ArrayList<>();


    @SuppressWarnings("CommentedOutCode") // tunnel and cased_belt will be supported in the future, no ETA
    public CreateModuleAbstract(String modId) {
        super(modId, "c");
        Supplier<CreativeModeTab> tab = getModTab("base");
        Supplier<CreativeModeTab> paletteTab = getModTab("palettes");

        sheet = ItemOnlyEntrySet.builder(MetalType.class, "sheet",
                        getModItem("iron_sheet"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Item(new Item.Properties())
                )
                .requiresChildren(INGOT) //REASON: recipes
                .addTexture(modRes("item/iron_sheet"))
                .addTag(platformTag("plates"), Registries.ITEM)
                //TAG: forge:plates/<type>
                .setTab(tab)
                //RECIPES: manually created
                .build();
        this.addEntry(sheet);

        casing = GemsRealmEntrySet.of(MetalType.class, "casing",
                        getModBlock("brass_casing"), () -> MetalTypeRegistry.getMetalType("create:brass"),
                        this::newCasingBlock
                )
                .requiresChildren(INGOT) //REASON: recipes
                .addTextureM(modRes("block/brass_casing"), GemsRealm.res("block/c/copper_casing_m"))
                .addTextureM(modRes("block/brass_casing_connected"), GemsRealm.res("block/c/copper_casing_connected_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("casing"), Registries.BLOCK, Registries.ITEM)
                .setTab(tab)
//                .defaultRecipe() //REQUIRED a unique recipe, create:deploying
                .build();
        this.addEntry(casing);

        encased_shaft = GemsRealmEntrySet.of(MetalType.class, "encased_shaft",
                        getModBlock("brass_encased_shaft"), () -> MetalTypeRegistry.getMetalType("create:brass"),
                        metalType -> newEncasedShaftedBlock(metalType, () -> casing.blocks.get(metalType))
                )
                .requiresFromMap(casing.blocks) //REASON: It's one of the main block's blockstate
                .addTile(getModTile("encased_shaft"))
                //TEXTURES: brass_casing
                .addTextureM(modRes("block/brass_gearbox"), GemsRealm.res("block/c/brass_innerby2pixel_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .noTab() //REASON: doesn't require item/tab
                .build();
        this.addEntry(encased_shaft);

        encased_cogwheel = GemsRealmEntrySet.of(MetalType.class, "encased_cogwheel",
                        getModBlock("brass_encased_cogwheel"), () -> MetalTypeRegistry.getMetalType("create:brass"),
                        metalType -> newEncasedCogwheelBlock(metalType, () -> casing.blocks.get(metalType))
                )
                .requiresFromMap(casing.blocks) //REASON: It's one of the main block's blockstate
                .addTile(getModTile("encased_cogwheel"))
                //TEXTURES: brass_casing, brass_gearbox
                .addTextureM(modRes("block/brass_encased_cogwheel_side"), GemsRealm.res("block/c/brass_innerby2pixel_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .copyParentDrop()
                .noTab() //REASON: doesn't require item/tab
                .build();
        this.addEntry(encased_cogwheel);

        encased_large_cogwheel = GemsRealmEntrySet.of(MetalType.class, "encased_large_cogwheel",
                        getModBlock("brass_encased_large_cogwheel"), () -> MetalTypeRegistry.getMetalType("create:brass"),
                        metalType -> newEncasedLargeCogwheelBlock(metalType, () -> casing.blocks.get(metalType))
                )
                .requiresFromMap(casing.blocks) //REASON: It's one of the main block's blockstate
                .addTile(getModTile("encased_large_cogwheel"))
                //TEXTURES: brass_casing, brass_gearbox
                .addTextureM(modRes("block/brass_encased_cogwheel_side_connected"), GemsRealm.res("block/c/brass_encased_cogwheel_side_connected_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .copyParentDrop()
                .noTab() //REASON: doesn't require item/tab
                .build();
        this.addEntry(encased_large_cogwheel);

        funnel = GemsRealmEntrySet.of(MetalType.class, "funnel",
                        getModBlock("brass_funnel"), () -> MetalTypeRegistry.getMetalType("create:brass"),
                        metalType -> newFunnelBlock(metalType, beltFunnelSupplier(metalType))
                )
                .addTile(getModTile("funnel"))
                .addModelTransform(m ->
                        m.replaceWithTextureFromChild("create:block/brass_block", BLOCK)
                )
                //TEXTURES: brass_block
                .addTexture(modRes("block/funnel/brass_funnel"))
                .addTexture(modRes("block/funnel/brass_funnel_push"))
                .addTexture(modRes("block/funnel/brass_funnel_neutral"))
                .addTexture(modRes("block/funnel/brass_funnel_pull"))
                .addTexture(modRes("block/funnel/brass_funnel_frame"))
                .addTexture(modRes("block/funnel/brass_funnel_unpowered"))
                .addTexture(modRes("block/funnel/brass_funnel_powered"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("safe_nbt"), Registries.BLOCK)
                .addTag(modRes("contraption_controlled"), Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //RECIPES: manually created
                .addCustomItem((ignored, block, properties) -> new FunnelItem(block, properties))
                .build();
        this.addEntry(funnel);

        belt_funnel = GemsRealmEntrySet.of(MetalType.class, "belt_funnel",
                        getModBlock("brass_belt_funnel"), () -> MetalTypeRegistry.getMetalType("create:brass"),
                        metalType -> newBeltFunnelBlock(metalType, funnel.blocks.get(metalType))
                )
                .requiresFromMap(funnel.blocks) //REASON: textures & It's one of the main block's blockstate
                .addTile(getModTile("funnel"))
                .addModelTransform(m ->
                        m.replaceWithTextureFromChild("create:block/brass_block", BLOCK)
                )
                //TEXTURES: brass_block, funnel_push, funnel_powered, funnel_unpowered, funnel, funnel_neutral, funnel_frame, funnel_pull
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("safe_nbt"), Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .copyParentDrop()
                .noTab().noItem()
                .build();
        this.addEntry(belt_funnel);

        /// Required BeltBlockEntity to have a list of BlockType instead of ENUM that contains NONE, ANDESITE, BRASS
        /// This applied to CASING where it can be applied to belt_block, too.
//        tunnel = GemsRealmEntrySet.of(MetalType.class, "tunnel",
//                        getModBlock("brass_tunnel"), () -> MetalTypeRegistry.getMetalType("create:brass"),
//                        metalType -> {
//                            Block block = newTunnelBlock(metalType);
//                            tunnelList.add(block);
//                            return block;
//                        }
//                )
//                .requiresFromMap(funnel.blocks) //REASON: textures
//                .addTile(getModTile("brass_tunnel"))
//                .addModelTransform(m ->
//                        m.replaceWithTextureFromChild("create:block/brass_block", BLOCK)
//                )
//                //TEXTURES: brass_block, funnel_neutral, funnel_frame
//                .addTextureM(modRes("block/tunnel/brass_tunnel"), GemsRealm.res("block/c/tunnel/brass_tunnel_m"))
//                .addTexture(modRes("block/tunnel/brass_tunnel_top"))
//                .addTexture(modRes("block/tunnel/brass_tunnel_top_connected"))
//                .addTextureM(modRes("block/tunnel/brass_tunnel_top_window"), GemsRealm.res("block/c/tunnel/brass_tunnel_top_window_m"))
//                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
//                .setTab(tab)
//                .setRenderType(RenderLayer.CUTOUT_MIPPED)
//                .build();
//        this.addEntry(tunnel);

        door = GemsRealmEntrySet.of(MetalType.class, "door",
                        getModBlock("copper_door"), () -> VanillaMetalTypes.COPPER,
                        this::makeSlidingDoorBlock
                )
                .requiresFromMap(casing.blocks) //REASON: recipes
                .addTile(getModTile("sliding_door"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .includeModelsBlock(true, modRes("block/copper_door/fold_left"), modRes("block/copper_door/fold_right"))
                .addTextureM(modRes("block/copper_door_bottom"), GemsRealm.res("block/c/copper_door_bottom_m"))
                .addTextureM(modRes("block/copper_door_side"), GemsRealm.res("block/c/copper_door_side_m"))
                .addTextureM(modRes("block/copper_door_top"), GemsRealm.res("block/c/copper_door_top_m"))
                .addTextureM(modRes("item/copper_door"), GemsRealm.res("item/c/copper_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .addRecipe(modRes("crafting/kinetics/copper_door"))
                .copyParentDrop()
                .addCustomItem((ignored, block, properties) -> new BeltTunnelItem(block, properties))
                .build();
        this.addEntry(door);

        ladder = GemsRealmEntrySet.of(MetalType.class, "ladder",
                        getModBlock("copper_ladder"), () -> VanillaMetalTypes.COPPER,
                        this::makeMetalLadderBlock
                )
                .requiresChildren(INGOT) //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTexture(modRes("block/ladder_copper"))
                .addTexture(modRes("block/ladder_copper_hoop"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .addTag(BlockTags.FALL_DAMAGE_RESETTING, Registries.BLOCK)
                .addTag(modRes("copycat_deny"), Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(ladder);

        scaffolding = GemsRealmEntrySet.of(MetalType.class, "scaffolding",
                        getModBlock("copper_scaffolding"), () -> VanillaMetalTypes.COPPER,
                        this::makeMetalScaffoldingBlock
                )
                .requiresChildren(INGOT) //REASON: recipes
                .addTexture(modRes("block/scaffold/copper_scaffold"))
                .addTexture(modRes("block/scaffold/copper_scaffold_connected"))
                .addTexture(modRes("block/scaffold/copper_scaffold_inside"))
                .addTexture(modRes("block/scaffold/copper_scaffold_inside_connected"))
                .addTexture(modRes("block/funnel/copper_funnel_frame"))
                //TEXTURES: casing,
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addCustomItem((metalType, block, properties) -> new MetalScaffoldingBlockItem(block, properties))
                .build();
        this.addEntry(scaffolding);

        shingles = GemsRealmEntrySet.of(MetalType.class, "shingles",
                        getModBlock("copper_shingles"), () -> VanillaMetalTypes.COPPER,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block).sound(metalType.getSound()))
                )
                .requiresChildren(INGOT) //REASON: recipes
                .addTexture(modRes("block/copper/copper_shingles"))
                .addTexture(modRes("block/copper/copper_shingles_top_connected"))
                .addTexture(modRes("block/copper/copper_roof_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //RECIPES: Manually created
                .build();
        this.addEntry(shingles);

        shingle_slab = GemsRealmEntrySet.of(MetalType.class, "shingle_slab",
                        getModBlock("copper_shingle_slab"), () -> VanillaMetalTypes.COPPER,
                        metalType -> new SlabBlock(Utils.copyPropertySafe(metalType.block))
                )
                .requiresFromMap(shingles.blocks) //REASON: recipes
                //TEXTURES: shingles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .addTag(ItemTags.SLABS, Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .addRecipe(modRes("copper_shingle_slab_from_copper_shingles_stonecutting"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(shingle_slab);

        shingle_stairs = GemsRealmEntrySet.of(MetalType.class, "shingle_stairs",
                        getModBlock("copper_shingle_stairs"), () -> VanillaMetalTypes.COPPER,
                        metalType -> new StairBlock(metalType.block.defaultBlockState(),
                                Utils.copyPropertySafe(metalType.block))
                )
                .requiresFromMap(shingles.blocks) //REASON: recipes
                //TEXTURES: shingles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .addTag(ItemTags.STAIRS, Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .addRecipe(modRes("copper_shingle_stairs_from_copper_shingles_stonecutting"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(shingle_stairs);

        tiles = GemsRealmEntrySet.of(MetalType.class, "tiles",
                        getModBlock("copper_tiles"), () -> VanillaMetalTypes.COPPER,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block).sound(metalType.getSound()))
                )
                .requiresChildren(INGOT) //REASON: recipes
                //TEXTURES: shingles' copper_roof_top
                .addTexture(modRes("block/copper/copper_tiles"))
                .addTexture(modRes("block/copper/copper_tiles_top_connected"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //RECIPES: Manually created
                .build();
        this.addEntry(tiles);

        tile_slab = GemsRealmEntrySet.of(MetalType.class, "tile_slab",
                        getModBlock("copper_tile_slab"), () -> VanillaMetalTypes.COPPER,
                        metalType -> new SlabBlock(Utils.copyPropertySafe(metalType.block))
                )
                .requiresFromMap(tiles.blocks) //REASON: recipes
                //TEXTURES: tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .addTag(ItemTags.SLABS, Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .addRecipe(modRes("copper_tile_slab_from_copper_tiles_stonecutting"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(tile_slab);

        tile_stairs = GemsRealmEntrySet.of(MetalType.class, "tile_stairs",
                        getModBlock("copper_tile_stairs"), () -> VanillaMetalTypes.COPPER,
                        metalType -> new StairBlock(metalType.block.defaultBlockState(),
                                Utils.copyPropertySafe(metalType.block))
                )
                .requiresFromMap(tiles.blocks) //REASON: recipes
                //TEXTURES: tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .addTag(ItemTags.STAIRS, Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .addRecipe(modRes("copper_tile_stairs_from_copper_tiles_stonecutting"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(tile_stairs);

        table_cloth = GemsRealmEntrySet.of(MetalType.class, "table_cloth",
                        getModBlock("copper_table_cloth"), () -> VanillaMetalTypes.COPPER,
                        this::newTableClothBlock
                )
                .addTile(getModTile("table_cloth"))
                .requiresChildren(INGOT) //REASON: recipes
                .addTextureM(modRes("block/table_cloth/copper"), GemsRealm.res("block/c/copper_table_cloth_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.INSIDE_STEP_SOUND_BLOCKS, Registries.BLOCK)
                .addTag(modRes("table_cloths"), Registries.BLOCK, Registries.ITEM)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addCustomItem((metalType, block, properties) -> newTableClothBlockItem(block, properties))
                .build();
        this.addEntry(table_cloth);

        orante_window = GemsRealmEntrySet.of(MetalType.class, "window", "ornate",
                        getModBlock("ornate_iron_window"), () -> VanillaMetalTypes.IRON,
                        this::makeWindow
                )
//                .requiresChildren("nugget") //REASON: recipes
                .addTextureM(modRes("block/palettes/ornate_iron_window"),
                        GemsRealm.res("block/c/ornate_iron_window_m"))
                .addTexture(modRes("block/palettes/ornate_iron_window_end"))
                .addTextureM(modRes("block/palettes/ornate_iron_window_connected"),
                        GemsRealm.res("block/c/ornate_iron_window_connected_m"))
                .setTab(paletteTab)
                .defaultRecipe()
                .setRenderType(RenderLayer.TRANSLUCENT)
                .build();
        this.addEntry(orante_window);

        ornate_window_pane = GemsRealmEntrySet.of(MetalType.class, "window_pane", "ornate",
                        getModBlock("ornate_iron_window_pane"), () -> VanillaMetalTypes.IRON,
                        this::makeConnectedGlassPaneBlock
                )
                .requiresFromMap(orante_window.blocks)
                //TEXTURES: orante_iron_window
                .addTexture(modRes("block/palettes/ornate_iron_window_pane_top"))
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(platformTag("glass_panes"), Registries.BLOCK, Registries.ITEM)
                .setTab(paletteTab)
                .defaultRecipe()
                .setRenderType(RenderLayer.TRANSLUCENT)
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                .build();
        this.addEntry(ornate_window_pane);

        valve_handle = GemsRealmEntrySet.of(MetalType.class, "valve_handle",
                        getModBlock("copper_valve_handle", ValveHandleBlock.class), () -> VanillaMetalTypes.COPPER,
                        this::newValveHandleBlock
                )
                .requiresChildren(INGOT) //REASON: recipes - INGOT is used for crafting create:sheet
                .includeModelsBlock(ResourceLocation.parse("block/valve_handle"))
                .addTile(getModTile("valve_handle"))
                .addTextureM(modRes("block/valve_handle/valve_handle_copper"), GemsRealm.res("block/c/valve_handle_copper_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("valve_handles"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("brittle"), Registries.BLOCK)
//                .addTag(modRes("kinetic_sources"), Registries.BLOCK/*, Registries.ITEM*/)
                .setTab(tab)
                //RECIPES: Manully created below
                .build();
        this.addEntry(valve_handle);

    }

    protected abstract Block newCasingBlock(MetalType metalType);
    protected abstract Block newEncasedShaftedBlock(MetalType metalType, Supplier<Block> casingBlock);
    protected abstract Block newEncasedCogwheelBlock(MetalType metalType, Supplier<Block> casingBlock);
    protected abstract Block newEncasedLargeCogwheelBlock(MetalType metalType, Supplier<Block> casingBlock);
    protected abstract Block newFunnelBlock(MetalType metalType, Supplier<Block> beltFunnelBlock);
    protected abstract Block newBeltFunnelBlock(MetalType metalType, Block funnelBlock);
//    protected abstract Block newTunnelBlock(MetalType metalType);

    protected abstract Block makeSlidingDoorBlock(MetalType metalType);
    protected abstract Block makeMetalLadderBlock(MetalType metalType);
    protected abstract Block makeMetalScaffoldingBlock(MetalType metalType);
    protected abstract Block makeWindow(MetalType metalType);
    protected abstract Block makeConnectedGlassPaneBlock(MetalType metalType);
    protected abstract Block newTableClothBlock(MetalType metalType);
    protected abstract Item newTableClothBlockItem(Block block, Item.Properties properties);
    protected abstract ValveHandleBlock newValveHandleBlock(MetalType metalType);

//!! ─────────────────────────────────────────────────────────────────────────────────────

    // RECIPES
    @Override
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) -> {

            String ladderRecipePath;
            String scaffoldingRecipePath;
            String shinglesRecipePath;
            String tilesRecipePath;
            String table_clothRecipePath;

            if (PlatHelper.getPlatform().isFabric()) {
                ladderRecipePath = "copper_ladder_from_copper_ingots_stonecutting";
                scaffoldingRecipePath = "copper_scaffolding_from_copper_ingots_stonecutting";
                shinglesRecipePath = "copper_shingles_from_copper_ingots_stonecutting";
                tilesRecipePath = "copper_tiles_from_copper_ingots_stonecutting";
                table_clothRecipePath = "copper_table_cloth_from_copper_ingots_stonecutting";
            }
            else {
                ladderRecipePath = "copper_ladder_from_ingots_copper_stonecutting";
                scaffoldingRecipePath = "copper_scaffolding_from_ingots_copper_stonecutting";
                shinglesRecipePath = "copper_shingles_from_ingots_copper_stonecutting";
                tilesRecipePath = "copper_tiles_from_ingots_copper_stonecutting";
                table_clothRecipePath = "copper_table_cloth_from_ingots_copper_stonecutting";
            }

            ladder.blocks.forEach((metalType, block) -> {
                ResourceLocation ladderRecipeId = ResourceLocation.parse(metalType.createFullIdWith(GemsRealm.MOD_ID, "", shortenedId(), "stonecutting/", "ladder_from_ingots"));
                ResourceLocation scaffoldingRecipeId = ResourceLocation.parse(metalType.createFullIdWith(GemsRealm.MOD_ID, "", shortenedId(), "stonecutting/", "scaffolding_from_ingots"));
                ResourceLocation shinglesRecipeId = ResourceLocation.parse(metalType.createFullIdWith(GemsRealm.MOD_ID, "", shortenedId(), "stonecutting/", "shingles_from_ingots"));
                ResourceLocation tilesRecipeId = ResourceLocation.parse(metalType.createFullIdWith(GemsRealm.MOD_ID, "", shortenedId(), "stonecutting/", "tiles_from_ingots"));
                ResourceLocation table_clothRecipeId = ResourceLocation.parse(metalType.createFullIdWith(GemsRealm.MOD_ID, "", shortenedId(), "stonecutting/", "_table_cloth_from_ingots"));

                grabTagAndCreateRecipe(ladderRecipePath, ladderRecipeId, "ingots/copper", ladder.blocks.get(metalType), metalType, manager, sink);

                grabTagAndCreateRecipe(scaffoldingRecipePath, scaffoldingRecipeId, "ingots/copper", scaffolding.blocks.get(metalType), metalType, manager, sink);

                grabTagAndCreateRecipe(shinglesRecipePath, shinglesRecipeId, "ingots/copper", shingles.blocks.get(metalType), metalType, manager, sink);

                grabTagAndCreateRecipe(tilesRecipePath, tilesRecipeId, "ingots/copper", tiles.blocks.get(metalType), metalType, manager, sink);

                /// NOT AVAILABLE IN FABRIC - will re-added when v6.0 is out for FABRIC
                grabTagAndCreateRecipe(table_clothRecipePath, table_clothRecipeId, "ingots/copper", table_cloth.blocks.get(metalType), metalType, manager, sink);
            });


            String pathLog = "item_application/copper_casing_from_log";
            String pathWood = "item_application/copper_casing_from_wood";
            casing.blocks.forEach((metalType, block) -> {
                ResourceLocation newResLocLog = ResourceLocation.parse(metalType.createFullIdWith(GemsRealm.MOD_ID, "", shortenedId(), "item_application/", "casing_from_log"));
                ResourceLocation newResLocWood = ResourceLocation.parse(metalType.createFullIdWith(GemsRealm.MOD_ID, "", shortenedId(), "item_application/", "casing_from_wood"));

                grabTagAndCreateRecipe(pathLog, newResLocLog, "ingots/copper", block, metalType, manager, sink);
                grabTagAndCreateRecipe(pathWood, newResLocWood, "ingots/copper", block, metalType, manager, sink);
            });

        });

        executor.accept((manager, sink) -> {
            String pathIngot = "pressing/iron_ingot";
            sheet.items.forEach((metalType, item) -> {
                String tagPathSheet = "plates/" + metalType.getTypeName();

                ResourceLocation newResLocIngot = ResourceLocation.parse(metalType.createFullIdWith(GemsRealm.MOD_ID, "", shortenedId(), "pressing/", "ingot"));

                grabTagAndCreateRecipe(pathIngot, newResLocIngot, "ingots/iron", item, metalType, manager, sink);
                addTagToAllItems(tagPathSheet, item, manager, sink);
            });

            String pathValveHandle = "crafting/kinetics/copper_valve_handle";
            valve_handle.blocks.forEach((metalType, block) -> {
                ResourceLocation newRecipeLoc = GemsRealm.res(pathValveHandle.replace("copper", metalType.getTypeName()))
                        .withPrefix(shortenedId() +"/"+ metalType.getNamespace());
                UtilityRecipe.createRecipeWithTag(modRes(pathValveHandle), newRecipeLoc,
                        platformTag("plates/copper").toString(),
                        platformTag("plates/" + metalType.getTypeName()).toString(),
                        block, sink, manager);
            });

            String pathFunnel = "crafting/logistics/brass_funnel";
            funnel.blocks.forEach((metalType, block) -> {
                ResourceLocation newRecipeLoc = GemsRealm.res(pathFunnel.replace("brass", metalType.getTypeName()))
                        .withPrefix(shortenedId() +"/"+ metalType.getNamespace());
                grabTagAndCreateRecipe(pathFunnel, newRecipeLoc, "ingots/brass", block, metalType, manager, sink);
            });
        });
    }

    public void grabTagAndCreateRecipe(String recipeLoc, ResourceLocation newRecipeLoc, String oldTagPath,
                                       Object newResult, MetalType metalType, ResourceManager manager, ResourceSink sink) {
        String tagPath = "ingots/" + typeName(metalType);

        Pair<ResourceLocation, Boolean> existingTagId = getATagId(
                platformTag(tagPath).toString(),
                platformTag(tagPath.replace("_", "")).toString(),
                manager);

        if (existingTagId.getSecond()) {
                UtilityRecipe.createRecipeWithTag(modRes(recipeLoc), newRecipeLoc, platformTag(oldTagPath).toString(),
                    existingTagId.getFirst().toString(), newResult, sink, manager);
        }
        else {
            ResourceLocation newTag = GemsRealm.res("ingots/" + metalType.getTypeName());
            boolean isTagCreated = UtilityTag.createAndAddCustomTags(newTag, sink, metalType.getItemOfThis(INGOT));
            UtilityRecipe.createRecipeWithTag(modRes(recipeLoc), newRecipeLoc, platformTag(oldTagPath).toString(),
                    newTag.toString(), newResult, sink, manager);

            if (!isTagCreated) GemsRealm.LOGGER.error("Failed to create a tag for {} in {}", newTag.toString(), Utils.getID(newResult));
        }

    }

    public String typeName(MetalType metalType) {
        return switch (metalType.getId().toString()) {
            case "crystalcraft_unlimited_java:adamantite" -> "adamantium";
            case "crystalcraft_unlimited_java:silicium" -> "silicon";
            case "crystalcraft_unlimited_java:pottasium" -> "potassium";
            case "crystalcraft_unlimited_java:hydro_pottasium" -> "hydratedpotassium";
            case "crystalcraft_unlimited_java:unoptanium" -> "unobtanium";
            case "ms:refined_quartz" -> "quartz";
            default -> metalType.getTypeName();
        };
    }

    public static void addTagToAllItems(String tagPath, Item item, ResourceManager manager, ResourceSink sink) {

        boolean isItemTagCreated = false;
        SimpleTagBuilder itemtagBuilder;

        Pair<ResourceLocation, Boolean> existingTag = getATagId(neoforgeTag(tagPath).toString(), fabricTag(tagPath).toString(), manager);

        if (existingTag.getSecond()) itemtagBuilder = SimpleTagBuilder.of(existingTag.getFirst());
        else itemtagBuilder = SimpleTagBuilder.of(platformTag(tagPath));

        if (Objects.nonNull(item)) {
            itemtagBuilder.addEntry(item);
            isItemTagCreated = true;
        }

        if (isItemTagCreated) sink.addTag(itemtagBuilder, Registries.ITEM);

    }

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                        UTilities                         │
    //      └──────────────────────────────────────────────────────────┘

    protected void putFoldingDoor(GemsRealmModule module, SimpleEntrySet<MetalType, Block> doors) {
        doors.blocks.forEach((metalType, block) -> {
            String path = metalType.createPathWith(module.shortenedId(), "door");
            FOLDING_DOORS.put(GemsRealm.res(path),
                    Couple.create(block(path + "/fold_left"), block(path + "/fold_right")));
        });
    }

    private static PartialModel block(String path) {
        return PartialModel.of(GemsRealm.res("block/"+ path));
    }

    /// Add encased blocks (create:encased_shaft) to ENCASED_VARIANTS so the create:casing can be applied to shaft or others
    protected <B extends Block & EncasableBlock> void registerEncasedBlock(B encaseable, SimpleEntrySet<MetalType, Block> entrySet) {
        entrySet.blocks.values().forEach(block -> EncasingRegistry.addVariant(encaseable, (Block & EncasedBlock) block));
    }

    // ─────────────────────────────────── Supplier ────────────────────────────────────
    protected Supplier<Block> beltFunnelSupplier(MetalType metalType) {
        return () -> belt_funnel.blocks.get(metalType);
    }
}
