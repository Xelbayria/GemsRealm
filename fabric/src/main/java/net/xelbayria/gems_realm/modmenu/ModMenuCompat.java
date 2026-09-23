package net.xelbayria.gems_realm.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.minecraft.resources.ResourceLocation;
import net.xelbayria.gems_realm.GemsRealm;

public class ModMenuCompat implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> ClientHelper.getMoonlightConfigScreen(GemsRealm.MOD_ID, parent,
                ResourceLocation.withDefaultNamespace("textures/block/emerald_block.png"));
    }
}