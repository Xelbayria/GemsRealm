package net.xelbayria.gems_realm.modules.neoforge.more_beautiful_bookshelves;

import net.ggwpgaming.bad.block.custom.ModBookshelfTwoTimes;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.api.GemsRealmEntrySet;
import net.xelbayria.gems_realm.api.set.dust.DustType;
import net.xelbayria.gems_realm.api.set.dust.VanillaDustTypes;

//SUPPORT: v2.1.1+
public class MoreBeautifulBookshelvesModuleD extends MoreBeautifulBookshelvesModuleAbstract {

    public final SimpleEntrySet<DustType, Block> block_bookshelf;

    public MoreBeautifulBookshelvesModuleD(String modId) {
        super(modId);

        block_bookshelf = GemsRealmEntrySet.of(DustType.class, "block_bookshelf",
                        getModBlock("redstone_block_bookshelf"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new ModBookshelfTwoTimes(BlockBehaviour.Properties.of()
                                .sound(SoundType.STONE)
                                .strength(0.5F)
                                .requiresCorrectToolForDrops()
                        )
                )
                //TEXTURES: block
                .addTextureM(modRes("block/redstone_block_bookshelf"),
                        GemsRealm.res("block/mbb/bookshelf_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .build();
        this.addEntry(block_bookshelf);

    }
}