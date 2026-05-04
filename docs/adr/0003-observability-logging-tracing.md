# ADR 0003: Observability — structured logs and Micrometer tracing

## Status

Accepted.

## Context

Operators need **machine-readable logs**, **metrics**, and **health probes** compatible with Kubernetes and central observability stacks. Correlation IDs simplify debugging across services.

## Decision

1. **Logs:** `logback-spring.xml` uses **Logstash JSON encoder** so console logs are one JSON object per line (ELK/Loki-friendly).
2. **Tracing:** Add **`micrometer-tracing-bridge-brave`** so HTTP observations can populate trace context; **`traceId` / `spanId`** appear in MDC when active.
3. **Metrics & health:** Spring Boot **Actuator** with **`health`**, **`metrics`**, **`prometheus`** exposed per engineering baseline; **liveness** probe path enabled via health groups.

## Consequences

### Positive

- Consistent operational footprint; Prometheus scrape without custom code.

### Negative

- Additional dependencies and configuration to tune sampling in production.

## Related

- [Observability](../ops/observability.md)
- [ADR 0001](0001-stack-and-patterns.md)
