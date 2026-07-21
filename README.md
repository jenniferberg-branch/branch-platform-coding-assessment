# Branch Interview Assessment - Jennifer Berg

This is a Spring Boot service that fetches a GitHub user's profile and repositories and returns them as a single JSON response.
The service follows a classic Spring MVC architecture. In detail:
- Controller layer:  Defines the GET REST endpoint and returns responses
- Service layer:  Calls the GitHub endpoints to retrieve the user information and repos and then merges them into one payload
- Model layer:  Response DTOs. Utilizes @JsonAlias when needed in order to map GitHub field names into the specified API field names
- Config layer:  Builds the RestClient with the GitHub users base URL
- ExceptionHandler layer:  Translates exceptions into consistent error responses

## Prerequisites

- **Java 21** (JDK)
- **Maven 3.9+**, or use the included Maven Wrapper (`mvnw` / `mvnw.cmd`)

## Install

Clone the repository, then download dependencies and compile:

```bash
./mvnw clean install
```

On Windows:

```bash
.\mvnw.cmd clean install
```

This builds the project and runs the unit tests.

## Run

Start the service with the Spring Boot Maven plugin:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
.\mvnw.cmd spring-boot:run
```

Or run the packaged jar after a build:

```bash
java -jar target/Branch-0.0.1-SNAPSHOT.jar
```

The service listens on **http://localhost:8080** by default.

## API

### Get user data

```http
GET /user?username={github-username}
```

**Example**

```bash
curl "http://localhost:8080/user?username=octocat"
```

**Success (200)** — user profile fields plus a `repos` list.

## Tests

Run the test suite:

```bash
./mvnw test
```

On Windows:

```bash
.\mvnw.cmd test
```
