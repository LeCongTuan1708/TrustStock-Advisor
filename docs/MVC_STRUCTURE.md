# TrustStock-Advisor — MVC structure (team review)

## Layer roles

| Layer | Package / location | Responsibility |
|--------|--------------------|----------------|
| **View** | `src/main/webapp/*.jsp` | Presentation only. Reads `request` attributes set by controllers. No business logic; minimal scriptlets where JSTL is not enough. |
| **Controller** | `com.investorcare.controller` | Servlets: parse request, call DAO/service, set attributes, forward to JSP or redirect. **MainController** is the front controller: single entry via `action` parameter. |
| **Model** | `com.investorcare.model` | POJOs / entities (User, Asset, Alert, …). No DB or HTTP code. |
| **Persistence** | `com.investorcare.dao` | JDBC and SQL only. Returns models or primitives; no JSP forwards. |
| **Service** | `com.investorcare.service` (+ legacy `service` package) | External APIs and domain logic that spans multiple DAOs (e.g. `SignalEngine`, `StockAPIService`). Controllers should prefer services over calling many DAOs when logic grows. |

## Request flow

1. Browser submits to `MainController` with `action=...` (GET/POST).
2. **MainController** maps `action` → next servlet or JSP and **forwards** (server-side; URL may stay as MainController until redirect).
3. Target **controller** loads data via DAO/service, sets `request.setAttribute(...)`, forwards to **JSP**.
4. **JSP** renders using attributes only.

## Conventions for smooth UX

- Prefer **redirect after POST** for mutations (login already uses `sendRedirect`).
- Use **one** forward per request where possible; avoid forwarding to JSP that should have been preceded by a controller that sets attributes.
- Add `disabled` + loading state on submit buttons to avoid double submit (see `js/form-loading.js`).

## Known technical debt (for refactors)

- `StockAPIService` lives in package `service` (default package style); ideally move to `com.investorcare.service` and inject config for API keys.
- Some controllers instantiate DAOs directly; extract to service classes as features grow.
- `web.xml` mixes servlet declarations; consider annotation-only or a single consolidated descriptor.
