import cuchaz.enigma.command.ConvertMappingsCommand

plugins {
    java
}

tasks {
    val buildClientMappings by registering {
        outputs.file("build/mappings/client.tiny")
        doLast {
            ConvertMappingsCommand.run(
                "enigma",
                project.file("client-mappings").toPath(),
                "tinyv2:intermediary:named",
                project.file("build/mappings/client.tiny").toPath()
            )
        }
    }

    val clientMappingsJar by registering(Jar::class) {
        group = "build"
        archiveClassifier.set("client")
        archiveVersion.set(project.version.toString())
        from(buildClientMappings) {
            into("mappings")
            rename("client.tiny", "mappings.tiny")
        }
    }

    val buildServerMappings by registering {
        outputs.file("build/mappings/server.tiny")
        doLast {
            ConvertMappingsCommand.run(
                "enigma",
                project.file("server-mappings").toPath(),
                "tinyv2:intermediary:named",
                project.file("build/mappings/server.tiny").toPath()
            )
        }
    }

    val serverMappingsJar by registering(Jar::class) {
        group = "build"
        archiveClassifier.set("server")
        from(buildServerMappings) {
            archiveVersion.set(project.version.toString())
            into("mappings")
            rename("server.tiny", "mappings.tiny")
        }
    }

    build.get().dependsOn(clientMappingsJar, serverMappingsJar)
}
