package net.xelbayria.gems_realm.modules.macaws;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.xelbayria.gems_realm.GemsRealm;
import net.xelbayria.gems_realm.api.GemsRealmEntrySet;
import net.xelbayria.gems_realm.api.GemsRealmModule;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.api.set.metal.VanillaMetalTypes;


//SUPPORT: v3.1.0+
public abstract class MacawBridgesAbstractModule extends GemsRealmModule {

    public final SimpleEntrySet<MetalType, Block> bridge;
    public final SimpleEntrySet<MetalType, Block> bridge_stair;
    public final SimpleEntrySet<MetalType, Block> bridge_pier;

    public MacawBridgesAbstractModule(String modId) {
        super(modId, "mcb");
        ResourceLocation tab = modRes("mcwbridges");

        bridge = GemsRealmEntrySet.of(MetalType.class, "bridge",
                        getModBlock("iron_bridge"), () -> VanillaMetalTypes.IRON,
                        this::newIron_Bridge
                )
//                .requiresChildren("ingot", "nugget") //REASON: recipes
                //TEXTURES: bridge_2
                .addModelTransform(m -> m.addModifier(
                        (s, blockId, metalType) ->
                                s.replace("mcwbridges:block/bridge_1",
                                                metalType.createFullIdWith(GemsRealm.MOD_ID, "block", shortenedId(), "", "bridge_1"))
                                        .replace("mcwbridges:block/bridge_2",
                                                metalType.createFullIdWith(GemsRealm.MOD_ID, "block", shortenedId(), "", "bridge_2"))
                                        .replace("mcwbridges:block/bridge_3",
                                                metalType.createFullIdWith(GemsRealm.MOD_ID, "block", shortenedId(), "", "bridge_3"))
                ))
                .addTextureC(modRes("block/bridge_1"), "block/iron_bridge_1")
                .addTextureC(modRes("block/bridge_2"), "block/iron_bridge_2")
                .addTextureC(modRes("block/bridge_3"), "block/iron_bridge_3")
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.COMBINATION_STEP_SOUND_BLOCKS, Registries.BLOCK)
                .addTag(modRes("metal_bridges"), Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("iron_bridge_middle"))
                .build();
        this.addEntry(bridge);

        bridge_stair = GemsRealmEntrySet.of(MetalType.class, "bridge_stair",
                        getModBlock("iron_bridge_stair"), () -> VanillaMetalTypes.IRON,
                        this::newBridge_Stairs
                )
                .requiresFromMap(bridge.blocks) //REASON: recipes
                //TEXTURES: iron_block, bridge_1, bridge_2
                .addModelTransform(m -> m.addModifier(
                        (s, blockId, metalType) ->
                                s.replace("mcwbridges:block/bridge_1",
                                                metalType.createFullIdWith(GemsRealm.MOD_ID, "block", shortenedId(), "", "bridge_1"))
                                        .replace("mcwbridges:block/bridge_2",
                                                metalType.createFullIdWith(GemsRealm.MOD_ID, "block", shortenedId(), "", "bridge_2"))
                ))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("iron_bridge_stair_recycle"))
                .build();
        this.addEntry(bridge_stair);

        bridge_pier = GemsRealmEntrySet.of(MetalType.class, "bridge_pier",
                        getModBlock("iron_bridge_pier"), () -> VanillaMetalTypes.IRON,
                        this::newBridge_Support
                )
//                .requiresChildren("bars") //REASON: recipes
                //TEXTURES: iron_block, bridge_1, bridge_2, bridge_3
                .addModelTransform(m -> m.addModifier(
                        (s, blockId, metalType) ->
                                s.replace("mcwbridges:block/bridge_1",
                                                metalType.createFullIdWith(GemsRealm.MOD_ID, "block", shortenedId(), "", "bridge_1"))
                                .replace("mcwbridges:block/bridge_2",
                                                metalType.createFullIdWith(GemsRealm.MOD_ID, "block", shortenedId(), "", "bridge_2"))
                                .replace("mcwbridges:block/bridge_3",
                                                metalType.createFullIdWith(GemsRealm.MOD_ID, "block", shortenedId(), "", "bridge_3"))
                ))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(bridge_pier);


    }

    public BlockBehaviour.Properties standardMetalProperties() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.METAL)
                .sound(SoundType.METAL)
                .strength(3.0F, 5.0F)
                .requiresCorrectToolForDrops();
    }

    public abstract Block newIron_Bridge(MetalType metalType);

    public abstract Block newBridge_Stairs(MetalType metalType);

    public abstract Block newBridge_Support(MetalType metalType);
}