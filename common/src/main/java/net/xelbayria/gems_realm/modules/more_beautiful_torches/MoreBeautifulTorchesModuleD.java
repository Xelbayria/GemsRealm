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
import net.xelbayria.gems_realm.api.set.dust.DustType;
import net.xelbayria.gems_realm.api.set.dust.VanillaDustTypes;

//SUPPORT: v3.0.0+
public class MoreBeautifulTorchesModuleD extends GemsRealmModule {

    public final SimpleEntrySet<DustType, Block> wall_torch,
                                                 torch;
    public final SimpleEntrySet<DustType, Block> soul_wall_torch,
                                                 soul_torch;
    public final SimpleEntrySet<DustType, Block> redstone_wall_torch,
                                                 redstone_torch;

    public MoreBeautifulTorchesModuleD(String modId) {
        super(modId, "mbt");
        ResourceLocation tab = (PlatHelper.Platform.FABRIC.isFabric())
                ? modRes("goldenfoods_tab")
                : modRes("morebeautifultorches_tab");

        wall_torch = GemsRealmEntrySet.of(DustType.class, "block_wall_torch",
                        getModBlock("redstone_block_wall_torch"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new WallTorchBlock(Utils.copyPropertySafe(Blocks.WALL_TORCH)
                                .noCollission()
                                .instabreak()
                                .lightLevel(l -> 14),
                                ParticleTypes.FLAME
                        )
                )
                .addTextureM(modRes("block/redstone_block_torch"), GemsRealm.res("block/common_torch_m"))
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(wall_torch);

        torch = GemsRealmEntrySet.of(DustType.class, "block_torch",
                        getModBlock("redstone_block_torch"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new TorchBlock(Utils.copyPropertySafe(Blocks.TORCH)
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
                .addCustomItem((dustType, b, p) -> new StandingAndWallBlockItem(b, wall_torch.blocks.get(dustType), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(torch);

        soul_wall_torch = GemsRealmEntrySet.of(DustType.class, "block_soul_wall_torch",
                        getModBlock("redstone_block_soul_wall_torch"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new WallTorchBlock(Utils.copyPropertySafe(Blocks.SOUL_WALL_TORCH), ParticleTypes.SOUL_FIRE_FLAME)
                )
                .addTextureM(modRes("block/redstone_block_soul_torch"), GemsRealm.res("block/common_torch_m"))
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(soul_wall_torch);

        soul_torch = GemsRealmEntrySet.of(DustType.class, "block_soul_torch",
                        getModBlock("redstone_block_soul_torch"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new TorchBlock(Utils.copyPropertySafe(Blocks.SOUL_TORCH), ParticleTypes.SOUL_FIRE_FLAME)
                )
                //TEXTURES: soul_wall_torch
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((dustType, b, p) -> new StandingAndWallBlockItem(b, soul_wall_torch.blocks.get(dustType), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(soul_torch);

        redstone_wall_torch = GemsRealmEntrySet.of(DustType.class, "block_redstone_wall_torch",
                        getModBlock("redstone_block_redstone_wall_torch"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new RedstoneWallTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_WALL_TORCH))
                )
                .addTextureM(modRes("block/redstone_block_redstone_torch"), GemsRealm.res("block/common_redstone_torch_m"))
                .addTextureM(modRes("block/redstone_block_redstone_torch_off"), GemsRealm.res("block/common_torch_m"))
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(redstone_wall_torch);

        redstone_torch = GemsRealmEntrySet.of(DustType.class, "block_redstone_torch",
                        getModBlock("redstone_block_redstone_torch"), () -> VanillaDustTypes.REDSTONE,
                        dustType -> new RedstoneTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_TORCH))
                )
                //TEXTURES: redstone_wall_torch
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((dustType, b, p) -> new StandingAndWallBlockItem(b, redstone_wall_torch.blocks.get(dustType), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(redstone_torch);

    }
}