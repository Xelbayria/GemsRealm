package net.xelbayria.gems_realm.misc;


import net.minecraft.resources.ResourceLocation;
import net.xelbayria.gems_realm.GemsRealm;

import java.util.HashMap;
import java.util.Map;

import static net.mehvahdjukaar.every_compat.misc.SpriteExtra.addOptional;

public class SpriteHelper {

    public final static Map<ResourceLocation, String> modelID = new HashMap<>();

    // TEXTURES \\
/// NOTE: Used to identify BlockTypes' texture only based off their name and path
    public static void initHardcodedSprite() {
            // Crystalcraft Unlimited Java
        addOptional("crystalcraft_unlimited_java:peacock_block", "all", "crystalcraft_unlimited_java:block/pa_peacock_block_north");
        addOptional("crystalcraft_unlimited_java:opal_block", "all", "crystalcraft_unlimited_java:block/pa_opal_block_north");
        addOptional("crystalcraft_unlimited_java:alexandrite_block", "all", "crystalcraft_unlimited_java:block/pa_alexandrite_block_north");
        addOptional("crystalcraft_unlimited_java:larimar_block", "all", "crystalcraft_unlimited_java:block/pa_larimar_block_north");
        addOptional("crystalcraft_unlimited_java:brown_pearl_block", "all", "crystalcraft_unlimited_java:block/pa_brown_pearl_block_north");
        addOptional("crystalcraft_unlimited_java:blue_pearl_block", "all", "crystalcraft_unlimited_java:block/pa_blue_pearl_block_north");
        addOptional("crystalcraft_unlimited_java:dark_blue_pearl_block", "all", "crystalcraft_unlimited_java:block/pa_dark_blue_pearl_block_north");
        addOptional("crystalcraft_unlimited_java:green_pearl_block", "all", "crystalcraft_unlimited_java:block/pa_green_pearl_block_north");
        addOptional("crystalcraft_unlimited_java:olive_pearl_block", "all", "crystalcraft_unlimited_java:block/pa_olive_pearl_block_north");
        addOptional("crystalcraft_unlimited_java:orange_pearl_block", "all", "crystalcraft_unlimited_java:block/pa_orange_pearl_block_north");
        addOptional("crystalcraft_unlimited_java:pink_pearl_block", "all", "crystalcraft_unlimited_java:block/pa_pink_pearl_block_north");
        addOptional("crystalcraft_unlimited_java:purple_pearl_block", "all", "crystalcraft_unlimited_java:block/pa_purple_pearl_block_north");
        addOptional("crystalcraft_unlimited_java:red_pearl_block", "all", "crystalcraft_unlimited_java:block/pa_red_pearl_block_north");
        addOptional("crystalcraft_unlimited_java:yellow_pearl_block", "all", "crystalcraft_unlimited_java:block/pa_yellow_pearl_block_north");
        addOptional("crystalcraft_unlimited_java:pearl_block", "all", "crystalcraft_unlimited_java:block/pa_pearl_block_north");
        addOptional("crystalcraft_unlimited_java:stibnite_block", "all", "crystalcraft_unlimited_java:block/pa_stibnite_block_north");
        addOptional("crystalcraft_unlimited_java:jasper_block", "all", "crystalcraft_unlimited_java:block/pa_jasper_block_north");
        addOptional("crystalcraft_unlimited_java:fluorite_block", "all", "crystalcraft_unlimited_java:block/pa_fluorite_block_north");
        addOptional("crystalcraft_unlimited_java:rare_sapphire_block", "all", "crystalcraft_unlimited_java:block/pa_rare_sapphire_block_north");
        addOptional("crystalcraft_unlimited_java:rare_tanzanite_block", "all", "crystalcraft_unlimited_java:block/pa_rare_tanzanite_block_north");
        addOptional("crystalcraft_unlimited_java:sunset_jasper_block", "all", "crystalcraft_unlimited_java:block/pa_sunset_jasper_block_north");
        addOptional("crystalcraft_unlimited_java:azurite_block", "all", "crystalcraft_unlimited_java:block/pa_azurite_block_north");
        addOptional("crystalcraft_unlimited_java:holmium_block", "all", "crystalcraft_unlimited_java:block/pa_holmium_block_north");
        addOptional("crystalcraft_unlimited_java:iris_agate_block", "all", "crystalcraft_unlimited_java:block/pa_iris_agate_block_north");
        addOptional("crystalcraft_unlimited_java:brickerite_block", "all", "crystalcraft_unlimited_java:block/brickerite_block_1");
    }

    //  MODELS  \\
/// NOTE: Used to identify RockTypes' model only based off their name or path
    public static void addHardcodedModel() {
    }

    private static void addToModelId(String blockid, String pathModel) {
        modelID.put(GemsRealm.res(blockid), pathModel);
    }
}
