# The GitHub Times 

[![Java Build Status](https://github.com/earlspilner/the-github-times/actions/workflows/maven-build.yml/badge.svg)](https://github.com/earlspilner/the-github-times/actions/workflows/maven-build.yml)

Welcome to **The GitHub Times**, a microservices architecture project designed to provide a comprehensive platform for handling and aggregating GitHub-related information. This project consists of two main services: an authentication server and a REST API server.

## Overview

- **Authentication Server**: Handles user authentication using JWT (JSON Web Tokens) with Spring Security.
- **REST API Server**: Provides RESTful APIs for interacting with a PostgreSQL database, built with Spring Boot, Java, and Maven.

### Authentication Server

- **Technology Stack**:
  - Spring Boot
  - Spring Security
  - JWT (JSON Web Tokens)

- **Responsibilities**:
  - User Authentication and Authorization
  - Token Generation and Validation

### REST API Server

- **Technology Stack**:
  - Spring Boot
  - Spring Data JPA
  - PostgreSQL
  - Maven
  - Java 17

- **Responsibilities**:
  - CRUD Operations on Entities
  - Data Persistence with PostgreSQL
  - Business Logic Implementation

## Getting Started

### Prerequisites

- Java 17
- Maven
- PostgreSQL

### Setting Up Authentication Server

1. Clone the repository:
    ```bash
    git clone https://github.com/earlspilner/the-github-times.git
    cd the-github-times/auth
    ```

2. Configure application properties:
    ```properties
    # src/main/resources/application.properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/the-github-times
    spring.datasource.username=postgres
    spring.datasource.password=secret
    ```

3. Build and run the server:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

### Setting Up REST API Server

1. Clone the repository:
    ```bash
    git clone https://github.com/earlspiler/the-github-times.git
    cd the-github-times/newspaper
    ```

2. Configure application properties:
    ```properties
    # src/main/resources/application.properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/the-github-times
    spring.datasource.username=postgres
    spring.datasource.password=secret
    ```

3. Build and run the server:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

## Contributing

We welcome contributions to **The GitHub Times**! If you'd like to contribute, please follow these steps:

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature/YourFeature`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature/YourFeature`).
5. Create a new Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For questions or feedback, please contact us at [alexraddan@gmail.com](mailto:alexraddan@gmail.com).

---

Thank you for checking out **The GitHub Times**! We hope you find it useful.
