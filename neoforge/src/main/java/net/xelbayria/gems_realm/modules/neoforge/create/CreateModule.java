package net.xelbayria.gems_realm.modules.neoforge.create;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.Create;
import com.simibubi.create.api.registry.SimpleRegistry;
import com.simibubi.create.api.stress.BlockStressValues;
import com.simibubi.create.content.decoration.MetalLadderBlock;
import com.simibubi.create.content.decoration.MetalScaffoldingBlock;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.content.decoration.palettes.ConnectedGlassPaneBlock;
import com.simibubi.create.content.decoration.palettes.WindowBlock;
import com.simibubi.create.content.decoration.slidingDoor.SlidingDoorBlock;
import com.simibubi.create.content.decoration.slidingDoor.SlidingDoorBlockEntity;
import com.simibubi.create.content.decoration.slidingDoor.SlidingDoorRenderer;
import com.simibubi.create.content.kinetics.crank.ValveHandleBlock;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedShaftBlock;
import com.simibubi.create.content.logistics.funnel.BeltFunnelBlock;
import com.simibubi.create.content.logistics.funnel.FunnelBlock;
import com.simibubi.create.content.logistics.funnel.FunnelMovementBehaviour;
import com.simibubi.create.content.logistics.tableCloth.TableClothBlock;
import com.simibubi.create.content.logistics.tableCloth.TableClothBlockEntity;
import com.simibubi.create.content.logistics.tableCloth.TableClothBlockItem;
import com.simibubi.create.content.logistics.tableCloth.TableClothRenderer;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipModifier;
import com.simibubi.create.foundation.ponder.CreatePonderPlugin;
import com.simibubi.create.infrastructure.ponder.AllCreatePonderScenes;
import com.simibubi.create.infrastructure.ponder.AllCreatePonderTags;
import com.simibubi.create.infrastructure.ponder.scenes.FunnelScenes;
import com.simibubi.create.infrastructure.ponder.scenes.KineticsScenes;
import com.simibubi.create.infrastructure.ponder.scenes.highLogistics.TableClothScenes;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.createmod.catnip.data.Couple;
import net.createmod.catnip.lang.FontHelper;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.scene.PonderStoryBoard;
import net.createmod.ponder.foundation.PonderIndex;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.api.GemsRealmModule;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.modules.create.CreateModuleAbstract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

import static com.simibubi.create.AllPartialModels.FOLDING_DOORS;

//See CreateModuleAbstract's SUPPORTED VERSION
@SuppressWarnings("CommentedOutCode")
public class CreateModule extends CreateModuleAbstract {

    private static final CreateRegistrate REGISTRATE = CreateRegistrate.create(GemsRealm.MOD_ID)
            .defaultCreativeTab((ResourceKey<CreativeModeTab>) null);

    public CreateModule(String modId) {
        super(modId);
    }

    protected Block newCasingBlock(MetalType metalType) {
        return new CasingBlock(Utils.copyPropertySafe(metalType.block)
                .sound(metalType.getSound()));
    }

    @Override
    protected Block newEncasedShaftedBlock(MetalType metalType, Supplier<Block> casingBlock) {
        return new EncasedShaftBlock(Utils.copyPropertySafe(metalType.block), casingBlock);
    }

    @Override
    protected Block newEncasedCogwheelBlock(MetalType metalType, Supplier<Block> casingBlock) {
        return new EncasedCogwheelBlock(Utils.copyPropertySafe(metalType.block).noOcclusion(), false, casingBlock);
    }

    @Override
    protected Block newEncasedLargeCogwheelBlock(MetalType metalType, Supplier<Block> casingBlock) {
        return new EncasedCogwheelBlock(Utils.copyPropertySafe(metalType.block).noOcclusion(), true, casingBlock);
    }

    @Override
    protected Block newFunnelBlock(MetalType metalType, Supplier<Block> beltFunnelBlock) {
        Block block = new CompatFunnelBlock(Utils.copyPropertySafe(metalType.block), beltFunnelBlock);

        SimpleRegistry.create().register(block, FunnelMovementBehaviour.brass());

        return block;
    }

    @Override
    protected Block newBeltFunnelBlock(MetalType metalType, Block funnelBlock) {
        BlockEntry<? extends FunnelBlock> blockEntry = new BlockEntry<>(
                Registrate.create(GemsRealm.MOD_ID),
                DeferredBlock.createBlock(Utils.getID(funnelBlock))
        );
        Block block = new BeltFunnelBlock(blockEntry, Utils.copyPropertySafe(metalType.block));
        supportsFilteringList.add(funnelBlock);
        supportsFilteringList.add(block);
        return block;
    }

//    @Override
//    protected Block newTunnelBlock(MetalType metalType) {
//        return new BrassTunnelBlock(Utils.copyPropertySafe(metalType.block).noOcclusion());
//    }

    protected Block makeSlidingDoorBlock(MetalType metalType) {
        return SlidingDoorBlock.metal(Utils.copyPropertySafe(metalType.block)
                        .sound(metalType.getSound())
                        .noOcclusion(),
                true);
    }

    protected Block makeMetalLadderBlock(MetalType metalType) {
        return new MetalLadderBlock(Utils.copyPropertySafe(metalType.block));
    }

    protected Block makeMetalScaffoldingBlock(MetalType metalType) {
        return new MetalScaffoldingBlock(Utils.copyPropertySafe(metalType.block));
    }

    protected Block makeWindow(MetalType metalType) {
        return new WindowBlock(Utils.copyPropertySafe(Blocks.GLASS)
                .isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false), false);
    }

    protected Block makeConnectedGlassPaneBlock(MetalType metalType) {
        return new ConnectedGlassPaneBlock(Utils.copyPropertySafe(Blocks.GLASS_PANE));
    }

    @Override
    protected Block newTableClothBlock(MetalType metalType) {
        return new TableClothBlock(Utils.copyPropertySafe(metalType.block), metalType.getTypeName());
    }

    @Override
    protected Item newTableClothBlockItem(Block block, Item.Properties properties) {
        return new TableClothBlockItem(block, properties);
    }

    @Override
    protected ValveHandleBlock newValveHandleBlock(MetalType metalType) {
        return ValveHandleBlock.copper(Utils.copyPropertySafe(metalType.block));
    }

    //!! ─────────────────────────────────────────────────────────────────────────────────

    @Override
    @OnlyIn(Dist.CLIENT)
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        super.registerBlockEntityRenderers(event);

        event.register(table_cloth.getTile(TableClothBlockEntity.class), TableClothRenderer::new);
        event.register(door.getTile(SlidingDoorBlockEntity.class), SlidingDoorRenderer::new);
    }

    @Override
    public void onModSetup() {
        super.onModSetup();
        putFoldingDoor(this, door);
        registerEncasedBlock(AllBlocks.SHAFT.get(), encased_shaft);
        registerEncasedBlock(AllBlocks.COGWHEEL.get(), encased_cogwheel);
        registerEncasedBlock(AllBlocks.LARGE_COGWHEEL.get(), encased_large_cogwheel);

//        tunnel.blocks.values().forEach(block -> DisplaySource.BY_BLOCK.add(block, AllDisplaySources.ACCUMULATE_ITEMS.get()));
//        tunnel.blocks.values().forEach(block -> DisplaySource.BY_BLOCK.add(block, AllDisplaySources.ITEM_THROUGHPUT.get()));

        // Add a ToolTip to show valve_handle's Stress Capacity
        valve_handle.blocks.values().stream().map(Block::asItem).forEach(item ->
                TooltipModifier.REGISTRY.register(item, new ItemDescription.Modifier(item, FontHelper.Palette.STANDARD_CREATE)
                        .andThen(TooltipModifier.mapNull(KineticStats.create(item))))
        );

    }

    @Override
    public void onClientInit() {
        super.onClientInit();
        PonderIndex.addPlugin(new CompatCreatePonderPlugin());
        valve_handle.blocks.values().forEach(block -> BlockStressValues.CAPACITIES.register(block, () -> 8.0F));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void onClientSetup() {
        super.onClientSetup();
        addToValveHandles(valve_handle);
        VALVE_HANDLES.put(Create.asResource("copper_valve_handle"), PartialModel.of(Create.asResource("block/valve_handle")));

        CreateClientModule.registerCasingCTBehavior(this, casing, encased_shaft, encased_cogwheel, encased_large_cogwheel);
        CreateClientModule.registerScaffoldCTBehavior(this, scaffolding);
        CreateClientModule.registerWindowCTBehavior(this, orante_window, ornate_window_pane);
        CreateClientModule.registerShinglesCTBehavior(this, shingles);
        CreateClientModule.registerTilesCTBehavior(this, tiles);
//        CreateClientModule.registerTunnelCTBehaviour(this, tunnel);
    }

//      ┌──────────────────────────────────────────────────────────┐
//      │                         CLASSES                          │
//      └──────────────────────────────────────────────────────────┘

    public static class CompatFunnelBlock extends FunnelBlock {

        private final Supplier<Block> funnelBlock;

        public CompatFunnelBlock(Properties properties, Supplier<Block> funnelBlock) {
            super(properties);
            this.funnelBlock = funnelBlock;
        }

        @Override
        public BlockState getEquivalentBeltFunnel(BlockGetter world, BlockPos pos, BlockState state) {
            Direction facing = getFacing(state);
            BlockEntry<Block> entry = createStandardBlockEntry(Utils.getID(funnelBlock));

            return entry.getDefaultState()
                    .setValue(BeltFunnelBlock.HORIZONTAL_FACING, facing)
                    .setValue(POWERED, state.getValue(POWERED));
        }
    }

    public class CompatCreatePonderPlugin extends CreatePonderPlugin {
        @Override
        public void registerScenes(@NotNull PonderSceneRegistrationHelper<ResourceLocation> helper) {
            CompatCreatePonderScenes.register(helper, valve_handle, "valve_handle", KineticsScenes::valveHandle);
            CompatCreatePonderScenes.register(helper, casing, "shaft/encasing", KineticsScenes::shaftsCanBeEncased);

            table_cloth.blocks.values().forEach(block ->
                    helper.forComponents(Utils.getID(block)).addStoryBoard("high_logistics/table_cloth", TableClothScenes::tableCloth));

            funnel.blocks.values().forEach(block -> {
                helper.addStoryBoard(Utils.getID(block), "funnels/brass", FunnelScenes::brass);
                helper.forComponents(Utils.getID(block))
                        .addStoryBoard("funnels/intro", FunnelScenes::intro, AllCreatePonderTags.LOGISTICS)
                        .addStoryBoard("funnels/direction", FunnelScenes::directionality)
                        .addStoryBoard("funnels/compat", FunnelScenes::compat)
                        .addStoryBoard("funnels/redstone", FunnelScenes::redstone)
                        .addStoryBoard("funnels/transposer", FunnelScenes::transposer);
            });
        }
    }

    public static class CompatCreatePonderScenes extends AllCreatePonderScenes {

        public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper,
                                    SimpleEntrySet<MetalType, ? extends Block> entrySet,
                                    String schematicPath,
                                    PonderStoryBoard ponderStoryBoard) {

            PonderSceneRegistrationHelper<ItemProviderEntry<?,?>> HELPER = helper.withKeyFunction(RegistryEntry::getId);

            entrySet.blocks.values().forEach(block -> {

                DeferredItem<Item> itemHolder = DeferredItem.createItem(Utils.getID(block));

                ItemProviderEntry<Item, Item> itemEntry = new ItemProviderEntry<>(Registrate.create(GemsRealm.MOD_ID), itemHolder);

                HELPER.addStoryBoard(itemEntry, schematicPath, ponderStoryBoard);
            });

        }

    }

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                         Utitiles                         │
    //      └──────────────────────────────────────────────────────────┘

    protected static BlockEntry<Block> createStandardBlockEntry(ResourceLocation blockId) {
        return new BlockEntry<>(Registrate.create(GemsRealm.MOD_ID), DeferredBlock.createBlock(blockId));
    }

    protected void putFoldingDoor(GemsRealmModule module, SimpleEntrySet<MetalType, Block> doors) {
        doors.blocks.forEach((metalType, block) -> {
            String path = metalType.createPathWith(module.shortenedId(), "door");
            FOLDING_DOORS.put(GemsRealm.res(path),
                    Couple.create(block(path + "/fold_left"), block(path + "/fold_right")));
        });
    }

    private static void addToValveHandles(SimpleEntrySet<MetalType, ValveHandleBlock> valve_handle) {
        valve_handle.blocks.forEach((metalType, block) -> {
            ResourceLocation blockId = Utils.getID(block);
//            AllPartialModels.VALVE_HANDLES.put(blockId, PartialModel.of(blockId.withPrefix("block/")));
            VALVE_HANDLES.put(blockId, PartialModel.of(blockId.withPrefix("block/")));

            KineticStats.create(block.asItem());
        });
    }
}
