# Documentation index

This folder is the **canonical design and governance source** for the **AI-Driven Greeting API**. It is structured as a **reference blueprint** for small greenfield services built with **human + AI-assisted development** (IDE agents, coding assistants).

Read documents in the order below for onboarding, or jump by role.

| Order | Document | Purpose |
|------|------------|---------|
| 1 | [Business / PRD](business/prd-greeting.md) | Product intent, functional and non-functional requirements. |
| 2 | [Architecture (HLD)](design/architecture.md) | Technical vision, quality attributes, and links to views. |
| 3 | [C4 model](design/c4-model.md) | Context, container, and component views (C4 levels 1–3). |
| 4 | [Sequence diagrams](design/sequence-diagrams.md) | Request flows: happy paths and validation errors. |
| 5 | [Feature design (FDD)](design/fdd-greeting-service.md) | API contracts, domain rules, and implementation mapping. |
| 6 | [Engineering standards](design/engineering.md) | TDD, test pyramid, Java/Spring conventions, Docker and ops baselines. |
| 7 | [Security](design/security.md) | Threat model, input handling, and runtime hardening. |
| 8 | [Observability](ops/observability.md) | Health, metrics, logs, tracing context. |
| 9 | Architecture decisions (ADRs) | [0001 stack](adr/0001-stack-and-patterns.md) · [0002 OpenAPI](adr/0002-openapi-documentation.md) · [0003 observability](adr/0003-observability-logging-tracing.md) · [0004 Docker](adr/0004-container-image-docker.md) |
| 10 | [AI governance](ai/governance.md) | How repo signals (`CLAUDE.md`, `.cursorrules`, this tree) steer coding agents. |

## Diagrams

All diagrams use **Mermaid** so they render in GitHub, GitLab, and most Markdown tooling.

## Conventions

- **Language:** Documentation is **English** so teams and tools share one vocabulary.
- **Versioning:** Public HTTP APIs use the `/v1/` prefix; documents reference the same paths.
- **Truth hierarchy:** Runtime behaviour is authoritative; when docs drift, **fix docs or code** in the same change.
