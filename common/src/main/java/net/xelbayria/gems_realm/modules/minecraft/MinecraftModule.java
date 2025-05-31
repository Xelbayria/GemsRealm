package net.xelbayria.gems_realm.modules.minecraft;

import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.api.GemsRealmEntrySet;
import net.xelbayria.gems_realm.api.GemsRealmModule;
import net.xelbayria.gems_realm.api.set.MetalType;
import net.xelbayria.gems_realm.api.set.MetalTypeRegistry;

//SUPPORT: v
public class MinecraftModule extends GemsRealmModule {

    public final SimpleEntrySet<MetalType, Block> bars;
    public final ItemOnlyEntrySet<MetalType, Item> nugget;

    public MinecraftModule(String modId) {
        super(modId, "mc");
        ResourceKey<CreativeModeTab> building_block = CreativeModeTabs.BUILDING_BLOCKS;
        ResourceKey<CreativeModeTab> ingredients = CreativeModeTabs.INGREDIENTS;

        bars = GemsRealmEntrySet.of(MetalType.class, "bars",
                        getModBlock("iron_bars"), MetalTypeRegistry::getIronType,
                        metalType -> new IronBarsBlock(BlockBehaviour.Properties.of()
                                .requiresCorrectToolForDrops()
                                .strength(5.0F, 6.0F)
                                .sound(SoundType.METAL).noOcclusion())


                )
                .requiresChildren("ingot") //REASON: recipes
                .addTexture(modRes("block/iron_bars"))
                .generateBlockModels(
                        new ResourceLocation("block/iron_bars_post"),
                        new ResourceLocation("block/iron_bars_post_ends"),
                        new ResourceLocation("block/iron_bars_cap"),
                        new ResourceLocation("block/iron_bars_cap_alt"),
                        new ResourceLocation("block/iron_bars_side"),
                        new ResourceLocation("block/iron_bars_side_alt")
                )
                .generateItemModels(new ResourceLocation("minecraft:item/iron_bars"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.DRAGON_IMMUNE, Registries.BLOCK)
                .setTabKey(building_block)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(bars);

        nugget = ItemOnlyEntrySet.builder(MetalType.class, "nugget",
                        getModItem("iron_nugget"), MetalTypeRegistry::getIronType,
                        metalType -> new Item(new Item.Properties())
                )
                .requiresChildren("ingot") //REASON: recipes, textures
                .createPaletteFromChild("ingot")
                .addTexture(modRes("item/iron_nugget"))
                .generateItemModels(new ResourceLocation("item/iron_nugget"))
                .setTabKey(ingredients)
                .defaultRecipe()
                .build();
        this.addEntry(nugget);

    }


}