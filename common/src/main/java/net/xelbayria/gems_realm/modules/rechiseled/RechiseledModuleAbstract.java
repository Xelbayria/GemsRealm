package net.xelbayria.gems_realm.modules.rechiseled;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.supermartijn642.core.TextComponents;
import com.supermartijn642.core.block.BaseBlock;
import net.mehvahdjukaar.every_compat.api.EntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.xelbayria.gems_realm.api.GemsRealmEntrySet;
import net.xelbayria.gems_realm.api.GemsRealmModule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Consumer;

import static com.supermartijn642.rechiseled.blocks.RechiseledPillarBlock.AXIS_PROPERTY;
import static net.xelbayria.gems_realm.api.set.VanillaRockChildKeys.*;
import static net.xelbayria.gems_realm.misc.HardcodedBlockType.isKnownVanillaBlockType;

//SUPPORT: v1.1.6
public abstract class RechiseledModuleAbstract extends GemsRealmModule {

    public final ResourceLocation tab = modRes("rechiseled");

    public RechiseledModuleAbstract(String modId) {
        super(modId, "rcd");
    }

    /// CUSTOM CLASS - REASON: Changed BlockProperties to Properties to use Utils.copyPropertySafe()
    protected class CompatRechiseledBlock extends BaseBlock {
        public final boolean connecting;

        public CompatRechiseledBlock(boolean connecting, BlockBehaviour.Properties properties) {
            super(false, properties);
            this.connecting = connecting;
        }

        protected void appendItemInformation(ItemStack stack, @Nullable BlockGetter level, Consumer<Component> info, boolean advanced) {
            if (this.connecting) {
                info.accept(TextComponents.translation("rechiseled.tooltip.connecting").color(ChatFormatting.GRAY).get());
            }

        }
    }

    protected class CompatRechiseledPillarBlock extends CompatRechiseledBlock {
        public CompatRechiseledPillarBlock(boolean connecting, Properties properties) {
            super(connecting, properties);
            this.registerDefaultState(this.defaultBlockState().setValue(AXIS_PROPERTY, Direction.Axis.Y));
        }

        @Nullable
        @Override
        public BlockState getStateForPlacement(BlockPlaceContext context) {
            return this.defaultBlockState().setValue(AXIS_PROPERTY, context.getClickedFace().getAxis());
        }

        @Override
        @SuppressWarnings("deprecation")
        public @NotNull BlockState rotate(@NotNull BlockState state, @NotNull Rotation rotation){
            if(rotation == Rotation.CLOCKWISE_90 || rotation == Rotation.COUNTERCLOCKWISE_90){
                Direction.Axis axis = state.getValue(AXIS_PROPERTY);
                if(axis != Direction.Axis.Y)
                    return state.setValue(AXIS_PROPERTY, axis == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X);
            }
            return state;
        }

        @Override
        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
            builder.add(AXIS_PROPERTY);
        }
    }

//    public static class CompatBaseBlock extends BaseBlock {
//        public CompatBaseBlock(boolean saveTileData, BlockBehaviour.Properties properties) {
//            super(saveTileData, properties);
//        }
//    }

    /// @param regex a specific keyword for to skip blocks that don't have the blocks with "_connecting"
    public void createChiselingRecipe(GemsRealmModule module, BlockTypeRegistry<?> blockTypeRegistry, ResourceSink sink, String regex) {
        for (BlockType blockType : blockTypeRegistry) {

            if (isKnownVanillaBlockType(blockType)) continue;

            // Adding all supported-blocks of a StoneType to Array
            JsonArray entriesArray = new JsonArray();

            for (EntrySet<?> entry : module.getEntries()) {
                JsonObject entryObj = new JsonObject();

                SimpleEntrySet<?, ?> currentEntry = ((SimpleEntrySet<?, ?>) entry);
                String currentName = currentEntry.getName();

                // Get the other block with "_connecting"
                String connectingKey = currentName + "_connecting";

                if (!currentName.contains("_connecting")) { // Skip the blocks with "_connecting"
                    Block currentBlock = currentEntry.blocks.get(blockType);
                    if (Objects.nonNull(currentBlock)) {
                        entryObj.addProperty("item", Utils.getID(currentBlock).toString());

                        if (!currentName.matches(regex)) { // Skip blc it don't have "_connecting" block
                            GemsRealmEntrySet<?, ?> connectingEntry = (GemsRealmEntrySet<?, ?>) this.getEntry(connectingKey);
                            Block connectingBlock = connectingEntry.blocks.get(blockType);
                            if (Objects.nonNull(connectingBlock))
                                entryObj.addProperty("connecting_item", Utils.getID(connectingBlock).toString());
                        }

                        entriesArray.add(entryObj);
                    }
                }
            }

            // Adding vanilla blocks to Array
            String[] vanillaBlocks = {
                    BLOCK,
                    BRICKS,
                    MOSSY_BRICKS,
                    CRACKED_BRICKS,
                    SMOOTH
            };

            for (var key : vanillaBlocks) {
                Block currentBlock = blockType.getBlockOfThis(key);
                if (Objects.nonNull(currentBlock)) {
                    JsonObject entryObj = new JsonObject();
                    entryObj.addProperty("item", Utils.getID(currentBlock).toString());
                    if (key.matches("block")) {
                        Block connectingBlock = blockType.getBlockOfThis("rechiseled:block_connecting_connecting");
                        if (Objects.nonNull(connectingBlock))
                            entryObj.addProperty("connecting_item", Utils.getID(connectingBlock).toString());
                    }
                    entriesArray.add(entryObj);
                }
            }

            // Recipes
            JsonObject chiseling_recipe = new JsonObject();
            chiseling_recipe.addProperty("type", "rechiseled:chiseling");
            chiseling_recipe.addProperty("overwrite", false);
            chiseling_recipe.add("entries", entriesArray);

            // Adding to resources
            ResourceLocation resLoc = modRes("chiseling_recipes/" + blockType.getAppendableId());
            sink.addJson(resLoc, chiseling_recipe, ResType.JSON);

        }

    }
}