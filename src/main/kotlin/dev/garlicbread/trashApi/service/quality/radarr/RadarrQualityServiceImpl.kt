package dev.garlicbread.trashApi.service.quality.radarr

import dev.garlicbread.trashApi.model.QualitySize
import dev.garlicbread.trashApi.repository.quality.radarr.RadarrQualityRepository
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class RadarrQualityServiceImpl @Autowired constructor(
    private val radarrQualityRepository: RadarrQualityRepository,
) : RadarrQualityService {

    init {
        logger.info { "Initialize Radarr Quality Service" }
    }
    override suspend fun fetchAll(): List<QualitySize> {
        logger.info { "RadarrQualityService.fetchAll()" }
        return radarrQualityRepository.fetchAll()
    }
    override suspend fun fetchById(trash_id: String): QualitySize {
        logger.info { "RadarrQualityService.fetchById($trash_id)" }
        return radarrQualityRepository.fetchById(trash_id)
    }
}
