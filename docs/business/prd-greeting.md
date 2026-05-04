# Product requirements document (PRD): Greeting service API

**Status:** Reference / greenfield  
**Goal:** Provide a low-latency, highly testable HTTP API for personalised greetings, serving as a **baseline product slice** for AI-assisted delivery.

## Functional requirements

| ID | Requirement |
|----|----------------|
| **FR-001** | The system accepts an optional **name** via query parameter `name` on **GET** `/v1/greetings`. |
| **FR-002** | The system accepts a **required validated name** in the JSON body on **POST** `/v1/greetings`. |
| **FR-003** | Success responses return JSON containing **message**, **timestamp** (UTC), and **environment** label. The resolved name appears inside **message** (e.g. `Olá, {name}!`). |
| **FR-004** | If no usable name is provided for **GET** (missing or blank), use the configured default (**Guest** unless overridden). |

## Non-functional requirements

| ID | Requirement |
|----|----------------|
| **NFR-001 Performance** | In-process greeting generation is **in-memory**; target sub-millisecond CPU work (excluding network and JVM warmup). |
| **NFR-002 Security** | Inputs validated on POST; logs must not concatenate unsanitised user input into log messages; follow [security design](../design/security.md). |
| **NFR-003 Observability** | Structured JSON logs and metrics/tracing hooks compatible with centralised observability stacks — see [observability](../ops/observability.md). |

## Out of scope (reference project)

- Authentication and authorisation.
- Persistence and multi-tenant data.
- Internationalisation beyond the reference message format.
