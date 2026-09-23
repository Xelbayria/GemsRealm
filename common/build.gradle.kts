plugins {
    id("com.possible-triangle.common")
}

common {
    accessWidener()
}

val modId: String = property("mod_id").toString()
val modVersion: String = property("mod_version").toString()
tasks.named<Jar>("jar") {
    archiveBaseName.set(modId)
    archiveVersion.set(modVersion)
    archiveClassifier.set("common")
}
tasks.named<Jar>("sourcesJar") {
    from(sourceSets.main.get().allSource)
    archiveBaseName.set(modId)
    archiveVersion.set(modVersion)
    archiveClassifier.set("common-sources")
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
        else modApi("net.mehvahdjukaar:moonlight-common:${property("moonlight_version")}") { isTransitive = false }
    }
    accessTransformers("net.mehvahdjukaar:moonlight-common:${property("moonlight_version")}")

//!! EVERY COMPAT (REQUIRED) ---------------------------------------------------------------------------------------- \\

    //- LOCAL
    if (findProperty("enable_everycomp_test").toString().toBoolean()) {
        modApi(files(path + "\\WoodGood\\neoforge\\build\\libs\\everycomp-${property("everycomp_testVersion")}-neoforge.jar"))
    }
    //+ MAVEN
    else {
        if (findProperty("maven_backup").toString().toBoolean()) modApi("maven.modrinth:every-compat:${property("everycomp_version")}-neoforge")
        else modApi("net.mehvahdjukaar:everycomp-common:${property("everycomp_version")}") { isTransitive = false }
    }


//!! ================================================= DEPENDENCIES ================================================= \\

    //+ REQUIRED - The modules access libaries from below
    modCompileOnly("curse.maven:zeta-968868:5597406") // v1.0.24 | Quark
    modCompileOnly("curse.maven:forge-config-api-port-fabric-547434:6325013") // v8.0.2 | Excessive-Building
    modCompileOnly("curse.maven:supermartijn642s-core-lib-454372:8623666") // v1.1.24 | Rechiseled

    //+ OTHER MAVENS
    modCompileOnly("com.tterrag.registrate_fabric:Registrate:${property("registrate_fabric_version")}") // Create
//    modRuntimeOnly("dev.engine-room.flywheel:flywheel-fabric-$minecraft_version:$flywheel_fabric_version") // Create - not really needed

//!! =================================================== IMPORTS ==================================================== \\

    // ~/common/mods LOCAL

    //+ REQUIRED - The modules access libaries from below
    modCompileOnly("curse.maven:more-beautiful-torches-860325:5263631") // MonoLib
    modCompileOnly("curse.maven:rechiseled-558998:8875899") // Fusion, supermartijn642s-[ Config-Lib, Core-Lib ]

//    modCompileOnly("curse.maven:excessive-building-845097:6280151") // Forge-Config-API-Port
//    modCompileOnly("curse.maven:chipped-456956:4634858") // v3.0.7 - not really needed
//    modCompileOnly("curse.maven:better-archeology-835687:5704094") // supermartijn642s' Config-Lib
//    modCompileOnly("curse.maven:blockus-312289:5896309")
//    modCompileOnly("curse.maven:quark-243121:5594847")
//    modCompileOnly("curse.maven:twigs-496913:4603805")
//    modCompileOnly("curse.maven:building-but-better-989479:5382599")
//    modCompileOnly("curse.maven:stoneworks-852663:4611947")

    //+ OTHER MAVENs
    modCompileOnly("earth.terrarium.chipped:chipped-fabric-${property("minecraft_version")}:4.0.2") //INCLUDED: Athena, Resourceful-Lib, REQUIRED: Bytecodecs, ConnectedTexturesMod (CTM), Fabric-API
    modCompileOnly("com.simibubi.create:create-${property("minecraft_version")}:${property("create_version")}:slim") { isTransitive = false } // Registrate, Flywheel, Ponder
}

tasks.named("copyAccessTransformersPublications") {
    dependsOn(":common:transformAccessWidener")
}