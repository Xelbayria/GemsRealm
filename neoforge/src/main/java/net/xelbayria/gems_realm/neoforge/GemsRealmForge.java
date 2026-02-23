package net.xelbayria.gems_realm.neoforge;

import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.GemsRealmClient;
import net.xelbayria.gems_realm.GemsRealmCommon;
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

import static net.mehvahdjukaar.every_compat.api.EveryCompatAPI.addIfLoaded;
import static net.mehvahdjukaar.every_compat.api.EveryCompatAPI.addMultipleIfLoaded;

/**
 * Author: Xel'Bayria
 */
@Mod(GemsRealm.MOD_ID)
public class GemsRealmForge extends GemsRealmCommon {

    public GemsRealmForge(IEventBus bus) {
        RegHelper.startRegisteringFor(bus);
        this.initialize();
    }

    @Override
    protected void addModules() {
        super.addModules();

//!! =================================================== Add Modules ================================================ \\

        // Macaw's
        addIfLoaded("mcwbridges", () -> MacawBridgesModule.class);
        addIfLoaded("mcwwindows", () -> MacawWindowsModule.class);
        addIfLoaded("mcwfences", () -> MacawFencesModule.class);

        // General
        addIfLoaded("create", () -> CreateModule.class);
        addMultipleIfLoaded("lapidary", () -> LapidaristModuleC.class, () -> LapidaristModuleD.class, () -> LapidaristModuleG.class, () -> LapidaristModuleM.class);
        addMultipleIfLoaded("bad", () -> MoreBeautifulBookshelvesModuleC.class, () -> MoreBeautifulBookshelvesModuleD.class, () -> MoreBeautifulBookshelvesModuleG.class, () -> MoreBeautifulBookshelvesModuleM.class);
//        addIfLoaded("rechiseled", () -> RechiseledModule::new);

//!! ====================================================== OTHERS ================================================== \\

    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void itemTooltipEvent(ItemTooltipEvent event) {
        GemsRealmClient.onItemTooltip(event.getItemStack(), event.getFlags(), event.getToolTip());
    }

}
