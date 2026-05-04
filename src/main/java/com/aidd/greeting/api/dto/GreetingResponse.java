package com.aidd.greeting.api.dto;

import java.time.Instant;

/**
 * Successful greeting payload returned by both GET and POST {@code /v1/greetings}.
 *
 * @param message   human-readable greeting (includes the resolved name)
 * @param timestamp instant when the greeting was generated (UTC)
 * @param environment deployment or profile label (non-secret)
 */
public record GreetingResponse(
        String message,
        Instant timestamp,
        String environment
) {
}
