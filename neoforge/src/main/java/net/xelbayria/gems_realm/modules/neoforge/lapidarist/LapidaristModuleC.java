package net.xelbayria.gems_realm.modules.neoforge.lapidarist;

import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.xelbayria.gems_realm.api.set.crystal.CrystalType;
import net.xelbayria.gems_realm.api.set.crystal.VanillaCrystalTypes;
import net.xelbayria.gems_realm.common_classes.BlockSetEntries;

//See LapidaristModuleAbstract's SUPPORTED VERSION
public class LapidaristModuleC extends LapidaristModuleAbstract {

    public final BlockSetEntries<CrystalType> polished,
            chiseled,
            pillar,
            bricksSet;

    public LapidaristModuleC(String modId) {
        super(modId);

        polished = BlockSetEntries.ofBlockParent(
                        this, CrystalType.class, VanillaCrystalTypes.AMETHYST,
                        "polished_amethyst", type -> type.block,
                        tabSupplier
                )
                .block(builder -> builder
                        .addTag(ResourceLocation.parse("minecraft:needs_wooden_tool"), Registries.BLOCK)
                        .addRecipe(modRes("polished_amethyst_stonecutting"))
                )
                .defaultBlockTexture()
                .pickaxeTag()
                .defaultRecipe()
                .build();
        this.addEntries(polished);

        chiseled = BlockSetEntries.ofBlockParent(
                        this, CrystalType.class, VanillaCrystalTypes.AMETHYST,
                        "chiseled_amethyst", type -> type.block,
                        tabSupplier
                )
                .block(builder -> builder
                        .requiresFromMap(polished.block.blocks)
                        .addTag(ResourceLocation.parse("minecraft:needs_wooden_tool"), Registries.BLOCK)
                        .addRecipe(modRes("chiseled_amethyst_from_amethyst_block"))
                        .addRecipe(modRes("chiseled_amethyst_from_amethyst_bricks"))
                        .addRecipe(modRes("chiseled_amethyst_from_polished_amethyst"))
                )
                .defaultBlockTexture()
                .pickaxeTag()
                .applyOneTag(ResourceLocation.parse("minecraft:needs_wooden_tool"), Registries.BLOCK)
                .defaultRecipe()
                .build();
        this.addEntries(chiseled);

        pillar = BlockSetEntries.ofBlockFactory(
                        this, CrystalType.class, VanillaCrystalTypes.AMETHYST,
                        "amethyst_pillar", type -> new RotatedPillarBlock(copyPropertiesSafe(type)),
                        tabSupplier
                )
                .block(builder -> builder
                        .requiresFromMap(polished.block.blocks)
                        .addTag(ResourceLocation.parse("minecraft:needs_wooden_tool"), Registries.BLOCK)
                        .addRecipe(modRes("amethyst_pillar_from_amethyst_block"))
                        .addRecipe(modRes("amethyst_pillar_from_polished_amethyst"))
                )
                .defaultBlockTexture()
                .defaultBlockTextureTop()
                .pickaxeTag()
                .defaultRecipe()
                .build();
        this.addEntries(pillar);

        bricksSet = BlockSetEntries.of3DefaultProperty(
                        this, CrystalType.class, VanillaCrystalTypes.AMETHYST,
                        "amethyst_bricks", this::copyPropertiesSafe,
                        "amethyst_brick_slab", this::copyPropertiesSafe,
                        "amethyst_brick_stairs", this::copyPropertiesSafe,
                        tabSupplier
                )
                .block(builder -> builder
                        .requiresFromMap(polished.block.blocks)
                        .addTag(BlockTags.BEACON_BASE_BLOCKS, Registries.BLOCK)
                        .addTag(ResourceLocation.parse("minecraft:needs_wooden_tool"), Registries.BLOCK)
                        .addRecipe(modRes("amethyst_bricks_from_amethyst_block"))
                        .addRecipe(modRes("amethyst_bricks_from_polished_amethyst"))
                )
                .slab(builder -> builder
                        //TEXTURES: bricks
                        .addTag(BlockTags.SLABS, Registries.BLOCK)
                        .addTag(ResourceLocation.parse("minecraft:needs_wooden_tool"), Registries.BLOCK)
                        .addRecipe(modRes("amethyst_brick_slab_from_amethyst_block_stonecutting"))
                        .addRecipe(modRes("amethyst_brick_slab_from_amethyst_bricks_stonecutting"))
                        .addRecipe(modRes("amethyst_brick_slab_from_polished_amethyst"))
                )
                .stairs(builder -> builder
                        //TEXTURES: bricks
                        .addTag(BlockTags.STAIRS, Registries.BLOCK)
                        .addTag(ResourceLocation.parse("minecraft:needs_wooden_tool"), Registries.BLOCK)
                        .addRecipe(modRes("amethyst_brick_stairs_from_amethyst_block"))
                        .addRecipe(modRes("amethyst_brick_stairs_from_amethyst_bricks"))
                        .addRecipe(modRes("amethyst_brick_stairs_from_polished_amethyst"))
                )
                .defaultBlockTexture()
                .pickaxeTag()
                .defaultRecipe()
                .build();
        this.addEntries(bricksSet);

    }

    public BlockBehaviour.Properties copyPropertiesSafe(CrystalType crystalType) {
        return Utils.copyPropertySafe(crystalType.block)
                .sound(SoundType.METAL)
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops();
    }
}