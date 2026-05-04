# AI governance and repository signals

This project is designed for **human + agent** development. Besides normal code review, we use **machine-readable and human-readable signals** so coding assistants (IDE agents, chat tools, CI bots) stay aligned with architecture.

## 1. Signal layers

| Layer | Role |
|-------|------|
| **`docs/`** | Canonical design: PRD, architecture, C4, sequences, FDD, engineering rules, security, observability, ADRs. **Agents should read these before large refactors.** |
| **`.github/workflows`** | CI runs `./mvnw verify` on pushes and PRs to `main`/`master`; keep the workflow green when changing build or tests. |
| **Dependabot** | `.github/dependabot.yml` proposes Maven and GitHub Actions updates — review for compatibility with Java 25 and Spring Boot. |
| **`CLAUDE.md`** | Short operational context: stack, Maven commands, non-negotiable constraints (records, constructor injection, `/v1/`, global exception handling). |
| **`.cursorrules`** | Editor-specific guardrails; keep them short and stable; link or mirror critical rules from `docs/design/engineering.md`. |

## 2. Recommended workflow for agents

1. Read **`docs/README.md`** for the documentation map.
2. For API or behaviour changes, read **`docs/design/fdd-greeting-service.md`** and **`docs/business/prd-greeting.md`**.
3. For structural changes, check **`docs/design/architecture.md`**, **`docs/design/c4-model.md`**, and update diagrams if boundaries move.
4. Follow **`docs/design/engineering.md`**: tests first for new logic, pyramid distribution.
5. Update **OpenAPI** surfaces (`OpenApiConfig`, controller annotations) when contracts change.

## 3. Keeping docs truthful

When behaviour changes, update in the **same change**:

- FDD / sequences / C4 if boundaries or flows change.
- ADR if a **new** architectural decision is made (do not rewrite history of accepted ADRs — add a new ADR).

## 4. Out of scope for automation

Secrets, production URLs, and credentials **never** belong in these files. Use environment-specific configuration and secret stores.
