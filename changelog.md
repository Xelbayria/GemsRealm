| **Legends**                                                                                                                       |
|-----------------------------------------------------------------------------------------------------------------------------------|
| \- **(C)**: FORGE & FABRIC                                                                                                        |
| - **(FB)**: FABRIC                                                                                                                |
| - **(NF)**: NEOFORGE                                                                                                              |
| - **(IT)**: Included Texture — added the ResourceLocation of the missing textures required for blocks or generating a new texture |
| - **(TEX)**: hand-made textures to improve the way a block looks                                                                  |
| - **(COMPAT)**: Create an exception for a compat mod. EveryCompat won't include the Supported Mod and the Wood Mod                |
| - **(INCLUDED)**: The block is not generated because a Wood Mod already has the same block as the supported mod will be generated |
| - **(EXCLUDED)**: The block is generated BUT it shouldn't be generated for a reason                                               |
| - **(UDBT)**: Undetected BlockTypes will be manually added                                                                        |
|                                                                                                                                   |

---
## v2.11.4

### CHANGES:
- **Gems Realm** (C): 
  - Major Updates to work with **EveryCompat v2.11.32 or newer** and using new build script
  - Improved a few classes
  - Improved BlockCycleItemRenderer, the Tab's icon will iterate all of 4 BlockTypes (Metal, Gem, Crystal, and Dust)  
- **Architect's Palette** (UDBT): Added 3 MetalTypes - [#72](https://github.com/Xelbayria/GemsRealm/issues/72)
- **Create Aquatic Ambitions** (UDBT): Added 1 metaltype, `prismarine_alloy` - [#71](https://github.com/Xelbayria/GemsRealm/issues/71)
- **Lapidarist** (NF): Updated for 1.21.1 with new mod_id: "lapidarist" instead of old mod_id: "lapidary" - [#75](https://github.com/Xelbayria/GemsRealm/issues/75) 

### FIXES:
- **Spelunkery** & **Etcetera** (UDBT): Added `spelunkery:bismuth_nugget` as children to `etcetera:bismuth` - [#74](https://github.com/Xelbayria/GemsRealm/issues/74)

### NEW:
- **Create Deco** (NF)

### CREATE (NF)
- **Added** 6 new blocks - [#11](https://github.com/Xelbayria/GemsRealm/issues/11)
  - encased_shaft
  - encased_cogwheel
  - encased_large_cogwheel
  - funnel
  - belt_funnel
  - valve_handle
- **CHANGES**: All of Gems Realm's blocks for valve_handle, casing, table_cloth, funnel will show "W" when you hover the item so you can play StoryBoard
- **FIXES**: Finally fixed the `SHINGLES` & `TILES`' issue with top connected texture where they don't show "X"
- **NOTES:** 
  - `CASING` can be not be applied to belt block because there is limitation in the code that prevent compatibility with **Gems Realm** - I've requested for a small changes in the **Create**'s Source Code. I do not know when the changes will be applied. Too bad Create's github do not have suggestion issue I could create. Discord suggestion tend to get ignored or forgetten.
  - `TUNNEL` cannot be added for same reason as casing not being applied to belt block
  - valve_handle doesn't have stress capacity like copper_valve_handle - I'm currently talking wtih Create's DEV to understand how it's implemented

Have fun Minecraft-ing! 

---

## v2.11.3

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