# Engineering standards and AI-driven development (AIDD)

## 1. TDD workflow

This project uses **Test-Driven Development (TDD)** as the default workflow for new behaviour:

1. **RED:** Write a failing test — unit (`GreetingService`) and/or web-layer integration (`GreetingController`) — that expresses the contract or business rule.
2. **GREEN:** Implement the minimum code to pass.
3. **REFACTOR:** Improve readability and structure while keeping tests green.

> **Rule for coding agents:** Do not introduce domain logic without a corresponding test that defines or guards it.

## 2. Java and Spring conventions (Java 25)

- **Immutability:** Prefer **`record`** for DTOs and stable value objects.
- **Dependency injection:** **Constructor injection only** — no `@Autowired` on fields.
- **Visibility:** Prefer `private` or package-private members unless a broader scope is required.
- **Single responsibility:** Short methods; descriptive names.

## 3. Test pyramid

| Layer | Focus | Tools |
|-------|--------|-------|
| **Unit (base)** | `GreetingService` rules — fast, **no** Spring context | JUnit 5, plain instantiation or Mockito if needed |
| **Integration (middle)** | `GreetingController` — JSON, status codes, validation, filters | `@SpringBootTest` + `MockMvc` (or slice tests where appropriate) |
| **Smoke (top)** | Container or process “is alive” | `curl` against health and a sample API call; optional CI job |

## 4. Errors and logging

- **Global handler:** Use `@RestControllerAdvice` for API error mapping.
- **HTTP errors:** Follow **RFC 7807** (*Problem Details for HTTP APIs*).
- **Logging:** Use SLF4J with **placeholders** — e.g. `log.info("Greeting for {}", name);` — never string concatenation of untrusted input in log messages.

## 5. API rules

- **Versioning:** All public routes live under **`/v1/`**.
- **HTTP semantics:** `GET` for read-like, idempotent operations; **400** for request validation failures.
- **Documentation:** OpenAPI (Swagger UI) for discoverability; **Javadoc** on public types and non-trivial methods.

## 6. Docker baseline

- **Multi-stage build:** Compile with JDK image; run with **JRE** image.
- **Non-root:** The runtime container must not execute as `root`.
- **Resources:** Set CPU/memory limits appropriate to the deployment environment (compose, Kubernetes, etc.).

---

## Technical standards (staff-level baseline)

### Performance and concurrency

- **Virtual threads:** Prefer virtual threads for the servlet stack when supported — `spring.threads.virtual.enabled=true`.

### Validation and errors

- **Input validation:** Use **`jakarta.validation`** on request models.
- **Error bodies:** **RFC 7807** `ProblemDetail` (or equivalent) for machine-readable errors.

### Resilience and operations

- **Graceful shutdown:** `server.shutdown=graceful` with a sensible per-phase timeout.
- **Actuator:** Expose **`health`**, **`metrics`**, and **`prometheus`** as required by operations.
