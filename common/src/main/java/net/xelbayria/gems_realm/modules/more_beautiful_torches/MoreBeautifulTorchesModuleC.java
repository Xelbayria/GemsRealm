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
import net.xelbayria.gems_realm.api.set.CrystalType;
import net.xelbayria.gems_realm.api.set.CrystalTypeRegistry;

//SUPPORT: v3.0.0+
public class MoreBeautifulTorchesModuleC extends GemsRealmModule {

    public final SimpleEntrySet<CrystalType, Block> block_wall_torch,
                                                    block_torch;
    public final SimpleEntrySet<CrystalType, Block> block_soul_wall_torch,
                                                    block_soul_torch;
    public final SimpleEntrySet<CrystalType, Block> block_redstone_wall_torch,
                                                    block_redstone_torch;
    public final SimpleEntrySet<CrystalType, Block> cluster_wall_torch,
                                                    cluster_torch;
    public final SimpleEntrySet<CrystalType, Block> cluster_soul_wall_torch,
                                                    cluster_soul_torch;
    public final SimpleEntrySet<CrystalType, Block> cluster_redstone_wall_torch,
                                                    cluster_redstone_torch;

    public MoreBeautifulTorchesModuleC(String modId) {
        super(modId, "mbt");
        ResourceLocation tab = (PlatHelper.Platform.FABRIC.isFabric())
                ? modRes("goldenfoods_tab")
                : modRes("morebeautifultorches_tab");

        block_wall_torch = GemsRealmEntrySet.of(CrystalType.class, "block_wall_torch",
                        getModBlock("amethyst_block_wall_torch"), CrystalTypeRegistry::getAmethystType,
                        crystalType -> new WallTorchBlock(Utils.copyPropertySafe(Blocks.WALL_TORCH)
                                .noCollission()
                                .instabreak()
                                .lightLevel(l -> 14),
                                ParticleTypes.FLAME
                        )
                )
                .addTextureM(modRes("block/amethyst_block_torch"), GemsRealm.res("block/common_torch_m"))
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(block_wall_torch);

        block_torch = GemsRealmEntrySet.of(CrystalType.class, "block_torch",
                        getModBlock("amethyst_block_torch"), CrystalTypeRegistry::getAmethystType,
                        crystalType -> new TorchBlock(Utils.copyPropertySafe(Blocks.TORCH)
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
                .addCustomItem((crystalType, b, p) -> new StandingAndWallBlockItem(b, block_wall_torch.blocks.get(crystalType), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(block_torch);

        block_soul_wall_torch = GemsRealmEntrySet.of(CrystalType.class, "block_soul_wall_torch",
                        getModBlock("amethyst_block_soul_wall_torch"), CrystalTypeRegistry::getAmethystType,
                        crystalType -> new WallTorchBlock(Utils.copyPropertySafe(Blocks.SOUL_WALL_TORCH), ParticleTypes.SOUL_FIRE_FLAME)
                )
                .addTextureM(modRes("block/amethyst_block_soul_torch"), GemsRealm.res("block/common_torch_m"))
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(block_soul_wall_torch);

        block_soul_torch = GemsRealmEntrySet.of(CrystalType.class, "block_soul_torch",
                        getModBlock("amethyst_block_soul_torch"), CrystalTypeRegistry::getAmethystType,
                        crystalType -> new TorchBlock(Utils.copyPropertySafe(Blocks.SOUL_TORCH), ParticleTypes.SOUL_FIRE_FLAME)
                )
                //TEXTURES: soul_wall_torch
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((crystalType, b, p) -> new StandingAndWallBlockItem(b, block_soul_wall_torch.blocks.get(crystalType), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(block_soul_torch);

        block_redstone_wall_torch = GemsRealmEntrySet.of(CrystalType.class, "block_redstone_wall_torch",
                        getModBlock("amethyst_block_redstone_wall_torch"), CrystalTypeRegistry::getAmethystType,
                        crystalType -> new RedstoneWallTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_WALL_TORCH))
                )
                .addTextureM(modRes("block/amethyst_block_redstone_torch"), GemsRealm.res("block/common_redstone_torch_m"))
                .addTextureM(modRes("block/amethyst_block_redstone_torch_off"), GemsRealm.res("block/common_torch_m"))
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(block_redstone_wall_torch);

        block_redstone_torch = GemsRealmEntrySet.of(CrystalType.class, "block_redstone_torch",
                        getModBlock("amethyst_block_redstone_torch"), CrystalTypeRegistry::getAmethystType,
                        crystalType -> new RedstoneTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_TORCH))
                )
                //TEXTURES: redstone_wall_torch
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((crystalType, b, p) -> new StandingAndWallBlockItem(b, block_redstone_wall_torch.blocks.get(crystalType), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(block_redstone_torch);


        //!! cluster
        cluster_wall_torch = GemsRealmEntrySet.of(CrystalType.class, "cluster_wall_torch",
                        getModBlock("amethyst_cluster_wall_torch"), CrystalTypeRegistry::getAmethystType,
                        crystalType -> new WallTorchBlock(Utils.copyPropertySafe(Blocks.WALL_TORCH)
                                .noCollission()
                                .instabreak()
                                .lightLevel(l -> 14),
                                ParticleTypes.FLAME
                        )
                )
                .createPaletteFromRockChild("cluster")
                .requiresChildren("cluster") //REASON: recipes, textures
                .addTextureM(modRes("block/amethyst_cluster_torch"), GemsRealm.res("block/common_torch_m"))
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(cluster_wall_torch);

        cluster_torch = GemsRealmEntrySet.of(CrystalType.class, "cluster_torch",
                        getModBlock("amethyst_cluster_torch"), CrystalTypeRegistry::getAmethystType,
                        crystalType -> new TorchBlock(Utils.copyPropertySafe(Blocks.TORCH)
                                .noCollission()
                                .instabreak()
                                .lightLevel(l -> 14),
                                ParticleTypes.FLAME
                        )
                )
                .requiresChildren("cluster") //REASON: recipes, textures
                //TEXTURES: wall_torch
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((crystalType, b, p) -> new StandingAndWallBlockItem(b, cluster_wall_torch.blocks.get(crystalType), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(cluster_torch);

        cluster_soul_wall_torch = GemsRealmEntrySet.of(CrystalType.class, "cluster_soul_wall_torch",
                        getModBlock("amethyst_cluster_soul_wall_torch"), CrystalTypeRegistry::getAmethystType,
                        crystalType -> new WallTorchBlock(Utils.copyPropertySafe(Blocks.SOUL_WALL_TORCH), ParticleTypes.SOUL_FIRE_FLAME)
                )
                .createPaletteFromRockChild("cluster")
                .requiresChildren("cluster") //REASON: recipes, textures
                .addTextureM(modRes("block/amethyst_cluster_soul_torch"), GemsRealm.res("block/common_torch_m"))
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(cluster_soul_wall_torch);

        cluster_soul_torch = GemsRealmEntrySet.of(CrystalType.class, "cluster_soul_torch",
                        getModBlock("amethyst_cluster_soul_torch"), CrystalTypeRegistry::getAmethystType,
                        crystalType -> new TorchBlock(Utils.copyPropertySafe(Blocks.SOUL_TORCH), ParticleTypes.SOUL_FIRE_FLAME)
                )
                .requiresChildren("cluster") //REASON: recipes, textures
                //TEXTURES: soul_wall_torch
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((crystalType, b, p) -> new StandingAndWallBlockItem(b, cluster_soul_wall_torch.blocks.get(crystalType), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(cluster_soul_torch);

        cluster_redstone_wall_torch = GemsRealmEntrySet.of(CrystalType.class, "cluster_redstone_wall_torch",
                        getModBlock("amethyst_cluster_redstone_wall_torch"), CrystalTypeRegistry::getAmethystType,
                        crystalType -> new RedstoneWallTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_WALL_TORCH))
                )
                .createPaletteFromRockChild("cluster")
                .requiresChildren("cluster") //REASON: recipes, textures
                .addTextureM(modRes("block/amethyst_cluster_redstone_torch"), GemsRealm.res("block/common_redstone_torch_m"))
                .addTextureM(modRes("block/amethyst_cluster_redstone_torch_off"), GemsRealm.res("block/common_torch_m"))
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(cluster_redstone_wall_torch);

        cluster_redstone_torch = GemsRealmEntrySet.of(CrystalType.class, "cluster_redstone_torch",
                        getModBlock("amethyst_cluster_redstone_torch"), CrystalTypeRegistry::getAmethystType,
                        crystalType -> new RedstoneTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_TORCH))
                )
                .requiresChildren("cluster") //REASON: recipes, textures
                //TEXTURES: redstone_wall_torch
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((crystalType, b, p) -> new StandingAndWallBlockItem(b, cluster_redstone_wall_torch.blocks.get(crystalType), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(cluster_redstone_torch);

    }
}