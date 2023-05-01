package dev.garlicbread.trashApi.repository.customformat.radarr

import dev.garlicbread.trashApi.model.CustomFormat
import org.springframework.stereotype.Repository

@Repository
interface RadarrCustomFormatRepository {
    suspend fun fetchAll(): List<CustomFormat>
    suspend fun fetchById(trash_id: String): CustomFormat
}
