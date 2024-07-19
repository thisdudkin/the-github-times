# The GitHub Times

## Description
"The GitHub Times" is a web application designed for managing news articles. It allows creating, editing, deleting, and viewing news articles. The application also supports user registration and authentication with different roles (administrator, editor, reader).

## Technologies
The project uses the following technologies:
- **Java**: primary programming language
- **Lombok**: library for reducing boilerplate code
- **Spring Web**: framework for developing web applications
- **Thymeleaf**: template engine for creating web pages
- **Spring Security**: security framework for managing authentication and authorization
- **JDBC API**: API for interacting with the database
- **Spring Data API**: high-level data access with repository support
- **PostgreSQL**: relational database management system

## Installation and Launch

### Requirements
- JDK 17 or higher
- Maven
- PostgreSQL

### Database Setup
1. Create a PostgreSQL database:
   
    ```
    CREATE DATABASE newspaper_db;
    ```
2. Create a user and grant them privileges:
   
    ```
    CREATE USER newspaper_user WITH PASSWORD 'password';
    GRANT ALL PRIVILEGES ON DATABASE newspaper_db TO newspaper_user;
    ```
### Build and Launch
1. Build the project using Maven:

    ```
    mvn clean install
    ```
2. Run the application:
   
    ```
    mvn spring-boot:run
    ```
## Main Features
- User registration and authentication
- Managing news articles (creating, editing, deleting)
- Viewing the list of news articles
- Access control based on user roles

## Contacts
For questions and suggestions, please contact me at: skyraddan@outlook.com
