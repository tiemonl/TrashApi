package dev.garlicbread.trashApi.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Metadata(
    val `$schema`: String,
    val json_paths: JsonPaths,
) {
    data class JsonPaths(
        val radarr: Radarr,
        val sonarr: Sonarr,
    ) {
        data class Radarr(
            val custom_formats: List<String>,
            val qualities: List<String>,
        )

        data class Sonarr(
            val release_profiles: List<String>,
            val custom_formats: List<String>,
            val qualities: List<String>,
        )
    }
}
