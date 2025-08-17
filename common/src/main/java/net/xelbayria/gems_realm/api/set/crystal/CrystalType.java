package net.xelbayria.gems_realm.api.set.crystal;

import com.google.common.base.Preconditions;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.moonlight.core.ClientConfigs;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.api.set.RockType;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

import static net.mehvahdjukaar.moonlight.api.set.DebugBlockTypes.appendToDebugFile;

/**
 * Childkey Availability:
 * block,
 * cluster, glinted_cluster, budding
 * shard (ITEM)
**/
@SuppressWarnings("SameParameterValue")
public class CrystalType extends RockType {

    protected CrystalType(ResourceLocation id, Block blockCrystal) {
        super(id, blockCrystal);

        if (ClientConfigs.BLOCKTYPES_DEBUG.get() && !this.isVanilla()) appendToDebugFile(getTranslationKey());
    }

    @Override
    public String getTranslationKey() {
        return "crystal_type." + this.getNamespace() + "." + this.getTypeName();
    }

    @Override
    protected void initializeChildrenBlocks() {
            this.addChild("cluster", findRelatedBlock("", "cluster"));
            this.addChild("glinted_cluster", findRelatedBlock("glinted", "cluster"));
            this.addChild("lamp", findRelatedBlock("", "lamp"));
            this.addChild("budding", findRelatedBlock("budding", ""));
            super.initializeChildrenBlocks();
    }

    @Override
    protected void initializeChildrenItems() {
        this.addChild("shard", findRelatedItem("", "shard"));
    }

     protected Block findRelatedBlock(String prefixOrInfix, String suffix) {
        return findRelatedEntry(prefixOrInfix, suffix, BuiltInRegistries.BLOCK);
    }

     protected Item findRelatedItem(String prefixOrInfix, String suffix) {
        return findRelatedEntry(prefixOrInfix, suffix, BuiltInRegistries.ITEM);
    }

    @Override
    protected @Nullable <V> V findRelatedEntry(String prefixOrInfix, String suffix, Registry<V> reg) {
        var block = super.findRelatedEntry(prefixOrInfix, suffix, reg);
        if (Objects.nonNull(block)) return block;

        String prefix = (prefixOrInfix.isEmpty()) ? "" : prefixOrInfix + "_";
        String infix = (prefixOrInfix.isEmpty()) ? "" : "_" + prefixOrInfix;
        if (!suffix.isEmpty()) suffix = "_" + suffix;

        ResourceLocation[] targets = {
                // DEFAULT
                new ResourceLocation(id.getNamespace(), id.getPath() + infix + suffix),
                new ResourceLocation(id.getNamespace(), prefix + id.getPath() + suffix),
        };
        V found = null;
        for (var r : targets) {
            if (reg.containsKey(r)) {
                found = reg.get(r);
                break;
            }
        }
        return found;
    }

    public static Block findCrystal(ResourceLocation id) {
        ResourceLocation[] tests = makeKnownIDConventions(id,  "", "block");
        return Utils.findFirstInRegistry(BuiltInRegistries.BLOCK, tests);
    }

    public static class Finder extends SetFinderBuilder<CrystalType> {

        private Supplier<Block> blockCrystalFinder;

        public Finder(ResourceLocation id) {
            super(id, CrystalTypeRegistry.INSTANCE);
            this.crystalBlock(() -> findCrystal(id));
        }

        public CrystalType.Finder crystalBlock(Supplier<Block> crystalFinder) {
            this.blockCrystalFinder = crystalFinder;
            return this;
        }

        /// @param id Full Id of CrystalType as ResourceLocation
        public CrystalType.Finder crystalBlock(ResourceLocation id) {
            return this.crystalBlock(() -> BuiltInRegistries.BLOCK.getOptional(id)
                    .orElseThrow(() -> new IllegalStateException("Failed to find Crystal Block: " + id))
            );
        }

        /// @param nameCrystalBlock name of Crystal Block without modId or namespace
        public CrystalType.Finder crystalBlock(String nameCrystalBlock) {
            return this.crystalBlock(Utils.idWithOptionalNamespace(nameCrystalBlock, id.getNamespace()));
        }

        /**
         * @param prefix include the underscore, "_" if the blockId has one
         * @param suffix include the underscore, "_" if the blockId has one
         */
        public CrystalType.Finder crystalBlockAffix(String prefix, String suffix) {
            return crystalBlock(prefix + id.getPath() + suffix);
        }

        /**
         * @param suffix include the underscore, "_" if the blockId has one
         */
        public CrystalType.Finder crystalBlockSuffix(String suffix) {
            return crystalBlock(id.getPath() + suffix);
        }

        @Override
        @ApiStatus.Internal
        public Optional<CrystalType> get() {
            if (PlatHelper.isModLoaded(id.getNamespace())) {
                try {
                    Block mud = Preconditions.checkNotNull(blockCrystalFinder.get(), "Manual Finder - failed to find a mud block for {}", id);
                    var mudType = new CrystalType(id, mud);
                    childNames.forEach((key, value) -> {
                        try {
                            ItemLike obj = Preconditions.checkNotNull(value.get());
                            mudType.addChild(key, obj);
                        } catch (Exception e) {
                            GemsRealm.LOGGER.warn("Failed to get children for CrystalType: {} - {}. Ignored! ERROR: {}", id, key, e.getMessage());
                        }
                    });
                    return Optional.of(mudType);
                } catch (Exception e) {
                    GemsRealm.LOGGER.warn("Failed to find custom CrystalType: {} - ", id, e);
                }
            }
            return Optional.empty();
        }
    }

}
