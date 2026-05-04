package com.aidd.greeting.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Request payload for {@code POST /v1/greetings}.
 *
 * @param name caller name; must not be blank and must not exceed 50 characters
 */
@Schema(name = "GreetingRequest", description = "Validated body for POST greeting.")
public record GreetingRequest(
        @NotBlank(message = "name must not be blank")
        @Size(max = 50, message = "name must be at most 50 characters")
        @Schema(description = "Name to greet", example = "Ana", maxLength = 50, requiredMode = Schema.RequiredMode.REQUIRED)
        String name
) {
}
