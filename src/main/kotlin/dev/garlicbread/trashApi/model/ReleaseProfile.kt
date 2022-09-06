package dev.garlicbread.trashApi.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReleaseProfile(
    val name: String,
    val trash_id: String,
    val includePreferredWhenRenaming: Boolean?,
    val required: List<Any>,
    val preferred: List<Preferred>,
    val ignored: List<Any>,
    val community_id: String?
) {
    data class Preferred(
        val score: Int,
        val terms: List<Any>
    )
}
