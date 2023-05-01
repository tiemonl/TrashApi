package dev.garlicbread.trashApi.exceptions.quality.sonarr

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

sealed class SonarrQualityException(message: String) : RuntimeException(message) {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    class SonarrQualityNotFoundException(message: String) : SonarrReleaseProfileException(message)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class SonarrQualityBadRequestException(message: String) : SonarrReleaseProfileException(message)
}
