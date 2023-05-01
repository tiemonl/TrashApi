package dev.garlicbread.trashApi.service.releaseprofile.sonarr

import dev.garlicbread.trashApi.model.ReleaseProfile

interface SonarrReleaseProfileService {
    suspend fun fetchAll(): List<ReleaseProfile>
    suspend fun fetchById(trash_id: String): ReleaseProfile
}
