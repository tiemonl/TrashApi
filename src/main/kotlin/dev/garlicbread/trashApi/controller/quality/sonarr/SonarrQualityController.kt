package dev.garlicbread.trashApi.controller.quality.sonarr

import dev.garlicbread.trashApi.config.APIConfig
import dev.garlicbread.trashApi.exceptions.quality.sonarr.SonarrQualityException.SonarrQualityBadRequestException
import dev.garlicbread.trashApi.exceptions.quality.sonarr.SonarrQualityException.SonarrQualityNotFoundException
import dev.garlicbread.trashApi.model.QualitySize
import dev.garlicbread.trashApi.service.quality.sonarr.SonarrQualityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping(APIConfig.API_PATH + "sonarr")
class SonarrQualityController @Autowired constructor(
    private val sonarrQualityService: SonarrQualityService,
) {
    @RequestMapping(
        value = ["quality"],
        method = [RequestMethod.GET],
        produces = ["application/json"],
    )
    suspend fun getAllSonarrQualitySizes(): ResponseEntity<List<QualitySize>> {
        val response = sonarrQualityService.fetchAll()
        return ResponseEntity.ok(response)
    }

    @RequestMapping(
        value = ["quality/{trash_id}"],
        method = [RequestMethod.GET],
        produces = ["application/json"],
    )
    suspend fun getSingleSonarrQualitySize(@PathVariable trash_id: String): ResponseEntity<QualitySize> {
        try {
            return ResponseEntity.ok(sonarrQualityService.fetchById(trash_id))
        } catch (error: SonarrQualityNotFoundException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, error.message)
        } catch (error: SonarrQualityBadRequestException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, error.message)
        }
    }
}
