package net.xelbayria.gems_realm.modules.forge.macaws;

import com.mcwfences.kikoz.objects.StoneWiredFence;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.xelbayria.gems_realm.api.GemsRealmEntrySet;
import net.xelbayria.gems_realm.api.GemsRealmModule;
import net.xelbayria.gems_realm.api.set.MetalType;
import net.xelbayria.gems_realm.api.set.MetalTypeRegistry;

import java.util.Objects;

//SUPPORT: v1.1.2
public class MacawFencesModule extends GemsRealmModule {

    public final SimpleEntrySet<MetalType, Block> cheval_de_frise;

    public MacawFencesModule(String modId) {
        super(modId, "mcf");
        ResourceLocation tab = modRes("fenceitemgroup");

        cheval_de_frise = GemsRealmEntrySet.of(MetalType.class, "cheval_de_frise",
                        getModBlock("iron_cheval_de_frise"), MetalTypeRegistry::getIronType,
                        metalType -> new StoneWiredFence(copyBarsSafe(metalType))
                )
                .requiresChildren("ingot") //REASON: recipes
                //RECIPES: minecraft:nugget
                //TEXTURES: block (original: anvil)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .addTag(ItemTags.FENCES, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(cheval_de_frise);

    }

    public BlockBehaviour.Properties copyBarsSafe(MetalType metalType) {
        Block bars = metalType.getBlockOfThis("bars");
        if (Objects.nonNull(bars)) return Utils.copyPropertySafe(bars);
        return Utils.copyPropertySafe(Blocks.IRON_BARS);
    }
}