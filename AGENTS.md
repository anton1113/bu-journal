# Repository Guidelines

## Project Structure & Module Organization
This repository is a Spring Boot 4 application backed by MongoDB. Production code lives under `src/main/java/com/arash/edu/bujournal`, split by layer: `controller` and `rest` for MVC/API endpoints, `service` for business logic, `repository` for Spring Data access, `domain` for Mongo documents, and `config` for application wiring. Thymeleaf templates are in `src/main/resources/templates`, static assets in `src/main/resources/static`, and OpenAPI specs in `src/main/resources/openapi`. Tests live under `src/test/java`; keep unit tests close to the package they cover and use integration-test base classes where Mongo wiring is required.

## Build, Test, and Development Commands
Use Java 24 and Maven.

- `mvn clean install`: compile, run tests, and build the application artifact in `target/`.
- `mvn test`: run the JUnit 5 test suite only.
- `mvn spring-boot:run`: start the app locally.
- `docker compose up -d mongo`: start the local MongoDB defined in `docker-compose.yaml`.

The app reads Mongo settings from `BU_JOURNAL_MONGO_DB_*` environment variables. `src/main/resources/application-local.properties` is the local override entry point.

## Coding Style & Naming Conventions
Follow the existing Java style: 4-space indentation, one top-level class per file, `PascalCase` for classes, `camelCase` for methods and fields, and package names under `com.arash.edu.bujournal`. Keep controllers thin and move business rules into services. Use Lombok only where it already fits the surrounding code. Do not hand-edit generated OpenAPI artifacts without also updating the YAML in `src/main/resources/openapi`.

## Testing Guidelines
Tests use JUnit 5, Spring Boot Test, and Testcontainers with MongoDB. Name fast unit tests `*Test` and integration coverage `*IntegrationTest`. Prefer extending `BaseIntegrationTest` for Spring-backed integration cases. Run `mvn test` before opening a PR; add coverage for service and repository changes, especially around persistence queries and role-based access paths.

## Commit & Pull Request Guidelines
Recent history uses Jira-style subjects such as `BU-100: fix mark deletion issue (#101)` and `BU-XX: sort lessons by date on UI (#102)`. Keep commit messages short, imperative, and prefixed with the ticket key when available. PRs should include a concise description, linked issue, test evidence, and screenshots for template or UI changes. Call out config, schema, or API-spec changes explicitly.
