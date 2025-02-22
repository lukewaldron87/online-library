# Online Library Backend

This is the backend for the Online Library application built with Spring Boot. It integrates with Cohere's AI API and provides a RESTful API for the frontend to interact with.

## Prerequisites

- Java 21 or later
- Maven
- Cohere API Key

## Setup

1. Clone the repository:
    ```bash
    git clone https://github.com/lukewaldron87/online-library.git
    cd online-library
    ```

2. Add your Cohere API key:
    - Copy `template-cohereapikey.properties` to `cohereapikey.properties`.
    - Add your Cohere API key in `cohereapikey.properties`.

3. Build the project:
    ```bash
    mvn clean install
    ```

4. Run the Spring Boot application:
    ```bash
    mvn spring-boot:run
    ```

## API Documentation

You can access the Swagger UI for API documentation at: http://localhost:8080/swagger-ui/index.html

# Design Overview

- **Cohere API Integration**: The backend integrates with Cohere's AI to provide intelligent features. The API key is configured through the `cohereapikey.properties` file.
- **Swagger/OpenAPI**: API documentation is available via Swagger UI for easy exploration of available endpoints.

## Testing

Run tests using Maven:
```bash
mvn test