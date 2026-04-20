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
import net.xelbayria.gems_realm.api.set.gem.GemType;
import net.xelbayria.gems_realm.api.set.gem.GemTypeRegistry;
import net.xelbayria.gems_realm.api.set.gem.VanillaGemTypes;

import java.util.function.Consumer;

//See RechiseledModuleAbstract's SUPPORTED VERSION
public class RechiseledModuleG extends RechiseledModuleAbstract {

    public final SimpleEntrySet<GemType, Block> block_bordered_crosses, block_bordered_crosses_connecting;
    public final SimpleEntrySet<GemType, Block> block_bordered_plating, block_bordered_plating_connecting;
    public final SimpleEntrySet<GemType, Block> block_chiseled, block_chiseled_connecting;
    public final SimpleEntrySet<GemType, Block> block_clovers, block_clovers_connecting;
    public final SimpleEntrySet<GemType, Block> block_crystal, block_crystal_connecting;
    public final SimpleEntrySet<GemType, Block> block_patterned, block_patterned_connecting;
    public final SimpleEntrySet<GemType, Block> block_patterned_squares, block_patterned_squares_connecting;
    public final SimpleEntrySet<GemType, Block> block_pillar, block_pillar_connecting;
    public final SimpleEntrySet<GemType, Block> block_polished, block_polished_connecting;
    public final SimpleEntrySet<GemType, Block> block_striped, block_striped_connecting;
    public final SimpleEntrySet<GemType, Block> block_waxed, block_waxed_connecting;
    public final SimpleEntrySet<GemType, Block> block_crosses;
    public final SimpleEntrySet<GemType, Block> block_plating;
    public final SimpleEntrySet<GemType, Block> block_smooth;

    public RechiseledModuleG(String modId) {
        super(modId);

        block_bordered_crosses = GemsRealmEntrySet.of(GemType.class, "block_bordered_crosses",
                        getModBlock("emerald_block_bordered_crosses"), () -> VanillaGemTypes.EMERALD,
                        gemType -> new RechiseledBlock(false, Utils.copyPropertySafe(gemType.block))
                )
                .addTexture(modRes("block/emerald_block_bordered_crosses"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_bordered_crosses);

        block_bordered_crosses_connecting = GemsRealmEntrySet.of(GemType.class, "block_bordered_crosses_connecting",
                        getModBlock("emerald_block_bordered_crosses_connecting"), () -> VanillaGemTypes.EMERALD,
                        gemType -> new RechiseledBlock(true, Utils.copyPropertySafe(gemType.block))
                )
                //TEXTURES: emerald_block_bordered_crosses
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_bordered_crosses_connecting);

        block_bordered_plating = GemsRealmEntrySet.of(GemType.class, "block_bordered_plating",
                        getModBlock("emerald_block_bordered_plating"), () -> VanillaGemTypes.EMERALD,
                        gemType -> new RechiseledBlock(false, Utils.copyPropertySafe(gemType.block))
                )
                .addTexture(modRes("block/emerald_block_bordered_plating"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_bordered_plating);

        block_bordered_plating_connecting = GemsRealmEntrySet.of(GemType.class, "block_bordered_plating_connecting",
                        getModBlock("emerald_block_bordered_plating_connecting"), () -> VanillaGemTypes.EMERALD,
                        gemType -> new RechiseledBlock(true, Utils.copyPropertySafe(gemType.block))
                )
                //TEXTURES: emerald_block_bordered_plating
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_bordered_plating_connecting);

        block_chiseled = GemsRealmEntrySet.of(GemType.class, "block_chiseled",
                        getModBlock("emerald_block_chiseled"), () -> VanillaGemTypes.EMERALD,
                        gemType -> new RechiseledBlock(false, Utils.copyPropertySafe(gemType.block))
                )
                .addTexture(modRes("block/emerald_block_chiseled"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_chiseled);

        block_chiseled_connecting = GemsRealmEntrySet.of(GemType.class, "block_chiseled_connecting",
                        getModBlock("emerald_block_chiseled_connecting"), () -> VanillaGemTypes.EMERALD,
                        gemType -> new RechiseledBlock(true, Utils.copyPropertySafe(gemType.block))
                )
                //TEXTURES: emerald_block_chiseled
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_chiseled_connecting);

        block_clovers = GemsRealmEntrySet.of(GemType.class, "block_clovers",
                        getModBlock("emerald_block_clovers"), () -> VanillaGemTypes.EMERALD,
                        gemType -> new RechiseledBlock(false, Utils.copyPropertySafe(gemType.block))
                )
                .addTexture(modRes("block/emerald_block_clovers"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_clovers);

        block_clovers_connecting = GemsRealmEntrySet.of(GemType.class, "block_clovers_connecting",
                        getModBlock("emerald_block_clovers_connecting"), () -> VanillaGemTypes.EMERALD,
                        gemType -> new RechiseledBlock(true, Utils.copyPropertySafe(gemType.block))
                )
                //TEXTURES: emerald_block_clovers
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_clovers_connecting);

        block_crystal = GemsRealmEntrySet.of(GemType.class, "block_crystal",
                        getModBlock("emerald_block_crystal"), () -> VanillaGemTypes.EMERALD,
                        gemType -> new RechiseledBlock(false, Utils.copyPropertySafe(gemType.block))
                )
                .addTexture(modRes("block/emerald_block_crystal"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_crystal);

        block_crystal_connecting = GemsRealmEntrySet.of(GemType.class, "block_crystal_connecting",
                        getModBlock("emerald_block_crystal_connecting"), () -> VanillaGemTypes.EMERALD,
                        gemType -> new RechiseledBlock(true, Utils.copyPropertySafe(gemType.block))
                )
                //TEXTURES: emerald_block_crystal
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_crystal_connecting);

        block_patterned = GemsRealmEntrySet.of(GemType.class, "block_patterned",
                        getModBlock("emerald_block_patterned"), () -> VanillaGemTypes.EMERALD,
                        gemType -> new RechiseledBlock(false, Utils.copyPropertySafe(gemType.block))
                )
                .addTexture(modRes("block/emerald_block_patterned"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_patterned);

        block_patterned_connecting = GemsRealmEntrySet.of(GemType.class, "block_patterned_connecting",
                        getModBlock("emerald_block_patterned_connecting"), () -> VanillaGemTypes.EMERALD,
                        gemType -> new RechiseledBlock(true, Utils.copyPropertySafe(gemType.block))
                )
                //TEXTURES: emerald_block_patterned
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_patterned_connecting);

        block_patterned_squares = GemsRealmEntrySet.of(GemType.class, "block_patterned_squares",
                        getModBlock("emerald_block_patterned_squares"), () -> VanillaGemTypes.EMERALD,
                        gemType -> new RechiseledBlock(false, Utils.copyPropertySafe(gemType.block))
                )
                .addTexture(modRes("block/emerald_block_patterned_squares"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_patterned_squares);

        block_patterned_squares_connecting = GemsRealmEntrySet.of(GemType.class, "block_patterned_squares_connecting",
                        getModBlock("emerald_block_patterned_squares_connecting"), () -> VanillaGemTypes.EMERALD,
                        gemType -> new RechiseledBlock(true, Utils.copyPropertySafe(gemType.block))
                )
                //TEXTURES: emerald_block_patterned_squares
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_patterned_squares_connecting);

        block_pillar = GemsRealmEntrySet.of(GemType.class, "block_pillar",
                        getModBlock("emerald_block_pillar"), () -> VanillaGemTypes.EMERALD,
                        gemType -> new RechiseledPillarBlock(false, Utils.copyPropertySafe(gemType.block))
                )
                .addTexture(modRes("block/emerald_block_pillar_side"))
                .addTexture(modRes("block/emerald_block_pillar_end"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_pillar);

        block_pillar_connecting = GemsRealmEntrySet.of(GemType.class, "block_pillar_connecting",
                        getModBlock("emerald_block_pillar_connecting"), () -> VanillaGemTypes.EMERALD,
                        gemType -> new RechiseledPillarBlock(true, Utils.copyPropertySafe(gemType.block))
                )
                //TEXTURES: emerald_block_pillar_side, emerald_block_pillar_end
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_pillar_connecting);

        block_polished = GemsRealmEntrySet.of(GemType.class, "block_polished",
                        getModBlock("emerald_block_polished"), () -> VanillaGemTypes.EMERALD,
                        gemType -> new RechiseledBlock(false, Utils.copyPropertySafe(gemType.block))
                )
                .addTexture(modRes("block/emerald_block_polished"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_polished);

        block_polished_connecting = GemsRealmEntrySet.of(GemType.class, "block_polished_connecting",
                        getModBlock("emerald_block_polished_connecting"), () -> VanillaGemTypes.EMERALD,
                        gemType -> new RechiseledBlock(true, Utils.copyPropertySafe(gemType.block))
                )
                //TEXTURES: emerald_block_polished
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_polished_connecting);

        block_striped = GemsRealmEntrySet.of(GemType.class, "block_striped",
                        getModBlock("emerald_block_striped"), () -> VanillaGemTypes.EMERALD,
                        gemType -> new RechiseledBlock(false, Utils.copyPropertySafe(gemType.block))
                )
                .addTexture(modRes("block/emerald_block_striped"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_striped);

        block_striped_connecting = GemsRealmEntrySet.of(GemType.class, "block_striped_connecting",
                        getModBlock("emerald_block_striped_connecting"), () -> VanillaGemTypes.EMERALD,
                        gemType -> new RechiseledBlock(true, Utils.copyPropertySafe(gemType.block))
                )
                //TEXTURES: emerald_block_striped
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_striped_connecting);

        block_waxed = GemsRealmEntrySet.of(GemType.class, "block_waxed",
                        getModBlock("emerald_block_waxed"), () -> VanillaGemTypes.EMERALD,
                        gemType -> new RechiseledBlock(false, Utils.copyPropertySafe(gemType.block))
                )
                .addTexture(modRes("block/emerald_block_waxed"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_waxed);

        block_waxed_connecting = GemsRealmEntrySet.of(GemType.class, "block_waxed_connecting",
                        getModBlock("emerald_block_waxed_connecting"), () -> VanillaGemTypes.EMERALD,
                        gemType -> new RechiseledBlock(true, Utils.copyPropertySafe(gemType.block))
                )
                //TEXTURES: emerald_block_waxed
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_waxed_connecting);

        block_crosses = GemsRealmEntrySet.of(GemType.class, "block_crosses",
                        getModBlock("emerald_block_crosses"), () -> VanillaGemTypes.EMERALD,
                        gemType -> new RechiseledBlock(false, Utils.copyPropertySafe(gemType.block))
                )
                .addTexture(modRes("block/emerald_block_crosses"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_crosses);

        block_plating = GemsRealmEntrySet.of(GemType.class, "block_plating",
                        getModBlock("emerald_block_plating"), () -> VanillaGemTypes.EMERALD,
                        gemType -> new RechiseledBlock(false, Utils.copyPropertySafe(gemType.block))
                )
                .addTexture(modRes("block/emerald_block_plating"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_plating);

        block_smooth = GemsRealmEntrySet.of(GemType.class, "block_smooth",
                        getModBlock("emerald_block_smooth"), () -> VanillaGemTypes.EMERALD,
                        gemType -> new RechiseledBlock(false, Utils.copyPropertySafe(gemType.block))
                )
                .addTexture(modRes("block/emerald_block_smooth"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: see addDynamicServerResources
                .build();
        this.addEntry(block_smooth);

    }

    @Override
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);
        executor.accept((manager, sink) ->
                createChiselingRecipe(this, GemTypeRegistry.INSTANCE, sink, "block_(smooth|plating|crosses)")
        );
    }
}