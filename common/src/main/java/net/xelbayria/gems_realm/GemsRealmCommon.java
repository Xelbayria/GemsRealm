package net.xelbayria.gems_realm;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.xelbayria.gems_realm.modules.chipped.*;
import net.xelbayria.gems_realm.modules.minecraft.MinecraftModuleBars;
import net.xelbayria.gems_realm.modules.minecraft.MinecraftModuleNugget;
import net.xelbayria.gems_realm.modules.more_beautiful_torches.MoreBeautifulTorchesModuleC;
import net.xelbayria.gems_realm.modules.more_beautiful_torches.MoreBeautifulTorchesModuleD;
import net.xelbayria.gems_realm.modules.more_beautiful_torches.MoreBeautifulTorchesModuleG;
import net.xelbayria.gems_realm.modules.more_beautiful_torches.MoreBeautifulTorchesModuleM;
import net.xelbayria.gems_realm.modules.rechiseled.RechiseledModuleC;
import net.xelbayria.gems_realm.modules.rechiseled.RechiseledModuleD;
import net.xelbayria.gems_realm.modules.rechiseled.RechiseledModuleG;
import net.xelbayria.gems_realm.modules.rechiseled.RechiseledModuleM;

import java.util.Set;

import static net.mehvahdjukaar.every_compat.api.EveryCompatAPI.addIfLoaded;
import static net.mehvahdjukaar.every_compat.api.EveryCompatAPI.addMultipleIfLoaded;

public class GemsRealmCommon {

    protected void initialize() {
        GemsRealm.init();

        this.addModules();
    }

    protected void addModules() {

//!! =============================================== Add Other Compat Mods ========================================== \\

//        addOtherCompatMod("rechiseledcreate", "create", "rechiseled");
//        addOtherCompatMod("rechiseledae2", "ae2", "rechiseled");

//!! =================================================== Add Modules ================================================ \\

        if (isModLoadedAndRequireNUGGET()) addIfLoaded("minecraft", () -> MinecraftModuleNugget.class);
        if (isModLoadedAndRequireBARS()) addIfLoaded("minecraft", () -> MinecraftModuleBars.class);

        addMultipleIfLoaded("chipped", () -> ChippedModuleC.class, () -> ChippedModuleD.class, () -> ChippedModuleM.class, () -> ChippedModuleRBM.class, () -> ChippedModuleG.class);
        addMultipleIfLoaded("more_beautiful_torches", () -> MoreBeautifulTorchesModuleC.class, () -> MoreBeautifulTorchesModuleD.class, () -> MoreBeautifulTorchesModuleG.class, () -> MoreBeautifulTorchesModuleM.class);
        addMultipleIfLoaded("rechiseled", () -> RechiseledModuleC.class, () -> RechiseledModuleD.class, () -> RechiseledModuleG.class, () -> RechiseledModuleM.class);
//        addIfLoaded("twigs", () -> TwigsModule.class);
//        addIfLoaded("bbb", () -> BuildingButBetterModule.class);
//        addIfLoaded("blockus", () -> BlockusModule.class);
//        addIfLoaded("stoneworks", () -> StoneworksModule.class);
//        addIfLoaded("quark", () -> QuarkModule.class);
//        addIfLoaded("quark", () -> QuarkMudModule.class);
//        addIfLoaded("betterarcheology", () -> BetterArcheologyModule.class);

//!! ====================================================== OTHERS ================================================== \\

    }

    public boolean isModLoadedAndRequireNUGGET() {
        Set<String> list = Set.of(
                //requires nuggets
                "create", "mcwfences", "mcwbridges"
        );

        for (String modId : list) {
            boolean isInstalled = PlatHelper.isModLoaded(modId);
            if (isInstalled) return true;
        }
        return false;
    }
    public boolean isModLoadedAndRequireBARS() {
        Set<String> list = Set.of(
                //requires bars
                "chipped", "mcwbridges"
        );

        for (String modId : list) {
            boolean isInstalled = PlatHelper.isModLoaded(modId);
            if (isInstalled) return true;
        }
        return false;
    }
}
