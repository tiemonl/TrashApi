package dev.garlicbread.trashApi.controller.customformats.sonarr

import dev.garlicbread.trashApi.config.APIConfig
import dev.garlicbread.trashApi.exceptions.customformats.sonarr.SonarrCustomFormatException.SonarrCustomFormatBadRequestException
import dev.garlicbread.trashApi.exceptions.customformats.sonarr.SonarrCustomFormatException.SonarrCustomFormatNotFoundException
import dev.garlicbread.trashApi.model.CustomFormat
import dev.garlicbread.trashApi.service.customformats.sonarr.SonarrCustomFormatService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
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
@Tag(name = "Sonarr Custom Format", description = "Custom formats within sonarr")
class SonarrCustomFormatController @Autowired constructor(
    private val sonarrCustomFormatService: SonarrCustomFormatService,
) {
    @Operation(summary = "Get all custom formats")
    @RequestMapping(
        value = ["custom-formats"],
        method = [RequestMethod.GET],
        produces = ["application/json"],
    )
    suspend fun getAllSonarrCustomFormats(): ResponseEntity<List<CustomFormat>> {
        val response = sonarrCustomFormatService.fetchAll()
        return ResponseEntity.ok(response)
    }

    @Operation(summary = "Get a single custom format with a trash_id")
    @RequestMapping(
        value = ["custom-formats/{trash_id}"],
        method = [RequestMethod.GET],
        produces = ["application/json"],
    )
    suspend fun getSingleSonarrCustomFormat(@PathVariable trash_id: String): ResponseEntity<CustomFormat> {
        try {
            return ResponseEntity.ok(sonarrCustomFormatService.fetchById(trash_id))
        } catch (error: SonarrCustomFormatNotFoundException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, error.message)
        } catch (error: SonarrCustomFormatBadRequestException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, error.message)
        }
    }
}
