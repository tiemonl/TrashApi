package dev.garlicbread.trashApi.exceptions.customformats.radarr

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

sealed class RadarrCustomFormatException(message: String) : RuntimeException(message) {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    class RadarrCustomFormatNotFoundException(message: String) : RadarrQualityException(message)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class RadarrCustomFormatBadRequestException(message: String) : RadarrQualityException(message)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class RadarrCustomFormatConflictIntegrityException(message: String) : RadarrQualityException(message)
}
