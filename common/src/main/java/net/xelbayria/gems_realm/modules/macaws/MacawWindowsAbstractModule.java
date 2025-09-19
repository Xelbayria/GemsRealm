package net.xelbayria.gems_realm.modules.macaws;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.xelbayria.gems_realm.api.GemsRealmEntrySet;
import net.xelbayria.gems_realm.api.GemsRealmModule;
import net.xelbayria.gems_realm.api.MetalPaletteStrategies;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.api.set.metal.VanillaMetalTypes;

import static net.xelbayria.gems_realm.api.set.metal.VanillaMetalChildKeys.TRAPDOOR;

//SUPPORT: v2.4.0+
public abstract class MacawWindowsAbstractModule extends GemsRealmModule {

    public final SimpleEntrySet<MetalType, Block> shutter;
/*
    public final SimpleEntrySet<MetalType, Block> curtain_rod;
    public final SimpleEntrySet<MetalType, Block> window;
    public final SimpleEntrySet<MetalType, Block> window2;
    public final SimpleEntrySet<MetalType, Block> four_window;
    public final SimpleEntrySet<MetalType, Block> pane_window;*/

    public MacawWindowsAbstractModule(String modId) {
        super(modId, "mcw");
        ResourceLocation tab = modRes(modId);

        shutter = GemsRealmEntrySet.of(MetalType.class, "shutter",
                        getModBlock("iron_shutter"), () -> VanillaMetalTypes.IRON,
                        this::newShutter
                )
                .requiresChildren(TRAPDOOR) //REASON: recipes, textures
                .addTexture(modRes("block/iron_shutter"), MetalPaletteStrategies.TRAPDOOR_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(shutter);

/*
        curtain_rod = GemsRealmEntrySet.of(MetalType.class, "curtain_rod",
                        getModBlock("metal_curtain_rod"), () -> MetalTypeRegistry.getValue("temp:metal"),
                        metalType -> new CurtainRod(BlockBehaviour.Properties.of()
                                .mapColor(MapColor.METAL)
                                .sound(SoundType.METAL)
                                .strength(0.3F, 0.7F)
                        )
                )
                .requiresChildren("ingot", "nugget") //REASON: recipes
                //TEXTURES: block (original: anvil)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(curtain_rod);

        window = GemsRealmEntrySet.of(MetalType.class, "window",
                        getModBlock("metal_window"), () -> MetalTypeRegistry.getValue("minecraft:metal"),
                        metalType -> new ConnectedWindow(BlockBehaviour.Properties.of()
                                .mapColor(MapColor.WOOD)
                                .sound(SoundType.WOOD)
                                .strength(0.3F, 0.7F)
                        )
                )
                //TEXTURES: block (original: anvil)
                .excludeTextureFromTinting("#0") //REASON: glass
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(window);

        window2 = GemsRealmEntrySet.of(MetalType.class, "window2",
                        getModBlock("metal_window2"), () -> MetalTypeRegistry.getValue("minecraft:metal"),
                        metalType -> new WindowBarred(BlockBehaviour.Properties.of()
                                .mapColor(MapColor.WOOD)
                                .sound(SoundType.WOOD)
                                .strength(0.3F, 0.7F)
                        )
                )
                //TEXTURES: block (original: anvil)
                .excludeTextureFromTinting("#0") //REASON: glass
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(window2);

        four_window = GemsRealmEntrySet.of(MetalType.class, "four_window",
                        getModBlock("metal_four_window"), () -> MetalTypeRegistry.getValue("minecraft:metal"),
                        metalType -> new WindowBarred(BlockBehaviour.Properties.of()
                                .mapColor(MapColor.WOOD)
                                .sound(SoundType.WOOD)
                                .strength(0.3F, 0.7F)
                        )
                )
                //TEXTURES: block (original: anvil)
                .excludeTextureFromTinting("#0") //REASON: glass
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(four_window);

        pane_window = GemsRealmEntrySet.of(MetalType.class, "pane_window",
                        getModBlock("metal_pane_window"), () -> MetalTypeRegistry.getValue("minecraft:metal"),
                        metalType -> new Window(BlockBehaviour.Properties.of()
                                .mapColor(MapColor.WOOD)
                                .sound(SoundType.WOOD)
                                .strength(0.3F, 0.7F)
                        )
                )
                //TEXTURES: block (original: anvil)
                .excludeTextureFromTinting("#0") //REASON: glass
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(pane_window);
*/

    }

    public abstract Block newShutter(MetalType metalType);

//    public abstract Block newCurtainRod(MetalType metalType);

//    public abstract Block newConnectedWindow(MetalType metalType);

//    public abstract Block newWindowBarred(MetalType metalType);

//    public abstract Block newWindow(MetalType metalType);


}