package dev.garlicbread.trashApi.repository.quality.sonarr

import dev.garlicbread.trashApi.model.QualitySize
import dev.garlicbread.trashApi.singleton.Configuration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Repository

@Repository
class SonarrQualityRepositoryImpl : SonarrQualityRepository {
    override suspend fun fetchAll(): List<QualitySize> = withContext(Dispatchers.IO) {
        return@withContext Configuration.sonarrQualitySizes
    }
    override suspend fun fetchById(trash_id: String): QualitySize = withContext(Dispatchers.IO) {
        return@withContext Configuration.sonarrQualitySizes.first { it.trash_id.equals(trash_id, ignoreCase = true) }
    }
}
