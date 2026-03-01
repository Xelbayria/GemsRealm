package net.xelbayria.gems_realm.modules.rechiseled;

import com.supermartijn642.rechiseled.blocks.RechiseledBlock;
import com.supermartijn642.rechiseled.blocks.RechiseledPillarBlock;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.xelbayria.gems_realm.api.GemsRealmEntrySet;
import net.xelbayria.gems_realm.api.set.crystal.CrystalType;
import net.xelbayria.gems_realm.api.set.crystal.CrystalTypeRegistry;
import net.xelbayria.gems_realm.api.set.crystal.VanillaCrystalTypes;

import java.util.function.Consumer;

//See RechiseledModuleAbstract's SUPPORTED VERSION
public class RechiseledModuleC extends RechiseledModuleAbstract {

    public final SimpleEntrySet<CrystalType, Block> block_beams, block_beams_connecting;
    public final SimpleEntrySet<CrystalType, Block> block_bordered_diagonal_tiles, block_bordered_diagonal_tiles_connecting;
    public final SimpleEntrySet<CrystalType, Block> block_bricks, block_bricks_connecting;
    public final SimpleEntrySet<CrystalType, Block> block_cut, block_cut_connecting;
    public final SimpleEntrySet<CrystalType, Block> block_edged, block_edged_connecting;
    public final SimpleEntrySet<CrystalType, Block> block_pillar, block_pillar_connecting;
    public final SimpleEntrySet<CrystalType, Block> block_polished, block_polished_connecting;
    public final SimpleEntrySet<CrystalType, Block> block_shiny, block_shiny_connecting;
    public final SimpleEntrySet<CrystalType, Block> block_tiles, block_tiles_connecting;
    public final SimpleEntrySet<CrystalType, Block> block_diagonal_tiles;
    public final SimpleEntrySet<CrystalType, Block> block_jewel;
    public final SimpleEntrySet<CrystalType, Block> block_smooth;

    public RechiseledModuleC(String modId) {
        super(modId);

        block_beams = GemsRealmEntrySet.of(CrystalType.class, "block_beams",
                        getModBlock("amethyst_block_beams"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RechiseledBlock(false, Utils.copyPropertySafe(crystalType.block))
                )
                .addTexture(modRes("block/amethyst_block_beams"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(block_beams);

        block_beams_connecting = GemsRealmEntrySet.of(CrystalType.class, "block_beams_connecting",
                        getModBlock("amethyst_block_beams_connecting"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RechiseledBlock(true, Utils.copyPropertySafe(crystalType.block))
                )
                //TEXTURES: amethyst_block_beams
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(block_beams_connecting);

        block_bordered_diagonal_tiles = GemsRealmEntrySet.of(CrystalType.class, "block_bordered_diagonal_tiles",
                        getModBlock("amethyst_block_bordered_diagonal_tiles"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RechiseledBlock(false, Utils.copyPropertySafe(crystalType.block))
                )
                .addTexture(modRes("block/amethyst_block_bordered_diagonal_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(block_bordered_diagonal_tiles);

        block_bordered_diagonal_tiles_connecting = GemsRealmEntrySet.of(CrystalType.class, "block_bordered_diagonal_tiles_connecting",
                        getModBlock("amethyst_block_bordered_diagonal_tiles_connecting"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RechiseledBlock(true, Utils.copyPropertySafe(crystalType.block))
                )
                //TEXTURES: amethyst_block_bordered_diagonal_tiles
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(block_bordered_diagonal_tiles_connecting);

        block_bricks = GemsRealmEntrySet.of(CrystalType.class, "block_bricks",
                        getModBlock("amethyst_block_bricks"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RechiseledBlock(false, Utils.copyPropertySafe(crystalType.block))
                )
                .addTexture(modRes("block/amethyst_block_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(block_bricks);

        block_bricks_connecting = GemsRealmEntrySet.of(CrystalType.class, "block_bricks_connecting",
                        getModBlock("amethyst_block_bricks_connecting"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RechiseledBlock(true, Utils.copyPropertySafe(crystalType.block))
                )
                //TEXTURES: amethyst_block_bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(block_bricks_connecting);

        block_cut = GemsRealmEntrySet.of(CrystalType.class, "block_cut",
                        getModBlock("amethyst_block_cut"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RechiseledBlock(false, Utils.copyPropertySafe(crystalType.block))
                )
                .addTexture(modRes("block/amethyst_block_cut"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(block_cut);

        block_cut_connecting = GemsRealmEntrySet.of(CrystalType.class, "block_cut_connecting",
                        getModBlock("amethyst_block_cut_connecting"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RechiseledBlock(true, Utils.copyPropertySafe(crystalType.block))
                )
                //TEXTURES: amethyst_block_cut
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(block_cut_connecting);

        block_edged = GemsRealmEntrySet.of(CrystalType.class, "block_edged",
                        getModBlock("amethyst_block_edged"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RechiseledBlock(false, Utils.copyPropertySafe(crystalType.block))
                )
                .addTexture(modRes("block/amethyst_block_edged"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(block_edged);

        block_edged_connecting = GemsRealmEntrySet.of(CrystalType.class, "block_edged_connecting",
                        getModBlock("amethyst_block_edged_connecting"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RechiseledBlock(true, Utils.copyPropertySafe(crystalType.block))
                )
                //TEXTURES: amethyst_block_edged
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(block_edged_connecting);

        block_pillar = GemsRealmEntrySet.of(CrystalType.class, "block_pillar",
                        getModBlock("amethyst_block_pillar"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RechiseledPillarBlock(false, Utils.copyPropertySafe(crystalType.block))
                )
                .addTexture(modRes("block/amethyst_block_pillar_side"))
                .addTexture(modRes("block/amethyst_block_pillar_end"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(block_pillar);

        block_pillar_connecting = GemsRealmEntrySet.of(CrystalType.class, "block_pillar_connecting",
                        getModBlock("amethyst_block_pillar_connecting"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RechiseledPillarBlock(true, Utils.copyPropertySafe(crystalType.block))
                )
                //TEXTURES: amethyst_block_pillar
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(block_pillar_connecting);

        block_polished = GemsRealmEntrySet.of(CrystalType.class, "block_polished",
                        getModBlock("amethyst_block_polished"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RechiseledBlock(false, Utils.copyPropertySafe(crystalType.block))
                )
                .addTexture(modRes("block/amethyst_block_polished"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(block_polished);

        block_polished_connecting = GemsRealmEntrySet.of(CrystalType.class, "block_polished_connecting",
                        getModBlock("amethyst_block_polished_connecting"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RechiseledBlock(true, Utils.copyPropertySafe(crystalType.block))
                )
                //TEXTURES: amethyst_block_polished
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(block_polished_connecting);

        block_shiny = GemsRealmEntrySet.of(CrystalType.class, "block_shiny",
                        getModBlock("amethyst_block_shiny"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RechiseledBlock(false, Utils.copyPropertySafe(crystalType.block))
                )
                .addTexture(modRes("block/amethyst_block_shiny"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(block_shiny);

        block_shiny_connecting = GemsRealmEntrySet.of(CrystalType.class, "block_shiny_connecting",
                        getModBlock("amethyst_block_shiny_connecting"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RechiseledBlock(true, Utils.copyPropertySafe(crystalType.block))
                )
                //TEXTURES: amethyst_block_shiny
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(block_shiny_connecting);

        block_tiles = GemsRealmEntrySet.of(CrystalType.class, "block_tiles",
                        getModBlock("amethyst_block_tiles"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RechiseledBlock(false, Utils.copyPropertySafe(crystalType.block))
                )
                .addTexture(modRes("block/amethyst_block_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(block_tiles);

        block_tiles_connecting = GemsRealmEntrySet.of(CrystalType.class, "block_tiles_connecting",
                        getModBlock("amethyst_block_tiles_connecting"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RechiseledBlock(true, Utils.copyPropertySafe(crystalType.block))
                )
                //TEXTURES: amethyst_block_tiles
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(block_tiles_connecting);

        block_diagonal_tiles = GemsRealmEntrySet.of(CrystalType.class, "block_diagonal_tiles",
                        getModBlock("amethyst_block_diagonal_tiles"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RechiseledBlock(false, Utils.copyPropertySafe(crystalType.block))
                )
                .addTexture(modRes("block/amethyst_block_diagonal_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(block_diagonal_tiles);

        block_jewel = GemsRealmEntrySet.of(CrystalType.class, "block_jewel",
                        getModBlock("amethyst_block_jewel"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RechiseledBlock(false, Utils.copyPropertySafe(crystalType.block))
                )
                .addTexture(modRes("block/amethyst_block_jewel"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(block_jewel);

        block_smooth = GemsRealmEntrySet.of(CrystalType.class, "block_smooth",
                        getModBlock("amethyst_block_smooth"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RechiseledBlock(false, Utils.copyPropertySafe(crystalType.block))
                )
                .addTexture(modRes("block/amethyst_block_smooth"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(block_smooth);

    }

    @Override
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);
        executor.accept((manager, sink) ->
            createChiselingRecipe(this, CrystalTypeRegistry.INSTANCE, sink, "block_(smooth|jewel|diagonal_tiles)")
        );
    }
}