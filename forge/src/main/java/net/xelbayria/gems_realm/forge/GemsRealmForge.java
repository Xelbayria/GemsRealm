package net.xelbayria.gems_realm.forge;

import net.minecraftforge.fml.common.Mod;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.GemsRealmCommon;
import net.xelbayria.gems_realm.modules.forge.macaws.MacawBridgesModule;
import net.xelbayria.gems_realm.modules.forge.macaws.MacawFencesModule;
import net.xelbayria.gems_realm.modules.forge.macaws.MacawWindowsModule;

import static net.mehvahdjukaar.every_compat.EveryCompat.addIfLoaded;

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
        addIfLoaded("mcwbridges", () -> MacawBridgesModule::new);
        addIfLoaded("mcwwindows", () -> MacawWindowsModule::new);
        addIfLoaded("mcwfences", () -> MacawFencesModule::new);
//        addIfLoaded("mcwroofs", () -> MacawRoofsModule::new);
//        addIfLoaded("mcwstairs", () -> MacawStairsModule::new);
//        addIfLoaded("mcwpaths", () -> MacawPathsModule::new);

        // General
//        addIfLoaded("create", () -> CreateModule::new);
//        addIfLoaded("rechiseled", () -> RechiseledModule::new);
//        addIfLoaded("stonechest", () -> StoneChestModule::new);


//!! ====================================================== OTHERS ================================================== \\

    }

}
