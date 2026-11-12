package net.xelbayria.gems_realm;

import net.mehvahdjukaar.every_compat.api.EveryCompatPlugin;
import net.mehvahdjukaar.every_compat.api.PluginEvent;
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

public class GemsRealmCommonPlugin implements EveryCompatPlugin {

    @Override
    public void register(PluginEvent event) {

//!! =============================================== Add Other Compat Mods ========================================== \\

//        addOtherCompatMod("rechiseledcreate", "create", "rechiseled");
//        addOtherCompatMod("rechiseledae2", "ae2", "rechiseled");

//!! =================================================== Add Modules ================================================ \\

        if (isModLoadedAndRequireNUGGET()) event.addModule("minecraft", () -> MinecraftModuleNugget.class);
        if (isModLoadedAndRequireBARS()) event.addModule("minecraft", () -> MinecraftModuleBars.class);

        event.addModules("chipped", () -> ChippedModuleC.class, () -> ChippedModuleD.class, () -> ChippedModuleM.class, () -> ChippedModuleRBM.class, () -> ChippedModuleG.class);
        event.addModules("more_beautiful_torches", () -> MoreBeautifulTorchesModuleC.class, () -> MoreBeautifulTorchesModuleD.class, () -> MoreBeautifulTorchesModuleG.class, () -> MoreBeautifulTorchesModuleM.class);
        event.addModules("rechiseled", () -> RechiseledModuleC.class, () -> RechiseledModuleD.class, () -> RechiseledModuleG.class, () -> RechiseledModuleM.class);
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
                "chipped", "mcwbridges", "createdeco"
        );

        for (String modId : list) {
            boolean isInstalled = PlatHelper.isModLoaded(modId);
            if (isInstalled) return true;
        }
        return false;
    }
}
