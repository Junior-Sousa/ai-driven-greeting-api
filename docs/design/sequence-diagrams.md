# Sequence diagrams

These diagrams describe the **runtime interactions** for the main HTTP flows. They complement the [C4 component view](c4-model.md) and the [feature design document](fdd-greeting-service.md).

---

## 1. GET `/v1/greetings` — success (optional name)

```mermaid
sequenceDiagram
    autonumber
    participant C as Client
    participant Ctrl as GreetingController
    participant Svc as GreetingService
    C->>Ctrl: GET /v1/greetings?name=Alice
    Ctrl->>Svc: greet(Optional.of("Alice"))
    Svc-->>Ctrl: GreetingResponse
    Ctrl-->>C: 200 application/json
```

If `name` is missing or blank after trim, the service uses the configured default (see `greeting.default-name`).

---

## 2. POST `/v1/greetings` — success

Validation runs before the controller method body (`@Valid`).

```mermaid
sequenceDiagram
    autonumber
    participant C as Client
    participant VF as Spring Validation
    participant Ctrl as GreetingController
    participant Svc as GreetingService
    C->>Ctrl: POST /v1/greetings\napplication/json
    Ctrl->>VF: validate GreetingRequest
    VF-->>Ctrl: ok
    Ctrl->>Svc: greetWithValidatedName(name)
    Svc-->>Ctrl: GreetingResponse
    Ctrl-->>C: 200 application/json
```

---

## 3. POST `/v1/greetings` — validation error (400, RFC 7807)

```mermaid
sequenceDiagram
    autonumber
    participant C as Client
    participant VF as Spring Validation
    participant Ctrl as GreetingController
    participant GEH as GlobalExceptionHandler
    C->>Ctrl: POST body { "name": "" }
    Ctrl->>VF: validate GreetingRequest
    VF-->>Ctrl: MethodArgumentNotValidException
    Ctrl-->>GEH: exception
    GEH-->>C: 400 application/problem+json\nProblemDetail + invalid_params
```

The controller method is not invoked when validation fails; the **global exception handler** produces the **RFC 7807** response.

---

## 4. Observability (parallel concerns)

For every handled request, Spring MVC + Micrometer may create **observations** and tracing may populate **MDC** keys such as `traceId` and `spanId` for JSON logs. That behaviour is cross-cutting and not repeated on every sequence diagram above.
