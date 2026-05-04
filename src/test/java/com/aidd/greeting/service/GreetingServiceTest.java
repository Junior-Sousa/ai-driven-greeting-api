package com.aidd.greeting.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link GreetingService} — no Spring context (see engineering test pyramid).
 */
class GreetingServiceTest {

    private static final String DEFAULT = "Guest";
    private static final String ENV = "test-env";

    private final GreetingService service = new GreetingService(DEFAULT, ENV);

    @Nested
    @DisplayName("greet(Optional)")
    class GreetOptional {

        @Test
        @DisplayName("uses provided name when present")
        void usesProvidedName() {
            var r = service.greet(Optional.of("Alice"));
            assertThat(r.message()).isEqualTo("Olá, Alice!");
            assertThat(r.environment()).isEqualTo(ENV);
            assertThat(r.timestamp()).isNotNull();
        }

        @Test
        @DisplayName("uses default when optional empty")
        void usesDefaultWhenEmpty() {
            var r = service.greet(Optional.empty());
            assertThat(r.message()).isEqualTo("Olá, " + DEFAULT + "!");
        }

        @Test
        @DisplayName("uses default when blank string")
        void usesDefaultWhenBlank() {
            var r = service.greet(Optional.of("   "));
            assertThat(r.message()).isEqualTo("Olá, " + DEFAULT + "!");
        }

        @Test
        @DisplayName("trims surrounding whitespace")
        void trimsWhitespace() {
            var r = service.greet(Optional.of("  Bob  "));
            assertThat(r.message()).isEqualTo("Olá, Bob!");
        }
    }

    @Nested
    @DisplayName("greetWithValidatedName")
    class GreetValidated {

        @Test
        @DisplayName("trims validated name")
        void trimsName() {
            var r = service.greetWithValidatedName("  Carol  ");
            assertThat(r.message()).isEqualTo("Olá, Carol!");
        }
    }
}
