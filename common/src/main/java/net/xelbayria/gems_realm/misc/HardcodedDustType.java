package net.xelbayria.gems_realm.misc;

import net.xelbayria.gems_realm.api.set.dust.DustType;
import org.jetbrains.annotations.Nullable;

import static net.xelbayria.gems_realm.configs.UnsafeDisablerConfigs.dustTypeList;
import static net.xelbayria.gems_realm.configs.UnsafeDisablerConfigs.entrySetList;

public class HardcodedDustType extends HardcodedBlockType{

    @Nullable
    public static Boolean isDustBlockAlreadyRegistered(String entrySetId, String blockName, DustType dustType, String supportedModId) {
        String dustFullId = dustType.getId().toString();
        String dustTypeNamespace = dustType.getNamespace();
        supportedMod = supportedModId;
        supportedBlockName = blockName;

        PendingBlockInfo pendingInfo = PendingBlockInfo.of(dustTypeNamespace, dustFullId, blockName, supportedModId);

        /// ─────────────────────────── Include Vanilla Type ────────────────────────────

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ EXCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

        // Exclude one StoneType from a Stone mod
        if (dustTypeList.get().stream().anyMatch(dustFullId::matches)) return true;

        // Exclude one EntrySet from a module
        if (entrySetList.get().stream().anyMatch(entrySetId::matches)) return true;

        // Exclude all of Vanilla Types
        if (isKnownVanillaDust(dustType)) return true;

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ INCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━



        return null;
    }

}
