package dev.garlicbread.trashApi.controller.quality.radarr

import dev.garlicbread.trashApi.config.APIConfig
import dev.garlicbread.trashApi.exceptions.customformats.radarr.RadarrQualityException.*
import dev.garlicbread.trashApi.model.QualitySize
import dev.garlicbread.trashApi.service.quality.radarr.RadarrQualityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping(APIConfig.API_PATH + "radarr")
class RadarrQualityController @Autowired constructor(
    private val radarrQualityService: RadarrQualityService,
) {
    @RequestMapping(
        value = ["quality"],
        method = [RequestMethod.GET],
        produces = ["application/json"],
    )
    suspend fun getAllRadarrQualitySizes(): ResponseEntity<List<QualitySize>> {
        val response = radarrQualityService.fetchAll()
        return ResponseEntity.ok(response)
    }

    @RequestMapping(
        value = ["quality/{trash_id}"],
        method = [RequestMethod.GET],
        produces = ["application/json"],
    )
    suspend fun getSingleRadarrQualitySize(@PathVariable trash_id: String): ResponseEntity<QualitySize> {
        try {
            return ResponseEntity.ok(radarrQualityService.fetchById(trash_id))
        } catch (error: RadarrQualityBadRequestException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, error.message)
        } catch (error: RadarrQualityNotFoundException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, error.message)
        }
    }
}
