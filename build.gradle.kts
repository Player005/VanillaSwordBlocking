import xyz.jpenilla.resourcefactory.bukkit.BukkitPluginYaml

plugins {
    `java-library`
    id("io.papermc.paperweight.userdev") version "1.7.3"
    id("xyz.jpenilla.run-paper") version "2.3.1" // Adds runServer and runMojangMappedServer tasks for testing
    id("xyz.jpenilla.resource-factory-bukkit-convention") version "1.2.0" // Generates plugin.yml based on the Gradle config
}

group = "io.papermc.paperweight"
version = "1.0.0-SNAPSHOT"
description = "Test plugin for paperweight-userdev"

java {
    // Configure the java toolchain. This allows gradle to auto-provision JDK 21 on systems that only have JDK 11 installed for example.
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

// 1)
// For >=1.20.5 when you don't care about supporting spigot
paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION

dependencies {
    paperweight.paperDevBundle("1.21.1-R0.1-SNAPSHOT")
    // paperweight.foliaDevBundle("1.21.1-R0.1-SNAPSHOT")
    // paperweight.devBundle("com.example.paperfork", "1.21.1-R0.1-SNAPSHOT")
}

tasks {
    compileJava {
        // Set the release flag. This configures what version bytecode the compiler will emit, as well as what JDK APIs are usable.
        // See https://openjdk.java.net/jeps/247 for more information.
        options.release = 21
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
    }
}

// Configure plugin.yml generation
// - name, version, and description are inherited from the Gradle project.
bukkitPluginYaml {
    main = "net.player005.vanillablocking.VanillaBlocking"
    load = BukkitPluginYaml.PluginLoadOrder.STARTUP
    authors.add("Player005")
    apiVersion = "1.21"
}
