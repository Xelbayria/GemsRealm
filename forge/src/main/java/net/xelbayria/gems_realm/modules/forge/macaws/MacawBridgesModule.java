package net.xelbayria.gems_realm.modules.forge.macaws;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.xelbayria.gems_realm.api.GemsrealmEntrySet;
import net.xelbayria.gems_realm.api.GemsrealmModule;
import net.xelbayria.gems_realm.api.set.MetalType;
import net.xelbayria.gems_realm.api.set.MetalTypeRegistry;


//SUPPORT: v
public class MacawBridgesModule extends GemsrealmModule {

    public final SimpleEntrySet<MetalType, Block> blank_1;

    public MacawBridgesModule(String modId) {
        super(modId, "");
        ResourceLocation tab = modRes("");

        blank_1 = GemsrealmEntrySet.of(MetalType.class, "stone_blanks",
                        getModBlock("stone_blanks"), MetalTypeRegistry::getIronType,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.block))
                )
                .addTexture(modRes("block/stone_blanks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(blank_1);

    }
}