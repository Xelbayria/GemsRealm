package net.xelbayria.gems_realm.modules.fabric.create;

import com.simibubi.create.content.decoration.palettes.ConnectedGlassPaneBlock;
import com.simibubi.create.content.decoration.palettes.WindowBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.xelbayria.gems_realm.api.GemsRealmEntrySet;
import net.xelbayria.gems_realm.api.GemsRealmModule;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.api.set.metal.VanillaMetalTypes;

import static net.xelbayria.gems_realm.api.set.metal.VanillaMetalChildKeys.INGOT;

//SUPPORT: v2.0.2
public class CreateDecoModule extends GemsRealmModule {

    public final SimpleEntrySet<MetalType, Block> window;
    public final SimpleEntrySet<MetalType, Block> window_pane;
    public final SimpleEntrySet<MetalType, Block> bars_overlay;
    public final SimpleEntrySet<MetalType, Block> mesh_fence;
    public final SimpleEntrySet<MetalType, Block> catwalk;
    public final SimpleEntrySet<MetalType, Block> catwalk_stairs;
    public final SimpleEntrySet<MetalType, Block> catwalk_railing;
    public final SimpleEntrySet<MetalType, Block> support_wedge;
    public final SimpleEntrySet<MetalType, Block> facade;
    public final SimpleEntrySet<MetalType, Block> ladder;
    public final SimpleEntrySet<MetalType, Block> hull;
    public final SimpleEntrySet<MetalType, Block> support;
    public final SimpleEntrySet<MetalType, Block> yellow_lamp;
    public final SimpleEntrySet<MetalType, Block> red_lamp;
    public final SimpleEntrySet<MetalType, Block> green_lamp;
    public final SimpleEntrySet<MetalType, Block> blue_lamp;
    public final SimpleEntrySet<MetalType, Block> sheet_metal;

    public CreateDecoModule(String modId) {
        super(modId, "cd");
        ResourceLocation tab = modRes("");

        window = GemsRealmEntrySet.of(MetalType.class, "window",
                        getModBlock("iron_window"), () -> VanillaMetalTypes.IRON,
                        this::makeWindow
                )
                .requiresChildren(INGOT) //REASON: recipes
                .addTexture(modRes("block/blanks_1"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(window);

        window_pane = GemsRealmEntrySet.of(MetalType.class, "window_pane",
                        getModBlock("iron_window_pane"), () -> VanillaMetalTypes.IRON,
                        metalType -> new ConnectedGlassPaneBlock(Utils.copyPropertySafe(Blocks.GLASS_PANE))
                )
                .requiresFromMap(window.blocks) //REASON: recipes
                .addTexture(modRes("block/blanks_2"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(window_pane);

        bars_overlay = GemsRealmEntrySet.of(MetalType.class, "bars_overlay",
                        getModBlock("iron_bars_overlay"), () -> VanillaMetalTypes.IRON,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.block))
                )
                //RECIPES: create:sheet
//                .requiresChildren("create:sheet") //REASON: recipes
                .addTexture(modRes("block/blanks_3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(bars_overlay);

        mesh_fence = GemsRealmEntrySet.of(MetalType.class, "mesh_fence",
                        getModBlock("iron_mesh_fence"), () -> VanillaMetalTypes.IRON,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.block))
                )
                //RECIPES: create:sheet
//                .requiresChildren("create:sheet") //REASON: recipes
                .addTexture(modRes("block/blanks_4"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mesh_fence);

        catwalk = GemsRealmEntrySet.of(MetalType.class, "catwalk",
                        getModBlock("iron_catwalk"), () -> VanillaMetalTypes.IRON,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.block))
                )
                //RECIPES: create:sheet, minecraft:bars
//                .requiresChildren("create:sheet" /*,"bars"*/) //REASON: recipes
                .addTexture(modRes("block/blanks_5"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(catwalk);

        catwalk_stairs = GemsRealmEntrySet.of(MetalType.class, "catwalk_stairs",
                        getModBlock("iron_catwalk_stairs"), () -> VanillaMetalTypes.IRON,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.block))
                )
                .requiresFromMap(catwalk.blocks) //REASON: recipes
                .requiresChildren("bars") //REASON: recipes
                .addTexture(modRes("block/blanks_6"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(catwalk_stairs);

        catwalk_railing = GemsRealmEntrySet.of(MetalType.class, "catwalk_railing",
                        getModBlock("iron_catwalk_railing"), () -> VanillaMetalTypes.IRON,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.block))
                )
                //RECIPES: create:sheet, minecraft:bars
//                .requiresChildren("create:sheet" /*,"bars"*/) //REASON: recipes
                .addTexture(modRes("block/blanks_7"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(catwalk_railing);

        support_wedge = GemsRealmEntrySet.of(MetalType.class, "support_wedge",
                        getModBlock("iron_support_wedge"), () -> VanillaMetalTypes.IRON,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.block))
                )
                //RECIPES: create:sheet
//                .requiresChildren("create:sheet") //REASON: recipes
                .addTexture(modRes("block/blanks_8"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(support_wedge);

        facade = GemsRealmEntrySet.of(MetalType.class, "facade",
                        getModBlock("iron_facade"), () -> VanillaMetalTypes.IRON,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.block))
                )
                .requiresChildren(INGOT) //REASON: recipes
                .addTexture(modRes("block/blanks_9"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(facade);

        ladder = GemsRealmEntrySet.of(MetalType.class, "ladder",
                        getModBlock("iron_ladder"), () -> VanillaMetalTypes.IRON,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.block))
                )
                .requiresChildren(INGOT) //REASON: recipes
                .addTexture(modRes("block/blanks_10"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(ladder);

        hull = GemsRealmEntrySet.of(MetalType.class, "hull",
                        getModBlock("iron_hull"), () -> VanillaMetalTypes.IRON,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.block))
                )
                .requiresChildren("create:sheet") //REASON: recipes
                //RECIPES: block
                .addTexture(modRes("block/blanks_11"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(hull);

        support = GemsRealmEntrySet.of(MetalType.class, "support",
                        getModBlock("iron_support"), () -> VanillaMetalTypes.IRON,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.block))
                )
                .requiresChildren(INGOT) //REASON: recipes
                .addTexture(modRes("block/blanks_12"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(support);

        yellow_lamp = GemsRealmEntrySet.of(MetalType.class, "lamp", "yellow",
                        getModBlock("yellow_iron_lamp"), () -> VanillaMetalTypes.IRON,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.block))
                )
                //RECIPES: create:sheet, minecraft:nugget
                .addTexture(modRes("block/blanks_13"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(yellow_lamp);

        red_lamp = GemsRealmEntrySet.of(MetalType.class, "lamp", "red",
                        getModBlock("red_iron_lamp"), () -> VanillaMetalTypes.IRON,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.block))
                )
                //RECIPES: create:sheet, minecraft:nugget
                .addTexture(modRes("block/blanks_14"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(red_lamp);

        green_lamp = GemsRealmEntrySet.of(MetalType.class, "lamp", "green",
                        getModBlock("green_iron_lamp"), () -> VanillaMetalTypes.IRON,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.block))
                )
                //RECIPES: create:sheet, minecraft:nugget
                .addTexture(modRes("block/blanks_15"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(green_lamp);

        blue_lamp = GemsRealmEntrySet.of(MetalType.class, "lamp", "blue",
                        getModBlock("blue_iron_lamp"), () -> VanillaMetalTypes.IRON,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.block))
                )
                //RECIPES: create:sheet, minecraft:nugget
                .addTexture(modRes("block/blanks_16"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(blue_lamp);

        sheet_metal = GemsRealmEntrySet.of(MetalType.class, "sheet_metal",
                        getModBlock("iron_sheet_metal"), () -> VanillaMetalTypes.IRON,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.block))
                )
                //RECIPES: create:sheet
                .addTexture(modRes("block/blanks_17"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(sheet_metal);

    }

    private WindowBlock makeWindow(MetalType metalType) {
        return new WindowBlock(Utils.copyPropertySafe(Blocks.GLASS)
                .isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false), false);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void onClientSetup() {
        super.onClientSetup();
        CreateClientModule.registerWindowCTBehavior(this, window, window_pane);
    }

}