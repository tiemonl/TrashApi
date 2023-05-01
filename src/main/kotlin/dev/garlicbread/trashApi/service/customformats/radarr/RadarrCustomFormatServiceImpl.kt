package dev.garlicbread.trashApi.service.customformats.radarr

import dev.garlicbread.trashApi.model.CustomFormat
import dev.garlicbread.trashApi.repository.customformat.radarr.RadarrCustomFormatRepository
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class RadarrCustomFormatServiceImpl @Autowired constructor(
    private val radarrCustomFormatRepository: RadarrCustomFormatRepository,
) : RadarrCustomFormatService {
    init {
        logger.info { "Initialize Radarr Custom Format Service" }
    }
    override suspend fun fetchAll(): List<CustomFormat> {
        logger.info { "RadarrCustomFormatService.fetchAll()" }
        return radarrCustomFormatRepository.fetchAll()
    }
    override suspend fun fetchById(trash_id: String): CustomFormat {
        logger.info { "RadarrCustomFormatService.fetchById($trash_id)" }
        return radarrCustomFormatRepository.fetchById(trash_id)
    }
}
