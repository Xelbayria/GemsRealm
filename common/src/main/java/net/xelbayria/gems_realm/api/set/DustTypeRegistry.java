package net.xelbayria.gems_realm.api.set;

import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import static net.xelbayria.gems_realm.misc.HardcodedBlockType.BLACKLISTED_DUSTTYPES;
import static net.xelbayria.gems_realm.misc.HardcodedBlockType.BLACKLISTED_MODS;

@SuppressWarnings("unused")
public class DustTypeRegistry extends BlockTypeRegistry<DustType> {

    public static final DustTypeRegistry INSTANCE = new DustTypeRegistry();

    public DustTypeRegistry() {
        super(DustType.class, "dust_type");

        this.addFinder(DustType.Finder.vanilla("redstone"));
    }

    public static DustType getRedstoneType() {
        return getValue("redstone");
    }

    public static Collection<DustType> getTypes() {
        return INSTANCE.getValues();
    }

    public static DustType getValue(String metalTypeId) {
        return INSTANCE.get(new ResourceLocation(metalTypeId));
    }

    @Override
    public DustType getDefaultType() {
        return getRedstoneType();
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
            if (Objects.isNull(get(idBlockType))
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

    @Override
    public void addTypeTranslations(AfterLanguageLoadEvent language) {
        this.getValues().forEach((dustType) -> {
            if (language.isDefault()) language.addEntry(dustType.getTranslationKey(), dustType.getReadableName());
        });
    }

    @Override
    public int priority() {
        return 110;
    }
}
