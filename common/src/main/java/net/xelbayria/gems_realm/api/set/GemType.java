package net.xelbayria.gems_realm.api.set;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.xelbayria.gems_realm.GemsRealm;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.ApiStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class GemType extends RockType {

    public final Block mud;

    protected GemType(ResourceLocation id, Block mud) {
        super(id, mud);
        this.mud = mud;
    }

    @Override
    public String getTranslationKey() {
        return "mud_type." + this.getNamespace() + "." + this.getTypeName();
    }

    @Override
    protected void initializeChildrenBlocks() {
        super.initializeChildrenBlocks();
        this.addChild("packed", this.findRelatedEntry("packed", BuiltInRegistries.BLOCK));
    }

    @Override
    public ItemLike mainChild() {
        return this.mud;
    }

    public static class Finder implements SetFinder<GemType> {

        private final Map<String, ResourceLocation> childNames = new HashMap<>();
        private final Supplier<Block> mudFinder;
        private final ResourceLocation id;

        public Finder(ResourceLocation id, Supplier<Block> mud) {
            this.id = id;
            this.mudFinder = mud;
        }

        public static Finder vanilla(String nameMud){
            return simple("minecraft", nameMud, nameMud);
        }

        public static Finder simple(String modId, String nameMudType, String nameMud) {
            return simple(new ResourceLocation(modId, nameMudType), new ResourceLocation(modId, nameMud));
        }

        public static Finder simple(ResourceLocation nameMudTYpe, ResourceLocation nameMud) {
            return new Finder(nameMudTYpe,
                    () -> BuiltInRegistries.BLOCK.get(nameMud));
        }

        public void addChild(String childType, String childName) {
            addChild(childType, new ResourceLocation(id.getNamespace(), childName));
        }

        public void addChild(String childType, ResourceLocation childName) {
            this.childNames.put(childType, childName);
        }

        @Override
        @ApiStatus.Internal
        public Optional<GemType> get() {
            if (PlatHelper.isModLoaded(id.getNamespace())) {
                try {
                    Block mud = mudFinder.get();
                    var d = BuiltInRegistries.BLOCK.get(BuiltInRegistries.BLOCK.getDefaultKey());
                    if (mud != d && mud != null) {
                        var newMud = new GemType(id, mud);
                        childNames.forEach((key, value) ->
                                newMud.addChild(key, BuiltInRegistries.BLOCK.get(value)));
                        return Optional.of(newMud);
                    }
                } catch (Exception ignored) {
                }
                GemsRealm.LOGGER.warn("Failed to find custom mud type {}", id);
            }
            return Optional.empty();
        }
    }

}
