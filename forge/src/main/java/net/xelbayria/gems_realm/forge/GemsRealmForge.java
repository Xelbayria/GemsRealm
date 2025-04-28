package net.xelbayria.gems_realm.forge;

import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.GemsRealmCommon;
import net.minecraftforge.fml.common.Mod;

/**
 * Author: Xel'Bayria
 */
@Mod(GemsRealm.MOD_ID)
public class GemsRealmForge extends GemsRealmCommon {

    public GemsRealmForge() {
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
//        addIfLoaded("stonechest", () -> StoneChestModule::new);

        //!! TEMP - Until Fabric version is updated to v6.0.0
//        addIfLoaded("create", () -> CreateModule::new);

//!! ====================================================== OTHERS ================================================== \\

    }

}
