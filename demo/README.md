# demo

A minimal Spring Boot demo that uses the Spring AI OpenAI model starter.

## Project overview

- Java: 21
- Spring Boot: 4.1.0
- Uses `spring-ai-starter-model-openai` to call OpenAI models

The project contains a simple `OpenAIService` implementation and a unit test that demonstrates calling the service.

## Prerequisites

- Java 21 (LTS) installed and `JAVA_HOME` set
- Maven (optional if using the included Maven wrapper)
- An OpenAI API key. The application reads it from the environment variable `OPENAI_API_KEY`.

## Configuration

The application reads configuration from `src/main/resources/application.properties`:

```
spring.application.name=demo
spring.ai.openai.api-key=${OPENAI_API_KEY}
```

Set your API key in the environment. On Windows PowerShell:

```powershell
$env:OPENAI_API_KEY = 'sk-...'
``` 

Or, when running a single command:

```powershell
setx OPENAI_API_KEY "sk-..." ; # restarts may be required to persist
```

For local tests you can export the variable in the same shell session before running Maven commands.

## Build

Using the included Maven wrapper (Windows):

```powershell
.\mvnw.cmd -DskipTests clean package
```

Or with a system Maven installation (cross-platform):

```sh
mvn -DskipTests clean package
```

## Run

Run with the Maven Spring Boot plugin (keeps the app in the foreground):

```powershell
.\mvnw.cmd spring-boot:run
```

Or run the packaged jar:

```powershell
.\mvnw.cmd -DskipTests package
java -jar target\demo-0.0.1-SNAPSHOT.jar
```

## Tests

Run the test suite (note: tests may call the real OpenAI API if the implementation uses the API directly):

```powershell
.\mvnw.cmd test
```

If you do not want tests to call the real API, set `OPENAI_API_KEY` to an empty value and/or mock the `OpenAIService` in your tests.

## Notes

- The sample test `src/test/java/com/spring/ai/practice/demo/service/OpenAIServiceImplTest.java` autowires the `OpenAIService` and will attempt to call the live API if an API key is present. For CI or offline development, consider mocking the service or disabling that test.
- This README is intentionally minimal — add project-specific instructions, contribution guidelines, or license details as needed.

