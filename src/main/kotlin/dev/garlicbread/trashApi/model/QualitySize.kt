package dev.garlicbread.trashApi.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QualitySize(
    val trash_id: String,
    val type: String,
    val qualities: List<Quality>,
) {
    data class Quality(
        val quality: String,
        val min: Double,
        val preferred: Double,
        val max: Double,
    )
}
