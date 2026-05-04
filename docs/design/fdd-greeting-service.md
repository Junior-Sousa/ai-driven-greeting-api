# Feature design document (FDD): Greeting service

## 1. Overview

Stateless service that accepts a **name** (optional or validated, depending on operation) and returns a structured greeting **JSON** with metadata.

## 2. Domain rules

| Rule | Description |
|------|----------------|
| **Default name** | If no usable name is provided for the **GET** flow (missing, or blank after trim), use `greeting.default-name` from configuration (default **Guest** in reference profile). |
| **POST validation** | Request body must contain **`name`**: not blank, maximum **50** characters (`jakarta.validation`). |
| **Message format** | Human-readable message: `Olá, {name}!` (reference locale; adjust if product requires another language). |
| **Timestamp** | Response includes an **UTC** instant (`timestamp`). |
| **Environment label** | Response includes **`environment`** (string) — deployment or profile hint, **not** a secret. |

## 3. API contract

Base path: **`/v1/greetings`** (all routes versioned).

### 3.1 GET — optional query parameter

- **Method / path:** `GET /v1/greetings`
- **Query:** `name` (optional string).
- **Success:** `200` — body matches `GreetingResponse` schema.

**Example response:**

```json
{
  "message": "Olá, Junior!",
  "timestamp": "2026-05-03T23:00:00.000Z",
  "environment": "local"
}
```

### 3.2 POST — validated JSON body

- **Method / path:** `POST /v1/greetings`
- **Content-Type:** `application/json`
- **Body:** `GreetingRequest` — `{ "name": "<string>" }`
- **Success:** `200` — same response schema as GET.
- **Validation failure:** `400` — **RFC 7807** `ProblemDetail` with extension **`invalid_params`** listing fields and messages.

## 4. Implementation mapping

| Concern | Location |
|---------|-----------|
| HTTP mapping | `com.aidd.greeting.api.GreetingController` |
| Domain logic | `com.aidd.greeting.service.GreetingService` |
| Request / response DTOs | `com.aidd.greeting.api.dto` |
| Validation errors | `com.aidd.greeting.exception.GlobalExceptionHandler` |
| OpenAPI | `com.aidd.greeting.config.OpenApiConfig` + springdoc |

## 5. Related diagrams

- [C4 components](c4-model.md#level-3--components-inside-the-spring-boot-application)
- [Sequences](sequence-diagrams.md)
