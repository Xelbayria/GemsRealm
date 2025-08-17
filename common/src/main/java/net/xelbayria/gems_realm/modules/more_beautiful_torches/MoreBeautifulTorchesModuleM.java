package net.xelbayria.gems_realm.modules.more_beautiful_torches;

import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.*;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.api.GemsRealmEntrySet;
import net.xelbayria.gems_realm.api.GemsRealmModule;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.api.set.metal.VanillaMetalTypes;

import static net.xelbayria.gems_realm.api.set.VanillaRockChildKeys.RAW_BLOCK;

//SUPPORT: v3.0.0+
public class MoreBeautifulTorchesModuleM extends GemsRealmModule {

    public final SimpleEntrySet<MetalType, Block> block_wall_torch,
                                                  block_torch;
    public final SimpleEntrySet<MetalType, Block> block_soul_wall_torch,
                                                  block_soul_torch;
    public final SimpleEntrySet<MetalType, Block> block_redstone_wall_torch,
                                                  block_redstone_torch;
    public final SimpleEntrySet<MetalType, Block> raw_block_wall_torch,
                                                  raw_block_torch;
    public final SimpleEntrySet<MetalType, Block> raw_block_soul_wall_torch,
                                                  raw_block_soul_torch;
    public final SimpleEntrySet<MetalType, Block> raw_block_redstone_wall_torch,
                                                  raw_block_redstone_torch;

    public MoreBeautifulTorchesModuleM(String modId) {
        super(modId, "mbt");
        ResourceLocation tab = (PlatHelper.Platform.FABRIC.isFabric())
                ? modRes("goldenfoods_tab")
                : modRes("morebeautifultorches_tab");

        block_wall_torch = GemsRealmEntrySet.of(MetalType.class, "block_wall_torch",
                        getModBlock("gold_block_wall_torch"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new WallTorchBlock(Utils.copyPropertySafe(Blocks.WALL_TORCH)
                                .noCollission()
                                .instabreak()
                                .lightLevel(l -> 14),
                                ParticleTypes.FLAME
                        )
                )
                .addTextureM(modRes("block/gold_block_torch"), GemsRealm.res("block/common_torch_m"))
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(block_wall_torch);

        block_torch = GemsRealmEntrySet.of(MetalType.class, "block_torch",
                        getModBlock("gold_block_torch"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new TorchBlock(Utils.copyPropertySafe(Blocks.TORCH)
                                .noCollission()
                                .instabreak()
                                .lightLevel(l -> 14),
                                ParticleTypes.FLAME
                        )
                )
                //TEXTURES: wall_torch
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((metalType, b, p) -> new StandingAndWallBlockItem(b, block_wall_torch.blocks.get(metalType), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(block_torch);

        block_soul_wall_torch = GemsRealmEntrySet.of(MetalType.class, "block_soul_wall_torch",
                        getModBlock("gold_block_soul_wall_torch"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new WallTorchBlock(Utils.copyPropertySafe(Blocks.SOUL_WALL_TORCH), ParticleTypes.SOUL_FIRE_FLAME)
                )
                .addTextureM(modRes("block/gold_block_soul_torch"), GemsRealm.res("block/common_torch_m"))
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(block_soul_wall_torch);

        block_soul_torch = GemsRealmEntrySet.of(MetalType.class, "block_soul_torch",
                        getModBlock("gold_block_soul_torch"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new TorchBlock(Utils.copyPropertySafe(Blocks.SOUL_TORCH), ParticleTypes.SOUL_FIRE_FLAME)
                )
                //TEXTURES: soul_wall_torch
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((metalType, b, p) -> new StandingAndWallBlockItem(b, block_soul_wall_torch.blocks.get(metalType), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(block_soul_torch);

        block_redstone_wall_torch = GemsRealmEntrySet.of(MetalType.class, "block_redstone_wall_torch",
                        getModBlock("gold_block_redstone_wall_torch"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new RedstoneWallTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_WALL_TORCH))
                )
                .addTextureM(modRes("block/gold_block_redstone_torch"), GemsRealm.res("block/common_redstone_torch_m"))
                .addTextureM(modRes("block/gold_block_redstone_torch_off"), GemsRealm.res("block/common_torch_m"))
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(block_redstone_wall_torch);

        block_redstone_torch = GemsRealmEntrySet.of(MetalType.class, "block_redstone_torch",
                        getModBlock("gold_block_redstone_torch"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new RedstoneTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_TORCH))
                )
                //TEXTURES: redstone_wall_torch
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((metalType, b, p) -> new StandingAndWallBlockItem(b, block_redstone_wall_torch.blocks.get(metalType), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(block_redstone_torch);


        //!! RAW_BLOCK
        raw_block_wall_torch = GemsRealmEntrySet.of(MetalType.class, "block_wall_torch", "raw",
                        getModBlock("raw_gold_block_wall_torch"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new WallTorchBlock(Utils.copyPropertySafe(Blocks.WALL_TORCH)
                                .noCollission()
                                .instabreak()
                                .lightLevel(l -> 14),
                                ParticleTypes.FLAME
                        )
                )
                .createPaletteFromRockChild("raw_block")
                .requiresChildren(RAW_BLOCK) //REASON: recipes, textures
                .addTextureM(modRes("block/raw_gold_block_torch"), GemsRealm.res("block/common_torch_m"))
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(raw_block_wall_torch);

        raw_block_torch = GemsRealmEntrySet.of(MetalType.class, "block_torch", "raw",
                        getModBlock("raw_gold_block_torch"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new TorchBlock(Utils.copyPropertySafe(Blocks.TORCH)
                                .noCollission()
                                .instabreak()
                                .lightLevel(l -> 14),
                                ParticleTypes.FLAME
                        )
                )
                .requiresChildren(RAW_BLOCK) //REASON: recipes, textures
                //TEXTURES: wall_torch
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((metalType, b, p) -> new StandingAndWallBlockItem(b, raw_block_wall_torch.blocks.get(metalType), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(raw_block_torch);

        raw_block_soul_wall_torch = GemsRealmEntrySet.of(MetalType.class, "block_soul_wall_torch", "raw",
                        getModBlock("raw_gold_block_soul_wall_torch"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new WallTorchBlock(Utils.copyPropertySafe(Blocks.SOUL_WALL_TORCH), ParticleTypes.SOUL_FIRE_FLAME)
                )
                .createPaletteFromRockChild("raw_block")
                .requiresChildren(RAW_BLOCK) //REASON: recipes, textures
                .addTextureM(modRes("block/raw_gold_block_soul_torch"), GemsRealm.res("block/common_torch_m"))
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(raw_block_soul_wall_torch);

        raw_block_soul_torch = GemsRealmEntrySet.of(MetalType.class, "block_soul_torch", "raw",
                        getModBlock("raw_gold_block_soul_torch"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new TorchBlock(Utils.copyPropertySafe(Blocks.SOUL_TORCH), ParticleTypes.SOUL_FIRE_FLAME)
                )
                .requiresChildren(RAW_BLOCK) //REASON: recipes, textures
                //TEXTURES: soul_wall_torch
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((metalType, b, p) -> new StandingAndWallBlockItem(b, raw_block_soul_wall_torch.blocks.get(metalType), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(raw_block_soul_torch);

        raw_block_redstone_wall_torch = GemsRealmEntrySet.of(MetalType.class, "block_redstone_wall_torch", "raw",
                        getModBlock("raw_gold_block_redstone_wall_torch"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new RedstoneWallTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_WALL_TORCH))
                )
                .createPaletteFromRockChild("raw_block")
                .requiresChildren(RAW_BLOCK) //REASON: recipes, textures
                .addTextureM(modRes("block/raw_gold_block_redstone_torch"), GemsRealm.res("block/common_redstone_torch_m"))
                .addTextureM(modRes("block/raw_gold_block_redstone_torch_off"), GemsRealm.res("block/common_torch_m"))
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(raw_block_redstone_wall_torch);

        raw_block_redstone_torch = GemsRealmEntrySet.of(MetalType.class, "block_redstone_torch", "raw",
                        getModBlock("raw_gold_block_redstone_torch"), () -> VanillaMetalTypes.GOLD,
                        metalType -> new RedstoneTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_TORCH))
                )
                .requiresChildren(RAW_BLOCK) //REASON: recipes, textures
                //TEXTURES: redstone_wall_torch
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((metalType, b, p) -> new StandingAndWallBlockItem(b, raw_block_redstone_wall_torch.blocks.get(metalType), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(raw_block_redstone_torch);

    }
}