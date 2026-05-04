package com.aidd.greeting.api;

import com.aidd.greeting.api.dto.GreetingRequest;
import com.aidd.greeting.api.dto.GreetingResponse;
import com.aidd.greeting.service.GreetingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * REST adapter for greeting operations under {@code /v1/greetings}.
 *
 * <p>Delegates business rules to {@link GreetingService}. POST bodies are validated
 * before the handler method runs; failures map to RFC 7807 via {@code GlobalExceptionHandler}.</p>
 */
@Tag(name = "Greetings", description = "Greeting API (version 1)")
@RestController
@RequestMapping("/v1/greetings")
public class GreetingController {

    private final GreetingService greetingService;

    /**
     * @param greetingService domain service (constructor-injected)
     */
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    /**
     * Returns a greeting using an optional {@code name} query parameter.
     * When absent or blank after trimming, the configured default name is used.
     *
     * @param name optional caller-provided name
     * @return greeting payload with message, timestamp, and environment label
     */
    @Operation(summary = "Get greeting", description = "Optional name; uses configured default when missing or blank.")
    @GetMapping
    public GreetingResponse getGreeting(
            @Parameter(description = "Name to greet", example = "Maria")
            @RequestParam(name = "name", required = false) String name) {
        return greetingService.greet(Optional.ofNullable(name));
    }

    /**
     * Returns a greeting for a validated JSON body ({@code name} required, max 50 characters).
     *
     * @param request validated request body
     * @return greeting payload with message, timestamp, and environment label
     */
    @Operation(summary = "Create greeting (validated)", description = "Requires non-blank name, max 50 characters.")
    @PostMapping
    public GreetingResponse postGreeting(@Valid @RequestBody GreetingRequest request) {
        return greetingService.greetWithValidatedName(request.name());
    }
}
