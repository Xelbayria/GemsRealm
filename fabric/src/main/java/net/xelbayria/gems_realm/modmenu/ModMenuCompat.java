package net.xelbayria.gems_realm.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.mehvahdjukaar.moonlight.api.platform.configs.fabric.FabricConfigListScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.configs.GRConfigs;

public class ModMenuCompat implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return p -> new FabricConfigListScreen(GemsRealm.MOD_ID, Items.STONE.getDefaultInstance(),
                Component.literal("§6Gems Realm Configs"), ResourceLocation.withDefaultNamespace("textures/block/stone.png"),
                p, GRConfigs.SPEC);
    }
}