package dev.garlicbread.trashApi.repository.quality.sonarr

import dev.garlicbread.trashApi.model.QualitySize
import org.springframework.stereotype.Repository

@Repository
interface SonarrQualityRepository {
    suspend fun fetchAll(): List<QualitySize>
    suspend fun fetchById(trash_id: String): QualitySize
}
