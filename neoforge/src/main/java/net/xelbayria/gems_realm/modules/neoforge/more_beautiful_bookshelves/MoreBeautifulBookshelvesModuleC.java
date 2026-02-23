package net.xelbayria.gems_realm.modules.neoforge.more_beautiful_bookshelves;

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
import net.xelbayria.gems_realm.api.set.crystal.CrystalType;
import net.xelbayria.gems_realm.api.set.crystal.VanillaCrystalTypes;

import static net.xelbayria.gems_realm.api.set.crystal.VanillaCrystalChildKeys.BUDDING;

//SUPPORT: v2.1.1+
public class MoreBeautifulBookshelvesModuleC extends GemsRealmModule {

    public final SimpleEntrySet<CrystalType, Block> block_bookshelf;
    public final SimpleEntrySet<CrystalType, Block> budding_bookshelf;

    public MoreBeautifulBookshelvesModuleC(String modId) {
        super(modId, "mbb");
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        block_bookshelf = GemsRealmEntrySet.of(CrystalType.class, "block_bookshelf",
                        getModBlock("amethyst_block_bookshelf"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new ModBookshelfTwoTimes(BlockBehaviour.Properties.of()
                                .sound(SoundType.METAL)
                                .strength(0.5F)
                                .requiresCorrectToolForDrops()
                        )
                )
                //TEXTURES: block
                .addTextureM(modRes("block/amethyst_block_bookshelf"), GemsRealm.res("block/mbb/bookshelf_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(block_bookshelf);

        budding_bookshelf = GemsRealmEntrySet.of(CrystalType.class, "bookshelf", "budding",
                        getModBlock("budding_amethyst_bookshelf"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new ModBookshelfTwoTimes(BlockBehaviour.Properties.of()
                                .sound(SoundType.METAL)
                                .strength(0.5F)
                                .requiresCorrectToolForDrops()
                        )
                )
                .createPaletteFromRockChild(BUDDING)
                .requiresChildren(BUDDING) //REASON: recipes, textures
                //TEXTURES: budding
                .addTextureM(modRes("block/budding_amethyst_bookshelf"), GemsRealm.res("block/mbb/bookshelf_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(budding_bookshelf);

    }
}