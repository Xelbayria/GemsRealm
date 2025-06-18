package net.xelbayria.gems_realm.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.xelbayria.gems_realm.GemsRealmClient;
import net.xelbayria.gems_realm.GemsRealmCommon;
import net.xelbayria.gems_realm.modules.fabric.macaws.MacawBridgesModule;
import net.xelbayria.gems_realm.modules.fabric.macaws.MacawFencesModule;
import net.xelbayria.gems_realm.modules.fabric.macaws.MacawWindowsModule;

import static net.mehvahdjukaar.every_compat.EveryCompat.addIfLoaded;

public class GemsRealmFabric extends GemsRealmCommon implements ModInitializer {

    @Override
    public void onInitialize() {
        this.initialize();

        if (PlatHelper.getPhysicalSide().isClient()) {
            ItemTooltipCallback.EVENT.register(GemsRealmClient::onItemTooltip);
        }
    }

    @Override
    protected void addModules() {
        super.addModules();

//!! =================================================== Add Modules ================================================ \\

        // Macaw's
        addIfLoaded("mcwbridges", () -> MacawBridgesModule::new);
        addIfLoaded("mcwfences", () -> MacawFencesModule::new);
        addIfLoaded("mcwwindows", () -> MacawWindowsModule::new);

        // General
//        addIfLoaded("rechiseled", () -> RechiseledModule::new);

//        addIfLoaded("create", () -> CreateModule::new);

//!! ====================================================== OTHERS ================================================== \\

    }
}
