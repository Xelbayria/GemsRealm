plugins {
    id("com.possible-triangle.neoforge")
}

neoforge {
    dependOn(project(":common"))
    accessWidener(project(":common"))
}

mod {
    additional.add("neoforge_version_range")
}

val modId: String = property("mod_id").toString()
val modVersion: String = property("mod_version").toString()
tasks.named<Jar>("jar") {
    archiveBaseName.set(modId)
    archiveVersion.set(modVersion)
    archiveClassifier.set("neoforge")
}
tasks.named<Jar>("sourcesJar") {
    from(sourceSets.main.get().allSource)
    archiveBaseName.set(modId)
    archiveVersion.set(modVersion)
    archiveClassifier.set("neoforge-sources")
}

val path = System.getenv("REPOS21_1").toString()
dependencies {

//!! MOONLIGHT LIB (REQUIRED)  -------------------------------------------------------------------------------------- \\
    //- LOCAL
    if (findProperty("enable_moonlight_test").toString().toBoolean()) {
        modApi(files(path + "\\Moonlight\\neoforge\\build\\libs\\moonlight-${property("moonlight_testVersion")}-neoforge.jar"))
    }
    //+ MAVEN
    else {
        if (findProperty("maven_backup").toString().toBoolean()) modApi("maven.modrinth:moonlight:${property("moonlight_version")}-neoforge")
        else modApi("net.mehvahdjukaar:moonlight-neoforge:${property("moonlight_version")}") { isTransitive = false }
    }
    accessTransformers("net.mehvahdjukaar:moonlight-neoforge:${property("moonlight_version")}")
//    implementation("net.mehvahdjukaar:codecui-neoforge:1.0.2")

//!! EVERY COMPAT (REQUIRED) ---------------------------------------------------------------------------------------- \\

    //- LOCAL
    if (findProperty("enable_everycomp_test").toString().toBoolean()) {
        modApi(files(path + "\\WoodGood\\neoforge\\build\\libs\\everycomp-${property("everycomp_testVersion")}-neoforge.jar"))
    }
    //+ MAVEN
    else {
        if (findProperty("maven_backup").toString().toBoolean()) modApi("maven.modrinth:every-compat:${property("everycomp_version")}-neoforge")
        else modApi("net.mehvahdjukaar:everycomp-neoforge:${property("everycomp_version")}:neoforge") { isTransitive = false }
    }

//!! TOOLS ---------------------------------------------------------------------------------------------------------- \\

    modRuntimeOnly("com.blamejared.crafttweaker:CraftTweaker-neoforge-${property("minecraft_version")}:${property("crafttweaker_version")}")
//    modRuntimeOnly("dev.emi:emi-neoforge:${property("emi_version")}+${property("minecraft_version")}")
    modRuntimeOnly("maven.modrinth:emi:${property("emi_version")}+${property("minecraft_version")}+neoforge")
    modRuntimeOnly("curse.maven:jei-238222:5846880")
    modRuntimeOnly("curse.maven:jade-324717:5884231") // v15.8.3
    modRuntimeOnly("curse.maven:configured-457570:5873783")
//    modRuntimeOnly("curse.maven:worldedit-225608:5830452")

//!! ================================================= DEPENDENCIES ================================================= \\
    //- ONLY FOR TESTING - can be commented out or enabled
    modRuntimeOnly("curse.maven:terrablender-neoforge-940057:6054947") // v4.1.0.8 |
//    modRuntimeOnly("curse.maven:citadel-331936:6002521") // v2.6.1 | Alexs-Caves //!!-1.20.1
//    modRuntimeOnly("curse.maven:yungs-api-421850:5769971") // v4.0.6 | Better-Archeology //!!-1.20.1
//    modRuntimeOnly("curse.maven:patchouli-306770:4966125") // v84 | TFC //!!-1.20.1
//    modRuntimeOnly("curse.maven:puzzles-lib-495476:5904750") // v8.1.25 | Stoneworks //!!-1.20.1
//    modRuntimeOnly("curse.maven:zeta-968868:5597406") // v1.0.24 | Quark //!!-1.20.1
//    modRuntimeOnly("curse.maven:athena-841890:5176879") // v3.1.2 | Chipped //!!-1.20.1
//    modRuntimeOnly("curse.maven:resourceful-lib-570073:5659871") // v2.1.29 | ??? //!!-1.20.1
//    modRuntimeOnly("curse.maven:fusion-connected-textures-854949:5129294") // v1.1.1 | Rechiseled //!!-1.20.1
//    modRuntimeOnly("curse.maven:supermartijn642s-config-lib-438332:4715408") // v1.1.8 | Rechiseled, Better-Archeology //!!-1.20.1
//    modRuntimeOnly("curse.maven:mantle-74924:6041712") // v1.11.28 | Tinkers-Construct //!!-1.20.1
//    modRuntimeOnly("curse.maven:blueprint-382216:6408581") // v7.1.3 | The-Outer-End //!!-1.20.1
//    modRuntimeOnly("curse.maven:kotlin-for-forge-351264:5402061") // v4.11.0 | More-Geodes-Reforged //!!-1.20.1
//    modRuntimeOnly("curse.maven:cloth-config-348521:5729105") // v11.1.136 | Atlantis //!!-1.20.1
//    modRuntimeOnly("curse.maven:architectury-api-419699:5137938") // v9.2.14 | Atlantis //!!-1.20.1
//    modRuntimeOnly("curse.maven:justhammers-681606:6358769") // v20.1.5 | Atlantis //!!-1.20.1
//    modRuntimeOnly("curse.maven:monolib-968432:6544057") // v2.1.0 | More-Beautiful-Torches //!!-1.20.1
//    modRuntimeOnly("curse.maven:ctm-267602:5983309") // Chipped //!!-1.20.1

    //- ~/neoforge/mods LOCAL

    //- OTHER MAVENs - can be commented out or enabled
//    modRuntimeOnly("software.bernie.geckolib:geckolib-forge-$minecraft_version:4.7.3") // v4.7.3 | Atlantis, Fins-And-Tails //!!-1.20.1
//    forgeRuntimeLibrary("com.eliotlash.mclib:mclib:20") // GeckoLib //!!-1.20.1
//    forgeRuntimeLibrary("com.teamresourceful:bytecodecs:1.0.2") // Chipped, Ad-Astra //!!-1.20.1

    //+ REQUIRED - The modules access libaries from below - Only in NEOFORGE

    //+ MIRRORED FROM COMMON - Required because dependOn(common) compiles common sources with neoforge classpath
    modCompileOnly("curse.maven:supermartijn642s-core-lib-454372:8623666") // v1.1.24 | Rechiseled

    // ---- OTHER MAVENS
    modCompileOnly("com.tterrag.registrate:Registrate:${property("registrate_version")}") // Create
    modCompileOnly("net.createmod.ponder:ponder-neoforge:${property("ponder_version")}+mc${property("minecraft_version")}") // Create
    modCompileOnly("dev.engine-room.flywheel:flywheel-neoforge-${property("minecraft_version")}:${property("flywheel_neoforge_version")}") // Create


//!! =================================================== IMPORTS ==================================================== \\
    //- ONLY FOR TESTING - can be commented out or enabled
//    modRuntimeOnly("curse.maven:better-archeology-835687:5693368") // supermartijn642s' Config-Lib, Yung's API //!!-1.20.1
//    modRuntimeOnly("curse.maven:twigs-496913:4605097") //!!-1.20.1
//    modRuntimeOnly("curse.maven:stoneworks-852663:4611947") // puzzles-lib //!!-1.20.1
//    modRuntimeOnly("curse.maven:building-but-better-989479:5362380") // v1.0.1 //!!-1.20.1
//    modRuntimeOnly("curse.maven:quark-243121:5594847") // Zeta //!!-1.20.1terr
//    modRuntimeOnly("curse.maven:more-beautiful-torches-860325:5263631") // MonoLib //!!-1.20.1
//    modRuntimeOnly("curse.maven:excessive-building-845097:5442075") // Forge-Config-API-Port //!!-1.20.1

    //- OTHER MAVENS

    //+ REQUIRED - The modules access libaries from below - ONLY IN FORGE

    // MACAW's
    modCompileOnly("curse.maven:macaws-bridges-351725:7627896") //v3.1.2
    modCompileOnly("curse.maven:macaws-windows-363569:6732754") //v2.4.0 //@ WIP - Requested for "metal" to "iron" //!!-1.20.1
    modCompileOnly("curse.maven:macaws-fences-and-walls-453925:5442217") //v1.1.2 //!!-1.20.1
//    modCompileOnly("curse.maven:macaws-paths-and-pavings-629153:5341126") //v1.0.5 - NO METAL //!!-1.20.1
//    modCompileOnly("curse.maven:macaws-roofs-352039:5554957") //v2.3.1 - NO METAL //!!-1.20.1
//    modCompileOnly("curse.maven:macaws-stairs-1119394:6155181") //v1.0.1 - NO METAL //!!-1.20.1

    //+ GENERAL
    modCompileOnly("curse.maven:create-deco-509285:7943181") // Create
    modCompileOnly("curse.maven:lapidarist-610032:5921456") //!!-1.20.1
    modCompileOnly("curse.maven:more-beautiful-bookshelves-846508:4671697") //!!-1.20.1
//    modImplementation("curse.maven:silents-gems-220311:5193708") // Silent-Lib //!!-1.20.1
//    modImplementation("curse.maven:fins-and-tails-427471:6485965") // GeckoLib //!!-1.20.1

    // OTHER MAVENs

    //+ MIRRORED FROM COMMON - Required because dependOn(common) compiles common sources with neoforge classpath
    modCompileOnly("curse.maven:rechiseled-558998:8875899") // Fusion, supermartijn642s-[ Config-Lib, Core-Lib ]

    // ---- OTHER MAVENs
    modCompileOnly("earth.terrarium.chipped:chipped-neoforge-${property("minecraft_version")}:4.0.2") //INCLUDED: Athena, Resourceful-Lib, REQUIRED: +Bytecodecs
    modCompileOnly("com.simibubi.create:create-${property("minecraft_version")}:${property("create_version")}:slim") { isTransitive = false } // Registrate, Flywheel, Ponder

//!! ================================================= FOR TESTING ================================================== \\

    // DUSTTYPE, GEMTYPE, METALTYPE, CRYSTALTYPE

    // DUSTTYPE, GEMTYPE, METALTYPE
//    modRuntimeOnly("curse.maven:the-age-of-ores-crystalcraft-unlimited-java-558675:7400834")
    modRuntimeOnly("curse.maven:mo-shiz-mod-62536:6939386")
//    modRuntimeOnly("curse.maven:shadowlands-398372:7316530") //!!-1.20.1

    // DUSTTYPE, GEMTYPE

    // DUSTTYPE, METALTYPE
//    modRuntimeOnly("curse.maven:atlantis-355418:6762614") // JustHammer, Architectury, Cloth-Config, GeckoLib //!!-1.20.1

    // GEMTYPE, METALTYPE

    // DUSTYPE
//    modRuntimeOnly("curse.maven:alexs-caves-924854:5848216") // Citadel //!!-1.20.1
    //@ glowroot-caves

    // CRYSTALTYPE
//    modRuntimeOnly("curse.maven:more-geodes-reforged-698475:5591834") // Kotlin-For-Forge //!!-1.20.1
    modRuntimeOnly("curse.maven:galosphere-631098:7382684")
//    modRuntimeOnly("curse.maven:the-outer-end-430404:6886571") //!!-1.20.1
//    modRuntimeOnly("curse.maven:official-divinerpg-363543:5715606") //!!-1.20.1

    // GEMTYPE

    // METALTYPE
//    modRuntimeOnly("curse.maven:terrafirmacraft-302973:5943050") // patchouli //!!-1.20.1


    // STONETYPE ONLY
//    modRuntimeOnly("curse.maven:strata-forge-edition-387296:4989643") //!!-1.20.1
//    modRuntimeOnly("curse.maven:spelunkery-790530:5727135") // Moonlight-Lib //!!-1.20.1
//    modRuntimeOnly("curse.maven:tinkers-construct-74072:6041763") // Mantle //!!-1.20.1
//    modRuntimeOnly("curse.maven:thaumon-926511:5492065") //!!-1.20.1
//    modRuntimeOnly("curse.maven:gaia-dimension-302529:4794552") //!!-1.20.1
//    modRuntimeOnly("curse.maven:what-is-stone-colorful-caves-853161:5175855") //!!-1.20.1
//    modRuntimeOnly("curse.maven:artsandcrafts-1034791:5992027") //!!-1.20.1
//    modRuntimeOnly("curse.maven:the-outer-end-430404:5812948") // Blueprint //!!-1.20.1

}