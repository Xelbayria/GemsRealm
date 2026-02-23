package net.xelbayria.gems_realm.modules.neoforge.macaws;

import com.mcwwindows.kikoz.objects.Shutter;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.world.level.block.Block;
import net.xelbayria.gems_realm.api.set.metal.MetalType;
import net.xelbayria.gems_realm.modules.macaws.MacawWindowsAbstractModule;

//SUPPORT: v2.4.0+
public class MacawWindowsModule extends MacawWindowsAbstractModule {

    public MacawWindowsModule(String modId) {
        super(modId);
    }

    @Override
    public Block newShutter(MetalType metalType) {
        return new Shutter(Utils.copyPropertySafe(metalType.block)
                .strength(1.5F, 2.0F)
                .requiresCorrectToolForDrops());
    }

//    @Override
//    public Block newCurtainRod(MetalType metalType) {
//        return null;
//    }
//
//    @Override
//    public Block newConnectedWindow(MetalType metalType) {
//        return null;
//    }
//
//    @Override
//    public Block newWindowBarred(MetalType metalType) {
//        return null;
//    }
//
//    @Override
//    public Block newWindow(MetalType metalType) {
//        return null;
//    }
}