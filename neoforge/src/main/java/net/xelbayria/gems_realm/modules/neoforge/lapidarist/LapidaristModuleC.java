package net.xelbayria.gems_realm.modules.neoforge.lapidarist;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.xelbayria.gems_realm.api.GemsRealmEntrySet;
import net.xelbayria.gems_realm.api.GemsRealmModule;
import net.xelbayria.gems_realm.api.set.crystal.CrystalType;
import net.xelbayria.gems_realm.api.set.crystal.VanillaCrystalTypes;

//SUPPORT: v3.3+
public class LapidaristModuleC extends GemsRealmModule {

    public final SimpleEntrySet<CrystalType, Block> polished;
    public final SimpleEntrySet<CrystalType, Block> chiseled;
    public final SimpleEntrySet<CrystalType, Block> pillar;
    public final SimpleEntrySet<CrystalType, Block> bricks;
    public final SimpleEntrySet<CrystalType, Block> brick_stairs;
    public final SimpleEntrySet<CrystalType, Block> brick_slab;

    public LapidaristModuleC(String modId) {
        super(modId, "lpd");
        ResourceLocation tab = modRes("lapidary_tab");

        polished = GemsRealmEntrySet.of(CrystalType.class, "", "polished",
                        getModBlock("polished_amethyst"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(Utils.copyPropertySafe(crystalType.block))
                )
                .addTexture(modRes("block/polished_amethyst"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(ResourceLocation.parse("minecraft:needs_wooden_tool"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("polished_amethyst_stonecutting"))
                .build();
        this.addEntry(polished);

        chiseled = GemsRealmEntrySet.of(CrystalType.class, "", "chiseled",
                        getModBlock("chiseled_amethyst"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(Utils.copyPropertySafe(crystalType.block))
                )
                .addTexture(modRes("block/chiseled_amethyst"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(ResourceLocation.parse("minecraft:needs_wooden_tool"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("chiseled_amethyst_stonecutting"))
                .addRecipe(modRes("chiseled_amethyst_from_polished_amethyst"))
                .build();
        this.addEntry(chiseled);

        pillar = GemsRealmEntrySet.of(CrystalType.class, "pillar",
                        getModBlock("amethyst_pillar"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new RotatedPillarBlock(copyPropertiesSafe(crystalType))
                )
                .addTexture(modRes("block/amethyst_pillar"))
                .addTexture(modRes("block/amethyst_pillar_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(ResourceLocation.parse("minecraft:needs_wooden_tool"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("amethyst_pillar_from_amethyst_block"))
                .addRecipe(modRes("amethyst_pillar_from_polished_amethyst"))
                .build();
        this.addEntry(pillar);

        bricks = GemsRealmEntrySet.of(CrystalType.class, "bricks",
                        getModBlock("amethyst_bricks"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new Block(copyPropertiesSafe(crystalType))
                )
                .addTexture(modRes("block/amethyst_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(ResourceLocation.parse("minecraft:needs_wooden_tool"), Registries.BLOCK)
                .addTag(BlockTags.BEACON_BASE_BLOCKS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("amethyst_bricks_from_amethyst_block"))
                .addRecipe(modRes("amethyst_bricks_from_polished_amethyst"))
                .build();
        this.addEntry(bricks);

        brick_stairs = GemsRealmEntrySet.of(CrystalType.class, "brick_stairs",
                        getModBlock("amethyst_brick_stairs"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new StairBlock(bricks.blocks.get(crystalType).defaultBlockState(),
                                copyPropertiesSafe(crystalType)
                        )
                )
                .requiresFromMap(bricks.blocks) //REASON: recipes, textures
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(ResourceLocation.parse("minecraft:needs_wooden_tool"), Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("amethyst_brick_stairs_from_amethyst_block_stonecutting"))
                .addRecipe(modRes("amethyst_brick_stairs_from_amethyst_bricks"))
                .addRecipe(modRes("amethyst_brick_stairs_from_polished_amethyst"))
                .build();
        this.addEntry(brick_stairs);

        brick_slab = GemsRealmEntrySet.of(CrystalType.class, "brick_slab",
                        getModBlock("amethyst_brick_slab"), () -> VanillaCrystalTypes.AMETHYST,
                        crystalType -> new SlabBlock(copyPropertiesSafe(crystalType))
                )
                .requiresFromMap(bricks.blocks) //REASON: recipes, textures
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(ResourceLocation.parse("minecraft:needs_wooden_tool"), Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("amethyst_brick_slab_from_amethyst_block_stonecutting"))
                .addRecipe(modRes("amethyst_brick_slab_from_amethyst_bricks_stonecutting"))
                .addRecipe(modRes("amethyst_brick_slab_from_polished_amethyst"))
                .build();
        this.addEntry(brick_slab);

    }

    public BlockBehaviour.Properties copyPropertiesSafe(CrystalType crystalType) {
        return Utils.copyPropertySafe(crystalType.block)
                .sound(SoundType.METAL)
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops();
    }
}