package com.aidd.greeting.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Registers OpenAPI metadata for Springdoc/Swagger UI.
 *
 * <p>Runtime paths: {@code /v3/api-docs}, {@code /swagger-ui/index.html}.</p>
 */
@Configuration
public class OpenApiConfig {

    /**
     * @return root OpenAPI document description shown in Swagger UI
     */
    @Bean
    public OpenAPI greetingOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("AI-Driven Greeting API")
                        .version("1.0.0")
                        .description("Stateless greeting HTTP API. Public routes are versioned under /v1."));
    }
}
