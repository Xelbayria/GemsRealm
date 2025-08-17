package net.xelbayria.gems_realm.modules.forge.more_beautiful_bookshelves;

import net.ggwpgaming.bad.block.custom.ModBookshelfEightTimes;
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
import net.xelbayria.gems_realm.api.set.gem.GemType;
import net.xelbayria.gems_realm.api.set.gem.VanillaGemTypes;

//SUPPORT: v2.1.1+
public class MoreBeautifulBookshelvesModuleG extends GemsRealmModule {

    public final SimpleEntrySet<GemType, Block> block_bookshelf;

    public MoreBeautifulBookshelvesModuleG(String modId) {
        super(modId, "mbb");
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        block_bookshelf = GemsRealmEntrySet.of(GemType.class, "block_bookshelf",
                        getModBlock("diamond_block_bookshelf"), () -> VanillaGemTypes.DIAMOND,
                        gemType -> new ModBookshelfEightTimes(BlockBehaviour.Properties.of()
                                .sound(SoundType.STONE)
                                .strength(0.5F)
                                .requiresCorrectToolForDrops()
                        )
                )
                //TEXTURES: block
                .addTextureM(modRes("block/diamond_block_bookshelf"), GemsRealm.res("block/mbb/bookshelf_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(block_bookshelf);


    }
}