| **Legends**                                                                                                                       |
|-----------------------------------------------------------------------------------------------------------------------------------|
| \- **(C)**: FORGE & FABRIC                                                                                                        |
| - **(FB)**: FABRIC                                                                                                                |
| - **(FG)**: FORGE                                                                                                                 |
| - **(IT)**: Included Texture — added the ResourceLocation of the missing textures required for blocks or generating a new texture |
| - **(TEX)**: hand-made textures to improve the way a block looks                                                                  |
| - **(COMPAT)**: Create an exception for a compat mod. EveryCompat won't include the Supported Mod and the Wood Mod                |
| - **(INCLUDED)**: The block is not generated because a Wood Mod already has the same block as the supported mod will be generated |
| - **(EXCLUDED)**: The block is generated BUT it shouldn't be generated for a reason                                               |
| - **(UDBT)**: Undetected BlockTypes will be manually added                                                                        |
|                                                                                                                                   |

---

## v2.9.9

### CHANGES:
- **Gems Realm** (C): Major Improvement in Detection System - Simply reducing the repetitive codes
- **Create** (C): 
  - Corrected the namespace for blocks/items' tag to use `c` for "fabric", `forge` for "forge"
  - Fixed the missing recipes for CASINGS & others - [#62](https://github.com/Xelbayria/GemsRealm/issues/62)

### ADDED:
- **More Ores More Gems** (UDBT): 31 MetalTypes, 42 GemTypes, & more_ores_more_gems:gunpowder (DustType) (backorted from 1.21)
- **Tech Reborn** (UDBT): 22 MetalTypes & 5 GemTypes (backported from 1.21)
  - Note: DustType cannot be added & CrystalType cannot be added

---

## v2.9.8

### CHANGES:
- **LANG** (JA_JP) - Updated by @HayaKoh-WeldyAlin
- **Rechiseled** (C): Updated the module to support v1.2.4+ & Re-enabled - [#45](https://github.com/Xelbayria/GemsRealm/issues/45)
- **Create** (C): Fixed Sheets' incorrect recipes & Added the missing tag, `#forge:plates/TYPE` or `#c:plates/TYPE` to sheets - [#58](https://github.com/Xelbayria/GemsRealm/issues/58)

### FIXES:
- **Chipped** (C): Fixed the `alchemy_bench`'s recipes not being generated for MetalTypes or GemTypes  - [#59](https://github.com/Xelbayria/GemsRealm/issues/59)

### ADDED:  
- **More Ores More Gems** (UDBT): 27 MetalTypes & 3 GemTypes
- **CrystalCraft Unlimited Edition** (UDBT): 16 new MetalTypes
- **'Dustrial Decor** (UDBT): 2 MetalTypes

---

## v2.9.7

### UPDATED: 
- **Gems Realm** (C): 
  - Fixed some undetected blocktypes with **CrystalCraft Unlimited Edition**
  - Updated to use **Every Compat v2.9.16**
- **Minecraft** (C): Added 2 tags to Nuggets - [#44](https://github.com/Xelbayria/GemsRealm/issues/44)

---

## v2.9.6

### UPDATED:
- **Gems Realm** (C): Improved logging
- **Rechiseled** (C): Disabled for now because it caused the crash. It will take some times to fix the problem.

---

## v2.9.5

### UPDATED: 
- **Gems Realm** (C): Fixed the crash with **Every Compat v2.9.15** - There is a changed code in **Every Compat** - [#1161](https://github.com/MehVahdJukaar/WoodGood/issues/1161)

---

## v2.9.4

### UPDATED: 
- **Atlantis** (UDBT):
  - Added aquamarine to GemType
  - Added aquatic_power to DustType

### ADDED:
- **Chipped** (C)
- **Rechiseled** (C)

---

## v2.9.3

### REQUIRED:
- **Every Compat** (C): v2.9.9 or newer version

### UPDATED: 
- **Gems Realm** (C): Blacklisted netherite_diamond & netherite_emerald from **Advanced Netherite** blc they are not MetalTypes
- **Create** (C): Fixed the recipe generation not accounting for ingots not having their own tags like `#forge:ingots/???`

### ADDED:
- **Create** (FB)

### DEV:
- **Every Compat** (C):
    - Utility Classes have been moved to net.mehvahdjukaar.every_compat.misc
    - `TextureUtility` are renamed to `UtilityTexture`
    - `TagUtility` are renamed to `UtilityTag`
    - `RecipeUtility` are renamed to `UtilityRecipe`
    - `Utility` are renamed to `UtilityMisc`

---

## v2.9.2

### UPDATED: 
- **Macaw's Mods** (C): Updated to have one module for both FORGE & FABRIC in COMMON - making the maintenance easier

### BLOCKTYPES:
- **The Outer End** (UDBT): Added 3 CrystalTypes: rose_crystal, mint_crystal, cobalt_crystal - [#25](https://github.com/Xelbayria/GemsRealm/issues/25)
- **GregTech CEU Modern** (F): 
  - Blacklisted 4 MetalTypes: magnetic_steel, magnetic_samarium, magnetic_neodymium, magnetic_iron
  - Blacklisted all of DustTypes for now because it has no textures that **GemsRealm** can use for (IT)

---

## v2.9.1

### UPDATED: 
- **Create** (C): Updated the recipes to have correct ingredients
- **Gems Realm** (C): 
  - Added nugget to MetalType's children to fix an incorrect recipe from **Create** - [#32](https://github.com/Xelbayria/GemsRealm/issues/32)
  - Added nugget from 2 mods to minecraft:netherite

### NOTE: 
- **Moonlight Lib v2.16.6+** (C): Fixed the stonecutter recipes' output with a count of 1 - [#30](https://github.com/Xelbayria/GemsRealm/issues/30)
- **Every Compat v2.9.2+** (C): Fixed missing textures with Macaw's Bridge - [#31](https://github.com/Xelbayria/GemsRealm/issues/31)

---

## v2.9.0

### UPDATED:
- **Gems Realm** (COMMON):
    - CHANGED CODES to work properly with Every Compat v2.9.0