package com.aidd.greeting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Bootstrap class for the AI-Driven Greeting API Spring Boot application.
 *
 * <p>Configuration is externalised via {@code application.yml} and environment variables
 * (see {@code docs/} for operations and architecture).</p>
 */
@SpringBootApplication
public class GreetingApplication {

    /**
     * Program entry point.
     *
     * @param args standard Spring Boot command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(GreetingApplication.class, args);
    }
}
