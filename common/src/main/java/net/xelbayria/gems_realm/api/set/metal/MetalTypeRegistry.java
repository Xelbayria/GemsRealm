package net.xelbayria.gems_realm.api.set.metal;

import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

import static net.xelbayria.gems_realm.api.set.RockType.*;
import static net.xelbayria.gems_realm.misc.HardcodedBlockType.BLACKLISTED_METALTYPES;


//@SuppressWarnings("unused")
public class MetalTypeRegistry extends BlockTypeRegistry<MetalType> {

    public static final MetalTypeRegistry INSTANCE = new MetalTypeRegistry();

    public MetalTypeRegistry() {
        super(MetalType.class, "metal_type");
    }

    @Override
    public MetalType register(MetalType vanillaType) {
        return super.register(vanillaType);
    }

    @Override
    public MetalType getDefaultType() {
        return VanillaMetalTypes.IRON;
    }

    @Override
    public Optional<MetalType> detectTypeFromBlock(Block baseBlock, ResourceLocation blockId) {
        String namespace = blockId.getNamespace();
        String blockPath = blockId.getPath();

        /// ─────────── Support Terrafirmacraft (tfc) & Arborfirmacraft (afc) ───────────
        if (namespace.matches("tfc|afc")) {
            // Ensure the detected block is actually MetalType
            boolean hasIngot = isInItemRegistry(namespace, blockPath, "block", "ingot");

            return newSubBlockType(MetalType::new, baseBlock, blockId, blockPath, "metal/block/(?<typename>\\w+)(?<!slab|stairs)",
                    valuesReg, hasIngot);
        }

        /// ───────────────────────────── Support Mo' Shiz ──────────────────────────────
        if (namespace.matches("ms")) {
            // Ensure the detected block is actually MetalType
            boolean hasIngot = BuiltInRegistries.ITEM.containsKey(
                    ResourceLocation.fromNamespaceAndPath(namespace, blockPath
                            .replace("block", "ingot")
                            .replace("resources", "gem")
                    )
            );

            return newSubBlockType(MetalType::new, baseBlock, blockId, blockPath, "resources/(?<typename>[a-z]+)_block",
                    valuesReg, BLACKLISTED_METALTYPES, hasIngot);
        }

        /// ──────────────────────────── Support Tech-reborn ────────────────────────────
        if (namespace.matches("techreborn")) {
            String blockSuffix = "storage_block";
            setChildInfix("storage_");

            boolean hasIngot = isInItemRegistry(namespace, blockPath, blockSuffix, "ingot");
            boolean noGemType = !isInItemRegistry(namespace, blockPath, blockSuffix, "gem");

            return newSubBlockType(MetalType::new, baseBlock, blockId, blockPath, "(?<typename>\\w+)_"+blockSuffix,
                    valuesReg, hasIngot, noGemType);
        }

        /// ────────────────────────────────── Default ──────────────────────────────────
        // Ensure the detected block is actually MetalType
        boolean hasIngot = isInItemRegistry(namespace, blockPath, "block", "ingot");
        boolean noWoodType = !isInItemRegistry(namespace, blockPath, "block", "log");
        boolean noGemType = !isInItemRegistry(namespace, blockPath, "_block", "");

        return newSubBlockType(MetalType::new, baseBlock, blockId, blockPath, "(?<typename>\\w+)_block",
                valuesReg, BLACKLISTED_METALTYPES, hasIngot, noWoodType, noGemType);

    }


    //shorthand for add finder. Gives a builder-like object that's meant to be configured inline
    public MetalType.Finder addSimpleFinder(ResourceLocation metalTypeId) {
        MetalType.Finder finder = new MetalType.Finder(metalTypeId);
        this.addFinder(finder);
        return finder;
    }

    public MetalType.Finder addSimpleFinder(String typeId) {
        return addSimpleFinder(ResourceLocation.parse(typeId));
    }

    public MetalType.Finder addSimpleFinder(String namespace, String nameMetalType) {
        return addSimpleFinder(ResourceLocation.fromNamespaceAndPath(namespace, nameMetalType));
    }

    @Override
    public int priority() {
        return 110;
    }
}
