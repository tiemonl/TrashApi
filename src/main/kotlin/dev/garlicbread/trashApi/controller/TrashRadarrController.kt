package dev.garlicbread.trashApi.controller

import dev.garlicbread.trashApi.model.CustomFormat
import dev.garlicbread.trashApi.model.QualitySize
import dev.garlicbread.trashApi.service.TrashApiService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/radarr")
class TrashRadarrController @Autowired constructor(
    private val trashApiService: TrashApiService
) {
    @RequestMapping(
        value = ["qualities"],
        method = [RequestMethod.GET],
        produces = ["application/json"]
    )
    suspend fun getAllRadarrQualities(): List<QualitySize> =
        trashApiService.getAllRadarrQualityProfiles()

    @RequestMapping(
        value = ["qualities/{trash_id}"],
        method = [RequestMethod.GET],
        produces = ["application/json"]
    )
    fun getRadarrQuality(@PathVariable trash_id: String) = trashApiService.getRadarrQualityProfile(trash_id)

    @RequestMapping(
        value = ["custom-formats"],
        method = [RequestMethod.GET],
        produces = ["application/json"]
    )
    suspend fun getAllRadarrCustomFormats(): List<CustomFormat> =
        trashApiService.getAllRadarrCustomFormats()

    @RequestMapping(
        value = ["custom-formats/{trash_id}"],
        method = [RequestMethod.GET],
        produces = ["application/json"]
    )
    fun getRadarrCustomFormat(@PathVariable trash_id: String) = trashApiService.getRadarrCustomFormat(trash_id)
}