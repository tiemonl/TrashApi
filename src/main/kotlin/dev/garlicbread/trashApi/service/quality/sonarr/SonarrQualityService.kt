package dev.garlicbread.trashApi.service.quality.sonarr

import dev.garlicbread.trashApi.model.QualitySize

interface SonarrQualityService {
    suspend fun fetchAll(): List<QualitySize>
    suspend fun fetchById(trash_id: String): QualitySize
}
