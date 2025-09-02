package net.xelbayria.gems_realm.misc;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.world.level.block.Block;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static net.xelbayria.gems_realm.configs.UnsafeDisablerConfigs.entrySetList;
import static net.xelbayria.gems_realm.configs.UnsafeDisablerConfigs.metalTypeList;

public class HardcodedMetalType extends HardcodedBlockType{

    @Nullable
    public static Boolean isMetalBlockAlreadyRegistered(String entrySetId, String blockName, MetalType metalType, String modId) {
        blockIdentify = metalType.getId().toString();
        BlockTypeFromMod = metalType.getNamespace();
        supportedMod = modId;
        supportedBlockName = blockName;

        /// ─────────────────────────── Include Vanilla Type ────────────────────────────

        // MInecraft's NETHERITE has no nugget
        if (!(PlatHelper.isModLoaded("oreganized") || PlatHelper.isModLoaded("caverns_and_chasms")) &&
                isBlockRegistryFrom("minecraft", "", "minecraft:netherite", "nugget")) return false;

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ EXCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

        // Exclude one MetalType from a Stone mod
        if (metalTypeList.get().stream().anyMatch(blockIdentify::matches)) return true;

        // Exclude one EntrySet from a module
        if (entrySetList.get().stream().anyMatch(entrySetId::matches)) return true;

        // Exclude all of Vanilla Types
        if (isKnownVanillaMetal(metalType)) return true;

        // The normal duplication system is not preventing the duplicated blocks between Supported mods and TFC due to TFC's unique IDs
        if (isMetalRegistryInTFC(metalType, supportedBlockName, "bars")) return true;


        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ INCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

        // Minecraft & TerraFirmaCraft have similar MetalType that preventing the
        if (isBlockRegistryFrom("minecraft", "", "tfc:gold", "nugget")) return false;

        return null;
    }

    public static boolean isMetalRegistryInTFC(MetalType metalType, String supportedBlockName, String childKey) {
        Block block = metalType.getBlockOfThis(childKey);
        if (metalType.getNamespace().equals("tfc") && Objects.nonNull(block)) {
            String[] split = Utils.getID(block).getPath().split("/"); // tfc: metal / childKey / metalType
            String baseName = split[2] +"_"+ childKey; // create a baseName: metalType _ childKey

            return Objects.equals(baseName, supportedBlockName);
        }
        return false;
    }
}
