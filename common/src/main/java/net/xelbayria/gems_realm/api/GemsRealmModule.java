package net.xelbayria.gems_realm.api;

import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
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
import net.xelbayria.gems_realm.common_classes.BlockSetEntries;
import net.xelbayria.gems_realm.misc.HardcodedCrystalType;
import net.xelbayria.gems_realm.misc.HardcodedDustType;
import net.xelbayria.gems_realm.misc.HardcodedGemType;
import net.xelbayria.gems_realm.misc.HardcodedMetalType;

import java.util.function.Consumer;


@SuppressWarnings("CommentedOutCode")
public class GemsRealmModule extends EveryCompatModule {

    public GemsRealmModule(String modId, String shortId) {
        super(modId, shortId, GemsRealm.MOD_ID);
    }


    @Override
    @SuppressWarnings({"DataFlowIssue"})
    public ResourceKey<CreativeModeTab> getDedicatedTab() {
        return GRRegistry.MOD_TAB.getKey();
    }

    @Override
    public boolean isEntryAlreadyRegistered(String entrySetId, ResourceLocation blockId, BlockType blockType, Registry<?> registry) {

        String blockPath = blockId.getPath();
        // blockName: <name>_column from the blockId: gems_realm:twigs/strata/<name>_column
        String blockName = blockPath.substring(blockPath.lastIndexOf("/") + 1);

        /// Enable this when there are Compat-Mods for Gems-Realm
//        String blockTypeFrom = blockType.getNamespace();

//        String slashConvention = blockTypeFrom + "/" + blockName; // quark/blossom_chair
//        String underscoreConvention = blockTypeFrom + "_" + blockName; // quark_blossom_chair

        /// Exclude blocks when a Compat-Mod have a support for Supported-Mod with Mods that add new BlockTypes
/*        for (var c : EveryCompat.getCompatMods()) { //TODO: add getCompatMods to StoneZone.java to avoid unstableApiUsage
            String compatModId = c.modId();  //bopcomp : bop->quark, twigs
            //if the wood is from the mod this adds compat for && it supports this block type
            if (c.woodsFrom().contains(blockTypeFrom) && c.blocksFrom().contains(modId)) {
                if (registry.containsKey(ResourceLocation.fromNamespaceAndPath(compatModId, blockName))) return true;
                if (registry.containsKey(ResourceLocation.fromNamespaceAndPath(compatModId, slashConvention)))
                    return true;
                if (registry.containsKey(ResourceLocation.fromNamespaceAndPath(compatModId, underscoreConvention)))
                    return true;
            }
        }*/

        switch (blockType) {
            case CrystalType crystalType -> {
                Boolean hardcoded = HardcodedCrystalType.isCrystalBlockAlreadyRegistered(entrySetId, blockName, crystalType, modId);
                if (hardcoded != null) return hardcoded;
            }
            case DustType dustType -> {
                Boolean hardcoded = HardcodedDustType.isDustBlockAlreadyRegistered(entrySetId, blockName, dustType, modId);
                if (hardcoded != null) return hardcoded;
            }
            case GemType gemType -> {
                Boolean hardcoded = HardcodedGemType.isGemBlockAlreadyRegistered(entrySetId, blockName, gemType, modId);
                if (hardcoded != null) return hardcoded;
            }
            case MetalType metalType -> {
                Boolean hardcoded = HardcodedMetalType.isMetalBlockAlreadyRegistered(entrySetId, blockName, metalType, modId);
                if (hardcoded != null) return hardcoded;
            }
            default -> {
            }
        }

        return super.isEntryAlreadyRegistered(entrySetId, blockId, blockType, registry);
    }

    @Override
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);
        executor.accept((resourceManager, resourceSink) ->
            getEntries().forEach(entrySetParent -> {
                if (entrySetParent instanceof GemsRealmEntrySet<?,?> entrySet) {
                    entrySet.generateModels(this, resourceManager, resourceSink);
                }
            })
        );
    }

    public  <T extends BlockType> void addEntries(BlockSetEntries<T> blockSet) {
        this.addEntry(blockSet.block);
        if (blockSet.slabOrExtra != null) this.addEntry(blockSet.slabOrExtra);
        if (blockSet.stairs != null) this.addEntry(blockSet.stairs);
        if (blockSet.wall != null) this.addEntry(blockSet.wall);
    }

}
