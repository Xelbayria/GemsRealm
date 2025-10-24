package net.xelbayria.gems_realm.modules.fabric.create;

import com.simibubi.create.content.decoration.MetalLadderBlock;
import com.simibubi.create.content.decoration.MetalScaffoldingBlock;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.content.decoration.palettes.ConnectedGlassPaneBlock;
import com.simibubi.create.content.decoration.palettes.WindowBlock;
import com.simibubi.create.content.decoration.slidingDoor.SlidingDoorBlock;
import com.simibubi.create.content.decoration.slidingDoor.SlidingDoorBlockEntity;
import com.simibubi.create.content.decoration.slidingDoor.SlidingDoorRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.modules.create.CreateAbstractModule;

//SUPPORT: v6.0.5 //!! WAITING FOR THE RELEASE of v6.0-FABRIC
@SuppressWarnings({"CommentedOutCode"})
public class CreateModule extends CreateAbstractModule {


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

    protected WindowBlock makeWindow(MetalType metalType) {
        return new WindowBlock(Utils.copyPropertySafe(Blocks.GLASS)
                .isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false), false);
    }

    protected Block makeConnectedGlassPaneBlock(MetalType metalType) {
        return new ConnectedGlassPaneBlock(Utils.copyPropertySafe(Blocks.GLASS_PANE));
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        super.registerBlockEntityRenderers(event);

//        event.register(table_cloth.getTile(TableClothBlockEntity.class), TableClothRenderer::new); // NOT AVAILABLE
        event.register(door.getTile(SlidingDoorBlockEntity.class), SlidingDoorRenderer::new);
//        event.register(valve_handle.getTile(ValveHandleBlockEntity.class), HandCrankRenderer::new); // SEE EntrySet's comment
    }

//    @Override
//    public void onClientInit() {
//        super.onClientInit();
        //TODO: Add ponder to table_cloth and other blocks:
        // https://github.com/Creators-of-Create/Create/blob/65fcd2b71bd56e16d0447f235e9ea83af7b900a1/src/main/java/com/simibubi/create/CreateClient.java#L101
        // https://github.com/Creators-of-Create/Create/blob/65fcd2b71bd56e16d0447f235e9ea83af7b900a1/src/main/java/com/simibubi/create/infrastructure/ponder/AllCreatePonderScenes.java#L379
//    }

    @Override
    @Environment(EnvType.CLIENT)
    public void onClientSetup() {
        super.onClientSetup();

        CreateClientModule.registerCasingCTBehavior(this, casing);
        CreateClientModule.registerScaffoldCTBehavior(this, scaffolding);
        CreateClientModule.registerWindowCTBehavior(this, orante_window, ornate_window_pane);
    }

    /// NOT AVAILABLE in v0.5.1+ - will be re-enabled when v6.0+ is out for FABRIC
//    @Override
//    public void onModSetup() {
//        super.onModSetup();
//        putFoldingDoor(this, door);
//    }

//    private static void putFoldingDoor(GemsRealmModule module, SimpleEntrySet<MetalType, Block> doors) {
//        doors.blocks.forEach((metalType, block) -> {
//            String path = metalType.createPathWith(module.shortenedId(), "door");
//            FOLDING_DOORS.put(GemsRealm.res(path),
//                    Couple.create(block(path + "/fold_left"), block(path + "/fold_right")));
//        });
//    }

//    private static PartialModel block(String path) {
//        return PartialModel.of(GemsRealm.res("block/"+ path));
//    }

}
