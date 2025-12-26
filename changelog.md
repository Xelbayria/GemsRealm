<div style="text-align: center; border: 1px solid yellow; padding: 10px;">

<div style="text-align: center; margin-bottom: 10px;">

### LEGEND

</div>

<div style="text-align: left;">

* **(C)**: FORGE & FABRIC
* **(FB)**: FABRIC
* **(FG)**: FORGE
* **(IT)**: Included Texture: Added the ResourceLocation of the missing textures required for blocks or generating a new texture
* **(TEX)**: hand-made textures to improve the way a block looks
* **(COMPAT)**: Create an exception for a compat mod. EveryCompat won't included for the Supported Mod and the Wood Mod
* **(INCLUDED)**: The block is not generated because a Wood Mod already have the same block as the supported mod will be generated
* **(EXCLUDED)**: The block is generated BUT it shouldn't be generated for a reason
* **(UDBT)**: Undetected BlockTypes will be manually added

</div>

</div>

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