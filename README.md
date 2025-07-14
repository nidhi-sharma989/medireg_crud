# Medical Register Application

A comprehensive medical patient registration system built with Spring Boot 3.x, JSF, and modern DevOps practices.

## Features

- **Patient Management**: Add, update, view, and delete patient records
- **Form Validation**: Comprehensive client and server-side validation
- **Responsive UI**: Modern JSF interface with custom styling
- **Security**: OAuth2 integration with Auth0
- **Logging**: Structured logging with Logback
- **Testing**: Comprehensive test suite with unit and integration tests
- **CI/CD**: Automated pipeline with GitHub Actions

## Technology Stack

- **Backend**: Spring Boot 3.1.4, Spring Security, Spring Data JPA
- **Frontend**: JSF (Jakarta Faces) 4.0, JoinFaces
- **Database**: H2 (development), PostgreSQL (production)
- **Testing**: JUnit 5, Mockito, TestContainers
- **Build**: Maven 3.x
- **CI/CD**: GitHub Actions



### Prerequisites
- Java 17+
- Maven 3.6+

### Running Locally

```bash
# Clone the repository
git clone <your-repo-url>
cd medical-register-crud-sb3

# Run tests
mvn clean test

# Run the application
mvn spring-boot:run

# Access the application
# Open http://localhost:8080 in your browser
```

### Building for Production

```bash
# Build JAR file
mvn clean package

# Run the JAR
java -jar target/medical-register-0.0.1-SNAPSHOT.jar
```

## Testing

### Test Structure

```
src/test/java/
├── model/           # Unit tests for entities
├── repository/      # Data layer tests
├── beans/          # JSF managed bean tests
└── controller/     # Integration tests
```

### Running Tests

```bash
# Run all tests
mvn clean test

# Run only unit tests
mvn test

# Run integration tests
mvn verify -P integration-tests

# Generate coverage report
mvn jacoco:report
```

### Test Coverage

- **Unit Tests**: Model classes, JSF beans, business logic
- **Integration Tests**: Web layer, database interactions
- **Repository Tests**: Data persistence and retrieval
- **Security Tests**: Authentication and authorization

## CI/CD Pipeline

### GitHub Actions Workflow

The pipeline includes:

1. **Test Stage**
   - Unit tests execution
   - Integration tests
   - Code coverage reporting (JaCoCo)
   - Test results publishing

2. **Security Stage**
   - OWASP dependency vulnerability scanning
   - Security report generation

3. **Build & Deploy Stage**
   - Application packaging to JAR
   - Artifact publishing to GitHub
   - Automatic release creation
   - Deployment scripts (configurable)

### Pipeline Triggers

- **Push to main**: Full pipeline execution with release creation
- **Push to develop**: Test and security stages
- **Pull Requests**: Test validation

### Deployment Options

The pipeline creates JAR artifacts that can be deployed to:
- Traditional servers (VM/bare metal)
- Cloud platforms (AWS, Azure, GCP)
- Application servers (Tomcat, etc.)

Example deployment commands are included in the pipeline (commented out).

## Application Structure

```
src/main/java/com/example/medicalregister/
├── MedicalRegisterApplication.java    # Main application class
├── beans/                            # JSF managed beans
├── controller/                       # REST/Web controllers
├── model/                           # JPA entities
├── repository/                      # Data repositories
├── security/                        # Security configuration
└── service/                         # Business logic services

src/main/resources/
├── META-INF/resources/              # JSF pages (.xhtml)
├── application.yml                  # Main configuration
└── logback-spring.xml              # Logging configuration
```

## Development

### Code Quality

- **Code Coverage**: Minimum 80% coverage enforced
- **Security Scanning**: Automated vulnerability detection
- **Code Style**: Maven checkstyle integration
- **Static Analysis**: SpotBugs integration

### Adding New Features

1. Create feature branch from `develop`
2. Write tests first (TDD approach)
3. Implement feature
4. Ensure all tests pass
5. Create pull request to `develop`

### Database Schema

The application uses JPA entities with automatic schema generation:

```sql
-- Patient table structure
CREATE TABLE Patient (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    age INTEGER CHECK (age >= 0 AND age <= 150),
    medical_history VARCHAR(200) NOT NULL
);
```

## Deployment

### Environment Configuration

- **Development**: H2 in-memory database
- **Testing**: H2 with test profiles
- **Production**: PostgreSQL (recommended)

### JAR Deployment

```bash
# Build production JAR
mvn clean package -Pprod

# Run with production profile
java -jar target/medical-register-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

### Environment Variables

```bash
# Database configuration
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/medicalregister
SPRING_DATASOURCE_USERNAME=your_username
SPRING_DATASOURCE_PASSWORD=your_password

# Security configuration
SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_AUTH0_CLIENT_ID=your_client_id
SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_AUTH0_CLIENT_SECRET=your_client_secret
```

## Monitoring & Observability

- **Logging**: Structured JSON logging with correlation IDs
- **Health Checks**: Spring Boot Actuator endpoints
- **Metrics**: Application performance monitoring
- **Tracing**: Request tracing capabilities

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Ensure all tests pass
6. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.
