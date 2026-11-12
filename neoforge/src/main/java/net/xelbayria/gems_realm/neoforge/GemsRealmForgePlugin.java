package net.xelbayria.gems_realm.neoforge;

import net.mehvahdjukaar.every_compat.api.ECPlugin;
import net.mehvahdjukaar.every_compat.api.PluginEvent;
import net.xelbayria.gems_realm.GemsRealmCommonPlugin;
import net.xelbayria.gems_realm.modules.neoforge.create.CreateDecoModule;
import net.xelbayria.gems_realm.modules.neoforge.create.CreateModule;
import net.xelbayria.gems_realm.modules.neoforge.lapidarist.LapidaristModuleC;
import net.xelbayria.gems_realm.modules.neoforge.lapidarist.LapidaristModuleD;
import net.xelbayria.gems_realm.modules.neoforge.lapidarist.LapidaristModuleG;
import net.xelbayria.gems_realm.modules.neoforge.lapidarist.LapidaristModuleM;
import net.xelbayria.gems_realm.modules.neoforge.macaws.MacawBridgesModule;
import net.xelbayria.gems_realm.modules.neoforge.macaws.MacawFencesModule;
import net.xelbayria.gems_realm.modules.neoforge.macaws.MacawWindowsModule;
import net.xelbayria.gems_realm.modules.neoforge.more_beautiful_bookshelves.MoreBeautifulBookshelvesModuleC;
import net.xelbayria.gems_realm.modules.neoforge.more_beautiful_bookshelves.MoreBeautifulBookshelvesModuleD;
import net.xelbayria.gems_realm.modules.neoforge.more_beautiful_bookshelves.MoreBeautifulBookshelvesModuleG;
import net.xelbayria.gems_realm.modules.neoforge.more_beautiful_bookshelves.MoreBeautifulBookshelvesModuleM;

@ECPlugin
public class GemsRealmForgePlugin extends GemsRealmCommonPlugin {

    @Override
    public void register(PluginEvent event) {
        super.register(event);

        // Macaw's
        event.addModule("mcwbridges", () -> MacawBridgesModule.class);
        event.addModule("mcwwindows", () -> MacawWindowsModule.class);
        event.addModule("mcwfences", () -> MacawFencesModule.class);

        // General
        event.addModule("create", () -> CreateModule.class);
        event.addModule("createdeco", () -> CreateDecoModule.class);
        event.addModules("lapidarist", () -> LapidaristModuleC.class, () -> LapidaristModuleD.class, () -> LapidaristModuleG.class, () -> LapidaristModuleM.class);
        event.addModules("bad", () -> MoreBeautifulBookshelvesModuleC.class, () -> MoreBeautifulBookshelvesModuleD.class, () -> MoreBeautifulBookshelvesModuleG.class, () -> MoreBeautifulBookshelvesModuleM.class);

    }
}
