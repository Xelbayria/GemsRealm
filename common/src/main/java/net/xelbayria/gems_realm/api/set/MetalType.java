package net.xelbayria.gems_realm.api.set;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import net.xelbayria.gems_realm.GemsRealm;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class MetalType extends BlockType {

    /**
     * Childkey Availability:
     * stone, stairs, slab, wall, button, pressure_plate, smooth_stone
     * polished, polished_stairs, polished_slab
     * bricks, brick_stairs, brick_slab, brick_wall, cracked_bricks, brick_tiles
    **/

    public final Block block;
    public final Item ingot;

    protected MetalType(ResourceLocation id, Item ingot, Block block) {
        super(id);
        this.ingot = ingot;
        this.block = block;
    }

    @Override
    public String getTranslationKey() {
        return "metal_type." + this.getNamespace() + "." + this.getTypeName();
    }

    @Override
    public ItemLike mainChild() {
        return this.getBlockOfThis("block");
//        return this.ingot;
    }

    @Override
    protected void initializeChildrenBlocks() {

        // Support TFC & AFC
        if (this.id.getNamespace().matches("tfc|afc")) {
            this.addChild("trapdoor", findRelatedEntry("trapdoor", BuiltInRegistries.BLOCK));
            this.addChild("lamp", findRelatedEntry("lamp", BuiltInRegistries.BLOCK));
            this.addChild("chain", findRelatedEntry("chain", BuiltInRegistries.BLOCK));
            this.addChild("anvil", findRelatedEntry("anvil", BuiltInRegistries.BLOCK));
            this.addChild("bars", findRelatedEntry("bars", BuiltInRegistries.BLOCK));
        }
        else {
        }

    }

    @Override
    protected void initializeChildrenItems() {
        this.addChild("ingot", this.ingot);
        this.addChild("nugget", findRelatedEntry("nugget", BuiltInRegistries.ITEM));
    }

    @Override
    protected @Nullable <V> V findRelatedEntry(String prefixOrInfix, String suffix, Registry<V> reg) {
        if (!suffix.isEmpty()) suffix = "_" + suffix;

        ResourceLocation[] targets = {
                // DEFAULT
                new ResourceLocation(id.getNamespace(), id.getPath() +"_"+ prefixOrInfix + suffix),
                new ResourceLocation(id.getNamespace(), prefixOrInfix +"_"+ id.getPath() + suffix),

                // TFC & AFC: Include children of metal_type: block
                new ResourceLocation(id.getNamespace(), "metal/" + prefixOrInfix + suffix +"/"+ id.getPath()),
                // TFC & AFC: Include children of metal_type: trapdoor, lamp, bars, anvil, ingot, block
                new ResourceLocation(id.getNamespace(), "metal/"+ prefixOrInfix +"/"+ id.getPath() + suffix),

                // Mo' Shiz: Include children: ingot, raw,
                new ResourceLocation(id.getNamespace(), "gem/"+ prefixOrInfix +"/"+ id.getPath() + suffix),
                // Mo' Shiz: Include children: block
                new ResourceLocation(id.getNamespace(), "resources/"+ id.getPath() +"_"+ prefixOrInfix + suffix),
                // Mo' Shiz: Include children: fence, stair, slab
                new ResourceLocation(id.getNamespace(), prefixOrInfix +"/"+ id.getPath() + suffix)
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

    @Override
    protected <V> V findRelatedEntry(String prefixOrInfix, Registry<V> reg) {
        return findRelatedEntry(prefixOrInfix, "", reg);
    }

    public static class Finder implements SetFinder<MetalType> {

        private final Map<String, ResourceLocation> childNames = new HashMap<>();
        private final Supplier<Item> ingotFinder;
        private final Supplier<Block> blockFinder;
        private final ResourceLocation id;

        public Finder(ResourceLocation id, Supplier<Item> ingot, Supplier<Block> block) {
            this.id = id;
            this.ingotFinder = ingot;
            this.blockFinder = block;
        }

        public static Finder vanilla(String nameMetal){
            return simple("minecraft", nameMetal, nameMetal);
        }

        public static Finder simple(String modId, String nameMetalType, String nameMetal) {
            return simple(new ResourceLocation(modId, nameMetalType), new ResourceLocation(modId, nameMetal), new ResourceLocation(modId, nameMetal + "_block"));
        }

        public static Finder simple(ResourceLocation nameStoneTYpe, ResourceLocation nameMetal, ResourceLocation nameMetalBlock) {
            return new Finder(nameStoneTYpe,
                    () -> BuiltInRegistries.ITEM.get(nameMetal),
                    () -> BuiltInRegistries.BLOCK.get(nameMetalBlock)
            );
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
                    Item ingot = ingotFinder.get();
                    Block block = blockFinder.get();
                    Item defaultKey = BuiltInRegistries.ITEM.get(BuiltInRegistries.ITEM.getDefaultKey());
                    if (ingot != defaultKey && ingot != null) {
                        var w = new MetalType(id, ingot, block);
                        childNames.forEach((key, value) -> w.addChild(key, BuiltInRegistries.BLOCK.get(value)));
                        return Optional.of(w);
                    }
                } catch (Exception ignored) {
                }
                GemsRealm.LOGGER.warn("Failed to find custom stone type {}", id);
            }
            return Optional.empty();
        }
    }

}
