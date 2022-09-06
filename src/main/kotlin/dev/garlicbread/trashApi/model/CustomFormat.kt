package dev.garlicbread.trashApi.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CustomFormat(
    val trash_id: String,
    val name: String,
    val includeCustomFormatWhenRenaming: Boolean,
    val specifications: List<Specification>
) {
    data class Specification(
        val name: String,
        val implementation: String,
        val negate: Boolean,
        val required: Boolean,
        val fields: Fields
    ) {
        data class Fields(
            val value: Any
        )
    }
}
