package dev.garlicbread.trashApi.singleton

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.garlicbread.trashApi.model.CustomFormat
import dev.garlicbread.trashApi.model.Metadata
import dev.garlicbread.trashApi.model.QualitySize
import dev.garlicbread.trashApi.model.ReleaseProfile
import mu.KotlinLogging
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

object Configuration {
    private lateinit var radarrCustomFormatPaths: List<String>
    private lateinit var radarrQualitySizePaths: List<String>
    private lateinit var sonarrCustomFormatPaths: List<String>
    private lateinit var sonarrQualitySizePaths: List<String>
    private lateinit var sonarrReleaseProfilePaths: List<String>

    var radarrQualitySizes: List<QualitySize>
    var radarrCustomFormats: List<CustomFormat>
    var sonarrQualitySizes: List<QualitySize>
    var sonarrCustomFormats: List<CustomFormat>
    var sonarrReleaseProfiles: List<ReleaseProfile>

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val logger = KotlinLogging.logger { }
    init {
        logger.warn { "Configuration Init ${System.getProperty("user.dir")}" }
        createPaths()
        radarrQualitySizes = getAllQualitySizes(radarrQualitySizePaths)
        radarrCustomFormats = getAllCustomFormats(radarrCustomFormatPaths)
        sonarrQualitySizes = getAllQualitySizes(sonarrQualitySizePaths)
        sonarrCustomFormats = getAllCustomFormats(sonarrCustomFormatPaths)
        sonarrReleaseProfiles = getAllReleaseProfiles(sonarrReleaseProfilePaths)
    }

    private fun createPaths() {
        val metadata = moshi.adapter(Metadata::class.java).fromJson(getResourceAsText("guide/metadata.json"))!!
        radarrCustomFormatPaths = metadata.json_paths.radarr.custom_formats.map { "guide/$it" }
        radarrQualitySizePaths = metadata.json_paths.radarr.qualities.map { "guide/$it" }
        sonarrCustomFormatPaths = metadata.json_paths.sonarr.custom_formats.map { "guide/$it" }
        sonarrQualitySizePaths = metadata.json_paths.sonarr.qualities.map { "guide/$it" }
        sonarrReleaseProfilePaths = metadata.json_paths.sonarr.release_profiles.map { "guide/$it" }
    }

    private fun getAllQualitySizes(paths: List<String>) = buildList {
        paths.map { path ->
            Files.walk(Paths.get(path)).filter { Files.isRegularFile(it) }.forEach { file ->
                moshi.adapter(QualitySize::class.java).fromJson(getResourceAsText(file.toString()))?.let { add(it) }
            }
        }
    }

    private fun getAllCustomFormats(paths: List<String>) = buildList {
        paths.map { path ->
            Files.walk(Paths.get(path)).filter { Files.isRegularFile(it) }.forEach { file ->
                moshi.adapter(CustomFormat::class.java).fromJson(getResourceAsText(file.toString()))?.let { add(it) }
            }
        }
    }

    private fun getAllReleaseProfiles(paths: List<String>) = buildList {
        paths.map { path ->
            Files.walk(Paths.get(path)).filter { Files.isRegularFile(it) }.forEach { file ->
                moshi.adapter(ReleaseProfile::class.java).fromJson(getResourceAsText(file.toString()))?.let { add(it) }
            }
        }
    }
}

fun getResourceAsText(path: String): String {
    return File(path).readText(Charsets.UTF_8)
}
