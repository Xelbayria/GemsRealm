plugins {
    id("com.possible-triangle.fabric")
}

fabric {
    dependOn(project(":common"))
    accessWidener(project(":common"))
}

val modId: String = property("mod_id").toString()
val modVersion: String = property("mod_version").toString()
tasks.remapJar {
    archiveBaseName.set(modId)
    archiveVersion.set(modVersion)
    archiveClassifier.set("fabric")
}
tasks.remapSourcesJar {
    from(sourceSets.main.get().allSource)
    archiveBaseName.set(modId)
    archiveVersion.set(modVersion)
    archiveClassifier.set("fabric-sources")
}

val path = System.getenv("REPOS21_1").toString()
dependencies {

//!! MOONLIGHT LIB (REQUIRED)  -------------------------------------------------------------------------------------- \\
    //- LOCAL
    if (findProperty("enable_moonlight_test").toString().toBoolean()) {
        modApi(files(path + "\\Moonlight\\fabric\\build\\libs\\moonlight-${property("moonlight_testVersion")}-fabric.jar"))
    }
    //+ MAVEN
    else {
        if (findProperty("maven_backup").toString().toBoolean()) modApi("maven.modrinth:moonlight:${property("moonlight_version")}-fabric")
        else modApi("net.mehvahdjukaar:moonlight-fabric:${property("moonlight_version")}") { isTransitive = false }
    }

//!! EVERY COMPAT (REQUIRED) ---------------------------------------------------------------------------------------- \\

    //- LOCAL
    if (findProperty("enable_everycomp_test").toString().toBoolean()) {
        implementation(files(path + "\\WoodGood\\fabric\\build\\libs\\everycomp-${property("everycomp_testVersion")}-fabric.jar"))
    }
    //+ MAVEN
    else {
        if (findProperty("maven_backup").toString().toBoolean()) modApi("maven.modrinth:every-compat:${property("everycomp_version")}-fabric")
        else modApi("net.mehvahdjukaar:everycomp-fabric:${property("everycomp_version")}:fabric") { isTransitive = false }
    }

//!! TOOLS  --------------------------------------------------------------------------------------------------------- \\
    modRuntimeOnly("com.blamejared.crafttweaker:CraftTweaker-fabric-${property("minecraft_version")}:${property("crafttweaker_version")}") // Faux-Custom-Entity-Data
//    modRuntimeOnly("dev.emi:emi-fabric:${property("emi_version")}+${property("minecraft_version")}")
    modRuntimeOnly("maven.modrinth:emi:${property("emi_version")}+${property("minecraft_version")}+fabric")
    modRuntimeOnly("curse.maven:jei-238222:5846878")
    modRuntimeOnly("curse.maven:jade-324717:5884237") // v15.8.3
//    modRuntimeOnly("curse.maven:worldedit-225608:5830452") // required loom 1.7.410

//!! ================================================= DEPENDENCIES ================================================= \\
    //- ONLY FOR TESTING - can be commented out or enabled
    modRuntimeOnly("curse.maven:text-placeholder-api-1037459:6131327") // +Mod-Menu
//    modRuntimeOnly("curse.maven:yungs-api-421850:5769972") // v4.0.6 | Better-Archeology //!!-1.20.1
//    modRuntimeOnly("curse.maven:puzzles-lib-495476:5904751") // v8.1.25 | Stoneworks //!!-1.20.1
//    modRuntimeOnly("curse.maven:forge-config-api-port-fabric-547434:5902421") // v8.1.25 | Stoneworks //!!-1.20.1
//    modRuntimeOnly("curse.maven:fusion-connected-textures-854949:5129312") // v1.1.1 | Rechiseled //!!-1.20.1
//    modRuntimeOnly("curse.maven:supermartijn642s-config-lib-438332:4785838") // v1.1.8a | Rechiseled //!!-1.20.1
//    modRuntimeOnly("curse.maven:monolib-968432:6544054") // v2.1.0 | More-Beautiful-Torches //!!-1.20.1
//    modRuntimeOnly("curse.maven:bclib-remastered-1501730:7961782") // v30.3.1 | BetterEnd
//    modRuntimeOnly("curse.maven:worldweaver-remastered-1501729:7961777") // v30.3.1 | BetterEnd

    //- OTHER MAVENS
//    modRuntimeOnly("com.terraformersmc.terraform-api:terraform-wood-api-v1:7.0.3") // Blockus //!!-1.20.1
//    modRuntimeOnly("fuzs.forgeconfigapiport:forgeconfigapiport-fabric:8.0.2") // Chipped, Create //!!-1.20.1
//    modRuntimeOnly("io.github.tropheusj:milk-lib:1.3.61") // Create //!!-1.20.1

    //+ REQUIRED - The modules access libaries from below - Only in FABRIC

    //+ OTHER MAVENS

    // Special dumb stuff required by TerraBlender
    modImplementation("com.terraformersmc:modmenu:11.0.4")
//    modImplementation("dev.onyxstudios.cardinal-components-api:cardinal-components-base:6.1.1")
//    modImplementation("dev.onyxstudios.cardinal-components-api:cardinal-components-entity:6.1.1")
    // so dumber stuff requird by GlitchCore
//    modImplementation("net.jodah:typetools:0.6.3")
//    modImplementation("com.electronwill.night-config:core:3.8.1")
//    modImplementation("com.electronwill.night-config:toml:3.8.1")

    //+ MIRRORED FROM COMMON - Required because dependOn(common) compiles common sources with neoforge classpath
    modCompileOnly("curse.maven:supermartijn642s-core-lib-454372:8623607") // v1.1.24 | Rechiseled

//!! =================================================== IMPORTS ==================================================== \\
    //- ONLY FOR TESTING - can be commented out or enabled
//    modRuntimeOnly("curse.maven:better-archeology-835687:5704094") // supermartijn642s' Config-Lib, Yung's API //!!-1.20.1
//    modRuntimeOnly("curse.maven:blockus-312289:5896309") // Terraformersmc's terraform-wood-api //!!-1.20.1
//    modRuntimeOnly("curse.maven:building-but-better-989479:5382599") //!!-1.20.1
//    modRuntimeOnly("curse.maven:twigs-496913:4603805") //!!-1.20.1
//    modRuntimeOnly("curse.maven:stoneworks-852663:4611948") // puzzles-lib, forge-config-api-port //!!-1.20.1

    //- OTHER MAVENs
//    modRuntimeOnly("earth.terrarium.chipped:chipped-fabric-${property("minecraft_version")}:4.0.2") //INCLUDED: Athena, Resourceful-Lib, REQUIRED: Bytecodecs, ConnectedTexturesMod (CTM), Fabric-API

    //+ REQUIRED - The modules access libaries from below - ONLY IN FABRIC
    // MACAW's
    modCompileOnly("curse.maven:macaws-bridges-351725:7628029") //v3.1.2
    modCompileOnly("curse.maven:macaws-windows-363569:6732785") //v2.4.0 //!!-1.20.1
    modCompileOnly("curse.maven:macaws-fences-and-walls-453925:5442191") //v1.1.2 //!!-1.20.1

    // GENERALS
    modCompileOnly("curse.maven:catwalks-llc-916800:4769531") //!! NOT STARTED
    modCompileOnly("curse.maven:create-deco-fabric-739872:5293979") //!! NOT UPDATED for v6.0+

    // OTHER MAVENS
    modCompileOnly("com.simibubi.create:create-fabric:${property("create_fabric_version")}") { isTransitive = false }  // Registrate, Flywheel, Ponder, Forge-Config-Api-Port, Milk-Lib, Porting-Lib

    //+ MIRRORED FROM COMMON - Required because dependOn(common) compiles common sources with neoforge classpath
    modCompileOnly("curse.maven:rechiseled-558998:8875842") // Fusion, supermartijn642s-[ Config-Lib, Core-Lib ]


//!! ================================================= FOR TESTING ================================================== \\

    // GEMTYPE, CRYSTALTYPE
    // spectrum

    // METALTYPE, CRYSTALTYPE (has budding)
//    modRuntimeOnly("curse.maven:betterend-remastered-1474279:7961785") // BCLib-Remastered, Worldweaver-Remastered -Mod Distr not allowed
    // clutter
    // glowroot caves
    //

    // OTHERS
//    modRuntimeOnly("curse.maven:mwtis-stone-expansion-907720:4767668")

}