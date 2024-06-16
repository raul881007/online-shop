# Online Shop multi-module application with hexagonal architecture

This project shows you how can you modularize a Spring Boot application with Maven and how can you use some of the modules as libraries in other Spring Boot applications. Note that the project structure applies a hexagonal architecture, which allows better work and understanding of the code.

## ⚙️ Requirements

- JDK 17.
- Maven 3.8.4

## How to run
Go to the project root directory (ms_shop) and execute the following command to compile, test, package and install the different artifacts in your local maven repository.

```shell
mvn clean install
```
After that you can see in your terminal the tests results and you can compare it with the requirements.

In case you want run the complete microservice an make all the requests by yourself you can use the following command inside the project root directory (ms_shop):

```shell
mvn spring-boot:run -pl boot
```

Once there, it is possible to see the application swagger documentation with the following link:

- `http://localhost:8081/swagger-ui.html`


## 📘 Technologies

### General

| Technology | Purpose |
| ---------- |----------|
|Hexagonal architecture| Following an hexagonal clean architecture when creating this microservice. Note that it aims at creating loosely coupled application components that can be easily connected to their software environment by means of ports and adapters. |
| [rest-assured](https://rest-assured.io/) | REST Assured is a Java DSL for simplifying testing of REST based services |
| [Lombok](https://projectlombok.org/) | Library that helps us significantly reduce boilerplate code when writing Java applications.|
| [MapStruct](https://mapstruct.org/) | Code generator that greatly simplifies the implementation of mappings between Java bean types based on a convention over configuration approach |
| [Spring-boot](https://quarkus.io/) | Java framework that helps you to create a REST microservice easily . |

### REST adapter

| Technology | Purpose |
| ---------- |----------|
| [springdoc-openapi-ui](https://springdoc.org/) | Helps to automate the generation of API documentation using spring boot projects. |
| [rest-assured](https://rest-assured.io/) | Testing and validating REST services |

### H2 adapter

| Technology | Purpose |
| ---------- |----------|
| [H2Database](https://www.h2database.com/html/main.html) | Very fast, open source, JDBC API. Support embedded and server modes; disk-based or in-memory databases |
| [Spring Data JPA](https://spring.io/projects/spring-data-jpa) | JPA based repositories implementation |
| [Flyway](https://flywaydb.org/) | To load database migrations at application startup. |
