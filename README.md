# desi-wanderer

Backend for a travel blogging platform, written as a handful of Spring Boot services (Java 21, Spring Boot 4.1). Users write posts, comment on them and upload images. New posts and comments get checked by an LLM (DeepSeek, through Spring AI) before they show up for everyone else.

There's no frontend in this repo. The Keycloak realm does define a `web` client that expects one on `http://localhost:3000`.

## What's in the repo

| Module | Port (dev) | What it does |
|---|---|---|
| `discovery-server` | 8761 | Eureka server, the other services register here |
| `api-gateway` | 8000 | Single entry point. Validates the Keycloak JWT and routes requests to the services below |
| `blog-service` | 8001 | Write side: create/update/delete posts, comments and images (images go to S3) |
| `report-service` | 8002 | Moderation: asks the LLM to review posts and comments and stores the result |
| `query-service` | 8003 | Read side: serves all the `GET` endpoints from its own database |
| `common` | - | Shared library: DTOs, Kafka consumer setup, security helpers, Postgres session handling |

Outside the services:

- `docker-compose.yaml` runs everything they depend on: four Postgres databases (Keycloak, blog, report, query), Kafka, Keycloak, Debezium, LocalStack (S3) and Grafana (OpenTelemetry, port 8900).
- `config/` holds what gets mounted into those containers and what you run by hand: the Keycloak realm export, Debezium connector definitions and SQL, the LocalStack bucket script and the Postgres service-user script.
- Each service that has a database keeps its Flyway migrations in `src/main/resources/db/migration`.

## How it works

- **Writes and reads are split.** `POST`/`PUT`/`PATCH`/`DELETE` on `/posts`, `/comments` and `/images` go to `blog-service`. `GET`s go to `query-service`.
- **Data moves through Kafka.** Debezium watches the Keycloak, blog and report databases and publishes row changes to Kafka. `query-service` and `report-service` consume those events and keep their own copies of the data they need.
- **Moderation.** `report-service` creates a comment report on its own whenever a comment event arrives (unless the comment is already approved). Post reports are created by `POST /post-reports` with a `postId`. The prompts are in `report-service/src/main/resources/prompts`.
- **Auth.** Keycloak issues the tokens. Access is controlled by scopes like `posts:write`, `posts:approve` or `comments:read:any`. The scopes are also passed to Postgres, and row-level security policies in the migrations decide which rows a request can see or change.

## Endpoints (through the gateway)

| Path | Methods | Served by |
|---|---|---|
| `/posts`, `/comments`, `/images` | POST, PUT, PATCH, DELETE | blog-service |
| `/posts`, `/comments`, `/images`, `/post-reports`, `/comment-reports` | GET | query-service |
| `/post-reports` | POST, DELETE | report-service |

Images are uploaded as `multipart/form-data` (5 MB limit). `GET /images/file/{id}` on the query service returns the file.

## Setup

You need Docker and JDK 21.

**1. Environment file**

```sh
cp .env.example .env
```

The passwords in there are throwaway dev defaults, so change them for anything that isn't local.

**2. Start the infrastructure**

```sh
docker compose up -d
```

Keycloak imports the `desi-wanderer` realm on first start. Its admin console is at http://localhost:8400 (`admin` / `admin` with the example env). The realm has no users, so create one there and give it the scopes you want to test with.

**3. Run the database migrations**

```sh
./gradlew :blog-service:flywayMigrate :report-service:flywayMigrate :query-service:flywayMigrate
```

**4. Set up Debezium**

The blog and report databases are ready after step 2. Keycloak's isn't: run the publication script once Keycloak has started (it needs Keycloak's `user_entity` table to exist):

```sh
docker exec -i desi_wanderer_keycloak_db psql -U root -d desi_wanderer < config/debezium/sql/01_keycloak_connector.sql
```

Then register the three connectors by sending the requests in `config/debezium/connectors/*.http` (IntelliJ or the VS Code REST Client work), or POST the JSON bodies to `http://localhost:9093/connectors` with curl.

**5. DeepSeek key**

`report-service` reads `DEEPSEEK_API_KEY` from the environment. `report-service/.env.example` only shows the variable name. Spring doesn't load that file on its own, so export the variable in the shell you start the service from.

**6. Start the services**

The ports and database settings live in `application-dev.yaml`, so use the `dev` profile. Start `discovery-server` first, each in its own terminal:

```sh
SPRING_PROFILES_ACTIVE=dev ./gradlew :discovery-server:bootRun
SPRING_PROFILES_ACTIVE=dev ./gradlew :api-gateway:bootRun
SPRING_PROFILES_ACTIVE=dev ./gradlew :blog-service:bootRun
SPRING_PROFILES_ACTIVE=dev ./gradlew :report-service:bootRun
SPRING_PROFILES_ACTIVE=dev ./gradlew :query-service:bootRun
```

Then send requests to http://localhost:8000 with a Keycloak access token as a Bearer header.
