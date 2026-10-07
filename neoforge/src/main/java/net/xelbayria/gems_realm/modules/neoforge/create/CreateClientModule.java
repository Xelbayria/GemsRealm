package net.xelbayria.gems_realm.modules.neoforge.create;

import com.simibubi.create.CreateClient;
import com.simibubi.create.content.decoration.MetalScaffoldingCTBehaviour;
import com.simibubi.create.content.decoration.encasing.EncasedCTBehaviour;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogCTBehaviour;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedShaftBlock;
import com.simibubi.create.foundation.block.connected.*;
import net.createmod.catnip.data.Couple;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.api.GemsRealmModule;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.modules.neoforge.create.client.CompatRoofBlockCTBehaviour;
import net.xelbayria.gems_realm.modules.neoforge.create.client.CompatTunnelCTBehaviour;

import static com.simibubi.create.foundation.block.connected.CTSpriteShifter.getCT;

@OnlyIn(Dist.CLIENT)
public class CreateClientModule {

    public static void registerWindowCTBehavior(GemsRealmModule module, SimpleEntrySet<MetalType, Block> window, SimpleEntrySet<MetalType, Block> window_pane) {
        window.blocks.forEach((metalType, block) -> {
            String textureId = metalType.createFullIdWith(GemsRealm.MOD_ID, "block", module.shortenedId(), "palettes/ornate_", "window");

            CTSpriteShiftEntry shiftEntry = vertical(textureId);

            registerCTBehviour(block, new HorizontalCTBehaviour(shiftEntry));
            registerCTBehviour(window_pane.blocks.get(metalType), new GlassPaneCTBehaviour(shiftEntry));
        });
    }

    public static void registerCasingCTBehavior(GemsRealmModule module,
                                                SimpleEntrySet<MetalType, Block> casing_entry,
                                                SimpleEntrySet<MetalType, Block> encasedShaft_entry,
                                                SimpleEntrySet<MetalType, Block> encasedCogwheel_entry,
                                                SimpleEntrySet<MetalType, Block> encasedLargeCogwheel_entry

    ) {
        casing_entry.blocks.forEach((metalType, block) -> {
            String casingId = metalType.createFullIdWith(GemsRealm.MOD_ID, "block", module.shortenedId(), "", "casing");
            String cogwheel_sideId = metalType.createFullIdWith(GemsRealm.MOD_ID, "block", module.shortenedId(), "", "encased_cogwheel_side");

            CTSpriteShiftEntry casingShift = omni(casingId);
            CTSpriteShiftEntry encasedCogwheelVertical = vertical(cogwheel_sideId);
            CTSpriteShiftEntry encasedCogwheelHorizontal = horizontal(cogwheel_sideId);

            /// CASING
            registerCTBehviour(block, new EncasedCTBehaviour(casingShift));

            CreateClient.CASING_CONNECTIVITY.makeCasing(block, casingShift);

            /// ENCASED_SHAFT
            Block encasedBlock = encasedShaft_entry.blocks.get(metalType);

            registerCTBehviour(encasedBlock, new EncasedCTBehaviour(casingShift));
            CreateClient.CASING_CONNECTIVITY.make(encasedBlock, casingShift,
                    (blockState, face) -> face.getAxis() != blockState.getValue(EncasedShaftBlock.AXIS));

            /// ENCASED_COGWHEEL
            Block encasedCogwheelBlock = encasedCogwheel_entry.blocks.get(metalType);

            registerCTBehviour(encasedCogwheelBlock, new EncasedCogCTBehaviour(casingShift,
                    Couple.create(encasedCogwheelVertical, encasedCogwheelHorizontal)));

            CreateClient.CASING_CONNECTIVITY.make(encasedCogwheelBlock, casingShift,
                    (blockState, face) ->
                            face.getAxis() == blockState.getValue(EncasedCogwheelBlock.AXIS)
                                    && !blockState.getValue(face.getAxisDirection() == Direction.AxisDirection.POSITIVE ? EncasedCogwheelBlock.TOP_SHAFT
                                    : EncasedCogwheelBlock.BOTTOM_SHAFT));

            /// ENCASED_LARGE_COGWHEEL
            Block encasedLargCogwheelBlock = encasedLargeCogwheel_entry.blocks.get(metalType);

            registerCTBehviour(encasedLargCogwheelBlock, new EncasedCogCTBehaviour(casingShift));

            CreateClient.CASING_CONNECTIVITY.make(encasedLargCogwheelBlock, casingShift,
                    (blockState, face) ->
                            face.getAxis() == blockState.getValue(EncasedCogwheelBlock.AXIS)
                            && !blockState.getValue(face.getAxisDirection() == Direction.AxisDirection.POSITIVE ? EncasedCogwheelBlock.TOP_SHAFT
                            : EncasedCogwheelBlock.BOTTOM_SHAFT));

        });
    }

    public static void registerTunnelCTBehaviour(GemsRealmModule module, SimpleEntrySet<MetalType, Block> tunnelEntry) {
        tunnelEntry.blocks.forEach((metalType, block) -> {
            String topTextureId = metalType.createFullIdWith(GemsRealm.MOD_ID, "block", module.shortenedId(), "tunnel/", "tunnel_top");

            CTSpriteShiftEntry tunnelTopShift = vertical(topTextureId);

            registerCTBehviour(block, new CompatTunnelCTBehaviour(tunnelTopShift));
        });
    }

    public static void registerScaffoldCTBehavior(GemsRealmModule module, SimpleEntrySet<MetalType, Block> scaffold) {
        scaffold.blocks.forEach((metalType, block) -> {
            // Scaffold
            String scaffoldTexture = metalType.createFullIdWith(GemsRealm.MOD_ID, "block", module.shortenedId(), "scaffold/", "scaffold");
            String insideTexture = metalType.createFullIdWith(GemsRealm.MOD_ID, "block", module.shortenedId(), "scaffold/", "scaffold_inside");
            String scaffoldCTM = metalType.createFullIdWith(GemsRealm.MOD_ID, "block", module.shortenedId(), "scaffold/", "scaffold_connected");
            String insideCTM = metalType.createFullIdWith(GemsRealm.MOD_ID, "block", module.shortenedId(), "scaffold/", "scaffold_inside_connected");

            // Casing
            String casingTexture = metalType.createFullIdWith(GemsRealm.MOD_ID, "block", module.shortenedId(), "", "casing");
            String casingCTM = metalType.createFullIdWith(GemsRealm.MOD_ID, "block", module.shortenedId(), "", "casing_connected");

            CTSpriteShiftEntry scaffoldShift = horizontal(scaffoldTexture, scaffoldCTM);
            CTSpriteShiftEntry scaffoldInsideShift = horizontal(insideTexture, insideCTM);
            CTSpriteShiftEntry casingShift = omni(casingTexture, casingCTM);

            registerCTBehviour(block, new MetalScaffoldingCTBehaviour(scaffoldShift, scaffoldInsideShift, casingShift));

//            CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(Utils.getID(block),
//                    model -> new CTModel(model, new MetalScaffoldingCTBehaviour(scaffoldShift, scaffoldInsideShift, casingShift)));
        });
    }

    public static void registerShinglesCTBehavior(GemsRealmModule module, SimpleEntrySet<MetalType, Block> shingles) {
        shingles.blocks.forEach((metalType, block) -> {
            String topTexture = metalType.createFullIdWith(GemsRealm.MOD_ID, "block", module.shortenedId(), metalType.getTypeName()+"/", "roof_top");
            String topConnectedTexture = metalType.createFullIdWith(GemsRealm.MOD_ID, "block", module.shortenedId(), metalType.getTypeName()+"/", "shingles_top");

            CTSpriteShiftEntry roofShift = roof(topTexture, topConnectedTexture);

            registerCTBehviour(block, new CompatRoofBlockCTBehaviour(metalType, roofShift));


        });
    }

    public static void registerTilesCTBehavior(GemsRealmModule module, SimpleEntrySet<MetalType, Block> tiles) {
        tiles.blocks.forEach((metalType, block) -> {
            String topTexture = metalType.createFullIdWith(GemsRealm.MOD_ID, "block", module.shortenedId(), metalType.getTypeName()+"/", "roof_top");
            String topConnectedTexture = metalType.createFullIdWith(GemsRealm.MOD_ID, "block", module.shortenedId(), metalType.getTypeName()+"/", "tiles_top");

            CTSpriteShiftEntry roofShift = roof(topTexture, topConnectedTexture);

            registerCTBehviour(block, new CompatRoofBlockCTBehaviour(metalType, roofShift));

        });
    }


    ///NOTE: texture filename with _connected is included
    private static CTSpriteShiftEntry omni(String blockTexture) {
        return getCT(AllCTTypes.OMNIDIRECTIONAL, ResourceLocation.parse(blockTexture), ResourceLocation.parse(blockTexture + "_connected"));
    }

    private static CTSpriteShiftEntry omni(String blockTexture, String connectedTexture) {
        return getCT(AllCTTypes.OMNIDIRECTIONAL, ResourceLocation.parse(blockTexture), ResourceLocation.parse(connectedTexture));
    }

    ///NOTE: texture filename with _connected is included
    private static CTSpriteShiftEntry horizontal(String blockTexture) {
        return getCT(AllCTTypes.HORIZONTAL, ResourceLocation.parse(blockTexture), ResourceLocation.parse(blockTexture +"_connected"));
    }

    private static CTSpriteShiftEntry horizontal(String blockTexture, String connectedTexture) {
        return getCT(AllCTTypes.HORIZONTAL, ResourceLocation.parse(blockTexture), ResourceLocation.parse(connectedTexture));
    }

    ///NOTE: texture filename with _connected is included
    private static CTSpriteShiftEntry vertical(String blockTexture) {
        return getCT(AllCTTypes.VERTICAL, ResourceLocation.parse(blockTexture), ResourceLocation.parse(blockTexture + "_connected"));
    }

    ///NOTE: texture filename with _connected is included
    private static CTSpriteShiftEntry roof(String blockTexture, String connectedTexture) {
        return getCT(AllCTTypes.ROOF, ResourceLocation.parse(blockTexture), ResourceLocation.parse(connectedTexture + "_connected"));
    }

    private static void registerCTBehviour(Block entry, ConnectedTextureBehaviour behavior) {
        CreateClient.MODEL_SWAPPER.getCustomBlockModels()
                .register(Utils.getID(entry), model -> new CTModel(model, behavior));
    }
}
