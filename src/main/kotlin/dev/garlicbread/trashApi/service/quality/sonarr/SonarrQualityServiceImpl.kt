package dev.garlicbread.trashApi.service.quality.sonarr

import dev.garlicbread.trashApi.model.QualitySize
import dev.garlicbread.trashApi.repository.quality.sonarr.SonarrQualityRepository
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class SonarrQualityServiceImpl @Autowired constructor(
    private val sonarrQualityRepository: SonarrQualityRepository,
) : SonarrQualityService {

    init {
        logger.info { "Initialize Sonarr Quality Service" }
    }
    override suspend fun fetchAll(): List<QualitySize> {
        logger.info { "SonarrQualityService.fetchAll()" }
        return sonarrQualityRepository.fetchAll()
    }
    override suspend fun fetchById(trash_id: String): QualitySize {
        logger.info { "SonarrQualityService.fetchById($trash_id)" }
        return sonarrQualityRepository.fetchById(trash_id)
    }
}
