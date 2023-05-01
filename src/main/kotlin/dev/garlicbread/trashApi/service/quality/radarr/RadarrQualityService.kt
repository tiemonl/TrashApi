package dev.garlicbread.trashApi.service.quality.radarr

import dev.garlicbread.trashApi.model.QualitySize

interface RadarrQualityService {
    suspend fun fetchAll(): List<QualitySize>
    suspend fun fetchById(trash_id: String): QualitySize
}
