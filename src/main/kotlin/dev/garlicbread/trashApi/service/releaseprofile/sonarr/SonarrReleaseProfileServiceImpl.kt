package dev.garlicbread.trashApi.service.releaseprofile.sonarr

import dev.garlicbread.trashApi.model.ReleaseProfile
import dev.garlicbread.trashApi.repository.releaseprofile.sonarr.SonarrReleaseProfileRepository
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class SonarrReleaseProfileServiceImpl @Autowired constructor(
    private val sonarrReleaseProfileRepository: SonarrReleaseProfileRepository,
) : SonarrReleaseProfileService {

    init {
        logger.info { "Initialize Sonarr Release Profile Service" }
    }
    override suspend fun fetchAll(): List<ReleaseProfile> {
        logger.info { "SonarrReleaseProfileService.fetchAll()" }
        return sonarrReleaseProfileRepository.fetchAll()
    }
    override suspend fun fetchById(trash_id: String): ReleaseProfile {
        logger.info { "SonarrReleaseProfileService.fetchById($trash_id)" }
        return sonarrReleaseProfileRepository.fetchById(trash_id)
    }
}
