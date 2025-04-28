package net.xelbayria.gems_realm.fabric;

import net.fabricmc.api.ModInitializer;
import net.xelbayria.gems_realm.GemsRealmCommon;

public class GemsRealmFabric extends GemsRealmCommon implements ModInitializer {

    @Override
    public void onInitialize() {
        this.initialize();
    }

    @Override
    protected void addModules() {
        super.addModules();

//!! =================================================== Add Modules ================================================ \\

        // Macaw's
//        addIfLoaded("mcwbridges", () -> MacawBridgesModule::new);
//        addIfLoaded("mcwfences", () -> MacawFencesModule::new);
//        addIfLoaded("mcwwindows", () -> MacawWindowsModule::new);
//        addIfLoaded("mcwroofs", () -> MacawRoofsModule::new);
//        addIfLoaded("mcwstairs", () -> MacawStairsModule::new);
//        addIfLoaded("mcwpaths", () -> MacawPathsModule::new);

        // General
//        addIfLoaded("rechiseled", () -> RechiseledModule::new);

        //!! TEMP - Until Fabric version is updated to v6.0.0
//        addIfLoaded("create", () -> CreateModule::new);

//!! ====================================================== OTHERS ================================================== \\

    }
}
