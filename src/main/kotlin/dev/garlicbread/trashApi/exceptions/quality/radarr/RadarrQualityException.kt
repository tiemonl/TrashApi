package dev.garlicbread.trashApi.exceptions.customformats.radarr

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

sealed class RadarrQualityException(message: String) : RuntimeException(message) {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    class RadarrQualityNotFoundException(message: String) : RadarrQualityException(message)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class RadarrQualityBadRequestException(message: String) : RadarrQualityException(message)
}
