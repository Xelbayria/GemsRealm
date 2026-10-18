package net.xelbayria.gems_realm.modules.neoforge.create;

import com.github.talrey.createdeco.api.Ladders;
import com.github.talrey.createdeco.api.SheetMetal;
import com.github.talrey.createdeco.blocks.*;
import com.github.talrey.createdeco.items.CatwalkBlockItem;
import com.github.talrey.createdeco.items.CatwalkStairBlockItem;
import com.simibubi.create.content.decoration.MetalLadderBlock;
import com.simibubi.create.content.decoration.palettes.ConnectedGlassPaneBlock;
import com.simibubi.create.content.decoration.palettes.ConnectedPillarBlock;
import com.simibubi.create.content.decoration.palettes.WindowBlock;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.modules.create.CreateDecoModuleAbstract;
import org.joml.Vector3f;

//See CreateDecoModuleAbstract's SUPPORTED VERSION
public class CreateDecoModule extends CreateDecoModuleAbstract {

    public CreateDecoModule(String modId) {
        super(modId);
    }

    @Override
    //REQUIRE: RenderType::cutout_mipped
    protected Block newWindowBlock(MetalType metalType) {
        return new WindowBlock(Utils.copyPropertySafe(Blocks.GLASS)
                .isValidSpawn((s, l, ps, t) -> false)
                .isRedstoneConductor((s, l, ps) -> false)
                .isSuffocating((s, l, ps) -> false)
                .isViewBlocking((s, l, ps) -> false), false);
    }

    @Override
    //Add WindowBlock's mapColor's defaultMapColor
    protected Block newConnectedGlassPaneBlock(MetalType metalType) {
        return new ConnectedGlassPaneBlock(Utils.copyPropertySafe(Blocks.GLASS_PANE)
                .mapColor(window.blocks.get(metalType).defaultMapColor())
        );
    }

    @Override
    protected Block newIronBarsBlock(MetalType metalType) {
        return new IronBarsBlock(BlockBehaviour.Properties.of()
                .noOcclusion()
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .sound(SoundType.NETHERITE_BLOCK)
        );
    }

    @Override
    protected Block newMeshFenceBlock(MetalType metalType) {
        return new MeshFenceBlock(BlockBehaviour.Properties.of()
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .sound(SoundType.CHAIN)
        );
    }

    @Override
    protected Block newCatwalkBlock(MetalType metalType) {
        return new CatwalkBlock(BlockBehaviour.Properties.of()
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .noOcclusion()
                .sound(SoundType.NETHERITE_BLOCK)
        );
    }

    @Override
    protected Item newCatwalkBlockItem(Block block, Item.Properties properties) {
        return new CatwalkBlockItem((CatwalkBlock) block, properties);
    }

    @Override
    protected Block newCatwalkStairBlock(MetalType metalType) {
        return new CatwalkStairBlock(BlockBehaviour.Properties.of()
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .noOcclusion()
                .sound(SoundType.NETHERITE_BLOCK),
                metalType.getTypeName()
        );
    }

    @Override
    protected Item newCatwalkStairBlockItem(Block block, Item.Properties properties) {
        return new CatwalkStairBlockItem((CatwalkStairBlock) block, properties);
    }

    @Override
    //REQUIRE CatwalkCTBehaviour()
    protected Block newCatwalkRailingBlock(MetalType metalType) {
        return new CatwalkRailingBlock(BlockBehaviour.Properties.of()
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .noOcclusion()
                .sound(SoundType.NETHERITE_BLOCK)
        );
    }

    @Override
    protected Block newSupportWedgeBlock(MetalType metalType) {
        return new SupportWedgeBlock(BlockBehaviour.Properties.of()
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .sound(SoundType.NETHERITE_BLOCK)
                .noOcclusion()
                .isViewBlocking((a, b, c) -> false).isSuffocating((a, b, c) -> false)
        );
    }

    @Override
    //REQUIRE RenderType::translucent
    protected Block newFacadeBlock(MetalType metalType) {
        return new FacadeBlock(BlockBehaviour.Properties.of()
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .sound(SoundType.NETHERITE_BLOCK)
                .noOcclusion()
                .isViewBlocking((a, b, c) -> false)
                .isSuffocating((a, b, c) -> false)
        );
    }

    @Override
    /// see {@link Ladders}
    protected Block newMetalLadderBlock(MetalType metalType) {
        return new MetalLadderBlock(Utils.copyPropertySafe(Blocks.LADDER));
    }

    @Override
    protected Block newHullBlock(MetalType metalType) {
        return new HullBlock(BlockBehaviour.Properties.of()
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .sound(SoundType.NETHERITE_BLOCK)
                .noOcclusion()
                .isViewBlocking((a, b, c) -> false)
        );
    }

    @Override
    protected Block newSupportBlock(MetalType metalType) {
        return new SupportBlock(BlockBehaviour.Properties.of()
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .sound(SoundType.NETHERITE_BLOCK)
                .noOcclusion()
                .isViewBlocking((a, b, c) -> false)
                .isSuffocating((a, b, c) -> false)
        );
    }

    @Override
    protected Block newCageLampBlock(MetalType metalType) {
        return new CageLampBlock(
                BlockBehaviour.Properties.of().noOcclusion().strength(0.5F).sound(SoundType.LANTERN)
                        .lightLevel(state -> (Boolean)state.getValue(BlockStateProperties.LIT) ? 15 : 0),
                new Vector3f(0.3F, 0.3F, 0.0F)
        );
    }

    @Override
    // SheetMetal - require RotatedPillarCTBehaviour()
    ///See {@link SheetMetal#buildBlock(CreateRegistrate, String)}
    protected Block newSheetMetalBlock(MetalType metalType) {
        return new ConnectedPillarBlock(BlockBehaviour.Properties.of()
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .sound(SoundType.NETHERITE_BLOCK));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void onClientSetup() {
        super.onClientSetup();
        CreateClientModule.registerWindowCTBehavior(this, "palettes/windows/", window, window_pane);
        CreateDecoClientModule.registerCatwalkCTBehavior(this);
        CreateDecoClientModule.registerSheetMetalCTBehavior(this);
    }

}