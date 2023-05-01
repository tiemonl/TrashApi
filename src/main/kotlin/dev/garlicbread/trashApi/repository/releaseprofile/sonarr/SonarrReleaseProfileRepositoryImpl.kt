package dev.garlicbread.trashApi.repository.releaseprofile.sonarr

import dev.garlicbread.trashApi.model.ReleaseProfile
import dev.garlicbread.trashApi.singleton.Configuration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Repository

@Repository
class SonarrReleaseProfileRepositoryImpl : SonarrReleaseProfileRepository {
    override suspend fun fetchAll(): List<ReleaseProfile> = withContext(Dispatchers.IO) {
        return@withContext Configuration.sonarrReleaseProfiles
    }
    override suspend fun fetchById(trash_id: String): ReleaseProfile = withContext(Dispatchers.IO) {
        return@withContext Configuration.sonarrReleaseProfiles.first { it.trash_id.equals(trash_id, ignoreCase = true) }
    }
}
