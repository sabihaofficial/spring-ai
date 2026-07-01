/# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project overview

A minimal Spring Boot demo (Java 21, Spring Boot 4.1.0) that uses `spring-ai-starter-model-openai` (Spring AI 2.0.0) to call OpenAI chat models via two REST endpoints.

## Commands

Use the Maven wrapper (`mvnw.cmd` on Windows, `./mvnw` elsewhere).

```powershell
# Build (skip tests)
.\mvnw.cmd -DskipTests clean package

# Run the app (foreground, port 8080)
.\mvnw.cmd spring-boot:run

# Run all tests
.\mvnw.cmd test

# Run a single test class
.\mvnw.cmd test "-Dtest=OpenAIServiceImplTest"

# Run a single test method
.\mvnw.cmd test "-Dtest=OpenAIServiceImplTest#getAnswerTest"
```

Requires `OPENAI_API_KEY` to be set in the environment (`spring.ai.openai.api-key=${OPENAI_API_KEY}` in `application.properties`). The only test in the repo (`OpenAIServiceImplTest`) is a `@SpringBootTest` that calls the live OpenAI API — it needs a real key to pass and will fail (not just skip) without one.

## Architecture

Standard Spring Boot layering, all under `com.spring.ai.practice.demo`:

- `controller/QuestionController` — REST endpoints `POST /ask` and `POST /capital`, delegates directly to `OpenAIService`.
- `service/OpenAIService` (interface) / `OpenAIServiceImpl` — wraps Spring AI's `ChatModel`, builds a `Prompt` (either from raw text or a `PromptTemplate`), calls `chatModel.call(prompt)`, and unwraps the result into an `Answer`.
- `model/` — plain records used as request/response DTOs: `Question`, `GetCapitalRequest`, `Answer`.
- `resources/templates/get-capital-prompt.st` — a Spring AI `PromptTemplate` resource (`.st` = StringTemplate) injected via `@Value("classpath:...")` and rendered with `promptTemplate.create(Map.of(...))`. This is the pattern to follow when adding new prompt-templated endpoints rather than concatenating strings inline.

The `ChatModel` bean itself is auto-configured by the `spring-ai-starter-model-openai` starter from `application.properties` — there is no manual bean wiring for it in this codebase.
