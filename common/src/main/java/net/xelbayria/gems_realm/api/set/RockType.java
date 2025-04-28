package net.xelbayria.gems_realm.api.set;

import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public abstract class RockType extends BlockType{
    /**
     * Childkey Availability:
     **/

    /**
     * CrystalCraft:
     *  REDSTONE_DUST
     * crystalcraft_unlimited_java:bluestone_dust - ITEM - detected by #forge:dusts/...
     *
     *      INGOT:
     * crystalcraft_unlimited_java:magnesium_ore - BLOCK - detected by BASS_DRUM
     * crystalcraft_unlimited_java:magnesium_block - BLOCK - detected by BASS_DRUM
     * crystalcraft_unlimited_java:raw_magnesium_block - BLOCK - detected by HARP
     * crystalcraft_unlimited_java:raw_magnesium - ITEM
     * crystalcraft_unlimited_java:magnesium - ITEM (INGOT) - detected by #forge:ingots
     *
     *      GEM:
     *  crystalcraft_unlimited_java:garnet_ore - BLOCK - detected by BASS_DRUM
     *  crystalcraft_unlimited_java:garnet_block - BLOCK - detected by BASS_DRUM
     *  crystalcraft_unlimited_java:garnet - ITEM (GEM) - detected by #forge:gems
     *
     *  crystalcraft_unlimited_java:amethyst_ore - BLOCK - detected by BASS_DRUM
     *  crystalcraft_unlimited_java:amethyst_block - BLOCK - detected by BASS_DRUM
     *  crystalcraft_unlimited_java:amethyst - ITEM (GEM)
     *
     *  crystalcraft_unlimited_java:fire_opal_ore - BLOCK - detected by BASS_DRUM
     *  crystalcraft_unlimited_java:fire_opal_block - BLOCK - detected by HARP
     *  crystalcraft_unlimited_java:fire_opal - ITEM (GEM)
     *
     * BetterEnd
     *      GEM:
     *  betterend:amber_gem - ITEM (GEM) - ???
     *  betterend:raw_amber - ITEM
     *  betterend:amber_block - BLOCK - detected by HARP
     *  betterend:amber_ore - BLOCK - detected by BASS_DRUM
     *
     *      INGOT:
     *  betterend:thallasium_block - BLOCK - detected by VIBRAPHONE
     *  betterend:thallasium_ingot - ITEM
     *  betterend:thallasium_nugget - ITEM
     *
     *  betterend:terminite_ingot - ITEM
     *  betterend:terminite_block - BLOCK - detected by VIBRAPHONE
     *  betterend:terminite_nugget - ITEM
     *
     *  betterend:aeternium_block - BLOCK - detected by VIBRAPHONE
     *  betterend:aeternium_ingot - ITEM
     *
     * TFC:
     *      INGOT:
     * tfc:ore/poor_bismuth/granite
     * tfc:metal/ingot/bismuth
     * tfc:metal/block/bismuth

     *      GEM:
     * tfc:ore/amethyst - ITEM
     * tfc:gem/amethyst - ITEM - detected by #forge:gems
     *
     * Galosphere
     *      CRYSTAL:
     * galosphere:amethyst_cluster - BLOCK - detected by HARP
     * galosphere:amethyst_block - BLOCK - detected by HARP
     *
     * galosphere:allurite_cluster - BLOCK - detected by HARP
     * galosphere:allurite_block - BLOCK - detected by HARP
     *
     * Mo' Shiz
     * ms:gem/cobalt_ingot - ITEM
     * ms:resources/cobalt_block - BLOCK - detected by HARP
     * ms:gem/raw_cobalt - ITEM
     * ms:nugget/cobalt_nugget - ITEM
     *
     * ms:fence/cobalt - BLOCK
     * ms:stair/cobalt - BLOCK
     * ms:slab/cobalt - BLOCK
     * ms:vertical/cobalt - BLOCK
     *
     * ms:gem/sapphire - ITEM (GEM)
     * ms:resources/sapphire_block - BLOCK
     * ms:nugget/sapphire_shard - ITEM (RAW)
     * ms:fence/sapphirefence
     * ms:stair/sapphirestair
     * ms:slab/sapphire_slab
     * ms:vertical/sapphire
     *
     * TOOLTIP - disable it
     *         ItemStack stack = event.getItemStack();
     *         if (stack != null) {
     *             // Remove tags from the tooltip
     *             event.getTooltipElements().removeIf(element -> element instanceof TooltipComponent && element.getText().getString().contains("#"));
     *         }
     */

    public final Block block;
//    public final Block ingot;

    protected RockType(ResourceLocation id, Block block) {
        super(id);
        this.block = block;
    }

    @Override
    public String getAppendableIdWith(String prefix, String suffix) {
        String suffixed = (suffix.isEmpty()) ? "" : "_" + suffix;
        String prefixed = (prefix.isEmpty()) ? "" : prefix + "_";
        return  this.getNamespace() +"/"+ prefixed + this.getTypeName() + suffixed;
    }

    @Override
    protected void initializeChildrenBlocks() {
            this.addChild("block", this.block);
            this.addChild("raw_block", findRelatedBlock("raw", "block"));

            // Support TFC & AFC
            if (this.id.getNamespace().matches("tfc|afc")) {
                this.addChild("trapdoor", findRelatedBlock("trapdoor", ""));
                this.addChild("lamp", findRelatedBlock("lamp", ""));
                this.addChild("chain", findRelatedBlock("chain", ""));
                this.addChild("anvil", findRelatedBlock("anvil", ""));
                this.addChild("bars", findRelatedBlock("bars", ""));
            }
            else {
            }

    }

    @Override
    protected void initializeChildrenItems() {
//        this.addChild("ingot", this.findRelatedEntry("ingot", BuiltInRegistries.ITEM));
    }

    protected @Nullable Block findRelatedBlock(String prefixOrInfix, String suffix) {
        return findRelatedEntry(prefixOrInfix, suffix, BuiltInRegistries.BLOCK);
    }

    protected @Nullable Item findRelatedItem(String prefixOrInfix, String suffix) {
        return findRelatedEntry(prefixOrInfix, suffix, BuiltInRegistries.ITEM);
    }

    @Override
    protected @Nullable <V> V findRelatedEntry(String prefixOrInfix, String suffix, Registry<V> reg) {


        if (!suffix.isEmpty()) suffix = "_" + suffix;
        ResourceLocation[] targets = {
                new ResourceLocation(id.getNamespace(), id.getPath() +"_"+ prefixOrInfix + suffix),
                new ResourceLocation(id.getNamespace(), prefixOrInfix +"_"+ id.getPath() + suffix),
        };
        V found = null;
        for (var r : targets) {
            if (reg.containsKey(r)) {
                found = reg.get(r);
                break;
            }
        }
        return found;
    }

    @Override
    protected <V> V findRelatedEntry(String prefixOrInfix, Registry<V> reg) {
        return findRelatedEntry(prefixOrInfix, "", reg);
    }

    @Override
    public ItemLike mainChild() {
        return block;
    }

    public Block bricksOrStone() {
        Block bricks= this.getBlockOfThis("bricks");
        return bricks != null ? bricks : this.block;
    }

}
