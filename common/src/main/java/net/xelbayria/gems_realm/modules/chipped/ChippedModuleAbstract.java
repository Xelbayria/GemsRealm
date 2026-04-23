package net.xelbayria.gems_realm.modules.chipped;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.api.GemsRealmModule;

import static net.xelbayria.gems_realm.api.set.VanillaRockChildKeys.BLOCK;
import static net.xelbayria.gems_realm.api.set.VanillaRockChildKeys.RAW_BLOCK;
import static net.xelbayria.gems_realm.api.set.metal.VanillaMetalChildKeys.BARS;
import static net.xelbayria.gems_realm.misc.HardcodedBlockType.isKnownVanillaBlockType;

///SUPPORT: v3.0.7
public class ChippedModuleAbstract extends GemsRealmModule {

    public final ResourceLocation tab = modRes("main");

    public ChippedModuleAbstract(String modId) {
        super(modId, "ch");
    }

    protected void createWorkbenchRecipe(String identifier, BlockTypeRegistry<?> blockTypeInstance, Workbench workbench, ResourceSink sink) {
        JsonArray arrayTags = new JsonArray();

        for (BlockType blockType : blockTypeInstance) {

            if (isKnownVanillaBlockType(blockType)) continue;

            boolean isTagCreated = false;

            String path;
            if (identifier.contains("_")) {
                String prefix = identifier.split("_")[0];
                String suffix = identifier.split("_")[1];
                path = blockType.createPathWith("", prefix, suffix);
            }
            else
                path = blockType.createPathWith("", identifier);

            SimpleTagBuilder tagBuilder = SimpleTagBuilder.of(GemsRealm.res(path));

            for (var entry : this.getEntries()) {
                String name = entry.getName();

                if (name.matches(".*(_" + identifier + "|" + identifier + "_).*")) {
                    Item item = ((SimpleEntrySet<?, ?>) entry).items.get(blockType);
                    if (item != null) {
                        tagBuilder.addEntry(item);
                        isTagCreated = true;
                    }
                }
            }

            // Checking for Child of block type exist
            if (blockType.getChild(identifier) != null) {
                switch (identifier) { // Adds normal or modded blockType
                    case BLOCK -> tagBuilder.addEntry(blockType.getChild(BLOCK));
                    case RAW_BLOCK -> tagBuilder.addEntry(blockType.getChild(RAW_BLOCK));
                    case BARS -> tagBuilder.addEntry(blockType.getChild(BARS));
                }
            }

            if (isTagCreated) {
                sink.addTag(tagBuilder, Registries.BLOCK);
                sink.addTag(tagBuilder, Registries.ITEM);
                arrayTags.add(tagBuilder.getId().toString());
            }
        }
        JsonObject jo = new JsonObject();
        jo.addProperty("type", "chipped:" + workbench.getString());
        jo.add("tags", arrayTags);
        sink.addJson(GemsRealm.res(shortenedId() + "/" + blockTypeInstance.typeName() + "/" + workbench.getString() + "_" + identifier), jo, ResType.RECIPES);

    }

    public enum Workbench {
        ///For: redstone_block, iron_bars
        TINKERING_TABLE("tinkering_table"),
        ///For: raw_iron_block, raw_gold_block,
        MASON_TABLE("mason_table"),
        ///For: iron_block, gold_block, diamond_block, amethyst_block
        ALCHEMY_BENCH("alchemy_bench");

        private final String workstation;

        Workbench(String workstation) {
            this.workstation = workstation;
        }

        public String getString() {
            return workstation;
        }
    }

}