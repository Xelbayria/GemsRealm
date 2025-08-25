package net.xelbayria.gems_realm.modules.forge.create;

import com.mojang.datafixers.util.Pair;
import com.simibubi.create.content.decoration.MetalLadderBlock;
import com.simibubi.create.content.decoration.MetalScaffoldingBlock;
import com.simibubi.create.content.decoration.MetalScaffoldingBlockItem;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.content.decoration.palettes.ConnectedGlassPaneBlock;
import com.simibubi.create.content.decoration.palettes.WindowBlock;
import com.simibubi.create.content.decoration.slidingDoor.SlidingDoorBlock;
import com.simibubi.create.content.decoration.slidingDoor.SlidingDoorBlockEntity;
import com.simibubi.create.content.decoration.slidingDoor.SlidingDoorRenderer;
import com.simibubi.create.content.logistics.tableCloth.TableClothBlock;
import com.simibubi.create.content.logistics.tableCloth.TableClothBlockEntity;
import com.simibubi.create.content.logistics.tableCloth.TableClothBlockItem;
import com.simibubi.create.content.logistics.tableCloth.TableClothRenderer;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.createmod.catnip.data.Couple;
import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.common_classes.RecipeUtility;
import net.mehvahdjukaar.every_compat.common_classes.TagUtility;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.api.GemsRealmEntrySet;
import net.xelbayria.gems_realm.api.GemsRealmModule;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.api.set.metal.VanillaMetalTypes;

import java.util.function.Consumer;

import static com.simibubi.create.AllPartialModels.FOLDING_DOORS;
import static net.xelbayria.gems_realm.api.set.metal.VanillaMetalChildKeys.INGOT;

//SUPPORT: v6.0.5
@SuppressWarnings({"removal", "CommentedOutCode"})
public class CreateModule extends GemsRealmModule {

    public final ItemOnlyEntrySet<MetalType, Item> sheet;
    public final SimpleEntrySet<MetalType, Block> casing;
    public final SimpleEntrySet<MetalType, Block> door;
    public final SimpleEntrySet<MetalType, Block> ladder;
    public final SimpleEntrySet<MetalType, Block> scaffolding;
    public final SimpleEntrySet<MetalType, Block> shingles;
    public final SimpleEntrySet<MetalType, Block> shingle_slab;
    public final SimpleEntrySet<MetalType, Block> shingle_stairs;
    public final SimpleEntrySet<MetalType, Block> tiles;
    public final SimpleEntrySet<MetalType, Block> tile_slab;
    public final SimpleEntrySet<MetalType, Block> tile_stairs;
    public final SimpleEntrySet<MetalType, Block> table_cloth;
//    public final SimpleEntrySet<MetalType, ValveHandleBlock> valve_handle; //@ Look at its comment for more details

    public final SimpleEntrySet<MetalType, Block> orante_window;
    public final SimpleEntrySet<MetalType, Block> ornate_window_pane;

    public CreateModule(String modId) {
        super(modId, "c");
        ResourceLocation tab = modRes("base");
        ResourceLocation paletteTab = modRes("palettes");

        sheet = ItemOnlyEntrySet.builder(MetalType.class, "sheet",
                        getModItem("iron_sheet"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Item(new Item.Properties())
                )
                .requiresChildren(INGOT) //REASON: recipes
                .addTexture(modRes("item/iron_sheet"))
                .addTag(new ResourceLocation("forge:plates"), Registries.ITEM)
                //TAG: forge:plates/<type>
                .setTabKey(tab)
                //RECIPES: Manully created below
                .build();
        this.addEntry(sheet);

        casing = GemsRealmEntrySet.of(MetalType.class, "casing",
                        getModBlock("copper_casing"), () -> VanillaMetalTypes.COPPER,
                        metalType -> new CasingBlock(Utils.copyPropertySafe(metalType.block)
                                .sound(metalType.getSound()))
                )
                .requiresChildren(INGOT) //REASON: recipes
                .addTextureM(modRes("block/copper_casing"), GemsRealm.res("block/c/copper_casing_m"))
                .addTextureM(modRes("block/copper_casing_connected"), GemsRealm.res("block/c/copper_casing_connected_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("casing"), Registries.BLOCK)
                .addTag(modRes("casing"), Registries.ITEM)
                .setTabKey(tab)
//                .defaultRecipe() //REQUIRED a unique recipe, create:deploying
                .build();
        this.addEntry(casing);

        door = GemsRealmEntrySet.of(MetalType.class, "door",
                        getModBlock("copper_door"), () -> VanillaMetalTypes.COPPER,
                        metalType -> SlidingDoorBlock.metal(Utils.copyPropertySafe(metalType.block)
                                        .sound(metalType.getSound())
                                        .noOcclusion(),
                                true)
                )
                .requiresFromMap(casing.blocks) //REASON: recipes
                .addTile(getModTile("sliding_door"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .generateBlockModels(true, modRes("block/copper_door/fold_left"), modRes("block/copper_door/fold_right"))
                .addTextureM(modRes("block/copper_door_bottom"), GemsRealm.res("block/c/copper_door_bottom_m"))
                .addTextureM(modRes("block/copper_door_side"), GemsRealm.res("block/c/copper_door_side_m"))
                .addTextureM(modRes("block/copper_door_top"), GemsRealm.res("block/c/copper_door_top_m"))
                .addTextureM(modRes("item/copper_door"), GemsRealm.res("item/c/copper_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("crafting/kinetics/copper_door"))
                .copyParentDrop()
                .build();
        this.addEntry(door);

        ladder = GemsRealmEntrySet.of(MetalType.class, "ladder",
                        getModBlock("copper_ladder"), () -> VanillaMetalTypes.COPPER,
                        metalType -> new MetalLadderBlock(Utils.copyPropertySafe(metalType.block))
                )
                .requiresChildren(INGOT) //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTexture(modRes("block/ladder_copper"))
                .addTexture(modRes("block/ladder_copper_hoop"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .addTag(BlockTags.FALL_DAMAGE_RESETTING, Registries.BLOCK)
                .addTag(modRes("copycat_deny"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(ladder);

        scaffolding = GemsRealmEntrySet.of(MetalType.class, "scaffolding",
                        getModBlock("copper_scaffolding"), () -> VanillaMetalTypes.COPPER,
                        metalType -> new MetalScaffoldingBlock(Utils.copyPropertySafe(metalType.block))
                )
                .requiresChildren(INGOT) //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTexture(modRes("block/scaffold/copper_scaffold"))
                .addTexture(modRes("block/scaffold/copper_scaffold_connected"))
                .addTexture(modRes("block/scaffold/copper_scaffold_inside"))
                .addTexture(modRes("block/scaffold/copper_scaffold_inside_connected"))
                .addTexture(modRes("block/funnel/copper_funnel_frame"))
                //TEXTURES: casing,
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addCustomItem((metalType, block, properties) -> new MetalScaffoldingBlockItem(block, properties))
                .build();
        this.addEntry(scaffolding);

        shingles = GemsRealmEntrySet.of(MetalType.class, "shingles",
                        getModBlock("copper_shingles"), () -> VanillaMetalTypes.COPPER,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
                )
                .requiresChildren(INGOT) //REASON: recipes
                .addTexture(modRes("block/copper/copper_shingles"))
                .addTexture(modRes("block/copper/copper_roof_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
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
                .setTabKey(tab)
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
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("copper_shingle_stairs_from_copper_shingles_stonecutting"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(shingle_stairs);

        tiles = GemsRealmEntrySet.of(MetalType.class, "tiles",
                        getModBlock("copper_tiles"), () -> VanillaMetalTypes.COPPER,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
                )
                .requiresChildren(INGOT) //REASON: recipes
                //TEXTURES: shingles' copper_roof_top
                .addTexture(modRes("block/copper/copper_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
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
                .setTabKey(tab)
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
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("copper_tile_stairs_from_copper_tiles_stonecutting"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(tile_stairs);

        table_cloth = GemsRealmEntrySet.of(MetalType.class, "table_cloth",
                        getModBlock("copper_table_cloth"), () -> VanillaMetalTypes.COPPER,
                        metalType -> new TableClothBlock(Utils.copyPropertySafe(metalType.block), metalType.getTypeName())
                )
                .addTile(getModTile("table_cloth"))
                .requiresChildren(INGOT) //REASON: recipes
                .addTextureM(modRes("block/table_cloth/copper"), GemsRealm.res("block/c/copper_table_cloth_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.INSIDE_STEP_SOUND_BLOCKS, Registries.BLOCK)
                .addTag(modRes("table_cloths"), Registries.BLOCK)
                .addTag(modRes("table_cloths"), Registries.ITEM)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addCustomItem((metalType, block, properties) -> new TableClothBlockItem(block, properties))
                .build();
        this.addEntry(table_cloth);

/// In ValveHandleVisual where the AllPartialModels.VALVE_HANDLE is setting ResourceLocation for copper's texture
/// one of options is to use mixin to change the ResourceLocation to replace copper's texture
//        valve_handle = GemsRealmEntrySet.of(MetalType.class, "valve_handle",
//                        getModBlock("copper_valve_handle", ValveHandleBlock.class), () -> VanillaMetalTypes.COPPER),
//                        metalType -> ValveHandleBlock.copper(Utils.copyPropertySafe(metalType.block))
//                )
//                .addTile(getModTile("valve_handle"))
//                .requiresFromMap(sheet.items) //REASON: recipes
//                .addTextureM(modRes("block/valve_handle/valve_handle_copper"), GemsRealm.res("block/c/valve_handle_copper_m"))
//                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
//                .addTag(modRes("valve_handles"), Registries.BLOCK)
//                .addTag(modRes("brittle"), Registries.BLOCK)
//                .addTag(modRes("valve_handles"), Registries.ITEM)
//                .setTabKey(tab)
//                //RECIPES: Manully created below
//                .addCustomItem((metalType, block, properties) -> new BlockItem(block, properties))
//                .build();
//        this.addEntry(valve_handle);

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
                .setTabKey(paletteTab)
                .defaultRecipe()
                .setRenderType(RenderLayer.TRANSLUCENT)
                .build();
        this.addEntry(orante_window);

        ornate_window_pane = GemsRealmEntrySet.of(MetalType.class, "window_pane", "ornate",
                        getModBlock("ornate_iron_window_pane"), () -> VanillaMetalTypes.IRON,
                        metalType -> new ConnectedGlassPaneBlock(Utils.copyPropertySafe(Blocks.GLASS_PANE))
                )
                .requiresFromMap(orante_window.blocks)
                //TEXTURES: orante_iron_window
                .addTexture(modRes("block/palettes/ornate_iron_window_pane_top"))
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(new ResourceLocation("forge:glass_panes"), Registries.BLOCK)
                .addTag(new ResourceLocation("forge:glass_panes"), Registries.ITEM)
                .setTabKey(paletteTab)
                .defaultRecipe()
                .setRenderType(RenderLayer.TRANSLUCENT)
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                .build();
        this.addEntry(ornate_window_pane);

    }

    private WindowBlock makeWindow(MetalType metalType) {
        return new WindowBlock(Utils.copyPropertySafe(Blocks.GLASS)
                .isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false), false);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        super.registerBlockEntityRenderers(event);

        event.register(table_cloth.getTile(TableClothBlockEntity.class), TableClothRenderer::new);
        event.register(door.getTile(SlidingDoorBlockEntity.class), SlidingDoorRenderer::new);
//        event.register(valve_handle.getTile(ValveHandleBlockEntity.class), HandCrankRenderer::new);
    }

//    @Override
//    public void onClientInit() {
//        super.onClientInit();
        //TODO: Add ponder to table_cloth and other blocks:
        // https://github.com/Creators-of-Create/Create/blob/65fcd2b71bd56e16d0447f235e9ea83af7b900a1/src/main/java/com/simibubi/create/CreateClient.java#L101
        // https://github.com/Creators-of-Create/Create/blob/65fcd2b71bd56e16d0447f235e9ea83af7b900a1/src/main/java/com/simibubi/create/infrastructure/ponder/AllCreatePonderScenes.java#L379
//    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void onClientSetup() {
        super.onClientSetup();

        CreateClientModule.registerCasingCTBehavior(this, casing);
        CreateClientModule.registerScaffoldCTBehavior(this, scaffolding);
        CreateClientModule.registerWindowCTBehavior(this, orante_window, ornate_window_pane);
    }

    @Override
    public void onModSetup() {
        super.onModSetup();
        putFoldingDoor(this, door);
    }

    private static void putFoldingDoor(GemsRealmModule module, SimpleEntrySet<MetalType, Block> doors) {
        doors.blocks.forEach((metalType, block) -> {
            String path = metalType.createPathWith(module.shortenedId(), "door");
            FOLDING_DOORS.put(GemsRealm.res(path),
                    Couple.create(block(path + "/fold_left"), block(path + "/fold_right")));
        });

    }

    private static PartialModel block(String path) {
        return PartialModel.of(GemsRealm.res("block/"+ path));
    }

    // RECIPES
    @Override
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) -> {

            String ladderRecipePath = "copper_ladder_from_ingots_copper_stonecutting";
            String scaffoldingRecipePath = "copper_scaffolding_from_ingots_copper_stonecutting";
            String shinglesRecipePath = "copper_shingles_from_ingots_copper_stonecutting";
            String tilesRecipePath = "copper_tiles_from_ingots_copper_stonecutting";
            String table_clothRecipePath = "copper_table_cloth_from_ingots_copper_stonecutting";
            ladder.blocks.forEach((metalType, block) -> {
                ResourceLocation ladderRecipeId = new ResourceLocation(metalType.createFullIdWith(GemsRealm.MOD_ID, "", shortenedId(), "stonecutting/", "_ladder_from_ingots"));
                ResourceLocation scaffoldingRecipeId = new ResourceLocation(metalType.createFullIdWith(GemsRealm.MOD_ID, "", shortenedId(), "stonecutting/", "_scaffolding_from_ingots"));
                ResourceLocation shinglesRecipeId = new ResourceLocation(metalType.createFullIdWith(GemsRealm.MOD_ID, "", shortenedId(), "stonecutting/", "_shingles_from_ingots"));
                ResourceLocation tilesRecipeId = new ResourceLocation(metalType.createFullIdWith(GemsRealm.MOD_ID, "", shortenedId(), "stonecutting/", "_tiles_from_ingots"));
                ResourceLocation table_clothRecipeId = new ResourceLocation(metalType.createFullIdWith(GemsRealm.MOD_ID, "", shortenedId(), "stonecutting/", "_table_cloth_from_ingots"));

                grabTagAndCreateRecipe(ladderRecipePath, ladderRecipeId, "copper", ladder.blocks.get(metalType), metalType, manager, sink);

                grabTagAndCreateRecipe(scaffoldingRecipePath, scaffoldingRecipeId, "copper", scaffolding.blocks.get(metalType), metalType, manager, sink);

                grabTagAndCreateRecipe(shinglesRecipePath, shinglesRecipeId, "copper", shingles.blocks.get(metalType), metalType, manager, sink);

                grabTagAndCreateRecipe(tilesRecipePath, tilesRecipeId, "copper", tiles.blocks.get(metalType), metalType, manager, sink);

                grabTagAndCreateRecipe(table_clothRecipePath, table_clothRecipeId, "copper", table_cloth.blocks.get(metalType), metalType, manager, sink);
            });


            String pathLog = "item_application/copper_casing_from_log";
            String pathWood = "item_application/copper_casing_from_wood";
            casing.blocks.forEach((metalType, block) -> {
                ResourceLocation newResLocLog = new ResourceLocation(metalType.createFullIdWith(GemsRealm.MOD_ID, "", shortenedId(), "item_application/", "casing_from_log"));
                ResourceLocation newResLocWood = new ResourceLocation(metalType.createFullIdWith(GemsRealm.MOD_ID, "", shortenedId(), "item_application/", "casing_from_wood"));

                grabTagAndCreateRecipe(pathLog, newResLocLog, "copper", block, metalType, manager, sink);
                grabTagAndCreateRecipe(pathWood, newResLocWood, "copper", block, metalType, manager, sink);
            });

//            String tagIdSheet = "forge:plates/";
            String pathIngot = "pressing/iron_ingot";
            sheet.items.forEach((metalType, item) -> {
                ResourceLocation newResLocIngot = new ResourceLocation(metalType.createFullIdWith(GemsRealm.MOD_ID, "", shortenedId(), "pressing/", "ingot"));

                grabTagAndCreateRecipe(pathIngot, newResLocIngot, "iron", item, metalType, manager, sink);

                //!! it's for valve_handle's recipe
//                TagUtility.createAndAddCustomTags(new ResourceLocation(tagIdSheet + typeName(metalType)), sink, item);
            });
        });
    }

    public void grabTagAndCreateRecipe(String recipeLoc, ResourceLocation newRecipeLoc, String oldTypeName, Object newResult, MetalType metalType, ResourceManager manager, ResourceSink sink) {
        String typeName = typeName(metalType);

        Pair<ResourceLocation, Boolean> newTagIngredient = TagUtility.getATagId(
                "forge:ingots/" + typeName,
                "forge:ingots/" + typeName.replace("_", ""),
                manager);

        if (newTagIngredient.getSecond()) {
                RecipeUtility.createRecipeWithTag(modRes(recipeLoc), newRecipeLoc, "forge:ingots/" + oldTypeName,
                    newTagIngredient.getFirst().toString(), newResult, sink, manager);

        }
        else GemsRealm.LOGGER.error("Failed to grab a tag for {}", Utils.getID(newResult));
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

}
