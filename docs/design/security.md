# Security design and threat model

## 1. Overview

This document captures security expectations for the **AI-Driven Greeting API**: a minimal REST surface with **no authentication** in the reference implementation (add auth at the gateway or service mesh for production).

## 2. Threat model (summary)

| Threat | Impact | Mitigation |
|--------|--------|------------|
| **Log injection** | Forged newlines or control characters in logs that confuse parsers or operators. | Validate input length; use structured logging and **parameterized** log messages; avoid logging raw user strings without policy. |
| **XSS via JSON** | Script injection if a browser incorrectly interprets response as HTML. | Responses are **`application/json`**; clients must not treat API output as HTML. |
| **Denial of service** | Exhaust CPU/memory or threads. | Container resource limits; validation (`@Size` on POST); optional rate limiting at edge (future). |
| **Information disclosure** | Environment details or stack traces leak to clients. | Global exception handling returns **RFC 7807** bodies **without** stack traces; tune Actuator exposure for production. |

## 3. DevSecOps

### Secrets

- **No secrets in Git:** credentials belong in secret stores or environment injection at deploy time.

### Dependencies

- Use **SCA** (e.g. Dependabot) for Maven dependencies.
- Prefer **minimal runtime images** (JRE, Alpine or distroless where appropriate) and official base images (e.g. Eclipse Temurin).

## 4. Docker runtime

| Control | Target |
|---------|--------|
| **Non-root** | Process runs as a **dedicated UID** (reference Dockerfile uses **10001**, not root). |
| **Read-only root** | Prefer read-only filesystem where the platform allows; writable tmp only if needed. |
| **Network** | Expose only **8080** (or the chosen port). The reference [`docker-compose.yml`](../../docker-compose.yml) attaches the app to a **dedicated bridge network** (`ai-driven-greeting-net`) for isolation from the default bridge. |
| **Resources** | The same compose file sets **memory and CPU limits** on the service (`mem_limit`, `cpus`) to contain resource abuse; tune for your environment. **Swarm / Kubernetes** should set equivalent `resources` in manifests. |

## 5. Input handling

| Surface | Constraints |
|---------|-------------|
| **POST body `name`** | `@NotBlank`, `@Size(max = 50)` — see `GreetingRequest`. |
| **GET query `name`** | Optional; trimmed; empty uses default. **Length** for GET is not enforced in the reference code — harden with max query length or reject oversized values if required by policy. |
| **Alphanumeric-only names** | Not enforced in the reference build; add `@Pattern` or normalisation if product security requires it. |

## 6. Vulnerability disclosure

Report security issues **privately** to maintainers (private issue or agreed channel) before public disclosure.
