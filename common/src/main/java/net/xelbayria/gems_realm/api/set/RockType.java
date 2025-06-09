package net.xelbayria.gems_realm.api.set;

import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

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

        Block bricks = this.findRelatedEntry("bricks", BuiltInRegistries.BLOCK);
        this.addChild("bricks", bricks);
        if (bricks != null) {
            this.addChild("brick_stairs", findChildBricks( "stairs"));
            this.addChild("brick_slab", findChildBricks( "slab"));
            this.addChild("brick_wall", findChildBricks( "wall"));
            this.addChild("brick_tiles", findChildBricks( "tiles"));
            this.addChild("cracked_bricks", findRelatedBrick("cracked", ""));
            this.addChild("mossy_bricks", findRelatedBrick("mossy", ""));
            this.addChild("mossy_brick_slab", findRelatedBrick("mossy", "slab"));
            this.addChild("mossy_brick_stairs", findRelatedBrick("mossy", "stairs"));
            this.addChild("mossy_brick_wall", findRelatedBrick("mossy", "wall"));
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
    protected void initializeChildrenItems() {
        this.addChild("ingot", findRelatedItem("", "ingot"));
    }

    /// Check for block with "s" and without "s"
    private @Nullable Block findChildBlocks(String suffix) {
        var first = this.findRelatedEntry("", suffix, BuiltInRegistries.BLOCK);
        if (first != null) return first;
        return this.findRelatedEntry("",suffix + "s", BuiltInRegistries.BLOCK);
    }

    /// Concatenation: "brick_" + suffix
    private @Nullable Block findChildBricks(String suffix) {
        var first = this.findRelatedEntry("brick_" + suffix, BuiltInRegistries.BLOCK);
        if (first != null) return first;
        return this.findRelatedEntry("bricks_" + suffix, BuiltInRegistries.BLOCK);
    }
    /// Concatentation: prefix + "_brick" + "_" + suffix
    private @Nullable Block findRelatedBrick(String prefix, String suffix) {
        suffix = (suffix.isEmpty()) ? "" : "_" + suffix;

        var first = this.findRelatedEntry(prefix,"brick" + suffix, BuiltInRegistries.BLOCK);
        if (first != null) return first;
        return this.findRelatedEntry(prefix,"bricks" + suffix, BuiltInRegistries.BLOCK);
    }

    protected @Nullable Block findRelatedBlock(String prefixOrInfix, String suffix) {
        return findRelatedEntry(prefixOrInfix, suffix, BuiltInRegistries.BLOCK);
    }

    protected @Nullable Item findRelatedItem(String prefixOrInfix, String suffix) {
        return findRelatedEntry(prefixOrInfix, suffix, BuiltInRegistries.ITEM);
    }

    @Override
    protected @Nullable <V> V findRelatedEntry(String prefixOrInfix, String suffix, Registry<V> reg) {
        String prefix = (prefixOrInfix.isEmpty()) ? "" : prefixOrInfix + "_";
        String infix = (prefixOrInfix.isEmpty()) ? "" : "_" + prefixOrInfix;
        String withoutUnderscore = suffix;
        if (!suffix.isEmpty()) suffix = "_" + suffix;

        ResourceLocation[] targets = {
                // DEFAULT
                new ResourceLocation(id.getNamespace(), id.getPath() + infix + suffix),
                new ResourceLocation(id.getNamespace(), prefix + id.getPath() + suffix),

                // Mo' Shiz: Include children: ingot, gem
                new ResourceLocation(id.getNamespace(), "gem/"+ id.getPath() + suffix),
                // Mo' Shiz: Include children: block, raw_block
                new ResourceLocation(id.getNamespace(), "resources/"+ prefix + id.getPath() + suffix),
                // Mo' Shiz: Include children: fence, stairs, slab
                new ResourceLocation(id.getNamespace(), withoutUnderscore +"/"+ id.getPath() + suffix),
                // Mo' Shiz: Include children: fence, stairs, slab (Another Naming Scheme)
                new ResourceLocation(id.getNamespace(), withoutUnderscore +"/"+ id.getPath() + withoutUnderscore),
                // Mo' Shiz: Include children: fence, stairs, slab (Another Naming Scheme)
                new ResourceLocation(id.getNamespace(), withoutUnderscore +"/"+ id.getPath())
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

}
