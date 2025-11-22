package net.xelbayria.gems_realm.modules.minecraft;

import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.xelbayria.gems_realm.api.GemsRealmModule;
import net.xelbayria.gems_realm.api.MetalPaletteStrategies;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.api.set.metal.VanillaMetalTypes;

import static net.xelbayria.gems_realm.api.set.metal.VanillaMetalChildKeys.INGOT;

public class MinecraftModuleNugget extends GemsRealmModule {

    public final ItemOnlyEntrySet<MetalType, Item> nugget;

    public MinecraftModuleNugget(String modId) {
        super(modId, "");
        ResourceKey<CreativeModeTab> ingredients = CreativeModeTabs.INGREDIENTS;

        nugget = ItemOnlyEntrySet.builder(MetalType.class, "nugget",
                        getModItem("iron_nugget"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Item(new Item.Properties())
                )
                .requiresChildren(INGOT) //REASON: recipes, textures
                .addTexture(modRes("item/iron_nugget"), MetalPaletteStrategies.INGOT_STANDARD)
                .generateItemModels(new ResourceLocation("item/iron_nugget"))
                .setTabKey(ingredients)
                .defaultRecipe()
                .build();
        this.addEntry(nugget);

    }
}