package net.xelbayria.gems_realm.forge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.GemsRealmClient;
import net.xelbayria.gems_realm.GemsRealmCommon;
import net.xelbayria.gems_realm.modules.forge.create.CreateModule;
import net.xelbayria.gems_realm.modules.forge.lapidarist.LapidaristModuleC;
import net.xelbayria.gems_realm.modules.forge.lapidarist.LapidaristModuleD;
import net.xelbayria.gems_realm.modules.forge.lapidarist.LapidaristModuleG;
import net.xelbayria.gems_realm.modules.forge.lapidarist.LapidaristModuleM;
import net.xelbayria.gems_realm.modules.forge.macaws.MacawBridgesModule;
import net.xelbayria.gems_realm.modules.forge.macaws.MacawFencesModule;
import net.xelbayria.gems_realm.modules.forge.macaws.MacawWindowsModule;

import static net.mehvahdjukaar.every_compat.EveryCompat.addIfLoaded;
import static net.xelbayria.gems_realm.GemsRealm.addMultipleIfLoaded;

/**
 * Author: Xel'Bayria
 */
@Mod(GemsRealm.MOD_ID)
public class GemsRealmForge extends GemsRealmCommon {

    public GemsRealmForge() {
        this.initialize();

        MinecraftForge.EVENT_BUS.register(this);
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
        addIfLoaded("create", () -> CreateModule::new);
//        addIfLoaded("rechiseled", () -> RechiseledModule::new);
//        addIfLoaded("stonechest", () -> StoneChestModule::new);


//!! ====================================================== OTHERS ================================================== \\

    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void itemTooltipEvent(ItemTooltipEvent event) {
        GemsRealmClient.onItemTooltip(event.getItemStack(), event.getFlags(), event.getToolTip());
    }

}
