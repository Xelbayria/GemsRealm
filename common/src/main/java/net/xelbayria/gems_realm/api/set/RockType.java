package net.xelbayria.gems_realm.api.set;

import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Childkey Availability:
 * block, raw_block,
 * stairs, slab, fence,
 * chiseled, bricks, brick_stairs, brick_slab, brick_wall, brick_tiles,
 * cracked_bricks, mossy_bricks, mossy_brick_slab, mossy_brick_stairs, mossy_brick_wall,
 * smooth, smooth_stairs, smooth_slab, smooth_wall
**/
@SuppressWarnings("SameParameterValue")
public abstract class RockType extends BlockType{

    public final Block block;

    protected RockType(ResourceLocation id, Block block) {
        super(id);
        this.block = block;
    }

    @Override
    protected void initializeChildrenBlocks() {
        this.addChild("block", this.block);
        this.addChild("raw_block", findRelatedBlock("raw", "block"));
        this.addChild("stairs", findChildBlocks("stair"));
        this.addChild("slab", findChildBlocks("slab"));
        this.addChild("fence", findChildBlocks("fence"));
        this.addChild("chiseled", findRelatedBlock("chiseled", ""));

        Block bricks = this.findBrickEntry("", "");
        this.addChild("bricks", bricks);
        if (bricks != null) {
            this.addChild("brick_stairs", findBrickEntry("",  "stairs"));
            this.addChild("brick_slab", findBrickEntry("",  "slab"));
            this.addChild("brick_wall", findBrickEntry("",  "wall"));
            this.addChild("brick_tiles", findBrickEntry("",  "tiles"));
            this.addChild("cracked_bricks", findBrickEntry("cracked", ""));
            this.addChild("mossy_bricks", findBrickEntry("mossy", ""));
            this.addChild("mossy_brick_slab", findBrickEntry("mossy", "slab"));
            this.addChild("mossy_brick_stairs", findBrickEntry("mossy", "stairs"));
            this.addChild("mossy_brick_wall", findBrickEntry("mossy", "wall"));
        }

        Block smooth = findRelatedEntry("smooth", BuiltInRegistries.BLOCK);
        this.addChild("smooth", smooth);
        if (Objects.nonNull(smooth)) {
            this.addChild("smooth_stairs", findRelatedBlock("smooth", "stairs"));
            this.addChild("smooth_slab", findRelatedBlock("smooth", "slab"));
            this.addChild("smooth_wall", findRelatedBlock("smooth", "wall"));
        }

    }

    @Override
    protected void initializeChildrenItems() {}

    /// Check for block with "s" and without "s"
    private @Nullable Block findChildBlocks(String suffix) {
        var first = this.findRelatedEntry("", suffix, BuiltInRegistries.BLOCK);
        if (first != null) return first;
        return this.findRelatedEntry("", suffix + "s", BuiltInRegistries.BLOCK);
    }

    /// Checking the id for "bricks" or "brick"
    private @Nullable Block findBrickEntry(String prefix, String suffix) {
        String suffixed = (suffix.isEmpty()) ? "" : "_" + suffix;

        Block first = this.findRelatedEntry(prefix, "brick" + suffixed, BuiltInRegistries.BLOCK);
        if (first != null) return first;

        return this.findRelatedEntry(prefix, "bricks" + suffixed, BuiltInRegistries.BLOCK);
    }

    protected @Nullable Block findRelatedBlock(String prefixOrInfix, String suffix) {
        return findRelatedEntry(prefixOrInfix, suffix, BuiltInRegistries.BLOCK);
    }

    protected @Nullable Item findRelatedItem(String prefixOrInfix, String suffix) {
        return findRelatedEntry(prefixOrInfix, suffix, BuiltInRegistries.ITEM);
    }

    @Override
    protected @Nullable <V> V findRelatedEntry(String prefixOrInfix, String suffix, Registry<V> reg) {
        String prefix_ = (prefixOrInfix.isEmpty()) ? "" : prefixOrInfix + "_";
        String _infix = (prefixOrInfix.isEmpty()) ? "" : "_" + prefixOrInfix;
        String _suffix = (suffix.isEmpty()) ? "" : "_" + suffix;

        ResourceLocation[] targets = {
                // DEFAULT
                new ResourceLocation(id.getNamespace(), id.getPath() + _infix + _suffix),
                new ResourceLocation(id.getNamespace(), prefix_ + id.getPath() + _suffix),

                // Mo' Shiz: Include children: ingot, gem
                new ResourceLocation(id.getNamespace(), "gem/"+ id.getPath() + _suffix),
                // Mo' Shiz: Include children: block, raw_block
                new ResourceLocation(id.getNamespace(), "resources/"+ prefix_ + id.getPath() + _suffix),
                // Mo' Shiz: Include children: stairs, slab...
                new ResourceLocation(id.getNamespace(), suffix +"/"+ id.getPath() + _suffix),
                // Mo' Shiz: Include children with no underscore
                new ResourceLocation(id.getNamespace(), suffix +"/"+ id.getPath() + suffix),
                // Mo' Shiz: Include children with no suffix
                new ResourceLocation(id.getNamespace(), suffix +"/"+ id.getPath())
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
    public ItemLike mainChild() {
        return block;
    }

    protected static ResourceLocation[] makeKnownIDConventions(ResourceLocation id, String... affixKeyword) {
        List<ResourceLocation> resources = new ArrayList<>();
        for (String keyword : affixKeyword) {
            String path = id.getPath();
            String namespace = id.getNamespace();

            String suffixed = (keyword.isEmpty()) ? "" : "_" + keyword;
            String prefixed = (keyword.isEmpty()) ? "" : keyword + "_";

            resources.add(new ResourceLocation(namespace, path + suffixed));
            resources.add(new ResourceLocation(namespace, prefixed + path));
        }
        return resources.toArray(new ResourceLocation[0]);
    }

}
