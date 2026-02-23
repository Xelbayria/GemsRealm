package net.xelbayria.gems_realm.modules.fabric.create;

import com.simibubi.create.CreateClient;
import com.simibubi.create.content.decoration.MetalScaffoldingCTBehaviour;
import com.simibubi.create.content.decoration.RoofBlockCTBehaviour;
import com.simibubi.create.foundation.block.connected.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.api.GemsRealmModule;
import net.xelbayria.gems_realm.api.set.metal.MetalType;

import static com.simibubi.create.foundation.block.connected.CTSpriteShifter.getCT;

@Environment(EnvType.CLIENT)
public class CreateClientModule {

    public static void registerWindowCTBehavior(GemsRealmModule module, SimpleEntrySet<MetalType, Block> window, SimpleEntrySet<MetalType, Block> window_pane) {
        window.blocks.forEach((metalType, block) -> {
            String blockTexture = metalType.createFullIdWith(GemsRealm.MOD_ID, "block", module.shortenedId(), "palettes/ornate_", "window");

            CTSpriteShiftEntry spriteShift = vertical(blockTexture, blockTexture + "_connected");

            CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(Utils.getID(block),
                    model -> new CTModel(model, new HorizontalCTBehaviour(spriteShift)));
            CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(Utils.getID(window_pane.blocks.get(metalType)),
                    model -> new CTModel(model, new GlassPaneCTBehaviour(spriteShift)));
        });
    }

    public static void registerCasingCTBehavior(GemsRealmModule module, SimpleEntrySet<MetalType, Block> casing) {
        casing.blocks.forEach((metalType, block) -> {
            String blockTexture = metalType.createFullIdWith(GemsRealm.MOD_ID, "block", module.shortenedId(), "", "casing");
            String connectedTexture = metalType.createFullIdWith(GemsRealm.MOD_ID, "block", module.shortenedId(), "", "casing_connected");

            CTSpriteShiftEntry spriteShift = omni(blockTexture, connectedTexture);

            CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(Utils.getID(block),
                    model -> new CTModel(model, new SimpleCTBehaviour(spriteShift)));

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

            CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(Utils.getID(block),
                    model -> new CTModel(model, new MetalScaffoldingCTBehaviour(scaffoldShift, scaffoldInsideShift, casingShift)));


        });
    }

    public static void registerShinglesCTBehavior(GemsRealmModule module, SimpleEntrySet<MetalType, Block> shingles) {
        shingles.blocks.forEach((metalType, block) -> {
            String topTexture = metalType.createFullIdWith(GemsRealm.MOD_ID, "block", module.shortenedId(), metalType.getTypeName()+"/", "roof_top");
            String topConnectedTexture = metalType.createFullIdWith(GemsRealm.MOD_ID, "block", module.shortenedId(), metalType.getTypeName()+"/", "shingles_top_connected");

            CTSpriteShiftEntry roofShift = getCT(AllCTTypes.ROOF, ResourceLocation.parse(topTexture), ResourceLocation.parse(topConnectedTexture));

            CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(Utils.getID(block),
                    model -> new CTModel(model, new RoofBlockCTBehaviour(roofShift)));

        });
    }

    public static void registerTilesCTBehavior(GemsRealmModule module, SimpleEntrySet<MetalType, Block> tiles) {
        tiles.blocks.forEach((metalType, block) -> {
            String topTexture = metalType.createFullIdWith(GemsRealm.MOD_ID, "block", module.shortenedId(), metalType.getTypeName()+"/", "roof_top");
            String topConnectedTexture = metalType.createFullIdWith(GemsRealm.MOD_ID, "block", module.shortenedId(), metalType.getTypeName()+"/", "tiles_top_connected");

            var roofShift = getCT(AllCTTypes.ROOF, ResourceLocation.parse(topTexture), ResourceLocation.parse(topConnectedTexture));

            CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(Utils.getID(block),
                    model -> new CTModel(model, new RoofBlockCTBehaviour(roofShift)));
        });
    }

    private static CTSpriteShiftEntry omni(String blockTexture, String connectedTexture) {
        return getCT(AllCTTypes.OMNIDIRECTIONAL, ResourceLocation.parse(blockTexture), ResourceLocation.parse(connectedTexture));
    }


    private static CTSpriteShiftEntry horizontal(String blockTexture, String connectedTexture) {
        return getCT(AllCTTypes.HORIZONTAL, ResourceLocation.parse(blockTexture), ResourceLocation.parse(connectedTexture));
    }

    private static CTSpriteShiftEntry vertical(String blockTexture, String connectedTexture) {
        return getCT(AllCTTypes.VERTICAL, ResourceLocation.parse(blockTexture), ResourceLocation.parse(connectedTexture));
    }

}
