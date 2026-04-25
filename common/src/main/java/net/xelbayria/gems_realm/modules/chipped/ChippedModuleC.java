package net.xelbayria.gems_realm.modules.chipped;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.xelbayria.gems_realm.api.GemsRealmEntrySet;
import net.xelbayria.gems_realm.api.set.crystal.CrystalType;
import net.xelbayria.gems_realm.api.set.crystal.CrystalTypeRegistry;
import net.xelbayria.gems_realm.api.set.crystal.VanillaCrystalTypes;

import java.util.Objects;
import java.util.function.Consumer;

import static net.xelbayria.gems_realm.api.set.VanillaRockChildKeys.BLOCK;

//See ChippedModuleAbstract's SUPPORTED VERSION
public class ChippedModuleC extends ChippedModuleAbstract {

    public final SimpleEntrySet<CrystalType, Block> angry_block,
            blank_block_carving,
            bordered_block,
            brick_bordered_block,
            carved_block,
            checkered_block_tiles,
            cobbled_block,
            cracked_disordered_block_bricks,
            cracked_flat_block_tiles,
            cracked_block_bricks,
            creeper_block_carving,
            crying_block,
            curly_block_pillar,
            cut_blank_block,
            cut_block_column,
            duh_block,
            edged_block_bricks,
            engraved_block,
            eroded_block,
            etched_block_bricks,
            fine_block_pillar,
            flat_block_tiles,
            glad_block,
            inlayed_block,
            inscribed_block,
            layed_block_bricks,
            loded_block,
            massive_block_bricks,
            offset_block_bricks,
            ornate_block_pillar,
            overlapping_block_tiles,
            pillar_block_bricks,
            polished_block,
            prismal_block_remnants,
            block_bricks,
            block_mini_tiles,
            block_pillar,
            block_pillar_top,
            block_scales,
            rough_block,
            rounded_block_bricks,
            runic_carved_block,
            sad_block,
            sanded_block,
            simple_block_pillar,
            small_block_bricks,
            smooth_inlayed_block,
            smooth_block_column,
            smooth_ringed_block,
            smoothed_double_inlayed_block,
            spider_block_carving,
            spiraled_block,
            stacked_block_bricks,
            thick_inlayed_block,
            tiled_bordered_block,
            tiled_block,
            tiled_block_column,
            tiny_brick_bordered_block,
            tiny_layered_block_bricks,
            tiny_layered_block_slabs,
            tiny_block_bricks,
            trodden_block,
            unamused_block,
            vertical_cut_block,
            vertical_disordered_block_bricks,
            weathered_block;

    public ChippedModuleC(String modId) {
        super(modId);

        angry_block = GemsRealmEntrySet.of(CrystalType.class, "block", "angry",
                        getModBlock("angry_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/angry_amethyst_block"))
                .addTexture(modRes("block/amethyst_block/ctm/common_textures/0"))
                .addTexture(modRes("block/amethyst_block/ctm/common_textures/1"))
                .addTexture(modRes("block/amethyst_block/ctm/common_textures/2"))
                .addTexture(modRes("block/amethyst_block/ctm/common_textures/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(angry_block);

        blank_block_carving = GemsRealmEntrySet.of(CrystalType.class, "block_carving", "blank",
                        getModBlock("blank_amethyst_block_carving"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/blank_amethyst_block_carving"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(blank_block_carving);

        bordered_block = GemsRealmEntrySet.of(CrystalType.class, "block", "bordered",
                        getModBlock("bordered_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/bordered_amethyst_block"))
                .addTexture(modRes("block/amethyst_block/ctm/bordered_amethyst_block_ctm/0"))
                .addTexture(modRes("block/amethyst_block/ctm/bordered_amethyst_block_ctm/1"))
                .addTexture(modRes("block/amethyst_block/ctm/bordered_amethyst_block_ctm/2"))
                .addTexture(modRes("block/amethyst_block/ctm/bordered_amethyst_block_ctm/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(bordered_block);

        brick_bordered_block = GemsRealmEntrySet.of(CrystalType.class, "block", "brick_bordered",
                        getModBlock("brick_bordered_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                //TEXTURES: angry_block's ctm/common_textures/0
                .addTexture(modRes("block/amethyst_block/brick_bordered_amethyst_block"))
                .addTexture(modRes("block/amethyst_block/ctm/brick_bordered_amethyst_block_ctm/1"))
                .addTexture(modRes("block/amethyst_block/ctm/brick_bordered_amethyst_block_ctm/2"))
                .addTexture(modRes("block/amethyst_block/ctm/brick_bordered_amethyst_block_ctm/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(brick_bordered_block);

        carved_block = GemsRealmEntrySet.of(CrystalType.class, "block", "carved",
                        getModBlock("carved_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/carved_amethyst_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(carved_block);

        checkered_block_tiles = GemsRealmEntrySet.of(CrystalType.class, "block_tiles", "checkered",
                        getModBlock("checkered_amethyst_block_tiles"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/checkered_amethyst_block_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(checkered_block_tiles);

        cobbled_block = GemsRealmEntrySet.of(CrystalType.class, "block", "cobbled",
                        getModBlock("cobbled_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/cobbled_amethyst_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(cobbled_block);

        cracked_disordered_block_bricks = GemsRealmEntrySet.of(CrystalType.class, "block_bricks", "cracked_disordered",
                        getModBlock("cracked_disordered_amethyst_block_bricks"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/cracked_disordered_amethyst_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(cracked_disordered_block_bricks);

        cracked_flat_block_tiles = GemsRealmEntrySet.of(CrystalType.class, "block_tiles", "cracked_flat",
                        getModBlock("cracked_flat_amethyst_block_tiles"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/cracked_flat_amethyst_block_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(cracked_flat_block_tiles);

        cracked_block_bricks = GemsRealmEntrySet.of(CrystalType.class, "block_bricks", "cracked",
                        getModBlock("cracked_amethyst_block_bricks"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/cracked_amethyst_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(cracked_block_bricks);

        creeper_block_carving = GemsRealmEntrySet.of(CrystalType.class, "block_carving", "creeper",
                        getModBlock("creeper_amethyst_block_carving"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/creeper_amethyst_block_carving"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(creeper_block_carving);

        crying_block = GemsRealmEntrySet.of(CrystalType.class, "block", "crying",
                        getModBlock("crying_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/crying_amethyst_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(crying_block);

        curly_block_pillar = GemsRealmEntrySet.of(CrystalType.class, "block_pillar", "curly",
                        getModBlock("curly_amethyst_block_pillar"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RotatedPillarBlock(copyPropertyStandard(crystalType))
                )
                //TEXTURES: angry_block/ctm/common_textures/3, polished_block
                .addTexture(modRes("block/amethyst_block/curly_amethyst_block_pillar"))
                .addTexture(modRes("block/amethyst_block/ctm/curly_amethyst_block_pillar/0"))
                .addTexture(modRes("block/amethyst_block/ctm/curly_amethyst_block_pillar/1"))
                .addTexture(modRes("block/amethyst_block/ctm/curly_amethyst_block_pillar/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(curly_block_pillar);

        cut_blank_block = GemsRealmEntrySet.of(CrystalType.class, "block", "cut_blank",
                        getModBlock("cut_blank_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/cut_blank_amethyst_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(cut_blank_block);

        cut_block_column = GemsRealmEntrySet.of(CrystalType.class, "block_column", "cut",
                        getModBlock("cut_amethyst_block_column"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RotatedPillarBlock(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/cut_amethyst_block_column"))
                .addTexture(modRes("block/amethyst_block/ctm/cut_amethyst_block_column_ctm/0"))
                .addTexture(modRes("block/amethyst_block/ctm/cut_amethyst_block_column_ctm/1"))
                .addTexture(modRes("block/amethyst_block/ctm/cut_amethyst_block_column_ctm/2"))
                .addTexture(modRes("block/amethyst_block/ctm/cut_amethyst_block_column_ctm/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(cut_block_column);

        duh_block = GemsRealmEntrySet.of(CrystalType.class, "block", "duh",
                        getModBlock("duh_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/duh_amethyst_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(duh_block);

        edged_block_bricks = GemsRealmEntrySet.of(CrystalType.class, "block_bricks", "edged",
                        getModBlock("edged_amethyst_block_bricks"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/edged_amethyst_block_bricks"))
                .addTexture(modRes("block/amethyst_block/ctm/edged_amethyst_block_bricks_ctm/0"))
                .addTexture(modRes("block/amethyst_block/ctm/edged_amethyst_block_bricks_ctm/1"))
                .addTexture(modRes("block/amethyst_block/ctm/edged_amethyst_block_bricks_ctm/2"))
                .addTexture(modRes("block/amethyst_block/ctm/edged_amethyst_block_bricks_ctm/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(edged_block_bricks);

        engraved_block = GemsRealmEntrySet.of(CrystalType.class, "block", "engraved",
                        getModBlock("engraved_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/engraved_amethyst_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(engraved_block);

        eroded_block = GemsRealmEntrySet.of(CrystalType.class, "block", "eroded",
                        getModBlock("eroded_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/eroded_amethyst_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(eroded_block);

        etched_block_bricks = GemsRealmEntrySet.of(CrystalType.class, "block_bricks", "etched",
                        getModBlock("etched_amethyst_block_bricks"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/etched_amethyst_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(etched_block_bricks);

        fine_block_pillar = GemsRealmEntrySet.of(CrystalType.class, "block_pillar", "fine",
                        getModBlock("fine_amethyst_block_pillar"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RotatedPillarBlock(copyPropertyStandard(crystalType))
                )
                //TEXTURES: angry_block's ctm/common_textures/3
                .addTexture(modRes("block/amethyst_block/fine_amethyst_block_pillar"))
                .addTexture(modRes("block/amethyst_block/ctm/fine_amethyst_block_pillar/0"))
                .addTexture(modRes("block/amethyst_block/ctm/fine_amethyst_block_pillar/1"))
                .addTexture(modRes("block/amethyst_block/ctm/fine_amethyst_block_pillar/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(fine_block_pillar);

        flat_block_tiles = GemsRealmEntrySet.of(CrystalType.class, "block_tiles", "flat",
                        getModBlock("flat_amethyst_block_tiles"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/flat_amethyst_block_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(flat_block_tiles);

        glad_block = GemsRealmEntrySet.of(CrystalType.class, "block", "glad",
                        getModBlock("glad_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/glad_amethyst_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(glad_block);

        inlayed_block = GemsRealmEntrySet.of(CrystalType.class, "block", "inlayed",
                        getModBlock("inlayed_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/inlayed_amethyst_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(inlayed_block);

        inscribed_block = GemsRealmEntrySet.of(CrystalType.class, "block", "inscribed",
                        getModBlock("inscribed_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/inscribed_amethyst_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(inscribed_block);

        layed_block_bricks = GemsRealmEntrySet.of(CrystalType.class, "block_bricks", "layed",
                        getModBlock("layed_amethyst_block_bricks"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/layed_amethyst_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(layed_block_bricks);

        loded_block = GemsRealmEntrySet.of(CrystalType.class, "block", "loded",
                        getModBlock("loded_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/loded_amethyst_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(loded_block);

        massive_block_bricks = GemsRealmEntrySet.of(CrystalType.class, "block_bricks", "massive",
                        getModBlock("massive_amethyst_block_bricks"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/massive_amethyst_block_bricks"))
                .addTexture(modRes("block/amethyst_block/ctm/massive_amethyst_block_bricks/0"))
                .addTexture(modRes("block/amethyst_block/ctm/massive_amethyst_block_bricks/1"))
                .addTexture(modRes("block/amethyst_block/ctm/massive_amethyst_block_bricks/2"))
                .addTexture(modRes("block/amethyst_block/ctm/massive_amethyst_block_bricks/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(massive_block_bricks);

        offset_block_bricks = GemsRealmEntrySet.of(CrystalType.class, "block_bricks", "offset",
                        getModBlock("offset_amethyst_block_bricks"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/offset_amethyst_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(offset_block_bricks);

        ornate_block_pillar = GemsRealmEntrySet.of(CrystalType.class, "block_pillar", "ornate",
                        getModBlock("ornate_amethyst_block_pillar"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RotatedPillarBlock(copyPropertyStandard(crystalType))
                )
                //TEXTURES: angry_block's ctm/common_textures/3
                .addTexture(modRes("block/amethyst_block/ornate_amethyst_block_pillar"))
                .addTexture(modRes("block/amethyst_block/ctm/ornate_amethyst_block_pillar/0"))
                .addTexture(modRes("block/amethyst_block/ctm/ornate_amethyst_block_pillar/1"))
                .addTexture(modRes("block/amethyst_block/ctm/ornate_amethyst_block_pillar/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(ornate_block_pillar);

        overlapping_block_tiles = GemsRealmEntrySet.of(CrystalType.class, "block_tiles", "overlapping",
                        getModBlock("overlapping_amethyst_block_tiles"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                //TEXTURES: angry_block's ctm/common_textures/0
                .addTexture(modRes("block/amethyst_block/overlapping_amethyst_block_tiles"))
                .addTexture(modRes("block/amethyst_block/ctm/overlapping_amethyst_block_tiles_ctm/1"))
                .addTexture(modRes("block/amethyst_block/ctm/overlapping_amethyst_block_tiles_ctm/2"))
                .addTexture(modRes("block/amethyst_block/ctm/overlapping_amethyst_block_tiles_ctm/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(overlapping_block_tiles);

        pillar_block_bricks = GemsRealmEntrySet.of(CrystalType.class, "block_bricks", "pillar",
                        getModBlock("pillar_amethyst_block_bricks"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RotatedPillarBlock(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/pillar_amethyst_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(pillar_block_bricks);

        polished_block = GemsRealmEntrySet.of(CrystalType.class, "block", "polished",
                        getModBlock("polished_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                //TEXTURES: angry_block's ctm/common_textures/0
                .addTexture(modRes("block/amethyst_block/polished_amethyst_block"))
                .addTexture(modRes("block/amethyst_block/ctm/polished_amethyst_block_ctm/1"))
                .addTexture(modRes("block/amethyst_block/ctm/polished_amethyst_block_ctm/2"))
                .addTexture(modRes("block/amethyst_block/ctm/polished_amethyst_block_ctm/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(polished_block);

        prismal_block_remnants = GemsRealmEntrySet.of(CrystalType.class, "block_remnants", "prismal",
                        getModBlock("prismal_amethyst_block_remnants"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/prismal_amethyst_block_remnants"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(prismal_block_remnants);

        block_bricks = GemsRealmEntrySet.of(CrystalType.class, "block_bricks",
                        getModBlock("amethyst_block_bricks"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/amethyst_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_bricks);

        block_mini_tiles = GemsRealmEntrySet.of(CrystalType.class, "block_mini_tiles",
                        getModBlock("amethyst_block_mini_tiles"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/amethyst_block_mini_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_mini_tiles);

        block_pillar = GemsRealmEntrySet.of(CrystalType.class, "block_pillar",
                        getModBlock("amethyst_block_pillar"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RotatedPillarBlock(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/amethyst_block_pillar"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_pillar);

        block_pillar_top = GemsRealmEntrySet.of(CrystalType.class, "block_pillar_top",
                        getModBlock("amethyst_block_pillar_top"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/amethyst_block_pillar_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_pillar_top);

        block_scales = GemsRealmEntrySet.of(CrystalType.class, "block_scales",
                        getModBlock("amethyst_block_scales"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/amethyst_block_scales"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_scales);

        rough_block = GemsRealmEntrySet.of(CrystalType.class, "block", "rough",
                        getModBlock("rough_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/rough_amethyst_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(rough_block);

        rounded_block_bricks = GemsRealmEntrySet.of(CrystalType.class, "block_bricks", "rounded",
                        getModBlock("rounded_amethyst_block_bricks"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/rounded_amethyst_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(rounded_block_bricks);

        runic_carved_block = GemsRealmEntrySet.of(CrystalType.class, "block", "runic_carved",
                        getModBlock("runic_carved_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/runic_carved_amethyst_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(runic_carved_block);

        sad_block = GemsRealmEntrySet.of(CrystalType.class, "block", "sad",
                        getModBlock("sad_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/sad_amethyst_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(sad_block);

        sanded_block = GemsRealmEntrySet.of(CrystalType.class, "block", "sanded",
                        getModBlock("sanded_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/sanded_amethyst_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(sanded_block);

        simple_block_pillar = GemsRealmEntrySet.of(CrystalType.class, "block_pillar", "simple",
                        getModBlock("simple_amethyst_block_pillar"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RotatedPillarBlock(copyPropertyStandard(crystalType))
                )
                //TEXTURES: angry_block's ctm/common_textures/3
                .addTexture(modRes("block/amethyst_block/simple_amethyst_block_pillar"))
                .addTexture(modRes("block/amethyst_block/ctm/simple_amethyst_block_pillar/0"))
                .addTexture(modRes("block/amethyst_block/ctm/simple_amethyst_block_pillar/1"))
                .addTexture(modRes("block/amethyst_block/ctm/simple_amethyst_block_pillar/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(simple_block_pillar);

        small_block_bricks = GemsRealmEntrySet.of(CrystalType.class, "block_bricks", "small",
                        getModBlock("small_amethyst_block_bricks"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/small_amethyst_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(small_block_bricks);

        smooth_inlayed_block = GemsRealmEntrySet.of(CrystalType.class, "block", "smooth_inlayed",
                        getModBlock("smooth_inlayed_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/smooth_inlayed_amethyst_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(smooth_inlayed_block);

        smooth_block_column = GemsRealmEntrySet.of(CrystalType.class, "block_column", "smooth",
                        getModBlock("smooth_amethyst_block_column"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RotatedPillarBlock(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/smooth_amethyst_block_column"))
                .addTexture(modRes("block/amethyst_block/ctm/smooth_amethyst_block_column_ctm/0"))
                .addTexture(modRes("block/amethyst_block/ctm/smooth_amethyst_block_column_ctm/1"))
                .addTexture(modRes("block/amethyst_block/ctm/smooth_amethyst_block_column_ctm/2"))
                .addTexture(modRes("block/amethyst_block/ctm/smooth_amethyst_block_column_ctm/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(smooth_block_column);

        smooth_ringed_block = GemsRealmEntrySet.of(CrystalType.class, "block", "smooth_ringed",
                        getModBlock("smooth_ringed_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/smooth_ringed_amethyst_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(smooth_ringed_block);

        smoothed_double_inlayed_block = GemsRealmEntrySet.of(CrystalType.class, "block", "smoothed_double_inlayed",
                        getModBlock("smoothed_double_inlayed_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/smoothed_double_inlayed_amethyst_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(smoothed_double_inlayed_block);

        spider_block_carving = GemsRealmEntrySet.of(CrystalType.class, "block_carving", "spider",
                        getModBlock("spider_amethyst_block_carving"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/spider_amethyst_block_carving"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(spider_block_carving);

        spiraled_block = GemsRealmEntrySet.of(CrystalType.class, "block", "spiraled",
                        getModBlock("spiraled_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/spiraled_amethyst_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(spiraled_block);

        stacked_block_bricks = GemsRealmEntrySet.of(CrystalType.class, "block_bricks", "stacked",
                        getModBlock("stacked_amethyst_block_bricks"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/stacked_amethyst_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(stacked_block_bricks);

        thick_inlayed_block = GemsRealmEntrySet.of(CrystalType.class, "block", "thick_inlayed",
                        getModBlock("thick_inlayed_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                //TEXTURES: angry_block's ctm/common_textures/1
                //TEXTURES: angry_block's ctm/common_textures/2
                .addTexture(modRes("block/amethyst_block/thick_inlayed_amethyst_block"))
                .addTexture(modRes("block/amethyst_block/ctm/thick_inlayed_amethyst_block_ctm/1"))
                .addTexture(modRes("block/amethyst_block/ctm/thick_inlayed_amethyst_block_ctm/2"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(thick_inlayed_block);

        tiled_bordered_block = GemsRealmEntrySet.of(CrystalType.class, "block", "tiled_bordered",
                        getModBlock("tiled_bordered_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                //TEXTURES: angry_block's ctm/common_textures/0
                .addTexture(modRes("block/amethyst_block/tiled_bordered_amethyst_block"))
                .addTexture(modRes("block/amethyst_block/ctm/tiled_bordered_amethyst_block_ctm/1"))
                .addTexture(modRes("block/amethyst_block/ctm/tiled_bordered_amethyst_block_ctm/2"))
                .addTexture(modRes("block/amethyst_block/ctm/tiled_bordered_amethyst_block_ctm/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(tiled_bordered_block);

        tiled_block = GemsRealmEntrySet.of(CrystalType.class, "block", "tiled",
                        getModBlock("tiled_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/tiled_amethyst_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(tiled_block);

        tiled_block_column = GemsRealmEntrySet.of(CrystalType.class, "block_column", "tiled",
                        getModBlock("tiled_amethyst_block_column"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RotatedPillarBlock(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/tiled_amethyst_block_column"))
                .addTexture(modRes("block/amethyst_block/ctm/tiled_amethyst_block_column_ctm/0"))
                .addTexture(modRes("block/amethyst_block/ctm/tiled_amethyst_block_column_ctm/1"))
                .addTexture(modRes("block/amethyst_block/ctm/tiled_amethyst_block_column_ctm/2"))
                .addTexture(modRes("block/amethyst_block/ctm/tiled_amethyst_block_column_ctm/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(tiled_block_column);

        tiny_brick_bordered_block = GemsRealmEntrySet.of(CrystalType.class, "block", "tiny_brick_bordered",
                        getModBlock("tiny_brick_bordered_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                //TEXTURES: angry_block's ctm/common_textures/1
                //TEXTURES: angry_block's ctm/common_textures/2
                .addTexture(modRes("block/amethyst_block/tiny_brick_bordered_amethyst_block"))
                .addTexture(modRes("block/amethyst_block/ctm/tiny_brick_bordered_amethyst_block_ctm/1"))
                .addTexture(modRes("block/amethyst_block/ctm/tiny_brick_bordered_amethyst_block_ctm/2"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(tiny_brick_bordered_block);

        tiny_layered_block_bricks = GemsRealmEntrySet.of(CrystalType.class, "block_bricks", "tiny_layered",
                        getModBlock("tiny_layered_amethyst_block_bricks"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/tiny_layered_amethyst_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(tiny_layered_block_bricks);

        tiny_layered_block_slabs = GemsRealmEntrySet.of(CrystalType.class, "block_slabs", "tiny_layered",
                        getModBlock("tiny_layered_amethyst_block_slabs"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/tiny_layered_amethyst_block_slabs"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(tiny_layered_block_slabs);

        tiny_block_bricks = GemsRealmEntrySet.of(CrystalType.class, "block_bricks", "tiny",
                        getModBlock("tiny_amethyst_block_bricks"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/tiny_amethyst_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(tiny_block_bricks);

        trodden_block = GemsRealmEntrySet.of(CrystalType.class, "block", "trodden",
                        getModBlock("trodden_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/trodden_amethyst_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(trodden_block);

        unamused_block = GemsRealmEntrySet.of(CrystalType.class, "block", "unamused",
                        getModBlock("unamused_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/unamused_amethyst_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(unamused_block);

        vertical_cut_block = GemsRealmEntrySet.of(CrystalType.class, "block", "vertical_cut",
                        getModBlock("vertical_cut_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/vertical_cut_amethyst_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(vertical_cut_block);

        vertical_disordered_block_bricks = GemsRealmEntrySet.of(CrystalType.class, "block_bricks", "vertical_disordered",
                        getModBlock("vertical_disordered_amethyst_block_bricks"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/vertical_disordered_amethyst_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(vertical_disordered_block_bricks);

        weathered_block = GemsRealmEntrySet.of(CrystalType.class, "block", "weathered",
                        getModBlock("weathered_amethyst_block"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertyStandard(crystalType))
                )
                .addTexture(modRes("block/amethyst_block/weathered_amethyst_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(weathered_block);

    }

    private BlockBehaviour.Properties copyPropertyStandard(CrystalType crystalType) {
        return Utils.copyPropertySafe(Objects.requireNonNull(crystalType.block));
    }

    @Override
    // RECIPES
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) ->
            createWorkbenchRecipe(BLOCK, CrystalTypeRegistry.INSTANCE, Workbench.ALCHEMY_BENCH, sink)
        );
    }
}