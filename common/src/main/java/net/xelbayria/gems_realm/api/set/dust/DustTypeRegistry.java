package net.xelbayria.gems_realm.api.set.dust;

import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

import static net.xelbayria.gems_realm.api.set.RockType.isInItemRegistry;
import static net.xelbayria.gems_realm.api.set.RockType.newSubBlockType;
import static net.xelbayria.gems_realm.misc.HardcodedBlockType.*;

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
    public Optional<DustType> detectTypeFromBlock(Block baseBlock, ResourceLocation blockId) {
        String namespace = blockId.getNamespace();
        String blockPath = blockId.getPath();

        /// ────────────────────────────────── Default ──────────────────────────────────
        // Ensure the detected block is actually DustType
        boolean hasDust = isInItemRegistry(namespace, blockPath, "block", "dust");
        boolean noWoodType = !isInItemRegistry(namespace, blockPath, "block", "log");
        boolean noMetalType = !isInItemRegistry(namespace, blockPath, "block", "ingot");
        boolean noGemType = !isInItemRegistry(namespace, blockPath, "_block", "");

        return newSubBlockType(DustType::new, baseBlock, blockId, blockPath, "(?<typename>\\w+)_block",
                valuesReg, BLACKLISTED_DUSTTYPES, BLACKLISTED_MODS,
                hasDust, noWoodType, noMetalType, noGemType,
                !BLACKLISTED_DUST_MODS.contains(blockId.getNamespace()) //TEMP
        );
    }


    //shorthand for add finder. Gives a builder-like object that's meant to be configured inline
    public DustType.Finder addSimpleFinder(ResourceLocation dustTypeId) {
        DustType.Finder finder = new DustType.Finder(dustTypeId);
        this.addFinder(finder);
        return finder;
    }

    public DustType.Finder addSimpleFinder(String typeId) {
        return addSimpleFinder(ResourceLocation.parse(typeId));
    }

    @SuppressWarnings("unused")
    public DustType.Finder addSimpleFinder(String namespace, String nameDustType) {
        return addSimpleFinder(ResourceLocation.fromNamespaceAndPath(namespace, nameDustType));
    }

    @Override
    public int priority() {
        return 110;
    }
}
