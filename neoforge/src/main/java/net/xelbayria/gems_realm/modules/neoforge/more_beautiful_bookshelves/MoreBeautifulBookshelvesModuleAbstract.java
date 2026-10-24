package net.xelbayria.gems_realm.modules.neoforge.more_beautiful_bookshelves;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.xelbayria.gems_realm.api.GemsRealmModule;

import java.util.function.Supplier;

///SUPPORT: v2.1.1+
public class MoreBeautifulBookshelvesModuleAbstract extends GemsRealmModule {

    protected Supplier<CreativeModeTab> tab = getTab(CreativeModeTabs.BUILDING_BLOCKS);

    public MoreBeautifulBookshelvesModuleAbstract(String modId) {
        super(modId, "mbb");
    }

}