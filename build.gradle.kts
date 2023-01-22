plugins {
    `jar-build` // defined in buildSrc
    `maven-publish`
}

version = "mcb1.0_01"

tasks.jar {
    enabled = false
}

publishing {
    repositories {
        maven {
            // TODO: Repo
            url = uri(layout.buildDirectory.dir("repo"))
        }
    }
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "org.duvetmc"
            from(components["java"])
            pom {
                organization {
                    url.set("https://github.com/DuvetMC")
                    name.set("DuvetMC")
                }
                name.set("$version-mappings")
                description.set("Chaotically created mappings for $version")
                url.set("https://github.com/DuvetMC/ChaosMappings/tree/$version")
                licenses {
                    license {
                        name.set("CC0-1.0")
                        url.set("https://creativecommons.org/publicdomain/zero/1.0/legalcode")
                    }
                }
                scm {
                    connection.set("scm:git:git+https://github.com/DuvetMC/ChaosMappings.git")
                    developerConnection.set("scm:git:ssh://git@github.com:DuvetMC/ChaosMappings.git")
                    url.set("https://github.com/DuvetMC/ChaosMappings")
                }
            }
        }
    }
}