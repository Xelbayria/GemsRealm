package net.xelbayria.gems_realm;

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

//        addIfLoaded("twigs", () -> TwigsModule::new);
//        addIfLoaded("create", () -> CreateModule::new); //!! TEMP until FABRIC version is updated to v6.0.0
//        addIfLoaded("bbb", () -> BuildingButBetterModule::new);
//        addIfLoaded("blockus", () -> BlockusModule::new);
//        addIfLoaded("stoneworks", () -> StoneworksModule::new);
//        addIfLoaded("quark", () -> QuarkModule::new);
//        addIfLoaded("quark", () -> QuarkMudModule::new);
//        addIfLoaded("betterarcheology", () -> BetterArcheologyModule::new);

//!! ====================================================== OTHERS ================================================== \\

    }
}
