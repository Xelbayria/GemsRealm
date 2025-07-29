package net.xelbayria.gems_realm.api;

import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.assets.LangBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.xelbayria.gems_realm.GRRegistry;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.api.set.CrystalType;
import net.xelbayria.gems_realm.api.set.DustType;
import net.xelbayria.gems_realm.api.set.GemType;
import net.xelbayria.gems_realm.api.set.MetalType;
import net.xelbayria.gems_realm.misc.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;


public class GemsRealmModule extends SimpleModule {

    public GemsRealmModule(String modId, String shortId) {
        super(modId, shortId, GemsRealm.MOD_ID);
    }


    @Override
    @SuppressWarnings("DataFlowIssue")
    public ResourceKey<CreativeModeTab> getDedicatedTab() {
        return GRRegistry.MOD_TAB.getKey();
    }

    @Override
    public String toString() {
        return "GemsRealm: " + LangBuilder.getReadableName(modId) + " Module";
    }

    @Override
    public ResourceLocation makeMyRes(String name) {
        return super.makeMyRes(name);
    }

    @Override
    public boolean isEntryAlreadyRegistered(String entrySetId, String blockId, BlockType blockType, Registry<?> registry) {

        // blockId: gems_realm:twigs/strata/<name>_column | blockName: <name>_column
        String blockName = blockId.substring(blockId.lastIndexOf("/") + 1);

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

            // Creating custom parent model files
            Map<ResourceLocation, JsonObject> models = ModelUtils.readAllModelsAndParents(resourceManager, modelsToModify.keySet());
            for (var e : models.entrySet()) {
                // Modifying the contents
                JsonObject json = e.getValue();
                ResourceLocation oldRes = e.getKey();
                var tintConfig = modelsToModify.getOrDefault(oldRes, TintConfiguration.EMPTY);
                ModelUtils.addTintIndexToModelAndReplaceParent(oldRes, json, null, null, tintConfig);
                ResourceLocation newRes = ModelUtils.transformModelID(e.getKey());

                // Add custom models to the resources
                resourceSink.addJson(newRes, json, ResType.MODELS);
            }
        });
    }

    private final Map<ResourceLocation, TintConfiguration> modelsToModify = new HashMap<>();

    public void markModelForModification(ResourceLocation oldRes, TintConfiguration config) {
        modelsToModify.put(oldRes, config);
    }


}
