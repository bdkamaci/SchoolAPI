# SchoolAPI

## Description
SchoolAPI is a project designed for managing school-related data using a PostgreSQL database. It provides a set of RESTful APIs to interact with the school's data.

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
- [Features](#features)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#author)

## Installation

### Technologies
- PostgreSQL
- Java 21
- Spring Boot
- Spring Data JPA
- Spring MVC
- Maven
- Lombok
- ModelMapper
- Swagger

## Usage

### How to Use
1. Clone the project to your computer.
2. Set up your PostgreSQL database and configure the connection in the `application.properties` file.
3. Open the project in an IDE and run the application.
4. Visit `http://localhost:8080/` in your browser to test the endpoints.
5. Customize and extend the application using the features and modules suitable for your school.
6. You can find the API documentation in the project's main path.

### Maven Configuration
Project's `pom.xml` file includes the following dependencies:

- **Spring Boot**: Core dependencies for building the application.
- **PostgreSQL**: `org.postgresql:postgresql` for database connectivity.
- **Lombok**: `org.projectlombok:lombok` for reducing boilerplate code.
- **JWT**: `io.jsonwebtoken` libraries for JSON Web Token handling.
- **Spring Security**: For security configurations.
- **Springdoc OpenAPI**: For API documentation with Swagger.

## Features

- CRUD Operations for Entities
- Spring Web Security for Registration, Login and Logout Operations
- Basic Error Handling & Exception Management

## Contributing

1. Clone this repository.
2. Create a new branch for adding a new feature or fixing a bug: `git checkout -b new-feature`.
3. Commit your changes: `git commit -m 'Added a new feature'`.
4. Merge your branch with the main branch: `git merge main`.
5. Push your changes to the origin repository: `git push origin new-feature`.
6. Submit a pull request.

## License

This project is licensed under the MIT License. See the `LICENSE` file for more information.

## Author

Burcu Doga KAMACI
