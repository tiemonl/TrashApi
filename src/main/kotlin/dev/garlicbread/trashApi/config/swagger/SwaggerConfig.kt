package dev.garlicbread.trashApi.config.swagger

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

// https://springdoc.org/v2/#Introduction
// https://stackoverflow.com/questions/74614369/how-to-run-swagger-3-on-spring-boot-3
// http://localhost:XXXX/swagger-ui/index.html
@Configuration
class SwaggerConfig {
    @Bean
    fun apiInfo(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Trash API Rest Service")
                    .description("Trash Guides API to get json config 2023")
                    .version("v1.0.0")
                    .license(
                        License()
                            .name("MIT License"),
                    ),
            )
    }
}
