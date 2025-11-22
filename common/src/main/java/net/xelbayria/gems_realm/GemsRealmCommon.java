package net.xelbayria.gems_realm;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.xelbayria.gems_realm.modules.chipped.*;
import net.xelbayria.gems_realm.modules.minecraft.MinecraftModuleBars;
import net.xelbayria.gems_realm.modules.minecraft.MinecraftModuleNugget;
import net.xelbayria.gems_realm.modules.more_beautiful_torches.MoreBeautifulTorchesModuleC;
import net.xelbayria.gems_realm.modules.more_beautiful_torches.MoreBeautifulTorchesModuleD;
import net.xelbayria.gems_realm.modules.more_beautiful_torches.MoreBeautifulTorchesModuleG;
import net.xelbayria.gems_realm.modules.more_beautiful_torches.MoreBeautifulTorchesModuleM;

import java.util.Set;

import static net.mehvahdjukaar.every_compat.EveryCompat.addIfLoaded;
import static net.xelbayria.gems_realm.GemsRealm.addMultipleIfLoaded;

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

        if (isModLoadedAndRequireNUGGET()) addIfLoaded("minecraft", () -> MinecraftModuleNugget::new);
        if (isModLoadedAndRequireBARS()) addIfLoaded("minecraft", () -> MinecraftModuleBars::new);

        addMultipleIfLoaded("more_beautiful_torches", () -> MoreBeautifulTorchesModuleC::new, () -> MoreBeautifulTorchesModuleD::new, () -> MoreBeautifulTorchesModuleG::new, () -> MoreBeautifulTorchesModuleM::new);
//        addIfLoaded("twigs", () -> TwigsModule::new);
//        addIfLoaded("bbb", () -> BuildingButBetterModule::new);
        addMultipleIfLoaded("chipped", () -> ChippedModuleC::new, () -> ChippedModuleD::new, () -> ChippedModuleM::new, () -> ChippedModuleRBM::new, () -> ChippedModuleG::new);
//        addIfLoaded("blockus", () -> BlockusModule::new);
//        addIfLoaded("stoneworks", () -> StoneworksModule::new);
//        addIfLoaded("quark", () -> QuarkModule::new);
//        addIfLoaded("quark", () -> QuarkMudModule::new);
//        addIfLoaded("betterarcheology", () -> BetterArcheologyModule::new);

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
