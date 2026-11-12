package net.xelbayria.gems_realm.fabric;

import net.mehvahdjukaar.every_compat.api.PluginEvent;
import net.xelbayria.gems_realm.GemsRealmCommonPlugin;
import net.xelbayria.gems_realm.modules.fabric.macaws.MacawBridgesModule;
import net.xelbayria.gems_realm.modules.fabric.macaws.MacawFencesModule;
import net.xelbayria.gems_realm.modules.fabric.macaws.MacawWindowsModule;

public class GemsRealmFabricPlugin extends GemsRealmCommonPlugin {

    @Override
    public void register(PluginEvent event) {
        super.register(event);

        // Macaw's
        event.addModule("mcwbridges", () -> MacawBridgesModule.class);
        event.addModule("mcwfences", () -> MacawFencesModule.class);
        event.addModule("mcwwindows", () -> MacawWindowsModule.class);

        // General
//        event.addModule("create", () -> CreateModule.class); //!! NOT AVAILABLE YET
    }
}
