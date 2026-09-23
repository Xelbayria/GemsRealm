package net.xelbayria.gems_realm.misc;

import net.xelbayria.gems_realm.api.set.crystal.CrystalType;
import org.jetbrains.annotations.Nullable;

import static net.xelbayria.gems_realm.configs.UnsafeDisablerConfigs.crystalTypeList;
import static net.xelbayria.gems_realm.configs.UnsafeDisablerConfigs.entrySetList;

public class HardcodedCrystalType extends HardcodedBlockType {

    @Nullable
    public static Boolean isCrystalBlockAlreadyRegistered(String entrySetId, String blockName, CrystalType crystalType, String supportedModId) {
        String crystalFullId = crystalType.getId().toString();
        String crystalTypeNamespace = crystalType.getNamespace();

        PendingBlockInfo pendingInfo = PendingBlockInfo.of(crystalTypeNamespace, crystalFullId, blockName, supportedModId);

        /// ─────────────────────────── Include Vanilla Type ────────────────────────────

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ EXCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

        // Exclude one StoneType from a Stone mod
        if (crystalTypeList.get().stream().anyMatch(crystalFullId::matches)) return true;

        // Exclude one EntrySet from a module
        if (entrySetList.get().stream().anyMatch(entrySetId::matches)) return true;

        // Exclude all of Vanilla Types
        if (isKnownVanillaCrystal(crystalType)) return true;

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ INCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

        if (pendingInfo.isForSupportedModId("chipped") && pendingInfo.isForTypeNamespace("crystalized_enchants")) return false;
//        if (isBlockRegistryFrom("chipped", "crystalized_enchants", "", "")) return false;


        return null;
    }

}
