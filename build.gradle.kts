plugins {
    id("fabric-loom") version "0.12-SNAPSHOT"
    id("io.github.juuxel.loom-quiltflower") version "1.7.1"
    kotlin("jvm") version "1.7.10"
}

val mod_version: String by project
val maven_group: String by project
val minecraft_version: String by project
val yarn_mappings: String by project
val loader_version: String by project
val fabric_version: String by project
val archives_base_name: String by project

version = mod_version
group = maven_group

repositories {
    maven("https://repo.essential.gg/repository/maven-public")
    maven("https://pkgs.dev.azure.com/djtheredstoner/DevAuth/_packaging/public/maven/v1")
}

dependencies {
    minecraft("com.mojang:minecraft:${minecraft_version}")
    mappings("net.fabricmc:yarn:${yarn_mappings}:v2")

    modImplementation("net.fabricmc:fabric-loader:${loader_version}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${fabric_version}")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.8.1+kotlin.1.7.0")
    modImplementation("gg.essential:universalcraft-1.19-fabric:214+pull-27")
    modImplementation(include("gg.essential:elementa-1.18.1-fabric:526")!!)

    modRuntimeOnly("me.djtheredstoner:DevAuth-fabric:1.1.0")
}

configurations.modImplementation {
    exclude("org.jetbrains.kotlin")
    exclude("gg.essential", "universalcraft-1.18.1-fabric")
}

tasks {
    processResources {
        inputs.property("version", project.version)
        filteringCharset = "UTF-8"

        filesMatching("fabric.mod.json") {
            expand(mapOf("version" to project.version))
        }
    }
    compileJava {
        options.encoding = "UTF-8"
    }
    jar {
        from("COPYING") {
            rename { "${it}_${archives_base_name}" }
        }
        archiveBaseName.set(archives_base_name)
    }

}
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    withSourcesJar()
}

