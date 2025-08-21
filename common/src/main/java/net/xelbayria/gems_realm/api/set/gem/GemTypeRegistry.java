package net.xelbayria.gems_realm.api.set.gem;

import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.Objects;
import java.util.Optional;

import static net.xelbayria.gems_realm.misc.HardcodedBlockType.BLACKLISTED_GEMTYPES;
import static net.xelbayria.gems_realm.misc.HardcodedBlockType.BLACKLISTED_MODS;

public class GemTypeRegistry extends BlockTypeRegistry<GemType> {

    public static final GemTypeRegistry INSTANCE = new GemTypeRegistry();

    public GemTypeRegistry() {
        super(GemType.class, "gem_type");
    }

    @Override
    public GemType register(GemType vanillaType) {
        return super.register(vanillaType);
    }

    @Override
    public GemType getDefaultType() {
        return VanillaGemTypes.EMERALD;
    }

    @Override
    public Optional<GemType> detectTypeFromBlock(Block baseblock, ResourceLocation baseRes) {
        String blockPath = baseRes.getPath();

        /// Default
        // Checking for word1_word2_TYPE_block - NOTE: word1, word2 can be present or not
        if (blockPath.matches("^(?:rare|dark|black|white|cyan|pink|yellow|blue|green|purple|red|orange|brown|olive)?_?(?:ice|fire|star|blue)?_?[a-z]+_block$") ) {
            String gemName = blockPath.replace("_block", ""); // get gemName from namespace:gemName_block
            ResourceLocation idBlockType = baseRes.withPath(gemName);

            /// Ensure the detected block is actually GemType
            boolean hasOre = BuiltInRegistries.ITEM.containsKey(
                    new ResourceLocation(baseRes.getNamespace(), blockPath.replace("block", "ore"))
            ) || BuiltInRegistries.ITEM.containsKey(
                    new ResourceLocation(baseRes.getNamespace(), "deepslate_"+ blockPath.replace("block", "ore"))
            );
            boolean hasGem = BuiltInRegistries.ITEM.containsKey(
                    new ResourceLocation(baseRes.getNamespace(), blockPath.replace("_block", ""))
            );
            boolean noWoodType = !BuiltInRegistries.BLOCK.containsKey(
                    new ResourceLocation(baseRes.getNamespace(), blockPath.replace("block", "log"))
            );
            boolean noCrystalType = !BuiltInRegistries.ITEM.containsKey(
                    new ResourceLocation(baseRes.getNamespace(), blockPath.replace("block", "cluster"))
            );
            boolean noDustType = !BuiltInRegistries.ITEM.containsKey(
                    new ResourceLocation(baseRes.getNamespace(), blockPath.replace("block", "dust"))
            );
            boolean noMetalType = !BuiltInRegistries.ITEM.containsKey(
                    new ResourceLocation(baseRes.getNamespace(), blockPath.replace("block", "ingot"))
            )
                && !BuiltInRegistries.ITEM.containsKey(
                    new ResourceLocation(baseRes.getNamespace(), "raw_"+ blockPath.replace("_block", ""))
            );

            // Ensure there is no duplicated GemType in the list
            if (!valuesReg.containsKey(idBlockType)
                    && hasOre
                    && hasGem
                    && noWoodType
                    && noCrystalType
                    && noDustType
                    && noMetalType
                    && !BLACKLISTED_GEMTYPES.contains(idBlockType.toString())
                    && !BLACKLISTED_MODS.contains(baseRes.getNamespace())
            ) {
                Optional<Block> opt = BuiltInRegistries.BLOCK.getOptional(baseRes);

                if (opt.isPresent()) return Optional.of(new GemType(idBlockType, opt.get()));
            }

        }
        return Optional.empty();
    }


    //shorthand for add finder. Gives a builder-like object that's meant to be configured inline
    public GemType.Finder addSimpleFinder(ResourceLocation gemTypeId) {
        GemType.Finder finder = new GemType.Finder(gemTypeId);
        this.addFinder(finder);
        return finder;
    }

    public GemType.Finder addSimpleFinder(String typeId) {
        return addSimpleFinder(new ResourceLocation(typeId));
    }

    public GemType.Finder addSimpleFinder(String namespace, String nameGemType) {
        return addSimpleFinder(new ResourceLocation(namespace, nameGemType));
    }

    @Override
    public int priority() {
        return 110;
    }
}
