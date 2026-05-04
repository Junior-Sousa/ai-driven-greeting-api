# ADR 0001: Stack and project patterns

## Status

Accepted.

## Context

We need a small but credible REST service that showcases **current Java and Spring practices**, remains easy to evolve with **AI-assisted coding**, and doubles as a **greenfield reference** for teams.

## Decision

We adopt:

| Choice | Rationale |
|--------|-----------|
| **Java 25 (LTS)** | Records, modern APIs, virtual threads; long-term vendor support. |
| **Spring Boot 3.4.x** | Mature ecosystem; native observability; validation and actuator integration. |
| **Constructor injection, records, RFC 7807** | Predictable structure for humans and automated refactoring tools. |
| **AI-directed governance** | Repository signals (`CLAUDE.md`, `.cursorrules`, `docs/`) encode constraints so agents preserve architecture. |

## Consequences

### Positive

- Readable, immutable-first codebase.
- Operational endpoints and metrics without bespoke glue.
- Documentation and code stay parallel when maintained as part of changes.

### Negative

- CI/CD and developer laptops must provide **JDK 25**.
- Dependency upgrades require periodic attention (Dependabot / renovate).

## Related documents

- [Architecture](../design/architecture.md)
- [Engineering standards](../design/engineering.md)
- [ADR 0002 — OpenAPI](0002-openapi-documentation.md)
- [ADR 0003 — Observability](0003-observability-logging-tracing.md)
- [ADR 0004 — Docker image](0004-container-image-docker.md)
