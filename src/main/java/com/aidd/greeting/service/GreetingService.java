package com.aidd.greeting.service;

import com.aidd.greeting.api.dto.GreetingResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

/**
 * Application service implementing greeting rules: optional-name resolution,
 * trimming, default fallback for GET, and message construction.
 *
 * <p>Pure-enough for unit testing without Spring by constructing with explicit
 * {@code defaultName} and {@code environment} values.</p>
 */
@Service
public class GreetingService {

    private final String defaultName;
    private final String environment;

    /**
     * @param defaultName value of {@code greeting.default-name} when GET input is unusable
     * @param environment value of {@code greeting.environment} (or default {@code local}) for responses
     */
    public GreetingService(
            @Value("${greeting.default-name}") String defaultName,
            @Value("${greeting.environment:local}") String environment) {
        this.defaultName = defaultName;
        this.environment = environment;
    }

    /**
     * Builds a greeting when the name may be absent or blank (GET flow).
     *
     * @param name optional raw name from the caller
     * @return response including generated message and metadata
     */
    public GreetingResponse greet(Optional<String> name) {
        String resolved = name
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .orElse(defaultName);
        return buildResponse(resolved);
    }

    /**
     * Builds a greeting when the name has already passed bean validation (POST flow).
     *
     * @param name non-null validated name (may still be trimmed)
     * @return response including generated message and metadata
     */
    public GreetingResponse greetWithValidatedName(String name) {
        return buildResponse(name.trim());
    }

    private GreetingResponse buildResponse(String name) {
        return new GreetingResponse(
                "Olá, " + name + "!",
                Instant.now(),
                environment
        );
    }
}
