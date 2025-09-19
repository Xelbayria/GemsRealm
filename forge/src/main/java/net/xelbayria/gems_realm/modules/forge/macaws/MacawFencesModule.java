package net.xelbayria.gems_realm.modules.forge.macaws;

import com.mcwfences.kikoz.objects.StoneWiredFence;
import net.minecraft.world.level.block.Block;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.modules.macaws.MacawFencesAbstractModule;

//SUPPORT: v1.1.2
public class MacawFencesModule extends MacawFencesAbstractModule {

    public MacawFencesModule(String modId) {
        super(modId);
    }

    @Override
    public Block newStoneWiredFence(MetalType metalType) {
        return new StoneWiredFence(copyBarsSafe(metalType));
    }
}