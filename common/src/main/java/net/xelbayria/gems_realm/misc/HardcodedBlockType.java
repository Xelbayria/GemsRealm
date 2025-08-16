package net.xelbayria.gems_realm.misc;

import net.minecraft.resources.ResourceLocation;
import net.xelbayria.gems_realm.api.set.CrystalType;
import net.xelbayria.gems_realm.api.set.GemType;
import net.xelbayria.gems_realm.api.set.MetalType;
import net.xelbayria.gems_realm.api.set.dust.DustType;

import java.util.Set;

public class HardcodedBlockType {

    public static String blockIdentify;
    public static String BlockTypeFromMod;
    public static String supportedMod;
    public static String supportedBlockName;

    public static final Set<String> BLACKLISTED_MODS = Set.of(
            "immersive_weathering", "chipped", "create_confectionery"
    );


    public static final Set<String> BLACKLISTED_METALTYPES = Set.of(
            //REASON: not a MetalType
            "ms:blaze", "atlantis:raw_ancient_metal"
    );

    public static final Set<String> BLACKLISTED_GEMTYPES = Set.of(
            //REASON: not a GemType
            "minecraft:redstone", "minecraft:coal",

            "crystalcraft_unlimited_java:aluminium", "crystalcraft_unlimited_java:bismuth", "crystalcraft_unlimited_java:bronze",
            "crystalcraft_unlimited_java:holmium", "crystalcraft_unlimited_java:iridium", "crystalcraft_unlimited_java:lead",
            "crystalcraft_unlimited_java:lithium", "crystalcraft_unlimited_java:magnesium", "crystalcraft_unlimited_java:matizium",
            "crystalcraft_unlimited_java:nickel", "crystalcraft_unlimited_java:orichalcum", "crystalcraft_unlimited_java:osmium",
            "crystalcraft_unlimited_java:palintinium", "crystalcraft_unlimited_java:palladium", "crystalcraft_unlimited_java:pelenium",
            "crystalcraft_unlimited_java:platinum", "crystalcraft_unlimited_java:silicium", "crystalcraft_unlimited_java:silver",
            "crystalcraft_unlimited_java:tin", "crystalcraft_unlimited_java:titanium", "crystalcraft_unlimited_java:uranium",
            "crystalcraft_unlimited_java:xernium", "crystalcraft_unlimited_java:yurium", "crystalcraft_unlimited_java:zinc",
            "crystalcraft_unlimited_java:chloronium", "crystalcraft_unlimited_java:cobalt", "crystalcraft_unlimited_java:maradonyx",
            "crystalcraft_unlimited_java:sulfur", "crystalcraft_unlimited_java:tungsten", "crystalcraft_unlimited_java:chrome",
            "crystalcraft_unlimited_java:carnotite", "crystalcraft_unlimited_java:ilmenite", "crystalcraft_unlimited_java:pyrite",
            "crystalcraft_unlimited_java:seaborgium",

            "shadowlands:goo", "landsoficaria:sliver"
    );

    public static final Set<String> BLACKLISTED_CRYSTALTYPES = Set.of(
            //REASON: not a CrystalType
    );

    public static final Set<String> BLACKLISTED_DUSTTYPES = Set.of(
            //REASON: not a DustType
            "betterend:ender"
    );

    /**
     * NOTE: BlockType represents Gem, Metal, Crystal, or Dust
     *
     * @param supportedModId Id of Supported Mods That GemsRealm is supporting - Can be one OR more Ids
     * @param blocktypeFromMod Id of mod that BlockType is from - Can be one or more Ids
     * @param blocktypeId id of blockid, ex: "ms:bismuth" OR "ms:(bismuth|refined_iron)"
     * @param supportedBlockId Id of block, ex: "chest" OR "redwood_chest" with blocktypeId
    **/
    public static Boolean isBlockRegistryFrom(String supportedModId, String blocktypeFromMod, String blocktypeId, String supportedBlockId) {

        String[] expressions = {
                supportedModId,
                blocktypeFromMod,
                blocktypeId,
                supportedBlockId
        };

        String[] values = {
                supportedMod,
                BlockTypeFromMod,
                blockIdentify,
                supportedBlockName
        };

        for (int idx = 0; idx < values.length; idx++ ) {

            if (!expressions[idx].isEmpty()) { // Skip the blank expressions
                boolean isNotMatched = !(values[idx].matches(expressions[idx]) || values[idx].contains(expressions[idx]));
                if (isNotMatched) return false;
            }
        }

        return true;
    }


    /// for mods that might add in vanilla namespace
    // CrystalType
    public static boolean isKnownVanillaCrystal(CrystalType crystalType){
        ResourceLocation id = crystalType.getId();
        if (id.getNamespace().equals("minecraft")) {
            return VANILLA_CRYSTAL.contains(id.getPath());
        }
        return false;
    }
    private static final Set<String> VANILLA_CRYSTAL = Set.of(
            "amethyst"
    );

    // DustType
    public static boolean isKnownVanillaDust(DustType dustType){
        ResourceLocation id = dustType.getId();
        if (id.getNamespace().equals("minecraft")) {
            return VANILLA_DUST.contains(id.getPath());
        }
        return false;
    }
    private static final Set<String> VANILLA_DUST = Set.of(
            "redstone"
    );

    // GemType
    public static boolean isKnownVanillaGem(GemType gemType){
        ResourceLocation id = gemType.getId();
        if (id.getNamespace().equals("minecraft")) {
            return VANILLA_GEM.contains(id.getPath());
        }
        return false;
    }
    private static final Set<String> VANILLA_GEM = Set.of(
            "diamond", "emerald", "lapis_lazuli"
    );

    // MetalType
    public static boolean isKnownVanillaMetal(MetalType metalType){
        ResourceLocation id = metalType.getId();
        if (id.getNamespace().equals("minecraft")) {
            return VANILLA_METAL.contains(id.getPath());
        }
        return false;
    }
    private static final Set<String> VANILLA_METAL = Set.of(
            "copper", "gold", "iron"
    );
}
