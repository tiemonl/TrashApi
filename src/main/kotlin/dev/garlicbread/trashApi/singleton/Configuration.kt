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
    private lateinit var radarrCustomFormatPath: String
    private lateinit var radarrQualitySizePath: String
    private lateinit var sonarrCustomFormatPath: String
    private lateinit var sonarrQualitySizePath: String
    private lateinit var sonarrReleaseProfilePath: String

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
        radarrQualitySizes = getAllQualitySizes(radarrQualitySizePath)
        radarrCustomFormats = getAllCustomFormats(radarrCustomFormatPath)
        sonarrQualitySizes = getAllQualitySizes(sonarrQualitySizePath)
        sonarrCustomFormats = getAllCustomFormats(sonarrCustomFormatPath)
        sonarrReleaseProfiles = getAllReleaseProfiles(sonarrReleaseProfilePath)
    }

    private fun createPaths() {
        val metadata = moshi.adapter(Metadata::class.java).fromJson(getResourceAsText("guide/metadata.json"))!!
        radarrCustomFormatPath = "guide/${metadata.json_paths.radarr.custom_formats.first()}"
        radarrQualitySizePath = "guide/${metadata.json_paths.radarr.qualities.first()}"
        sonarrCustomFormatPath = "guide/${metadata.json_paths.sonarr.custom_formats.first()}"
        sonarrQualitySizePath = "guide/${metadata.json_paths.sonarr.qualities.first()}"
        sonarrReleaseProfilePath = "guide/${metadata.json_paths.sonarr.release_profiles.first()}"
    }

    private fun getAllQualitySizes(path: String) = buildList {
        Files.walk(Paths.get(path)).filter { Files.isRegularFile(it) }.forEach { file ->
            moshi.adapter(QualitySize::class.java).fromJson(getResourceAsText(file.toString()))?.let { add(it) }
        }
    }

    private fun getAllCustomFormats(path: String) = buildList {
        Files.walk(Paths.get(path)).filter { Files.isRegularFile(it) }.forEach { file ->
            moshi.adapter(CustomFormat::class.java).fromJson(getResourceAsText(file.toString()))?.let { add(it) }
        }
    }

    private fun getAllReleaseProfiles(path: String) = buildList {
        Files.walk(Paths.get(path)).filter { Files.isRegularFile(it) }.forEach { file ->
            moshi.adapter(ReleaseProfile::class.java).fromJson(getResourceAsText(file.toString()))?.let { add(it) }
        }
    }
}

fun getResourceAsText(path: String): String {
    return File(path).readText(Charsets.UTF_8)
}
