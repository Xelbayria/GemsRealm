package net.xelbayria.gems_realm.api;

import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.PaletteStrategy;
import net.xelbayria.gems_realm.api.set.metal.VanillaMetalChildKeys;

public class MetalPaletteStrategies extends PaletteStrategies {

    public static final PaletteStrategy TRAPDOOR_STANDARD = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, VanillaMetalChildKeys.TRAPDOOR, null, null));

    public static final PaletteStrategy INGOT_STANDARD = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, VanillaMetalChildKeys.INGOT, null, null));
}
