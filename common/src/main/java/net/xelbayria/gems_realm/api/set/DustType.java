package net.xelbayria.gems_realm.api.set;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.xelbayria.gems_realm.GemsRealm;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Childkey Availability:
 * block,
 * cluster, glinted_cluster
 * shard
**/
@SuppressWarnings("SameParameterValue")
public class DustType extends RockType {

    protected DustType(ResourceLocation id, Block blockCrystal) {
        super(id, blockCrystal);
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

     protected Block findRelatedBlock(String prefixOrInfix, String suffix) {
        return findRelatedEntry(prefixOrInfix, suffix, BuiltInRegistries.BLOCK);
    }

     protected Item findRelatedItem(String prefixOrInfix, String suffix) {
        return findRelatedEntry(prefixOrInfix, suffix, BuiltInRegistries.ITEM);
    }

    @Override
    protected @Nullable <V> V findRelatedEntry(String prefixOrInfix, String suffix, Registry<V> reg) {
        if (!suffix.isEmpty()) suffix = "_" + suffix;

        ResourceLocation[] targets = {
                // DEFAULT
                new ResourceLocation(id.getNamespace(), id.getPath() +"_"+ prefixOrInfix + suffix),
                new ResourceLocation(id.getNamespace(), prefixOrInfix +"_"+ id.getPath() + suffix),
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

    public static class Finder implements SetFinder<DustType> {

        private final Map<String, ResourceLocation> childNames = new HashMap<>();
        private final Supplier<Block> blockCrystal;
        private final ResourceLocation id;

        public Finder(ResourceLocation id, Supplier<Block> blockCrystal) {
            this.id = id;
            this.blockCrystal = blockCrystal;
        }

        public static Finder vanilla(String nameDust){
            return simple("minecraft", nameDust, nameDust + "_block");
        }

        public static Finder simple(String modId, String nameCrystsalType, String nameCrystalBlock) {
            return simple(new ResourceLocation(modId, nameCrystsalType), new ResourceLocation(modId, nameCrystalBlock));
        }

        public static Finder simple(ResourceLocation nameDust, ResourceLocation nameDustBlock) {
            return new Finder(nameDust, () -> BuiltInRegistries.BLOCK.get(nameDustBlock));
        }

        public void addChild(String childType, String childName) {
            addChild(childType, new ResourceLocation(id.getNamespace(), childName));
        }

        public void addChild(String childType, ResourceLocation childName) {
            this.childNames.put(childType, childName);
        }

        @Override
        @ApiStatus.Internal
        public Optional<DustType> get() {
            if (PlatHelper.isModLoaded(id.getNamespace())) {
                try {
                    Block blockDust = this.blockCrystal.get();
                    Block defaultKey = BuiltInRegistries.BLOCK.get(BuiltInRegistries.BLOCK.getDefaultKey()); // minecraft:air
                    if (blockDust != defaultKey && blockDust != null) {
                        var dustType = new DustType(id, blockDust);
                        childNames.forEach((key, value) -> {
                                if (BuiltInRegistries.ITEM.containsKey(value)) dustType.addChild(key, BuiltInRegistries.ITEM.get(value));
                                else if (BuiltInRegistries.BLOCK.containsKey(value)) dustType.addChild(key, BuiltInRegistries.BLOCK.get(value));
                                else GemsRealm.LOGGER.error("Failed to get children for DustType: {} - {}", id, key);
                        });
                        return Optional.of(dustType);
                    }
                } catch (Exception e) {
                    GemsRealm.LOGGER.warn("Failed to find custom dust type {} - {}", id, e.getMessage());
                }
            }
            return Optional.empty();
        }
    }

}
