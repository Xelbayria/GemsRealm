package net.xelbayria.gems_realm.modules.neoforge.create;

import com.github.talrey.createdeco.connected.CatwalkCTBehaviour;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.RotatedPillarCTBehaviour;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.xelbayria.gems_realm.GemsRealm;

@OnlyIn(Dist.CLIENT)
public class CreateDecoClientModule extends CreateClientModule {

    public static void registerCatwalkCTBehavior(CreateDecoModule module) {
        module.catwalk.blocks.forEach((metalType, block) -> {
            String blockTexture = metalType.createFullIdWith(GemsRealm.MOD_ID, "block", module.shortenedId(),
                    "palettes/catwalks/", "catwalk"
            );

            CTSpriteShiftEntry catwalkShift = omni(blockTexture);

            registerCTBehviour(block, new CatwalkCTBehaviour(catwalkShift));
        });
    }

    public static void registerSheetMetalCTBehavior(CreateDecoModule module) {
        module.sheet_metal.blocks.forEach((metalType, block) -> {
            String blockTexture = metalType.createFullIdWith(GemsRealm.MOD_ID, "block", module.shortenedId(),
                    "palettes/sheet_metal/", "sheet_metal"
            );

            CTSpriteShiftEntry catwalkShift = vertical(blockTexture);

            registerCTBehviour(block, new RotatedPillarCTBehaviour(catwalkShift, null));
        });
    }


}
