# Deployment Guide for Spring Boot API

This guide outlines the steps to deploy a Spring Boot API.

## Prerequisites

- Java Development Kit (JDK) installed (version 8 or higher)
- Gradle installed

## Deployment Steps

1. Clone the repository:

   ```
     git clone https://github.com/amine-hamzi/example-service-films.git
   ```


2. Build the application:

   ```
   gradle build
   ```

   This command compiles the source code, runs tests, and packages the application into an executable JAR file.



3. (Optional) Configure the application:

    - Open the `application.properties` file in the `src/main/resources` directory.
    - Modify the configuration properties according to your deployment environment (e.g., database connection, server port).
    - Save the changes.



4. Run the application:

   ```
   java -jar build/libs/example-service-films-0.0.1-SNAPSHOT.jar
   ```

   The Spring Boot API will start and be accessible at the configured server port.

Congratulations! You have successfully deployed your Spring Boot API. Please refer to the application's documentation for API usage and additional configuration options.