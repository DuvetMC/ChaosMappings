plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    maven("https://maven.quiltmc.org/repository/release/")
    maven("https://maven.quiltmc.org/repository/snapshot/")
}

dependencies {
    val enigmaVersion = "1.4.0"
    implementation("org.quiltmc:enigma:$enigmaVersion")
    implementation("org.quiltmc:enigma-cli:$enigmaVersion")
}
