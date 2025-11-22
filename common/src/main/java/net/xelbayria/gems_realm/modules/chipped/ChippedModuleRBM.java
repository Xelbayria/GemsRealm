package net.xelbayria.gems_realm.modules.chipped;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.xelbayria.gems_realm.api.GemsRealmEntrySet;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.api.set.metal.MetalTypeRegistry;
import net.xelbayria.gems_realm.api.set.metal.VanillaMetalTypes;

import java.util.Objects;
import java.util.function.Consumer;

import static net.xelbayria.gems_realm.api.MetalPaletteStrategies.RAW_BLOCK_STANDARD;
import static net.xelbayria.gems_realm.api.set.VanillaRockChildKeys.RAW_BLOCK;

//See ChippedModuleAbstract's SUPPORTED VERSION
/// Raw Block for MetalType
public class ChippedModuleRBM extends ChippedModuleAbstract {

    public final SimpleEntrySet<MetalType, Block> angry_raw_block,
            blank_raw_block_carving,
            bordered_raw_block,
            brick_bordered_raw_block,
            carved_raw_block,
            checkered_raw_block_tiles,
            cobbled_raw_block,
            cracked_disordered_raw_block_bricks,
            cracked_flat_raw_block_tiles,
            cracked_raw_block_bricks,
            creeper_raw_block_carving,
            crying_raw_block,
            curly_raw_block_pillar,
            cut_blank_raw_block,
            cut_raw_block_column,
            duh_raw_block,
            edged_raw_block_bricks,
            engraved_raw_block,
            eroded_raw_block,
            etched_raw_block_bricks,
            fine_raw_block_pillar,
            flat_raw_block_tiles,
            glad_raw_block,
            inlayed_raw_block,
            inscribed_raw_block,
            layed_raw_block_bricks,
            loded_raw_block,
            massive_raw_block_bricks,
            offset_raw_block_bricks,
            ornate_raw_block_pillar,
            overlapping_raw_block_tiles,
            pillar_raw_block_bricks,
            polished_raw_block,
            prismal_raw_block_remnants,
            raw_block_bricks,
            raw_block_mini_tiles,
            raw_block_pillar,
            raw_block_pillar_top,
            raw_block_scales,
            rough_raw_block,
            rounded_raw_block_bricks,
            runic_carved_raw_block,
            sad_raw_block,
            sanded_raw_block,
            simple_raw_block_pillar,
            small_raw_block_bricks,
            smooth_inlayed_raw_block,
            smooth_raw_block_column,
            smooth_ringed_raw_block,
            smoothed_double_inlayed_raw_block,
            spider_raw_block_carving,
            spiraled_raw_block,
            stacked_raw_block_bricks,
            thick_inlayed_raw_block,
            tiled_bordered_raw_block,
            tiled_raw_block,
            tiled_raw_block_column,
            tiny_brick_bordered_raw_block,
            tiny_layered_raw_block_bricks,
            tiny_layered_raw_block_slabs,
            tiny_raw_block_bricks,
            trodden_raw_block,
            unamused_raw_block,
            vertical_cut_raw_block,
            vertical_disordered_raw_block_bricks,
            weathered_raw_block;

    public ChippedModuleRBM(String modId) {
        super(modId);

        angry_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "angry_raw",
                        getModBlock("angry_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/angry_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/common_textures/0"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/common_textures/1"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/common_textures/2"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/common_textures/3"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(angry_raw_block);

        blank_raw_block_carving = GemsRealmEntrySet.of(MetalType.class, "block_carving", "blank_raw",
                        getModBlock("blank_raw_iron_block_carving"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/blank_raw_iron_block_carving"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(blank_raw_block_carving);

        bordered_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "bordered_raw",
                        getModBlock("bordered_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/bordered_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/bordered_raw_iron_block_ctm/0"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/bordered_raw_iron_block_ctm/1"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/bordered_raw_iron_block_ctm/2"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/bordered_raw_iron_block_ctm/3"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(bordered_raw_block);

        brick_bordered_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "brick_bordered_raw",
                        getModBlock("brick_bordered_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                //TEXTURES: angry_raw_block's ctm/common_textures/0
                .addTexture(modRes("block/raw_iron_block/brick_bordered_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/brick_bordered_raw_iron_block_ctm/1"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/brick_bordered_raw_iron_block_ctm/2"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/brick_bordered_raw_iron_block_ctm/3"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(brick_bordered_raw_block);

        carved_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "carved_raw",
                        getModBlock("carved_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/carved_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(carved_raw_block);

        checkered_raw_block_tiles = GemsRealmEntrySet.of(MetalType.class, "block_tiles", "checkered_raw",
                        getModBlock("checkered_raw_iron_block_tiles"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/checkered_raw_iron_block_tiles"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(checkered_raw_block_tiles);

        cobbled_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "cobbled_raw",
                        getModBlock("cobbled_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/cobbled_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(cobbled_raw_block);

        cracked_disordered_raw_block_bricks = GemsRealmEntrySet.of(MetalType.class, "block_bricks", "cracked_disordered_raw",
                        getModBlock("cracked_disordered_raw_iron_block_bricks"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/cracked_disordered_raw_iron_block_bricks"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(cracked_disordered_raw_block_bricks);

        cracked_flat_raw_block_tiles = GemsRealmEntrySet.of(MetalType.class, "block_tiles", "cracked_flat_raw",
                        getModBlock("cracked_flat_raw_iron_block_tiles"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/cracked_flat_raw_iron_block_tiles"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(cracked_flat_raw_block_tiles);

        cracked_raw_block_bricks = GemsRealmEntrySet.of(MetalType.class, "block_bricks", "cracked_raw",
                        getModBlock("cracked_raw_iron_block_bricks"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/cracked_raw_iron_block_bricks"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(cracked_raw_block_bricks);

        creeper_raw_block_carving = GemsRealmEntrySet.of(MetalType.class, "block_carving", "creeper_raw",
                        getModBlock("creeper_raw_iron_block_carving"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/creeper_raw_iron_block_carving"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(creeper_raw_block_carving);

        crying_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "crying_raw",
                        getModBlock("crying_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/crying_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(crying_raw_block);

        curly_raw_block_pillar = GemsRealmEntrySet.of(MetalType.class, "block_pillar", "curly_raw",
                        getModBlock("curly_raw_iron_block_pillar"), () -> VanillaMetalTypes.IRON,
                        metalType -> new RotatedPillarBlock(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                //TEXTURES: angry_raw_block's ctm/common_textures/3
                .addTexture(modRes("block/raw_iron_block/curly_raw_iron_block_pillar"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/curly_raw_iron_block_pillar/0"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/curly_raw_iron_block_pillar/1"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/curly_raw_iron_block_pillar/3"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(curly_raw_block_pillar);

        cut_blank_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "cut_blank_raw",
                        getModBlock("cut_blank_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/cut_blank_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(cut_blank_raw_block);

        cut_raw_block_column = GemsRealmEntrySet.of(MetalType.class, "block_column", "cut_raw",
                        getModBlock("cut_raw_iron_block_column"), () -> VanillaMetalTypes.IRON,
                        metalType -> new RotatedPillarBlock(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/cut_raw_iron_block_column"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/cut_raw_iron_block_column_ctm/0"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/cut_raw_iron_block_column_ctm/1"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/cut_raw_iron_block_column_ctm/2"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/cut_raw_iron_block_column_ctm/3"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(cut_raw_block_column);

        duh_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "duh_raw",
                        getModBlock("duh_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/duh_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(duh_raw_block);

        edged_raw_block_bricks = GemsRealmEntrySet.of(MetalType.class, "block_bricks", "edged_raw",
                        getModBlock("edged_raw_iron_block_bricks"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/edged_raw_iron_block_bricks"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/edged_raw_iron_block_bricks_ctm/0"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/edged_raw_iron_block_bricks_ctm/1"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/edged_raw_iron_block_bricks_ctm/2"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/edged_raw_iron_block_bricks_ctm/3"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(edged_raw_block_bricks);

        engraved_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "engraved_raw",
                        getModBlock("engraved_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/engraved_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(engraved_raw_block);

        eroded_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "eroded_raw",
                        getModBlock("eroded_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/eroded_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(eroded_raw_block);

        etched_raw_block_bricks = GemsRealmEntrySet.of(MetalType.class, "block_bricks", "etched_raw",
                        getModBlock("etched_raw_iron_block_bricks"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/etched_raw_iron_block_bricks"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(etched_raw_block_bricks);

        fine_raw_block_pillar = GemsRealmEntrySet.of(MetalType.class, "block_pillar", "fine_raw",
                        getModBlock("fine_raw_iron_block_pillar"), () -> VanillaMetalTypes.IRON,
                        metalType -> new RotatedPillarBlock(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                //TEXTURES: angry_raw_block's ctm/common_textures/3
                .addTexture(modRes("block/raw_iron_block/fine_raw_iron_block_pillar"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/fine_raw_iron_block_pillar/0"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/fine_raw_iron_block_pillar/1"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/fine_raw_iron_block_pillar/3"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(fine_raw_block_pillar);

        flat_raw_block_tiles = GemsRealmEntrySet.of(MetalType.class, "block_tiles", "flat_raw",
                        getModBlock("flat_raw_iron_block_tiles"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/flat_raw_iron_block_tiles"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(flat_raw_block_tiles);

        glad_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "glad_raw",
                        getModBlock("glad_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/glad_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(glad_raw_block);

        inlayed_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "inlayed_raw",
                        getModBlock("inlayed_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/inlayed_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(inlayed_raw_block);

        inscribed_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "inscribed_raw",
                        getModBlock("inscribed_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/inscribed_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(inscribed_raw_block);

        layed_raw_block_bricks = GemsRealmEntrySet.of(MetalType.class, "block_bricks", "layed_raw",
                        getModBlock("layed_raw_iron_block_bricks"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/layed_raw_iron_block_bricks"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(layed_raw_block_bricks);

        loded_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "loded_raw",
                        getModBlock("loded_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/loded_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(loded_raw_block);

        massive_raw_block_bricks = GemsRealmEntrySet.of(MetalType.class, "block_bricks", "massive_raw",
                        getModBlock("massive_raw_iron_block_bricks"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/massive_raw_iron_block_bricks"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/massive_raw_iron_block_bricks/0"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/massive_raw_iron_block_bricks/1"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/massive_raw_iron_block_bricks/2"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/massive_raw_iron_block_bricks/3"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(massive_raw_block_bricks);

        offset_raw_block_bricks = GemsRealmEntrySet.of(MetalType.class, "block_bricks", "offset_raw",
                        getModBlock("offset_raw_iron_block_bricks"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/offset_raw_iron_block_bricks"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(offset_raw_block_bricks);

        ornate_raw_block_pillar = GemsRealmEntrySet.of(MetalType.class, "block_pillar", "ornate_raw",
                        getModBlock("ornate_raw_iron_block_pillar"), () -> VanillaMetalTypes.IRON,
                        metalType -> new RotatedPillarBlock(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                //TEXTURES: angry_raw_block's ctm/common_textures/3
                .addTexture(modRes("block/raw_iron_block/ornate_raw_iron_block_pillar"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/ornate_raw_iron_block_pillar/0"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/ornate_raw_iron_block_pillar/1"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/ornate_raw_iron_block_pillar/3"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(ornate_raw_block_pillar);

        overlapping_raw_block_tiles = GemsRealmEntrySet.of(MetalType.class, "block_tiles", "overlapping_raw",
                        getModBlock("overlapping_raw_iron_block_tiles"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                //TEXTURES: angry_raw_block's ctm/common_textures/0
                .addTexture(modRes("block/raw_iron_block/overlapping_raw_iron_block_tiles"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/overlapping_raw_iron_block_tiles_ctm/1"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/overlapping_raw_iron_block_tiles_ctm/2"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/overlapping_raw_iron_block_tiles_ctm/3"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(overlapping_raw_block_tiles);

        pillar_raw_block_bricks = GemsRealmEntrySet.of(MetalType.class, "block_bricks", "pillar_raw",
                        getModBlock("pillar_raw_iron_block_bricks"), () -> VanillaMetalTypes.IRON,
                        metalType -> new RotatedPillarBlock(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/pillar_raw_iron_block_bricks"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(pillar_raw_block_bricks);

        polished_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "polished_raw",
                        getModBlock("polished_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                //TEXTURES: angry_raw_block's ctm/common_textures/0
                .addTexture(modRes("block/raw_iron_block/polished_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/polished_raw_iron_block_ctm/1"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/polished_raw_iron_block_ctm/2"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/polished_raw_iron_block_ctm/3"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(polished_raw_block);

        prismal_raw_block_remnants = GemsRealmEntrySet.of(MetalType.class, "block_remnants", "prismal_raw",
                        getModBlock("prismal_raw_iron_block_remnants"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/prismal_raw_iron_block_remnants"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(prismal_raw_block_remnants);

        raw_block_bricks = GemsRealmEntrySet.of(MetalType.class, "block_bricks", "raw",
                        getModBlock("raw_iron_block_bricks"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/raw_iron_block_bricks"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(raw_block_bricks);

        raw_block_mini_tiles = GemsRealmEntrySet.of(MetalType.class, "block_mini_tiles", "raw",
                        getModBlock("raw_iron_block_mini_tiles"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/raw_iron_block_mini_tiles"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(raw_block_mini_tiles);

        raw_block_pillar = GemsRealmEntrySet.of(MetalType.class, "block_pillar", "raw",
                        getModBlock("raw_iron_block_pillar"), () -> VanillaMetalTypes.IRON,
                        metalType -> new RotatedPillarBlock(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/raw_iron_block_pillar"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(raw_block_pillar);

        raw_block_pillar_top = GemsRealmEntrySet.of(MetalType.class, "block_pillar_top", "raw",
                        getModBlock("raw_iron_block_pillar_top"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/raw_iron_block_pillar_top"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(raw_block_pillar_top);

        raw_block_scales = GemsRealmEntrySet.of(MetalType.class, "block_scales", "raw",
                        getModBlock("raw_iron_block_scales"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/raw_iron_block_scales"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(raw_block_scales);

        rough_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "rough_raw",
                        getModBlock("rough_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/rough_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(rough_raw_block);

        rounded_raw_block_bricks = GemsRealmEntrySet.of(MetalType.class, "block_bricks", "rounded_raw",
                        getModBlock("rounded_raw_iron_block_bricks"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/rounded_raw_iron_block_bricks"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(rounded_raw_block_bricks);

        runic_carved_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "runic_carved_raw",
                        getModBlock("runic_carved_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/runic_carved_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(runic_carved_raw_block);

        sad_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "sad_raw",
                        getModBlock("sad_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/sad_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(sad_raw_block);

        sanded_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "sanded_raw",
                        getModBlock("sanded_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/sanded_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(sanded_raw_block);

        simple_raw_block_pillar = GemsRealmEntrySet.of(MetalType.class, "block_pillar", "simple_raw",
                        getModBlock("simple_raw_iron_block_pillar"), () -> VanillaMetalTypes.IRON,
                        metalType -> new RotatedPillarBlock(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                //TEXTURES: angry_raw_block's ctm/common_textures/3
                .addTexture(modRes("block/raw_iron_block/simple_raw_iron_block_pillar"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/simple_raw_iron_block_pillar/0"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/simple_raw_iron_block_pillar/1"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/simple_raw_iron_block_pillar/3"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(simple_raw_block_pillar);

        small_raw_block_bricks = GemsRealmEntrySet.of(MetalType.class, "block_bricks", "small_raw",
                        getModBlock("small_raw_iron_block_bricks"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/small_raw_iron_block_bricks"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(small_raw_block_bricks);

        smooth_inlayed_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "smooth_inlayed_raw",
                        getModBlock("smooth_inlayed_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/smooth_inlayed_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(smooth_inlayed_raw_block);

        smooth_raw_block_column = GemsRealmEntrySet.of(MetalType.class, "block_column", "smooth_raw",
                        getModBlock("smooth_raw_iron_block_column"), () -> VanillaMetalTypes.IRON,
                        metalType -> new RotatedPillarBlock(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/smooth_raw_iron_block_column"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/smooth_raw_iron_block_column_ctm/0"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/smooth_raw_iron_block_column_ctm/1"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/smooth_raw_iron_block_column_ctm/2"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/smooth_raw_iron_block_column_ctm/3"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(smooth_raw_block_column);

        smooth_ringed_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "smooth_ringed_raw",
                        getModBlock("smooth_ringed_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/smooth_ringed_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(smooth_ringed_raw_block);

        smoothed_double_inlayed_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "smoothed_double_inlayed_raw",
                        getModBlock("smoothed_double_inlayed_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/smoothed_double_inlayed_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(smoothed_double_inlayed_raw_block);

        spider_raw_block_carving = GemsRealmEntrySet.of(MetalType.class, "block_carving", "spider_raw",
                        getModBlock("spider_raw_iron_block_carving"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/spider_raw_iron_block_carving"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(spider_raw_block_carving);

        spiraled_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "spiraled_raw",
                        getModBlock("spiraled_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/spiraled_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(spiraled_raw_block);

        stacked_raw_block_bricks = GemsRealmEntrySet.of(MetalType.class, "block_bricks", "stacked_raw",
                        getModBlock("stacked_raw_iron_block_bricks"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/stacked_raw_iron_block_bricks"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(stacked_raw_block_bricks);

        thick_inlayed_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "thick_inlayed_raw",
                        getModBlock("thick_inlayed_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                //TEXTURES: angry_raw_block's ctm/common_textures/1
                //TEXTURES: angry_raw_block's ctm/common_textures/2
                .addTexture(modRes("block/raw_iron_block/thick_inlayed_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/thick_inlayed_raw_iron_block_ctm/1"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/thick_inlayed_raw_iron_block_ctm/2"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(thick_inlayed_raw_block);

        tiled_bordered_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "tiled_bordered_raw",
                        getModBlock("tiled_bordered_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                //TEXTURES: angry_raw_block's ctm/common_textures/0
                .addTexture(modRes("block/raw_iron_block/tiled_bordered_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/tiled_bordered_raw_iron_block_ctm/1"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/tiled_bordered_raw_iron_block_ctm/2"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/tiled_bordered_raw_iron_block_ctm/3"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(tiled_bordered_raw_block);

        tiled_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "tiled_raw",
                        getModBlock("tiled_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/tiled_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(tiled_raw_block);

        tiled_raw_block_column = GemsRealmEntrySet.of(MetalType.class, "block_column", "tiled_raw",
                        getModBlock("tiled_raw_iron_block_column"), () -> VanillaMetalTypes.IRON,
                        metalType -> new RotatedPillarBlock(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/tiled_raw_iron_block_column"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/tiled_raw_iron_block_column_ctm/0"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/tiled_raw_iron_block_column_ctm/1"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/tiled_raw_iron_block_column_ctm/2"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/tiled_raw_iron_block_column_ctm/3"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(tiled_raw_block_column);

        tiny_brick_bordered_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "tiny_brick_bordered_raw",
                        getModBlock("tiny_brick_bordered_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                //TEXTURES: angry_raw_block's ctm/common_textures/1
                //TEXTURES: angry_raw_block's ctm/common_textures/2
                .addTexture(modRes("block/raw_iron_block/tiny_brick_bordered_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/tiny_brick_bordered_raw_iron_block_ctm/1"), RAW_BLOCK_STANDARD)
                .addTexture(modRes("block/raw_iron_block/ctm/tiny_brick_bordered_raw_iron_block_ctm/2"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(tiny_brick_bordered_raw_block);

        tiny_layered_raw_block_bricks = GemsRealmEntrySet.of(MetalType.class, "block_bricks", "tiny_layered_raw",
                        getModBlock("tiny_layered_raw_iron_block_bricks"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/tiny_layered_raw_iron_block_bricks"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(tiny_layered_raw_block_bricks);

        tiny_layered_raw_block_slabs = GemsRealmEntrySet.of(MetalType.class, "block_slabs", "tiny_layered_raw",
                        getModBlock("tiny_layered_raw_iron_block_slabs"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/tiny_layered_raw_iron_block_slabs"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(tiny_layered_raw_block_slabs);

        tiny_raw_block_bricks = GemsRealmEntrySet.of(MetalType.class, "block_bricks", "tiny_raw",
                        getModBlock("tiny_raw_iron_block_bricks"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/tiny_raw_iron_block_bricks"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(tiny_raw_block_bricks);

        trodden_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "trodden_raw",
                        getModBlock("trodden_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/trodden_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(trodden_raw_block);

        unamused_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "unamused_raw",
                        getModBlock("unamused_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/unamused_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(unamused_raw_block);

        vertical_cut_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "vertical_cut_raw",
                        getModBlock("vertical_cut_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/vertical_cut_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(vertical_cut_raw_block);

        vertical_disordered_raw_block_bricks = GemsRealmEntrySet.of(MetalType.class, "block_bricks", "vertical_disordered_raw",
                        getModBlock("vertical_disordered_raw_iron_block_bricks"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/vertical_disordered_raw_iron_block_bricks"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(vertical_disordered_raw_block_bricks);

        weathered_raw_block = GemsRealmEntrySet.of(MetalType.class, "block", "weathered_raw",
                        getModBlock("weathered_raw_iron_block"), () -> VanillaMetalTypes.IRON,
                        metalType -> new Block(Utils.copyPropertySafe(Objects.requireNonNull(metalType.getBlockOfThis(RAW_BLOCK))))
                )
                .requiresChildren(RAW_BLOCK) //REASON: textures, recipes
                .addTexture(modRes("block/raw_iron_block/weathered_raw_iron_block"), RAW_BLOCK_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(weathered_raw_block);

    }

    @Override
    // RECIPES
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) ->
            createWorkbenchRecipe(sink, RAW_BLOCK, MetalTypeRegistry.INSTANCE, Workbench.MASON_TABLE)
        );
    }
}