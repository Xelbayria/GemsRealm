package net.xelbayria.gems_realm;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.minecraft.resources.ResourceLocation;
import net.xelbayria.gems_realm.api.set.MetalTypeRegistry;
import net.xelbayria.gems_realm.configs.GRConfigs;
import net.xelbayria.gems_realm.misc.ModelUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CompletableFuture;

public class GemsRealm extends EveryCompat {
    public static final String MOD_ID = "gemsrealm";
    public static final Logger LOGGER = LogManager.getLogger("Gems Realm");

    public static void init() {
        GRConfigs.init();
        GRRegistry.init();

        BlockSetAPI.registerBlockSetDefinition(MetalTypeRegistry.INSTANCE);
//        BlockSetAPI.registerBlockSetDefinition(GemTypeRegistry.INSTANCE);
//        CompatStoneType.init();

//        PlatHelper.addCommonSetup(SpriteHelper::addHardcodedSprites);

        if (PlatHelper.getPhysicalSide().isClient()) {
            ClientHelper.addClientReloadListener(() -> (preparationBarrier, resourceManager, preparationsProfiler, reloadProfiler, backgroundExecutor, gameExecutor) ->
                            CompletableFuture.completedFuture(null)
                                    .thenCompose(preparationBarrier::wait)
                                    .thenAcceptAsync((object) -> ModelUtils.reset(), gameExecutor),
                    res("gemsrealm_reloader"));
        }
    }

    public static ResourceLocation res(String name) {
        return new ResourceLocation(MOD_ID, name);
    }


}