package net.xelbayria.gems_realm.api.set.dust;

import com.google.common.base.Preconditions;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.moonlight.core.ClientConfigs;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
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

@SuppressWarnings("SameParameterValue")
public class DustType extends RockType {

    protected DustType(ResourceLocation id, Block blockDust) {
        super(id, blockDust);

        if (ClientConfigs.BLOCKTYPES_DEBUG.get() && !this.isVanilla()) appendToDebugFile(getTranslationKey());
    }

    @Override
    public String getTranslationKey() {
        return "dust_type." + this.getNamespace() + "." + this.getTypeName();
    }

    @Override
    protected void initializeChildrenBlocks() {
            super.initializeChildrenBlocks();
    }

    @Override
    protected void initializeChildrenItems() {
        this.addChild("dust", findRelatedItem("", "dust"));
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

    public static Block findDustBlock(ResourceLocation id) {
        ResourceLocation[] tests = makeKnownIDConventions(id,  "dust");
        return Utils.findFirstInRegistry(BuiltInRegistries.BLOCK, tests);
    }

    public static class Finder extends SetFinderBuilder<DustType> {

        private Supplier<Block> blockDustFinder;

        public Finder(ResourceLocation id) {
            super(id, DustTypeRegistry.INSTANCE);
            this.dustBlock(() -> findDustBlock(id));
        }

        public DustType.Finder dustBlock(Supplier<Block> dustFinder) {
            this.blockDustFinder = dustFinder;
            return this;
        }

        /// @param id Full Id of DustType as ResourceLocation
        public DustType.Finder dustBlock(ResourceLocation id) {
            return this.dustBlock(() -> BuiltInRegistries.BLOCK.getOptional(id)
                    .orElseThrow(() -> new IllegalStateException("Failed to find Dust Block: " + id))
            );
        }

        /// @param nameDustBlock name of Dust Block without modId or namespace
        public DustType.Finder dustBlock(String nameDustBlock) {
            return this.dustBlock(Utils.idWithOptionalNamespace(nameDustBlock, id.getNamespace()));
        }

        /**
         * @param prefix include the underscore, "_" if the blockId has one
         * @param suffix include the underscore, "_" if the blockId has one
         */
        public DustType.Finder dustBlockAffix(String prefix, String suffix) {
            return dustBlock(prefix + id.getPath() + suffix);
        }

        /**
         * @param suffix include the underscore, "_" if the blockId has one
         */
        public DustType.Finder dustBlockSuffix(String suffix) {
            return dustBlock(id.getPath() + suffix);
        }

        @Override
        @ApiStatus.Internal
        public Optional<DustType> get() {
            if (PlatHelper.isModLoaded(id.getNamespace())) {
                try {
                    Block dust = Preconditions.checkNotNull(blockDustFinder.get(), "Manual Finder - failed to find a dust block for {}", id);
                    var dustType = new DustType(id, dust);
                    childNames.forEach((key, value) -> {
                        try {
                            ItemLike obj = Preconditions.checkNotNull(value.get());
                            dustType.addChild(key, obj);
                        } catch (Exception e) {
                            GemsRealm.LOGGER.warn("Failed to get children for DustType: {} - {}. Ignored! ERROR: {}", id, key, e.getMessage());
                        }
                    });
                    return Optional.of(dustType);
                } catch (Exception e) {
                    GemsRealm.LOGGER.warn("Failed to find custom DustType: {} - ", id, e);
                }
            }
            return Optional.empty();
        }
    }

}
