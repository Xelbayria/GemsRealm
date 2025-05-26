package net.xelbayria.gems_realm;

import net.xelbayria.gems_realm.modules.minecraft.MinecraftModule;

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

        addIfLoaded("minecraft", () -> MinecraftModule::new);
//        addIfLoaded("twigs", () -> TwigsModule::new);
//        addIfLoaded("bbb", () -> BuildingButBetterModule::new);
//        addIfLoaded("blockus", () -> BlockusModule::new);
//        addIfLoaded("stoneworks", () -> StoneworksModule::new);
//        addIfLoaded("quark", () -> QuarkModule::new);
//        addIfLoaded("quark", () -> QuarkMudModule::new);
//        addIfLoaded("betterarcheology", () -> BetterArcheologyModule::new);

//!! ====================================================== OTHERS ================================================== \\

    }
}
