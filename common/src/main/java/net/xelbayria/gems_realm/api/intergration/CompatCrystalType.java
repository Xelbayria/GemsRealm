package net.xelbayria.gems_realm.api.intergration;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.minecraft.resources.ResourceLocation;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.api.set.CrystalType;

import static net.xelbayria.gems_realm.api.intergration.CompatGemType.childKeySafe;
import static net.xelbayria.gems_realm.api.intergration.CompatGemType.getChildKeyFrom;

/**
 * Undetected CrystalType that doesn't met 2 requirements:
 **/
// Put all undetected CrystalTypes here
public class CompatCrystalType {

    public static void init() {

        // CrystalCraft-Unlimited-Java
        simpleCrystalFinder("crystalcraft_unlimited_java", "aura_quartz");
    }

    public static void simpleCrystalFinder(String modId, String nameCrystalType, String... nameChildren) {
        advancedCrystalFinder(modId, nameCrystalType, nameCrystalType + "_block", nameChildren);
    }

    public static void mediumCrystalFinder(String modId, String nameCrystalType, String nameCrystalBlock, String... nameChildren) {
        advancedCrystalFinder(modId, nameCrystalType, nameCrystalBlock, nameChildren);
    }

    //!! ADVANCED FINDER
    public static void advancedCrystalFinder(String modId, String nameCrystalType, String nameCrystalBlock,  String... nameChildren) {
        if (PlatHelper.isModLoaded(modId)) {
            var crystaltypeFinder = CrystalType.Finder.simple(modId, nameCrystalType, nameCrystalBlock);

            for (String currentChild : nameChildren) {
                String childKey = getChildKeyFrom(currentChild);
                String blockId = currentChild.split("-")[1];
                ResourceLocation childId;
                if (blockId.contains(":")) childId = new ResourceLocation(modId, blockId);
                else childId = new ResourceLocation(blockId);

                if (currentChild.contains("-") && childKeySafe.contains(childKey))
                    crystaltypeFinder.addChild(childKey, childId);
                else if (childKeySafe.contains(childKey))
                    crystaltypeFinder.addChild(childKey, currentChild);
                else
                    GemsRealm.LOGGER.warn("CompatCrystalType: Incorrect childKey - {} for {}", childKey, currentChild);
            }

            BlockSetAPI.addBlockTypeFinder(CrystalType.class, crystaltypeFinder);
        }
    }

}
