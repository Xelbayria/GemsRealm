package net.xelbayria.gems_realm.misc;

import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.resources.ResourceLocation;
import net.xelbayria.gems_realm.api.set.crystal.CrystalType;
import net.xelbayria.gems_realm.api.set.dust.DustType;
import net.xelbayria.gems_realm.api.set.gem.GemType;
import net.xelbayria.gems_realm.api.set.metal.MetalType;

import java.util.Set;

public class HardcodedBlockType {

    public static String supportedMod;
    public static String supportedBlockName;

    public static final Set<String> BLACKLISTED_MODS = Set.of(
            "immersive_weathering", "chipped", "create_confectionery"
    );

    //TEMP
    public static final Set<String> BLACKLISTED_DUST_MODS = Set.of(
            "gtceu"
    );

    public static final Set<String> BLACKLISTED_METALTYPES = Set.of(
            //REASON: not a MetalType
            "ms:blaze", "atlantis:raw_ancient_metal", "advancednetherite:netherite_diamond", "advancednetherite:netherite_emerald",

            //REASON: Other MetalTypes from GregTech-CEU has similar textures as below
            "gtceu:magnetic_steel", "gtceu:magnetic_samarium", "gtceu:magnetic_neodymium", "gtceu:magnetic_iron"
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
     * @param modThatTheBlockIsFrom Id of mod that supported block is from
     * @param TypeNamespace The mod that new BlockType is from
     * @param TypeFullId Full Id of BlockType (MetalType, GemType, CrystalType, DustType)
     * @param blockName Gems Realm's Id of block
     */
    protected record PendingBlockInfo(String TypeNamespace, String TypeFullId, String blockName, String modThatTheBlockIsFrom) {

        public static PendingBlockInfo of(String typeNamespace, String typeFullId, String blockName, String modThatTheBlockIsFrom) {
            return new PendingBlockInfo(typeNamespace, typeFullId, blockName, modThatTheBlockIsFrom);
        }

        public boolean isForSupportedModId(String modId) {
            return this.modThatTheBlockIsFrom.matches(modId);
        }

        public boolean isForTypeNamespace(String namespace) {
            return this.TypeNamespace.matches(namespace);
        }

        public boolean isForTypeFullId(String fullId) {
            return this.TypeFullId.matches(fullId);
        }

        /**
         * @param BlockNameOrKeyword keyword or Block Name
         *                                  <br>Example (keyword): "chair"
         *                                  <br>Example (Blockname): "gold_chair"
         */
        public boolean isForBlockName(String BlockNameOrKeyword) {
            return this.blockName.matches(BlockNameOrKeyword) || this.blockName.contains(BlockNameOrKeyword);
        }

    }

    /// Check if BlockType is vanilla (from Minecraft)
    public static boolean isKnownVanillaBlockType(BlockType blockType) {
        if (blockType instanceof CrystalType crystalType && HardcodedBlockType.isKnownVanillaCrystal(crystalType))
            return true;
        else if (blockType instanceof DustType dustType && HardcodedBlockType.isKnownVanillaDust(dustType))
            return true;
        else if (blockType instanceof GemType gemType && HardcodedBlockType.isKnownVanillaGem(gemType))
            return true;
        else
            return blockType instanceof MetalType metalType && HardcodedBlockType.isKnownVanillaMetal(metalType);
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
            "diamond", "emerald", "lapis", "quartz"
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
            "copper", "gold", "iron", "netherite"
    );
}
