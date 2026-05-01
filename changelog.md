<div style="text-align: center; border: 1px solid yellow; padding: 10px;">

<div style="text-align: center; margin-bottom: 10px;">

### LEGEND

</div>

<div style="text-align: left;">

* **(C)**: NEOFORGE & FABRIC
* **(FB)**: FABRIC
* **(NF)**: NEOFORGE
* **(IT)**: Included Texture: Added the ResourceLocation of the missing textures required for blocks or generating a new texture
* **(TEX)**: hand-made textures to improve the way a block looks
* **(COMPAT)**: Create an exception for a compat mod. EveryCompat won't included for the Supported Mod and the Wood Mod
* **(INCLUDED)**: The block is not generated because a Wood Mod already have the same block as the supported mod will be generated
* **(EXCLUDED)**: The block is generated BUT it shouldn't be generated for a reason
* **(UDBT)**: Undetected BlockTypes will be manually added

</div>

</div>

<br>

### CHANGES:
- **Gems Realm** (C): Major Improvement in Detection System - Simply reducing the repetitive codes
- **Create** (C): Fixed Sheets' incorrect recipes & Added the missing tag, `#forge:plates/TYPE` or `#c:plates/TYPE` to sheets - [#58](https://github.com/Xelbayria/GemsRealm/issues/58)

### FIXES:
- **Chipped** (C): 
  - the `alchemy_bench`'s recipes not being generated for MetalTypes or GemTypes - [#59](https://github.com/Xelbayria/GemsRealm/issues/59)
  - Updated the recipe generation (it was using 1.20.1) and is now working properly - [#50](https://github.com/Xelbayria/GemsRealm/issues/50)
  - the missing textures for **Crystalized Enchants**' 3 CrystalType by using (INCLUDED) for certain blocks along textures that did not get generated - [#50](https://github.com/Xelbayria/GemsRealm/issues/50)
- **Macaw's Bridges** (FB): Updated an outdated ResourceLocation for Creative Tab on FABRIC side

### ADDED:
- **More Ores More Gems** (UDBT): 31 MetalTypes, 42 GemTypes, & more_ores_more_gems:gunpowder (DustType)
- **CrystalCraft Unlimited Edition** (UDBT): 16 new MetalTypes (Ported from 1.20)
- **'Dustrial Decor** (UDBT): 2 MetalTypes (Ported from 1.20)
- **Tech Reborn** (UDBT): 22 MetalTypes & 5 GemTypes
  - Note: DustType cannot be added & CrystalType cannot be added

---

## v2.11.2

### REQUIRED:
- **Every Compat v2.11.32 or newer** - REASON: the code related to 2 configs are removed

### CHANGES:
- **Gems Realm** (C): Removed 2 configs due to a misunderstood request

---

## v2.11.1

### REQUIRED: 
- **Every Compat v2.11.31** - REASON: 2 new configs and new codes added responsible for Creative Tab stuff. 

### CHANGES: 
- **LANG** (JA_JP) - Updated by @HayaKoh-WeldyAlin 
- **Create** (C): Updated recipe generation to fix the missing recipes [#54](https://github.com/Xelbayria/GemsRealm/issues/54)
- **Gems Realm** (C): Added 2 new configs - [Every Compat#1203](https://github.com/MehVahdJukaar/WoodGood/issues/1203) 
  - `DISABLE_CYCLE_ITEM_RENDERER` - disable creative-tab from showing the iteration of every item from Gems Realm 
  - `CREATIVE_TAB_ICON` - Choose one item (can be from Gems Realm or Minecraft) to replace the icon instead of iterating every item from Gems Realm

---

## v2.11.0

## Changes:
- **Gems Realm** (C): Updated to 1.21.1
- **Rechiseled** (C): Re-enabled & Supported v1.2.0+

# Sorry for the long waiting, so have fun minecraft-ing! 