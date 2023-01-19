import { JSZip } from "https://deno.land/x/jszip@0.11.0/mod.ts"
import { Logger } from "https://deno.land/std@0.173.0/log/mod.ts";

const LOGGER = new Logger("jarBuild", "NOTSET")

if (!Deno.args[0]) {
    LOGGER.error("Please provide what platform you want to export!")
}

const file = Deno.readFile("./"+Deno.args[0]+".tiny")
const manifest = "Manifest-Version: 1.0\n\n"

const zip = new JSZip()

zip.addFile("/mappings/mappings.tiny", await file)
zip.addFile("/META-INF/MANIFEST.INF", manifest)

try {
    await Deno.mkdir("./build/")
} catch (_ignored) {
    // Already exists
}

await zip.writeZip("./build/"+Deno.args[0]+".jar")
