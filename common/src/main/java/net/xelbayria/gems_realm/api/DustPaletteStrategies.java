package net.xelbayria.gems_realm.api;

import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.PaletteStrategy;
import net.xelbayria.gems_realm.api.set.VanillaRockChildKeys;

public class DustPaletteStrategies extends PaletteStrategies {

// ────────────────────────────────────────────────────── Blocks ─────────────────────────────────────────────────────--
    public static final PaletteStrategy RAW_BLOCK_STANDARD = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, VanillaRockChildKeys.RAW_BLOCK, null, null));

// ─────────────────────────────────────────────────────── Items ──────────────────────────────────────────────────────-
}
