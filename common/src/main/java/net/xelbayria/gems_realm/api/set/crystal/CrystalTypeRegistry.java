package net.xelbayria.gems_realm.api.set.crystal;

import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

import static net.xelbayria.gems_realm.api.set.RockType.isInItemRegistry;
import static net.xelbayria.gems_realm.api.set.RockType.newSubBlockType;
import static net.xelbayria.gems_realm.api.set.crystal.VanillaCrystalChildKeys.CLUSTER;
import static net.xelbayria.gems_realm.api.set.crystal.VanillaCrystalChildKeys.SHARD;
import static net.xelbayria.gems_realm.api.set.metal.VanillaMetalChildKeys.INGOT;
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
    public Optional<CrystalType> detectTypeFromBlock(Block baseBlock, ResourceLocation blockId) {
        String namespace = blockId.getNamespace();
        String blockPath = blockId.getPath();

        /// ────────────────────────────────── Default ──────────────────────────────────
        // Ensure the detected block is actually CrystalType
        boolean hasShard = isInItemRegistry(namespace, blockPath, "block", SHARD);
        boolean hasCluster = isInItemRegistry(namespace, blockPath, "block", CLUSTER);
        boolean noGemType = !isInItemRegistry(namespace, blockPath, "_block", "");
        boolean noMetalType = !isInItemRegistry(namespace, blockPath, "block", INGOT);
        boolean noWoodType = !isInItemRegistry(namespace, blockPath, "block", VanillaWoodChildKeys.LOG);

        return newSubBlockType(CrystalType::new, baseBlock, blockId, blockPath, "(?<typename>\\w+)_block",
                valuesReg, BLACKLISTED_CRYSTALTYPES, BLACKLISTED_MODS,
                (hasCluster || hasShard), noWoodType, noMetalType, noGemType);
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
