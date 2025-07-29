package net.xelbayria.gems_realm.misc;

import net.xelbayria.gems_realm.api.set.GemType;
import org.jetbrains.annotations.Nullable;

import static net.xelbayria.gems_realm.configs.UnsafeDisablerConfigs.entrySetList;
import static net.xelbayria.gems_realm.configs.UnsafeDisablerConfigs.gemTypeList;
import static net.xelbayria.gems_realm.misc.HardcodedBlockType.isKnownVanillaGem;

public class HardcodedGemType extends HardcodedBlockType{

    @Nullable
    public static Boolean isGemBlockAlreadyRegistered(String entrySetId, String blockName, GemType gemType, String ModId) {
        blockIdentify = gemType.getId().toString();
        BlockTypeFromMod = gemType.getNamespace();
        supportedMod = ModId;
        supportedBlockName = blockName;

        /// ─────────────────────────── Include Vanilla Type ────────────────────────────

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ EXCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

        // Exclude one StoneType from a Stone mod
        if (gemTypeList.get().stream().anyMatch(blockIdentify::matches)) return true;

        // Exclude one EntrySet from a module
        if (entrySetList.get().stream().anyMatch(entrySetId::matches)) return true;

        // Exclude all of Vanilla Types
        if (isKnownVanillaGem(gemType)) return true;

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ INCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

        return null;
    }

}
