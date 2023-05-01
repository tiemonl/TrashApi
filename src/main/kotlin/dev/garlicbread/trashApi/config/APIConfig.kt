package dev.garlicbread.trashApi.config

import org.springframework.beans.factory.annotation.Value

class APIConfig {
    companion object {
        @Value("\${api.path}")
        const val API_PATH = "/api/v1/"

        @Value("\${api.version}")
        const val API_VERSION = "1.0"

        @Value("\${project.name}")
        const val PROJECT_NAME = "Trash REST API"

        @Value("\${spring.profiles.active}")
        const val PROFILE = "dev"
    }
}
