package net.xelbayria.gems_realm;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.xelbayria.gems_realm.modules.minecraft.MinecraftModule;

import java.util.Set;

import static net.mehvahdjukaar.every_compat.EveryCompat.addIfLoaded;
import static net.mehvahdjukaar.every_compat.EveryCompat.addOtherCompatMod;

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

        if (isModLoaded()) addIfLoaded("minecraft", () -> MinecraftModule::new);

//        addIfLoaded("twigs", () -> TwigsModule::new);
//        addIfLoaded("bbb", () -> BuildingButBetterModule::new);
//        addIfLoaded("blockus", () -> BlockusModule::new);
//        addIfLoaded("stoneworks", () -> StoneworksModule::new);
//        addIfLoaded("quark", () -> QuarkModule::new);
//        addIfLoaded("quark", () -> QuarkMudModule::new);
//        addIfLoaded("betterarcheology", () -> BetterArcheologyModule::new);

//!! ====================================================== OTHERS ================================================== \\

    }

    public boolean isModLoaded() {
        Set<String> list = Set.of(
                //requires nuggets
                "create", "mcwfences",
                //requires nuggets, bars
                "mcwbridges"
        );

        for (String modId : list) {
            boolean isInstalled = PlatHelper.isModLoaded(modId);
            if (isInstalled) return true;
        }
        return false;
    }
}
