package net.xelbayria.gems_realm.api.intergration;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.minecraft.resources.ResourceLocation;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.api.set.DustType;

import static net.xelbayria.gems_realm.api.intergration.CompatGemType.childKeySafe;
import static net.xelbayria.gems_realm.api.intergration.CompatGemType.getChildKeyFrom;

/**
 * Undetected DustType that doesn't met 2 requirements:
 * the id of dust that don't have "_dust"
 * the dust have no block
 **/
// Put all undetected DustTypes here
public class CompatDustType {

    public static void init() {

        // CrystalCraft-Unlimited-Java
        simpleDustFinder("crystalcraft_unlimited_java", "ikegamini", "dust-ikegamini");
        simpleDustFinder("crystalcraft_unlimited_java", "ikegamonium", "dust-ikegamonium");
        simpleDustFinder("crystalcraft_unlimited_java", "ikegamium", "dust-ikegamium");
        simpleDustFinder("crystalcraft_unlimited_java", "simonium", "dust-simonium");
        simpleDustFinder("crystalcraft_unlimited_java", "simoganium", "dust-simoganium");
        simpleDustFinder("crystalcraft_unlimited_java", "chloronium", "dust-chloronium");
        simpleDustFinder("crystalcraft_unlimited_java", "whitestone", "dust-whitestone");
        simpleDustFinder("crystalcraft_unlimited_java", "blackstone", "dust-blackstone");
        simpleDustFinder("crystalcraft_unlimited_java", "sulfur", "dust-sulfur");

    }

    //!! SIMPLE FINDER
    /**
     * @param modId - mod id of the mod
     * @param nameDustType - name of DustType without "_block" or "_ingot"
     * @param nameChildren - childkey-ID_of_the_children or nameDustType_ingot
     */
    public static void simpleDustFinder(String modId, String nameDustType, String... nameChildren) {
        advancedDustFinder(modId, nameDustType, nameDustType + "_block", nameChildren);
    }

    //!! ADVANCED FINDER
    /**
     * @param modId - mod id of the mod
     * @param nameDustType - name of DustType without "_block" or "_ingot"
     * @param nameBlockDust - name of block for DustType. Usually with "_block"
     * @param nameChildren - childkey-ID_of_the_children or nameDustType_ingot
     */
    public static void advancedDustFinder(String modId, String nameDustType, String nameBlockDust,  String... nameChildren) {
        if (PlatHelper.isModLoaded(modId)) {
            var DustTypeFinder = DustType.Finder.simple(modId, nameDustType, nameBlockDust);

            for (String currentChild : nameChildren) {
                String childKey = getChildKeyFrom(currentChild);
                String blockId = currentChild.split("-")[1];
                ResourceLocation childId = (blockId.contains(":"))
                        ? new ResourceLocation(blockId)
                        : new ResourceLocation(modId, blockId);

                if (currentChild.contains("-") && childKeySafe.contains(childKey))
                    DustTypeFinder.addChild(childKey, childId);
                else if (childKeySafe.contains(childKey))
                    DustTypeFinder.addChild(childKey, currentChild);
                else
                    GemsRealm.LOGGER.warn("CompatDustType: Incorrect childKey - {} for {}", childKey, currentChild);
            }

            BlockSetAPI.addBlockTypeFinder(DustType.class, DustTypeFinder);
        }
    }

}
