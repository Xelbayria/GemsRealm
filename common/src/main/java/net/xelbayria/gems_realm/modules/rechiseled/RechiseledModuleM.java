package net.xelbayria.gems_realm.modules.rechiseled;

import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.PaletteStrategy;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.xelbayria.gems_realm.api.GemsRealmEntrySet;
import net.xelbayria.gems_realm.api.set.VanillaRockChildKeys;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.api.set.metal.MetalTypeRegistry;
import net.xelbayria.gems_realm.api.set.metal.VanillaMetalTypes;

import java.util.function.Consumer;

import static net.mehvahdjukaar.every_compat.api.PaletteStrategies.registerCached;

//See RechiseledModuleAbstract's SUPPORTED VERSION
public class RechiseledModuleM extends RechiseledModuleAbstract {

    public final SimpleEntrySet<MetalType, Block> block_bordered;
    public final SimpleEntrySet<MetalType, Block> block_bordered_connecting;
    public final SimpleEntrySet<MetalType, Block> block_chiseled;
    public final SimpleEntrySet<MetalType, Block> block_chiseled_connecting;
    public final SimpleEntrySet<MetalType, Block> block_framed;
    public final SimpleEntrySet<MetalType, Block> block_framed_connecting;
    public final SimpleEntrySet<MetalType, Block> block_gears;
    public final SimpleEntrySet<MetalType, Block> block_gears_connecting;
    public final SimpleEntrySet<MetalType, Block> block_lines;
    public final SimpleEntrySet<MetalType, Block> block_lines_connecting;
    public final SimpleEntrySet<MetalType, Block> block_patterned;
    public final SimpleEntrySet<MetalType, Block> block_patterned_connecting;
    public final SimpleEntrySet<MetalType, Block> block_pipes;
    public final SimpleEntrySet<MetalType, Block> block_pipes_connecting;
    public final SimpleEntrySet<MetalType, Block> block_polished;
    public final SimpleEntrySet<MetalType, Block> block_polished_connecting;
    public final SimpleEntrySet<MetalType, Block> block_processed;
    public final SimpleEntrySet<MetalType, Block> block_processed_connecting;
    public final SimpleEntrySet<MetalType, Block> block_small_bricks;
    public final SimpleEntrySet<MetalType, Block> block_small_bricks_connecting;
    public final SimpleEntrySet<MetalType, Block> block_plated;
    public final SimpleEntrySet<MetalType, Block> block_pulverized;
    public final SimpleEntrySet<MetalType, Block> block_reinforced;
    public final SimpleEntrySet<MetalType, Block> block_sheets;
    public final SimpleEntrySet<MetalType, Block> block_smooth;
    public final SimpleEntrySet<MetalType, Block> block_connecting_connecting; //iron_block's connectingBlock

    public RechiseledModuleM(String modId) {
        super(modId);

        block_bordered = GemsRealmEntrySet.of(MetalType.class, "block_bordered",
                        getModBlock("iron_block_bordered"), () -> VanillaMetalTypes.IRON,
                        metalType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/iron_block_bordered"), BLOCK_LOW_CONTRAST)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_bordered);

        block_bordered_connecting = GemsRealmEntrySet.of(MetalType.class, "block_bordered_connecting",
                        getModBlock("iron_block_bordered_connecting"), () -> VanillaMetalTypes.IRON,
                        metalType -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(metalType.block))
                )
                //TEXTURES: iron_block_bordered
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_bordered_connecting);

        block_chiseled = GemsRealmEntrySet.of(MetalType.class, "block_chiseled",
                        getModBlock("iron_block_chiseled"), () -> VanillaMetalTypes.IRON,
                        metalType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/iron_block_chiseled"), BLOCK_LOW_CONTRAST)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_chiseled);

        block_chiseled_connecting = GemsRealmEntrySet.of(MetalType.class, "block_chiseled_connecting",
                        getModBlock("iron_block_chiseled_connecting"), () -> VanillaMetalTypes.IRON,
                        metalType -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(metalType.block))
                )
                //TEXTURES: iron_block_chiseled
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_chiseled_connecting);

        block_framed = GemsRealmEntrySet.of(MetalType.class, "block_framed",
                        getModBlock("iron_block_framed"), () -> VanillaMetalTypes.IRON,
                        metalType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/iron_block_framed"), BLOCK_LOW_CONTRAST)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_framed);

        block_framed_connecting = GemsRealmEntrySet.of(MetalType.class, "block_framed_connecting",
                        getModBlock("iron_block_framed_connecting"), () -> VanillaMetalTypes.IRON,
                        metalType -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(metalType.block))
                )
                //TEXTURES: iron_block_framed
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_framed_connecting);

        block_gears = GemsRealmEntrySet.of(MetalType.class, "block_gears",
                        getModBlock("iron_block_gears"), () -> VanillaMetalTypes.IRON,
                        metalType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/iron_block_gears"), BLOCK_LOW_CONTRAST)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_gears);

        block_gears_connecting = GemsRealmEntrySet.of(MetalType.class, "block_gears_connecting",
                        getModBlock("iron_block_gears_connecting"), () -> VanillaMetalTypes.IRON,
                        metalType -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(metalType.block))
                )
                //TEXTURES: iron_block_gears
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_gears_connecting);

        block_lines = GemsRealmEntrySet.of(MetalType.class, "block_lines",
                        getModBlock("iron_block_lines"), () -> VanillaMetalTypes.IRON,
                        metalType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/iron_block_lines"), BLOCK_LOW_CONTRAST)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_lines);

        block_lines_connecting = GemsRealmEntrySet.of(MetalType.class, "block_lines_connecting",
                        getModBlock("iron_block_lines_connecting"), () -> VanillaMetalTypes.IRON,
                        metalType -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(metalType.block))
                )
                //TEXTURES: iron_block_lines
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_lines_connecting);

        block_patterned = GemsRealmEntrySet.of(MetalType.class, "block_patterned",
                        getModBlock("iron_block_patterned"), () -> VanillaMetalTypes.IRON,
                        metalType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/iron_block_patterned"), BLOCK_LOW_CONTRAST)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_patterned);

        block_patterned_connecting = GemsRealmEntrySet.of(MetalType.class, "block_patterned_connecting",
                        getModBlock("iron_block_patterned_connecting"), () -> VanillaMetalTypes.IRON,
                        metalType -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(metalType.block))
                )
                //TEXTURES: iron_block_patterned
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_patterned_connecting);

        block_pipes = GemsRealmEntrySet.of(MetalType.class, "block_pipes",
                        getModBlock("iron_block_pipes"), () -> VanillaMetalTypes.IRON,
                        metalType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/iron_block_pipes"), BLOCK_LOW_CONTRAST)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_pipes);

        block_pipes_connecting = GemsRealmEntrySet.of(MetalType.class, "block_pipes_connecting",
                        getModBlock("iron_block_pipes_connecting"), () -> VanillaMetalTypes.IRON,
                        metalType -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(metalType.block))
                )
                //TEXTURES: iron_block_pipes
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_pipes_connecting);

        block_polished = GemsRealmEntrySet.of(MetalType.class, "block_polished",
                        getModBlock("iron_block_polished"), () -> VanillaMetalTypes.IRON,
                        metalType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/iron_block_polished"), BLOCK_LOW_CONTRAST)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_polished);

        block_polished_connecting = GemsRealmEntrySet.of(MetalType.class, "block_polished_connecting",
                        getModBlock("iron_block_polished_connecting"), () -> VanillaMetalTypes.IRON,
                        metalType -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(metalType.block))
                )
                //TEXTURES: iron_block_polished
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_polished_connecting);

        block_processed = GemsRealmEntrySet.of(MetalType.class, "block_processed",
                        getModBlock("iron_block_processed"), () -> VanillaMetalTypes.IRON,
                        metalType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/iron_block_processed"), BLOCK_LOW_CONTRAST)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_processed);

        block_processed_connecting = GemsRealmEntrySet.of(MetalType.class, "block_processed_connecting",
                        getModBlock("iron_block_processed_connecting"), () -> VanillaMetalTypes.IRON,
                        metalType -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(metalType.block))
                )
                //TEXTURES: iron_block_processed
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_processed_connecting);

        block_small_bricks = GemsRealmEntrySet.of(MetalType.class, "block_small_bricks",
                        getModBlock("iron_block_small_bricks"), () -> VanillaMetalTypes.IRON,
                        metalType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/iron_block_small_bricks"), BLOCK_LOW_CONTRAST)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_small_bricks);

        block_small_bricks_connecting = GemsRealmEntrySet.of(MetalType.class, "block_small_bricks_connecting",
                        getModBlock("iron_block_small_bricks_connecting"), () -> VanillaMetalTypes.IRON,
                        metalType -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(metalType.block))
                )
                //TEXTURES: iron_block_small_bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_small_bricks_connecting);

        block_plated = GemsRealmEntrySet.of(MetalType.class, "block_plated",
                        getModBlock("iron_block_plated"), () -> VanillaMetalTypes.IRON,
                        metalType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/iron_block_plated"), BLOCK_LOW_CONTRAST)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_plated);

        block_pulverized = GemsRealmEntrySet.of(MetalType.class, "block_pulverized",
                        getModBlock("iron_block_pulverized"), () -> VanillaMetalTypes.IRON,
                        metalType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/iron_block_pulverized"), BLOCK_LOW_CONTRAST)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_pulverized);

        block_reinforced = GemsRealmEntrySet.of(MetalType.class, "block_reinforced",
                        getModBlock("iron_block_reinforced"), () -> VanillaMetalTypes.IRON,
                        metalType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/iron_block_reinforced"), BLOCK_LOW_CONTRAST)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_reinforced);

        block_sheets = GemsRealmEntrySet.of(MetalType.class, "block_sheets",
                        getModBlock("iron_block_sheets"), () -> VanillaMetalTypes.IRON,
                        metalType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/iron_block_sheets"), BLOCK_LOW_CONTRAST)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_sheets);

        block_smooth = GemsRealmEntrySet.of(MetalType.class, "block_smooth",
                        getModBlock("iron_block_smooth"), () -> VanillaMetalTypes.IRON,
                        metalType -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/iron_block_smooth"), BLOCK_LOW_CONTRAST)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_smooth);

        block_connecting_connecting = GemsRealmEntrySet.of(MetalType.class, "block_connecting_connecting",
                        getModBlock("iron_block_connecting_connecting"), () -> VanillaMetalTypes.IRON,
                        metalType -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/iron_block_connecting"), BLOCK_LOW_CONTRAST)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_connecting_connecting);

    }

    public static final PaletteStrategy BLOCK_LOW_CONTRAST = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, VanillaRockChildKeys.BLOCK, null, p -> {
                //luminance step is the distance between 2 colors. Essentially contrast
                float averageStep = p.getAverageLuminanceStep();
                //lower step = lower contrast. Tweak as needed
                p.matchLuminanceStep(averageStep * 0.85f);
            }));

    @Override
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);
        executor.accept((manager, sink) ->
                createChiselingRecipe(this, MetalTypeRegistry.INSTANCE, sink, "block_(smooth|sheets|reinforced|plated|pulverized)")
        );
    }
}