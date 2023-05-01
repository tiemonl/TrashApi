package dev.garlicbread.trashApi.exceptions.customformats.sonarr

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

sealed class SonarrCustomFormatException(message: String) : RuntimeException(message) {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    class SonarrCustomFormatNotFoundException(message: String) : SonarrCustomFormatException(message)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class SonarrCustomFormatBadRequestException(message: String) : SonarrCustomFormatException(message)
}
