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

        // Excessive Building
        simpleCrystalFinder("excessive_building", "prismarine_crystal", "SHARD-minecraft:prismarine_crystals");

        // Divine RPG
        simpleCrystalFinder("divinerpg", "olivine", "SHARD-olivine");

        // Biomes O' Plenty
        simpleCrystalFinder("biomesoplenty", "rose_quartz");

        // CrystalCraft-Unlimited-Java
        simpleCrystalFinder("crystalcraft_unlimited_java", "aura_quartz", "CLUSTER-aura_crystal");

        // More Geodes Reforge
        simpleCrystalFinder("geodes", "echo", "BUDDING-budding_echo_block", "SHARD-minecraft:echo_shard");
        simpleCrystalFinder("geodes", "gypsum_crystal");
        simpleCrystalFinder("geodes", "diamond_crystal");
        simpleCrystalFinder("geodes", "quartz_crystal");
        simpleCrystalFinder("geodes", "emerald_crystal");
        simpleCrystalFinder("geodes", "lapis_crystal","CLUSTER-lapis_cluster");
    }

    //!! SIMPLE FINDER
    /**
     * @param modId mod-id of the mod
     * @param nameCrystalType name of CrystalType without "_block"
     * @param nameChildren "childkey-ID_of_the_children" or "nameCrystalType_shard"
     */
    public static void simpleCrystalFinder(String modId, String nameCrystalType, String... nameChildren) {
        advancedCrystalFinder(modId, nameCrystalType, nameCrystalType + "_block", nameChildren);
    }

    //!! ADVANCED FINDER
    /**
     * @param modId mod id of the mod
     * @param nameCrystalType name of CrystalType without "_block"
     * @param nameBlockCrystal name of block for CrystalType. Usually with "_block"
     * @param nameChildren childkey-ID_of_the_children or nameCrystalType_shard
     */
    public static void advancedCrystalFinder(String modId, String nameCrystalType, String nameBlockCrystal, String... nameChildren) {
        if (PlatHelper.isModLoaded(modId)) {
            var crystaltypeFinder = CrystalType.Finder.simple(modId, nameCrystalType, nameBlockCrystal);

            for (String currentChild : nameChildren) {
                String childKey = getChildKeyFrom(currentChild);
                String blockId = currentChild.split("-")[1];
                ResourceLocation childId = (blockId.contains(":"))
                        ? new ResourceLocation(blockId)
                        : new ResourceLocation(modId, blockId);


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
