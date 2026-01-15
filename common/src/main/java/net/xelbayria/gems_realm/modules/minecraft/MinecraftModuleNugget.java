package net.xelbayria.gems_realm.modules.minecraft;

import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.mehvahdjukaar.every_compat.misc.UtilityTag;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.xelbayria.gems_realm.api.GemsRealmModule;
import net.xelbayria.gems_realm.api.MetalPaletteStrategies;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.api.set.metal.VanillaMetalTypes;

import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

import static net.xelbayria.gems_realm.api.set.metal.VanillaMetalChildKeys.INGOT;

public class MinecraftModuleNugget extends GemsRealmModule {

    public final ItemOnlyEntrySet<MetalType, Item> nugget;

    public MinecraftModuleNugget(String modId) {
        super(modId, "mc");
        ResourceKey<CreativeModeTab> ingredients = CreativeModeTabs.INGREDIENTS;
        //TODO: Add setBlockType() when updated to EC v2.9.16

        nugget = ItemOnlyEntrySet.builder(MetalType.class, "nugget",
                        getModItem("iron_nugget"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Item(new Item.Properties())
                )
                .requiresChildren(INGOT) //REASON: recipes, textures
                .addTexture(modRes("item/iron_nugget"), MetalPaletteStrategies.INGOT_STANDARD)
                .generateItemModels(new ResourceLocation("item/iron_nugget"))
                //TODO: Update EC to v2.9.16 and change platformTag
                .addTag(UtilityTag.platformTag("nuggets", "nuggets"), Registries.ITEM)
                .setTabKey(ingredients)
                .defaultRecipe()
                .build();
        this.addEntry(nugget);
    }

    @Override
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) -> {
            boolean isTagCreated = false;

            for (Map.Entry<MetalType, Item> entry : nugget.items.entrySet()) {
                MetalType metalType = entry.getKey();
                Item item = entry.getValue();

                String tagPath = "nuggets/" + metalType.getTypeName();

                SimpleTagBuilder tagBuilder = SimpleTagBuilder.of(UtilityTag.platformTag(tagPath, tagPath));

                if (Objects.nonNull(item)) {
                    tagBuilder.addEntry(item);
                    isTagCreated = true;
                }

                if (isTagCreated) {
                    sink.addTag(tagBuilder, Registries.ITEM);
                }
            }

        });
    }
}