package net.xelbayria.gems_realm.modules.neoforge.create;

import com.github.talrey.createdeco.api.Catwalks;
import com.github.talrey.createdeco.api.Wedges;
import com.github.talrey.createdeco.blocks.*;
import com.github.talrey.createdeco.items.CatwalkBlockItem;
import com.simibubi.create.content.decoration.MetalLadderBlock;
import com.simibubi.create.content.decoration.palettes.ConnectedGlassPaneBlock;
import com.simibubi.create.content.decoration.palettes.ConnectedPillarBlock;
import com.simibubi.create.content.decoration.palettes.WindowBlock;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.xelbayria.gems_realm.api.GemsRealmEntrySet;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.api.set.metal.VanillaMetalTypes;
import net.xelbayria.gems_realm.modules.create.CreateDecoModuleAbstract;
import org.joml.Vector3f;

import static net.xelbayria.gems_realm.api.set.metal.VanillaMetalChildKeys.INGOT;

//SUPPORT: v
public class CreateDecoModule extends CreateDecoModuleAbstract {

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
        super(modId);
        ResourceLocation tab = modRes("");

        window = GemsRealmEntrySet.of(MetalType.class, "window",
                        getModBlock("iron_window"), () -> VanillaMetalTypes.IRON,
                        this::newWindowBlock
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
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
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
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
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
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
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
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
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
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
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
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
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
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
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
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
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
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
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
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
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
                        this::newCageLampBlock
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
                        this::newCageLampBlock
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
                        this::newCageLampBlock
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
                        this::newCageLampBlock
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
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
                )
                //RECIPES: create:sheet
                .addTexture(modRes("block/blanks_17"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(sheet_metal);

    }

    @Override
    //REQUIRE: RenderType::cutout_mipped
    protected Block newWindowBlock(MetalType metalType) {
        return new WindowBlock(Utils.copyPropertySafe(Blocks.GLASS)
                .isValidSpawn((s, l, ps, t) -> false)
                .isRedstoneConductor((s, l, ps) -> false)
                .isSuffocating((s, l, ps) -> false)
                .isViewBlocking((s, l, ps) -> false), false);
    }

    @Override
    //Add WindowBlock's mapColor's defaultMapColor
    protected Block newConnectedGlassPaneBlock(MetalType metalType) {
        return new ConnectedPillarBlock(Utils.copyPropertySafe(Blocks.GLASS_PANE)
                .mapColor(window.blocks.get(metalType).defaultMapColor())
        );
    }

    @Override
    protected Block newIronBarsBlock(MetalType metalType) {
        return new IronBarsBlock(BlockBehaviour.Properties.of()
                .noOcclusion()
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .sound(SoundType.NETHERITE_BLOCK)
        );
    }

    /// See {@link com.github.talrey.createdeco.blocks.MeshFenceBlock}
    @Override
    protected Block newMeshFenceBlock(MetalType metalType) {
        return new MeshFenceBlock(BlockBehaviour.Properties.of()
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .sound(SoundType.CHAIN)
        );
    }

    /// MAIN CLASS: {@link Catwalks}

    /// See {@link CatwalkBlock}
    /// See {@link CatwalkBlockItem}
    @Override
    protected Block newCatwalkBlock(MetalType metalType) {
        return new CatwalkBlock(BlockBehaviour.Properties.of()
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .noOcclusion()
                .sound(SoundType.NETHERITE_BLOCK)
        );
    }


    /// See {@link CatwalkStairBlock}
    @Override
    protected Block newCatwalkStairBlock(MetalType metalType) {
        return new CatwalkStairBlock(BlockBehaviour.Properties.of()
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .noOcclusion()
                .sound(SoundType.NETHERITE_BLOCK),
                metalType.getTypeName()
        );
    }

    /// See {@link CatwalkRailingBlock}
    @Override
    //REQUIRE CatwalkCTBehaviour()
    protected Block newCatwalkRailingBlock(MetalType metalType) {
        return new CatwalkRailingBlock(BlockBehaviour.Properties.of()
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .noOcclusion()
                .sound(SoundType.NETHERITE_BLOCK)
        );
    }

    /// See {@link Wedges}
    /// See {@link SupportWedgeBlock}
    @Override
    protected Block newSupportWedgeBlock(MetalType metalType) {
        return new SupportWedgeBlock(BlockBehaviour.Properties.of()
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .sound(SoundType.NETHERITE_BLOCK)
                .noOcclusion()
                .isViewBlocking((a, b, c) -> false).isSuffocating((a, b, c) -> false)
        );
    }

    @Override
    //REQUIRE RenderType::translucent
    protected Block newFacadeBlock(MetalType metalType) {
        return new FacadeBlock(BlockBehaviour.Properties.of()
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .sound(SoundType.NETHERITE_BLOCK)
                .noOcclusion()
                .isViewBlocking((a, b, c) -> false)
                .isSuffocating((a, b, c) -> false)
        );
    }

    @Override
    //REQUIRE RenderType::cutout
    protected Block newMetalLadderBlock(MetalType metalType) {
        return new MetalLadderBlock(Utils.copyPropertySafe(Blocks.LADDER));
    }

    @Override
    //REQUIRE RenderType::cutout_mipped
    protected Block newHullBlock(MetalType metalType) {
        return new HullBlock(BlockBehaviour.Properties.of()
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .sound(SoundType.NETHERITE_BLOCK)
                .noOcclusion()
                .isViewBlocking((a, b, c) -> false)
        );
    }

    @Override
    //REQUIRE RenderType::translucent
    protected Block newSupportBlock(MetalType metalType) {
        return new SupportBlock(BlockBehaviour.Properties.of()
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .sound(SoundType.NETHERITE_BLOCK)
                .noOcclusion()
                .isViewBlocking((a, b, c) -> false)
                .isSuffocating((a, b, c) -> false)
        );
    }

    @Override
    protected Block newCageLampBlock(MetalType metalType) {
        return new CageLampBlock(
                BlockBehaviour.Properties.of().noOcclusion().strength(0.5F).sound(SoundType.LANTERN)
                        .lightLevel(state -> (Boolean)state.getValue(BlockStateProperties.LIT) ? 15 : 0),
                new Vector3f(0.3F, 0.3F, 0.0F)
        );
    }

    @Override
    // SheetMetal - require RotatedPillarCTBehaviour()
    protected Block newSheetMetalBlock(MetalType metalType) {
        return new ConnectedPillarBlock(BlockBehaviour.Properties.of()
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .sound(SoundType.NETHERITE_BLOCK));
    }


    @Override
    @OnlyIn(Dist.CLIENT)
    public void onClientSetup() {
        super.onClientSetup();
        CreateClientModule.registerWindowCTBehavior(this, window, window_pane);
    }

}