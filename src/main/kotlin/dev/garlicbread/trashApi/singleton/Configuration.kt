package dev.garlicbread.trashApi.singleton

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.garlicbread.trashApi.model.CustomFormat
import dev.garlicbread.trashApi.model.Metadata
import dev.garlicbread.trashApi.model.QualitySize
import dev.garlicbread.trashApi.model.ReleaseProfile
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

object Configuration {
    private var radarrCustomFormatPath: String = ""
    private var radarrQualitySizePath: String = ""
    private var sonarrCustomFormatPath: String = ""
    private var sonarrQualitySizePath: String = ""
    private var sonarrReleaseProfilePath: String = ""

    var radarrQualitySizes: List<QualitySize> = emptyList()
    var radarrCustomFormats: List<CustomFormat> = emptyList()

    var sonarrQualitySizes: List<QualitySize> = emptyList()
    var sonarrCustomFormats: List<CustomFormat> = emptyList()
    var sonarrReleaseProfiles: List<ReleaseProfile> = emptyList()

    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    init {
        createPaths()
        radarrQualitySizes = getAllQualitySizes(radarrQualitySizePath)
        radarrCustomFormats = getAllCustomFormats(radarrCustomFormatPath)
        sonarrQualitySizes = getAllQualitySizes(sonarrQualitySizePath)
        sonarrCustomFormats = getAllCustomFormats(sonarrCustomFormatPath)
        sonarrReleaseProfiles = getAllReleaseProfiles(sonarrReleaseProfilePath)
    }

    private fun createPaths() {
        val metadata = moshi.adapter(Metadata::class.java).fromJson(getResourceAsText("./guide/metadata.json"))!!
        radarrCustomFormatPath = "guide/${metadata.json_paths.radarr.custom_formats.first()}"
        radarrQualitySizePath = "guide/${metadata.json_paths.radarr.qualities.first()}"
        sonarrCustomFormatPath = "guide/${metadata.json_paths.sonarr.custom_formats.first()}"
        sonarrQualitySizePath = "guide/${metadata.json_paths.sonarr.qualities.first()}"
        sonarrReleaseProfilePath = "guide/${metadata.json_paths.sonarr.release_profiles.first()}"
    }

    private fun getAllQualitySizes(path: String) = buildList<QualitySize> {
        Files.walk(Paths.get(path)).filter { Files.isRegularFile(it) }.forEach { file ->
            val qualitySize = moshi.adapter(QualitySize::class.java).fromJson(getResourceAsText(file.toString()))
//            val qualitySize = Json.decodeFromString<QualitySize>(getResourceAsText(file.toString()))
            if (qualitySize != null) {
                add(qualitySize)
            }
        }
    }

    private fun getAllCustomFormats(path: String) = buildList<CustomFormat> {
        Files.walk(Paths.get(path)).filter { Files.isRegularFile(it) }.forEach { file ->
            val customFormat = moshi.adapter(CustomFormat::class.java).fromJson(getResourceAsText(file.toString()))
//            val customFormat = Json.decodeFromString<CustomFormat>(getResourceAsText(file.toString()))
            if (customFormat != null) {
                add(customFormat)
            }
        }
    }

    private fun getAllReleaseProfiles(path: String) = buildList<ReleaseProfile> {
        Files.walk(Paths.get(path)).filter { Files.isRegularFile(it) }.forEach { file ->
            val releaseProfile = moshi.adapter(ReleaseProfile::class.java).fromJson(getResourceAsText(file.toString()))
//            val releaseProfile = Json.decodeFromString<ReleaseProfile>(getResourceAsText(file.toString()))
            if (releaseProfile != null) {
                add(releaseProfile)
            }
        }
    }
}

fun getResourceAsText(path: String): String {
    return File(path).readText(Charsets.UTF_8)
}
