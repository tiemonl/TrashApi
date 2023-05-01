package dev.garlicbread.trashApi.service.customformats.sonarr

import dev.garlicbread.trashApi.model.CustomFormat
import dev.garlicbread.trashApi.repository.customformat.sonarr.SonarrCustomFormatRepository
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class SonarrCustomFormatServiceImpl @Autowired constructor(
    private val sonarrCustomFormatRepository: SonarrCustomFormatRepository,
) : SonarrCustomFormatService {
    init {
        logger.info { "Initialize Sonarr Custom Format Service" }
    }
    override suspend fun fetchAll(): List<CustomFormat> {
        logger.info { "SonarrCustomFormatService.fetchAll()" }
        return sonarrCustomFormatRepository.fetchAll()
    }
    override suspend fun fetchById(trash_id: String): CustomFormat {
        logger.info { "SonarrCustomFormatService.fetchById($trash_id)" }
        return sonarrCustomFormatRepository.fetchById(trash_id)
    }
}
