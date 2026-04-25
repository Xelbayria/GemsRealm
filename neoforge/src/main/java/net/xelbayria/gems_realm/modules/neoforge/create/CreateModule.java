package net.xelbayria.gems_realm.modules.neoforge.create;

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
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.api.GemsRealmModule;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.modules.create.CreateModuleAbstract;

import java.util.function.Consumer;

import static com.simibubi.create.AllPartialModels.FOLDING_DOORS;

//SUPPORT: v6.0.5
@SuppressWarnings({"removal"})
public class CreateModule extends CreateModuleAbstract {

    public CreateModule(String modId) {
        super(modId);
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

    protected Block makeWindow(MetalType metalType) {
        return new WindowBlock(Utils.copyPropertySafe(Blocks.GLASS)
                .isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false), false);
    }

    protected Block makeConnectedGlassPaneBlock(MetalType metalType) {
        return new ConnectedGlassPaneBlock(Utils.copyPropertySafe(Blocks.GLASS_PANE));
    }

    @Override
    protected Block newTableClothBlock(MetalType metalType) {
        return new TableClothBlock(Utils.copyPropertySafe(metalType.block), metalType.getTypeName());
    }

    @Override
    protected Item newTableClothBlockItem(Block block, Item.Properties properties) {
        return new TableClothBlockItem(block, properties);
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

            String table_clothRecipePath = "copper_table_cloth_from_ingots_copper_stonecutting"; //NOTE: this path is different from FABRIC's recipe path
            ladder.blocks.forEach((metalType, block) -> {
                ResourceLocation newTableClothRecipeId = ResourceLocation.parse(metalType.createFullIdWith(GemsRealm.MOD_ID, "", shortenedId(), "stonecutting/", "_table_cloth_from_ingots"));

                grabTagAndCreateRecipe(table_clothRecipePath, newTableClothRecipeId, "copper", table_cloth.blocks.get(metalType), metalType, manager, sink);
            });

        });
    }

}
