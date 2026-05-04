# CLAUDE.md — Operational context

Full design and governance live under **`docs/`** — start at [`docs/README.md`](docs/README.md).

## Tech Stack
- Runtime: Java 25 (LTS)
- Framework: Spring Boot 3.4.x
- Build: Maven 3.9+
- OS: macOS Sonoma (Docker via Colima + VirtioFS)

## Build & Test Commands
- Compile: `./mvnw clean compile`
- Package: `./mvnw package -DskipTests`
- Run Tests: `./mvnw test`
- Full build (tests + JAR): `./mvnw verify`
- Docker: `docker compose up --build`

## Architecture Constraints
- Use **Java Records** for all Data Transfer Objects (DTOs).
- Use **Constructor Injection** (no @Autowired on fields).
- Endpoints must follow the `/v1/` versioning pattern.
- Error handling must be global via `@RestControllerAdvice`.
