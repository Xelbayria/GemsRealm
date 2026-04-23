package net.xelbayria.gems_realm.modules.chipped;

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

import static net.xelbayria.gems_realm.api.set.VanillaRockChildKeys.BLOCK;

//See ChippedModuleAbstract's SUPPORTED VERSION
public class ChippedModuleG extends ChippedModuleAbstract {

    public final SimpleEntrySet<GemType, Block> block_panels,
            ancient_block,
            chipped_block,
            embossed_block,
            engraved_block,
            layered_block,
            plated_block,
            pressed_block,
            reinforced_block,
            sheet_block,
            shuttered_block,
            stacked_block;

    public ChippedModuleG(String modId) {
        super(modId);

        block_panels = GemsRealmEntrySet.of(GemType.class, "block_panels",
                        getModBlock("diamond_block_panels"), () -> VanillaGemTypes.DIAMOND,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/diamond_block/diamond_block_panels"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(block_panels);

        ancient_block = GemsRealmEntrySet.of(GemType.class, "block", "ancient",
                        getModBlock("ancient_diamond_block"), () -> VanillaGemTypes.DIAMOND,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/diamond_block/ancient_diamond_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(ancient_block);

        chipped_block = GemsRealmEntrySet.of(GemType.class, "block", "chipped",
                        getModBlock("chipped_diamond_block"), () -> VanillaGemTypes.DIAMOND,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/diamond_block/chipped_diamond_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(chipped_block);

        embossed_block = GemsRealmEntrySet.of(GemType.class, "block", "embossed",
                        getModBlock("embossed_diamond_block"), () -> VanillaGemTypes.DIAMOND,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/diamond_block/embossed_diamond_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(embossed_block);

        engraved_block = GemsRealmEntrySet.of(GemType.class, "block", "engraved",
                        getModBlock("engraved_diamond_block"), () -> VanillaGemTypes.DIAMOND,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/diamond_block/engraved_diamond_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(engraved_block);

        layered_block = GemsRealmEntrySet.of(GemType.class, "block", "layered",
                        getModBlock("layered_diamond_block"), () -> VanillaGemTypes.DIAMOND,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/diamond_block/layered_diamond_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(layered_block);

        plated_block = GemsRealmEntrySet.of(GemType.class, "block", "plated",
                        getModBlock("plated_diamond_block"), () -> VanillaGemTypes.DIAMOND,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/diamond_block/plated_diamond_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(plated_block);

        pressed_block = GemsRealmEntrySet.of(GemType.class, "block", "pressed",
                        getModBlock("pressed_diamond_block"), () -> VanillaGemTypes.DIAMOND,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/diamond_block/pressed_diamond_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(pressed_block);

        reinforced_block = GemsRealmEntrySet.of(GemType.class, "block", "reinforced",
                        getModBlock("reinforced_diamond_block"), () -> VanillaGemTypes.DIAMOND,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/diamond_block/reinforced_diamond_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(reinforced_block);

        sheet_block = GemsRealmEntrySet.of(GemType.class, "block", "sheet",
                        getModBlock("sheet_diamond_block"), () -> VanillaGemTypes.DIAMOND,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/diamond_block/sheet_diamond_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(sheet_block);

        shuttered_block = GemsRealmEntrySet.of(GemType.class, "block", "shuttered",
                        getModBlock("shuttered_diamond_block"), () -> VanillaGemTypes.DIAMOND,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/diamond_block/shuttered_diamond_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(shuttered_block);

        stacked_block = GemsRealmEntrySet.of(GemType.class, "block", "stacked",
                        getModBlock("stacked_diamond_block"), () -> VanillaGemTypes.DIAMOND,
                        metalType -> new Block(Utils.copyPropertySafe(metalType.block))
                )
                .addTexture(modRes("block/diamond_block/stacked_diamond_block"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(stacked_block);

    }

    @Override
    // RECIPES
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) ->
                createWorkbenchRecipe(BLOCK, GemTypeRegistry.INSTANCE, Workbench.ALCHEMY_BENCH, sink)
        );
    }
}