package net.xelbayria.gems_realm.api.set.dust;

import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.Objects;
import java.util.Optional;

import static net.xelbayria.gems_realm.misc.HardcodedBlockType.BLACKLISTED_DUSTTYPES;
import static net.xelbayria.gems_realm.misc.HardcodedBlockType.BLACKLISTED_MODS;

public class DustTypeRegistry extends BlockTypeRegistry<DustType> {

    public static final DustTypeRegistry INSTANCE = new DustTypeRegistry();

    public DustTypeRegistry() {
        super(DustType.class, "dust_type");
    }

    @Override
    public DustType register(DustType vanillaType) {
        return super.register(vanillaType);
    }

    @Override
    public DustType getDefaultType() {
        return VanillaDustTypes.REDSTONE;
    }

    @Override
    public Optional<DustType> detectTypeFromBlock(Block baseBlock, ResourceLocation baseRes) {
        String blockPath = baseRes.getPath();

        /// Default
        if (blockPath.matches("\\w+_block")) {
            String crystalName = blockPath.replace("_block", ""); // get gemName from namespace:gemName_block
            ResourceLocation idBlockType = baseRes.withPath(crystalName);

            /// Ensure the detected block is actually DustType
            boolean hasDust = BuiltInRegistries.ITEM.containsKey(
                    new ResourceLocation(baseRes.getNamespace(), blockPath.replace("block", "dust"))
            );
            boolean noWoodType = !BuiltInRegistries.ITEM.containsKey(
                    new ResourceLocation(baseRes.getNamespace(), blockPath.replace("block", "log"))
            );
            boolean noMetalType = !BuiltInRegistries.ITEM.containsKey(
                    new ResourceLocation(baseRes.getNamespace(), blockPath.replace("block", "ingot"))
            );
            boolean noGemType = !BuiltInRegistries.ITEM.containsKey(
                    new ResourceLocation(baseRes.getNamespace(), blockPath.replace("_block", ""))
            );

            // Ensure there is no duplicated DustType in the list
            if (!valuesReg.containsKey(idBlockType)
                    && hasDust
                    && noWoodType
                    && noMetalType
                    && noGemType
                    && !BLACKLISTED_DUSTTYPES.contains(idBlockType.toString())
                    && !BLACKLISTED_MODS.contains(baseRes.getNamespace())
            ) {
                Optional<Block> opt = BuiltInRegistries.BLOCK.getOptional(baseRes);

                if (opt.isPresent()) return Optional.of(new DustType(idBlockType, opt.get()));
            }

        }
        return Optional.empty();
    }


    //shorthand for add finder. Gives a builder-like object that's meant to be configured inline
    public DustType.Finder addSimpleFinder(ResourceLocation dustTypeId) {
        DustType.Finder finder = new DustType.Finder(dustTypeId);
        this.addFinder(finder);
        return finder;
    }

    public DustType.Finder addSimpleFinder(String typeId) {
        return addSimpleFinder(new ResourceLocation(typeId));
    }

    @SuppressWarnings("unused")
    public DustType.Finder addSimpleFinder(String namespace, String nameDustType) {
        return addSimpleFinder(new ResourceLocation(namespace, nameDustType));
    }

    @Override
    public int priority() {
        return 110;
    }
}
