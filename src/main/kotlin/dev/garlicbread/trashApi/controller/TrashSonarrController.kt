package dev.garlicbread.trashApi.controller

import dev.garlicbread.trashApi.model.CustomFormat
import dev.garlicbread.trashApi.model.QualitySize
import dev.garlicbread.trashApi.model.ReleaseProfile
import dev.garlicbread.trashApi.service.TrashApiService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/sonarr/")
class TrashSonarrController @Autowired constructor(
    private val trashApiService: TrashApiService
) {

    @RequestMapping(
        value = ["qualities"],
        method = [RequestMethod.GET],
        produces = ["application/json"]
    )
    suspend fun getAllSonarrQualities(): List<QualitySize> =
        trashApiService.getAllSonarrQualityProfiles()

    @RequestMapping(
        value = ["qualities/{trash_id}"],
        method = [RequestMethod.GET],
        produces = ["application/json"]
    )
    fun getSonarrQuality(@PathVariable trash_id: String) = trashApiService.getSonarrQualityProfile(trash_id)

    @RequestMapping(
        value = ["custom-formats"],
        method = [RequestMethod.GET],
        produces = ["application/json"]
    )
    suspend fun getAllSonarrCustomFormats(): List<CustomFormat> =
        trashApiService.getAllSonarrCustomFormats()

    @RequestMapping(
        value = ["custom-formats/{trash_id}"],
        method = [RequestMethod.GET],
        produces = ["application/json"]
    )
    fun getSonarrCustomFormat(@PathVariable trash_id: String) = trashApiService.getSonarrCustomFormat(trash_id)

    @RequestMapping(
        value = ["release-profiles"],
        method = [RequestMethod.GET],
        produces = ["application/json"]
    )
    suspend fun getAllSonarrReleaseProfiles(): List<ReleaseProfile> =
        trashApiService.getAllSonarrReleaseProfiles()

    @RequestMapping(
        value = ["release-profiles/{trash_id}"],
        method = [RequestMethod.GET],
        produces = ["application/json"]
    )
    fun getSonarrReleaseProfile(@PathVariable trash_id: String) = trashApiService.getSonarrReleaseProfile(trash_id)
}
