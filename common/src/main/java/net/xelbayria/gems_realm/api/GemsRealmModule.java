package net.xelbayria.gems_realm.api;

import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.resources.assets.LangBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.xelbayria.gems_realm.GRRegistry;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.api.set.crystal.CrystalType;
import net.xelbayria.gems_realm.api.set.dust.DustType;
import net.xelbayria.gems_realm.api.set.gem.GemType;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.misc.HardcodedCrystalType;
import net.xelbayria.gems_realm.misc.HardcodedDustType;
import net.xelbayria.gems_realm.misc.HardcodedGemType;
import net.xelbayria.gems_realm.misc.HardcodedMetalType;

import java.util.function.Consumer;


public class GemsRealmModule extends EveryCompatModule {

    public GemsRealmModule(String modId, String shortId) {
        super(modId, shortId, GemsRealm.MOD_ID);
    }


    @Override
    @SuppressWarnings({"DataFlowIssue", "unchecked"})
    public ResourceKey<CreativeModeTab> getDedicatedTab() {
        return (ResourceKey<CreativeModeTab>) GRRegistry.MOD_TAB.getKey();
    }

    @Override
    public String toString() {
        return "[GemsRealm - " + LangBuilder.getReadableName(modId) + " Module]";
    }

    @Override
    public boolean isEntryAlreadyRegistered(String entrySetId, ResourceLocation blockId, BlockType blockType, Registry<?> registry) {

        String blockPath = blockId.getPath();
        // blockName: <name>_column from the blockId: gems_realm:twigs/strata/<name>_column
        String blockName = blockPath.substring(blockPath.lastIndexOf("/") + 1);

        if (blockType instanceof DustType dustType) {
            Boolean hardcoded = HardcodedDustType.isDustBlockAlreadyRegistered(entrySetId, blockName, dustType, modId);
            if (hardcoded != null) return hardcoded;
        }
        else if (blockType instanceof CrystalType crystalType) {
            Boolean hardcoded = HardcodedCrystalType.isCrystalBlockAlreadyRegistered(entrySetId, blockName, crystalType, modId);
            if (hardcoded != null) return hardcoded;
        }
        else if (blockType instanceof GemType gemType) {
            Boolean hardcoded = HardcodedGemType.isGemBlockAlreadyRegistered(entrySetId, blockName, gemType, modId);
            if (hardcoded != null) return hardcoded;
        }
        else if (blockType instanceof MetalType metalType) {
            Boolean hardcoded = HardcodedMetalType.isMetalBlockAlreadyRegistered(entrySetId, blockName, metalType, modId);
            if (hardcoded != null) return hardcoded;
        }

        return super.isEntryAlreadyRegistered(entrySetId, blockId, blockType, registry);
    }

    @Override
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);
        executor.accept((resourceManager, resourceSink) -> {
            getEntries().forEach(entrySetParent -> {
                if (entrySetParent instanceof GemsRealmEntrySet<?,?> entrySet) {
                    entrySet.generateModels(this, resourceManager, resourceSink);
                }
            });
        });
    }

}
