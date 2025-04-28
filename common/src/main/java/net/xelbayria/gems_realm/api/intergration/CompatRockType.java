package net.xelbayria.gems_realm.api.intergration;

import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.xelbayria.gems_realm.api.set.GemType;
import net.xelbayria.gems_realm.api.set.MetalType;

/// MetalType Detection detect a MetalType that met 2 requirements:
        /// BASS_DRUM (sound)
        /// blockID: nameStoneType_bricks - only 2 words
// Put all undetected StoneTypes (including hardcoded ones) from mods in here to be included
public class CompatRockType {

    public static void init() {

    }

//!! MetalType
    public static void simpleMetalFinder(String modId, String nameStoneType) {
        var metaltypeFinder = MetalType.Finder.simple(modId, nameStoneType, nameStoneType);

        BlockSetAPI.addBlockTypeFinder(MetalType.class, metaltypeFinder);
    }

    public static void metalFinder(String modId, String nameMetalType, String nameMetal) {
        var metaltypeFinder = MetalType.Finder.simple(modId, nameMetalType, nameMetal);

        BlockSetAPI.addBlockTypeFinder(MetalType.class, metaltypeFinder);
    }

    /// nameMetal has "_block" as suffix
    public static void metalBlockFinder(String modId, String nameMetalType, boolean includeSlab, boolean includeStairs) {
        String baseName = nameMetalType + "_block";

        var metaltypeFinder = MetalType.Finder.simple(modId, nameMetalType, baseName);
        if (includeSlab) metaltypeFinder.addChild("slab", baseName +"_slab");
        if (includeStairs) metaltypeFinder.addChild("slab", baseName +"_stairs");

        BlockSetAPI.addBlockTypeFinder(MetalType.class, metaltypeFinder);
    }
    /** nameStone has "_block" as suffix
     * default parameter
     * includeSlab: false
     * includeStairs: false
    */
    public static void metalBlockFinder(String modId, String nameMetalType) {
        metalBlockFinder(modId, nameMetalType, false, false);
    }

//!! GemType
    public static void simpleGemFinder(String modId, String nameGemType) {
        var gemtypeFinder = GemType.Finder.simple(modId, nameGemType, nameGemType);

        BlockSetAPI.addBlockTypeFinder(GemType.class, gemtypeFinder);
    }

}
