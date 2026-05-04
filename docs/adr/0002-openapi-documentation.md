# ADR 0002: OpenAPI and Swagger UI (springdoc-openapi)

## Status

Accepted.

## Context

Consumers and reviewers need a discoverable, version-controlled description of HTTP endpoints without maintaining duplicate manuals.

## Decision

Use **`springdoc-openapi-starter-webmvc-ui`** to expose:

- **OpenAPI 3** JSON at `/v3/api-docs`
- **Swagger UI** at `/swagger-ui` (index under `/swagger-ui/index.html`)

Programmatic metadata is registered via `OpenApiConfig`; controllers use OpenAPI annotations where helpful.

## Consequences

### Positive

- Single source of truth aligned with running code; try-it-out flows in Swagger UI.

### Negative

- Extra dependency surface; production deployments should restrict UI exposure if policy requires (gateway auth, network policy).

## Related

- [ADR 0001](0001-stack-and-patterns.md)
