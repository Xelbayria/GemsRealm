package net.xelbayria.gems_realm.api.set;

import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import static net.xelbayria.gems_realm.misc.HardcodedBlockType.BLACKLISTED_CRYSTALTYPES;
import static net.xelbayria.gems_realm.misc.HardcodedBlockType.BLACKLISTED_MODS;

@SuppressWarnings("unused")
public class CrystalTypeRegistry extends BlockTypeRegistry<CrystalType> {

    public static final CrystalTypeRegistry INSTANCE = new CrystalTypeRegistry();

    public CrystalTypeRegistry() {
        super(CrystalType.class, "crystal_type");

        this.addFinder(CrystalType.Finder.vanilla("amethyst"));
    }

    public static CrystalType getAmethystType() {
        return getValue("amethyst");
    }

    public static Collection<CrystalType> getTypes() {
        return INSTANCE.getValues();
    }

    public static CrystalType getValue(String metalTypeId) {
        return INSTANCE.get(new ResourceLocation(metalTypeId));
    }

    @Override
    public CrystalType getDefaultType() {
        return getAmethystType();
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

    @Override
    public void addTypeTranslations(AfterLanguageLoadEvent language) {
        this.getValues().forEach((crystalType) -> {
            if (language.isDefault()) language.addEntry(crystalType.getTranslationKey(), crystalType.getReadableName());
        });
    }

    @Override
    public int priority() {
        return 110;
    }
}
