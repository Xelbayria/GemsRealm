package net.xelbayria.gems_realm.api.set.metal;

import com.google.common.base.Preconditions;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.util.Utils;
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

/**
 * Childkey Availability:
 * block, raw_block, stairs, slab, fence
 * bricks, smooth
 * door, trapdoor, lamp, chain, anvil, bars
 * ingot, nugget
**/
@SuppressWarnings("SameParameterValue")
public class MetalType extends RockType {

    protected MetalType(ResourceLocation id, Block block) {
        super(id, block);
    }

    @Override
    public String getTranslationKey() {
        return "metal_type." + this.getNamespace() + "." + this.getTypeName();
    }

    @Override
    protected void initializeChildrenBlocks() {
            super.initializeChildrenBlocks();
            this.addChild("trapdoor", findRelatedBlock("", "trapdoor"));
            this.addChild("lamp", findRelatedBlock("", "lamp"));
            this.addChild("chain", findRelatedBlock("", "chain"));
            this.addChild("anvil", findRelatedBlock("", "anvil"));
            this.addChild("bars", findRelatedBlock("", "bars"));
    }

    @Override
    protected void initializeChildrenItems() {
        super.initializeChildrenItems();
    }

    @Override
    protected @Nullable <V> V findRelatedEntry(String prefixOrInfix, String suffix, Registry<V> reg) {
        var block = super.findRelatedEntry(prefixOrInfix, suffix, reg);
        if (Objects.nonNull(block)) return block;

        if (this.getId().getNamespace().matches("tfc|afc")) {
            String prefix = (prefixOrInfix.isEmpty()) ? "" : prefixOrInfix + "_";
//            String infix = (prefixOrInfix.isEmpty()) ? "" : "_" + prefixOrInfix;
            String withoutUnderscore = suffix;
            if (!suffix.isEmpty()) suffix = "_" + suffix;

            ResourceLocation[] targets = { //!! CHECK FOR GEM in TFC
                    // TFC & AFC: Include children of metal_type: block
                    new ResourceLocation(id.getNamespace(), "metal/" + prefix + withoutUnderscore +"/"+ id.getPath()),
                    // TFC & AFC: Include children of metal_type: trapdoor, lamp, bars, anvil, ingot, block
                    new ResourceLocation(id.getNamespace(), "metal/"+ prefixOrInfix +"/"+ id.getPath() + suffix),
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
        return null;
    }

    public static Block findMetalBlock(ResourceLocation id) {
        ResourceLocation[] tests = makeKnownIDConventions(id,  "block");
        return Utils.findFirstInRegistry(BuiltInRegistries.BLOCK, tests);
    }

    //!! FINDER
    public static class Finder extends SetFinderBuilder<MetalType> {

        private Supplier<Block> blockMetalFinder;

        public Finder(ResourceLocation id) {
            super(id, MetalTypeRegistry.INSTANCE);
            this.metalBlock(() -> findMetalBlock(id));
        }

        public MetalType.Finder metalBlock(Supplier<Block> metalFinder) {
            this.blockMetalFinder = metalFinder;
            return this;
        }

        /// @param id Full Id of MetalType as ResourceLocation
        public MetalType.Finder metalBlock(ResourceLocation id) {
            return this.metalBlock(() -> BuiltInRegistries.BLOCK.getOptional(id)
                    .orElseThrow(() -> new IllegalStateException("Failed to find Metal Block: " + id))
            );
        }

        /// @param nameMetalBlock name of Metal Block without modId or namespace
        public MetalType.Finder metalBlock(String nameMetalBlock) {
            return this.metalBlock(Utils.idWithOptionalNamespace(nameMetalBlock, id.getNamespace()));
        }

        /**
         * @param prefix include the underscore, "_" if the blockId has one
         * @param suffix include the underscore, "_" if the blockId has one
         */
        public MetalType.Finder metalBlockAffix(String prefix, String suffix) {
            return metalBlock(prefix + id.getPath() + suffix);
        }

        /**
         * @param suffix include the underscore, "_" if the blockId has one
         */
        public MetalType.Finder metalBlockSuffix(String suffix) {
            return metalBlock(id.getPath() + suffix);
        }

        @Override
        @ApiStatus.Internal
        public Optional<MetalType> get() {
            if (PlatHelper.isModLoaded(id.getNamespace())) {
                try {
                    Block metal = Preconditions.checkNotNull(blockMetalFinder.get(), "Manual Finder - failed to find a metal block for {}", id);
                    var metalType = new MetalType(id, metal);
                    childNames.forEach((key, value) -> {
                        try {
                            ItemLike obj = Preconditions.checkNotNull(value.get());
                            metalType.addChild(key, obj);
                        } catch (Exception e) {
                            GemsRealm.LOGGER.warn("Failed to get children for MetalType: {} - {}. Ignored! ERROR: {}", id, key, e.getMessage());
                        }
                    });
                    return Optional.of(metalType);
                } catch (Exception e) {
                    GemsRealm.LOGGER.warn("Failed to find custom MetalType: {} - ", id, e);
                }
            }
            return Optional.empty();
        }
    }

}
