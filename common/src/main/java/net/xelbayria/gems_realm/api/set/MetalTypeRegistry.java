package net.xelbayria.gems_realm.api.set;

import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import static net.xelbayria.gems_realm.misc.HardcodedBlockType.BLACKLISTED_METALTYPES;
import static net.xelbayria.gems_realm.misc.HardcodedBlockType.BLACKLISTED_MODS;


@SuppressWarnings("unused")
public class MetalTypeRegistry extends BlockTypeRegistry<MetalType> {

    public static final MetalTypeRegistry INSTANCE = new MetalTypeRegistry();

    public MetalTypeRegistry() {
        super(MetalType.class, "metal_type");

        this.addFinder(MetalType.Finder.vanilla("iron"));
        this.addFinder(MetalType.Finder.vanilla("gold"));
        this.addFinder(MetalType.Finder.vanilla("copper"));
    }

    public static MetalType getIronType() {
        return getValue("iron");
    }

    public static MetalType getGoldType() {
        return getValue("gold");
    }

    public static MetalType getCopperType() {
        return getValue("copper");
    }

    public static Collection<MetalType> getTypes() {
        return INSTANCE.getValues();
    }

    public static MetalType getValue(String metalTypeId) {
        return INSTANCE.get(new ResourceLocation(metalTypeId));
    }

    @Override
    public MetalType getDefaultType() {
        return getIronType();
    }

    @Override
    public Optional<MetalType> detectTypeFromBlock(Block baseblock, ResourceLocation baseRes) {
        String blockPath = baseRes.getPath();

        /// Support TerraFirmaCraft (TFC) & ArborFirmaCraft (AFC)
        if (baseRes.getNamespace().matches("tfc|afc")) {
            if (blockPath.matches("metal/block/\\w+(?<!slab|stairs)")) {
                int index = blockPath.lastIndexOf("/");
                String metalName = blockPath.substring(index + 1); // Get bismuth from tfc:metal/block/bismuth

                Optional<Block> block = BuiltInRegistries.BLOCK.getOptional(baseRes);

                /// Ensure the detected block is actually MetalType
                boolean hasIngot = BuiltInRegistries.ITEM.containsKey(
                        new ResourceLocation(baseRes.getNamespace(), blockPath.replace("block", "ingot"))
                );

                if (block.isPresent() && hasIngot) {
                    return Optional.of(new MetalType(baseRes.withPath(metalName), block.get()));
                }
            }
        }

        /// Support Mo' Shiz
        if (baseRes.getNamespace().matches("ms")) {
            if (blockPath.matches("resources/[a-z]+_block")) {
                int index = blockPath.lastIndexOf("/");
                // Get metalName from ms:resources/metalName_block
                String metalName = blockPath.substring(index + 1).replace("_block", "");
                ResourceLocation idBlockType = baseRes.withPath(metalName);

                Optional<Block> block = BuiltInRegistries.BLOCK.getOptional(baseRes);

                /// Ensure the detected block is actually MetalType
                boolean hasIngot = BuiltInRegistries.ITEM.containsKey(
                        new ResourceLocation(baseRes.getNamespace(), blockPath
                                .replace("block", "ingot")
                                .replace("resources", "gem")
                        )
                );

                boolean isBlacklisted = !BLACKLISTED_METALTYPES.contains(idBlockType.toString());

                // Ensure the detected block is actually MetalType
                if (block.isPresent() && hasIngot && isBlacklisted) {
                    return Optional.of(new MetalType(idBlockType, block.get()));
                }
            }
        }

        /// Default
        if (blockPath.matches("\\w+_block") ) {
            // Get metalName from namespace:metalName_block
            String metalName = blockPath.replace("_block", "");
            ResourceLocation idBlockType = baseRes.withPath(metalName);

            /// Ensure the detected block is actually MetalType
            boolean hasIngot = BuiltInRegistries.ITEM.containsKey(
                    new ResourceLocation(baseRes.getNamespace(), blockPath.replace("block", "ingot"))
            );
            boolean noWoodType = !BuiltInRegistries.BLOCK.containsKey(
                    new ResourceLocation(baseRes.getNamespace(), blockPath.replace("block", "log"))
            );
            boolean noGemType = !BuiltInRegistries.ITEM.containsKey(
                    new ResourceLocation(baseRes.getNamespace(), blockPath.replace("_block", ""))
            );

            // Ensure there is no duplicated MetalType in the list
            if (Objects.isNull(get(idBlockType))
                    && hasIngot
                    && noWoodType
                    && noGemType
                    && !BLACKLISTED_METALTYPES.contains(idBlockType.toString())
                    && !BLACKLISTED_MODS.contains(baseRes.getNamespace())
            ) {
                Optional<Block> opt = BuiltInRegistries.BLOCK.getOptional(baseRes);

                if (opt.isPresent()) return Optional.of(new MetalType(idBlockType, opt.get()));
            }
        }
        return Optional.empty();
    }

    @Override
    public void addTypeTranslations(AfterLanguageLoadEvent language) {
        this.getValues().forEach((metalType) -> {
            if (language.isDefault()) language.addEntry(metalType.getTranslationKey(), metalType.getReadableName());
        });
    }

    @Override
    public int priority() {
        return 110;
    }
}
