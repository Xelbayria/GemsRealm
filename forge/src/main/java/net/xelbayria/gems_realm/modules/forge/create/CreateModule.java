package net.xelbayria.gems_realm.modules.forge.create;

import com.simibubi.create.content.decoration.MetalLadderBlock;
import com.simibubi.create.content.decoration.MetalScaffoldingBlock;
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
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.api.GemsRealmEntrySet;
import net.xelbayria.gems_realm.api.GemsRealmModule;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.api.set.metal.VanillaMetalTypes;
import net.xelbayria.gems_realm.modules.create.CreateAbstractModule;

import java.util.function.Consumer;

import static com.simibubi.create.AllPartialModels.FOLDING_DOORS;
import static net.xelbayria.gems_realm.api.set.metal.VanillaMetalChildKeys.INGOT;

//SUPPORT: v6.0.5
@SuppressWarnings({"removal"})
public class CreateModule extends CreateAbstractModule {

//    public final ItemOnlyEntrySet<MetalType, Item> sheet;
//    public final SimpleEntrySet<MetalType, Block> casing;
//    public final SimpleEntrySet<MetalType, Block> door;
//    public final SimpleEntrySet<MetalType, Block> ladder;
//    public final SimpleEntrySet<MetalType, Block> scaffolding;
//    public final SimpleEntrySet<MetalType, Block> shingles;
//    public final SimpleEntrySet<MetalType, Block> shingle_slab;
//    public final SimpleEntrySet<MetalType, Block> shingle_stairs;
//    public final SimpleEntrySet<MetalType, Block> tiles;
//    public final SimpleEntrySet<MetalType, Block> tile_slab;
//    public final SimpleEntrySet<MetalType, Block> tile_stairs;
    public final SimpleEntrySet<MetalType, Block> table_cloth;
//    public final SimpleEntrySet<MetalType, ValveHandleBlock> valve_handle; //@ Look at its comment for more details

//    public final SimpleEntrySet<MetalType, Block> orante_window;
//    public final SimpleEntrySet<MetalType, Block> ornate_window_pane;

    public CreateModule(String modId) {
        super(modId);
        ResourceLocation tab = modRes("base");
        ResourceLocation paletteTab = modRes("palettes");

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

    }

    protected Block makeCasingBlock(MetalType metalType) {
        return new CasingBlock(Utils.copyPropertySafe(metalType.block)
                .sound(metalType.getSound()));
    }

    protected Block makeSlidingDoorBlock(MetalType metalType) {
        return SlidingDoorBlock.metal(Utils.copyPropertySafe(metalType.block)
                        .sound(metalType.getSound())
                        .noOcclusion(),
                true);
    }

    protected Block makeMetalLadderBlock(MetalType metalType) {
        return new MetalLadderBlock(Utils.copyPropertySafe(metalType.block));
    }

    protected Block makeMetalScaffoldingBlock(MetalType metalType) {
        return new MetalScaffoldingBlock(Utils.copyPropertySafe(metalType.block));
    }

    protected WindowBlock makeWindow(MetalType metalType) {
        return new WindowBlock(Utils.copyPropertySafe(Blocks.GLASS)
                .isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false), false);
    }

    protected Block makeConnectedGlassPaneBlock(MetalType metalType) {
        return new ConnectedGlassPaneBlock(Utils.copyPropertySafe(Blocks.GLASS_PANE));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        super.registerBlockEntityRenderers(event);

        event.register(table_cloth.getTile(TableClothBlockEntity.class), TableClothRenderer::new);
        event.register(door.getTile(SlidingDoorBlockEntity.class), SlidingDoorRenderer::new);
//        event.register(valve_handle.getTile(ValveHandleBlockEntity.class), HandCrankRenderer::new); // SEE EntrySet's comment
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void onClientSetup() {
        super.onClientSetup();

        CreateClientModule.registerCasingCTBehavior(this, casing);
        CreateClientModule.registerScaffoldCTBehavior(this, scaffolding);
        CreateClientModule.registerWindowCTBehavior(this, orante_window, ornate_window_pane);
        // it was partially working but why is it not behaving like the copper's connected_textures?
//        CreateClientModule.registerTilesCTBehavior(this, tiles);
//        CreateClientModule.registerShinglesCTBehavior(this, shingles);
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

            String table_clothRecipePath = "copper_table_cloth_from_ingots_copper_stonecutting";
            ladder.blocks.forEach((metalType, block) -> {
                ResourceLocation table_clothRecipeId = new ResourceLocation(metalType.createFullIdWith(GemsRealm.MOD_ID, "", shortenedId(), "stonecutting/", "_table_cloth_from_ingots"));

                grabTagAndCreateRecipe(table_clothRecipePath, table_clothRecipeId, "copper", table_cloth.blocks.get(metalType), metalType, manager, sink);
            });

        });
    }

}
