# Medium Scale Project

This project is a medium-scale software application built with Java and Spring Framework. It is designed to demonstrate a simple RESTful API service that manages a collection of examples.

## Requirements

- Java 11
- Maven
- Spring Boot 2.7.0

## Running the Application

To run the application, use the following command in the root directory of the project:

```bash
mvn spring-boot:run
```

The application will start and be available at `http://localhost:8080`.

## REST API

The REST API to the example app is described below.

### Get list of Examples

`GET /api/examples`

### Get a specific Example

`GET /api/examples/{id}`

### Create a new Example

`POST /api/examples`

### Update an existing Example

`PUT /api/examples/{id}`

### Delete an Example

`DELETE /api/examples/{id}`

## H2 Database Console

The H2 database console is enabled by default and can be accessed at `http://localhost:8080/h2-console`.

## Project Structure

The project has the following structure:

- `pom.xml`: Project Object Model file for Maven build.
- `src/main/resources/application.properties`: Configuration properties for the application.
- `src/main/java/com/example/YourApplication.java`: Main application class that runs the Spring Boot application.
- `src/main/java/com/example/controller/ExampleController.java`: REST controller for handling API requests.
- `src/main/java/com/example/service/ExampleService.java`: Service layer to handle business logic.
- `src/main/java/com/example/repository/ExampleRepository.java`: Repository interface for data access.
- `src/main/java/com/example/model/ExampleModel.java`: Entity model representing an example.
- `src/test/java/com/example/YourApplicationTests.java`: Tests for the Spring Boot application.
- `src/test/java/com/example/controller/ExampleControllerTests.java`: Tests for the ExampleController.
- `src/test/java/com/example/service/ExampleServiceTests.java`: Tests for the ExampleService.
- `src/test/java/com/example/repository/ExampleRepositoryTests.java`: Tests for the ExampleRepository.
- `.gitignore`: Specifies intentionally untracked files to ignore.
- `README.md`: This file, containing project documentation.

## Testing

To run the tests, use the following command:

```bash
mvn test
```

## Contributing

If you wish to contribute to this project, please fork the repository and submit a pull request.

## License

This project is open-sourced under the MIT License.
