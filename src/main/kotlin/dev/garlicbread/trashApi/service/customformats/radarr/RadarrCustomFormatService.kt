package dev.garlicbread.trashApi.service.customformats.radarr

import dev.garlicbread.trashApi.model.CustomFormat

interface RadarrCustomFormatService {
    suspend fun fetchAll(): List<CustomFormat>
    suspend fun fetchById(trash_id: String): CustomFormat
}
