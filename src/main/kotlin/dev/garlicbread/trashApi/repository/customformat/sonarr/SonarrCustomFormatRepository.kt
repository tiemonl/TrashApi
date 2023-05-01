package dev.garlicbread.trashApi.repository.customformat.sonarr

import dev.garlicbread.trashApi.model.CustomFormat
import org.springframework.stereotype.Repository

@Repository
interface SonarrCustomFormatRepository {
    suspend fun fetchAll(): List<CustomFormat>
    suspend fun fetchById(trash_id: String): CustomFormat
}
