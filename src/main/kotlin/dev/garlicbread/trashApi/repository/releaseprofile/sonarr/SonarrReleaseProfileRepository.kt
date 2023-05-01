package dev.garlicbread.trashApi.repository.releaseprofile.sonarr

import dev.garlicbread.trashApi.model.ReleaseProfile
import org.springframework.stereotype.Repository

@Repository
interface SonarrReleaseProfileRepository {
    suspend fun fetchAll(): List<ReleaseProfile>
    suspend fun fetchById(trash_id: String): ReleaseProfile
}
