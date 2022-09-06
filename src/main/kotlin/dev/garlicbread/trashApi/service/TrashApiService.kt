package dev.garlicbread.trashApi.service

import dev.garlicbread.trashApi.model.CustomFormat
import dev.garlicbread.trashApi.model.QualitySize
import dev.garlicbread.trashApi.model.ReleaseProfile
import dev.garlicbread.trashApi.singleton.Configuration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TrashApiService @Autowired constructor() {
    fun getAllRadarrQualityProfiles(): List<QualitySize> = Configuration.radarrQualitySizes

    fun getRadarrQualityProfile(trash_id: String): QualitySize = Configuration.radarrQualitySizes.first { it.trash_id.equals(trash_id, ignoreCase = true) }

    fun getAllRadarrCustomFormats(): List<CustomFormat> = Configuration.radarrCustomFormats

    fun getRadarrCustomFormat(trash_id: String): CustomFormat = Configuration.radarrCustomFormats.first { it.trash_id.equals(trash_id, ignoreCase = true) }

    fun getAllSonarrQualityProfiles(): List<QualitySize> = Configuration.sonarrQualitySizes

    fun getSonarrQualityProfile(trash_id: String): QualitySize = Configuration.sonarrQualitySizes.first { it.trash_id.equals(trash_id, ignoreCase = true) }

    fun getAllSonarrCustomFormats(): List<CustomFormat> = Configuration.sonarrCustomFormats

    fun getSonarrCustomFormat(trash_id: String): CustomFormat = Configuration.sonarrCustomFormats.first { it.trash_id.equals(trash_id, ignoreCase = true) }

    fun getAllSonarrReleaseProfiles(): List<ReleaseProfile> = Configuration.sonarrReleaseProfiles

    fun getSonarrReleaseProfile(trash_id: String): ReleaseProfile = Configuration.sonarrReleaseProfiles.first { it.trash_id.equals(trash_id, ignoreCase = true) }
}
