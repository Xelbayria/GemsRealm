package net.xelbayria.gems_realm.misc;

import net.xelbayria.gems_realm.api.set.crystal.CrystalType;
import net.xelbayria.gems_realm.api.set.dust.DustType;
import net.xelbayria.gems_realm.api.set.gem.GemType;

public class GemTypeCycleItemRenderer extends GRBlockTypeCycleItemRenderer {

    public GemTypeCycleItemRenderer() {
        super(CrystalType.class, DustType.class, GemType.class);
    }

}
