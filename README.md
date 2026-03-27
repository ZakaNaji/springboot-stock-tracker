# Stock Tracker (Practice Project)

This is a Spring Boot project I built to practice backend architecture beyond basic CRUD.

It is **not production-ready** (by design), but it helped me train on API integration, caching, clean error handling, and layering.

## What I built

- REST API to fetch stock data:
  - current quote
  - company overview
  - historical prices
- REST API to manage favorite stocks (persisted in H2)
- External provider integration with interchangeable clients:
  - Alpha Vantage
  - Financial Modeling Prep (FMP)
- In-memory caching with Caffeine for stock endpoints
- Centralized API error responses with `@RestControllerAdvice`
- Request validation (example: history `days` must be between `1` and `365`)
- Basic observability via Actuator (`health`, `caches`)

## Why this is different from a simple CRUD app

Most simple CRUD apps are mostly:

- one entity
- one database
- create/read/update/delete only

This project goes further by adding:

1. **External API orchestration**
   - Data is pulled from live providers, mapped into internal models, then exposed as stable API responses.
2. **Provider abstraction**
   - `StockMarketClient` is an interface with provider-specific implementations.
   - Provider can be switched with configuration (`stock.provider`).
3. **Caching strategy**
   - Different cache TTLs by use case (quotes vs overview/history) to reduce repeated external calls.
4. **Input normalization and defensive checks**
   - Symbols are normalized (`trim + uppercase`) before processing.
   - Duplicate favorites and invalid input are handled explicitly.
5. **Consistent error contract**
   - The API returns structured error responses with status, message, path, and timestamp.

## Project structure

- `controller/` -> API endpoints
- `service/` -> business logic
- `client/` -> external provider clients + DTO mappers
- `repository/` -> JPA access for favorites
- `config/` -> HTTP client + cache configuration
- `exception/` -> custom exceptions + global handler

## API endpoints

### Stocks

- `GET /api/stocks/{symbol}`
- `GET /api/stocks/{symbol}/overview`
- `GET /api/stocks/{symbol}/history?days=30`

### Favourites

- `GET /api/favourites`
- `POST /api/favourites`

Example request body for `POST /api/favourites`:

```json
{
  "symbol": "AAPL"
}
```

## Quick start

### 1) Set API keys (optional for local testing)

If not set, the app falls back to `demo` keys from config.

```powershell
$env:ALPHAVANTAGE_API_KEY="your_key_here"
$env:FMP_API_KEY="your_key_here"
```

### 2) Choose provider in `src/main/resources/application.yaml`

```yaml
stock:
  provider: fmp # or alphavantage
```

### 3) Run the app

```powershell
.\mvnw.cmd spring-boot:run
```

## Useful local URLs

- App: `http://localhost:8080`
- H2 Console: `http://localhost:8080/h2-console`
- Actuator health: `http://localhost:8080/actuator/health`
- Actuator caches: `http://localhost:8080/actuator/caches`

## Current limitations (known and accepted for practice)

- Test coverage is still minimal.
- `FmpMarketClient` does not yet implement history (`getStockHistory`), so history is effectively Alpha Vantage-only right now.
- No authentication/authorization.
- No rate-limit/backoff strategy for upstream APIs yet.
- In-memory H2 is great for learning but not for real deployment.

## Next improvements I could add

- Full test suite (unit + integration + controller tests)
- Complete FMP history support
- Retry/backoff and circuit breaker for provider calls
- Swagger/OpenAPI docs
- Docker setup + profile-based configuration

---

If you're checking this repo as part of my learning journey: this project is where I focused on building stronger backend fundamentals, not just shipping CRUD endpoints.

