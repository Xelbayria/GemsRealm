package net.xelbayria.gems_realm.modules.chipped;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.xelbayria.gems_realm.api.GemsRealmModule;
import net.xelbayria.gems_realm.api.set.VanillaRockChildKeys;
import net.xelbayria.gems_realm.api.set.crystal.CrystalType;
import net.xelbayria.gems_realm.api.set.dust.DustType;
import net.xelbayria.gems_realm.api.set.gem.GemType;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.api.set.metal.VanillaMetalChildKeys;
import net.xelbayria.gems_realm.misc.HardcodedBlockType;

//SUPPORT: v3.0.7
public class ChippedModuleAbstract extends GemsRealmModule {

    public final ResourceLocation tab = modRes("main");

    public ChippedModuleAbstract(String modId) {
        super(modId, "ch");
    }

    protected void createWorkbenchRecipe(ResourceSink sink, String identifier, BlockTypeRegistry<?> blockTypeInstance, Workbench workbench) {
        JsonArray arrayTags = new JsonArray();

        for (var blockType : blockTypeInstance) {
            if (blockType instanceof CrystalType crystalType && HardcodedBlockType.isKnownVanillaCrystal(crystalType)) {
                continue;
            }
            else if (blockType instanceof DustType dustType && HardcodedBlockType.isKnownVanillaDust(dustType)) {
                continue;
            }
            else if (blockType instanceof GemType gemType && HardcodedBlockType.isKnownVanillaGem(gemType)) {
                continue;
            }
            else if (blockType instanceof MetalType metalType && HardcodedBlockType.isKnownVanillaMetal(metalType)) {
                continue;
            }

            boolean isTagCreated = false;

            String path;
            if (identifier.contains("_")) {
                String prefix = identifier.split("_")[0];
                String suffix = identifier.split("_")[1];
                path = blockType.createPathWith("", prefix, suffix);
            }
            else
                path = blockType.createPathWith("", identifier);

            SimpleTagBuilder tagBuilder = SimpleTagBuilder.of(EveryCompat.res(path));

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
                    case VanillaRockChildKeys.BLOCK -> tagBuilder.addEntry(blockType.getChild(VanillaRockChildKeys.BLOCK));
                    case VanillaRockChildKeys.RAW_BLOCK -> tagBuilder.addEntry(blockType.getChild(VanillaRockChildKeys.RAW_BLOCK));
                    case VanillaMetalChildKeys.BARS -> tagBuilder.addEntry(blockType.getChild(VanillaMetalChildKeys.BARS));
                }
            }

            if (isTagCreated) {
                sink.addTag(tagBuilder, Registries.BLOCK);
                sink.addTag(tagBuilder, Registries.ITEM);
                arrayTags.add(tagBuilder.getId().toString());
            }
        }
        JsonObject jo = new JsonObject();
        jo.addProperty("type", "chipped:" + workbench.getWorkstation());
        jo.add("tags", arrayTags);
        sink.addJson(EveryCompat.res(shortenedId() + "/" + workbench.getWorkstation() + "_" + identifier), jo, ResType.RECIPES);

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

        public String getWorkstation() {
            return workstation;
        }
    }

}