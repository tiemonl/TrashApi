package dev.garlicbread.trashApi.repository.quality.radarr

import dev.garlicbread.trashApi.model.QualitySize
import org.springframework.stereotype.Repository

@Repository
interface RadarrQualityRepository {
    suspend fun fetchAll(): List<QualitySize>
    suspend fun fetchById(trash_id: String): QualitySize
}
