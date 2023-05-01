package dev.garlicbread.trashApi.repository.customformat.sonarr

import dev.garlicbread.trashApi.model.CustomFormat
import dev.garlicbread.trashApi.singleton.Configuration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class SonarrCustomFormatRepositoryImpl @Autowired constructor() : SonarrCustomFormatRepository {
    override suspend fun fetchAll(): List<CustomFormat> = withContext(Dispatchers.IO) {
        return@withContext Configuration.sonarrCustomFormats
    }
    override suspend fun fetchById(trash_id: String): CustomFormat = withContext(Dispatchers.IO) {
        return@withContext Configuration.sonarrCustomFormats.first { it.trash_id.equals(trash_id, ignoreCase = true) }
    }
}
