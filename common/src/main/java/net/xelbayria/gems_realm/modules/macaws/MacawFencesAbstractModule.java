package net.xelbayria.gems_realm.modules.macaws;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
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
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.api.set.metal.VanillaMetalTypes;

import java.util.Objects;

import static net.xelbayria.gems_realm.api.set.metal.VanillaMetalChildKeys.INGOT;

//SUPPORT: v1.1.2
public abstract class MacawFencesAbstractModule extends GemsRealmModule {

    public final SimpleEntrySet<MetalType, Block> cheval_de_frise;

    public MacawFencesAbstractModule(String modId) {
        super(modId, "mcf");
        ResourceLocation tab = (PlatHelper.getPlatform().isFabric())
                ? modRes("fencesgroup")
                : modRes("fenceitemgroup");

        cheval_de_frise = GemsRealmEntrySet.of(MetalType.class, "cheval_de_frise",
                        getModBlock("iron_cheval_de_frise"), () -> VanillaMetalTypes.IRON,
                        this::newStoneWiredFence
                )
                .requiresChildren(INGOT) //REASON: recipes
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

    public abstract Block newStoneWiredFence(MetalType metalType);
}