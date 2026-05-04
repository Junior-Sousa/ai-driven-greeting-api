# AI-Driven Greeting API

Reference **Spring Boot 3** service on **Java 25**: stateless greetings under **`/v1/`**, validation, **RFC 7807** errors, Actuator, structured JSON logs, OpenAPI (Swagger UI), Docker multi-stage build.

## Documentation

Start here: **[`docs/README.md`](docs/README.md)** — product intent, **C4**, **sequence diagrams**, engineering standards, security, observability, ADRs, and AI governance.

## Quick start

```bash
./mvnw test
./mvnw spring-boot:run
```

- API base: `http://localhost:8080/v1/greetings`
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- Health (liveness): `http://localhost:8080/actuator/health/liveness`

## Docker

```bash
docker compose up --build
```

See [`Dockerfile`](Dockerfile) and [`docker-compose.yml`](docker-compose.yml) (dedicated network and resource limits for the reference stack).

## Continuous integration

GitHub Actions runs `./mvnw verify` on push/PR to `main` / `master` (see [`.github/workflows/ci.yml`](.github/workflows/ci.yml)). Dependabot is enabled for Maven and Actions (see [`.github/dependabot.yml`](.github/dependabot.yml)).

## Agent / IDE context

Short constraints for tools: [`CLAUDE.md`](CLAUDE.md).
