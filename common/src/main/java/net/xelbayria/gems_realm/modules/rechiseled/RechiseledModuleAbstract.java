package net.xelbayria.gems_realm.modules.rechiseled;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.api.EntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.xelbayria.gems_realm.api.GemsRealmEntrySet;
import net.xelbayria.gems_realm.api.GemsRealmModule;

import java.util.Objects;

import static net.xelbayria.gems_realm.api.set.VanillaRockChildKeys.*;
import static net.xelbayria.gems_realm.misc.HardcodedBlockType.isKnownVanillaBlockType;

//SUPPORT: v1.2.4
public abstract class RechiseledModuleAbstract extends GemsRealmModule {

    public final ResourceLocation tab = modRes("rechiseled");

    public RechiseledModuleAbstract(String modId) {
        super(modId, "rcd");
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
}