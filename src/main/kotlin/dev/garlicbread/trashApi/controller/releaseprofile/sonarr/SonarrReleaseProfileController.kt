package dev.garlicbread.trashApi.controller.releaseprofile.sonarr

import dev.garlicbread.trashApi.config.APIConfig
import dev.garlicbread.trashApi.exceptions.quality.sonarr.SonarrReleaseProfileException.SonarrReleaseProfileBadRequestException
import dev.garlicbread.trashApi.exceptions.quality.sonarr.SonarrReleaseProfileException.SonarrReleaseProfileNotFoundException
import dev.garlicbread.trashApi.model.ReleaseProfile
import dev.garlicbread.trashApi.service.releaseprofile.sonarr.SonarrReleaseProfileService
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
class SonarrReleaseProfileController @Autowired constructor(
    private val sonarrReleaseProfileService: SonarrReleaseProfileService,
) {
    @RequestMapping(
        value = ["release-profiles"],
        method = [RequestMethod.GET],
        produces = ["application/json"],
    )
    suspend fun getAllSonarrReleaseProfiles(): ResponseEntity<List<ReleaseProfile>> {
        val response = sonarrReleaseProfileService.fetchAll()
        return ResponseEntity.ok(response)
    }

    @RequestMapping(
        value = ["release-profiles/{trash_id}"],
        method = [RequestMethod.GET],
        produces = ["application/json"],
    )
    suspend fun getSingleSonarrReleaseProfile(@PathVariable trash_id: String): ResponseEntity<ReleaseProfile> {
        try {
            return ResponseEntity.ok(sonarrReleaseProfileService.fetchById(trash_id))
        } catch (error: SonarrReleaseProfileNotFoundException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, error.message)
        } catch (error: SonarrReleaseProfileBadRequestException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, error.message)
        }
    }
}
