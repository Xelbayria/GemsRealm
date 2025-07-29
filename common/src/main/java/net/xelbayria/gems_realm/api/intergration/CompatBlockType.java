package net.xelbayria.gems_realm.api.intergration;

import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// COMMON METHODS
public class CompatBlockType {

    /// Get the keyword from block: stone_bricks, key: bricks
    public static String getChildKeyFrom(String childBlock) {
        if (childBlock.contains("-")) {
            return childBlock.split("-")[0].toLowerCase();
        }

        String lastword = childBlock.substring(childBlock.lastIndexOf("_") + 1);

        // With "bricks"
        if (childBlock.matches("\\w+(?:_bricks?)?(?:_[a-z]+)?")) {
            Pattern pattern = Pattern.compile("\\w+(?<type>_bricks?)?(?<subtype>_[a-z]+)?");
            Matcher matcher = pattern.matcher(childBlock);
            if (matcher.find()) {
                String suffix = (Objects.isNull(matcher.group("type"))) ? matcher.group("type") : matcher.group("type") + matcher.group("subtype");
                return switch (suffix) {
                    case "brick", "bricks" -> "bricks";
                    case "brick_slab", "bricks_slab" -> "brick_slab";
                    case "brick_stairs", "bricks_stairs" -> "brick_stairs";
                    case "brick_wall", "bricks_wall" -> "brick_wall";
                    default -> lastword;
                };
            }
        }
        // Default
        return lastword;
    }

    protected static final Set<String> childKeySafe = Set.of(
            //GENERAL
            //BLOCK
            "block", "stairs", "slab", "wall", "button", "pressure_plate", "smooth_stone",
            "polished", "polished_stairs", "polished_slab",
            "bricks", "brick_stairs", "brick_slab", "brick_wall", "cracked_bricks", "brick_tiles",
            "mossy_bricks", "mossy_brick_slab", "mossy_brick_stairs", "mossy_brick_wall",
            //ITEM
            "ingot", "nugget",

            //CrystalType
            "cluster", "budding",

            //ITEM
            "shard", "dust"
    );
}
