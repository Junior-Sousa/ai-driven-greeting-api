# ADR 0004: Container image packaging (Docker multi-stage)

## Status

Accepted.

## Context

The service must run reproducibly in containers with a **small attack surface** and **JDK/JRE separation**: compile with a full JDK; ship only a JRE-based runtime.

## Decision

1. **Multi-stage Dockerfile:** build stage **`eclipse-temurin:25-jdk-alpine`** runs `./mvnw package`; runtime stage **`eclipse-temurin:25-jre-alpine`** runs the fat JAR.
2. **Non-root:** runtime user **`app`** with fixed UID/GID **10001**.
3. **JVM in containers:** `JAVA_OPTS` includes **`-XX:+UseContainerSupport`**, **`-XX:MaxRAMPercentage`**, **`-XX:InitialRAMPercentage`**, **`-XX:+ExitOnOutOfMemoryError`**.
4. **Health tooling:** `curl` installed in the runtime image for Compose/Kubernetes-style **`HEALTHCHECK`** probes hitting **`/actuator/health/liveness`**.

## Consequences

### Positive

- Smaller image than JDK-at-runtime; aligns with security baseline (non-root, Temurin).

### Negative

- Alpine/musl considerations for native agents (acceptable for this reference scope).

## Related

- [ADR 0001](0001-stack-and-patterns.md)
- [`Dockerfile`](../../Dockerfile)
