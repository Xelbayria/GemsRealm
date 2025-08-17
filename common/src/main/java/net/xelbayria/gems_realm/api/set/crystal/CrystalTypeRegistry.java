package net.xelbayria.gems_realm.api.set.crystal;

import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.Objects;
import java.util.Optional;

import static net.xelbayria.gems_realm.misc.HardcodedBlockType.BLACKLISTED_CRYSTALTYPES;
import static net.xelbayria.gems_realm.misc.HardcodedBlockType.BLACKLISTED_MODS;

//@SuppressWarnings("unused")
public class CrystalTypeRegistry extends BlockTypeRegistry<CrystalType> {

    public static final CrystalTypeRegistry INSTANCE = new CrystalTypeRegistry();

    public CrystalTypeRegistry() {
        super(CrystalType.class, "crystal_type");
    }

    @Override
    public CrystalType register(CrystalType vanillaType) {
        return super.register(vanillaType);
    }

    @Override
    public CrystalType getDefaultType() {
        return VanillaCrystalTypes.AMETHYST;
    }

    @Override
    public Optional<CrystalType> detectTypeFromBlock(Block baseBlock, ResourceLocation baseRes) {
        String blockPath = baseRes.getPath();

        /// Default
        if (blockPath.matches("\\w+_block")) {
            String crystalName = blockPath.replace("_block", ""); // get gemName from namespace:gemName_block
            ResourceLocation idBlockType = baseRes.withPath(crystalName);

            /// Ensure the detected block is actually CrystalType
            boolean hasShard = BuiltInRegistries.ITEM.containsKey(
                    new ResourceLocation(baseRes.getNamespace(), blockPath.replace("block", "shard"))
            );
            boolean hasCluster = BuiltInRegistries.ITEM.containsKey(
                    new ResourceLocation(baseRes.getNamespace(), blockPath.replace("block", "cluster"))
            );
            boolean noWoodType = !BuiltInRegistries.BLOCK.containsKey(
                    new ResourceLocation(baseRes.getNamespace(), blockPath.replace("block", "log"))
            );
            boolean noMetalType = !BuiltInRegistries.ITEM.containsKey(
                    new ResourceLocation(baseRes.getNamespace(), blockPath.replace("block", "ingot"))
            );
            boolean noGemType = !BuiltInRegistries.ITEM.containsKey(
                    new ResourceLocation(baseRes.getNamespace(), blockPath.replace("_block", ""))
            );

            // Ensure there is no duplicated CrystalType in the list
            if (Objects.isNull(get(idBlockType))
                    && hasCluster
                    && hasShard
                    && noWoodType
                    && noMetalType
                    && noGemType
                    && !BLACKLISTED_CRYSTALTYPES.contains(idBlockType.toString())
                    && !BLACKLISTED_MODS.contains(baseRes.getNamespace())
            ) {
                Optional<Block> opt = BuiltInRegistries.BLOCK.getOptional(baseRes);

                if (opt.isPresent()) return Optional.of(new CrystalType(idBlockType, opt.get()));
            }

        }
        return Optional.empty();
    }

    //shorthand for add finder. Gives a builder-like object that's meant to be configured inline
    public CrystalType.Finder addSimpleFinder(ResourceLocation crystalTypeId) {
        CrystalType.Finder finder = new CrystalType.Finder(crystalTypeId);
        this.addFinder(finder);
        return finder;
    }

    public CrystalType.Finder addSimpleFinder(String typeId) {
        return addSimpleFinder(new ResourceLocation(typeId));
    }

    public CrystalType.Finder addSimpleFinder(String namespace, String nameCrystalType) {
        return addSimpleFinder(new ResourceLocation(namespace, nameCrystalType));
    }

    @Override
    public int priority() {
        return 110;
    }
}
