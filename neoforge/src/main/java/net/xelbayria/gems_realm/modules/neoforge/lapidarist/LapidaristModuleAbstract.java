package net.xelbayria.gems_realm.modules.neoforge.lapidarist;

import net.minecraft.world.item.CreativeModeTab;
import net.xelbayria.gems_realm.api.GemsRealmModule;

import java.util.function.Supplier;

///SUPPORT: v4.0+
public class LapidaristModuleAbstract extends GemsRealmModule {

    protected final Supplier<CreativeModeTab> tab = getModTab("lapidarist_tab");

    public LapidaristModuleAbstract(String modId) {
        super(modId, "lpd");
    }
}
