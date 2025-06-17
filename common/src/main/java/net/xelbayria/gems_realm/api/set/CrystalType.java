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
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Childkey Availability:
 * block,
 * cluster, glinted_cluster, budding
 * shard (ITEM)
**/
@SuppressWarnings("SameParameterValue")
public class CrystalType extends RockType {


//    public final Block block;

    protected CrystalType(ResourceLocation id, Block blockCrystal) {
        super(id, blockCrystal);
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

    public static class Finder implements SetFinder<CrystalType> {

        private final Map<String, ResourceLocation> childNames = new HashMap<>();
        private final Supplier<Block> blockCrystal;
        private final ResourceLocation id;

        public Finder(ResourceLocation id, Supplier<Block> blockCrystal) {
            this.id = id;
            this.blockCrystal = blockCrystal;
        }

        public static Finder vanilla(String nameCrystsal){
            return simple("minecraft", nameCrystsal, nameCrystsal + "_block");
        }

        public static Finder simple(String modId, String nameCrystsalType, String nameCrystalBlock) {
            return simple(new ResourceLocation(modId, nameCrystsalType), new ResourceLocation(modId, nameCrystalBlock));
        }

        public static Finder simple(ResourceLocation nameCrystsal, ResourceLocation nameCrystalBlock) {
            return new Finder(nameCrystsal, () -> BuiltInRegistries.BLOCK.get(nameCrystalBlock));
        }

        public void addChild(String childType, String childName) {
            addChild(childType, new ResourceLocation(id.getNamespace(), childName));
        }

        public void addChild(String childType, ResourceLocation childName) {
            this.childNames.put(childType, childName);
        }

        @Override
        @ApiStatus.Internal
        public Optional<CrystalType> get() {
            if (PlatHelper.isModLoaded(id.getNamespace())) {
                try {
                    Block blockCrystal = this.blockCrystal.get();
                    Block defaultKey = BuiltInRegistries.BLOCK.get(BuiltInRegistries.BLOCK.getDefaultKey()); // minecraft:air
                    if (blockCrystal != defaultKey && blockCrystal != null) {
                        var crystalType = new CrystalType(id, blockCrystal);
                        childNames.forEach((key, value) ->
                                crystalType.addChild(key, BuiltInRegistries.BLOCK.get(value))
                        );
                        return Optional.of(crystalType);
                    }
                } catch (Exception e) {
                    GemsRealm.LOGGER.warn("Failed to find custom crystal type {} - {}", id, e.getMessage());
                }
            }
            return Optional.empty();
        }
    }

}
