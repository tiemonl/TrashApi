package dev.garlicbread.trashApi.config.cors

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfig {
    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/api/v1/**")
                    .allowedOrigins("http://localhost:4444")
                    .allowedHeaders("*")
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .maxAge(3600)
            }
        }
    }
}
