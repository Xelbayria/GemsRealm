package net.xelbayria.gems_realm.misc;


import net.mehvahdjukaar.moonlight.api.client.TextureCache;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.xelbayria.gems_realm.GemsRealm;

import java.util.HashMap;
import java.util.Map;

public class SpriteHelper {

    public final static Map<ResourceLocation, String> modelID = new HashMap<>();

    public static void addHardcodedSprites() {

                ///  MODELS  \\\
// NOTE: Used to identify StoneTypes' model only based off their name or path


                /// TEXTURES \\\
// NOTE: Used to identify BlockTypes' texture only based off their name and path
    }

    @SuppressWarnings("SameParameterValue")
    private static void addOptionalBlock(String blockId, String textureId, String texturePath) {
        BuiltInRegistries.BLOCK.getOptional(new ResourceLocation(blockId))
                .ifPresent(block -> TextureCache.registerSpecialTextureForBlock(block, textureId, new ResourceLocation(texturePath)));
    }

    @SuppressWarnings("SameParameterValue")
    private static void addOptionalItem(String itemId, String textureId, String texturePath) {
        BuiltInRegistries.ITEM.getOptional(new ResourceLocation(itemId))
                .ifPresent(item -> TextureCache.registerSpecialTextureForBlock(item, textureId, new ResourceLocation(texturePath)));
    }

    private static void addToModelId(String blockid, String pathModel) {
        modelID.put(GemsRealm.res(blockid), pathModel);


    }
}
