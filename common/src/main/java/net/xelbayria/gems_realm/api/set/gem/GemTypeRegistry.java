package net.xelbayria.gems_realm.api.set.gem;

import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

import static net.xelbayria.gems_realm.api.set.RockType.*;
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
    public Optional<GemType> detectTypeFromBlock(Block baseBlock, ResourceLocation blockId) {
        String namespace = blockId.getNamespace();
        String blockPath = blockId.getPath();

        /// ──────────────────────────── Support Tech-reborn ────────────────────────────
        if (namespace.matches("techreborn")) {
            String blockSuffix = "storage_block";
            setChildInfix("storage_");

            // Ensure the detected block is actually GemType
            boolean hasGem = isInItemRegistry(namespace, blockPath, blockSuffix, "gem");
            boolean noMetalType = !isInItemRegistry(namespace, blockPath, blockSuffix, "ingot");

            return newSubBlockType(GemType::new, baseBlock, blockId, blockPath, "(?<typename>\\w+)_"+blockSuffix,
                    valuesReg, hasGem, noMetalType);
        }

        /// ────────────────────────────────── Default ──────────────────────────────────
        // Ensure the detected block is actually GemType
        boolean hasOre = isInItemRegistry(namespace, blockPath, "block", "ore")
                || isInItemRegistry(namespace, "deepslate_"+blockPath, "block", "ore");
        boolean hasGem = isInItemRegistry(namespace, blockPath, "_block", "");
        boolean noWoodType = !isInItemRegistry(namespace, blockPath, "block", "log");
        boolean noCrystalType = !isInItemRegistry(namespace, blockPath, "block", "cluster");
        boolean noDustType = !isInItemRegistry(namespace, blockPath, "block", "dust");
        boolean noMetalType = !isInItemRegistry(namespace, blockPath, "block", "ingot")
                && !isInItemRegistry(namespace, "raw_"+blockPath, "block", "");

        // Checking for word1_word2_TYPE_block - NOTE: word1, word2 can be present or not
        String blockPathRegex = "^(?<typename>(?:rare|dark|black|white|cyan|pink|yellow|blue|green|purple|red|orange|brown|olive)?_?(?:ice|fire|star|blue)?_?[a-z]+)_block$";

        return newSubBlockType(GemType::new, baseBlock, blockId, blockPath, blockPathRegex, valuesReg,
                BLACKLISTED_GEMTYPES, BLACKLISTED_MODS,
                hasOre, hasGem, noWoodType, noCrystalType, noDustType, noMetalType);
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
