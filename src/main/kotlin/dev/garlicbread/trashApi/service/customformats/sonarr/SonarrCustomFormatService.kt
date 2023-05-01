package dev.garlicbread.trashApi.service.customformats.sonarr

import dev.garlicbread.trashApi.model.CustomFormat

interface SonarrCustomFormatService {
    suspend fun fetchAll(): List<CustomFormat>
    suspend fun fetchById(trash_id: String): CustomFormat
}
