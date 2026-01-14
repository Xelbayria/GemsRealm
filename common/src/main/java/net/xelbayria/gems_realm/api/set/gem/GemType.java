package net.xelbayria.gems_realm.api.set.gem;

import com.google.common.base.Preconditions;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.api.set.RockType;
import org.jetbrains.annotations.ApiStatus;

import java.util.Optional;
import java.util.function.Supplier;

public class GemType extends RockType {

    protected GemType(ResourceLocation id, Block blockGem) {
        super(id, blockGem);
    }

    @Override
    public String getTranslationKey() {
        return "gem_type." + this.getNamespace() + "." + this.getTypeName();
    }

    @Override
    protected void initializeChildrenBlocks() {
        super.initializeChildrenBlocks();
    }

    @Override
    protected void initializeChildrenItems() {
        super.initializeChildrenItems();

        this.addChild("gem", this.findRelatedItem("", ""));
    }

    public static Block findGemBlock(ResourceLocation id) {
        ResourceLocation[] tests = makeKnownIDConventions(id,  "block");
        Finder.blockId = tests[0];
        return Utils.findFirstInRegistry(BuiltInRegistries.BLOCK, tests);
    }

    @SuppressWarnings("unused")
    public static class Finder extends SetFinderBuilder<GemType> {

        private Supplier<Block> blockGemFinder;
        public static ResourceLocation blockId;

        public Finder(ResourceLocation id) {
            super(id, GemTypeRegistry.INSTANCE);
            this.gemBlock(() -> findGemBlock(id));
        }

        public GemType.Finder gemBlock(Supplier<Block> gemFinder) {
            this.blockGemFinder = gemFinder;
            return this;
        }

        /// @param id Full Id of GemType as ResourceLocation
        public GemType.Finder gemBlock(ResourceLocation id) {
            return this.gemBlock(() -> BuiltInRegistries.BLOCK.getOptional(id)
                    .orElseThrow(() -> new IllegalStateException("Failed to find Gem Block: " + id))
            );
        }

        /// @param nameGemBlock name of Gem Block without modId or namespace
        public GemType.Finder gemBlock(String nameGemBlock) {
            return this.gemBlock(Utils.idWithOptionalNamespace(nameGemBlock, id.getNamespace()));
        }

        /**
         * @param prefix include the underscore, "_" if the blockId has one
         * @param suffix include the underscore, "_" if the blockId has one
         */
        public GemType.Finder gemBlockAffix(String prefix, String suffix) {
            return gemBlock(prefix + id.getPath() + suffix);
        }

        /**
         * @param suffix include the underscore, "_" if the blockId has one
         */
        public GemType.Finder gemBlockSuffix(String suffix) {
            return gemBlock(id.getPath() + suffix);
        }

        @Override
        @ApiStatus.Internal
        public Optional<GemType> get() {
            if (PlatHelper.isModLoaded(id.getNamespace())) {
                try {
                    Block gem = Preconditions.checkNotNull(blockGemFinder.get(), "Manual Finder - failed to find a Gem Block wtih ID:", blockId);
                    var gemType = new GemType(id, gem);
                    childNames.forEach((key, value) -> {
                        try {
                            ItemLike obj = Preconditions.checkNotNull(value.get());
                            gemType.addChild(key, obj);
                        } catch (Exception e) {
                            GemsRealm.LOGGER.warn("Failed to get children for GemType: {} - {}. Ignored! ERROR: {}", id, key, e.getMessage());
                        }
                    });
                    return Optional.of(gemType);
                } catch (Exception e) {
                    GemsRealm.LOGGER.warn("Failed to find custom GemType: {} - ", id, e);
                }
            }
            return Optional.empty();
        }
    }

}
