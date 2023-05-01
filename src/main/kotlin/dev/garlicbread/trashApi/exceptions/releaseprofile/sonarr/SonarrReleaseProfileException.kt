package dev.garlicbread.trashApi.exceptions.quality.sonarr

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

sealed class SonarrReleaseProfileException(message: String) : RuntimeException(message) {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    class SonarrReleaseProfileNotFoundException(message: String) : SonarrReleaseProfileException(message)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class SonarrReleaseProfileBadRequestException(message: String) : SonarrReleaseProfileException(message)
}
