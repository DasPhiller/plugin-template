val javaVersion = 17 // Minecraft 1.19 requires Java 17
val extensionsVersion = "4.3.4"

plugins {
    kotlin("jvm") version "1.8.20"
    id("io.papermc.paperweight.userdev") version "1.5.0"
    id("xyz.jpenilla.run-paper") version "1.1.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.3"
}

group = "org.example"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    // PaperMC Dependency
    paperweight.paperDevBundle("1.19.4-R0.1-SNAPSHOT")
    
    // KSpigot dependency
    implementation("net.axay", "kspigot", "1.19.2")
    
    library(kotlin("stdlib"))
    
    //Extensions dependency
    implementation("de.dasphiller.extensions:extensions:$extensionsVersion")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "$javaVersion"
        }
    }
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(javaVersion)
    }
    assemble {
        dependsOn(reobfJar)
    }
    runServer {
        minecraftVersion("1.19.4")
    }
}


bukkit {
    name = "ExamplePlugin"
    apiVersion = "1.19"
    authors = listOf(
        "Your Name",
    )
    main = "$group.exampleplugin.ExamplePlugin"
    version = getVersion().toString()
    libraries = listOf(
        "net.axay:kspigot:1.19.2",
        "de.dasphiller.extensions:extensions:$extensionsVersion"
    )
    prefix = "ExamplePlugin"
}
