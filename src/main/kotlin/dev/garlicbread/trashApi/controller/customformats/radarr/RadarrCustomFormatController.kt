package dev.garlicbread.trashApi.controller.customformats.radarr

import dev.garlicbread.trashApi.config.APIConfig
import dev.garlicbread.trashApi.exceptions.customformats.radarr.RadarrCustomFormatException.RadarrCustomFormatBadRequestException
import dev.garlicbread.trashApi.exceptions.customformats.radarr.RadarrCustomFormatException.RadarrCustomFormatNotFoundException
import dev.garlicbread.trashApi.model.CustomFormat
import dev.garlicbread.trashApi.service.customformats.radarr.RadarrCustomFormatService
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
class RadarrCustomFormatController @Autowired constructor(
    private val radarrCustomFormatService: RadarrCustomFormatService,
) {
    @RequestMapping(
        value = ["custom-formats"],
        method = [RequestMethod.GET],
        produces = ["application/json"],
    )
    suspend fun getAllRadarrCustomFormats(): ResponseEntity<List<CustomFormat>> {
        val response = radarrCustomFormatService.fetchAll()
        return ResponseEntity.ok(response)
    }

    @RequestMapping(
        value = ["custom-formats/{trash_id}"],
        method = [RequestMethod.GET],
        produces = ["application/json"],
    )
    suspend fun getSingleRadarrCustomFormat(@PathVariable trash_id: String): ResponseEntity<CustomFormat> {
        try {
            return ResponseEntity.ok(radarrCustomFormatService.fetchById(trash_id))
        } catch (error: RadarrCustomFormatNotFoundException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, error.message)
        } catch (error: RadarrCustomFormatBadRequestException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, error.message)
        }
    }
}
