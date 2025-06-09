package net.xelbayria.gems_realm.api.set;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
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
 * block, raw_block, stairs, slab, fence
 * bricks, smooth
 * door, trapdoor, lamp, chain, anvil, bars
 * ingot, nugget
**/
@SuppressWarnings("SameParameterValue")
public class MetalType extends RockType {

    public final Block block;

    protected MetalType(ResourceLocation id, Block block) {
        super(id, block);
        this.block = block;
    }

    @Override
    public String getTranslationKey() {
        return "metal_type." + this.getNamespace() + "." + this.getTypeName();
    }

    @Override
    public ItemLike mainChild() {
        return this.block;
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

    //!! FINDER
    public static class Finder implements SetFinder<MetalType> {

        private final Map<String, ResourceLocation> childNames = new HashMap<>();
        private final Supplier<Block> metalFinder;
        private final ResourceLocation id;

        public Finder(ResourceLocation id, Supplier<Block> metalBlock) {
            this.id = id;
            this.metalFinder = metalBlock;
        }

        public static Finder vanilla(String nameMetal){
            return simple("minecraft", nameMetal, nameMetal + "_block");
        }

        public static Finder simple(String modId, String nameMetalType, String nameMetalBlock) {
            return simple(new ResourceLocation(modId, nameMetalType), new ResourceLocation(modId, nameMetalBlock));
        }

        public static Finder simple(ResourceLocation nameMetal, ResourceLocation nameMetalBlock) {
            return new Finder(nameMetal, () -> BuiltInRegistries.BLOCK.get(nameMetalBlock));
        }

        public void addChild(String childType, String childName) {
            addChild(childType, new ResourceLocation(id.getNamespace(), childName));
        }

        public void addChild(String childType, ResourceLocation childName) {
            this.childNames.put(childType, childName);
        }

        @Override
        @ApiStatus.Internal
        public Optional<MetalType> get() {
            if (PlatHelper.isModLoaded(id.getNamespace())) {
                try {
                    Block metalFinder = this.metalFinder.get();
                    Block defaultKey = BuiltInRegistries.BLOCK.get(BuiltInRegistries.BLOCK.getDefaultKey()); // minecraft:air
                    if (metalFinder != defaultKey && metalFinder != null) {
                        var metalType = new MetalType(id, metalFinder);
                        childNames.forEach((key, value) -> {
                            if (BuiltInRegistries.ITEM.containsKey(value)) metalType.addChild(key, BuiltInRegistries.ITEM.get(value));
                            else if (BuiltInRegistries.BLOCK.containsKey(value)) metalType.addChild(key, BuiltInRegistries.BLOCK.get(value));
                            else GemsRealm.LOGGER.error("Failed to get children for MetalType: {} - {}", id, key);
                        });
                        return Optional.of(metalType);
                    }
                } catch (Exception e) {
                    GemsRealm.LOGGER.warn("Failed to find custom metal type {}: {}", id, e.getMessage());
                }
//                GemsRealm.LOGGER.warn("Failed to find custom metal type {}", id);
            }
            return Optional.empty();
        }
    }

}
