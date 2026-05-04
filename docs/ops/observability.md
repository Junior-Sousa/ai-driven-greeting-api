# Observability and metrics

## 1. Health checks

Spring Boot **Actuator** exposes health endpoints.

| Probe | Path | Purpose |
|-------|------|---------|
| **Liveness** | `/actuator/health/liveness` | Process should be restarted? |
| **Readiness** | `/actuator/health/readiness` | Accept traffic? |

Kubernetes-style probe groups are enabled when **`management.endpoint.health.probes.enabled=true`**.

## 2. Metrics (Micrometer)

- **Prometheus scrape:** `/actuator/prometheus`
- **RED-style signals (examples):**
  - **Rate:** HTTP server request counts / throughput.
  - **Errors:** 5xx rates from HTTP metrics.
  - **Duration:** Latency histograms for request handling.

## 3. Structured logs

- Application logs are emitted as **JSON** (Logback + Logstash encoder) for ingestion into ELK, Loki, CloudWatch, etc.
- When **Micrometer Tracing** is active, **trace** and **span** identifiers may appear in the MDC and therefore in log fields (e.g. `traceId`, `spanId`).

## 4. API discovery

- **OpenAPI:** `/v3/api-docs`
- **Swagger UI:** `/swagger-ui/index.html`

Keep actuator and docs exposure aligned with your production security model (gateway auth, network policies).
