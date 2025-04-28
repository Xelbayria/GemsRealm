package net.xelbayria.gems_realm;

import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.xelbayria.gems_realm.configs.GRConfigs;
import net.xelbayria.gems_realm.misc.AllGemsItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class GRRegistry {

    public static void init() {

    }

    public static final Supplier<AllGemsItem> ALL_GEMS = RegHelper.registerItem(GemsRealm.res("all_gems"), AllGemsItem::new);

    @Nullable
    public static final RegSupplier<CreativeModeTab> MOD_TAB = GRConfigs.TAB_ENABLED.get() ?
            RegHelper.registerCreativeModeTab(GemsRealm.res("gems_realm"),
                    true,
                    builder -> builder.icon(() -> ALL_GEMS.get().getDefaultInstance())
                            .backgroundSuffix("item_search.png")
                            .title(Component.translatable("itemGroup.gems_realm.gems_realm"))
                            .build())
            : null;
}
