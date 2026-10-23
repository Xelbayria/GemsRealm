package net.xelbayria.gems_realm.modules.rechiseled;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.supermartijn642.rechiseled.blocks.RechiseledBlock;
import com.supermartijn642.rechiseled.blocks.RechiseledPillarBlock;
import net.mehvahdjukaar.every_compat.api.EntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.api.GemsRealmEntrySet;
import net.xelbayria.gems_realm.api.GemsRealmModule;
import net.xelbayria.gems_realm.api.set.RockType;
import net.xelbayria.gems_realm.common_classes.BlockSetEntries;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.xelbayria.gems_realm.api.set.VanillaRockChildKeys.*;
import static net.xelbayria.gems_realm.misc.HardcodedBlockType.isKnownVanillaBlockType;

///SUPPORT: v1.2.6
public abstract class RechiseledModuleAbstract extends GemsRealmModule {

    protected final Supplier<CreativeModeTab> TAB = getModTab("rechiseled");
    protected static String mod_id;

    public RechiseledModuleAbstract(String modId) {
        super(modId, "rcd");
        mod_id = modId;
    }

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

//      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
//      │                                           CLASSES                                            │
//      └──────────────────────────────────────────────────────────────────────────────────────────────┘

    public static class RechiseledSetEntries<T extends RockType> extends BlockSetEntries<T> {

        protected RechiseledSetEntries(
                SimpleEntrySet<T, Block> block,
                @Nullable SimpleEntrySet<T, Block> connectingBlock) {
            super(block, connectingBlock, null, null);
        }

        //      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
        //      │                                           Builder                                            │
        //      └──────────────────────────────────────────────────────────────────────────────────────────────┘
        /**
         * The class to generate one block is following:
         * <p style="color: LIME;">BlockFactory:</p> new Rechiseled(false, Utils.copyPropertySafe(type.block))<br>
         * <p style="color: YELLOW;">The default added tag:</p>
         * - MINEABLE_WITH_PICKAXE
        **/
        public static <T extends RockType> BlockSetEntries.BlockSetBuilder<T> ofBlockDefault(
                GemsRealmModule module, Class<T> typeClass, T baseTypeId,
                String getBlockPath,
                Supplier<CreativeModeTab> tabSupplier)
        {
//            String childKeyPath = getChildKeyPath(getBlockPath, baseTypeId);

            return BlockSetEntries.ofBlockFactory(module, typeClass, baseTypeId,
                            getBlockPath, type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.block)),
                            tabSupplier
                    )
                    .pickaxeTag();
        }

        /**
         * The classes to generate new blocks are following:
         * <p style="color: LIME;">BlockFactory:</p> new Rechiseled(false, Utils.copyPropertySafe(type.block))<br>
         * <p style="color: ORANGE;">extraBlockFactory:</p> new Rechiseled(true, Utils.copyPropertySafe(type.block))<br>
         * <p style="color: YELLOW;">The default added tag:</p>
         * - MINEABLE_WITH_PICKAXE<br>
        **/
        public static <T extends RockType> BlockSetEntries.BlockSetBuilder<T> of2BlockDefault(
                GemsRealmModule module, Class<T> typeClass, T baseTypeId,
                String getBlockPath,
                String getConnectingBlockPath,
                Supplier<CreativeModeTab> tabSupplier)
        {

            return of2BlocksFactory(module, typeClass, baseTypeId,
                    getBlockPath, type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.block)),
                    getConnectingBlockPath, type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.block)),
                    tabSupplier
            );
        }

        /**
         * The classes to generate new blocks are following:
         * <p style="color: LIME;">BlockFactory:</p> new RechiseledPillarBlock(false, Utils.copyPropertySafe(type.block))<br>
         * <p style="color: ORANGE;">extraBlockFactory:</p> new RechiseledPillarBlock(true, Utils.copyPropertySafe(type.block))<br>
         * <p style="color: YELLOW;">The default added tag:</p>
         * - MINEABLE_WITH_PICKAXE<br>
        **/
        public static <T extends RockType> BlockSetEntries.BlockSetBuilder<T> of2PillarBlockDefault(
                GemsRealmModule module, Class<T> typeClass, T baseTypeId,
                String getBlockPath,
                String getConnectingBlockPath,
                Supplier<CreativeModeTab> tabSupplier)
        {

            return of2BlocksFactory(module, typeClass, baseTypeId,
                    getBlockPath, type -> new RechiseledPillarBlock(false, Utils.copyPropertySafe(type.block)),
                    getConnectingBlockPath, type -> new RechiseledPillarBlock(true, Utils.copyPropertySafe(type.block)),
                    tabSupplier
            );
        }

        /**
         *
         * @param getBlockPath Path of block's id
         * @param blockFactory a Custom Block Factory
         * @param getConnectingBlockPath Path of Block's id (the one with connecting)
         * @param extraBlockFactory a Custom Block Factory
         */
        private static <T extends RockType> BlockSetEntries.BlockSetBuilder<T> of2BlocksFactory(
                GemsRealmModule module, Class<T> typeClass, T baseTypeId,
                String getBlockPath, Function<T, Block> blockFactory,
                String getConnectingBlockPath, Function<T, Block> extraBlockFactory,
                Supplier<CreativeModeTab> tabSupplier)
        {
            return BlockSetEntries.of2BlockFactory(module, typeClass, baseTypeId,
                            getBlockPath, blockFactory,
                            getConnectingBlockPath, extraBlockFactory,
                            tabSupplier
                    )
                    .pickaxeTag();
        }


        //      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
        //      │                                          Utilities                                           │
        //      └──────────────────────────────────────────────────────────────────────────────────────────────┘

        public static <T extends RockType> String getChildKeyPath(String baseblockPath, T oldRockType) {
            String typeName = oldRockType.getTypeName();

            Pattern pattern = Pattern.compile("(?<prefix>(?:[a-z]+_)?[a-z]+)?_?"+typeName+"_?(?<suffix>\\w+)?");
            Matcher matcher = pattern.matcher(baseblockPath);

            if (matcher.find()) {
                String prefix = matcher.group("prefix");
                String suffix = matcher.group("suffix");

                if (prefix == null && suffix != null) return suffix;
                else if (prefix != null && suffix == null) return prefix;
                else return prefix +"_"+ suffix;
            }
            else {
                GemsRealm.LOGGER.error("RechiseledModule - Failed to get DecorativeName for {}", baseblockPath);
                return "big_tiles";
            }
        }

        protected static ResourceLocation modLoc(String mod_id, String path) {
            return ResourceLocation.fromNamespaceAndPath(mod_id, path);
        }
    }
}