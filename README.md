# RestAssured API Testing Framework

Automated API testing project for [reqres.in](https://reqres.in) built with Java, RestAssured, and TestNG.

## Tech Stack

- Java 17
- RestAssured 5.5.1
- TestNG 7.11.0
- Allure Reports 2.27.0
- Maven

## Project Structure

src/
└── test/java/api/
    ├── BaseConfig.java         # Base configuration with RequestSpec and API key
    └── test/
        ├── GetUserTest.java
        ├── CreateUserTest.java
        ├── UpdateUserPutTest.java
        ├── UpdateUserPatchTest.java
        └── DeleteUserTest.java

## Test Coverage

| Method | Endpoint       | Tests                              |
|--------|----------------|------------------------------------|
| GET    | /api/users/2   | Valid user, Non-existing user (404)|
| GET    | /api/users     | User list, non-empty               |
| POST   | /api/users     | Create user, Empty body            |
| PUT    | /api/users/2   | Full update, Non-existing user     |
| PATCH  | /api/users/2   | Partial update, Multiple fields    |
| DELETE | /api/users/2   | Delete user, Non-existing user     |

## How to Run

### Run all tests:
```bash
mvn clean test
```

### Generate Allure Report:
```bash
mvn allure:serve
```

## Design Patterns

- **BaseConfig** — centralized API key and base URL configuration
- **RequestSpecBuilder** — reusable request specification across all tests
- **Allure Annotations** — `@Epic`, `@Feature`, `@Description`, `@Severity` for structured reporting
