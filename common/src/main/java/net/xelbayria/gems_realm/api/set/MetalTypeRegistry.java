package net.xelbayria.gems_realm.api.set;

import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.*;

import static net.xelbayria.gems_realm.misc.HardcodedBlockType.BLACKLISTED_MODS;


@SuppressWarnings("unused")
public class MetalTypeRegistry extends BlockTypeRegistry<MetalType> {

    public static final MetalTypeRegistry INSTANCE = new MetalTypeRegistry();

    public MetalTypeRegistry() {
        super(MetalType.class, "metal_type");

        this.addFinder(MetalType.Finder.vanilla("iron"));
        this.addFinder(MetalType.Finder.vanilla("gold"));
        this.addFinder(MetalType.Finder.vanilla("copper"));

    }

    public static MetalType getIronType() {
        return getValue("iron");
    }

    public static Collection<MetalType> getTypes() {
        return INSTANCE.getValues();
    }

    public static MetalType getValue(String metalTypeId) {
        return INSTANCE.get(new ResourceLocation(metalTypeId));
    }

    @Override
    public MetalType getDefaultType() {
        return getIronType();
    }

    @Override
    public Optional<MetalType> detectTypeFromBlock(Block baseblock, ResourceLocation baseRes) {
        String ingotPath = baseRes.getPath();

        // Support TerraFirmaCraft (TFC) & ArborFirmaCraft (AFC)
        if (baseRes.getNamespace().matches("tfc|afc")) {
            if (ingotPath.matches("metal/ingot/\\w+")) {
                int index = ingotPath.lastIndexOf("/");
                String metalName = ingotPath.substring(index + 1); // Get bismuth from tfc:metal/ingot/bismuth
                String blockPath = baseRes.getPath().replace("ingot", "block");

                Optional<Item> ingot = BuiltInRegistries.ITEM.getOptional(
                        new ResourceLocation(baseRes.getNamespace(), ingotPath)
                );
                Optional<Block> block = BuiltInRegistries.BLOCK.getOptional(
                        new ResourceLocation(baseRes.getNamespace(), blockPath)
                );

                if (ingot.isPresent() && block.isPresent()) {
                    return Optional.of(new MetalType(baseRes.withPath(metalName), ingot.get(), block.get()));
                }
                else if (ingot.isPresent()) {
                    return Optional.of(new MetalType(baseRes.withPath(metalName), ingot.get(), null));
                }
            }
        }

        if (!BLACKLISTED_MODS.contains(baseRes.getNamespace())) {
            if (ingotPath.matches("\\w+_ingot")) {
                String metalName = ingotPath.replace("_ingot", "");
                Optional<Item> ingot = BuiltInRegistries.ITEM.getOptional(
                        new ResourceLocation(baseRes.getNamespace(), ingotPath)
                );
                Optional<Block> block = BuiltInRegistries.BLOCK.getOptional(
                        new ResourceLocation(baseRes.getNamespace(), ingotPath)
                );

                if (ingot.isPresent() && block.isPresent()) {
                    return Optional.of(new MetalType(baseRes.withPath(metalName), ingot.get(), block.get()));
                }
                else if (ingot.isPresent()) {
                    return Optional.of(new MetalType(baseRes.withPath(metalName), ingot.get(), null));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void addTypeTranslations(AfterLanguageLoadEvent language) {
        this.getValues().forEach((stoneType) -> {
            if (language.isDefault()) language.addEntry(stoneType.getTranslationKey(), stoneType.getReadableName());
        });
    }

    @Override
    public int priority() {
        return 110;
    }
}
