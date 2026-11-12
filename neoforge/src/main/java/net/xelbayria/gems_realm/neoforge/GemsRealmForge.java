package net.xelbayria.gems_realm.neoforge;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.GemsRealmClient;
import net.xelbayria.gems_realm.GemsRealmCommon;
import org.jetbrains.annotations.NotNull;

/**
 * Author: Xel'Bayria
 */
@Mod(GemsRealm.MOD_ID)
public class GemsRealmForge extends GemsRealmCommon {

    public GemsRealmForge(IEventBus bus) {
        this.initialize();

        NeoForge.EVENT_BUS.register(this); // Ensure that iTemTooltipEvent() get called
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void itemTooltipEvent(@NotNull ItemTooltipEvent event) {
        if (PlatHelper.getPhysicalSide().isClient())
            GemsRealmClient.onItemTooltip(event.getItemStack(), event.getContext(), event.getFlags(), event.getToolTip());
    }

}
