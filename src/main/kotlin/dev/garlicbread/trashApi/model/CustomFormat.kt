package dev.garlicbread.trashApi.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CustomFormat(
    val trash_id: String,
    val trash_score: String?,
    val name: String,
    val includeCustomFormatWhenRenaming: Boolean?,
    val specifications: List<Specification>,
) {
    data class Specification(
        val fields: Fields,
        val implementation: String,
        val name: String,
        val negate: Boolean,
        val required: Boolean,
    ) {
        data class Fields(
            val value: Any,
        )
    }
}
