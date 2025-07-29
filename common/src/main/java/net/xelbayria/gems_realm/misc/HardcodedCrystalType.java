package net.xelbayria.gems_realm.misc;

import net.xelbayria.gems_realm.api.set.CrystalType;
import org.jetbrains.annotations.Nullable;

import static net.xelbayria.gems_realm.configs.UnsafeDisablerConfigs.crystalTypeList;
import static net.xelbayria.gems_realm.configs.UnsafeDisablerConfigs.entrySetList;
import static net.xelbayria.gems_realm.misc.HardcodedBlockType.isKnownVanillaCrystal;

public class HardcodedCrystalType extends HardcodedBlockType {

    @Nullable
    public static Boolean isCrystalBlockAlreadyRegistered(String entrySetId, String blockName, CrystalType crystalType, String ModId) {
        blockIdentify = crystalType.getId().toString();
        BlockTypeFromMod = crystalType.getNamespace();
        supportedMod = ModId;
        supportedBlockName = blockName;

        /// ─────────────────────────── Include Vanilla Type ────────────────────────────

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ EXCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

        // Exclude one StoneType from a Stone mod
        if (crystalTypeList.get().stream().anyMatch(blockIdentify::matches)) return true;

        // Exclude one EntrySet from a module
        if (entrySetList.get().stream().anyMatch(entrySetId::matches)) return true;

        // Exclude all of Vanilla Types
        if (isKnownVanillaCrystal(crystalType)) return true;

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ INCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━



        return null;
    }

}
