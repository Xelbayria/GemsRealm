package net.xelbayria.gems_realm.misc;

import net.xelbayria.gems_realm.api.set.DustType;
import org.jetbrains.annotations.Nullable;

import static net.xelbayria.gems_realm.configs.UnsafeDisablerConfigs.dustTypeList;
import static net.xelbayria.gems_realm.configs.UnsafeDisablerConfigs.entrySetList;
import static net.xelbayria.gems_realm.misc.HardcodedBlockType.isKnownVanillaDust;

public class HardcodedDustType extends HardcodedBlockType{

    @Nullable
    public static Boolean isDustBlockAlreadyRegistered(String entrySetId, String blockName, DustType dustType, String ModId) {
        blockIdentify = dustType.getId().toString();
        BlockTypeFromMod = dustType.getNamespace();
        supportedMod = ModId;
        supportedBlockName = blockName;

        /// ─────────────────────────── Include Vanilla Type ────────────────────────────

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ EXCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

        // Exclude one StoneType from a Stone mod
        if (dustTypeList.get().stream().anyMatch(blockIdentify::matches)) return true;

        // Exclude one EntrySet from a module
        if (entrySetList.get().stream().anyMatch(entrySetId::matches)) return true;

        // Exclude all of Vanilla Types
        if (isKnownVanillaDust(dustType)) return true;

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ INCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━



        return null;
    }

}
