plugins {
    id("java-library")
    id("com.gradleup.shadow") version "9.6.1"
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
    //maven("https://repo.codemc.io/repository/maven-releases/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.2.build.+")

    //("com.github.retrooper:packetevents-spigot:2.13.0")

    implementation("de.itsgraphax:GrphxLib:5.1.0")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(25)
}

tasks {
    build {
        dependsOn(shadowJar)
    }

    processResources {
        val props = mapOf("version" to version)
        filesMatching("paper-plugin.yml") {
            expand(props)
        }
    }
}
