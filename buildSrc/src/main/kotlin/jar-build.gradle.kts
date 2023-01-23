import cuchaz.enigma.command.ConvertMappingsCommand

plugins {
    java
}

tasks {
    fun createTasks(side: String): List<TaskProvider<out Task>> {
        val titlecased = side.capitalize()
        val buildMappings = register("build${titlecased}Mappings") {
            outputs.file("build/mappings/$side.tiny")
            doLast {
                ConvertMappingsCommand.run(
                    "enigma",
                    project.file("$side-mappings").toPath(),
                    "tinyv2:official:named",
                    project.file("build/mappings/$side.tiny").toPath()
                )
            }
        }
        val lieToLoom = register("build${titlecased}LoomMappings") {
            outputs.file("build/mappings/$side-loom.tiny")
            doLast {
                ConvertMappingsCommand.run(
                    "enigma",
                    project.file("$side-mappings").toPath(),
                    "tinyv2:intermediary:named",
                    project.file("build/mappings/$side-loom.tiny").toPath()
                )
            }
        }

        val mappingsJar = register<Jar>("${side}MappingsJar") {
            group = "build"
            archiveClassifier.set(side)
            archiveVersion.set(project.version.toString())
            from(buildMappings) {
                into("mappings")
                rename("$side.tiny", "mappings.tiny")
            }
        }

        val loomMappingsJar = register<Jar>("${side}LoomMappingsJar") {
            group = "build"
            archiveClassifier.set("${side}-loom")
            archiveVersion.set(project.version.toString())
            from(lieToLoom) {
                into("mappings")
                rename("$side-loom.tiny", "mappings.tiny")
            }
        }

        return listOf(buildMappings, mappingsJar, lieToLoom, loomMappingsJar)
    }
    
    val clientTasks = createTasks("client")
    val serverTasks = createTasks("server")

    build.get().dependsOn(clientTasks + serverTasks)
}
