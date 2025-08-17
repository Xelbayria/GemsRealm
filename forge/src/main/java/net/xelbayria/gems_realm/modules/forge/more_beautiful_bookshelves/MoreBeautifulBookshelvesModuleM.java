package net.xelbayria.gems_realm.modules.forge.more_beautiful_bookshelves;

import net.ggwpgaming.bad.block.custom.ModBookshelfFourTimes;
import net.ggwpgaming.bad.block.custom.ModBookshelfTwoTimes;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.api.GemsRealmEntrySet;
import net.xelbayria.gems_realm.api.GemsRealmModule;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.api.set.metal.VanillaMetalTypes;

import static net.xelbayria.gems_realm.api.set.VanillaRockChildKeys.RAW_BLOCK;

//SUPPORT: v2.1.1+
public class MoreBeautifulBookshelvesModuleM extends GemsRealmModule {

    public final SimpleEntrySet<MetalType, Block> block_bookshelf;
    public final SimpleEntrySet<MetalType, Block> raw_block_bookshelf;

    public MoreBeautifulBookshelvesModuleM(String modId) {
        super(modId, "mbb");
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        block_bookshelf = GemsRealmEntrySet.of(MetalType.class, "block_bookshelf",
                        getModBlock("gold_block_bookshelf"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new ModBookshelfFourTimes(BlockBehaviour.Properties.of()
                                .sound(SoundType.METAL)
                                .strength(0.5F)
                                .requiresCorrectToolForDrops()
                        )
                )
                //TEXTURES: block
                .addTextureM(modRes("block/gold_block_bookshelf"), GemsRealm.res("block/mbb/bookshelf_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(block_bookshelf);

        raw_block_bookshelf = GemsRealmEntrySet.of(MetalType.class, "block_bookshelf", "raw",
                        getModBlock("raw_gold_block_bookshelf"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new ModBookshelfTwoTimes(BlockBehaviour.Properties.of()
                                .sound(SoundType.METAL)
                                .strength(0.5F)
                                .requiresCorrectToolForDrops()
                        )
                )
                .createPaletteFromRockChild(RAW_BLOCK)
                .requiresChildren(RAW_BLOCK) //REASON: recipes, textures
                //TEXTURES: raw_block
                .addTextureM(modRes("block/raw_gold_block_bookshelf"), GemsRealm.res("block/mbb/bookshelf_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(raw_block_bookshelf);

    }
}