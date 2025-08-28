package net.xelbayria.gems_realm;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.minecraft.resources.ResourceLocation;
import net.xelbayria.gems_realm.api.intergration.CompatCrystalType;
import net.xelbayria.gems_realm.api.intergration.CompatDustType;
import net.xelbayria.gems_realm.api.intergration.CompatGemType;
import net.xelbayria.gems_realm.api.intergration.CompatMetalType;
import net.xelbayria.gems_realm.api.set.crystal.CrystalTypeRegistry;
import net.xelbayria.gems_realm.api.set.dust.DustTypeRegistry;
import net.xelbayria.gems_realm.api.set.gem.GemTypeRegistry;
import net.xelbayria.gems_realm.api.set.metal.MetalTypeRegistry;
import net.xelbayria.gems_realm.configs.GRConfigs;
import net.xelbayria.gems_realm.configs.UnsafeDisablerConfigs;
import net.xelbayria.gems_realm.misc.CompatSpriteHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Function;
import java.util.function.Supplier;

public class GemsRealm extends EveryCompat {
    public static final String MOD_ID = "gemsrealm";
    public static final Logger LOGGER = LogManager.getLogger("Gems Realm");

    public static void init() {
        GRConfigs.init();
        UnsafeDisablerConfigs.init();
        GRRegistry.init();
        CompatSpriteHelper.initHardcodedSprite();

        BlockSetAPI.registerBlockSetDefinition(CrystalTypeRegistry.INSTANCE);
        CompatCrystalType.init();

        BlockSetAPI.registerBlockSetDefinition(DustTypeRegistry.INSTANCE);
        CompatDustType.init();

        BlockSetAPI.registerBlockSetDefinition(MetalTypeRegistry.INSTANCE);
        CompatMetalType.init();

        BlockSetAPI.registerBlockSetDefinition(GemTypeRegistry.INSTANCE);
        CompatGemType.init();
    }

    public static ResourceLocation res(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    @SafeVarargs
    public static void addMultipleIfLoaded(String modId, Supplier<Function<String, CompatModule>>... moduleFactories) {
        if (PlatHelper.isModLoaded(modId)) {
            CompatModule module;
            for (var moduleFactory : moduleFactories) {
                module = moduleFactory.get().apply(modId);
                addModule(module);
            }
        }

    }

}