package dev.garlicbread.trashApi.repository.quality.radarr

import dev.garlicbread.trashApi.model.QualitySize
import dev.garlicbread.trashApi.singleton.Configuration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class RadarrQualityRepositoryImpl @Autowired constructor() : RadarrQualityRepository {
    override suspend fun fetchAll(): List<QualitySize> = withContext(Dispatchers.IO) {
        return@withContext Configuration.radarrQualitySizes
    }
    override suspend fun fetchById(trash_id: String): QualitySize = withContext(Dispatchers.IO) {
        return@withContext Configuration.radarrQualitySizes.first { it.trash_id.equals(trash_id, ignoreCase = true) }
    }
}
