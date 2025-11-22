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
import net.xelbayria.gems_realm.api.set.dust.DustType;
import net.xelbayria.gems_realm.api.set.dust.DustTypeRegistry;
import net.xelbayria.gems_realm.api.set.dust.VanillaDustTypes;

import java.util.Objects;
import java.util.function.Consumer;

import static net.xelbayria.gems_realm.api.set.VanillaRockChildKeys.BLOCK;

//See ChippedModuleAbstract's SUPPORTED VERSION
public class ChippedModuleD extends ChippedModuleAbstract {

    public final SimpleEntrySet<DustType, Block> angry_block,
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

    public ChippedModuleD(String modId) {
        super(modId);

        angry_block = GemsRealmEntrySet.of(DustType.class, "block", "angry",
                        getModBlock("angry_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/angry_redstone_block"))
                .addTexture(modRes("block/redstone_block/ctm/common_textures/0"))
                .addTexture(modRes("block/redstone_block/ctm/common_textures/1"))
                .addTexture(modRes("block/redstone_block/ctm/common_textures/2"))
                .addTexture(modRes("block/redstone_block/ctm/common_textures/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(angry_block);

        blank_block_carving = GemsRealmEntrySet.of(DustType.class, "block_carving", "blank",
                        getModBlock("blank_redstone_block_carving"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/blank_redstone_block_carving"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(blank_block_carving);

        bordered_block = GemsRealmEntrySet.of(DustType.class, "block", "bordered",
                        getModBlock("bordered_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/bordered_redstone_block"))
                .addTexture(modRes("block/redstone_block/ctm/bordered_redstone_block_ctm/0"))
                .addTexture(modRes("block/redstone_block/ctm/bordered_redstone_block_ctm/1"))
                .addTexture(modRes("block/redstone_block/ctm/bordered_redstone_block_ctm/2"))
                .addTexture(modRes("block/redstone_block/ctm/bordered_redstone_block_ctm/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(bordered_block);

        brick_bordered_block = GemsRealmEntrySet.of(DustType.class, "block", "brick_bordered",
                        getModBlock("brick_bordered_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                //TEXTURES: angry_block's ctm/common_textures/0
                .addTexture(modRes("block/redstone_block/brick_bordered_redstone_block"))
                .addTexture(modRes("block/redstone_block/ctm/brick_bordered_redstone_block_ctm/1"))
                .addTexture(modRes("block/redstone_block/ctm/brick_bordered_redstone_block_ctm/2"))
                .addTexture(modRes("block/redstone_block/ctm/brick_bordered_redstone_block_ctm/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(brick_bordered_block);

        carved_block = GemsRealmEntrySet.of(DustType.class, "block", "carved",
                        getModBlock("carved_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/carved_redstone_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(carved_block);

        checkered_block_tiles = GemsRealmEntrySet.of(DustType.class, "block_tiles", "checkered",
                        getModBlock("checkered_redstone_block_tiles"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/checkered_redstone_block_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(checkered_block_tiles);

        cobbled_block = GemsRealmEntrySet.of(DustType.class, "block", "cobbled",
                        getModBlock("cobbled_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/cobbled_redstone_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(cobbled_block);

        cracked_disordered_block_bricks = GemsRealmEntrySet.of(DustType.class, "block_bricks", "cracked_disordered",
                        getModBlock("cracked_disordered_redstone_block_bricks"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/cracked_disordered_redstone_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(cracked_disordered_block_bricks);

        cracked_flat_block_tiles = GemsRealmEntrySet.of(DustType.class, "block_tiles", "cracked_flat",
                        getModBlock("cracked_flat_redstone_block_tiles"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/cracked_flat_redstone_block_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(cracked_flat_block_tiles);

        cracked_block_bricks = GemsRealmEntrySet.of(DustType.class, "block_bricks", "cracked",
                        getModBlock("cracked_redstone_block_bricks"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/cracked_redstone_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(cracked_block_bricks);

        creeper_block_carving = GemsRealmEntrySet.of(DustType.class, "block_carving", "creeper",
                        getModBlock("creeper_redstone_block_carving"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/creeper_redstone_block_carving"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(creeper_block_carving);

        crying_block = GemsRealmEntrySet.of(DustType.class, "block", "crying",
                        getModBlock("crying_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/crying_redstone_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(crying_block);

        curly_block_pillar = GemsRealmEntrySet.of(DustType.class, "block_pillar", "curly",
                        getModBlock("curly_redstone_block_pillar"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new RotatedPillarBlock(copyPropertyStandard(dustType))
                )
                //TEXTURES: angry_block's ctm/common_textures/3
                .addTexture(modRes("block/redstone_block/curly_redstone_block_pillar"))
                .addTexture(modRes("block/redstone_block/ctm/curly_redstone_block_pillar/0"))
                .addTexture(modRes("block/redstone_block/ctm/curly_redstone_block_pillar/1"))
                .addTexture(modRes("block/redstone_block/ctm/curly_redstone_block_pillar/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(curly_block_pillar);

        cut_blank_block = GemsRealmEntrySet.of(DustType.class, "block", "cut_blank",
                        getModBlock("cut_blank_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/cut_blank_redstone_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(cut_blank_block);

        cut_block_column = GemsRealmEntrySet.of(DustType.class, "block_column", "cut",
                        getModBlock("cut_redstone_block_column"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new RotatedPillarBlock(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/cut_redstone_block_column"))
                .addTexture(modRes("block/redstone_block/ctm/cut_redstone_block_column_ctm/0"))
                .addTexture(modRes("block/redstone_block/ctm/cut_redstone_block_column_ctm/1"))
                .addTexture(modRes("block/redstone_block/ctm/cut_redstone_block_column_ctm/2"))
                .addTexture(modRes("block/redstone_block/ctm/cut_redstone_block_column_ctm/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(cut_block_column);

        duh_block = GemsRealmEntrySet.of(DustType.class, "block", "duh",
                        getModBlock("duh_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/duh_redstone_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(duh_block);

        edged_block_bricks = GemsRealmEntrySet.of(DustType.class, "block_bricks", "edged",
                        getModBlock("edged_redstone_block_bricks"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/edged_redstone_block_bricks"))
                .addTexture(modRes("block/redstone_block/ctm/edged_redstone_block_bricks_ctm/0"))
                .addTexture(modRes("block/redstone_block/ctm/edged_redstone_block_bricks_ctm/1"))
                .addTexture(modRes("block/redstone_block/ctm/edged_redstone_block_bricks_ctm/2"))
                .addTexture(modRes("block/redstone_block/ctm/edged_redstone_block_bricks_ctm/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(edged_block_bricks);

        engraved_block = GemsRealmEntrySet.of(DustType.class, "block", "engraved",
                        getModBlock("engraved_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/engraved_redstone_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(engraved_block);

        eroded_block = GemsRealmEntrySet.of(DustType.class, "block", "eroded",
                        getModBlock("eroded_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/eroded_redstone_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(eroded_block);

        etched_block_bricks = GemsRealmEntrySet.of(DustType.class, "block_bricks", "etched",
                        getModBlock("etched_redstone_block_bricks"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/etched_redstone_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(etched_block_bricks);

        fine_block_pillar = GemsRealmEntrySet.of(DustType.class, "block_pillar", "fine",
                        getModBlock("fine_redstone_block_pillar"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new RotatedPillarBlock(copyPropertyStandard(dustType))
                )
                //TEXTURES: angry_block's ctm/common_textures/3
                .addTexture(modRes("block/redstone_block/fine_redstone_block_pillar"))
                .addTexture(modRes("block/redstone_block/ctm/fine_redstone_block_pillar/0"))
                .addTexture(modRes("block/redstone_block/ctm/fine_redstone_block_pillar/1"))
                .addTexture(modRes("block/redstone_block/ctm/fine_redstone_block_pillar/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(fine_block_pillar);

        flat_block_tiles = GemsRealmEntrySet.of(DustType.class, "block_tiles", "flat",
                        getModBlock("flat_redstone_block_tiles"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/flat_redstone_block_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(flat_block_tiles);

        glad_block = GemsRealmEntrySet.of(DustType.class, "block", "glad",
                        getModBlock("glad_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/glad_redstone_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(glad_block);

        inlayed_block = GemsRealmEntrySet.of(DustType.class, "block", "inlayed",
                        getModBlock("inlayed_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/inlayed_redstone_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(inlayed_block);

        inscribed_block = GemsRealmEntrySet.of(DustType.class, "block", "inscribed",
                        getModBlock("inscribed_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/inscribed_redstone_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(inscribed_block);

        layed_block_bricks = GemsRealmEntrySet.of(DustType.class, "block_bricks", "layed",
                        getModBlock("layed_redstone_block_bricks"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/layed_redstone_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(layed_block_bricks);

        loded_block = GemsRealmEntrySet.of(DustType.class, "block", "loded",
                        getModBlock("loded_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/loded_redstone_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(loded_block);

        massive_block_bricks = GemsRealmEntrySet.of(DustType.class, "block_bricks", "massive",
                        getModBlock("massive_redstone_block_bricks"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/massive_redstone_block_bricks"))
                .addTexture(modRes("block/redstone_block/ctm/massive_redstone_block_bricks/0"))
                .addTexture(modRes("block/redstone_block/ctm/massive_redstone_block_bricks/1"))
                .addTexture(modRes("block/redstone_block/ctm/massive_redstone_block_bricks/2"))
                .addTexture(modRes("block/redstone_block/ctm/massive_redstone_block_bricks/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(massive_block_bricks);

        offset_block_bricks = GemsRealmEntrySet.of(DustType.class, "block_bricks", "offset",
                        getModBlock("offset_redstone_block_bricks"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/offset_redstone_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(offset_block_bricks);

        ornate_block_pillar = GemsRealmEntrySet.of(DustType.class, "block_pillar", "ornate",
                        getModBlock("ornate_redstone_block_pillar"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new RotatedPillarBlock(copyPropertyStandard(dustType))
                )
                //TEXTURES: angry_block's ctm/common_textures/3
                .addTexture(modRes("block/redstone_block/ornate_redstone_block_pillar"))
                .addTexture(modRes("block/redstone_block/ctm/ornate_redstone_block_pillar/0"))
                .addTexture(modRes("block/redstone_block/ctm/ornate_redstone_block_pillar/1"))
                .addTexture(modRes("block/redstone_block/ctm/ornate_redstone_block_pillar/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(ornate_block_pillar);

        overlapping_block_tiles = GemsRealmEntrySet.of(DustType.class, "block_tiles", "overlapping",
                        getModBlock("overlapping_redstone_block_tiles"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                //TEXTURES: angry_block's ctm/common_textures/0
                .addTexture(modRes("block/redstone_block/overlapping_redstone_block_tiles"))
                .addTexture(modRes("block/redstone_block/ctm/overlapping_redstone_block_tiles_ctm/1"))
                .addTexture(modRes("block/redstone_block/ctm/overlapping_redstone_block_tiles_ctm/2"))
                .addTexture(modRes("block/redstone_block/ctm/overlapping_redstone_block_tiles_ctm/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(overlapping_block_tiles);

        pillar_block_bricks = GemsRealmEntrySet.of(DustType.class, "block_bricks", "pillar",
                        getModBlock("pillar_redstone_block_bricks"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new RotatedPillarBlock(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/pillar_redstone_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(pillar_block_bricks);

        polished_block = GemsRealmEntrySet.of(DustType.class, "block", "polished",
                        getModBlock("polished_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                //TEXTURES: angry_block's ctm/common_textures/0
                .addTexture(modRes("block/redstone_block/polished_redstone_block"))
                .addTexture(modRes("block/redstone_block/ctm/polished_redstone_block_ctm/1"))
                .addTexture(modRes("block/redstone_block/ctm/polished_redstone_block_ctm/2"))
                .addTexture(modRes("block/redstone_block/ctm/polished_redstone_block_ctm/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(polished_block);

        prismal_block_remnants = GemsRealmEntrySet.of(DustType.class, "block_remnants", "prismal",
                        getModBlock("prismal_redstone_block_remnants"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/prismal_redstone_block_remnants"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(prismal_block_remnants);

        block_bricks = GemsRealmEntrySet.of(DustType.class, "block_bricks",
                        getModBlock("redstone_block_bricks"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/redstone_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_bricks);

        block_mini_tiles = GemsRealmEntrySet.of(DustType.class, "block_mini_tiles",
                        getModBlock("redstone_block_mini_tiles"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/redstone_block_mini_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_mini_tiles);

        block_pillar = GemsRealmEntrySet.of(DustType.class, "block_pillar",
                        getModBlock("redstone_block_pillar"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new RotatedPillarBlock(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/redstone_block_pillar"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_pillar);

        block_pillar_top = GemsRealmEntrySet.of(DustType.class, "block_pillar_top",
                        getModBlock("redstone_block_pillar_top"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/redstone_block_pillar_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_pillar_top);

        block_scales = GemsRealmEntrySet.of(DustType.class, "block_scales",
                        getModBlock("redstone_block_scales"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/redstone_block_scales"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_scales);

        rough_block = GemsRealmEntrySet.of(DustType.class, "block", "rough",
                        getModBlock("rough_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/rough_redstone_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(rough_block);

        rounded_block_bricks = GemsRealmEntrySet.of(DustType.class, "block_bricks", "rounded",
                        getModBlock("rounded_redstone_block_bricks"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/rounded_redstone_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(rounded_block_bricks);

        runic_carved_block = GemsRealmEntrySet.of(DustType.class, "block", "runic_carved",
                        getModBlock("runic_carved_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/runic_carved_redstone_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(runic_carved_block);

        sad_block = GemsRealmEntrySet.of(DustType.class, "block", "sad",
                        getModBlock("sad_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/sad_redstone_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(sad_block);

        sanded_block = GemsRealmEntrySet.of(DustType.class, "block", "sanded",
                        getModBlock("sanded_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/sanded_redstone_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(sanded_block);

        simple_block_pillar = GemsRealmEntrySet.of(DustType.class, "block_pillar", "simple",
                        getModBlock("simple_redstone_block_pillar"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new RotatedPillarBlock(copyPropertyStandard(dustType))
                )
                //TEXTURES: angry_block's ctm/common_textures/3
                .addTexture(modRes("block/redstone_block/simple_redstone_block_pillar"))
                .addTexture(modRes("block/redstone_block/ctm/simple_redstone_block_pillar/0"))
                .addTexture(modRes("block/redstone_block/ctm/simple_redstone_block_pillar/1"))
                .addTexture(modRes("block/redstone_block/ctm/simple_redstone_block_pillar/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(simple_block_pillar);

        small_block_bricks = GemsRealmEntrySet.of(DustType.class, "block_bricks", "small",
                        getModBlock("small_redstone_block_bricks"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/small_redstone_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(small_block_bricks);

        smooth_inlayed_block = GemsRealmEntrySet.of(DustType.class, "block", "smooth_inlayed",
                        getModBlock("smooth_inlayed_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/smooth_inlayed_redstone_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(smooth_inlayed_block);

        smooth_block_column = GemsRealmEntrySet.of(DustType.class, "block_column", "smooth",
                        getModBlock("smooth_redstone_block_column"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new RotatedPillarBlock(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/smooth_redstone_block_column"))
                .addTexture(modRes("block/redstone_block/ctm/smooth_redstone_block_column_ctm/0"))
                .addTexture(modRes("block/redstone_block/ctm/smooth_redstone_block_column_ctm/1"))
                .addTexture(modRes("block/redstone_block/ctm/smooth_redstone_block_column_ctm/2"))
                .addTexture(modRes("block/redstone_block/ctm/smooth_redstone_block_column_ctm/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(smooth_block_column);

        smooth_ringed_block = GemsRealmEntrySet.of(DustType.class, "block", "smooth_ringed",
                        getModBlock("smooth_ringed_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/smooth_ringed_redstone_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(smooth_ringed_block);

        smoothed_double_inlayed_block = GemsRealmEntrySet.of(DustType.class, "block", "smoothed_double_inlayed",
                        getModBlock("smoothed_double_inlayed_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/smoothed_double_inlayed_redstone_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(smoothed_double_inlayed_block);

        spider_block_carving = GemsRealmEntrySet.of(DustType.class, "block_carving", "spider",
                        getModBlock("spider_redstone_block_carving"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/spider_redstone_block_carving"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(spider_block_carving);

        spiraled_block = GemsRealmEntrySet.of(DustType.class, "block", "spiraled",
                        getModBlock("spiraled_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/spiraled_redstone_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(spiraled_block);

        stacked_block_bricks = GemsRealmEntrySet.of(DustType.class, "block_bricks", "stacked",
                        getModBlock("stacked_redstone_block_bricks"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/stacked_redstone_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(stacked_block_bricks);

        thick_inlayed_block = GemsRealmEntrySet.of(DustType.class, "block", "thick_inlayed",
                        getModBlock("thick_inlayed_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                //TEXTURES: angry_block's ctm/common_textures/1
                //TEXTURES: angry_block's ctm/common_textures/2
                .addTexture(modRes("block/redstone_block/thick_inlayed_redstone_block"))
                .addTexture(modRes("block/redstone_block/ctm/thick_inlayed_redstone_block_ctm/1"))
                .addTexture(modRes("block/redstone_block/ctm/thick_inlayed_redstone_block_ctm/2"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(thick_inlayed_block);

        tiled_bordered_block = GemsRealmEntrySet.of(DustType.class, "block", "tiled_bordered",
                        getModBlock("tiled_bordered_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                //TEXTURES: angry_block's ctm/common_textures/0
                .addTexture(modRes("block/redstone_block/tiled_bordered_redstone_block"))
                .addTexture(modRes("block/redstone_block/ctm/tiled_bordered_redstone_block_ctm/1"))
                .addTexture(modRes("block/redstone_block/ctm/tiled_bordered_redstone_block_ctm/2"))
                .addTexture(modRes("block/redstone_block/ctm/tiled_bordered_redstone_block_ctm/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(tiled_bordered_block);

        tiled_block = GemsRealmEntrySet.of(DustType.class, "block", "tiled",
                        getModBlock("tiled_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/tiled_redstone_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(tiled_block);

        tiled_block_column = GemsRealmEntrySet.of(DustType.class, "block_column", "tiled",
                        getModBlock("tiled_redstone_block_column"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new RotatedPillarBlock(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/tiled_redstone_block_column"))
                .addTexture(modRes("block/redstone_block/ctm/tiled_redstone_block_column_ctm/0"))
                .addTexture(modRes("block/redstone_block/ctm/tiled_redstone_block_column_ctm/1"))
                .addTexture(modRes("block/redstone_block/ctm/tiled_redstone_block_column_ctm/2"))
                .addTexture(modRes("block/redstone_block/ctm/tiled_redstone_block_column_ctm/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(tiled_block_column);

        tiny_brick_bordered_block = GemsRealmEntrySet.of(DustType.class, "block", "tiny_brick_bordered",
                        getModBlock("tiny_brick_bordered_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                //TEXTURES: angry_block's ctm/common_textures/1
                //TEXTURES: angry_block's ctm/common_textures/2
                .addTexture(modRes("block/redstone_block/tiny_brick_bordered_redstone_block"))
                .addTexture(modRes("block/redstone_block/ctm/tiny_brick_bordered_redstone_block_ctm/1"))
                .addTexture(modRes("block/redstone_block/ctm/tiny_brick_bordered_redstone_block_ctm/2"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(tiny_brick_bordered_block);

        tiny_layered_block_bricks = GemsRealmEntrySet.of(DustType.class, "block_bricks", "tiny_layered",
                        getModBlock("tiny_layered_redstone_block_bricks"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/tiny_layered_redstone_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(tiny_layered_block_bricks);

        tiny_layered_block_slabs = GemsRealmEntrySet.of(DustType.class, "block_slabs", "tiny_layered",
                        getModBlock("tiny_layered_redstone_block_slabs"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/tiny_layered_redstone_block_slabs"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(tiny_layered_block_slabs);

        tiny_block_bricks = GemsRealmEntrySet.of(DustType.class, "block_bricks", "tiny",
                        getModBlock("tiny_redstone_block_bricks"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/tiny_redstone_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(tiny_block_bricks);

        trodden_block = GemsRealmEntrySet.of(DustType.class, "block", "trodden",
                        getModBlock("trodden_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/trodden_redstone_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(trodden_block);

        unamused_block = GemsRealmEntrySet.of(DustType.class, "block", "unamused",
                        getModBlock("unamused_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/unamused_redstone_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(unamused_block);

        vertical_cut_block = GemsRealmEntrySet.of(DustType.class, "block", "vertical_cut",
                        getModBlock("vertical_cut_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/vertical_cut_redstone_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(vertical_cut_block);

        vertical_disordered_block_bricks = GemsRealmEntrySet.of(DustType.class, "block_bricks", "vertical_disordered",
                        getModBlock("vertical_disordered_redstone_block_bricks"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/vertical_disordered_redstone_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(vertical_disordered_block_bricks);

        weathered_block = GemsRealmEntrySet.of(DustType.class, "block", "weathered",
                        getModBlock("weathered_redstone_block"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new Block(copyPropertyStandard(dustType))
                )
                .addTexture(modRes("block/redstone_block/weathered_redstone_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(weathered_block);

    }

    private BlockBehaviour.Properties copyPropertyStandard(DustType dustType) {
        return Utils.copyPropertySafe(Objects.requireNonNull(dustType.block));
    }

    @Override
    // RECIPES
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) ->
            createWorkbenchRecipe(sink, BLOCK, DustTypeRegistry.INSTANCE, Workbench.TINKERING_TABLE)
        );
    }
}