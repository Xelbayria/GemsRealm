package net.xelbayria.gems_realm.api;

import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.PaletteStrategy;
import net.xelbayria.gems_realm.api.set.crystal.VanillaCrystalChildKeys;

public class CrystalPaletteStrategies extends PaletteStrategies {

    public static final PaletteStrategy BUDDING_STANDARD = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, VanillaCrystalChildKeys.BUDDING, null, null));

}
