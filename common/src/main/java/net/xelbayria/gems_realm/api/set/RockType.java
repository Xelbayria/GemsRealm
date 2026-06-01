package net.xelbayria.gems_realm.api.set;

import net.mehvahdjukaar.moonlight.api.misc.MapRegistry;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.xelbayria.gems_realm.api.set.VanillaRockChildKeys.*;

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

    private static String childInfix = "";

    protected RockType(ResourceLocation id, Block block) {
        super(id);
        this.block = block;
    }

    /**
     * REASON: Mod like Tech-Reborn has blockID, "storage_block" for slab, stairs, wall.
     *<br>NOTE: the method will return "infix" + "block" or "infix_" + "block"
     * @param infix "infix" or "infix_"
    */
    public static void setChildInfix(String infix) {
        childInfix = infix;
    }

    @Override
    protected void initializeChildrenBlocks() {

        this.addChild(BLOCK, this.block);
        this.addChild(RAW_BLOCK, findRelatedBlock("raw", "block"));
        this.addChild(STAIRS, findChildBlocks(STAIRS));
        this.addChild(SLAB, findChildBlocks(SLAB));
        this.addChild(WALL, findChildBlocks(WALL));
        this.addChild(FENCE, findChildBlocks(FENCE));
        this.addChild(CHISELED, findRelatedBlock("chiseled", ""));

        Block bricks = this.findBrickEntry("", "");
        this.addChild(BRICKS, bricks);
        if (bricks != null) {
            this.addChild(BRICK_STAIRS, findBrickEntry("",  "stairs"));
            this.addChild(BRICK_SLAB, findBrickEntry("",  "slab"));
            this.addChild(BRICK_WALL, findBrickEntry("",  "wall"));
            this.addChild(BRICK_TILES, findBrickEntry("",  "tiles"));
            this.addChild(CRACKED_BRICKS, findBrickEntry("cracked", ""));
            this.addChild(MOSSY_BRICKS, findBrickEntry("mossy", ""));
            this.addChild(MOSSY_BRICK_SLAB, findBrickEntry("mossy", "slab"));
            this.addChild(MOSSY_BRICK_STAIRS, findBrickEntry("mossy", "stairs"));
            this.addChild(MOSSY_BRICK_WALL, findBrickEntry("mossy", "wall"));
        }

        Block smooth = findRelatedEntry("smooth", BuiltInRegistries.BLOCK);
        this.addChild(SMOOTH, smooth);
        if (Objects.nonNull(smooth)) {
            this.addChild(SMOOTH_STAIRS, findRelatedBlock("smooth", "stairs"));
            this.addChild(SMOOTH_SLAB, findRelatedBlock("smooth", "slab"));
            this.addChild(SMOOTH_WALL, findRelatedBlock("smooth", "wall"));
        }

    }

    @Override
    protected void initializeChildrenItems() {}

    /// Check for block with "s" and without "s"
    private @Nullable Block findChildBlocks(String suffix) {
        if (!childInfix.isEmpty()) suffix = childInfix + suffix;
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

    /// Create new BlockType using the subclass: CrystalType, DustType, GemType, Or MetalType
    /// @param factory use 1 of 4 subclass, example: CrystalType::new
    public static <T extends BlockType> Optional<T> newSubBlockType(BiFunction<ResourceLocation, Block, T> factory,
                                                                    Block baseBlock, ResourceLocation blockId,
                                                                    String blockPath, String blockPathRegex,
                                                                    MapRegistry<T> valuesReg,
                                                                    Set<String> blockTypeBlacklist,
                                                                    @Nullable Set<String> modBlacklist,
                                                                    Boolean ... checks
    ) {
        Pattern regex = Pattern.compile(blockPathRegex);
        Matcher matcher = regex.matcher(blockPath);

        if (matcher.find()) {

            String blocktypeName = matcher.group("typename");

            ResourceLocation idBlockType = blockId.withPath(blocktypeName);

            boolean isModBlacklisted = Objects.nonNull(modBlacklist) && modBlacklist.contains(blockId.getNamespace());
            boolean isBlockTypeBlacklisted = Objects.nonNull(blockTypeBlacklist) && blockTypeBlacklist.contains(idBlockType.toString());

            if (!valuesReg.containsKey(idBlockType) && !isModBlacklisted && !isBlockTypeBlacklisted) {

                // if check is true, then it pass but if it's false, then denied.
                for (Boolean pass : checks) {
                    if (!pass) return Optional.empty();
                }

                return Optional.of(factory.apply(idBlockType, baseBlock));
            }
        }
        return Optional.empty();
    }

    public static <T extends BlockType> Optional<T> newSubBlockType(BiFunction<ResourceLocation, Block, T> factory,
                                                                    Block baseBlock, ResourceLocation blockId,
                                                                    String blockPath, String blockPathRegex,
                                                                    MapRegistry<T> valuesReg,
                                                                    Set<String> blockTypeBlacklist,
                                                                    Boolean ... checks
    ) {
        return newSubBlockType(factory, baseBlock, blockId, blockPath, blockPathRegex, valuesReg, blockTypeBlacklist, null, checks);
    }

    public static <T extends BlockType> Optional<T> newSubBlockType(BiFunction<ResourceLocation, Block, T> factory,
                                                                    Block baseBlock, ResourceLocation blockId,
                                                                    String blockPath, String blockPathRegex,
                                                                    MapRegistry<T> valuesReg,
                                                                    Boolean ... checks
    ) {
        return newSubBlockType(factory, baseBlock, blockId, blockPath, blockPathRegex, valuesReg, null, null, checks);
    }

    public static boolean isInItemRegistry(String namespace, String prefix, String blockPath, String target, String replacement) {
        return BuiltInRegistries.ITEM.containsKey(
                new ResourceLocation(namespace,prefix + blockPath.replace(target, replacement))
        );
    }
    public static boolean isInItemRegistry(String namespace, String blockPath, String target, String replacement) {
        return BuiltInRegistries.ITEM.containsKey(
                new ResourceLocation(namespace, blockPath.replace(target, replacement))
        );
    }

}
