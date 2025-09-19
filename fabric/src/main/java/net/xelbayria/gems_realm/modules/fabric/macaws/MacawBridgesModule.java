package net.xelbayria.gems_realm.modules.fabric.macaws;

import net.kikoz.mcwbridges.objects.Bridge_Stairs;
import net.kikoz.mcwbridges.objects.Bridge_Support;
import net.kikoz.mcwbridges.objects.Iron_Bridge;
import net.minecraft.world.level.block.Block;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.modules.macaws.MacawBridgesAbstractModule;

//SUPPORT: v3.1.0+
public class MacawBridgesModule extends MacawBridgesAbstractModule {


    public MacawBridgesModule(String modId) {
        super(modId);
    }

    @Override
    public Block newIron_Bridge(MetalType metalType) {
        return new Iron_Bridge(standardMetalProperties());
    }

    @Override
    public Block newBridge_Stairs(MetalType metalType) {
        return new Bridge_Stairs(standardMetalProperties());
    }

    @Override
    public Block newBridge_Support(MetalType metalType) {
        return new Bridge_Support(standardMetalProperties());
    }
}