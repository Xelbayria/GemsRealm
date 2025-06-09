package net.xelbayria.gems_realm.api.set;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.xelbayria.gems_realm.GemsRealm;
import org.jetbrains.annotations.ApiStatus;

import java.util.HashMap;
import java.util.Map;
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

    @Override
    public ItemLike mainChild() {
        return this.block;
    }

    @SuppressWarnings("unused")
    public static class Finder implements SetFinder<GemType> {

        private final Map<String, ResourceLocation> childNames = new HashMap<>();
        private final Supplier<Block> blockGem;
        private final ResourceLocation id;

        public Finder(ResourceLocation id, Supplier<Block> blockGem) {
            this.id = id;
            this.blockGem = blockGem;
        }

        public static Finder vanilla(String nameGem){
            return simple("minecraft", nameGem, nameGem + "_block");
        }

        public static Finder simple(String modId, String nameGemType, String nameGemBlock) {
            return simple(new ResourceLocation(modId, nameGemType), new ResourceLocation(modId, nameGemBlock));
        }

        public static Finder simple(ResourceLocation nameGemType, ResourceLocation nameGem) {
            return new Finder(nameGemType, () -> BuiltInRegistries.BLOCK.get(nameGem));
        }

        public void addChild(String childType, String childName) {
            addChild(childType, new ResourceLocation(id.getNamespace(), childName));
        }

        public void addChild(String childType, ResourceLocation childName) {
            this.childNames.put(childType, childName);
        }

        @Override
        @ApiStatus.Internal
        public Optional<GemType> get() {
            if (PlatHelper.isModLoaded(id.getNamespace())) {
                try {
                    Block blockGem = this.blockGem.get();
                    Block defaultKey = BuiltInRegistries.BLOCK.get(BuiltInRegistries.BLOCK.getDefaultKey()); // minecraft:air
                    if (blockGem != defaultKey && blockGem != null) {
                        var gemType = new GemType(id, blockGem);
                        childNames.forEach((key, value) ->
                                gemType.addChild(key, BuiltInRegistries.BLOCK.get(value))
                        );
                        return Optional.of(gemType);
                    }
                } catch (Exception e) {
                    GemsRealm.LOGGER.warn("Failed to find custom gem type: {} - {}", id, e.getMessage());
                }
            }
            return Optional.empty();
        }
    }

}
