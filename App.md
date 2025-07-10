# Building a Robust JavaFX and Spring Boot Application with Maven, MySQL, and MongoDB in IntelliJ IDEA for LAN Deployment

## Introduction
This report details the comprehensive setup of a modern Java application within IntelliJ IDEA Community 
Edition, integrating several key technologies to meet specific functional and architectural requirements. 
The application is designed to leverage Spring Boot for robust backend services, providing a powerful 
framework for business logic and data management. 

For the user interface, JavaFX has been selected to deliver a rich, native desktop experience. 
Maven will serve as the build automation tool, managing project dependencies and the build lifecycle. 
Data persistence will be handled by a dual-database approach: MySQL will be utilized for primary data storage, 
while MongoDB will be employed specifically for audit logging, offering flexibility and scalability for 
logging purposes. 

A critical functional requirement is the application's accessibility over a Local Area Network (LAN), 
necessitating specific network and firewall configurations. The entire development environment will 
standardize on Liberica Full JDK 24.0.1, chosen for its comprehensive features, including bundled 
JavaFX support. 

This guide is structured to provide clear, actionable instructions for developers aiming to implement such 
a multi-faceted Java application.

## 1. Initial Setup and Environment Configuration
The foundational steps for any development project involve establishing a stable and efficient environment. 
For this application, the initial configuration of the Integrated Development Environment (IDE) and the 
Java Development Kit (JDK) are paramount.
### 1.1. Installing IntelliJ IDEA Community Edition
IntelliJ IDEA, developed by JetBrains, is a widely recognized and highly capable Integrated Development 
Environment for Java development. The Community Edition, available free of charge, offers robust support 
for Spring Boot development, making it an ideal choice for this project. 

To begin, the IntelliJ IDEA Community Edition should be downloaded and installed from the official 
JetBrains website. The selection of IntelliJ IDEA as the development environment is a strategic decision 
that extends beyond mere code editing. Its integrated Spring Initializr wizard significantly streamlines 
the project creation process, directly addressing the need for a straightforward setup. 

This native integration means the IDE inherently understands Maven project structures and Spring Boot 
conventions, which contributes to a more efficient development workflow. The focus on the Community 
Edition ensures that the provided instructions are accessible to a broad range of developers, 
irrespective of commercial licensing. This initial choice of IDE establishes a solid foundation, directly 
impacting developer productivity and minimizing initial configuration complexities.
### 1.2. Configuring Liberica Full JDK 24.0.1 in IntelliJ IDEA
The specified Java Development Kit for this project is Liberica Full JDK 24.0.1. This particular JDK 
distribution offers a significant advantage for JavaFX applications: it bundles JavaFX (OpenJFX) components 
directly, thereby eliminating the need for developers to manually add numerous JavaFX-specific dependencies 
to the project's pom.xml file.3 This pre-packaging simplifies the dependency management process, a common 
source of configuration challenges in JavaFX development, and contributes to a smoother project setup.

**To configure Liberica Full JDK 24.0.1 within IntelliJ IDEA:**

**1. Download Liberica Full JDK 24.0.1:**
Obtain the appropriate installer for the operating system from BellSoft's official website.

**2. Add JDK to IntelliJ IDEA (Global Configuration):** 
- Launch IntelliJ IDEA.
- Navigate to `File` > `New Project Setup` > `Structure`. 
- In the dialog, under `Project Settings`, select `Project`. Alternatively, for managing all available JDKs, 
navigate to `Platform Settings` > `SDKs`. 
- Click the SDK dropdown list. If `Liberica JDK 24.0.`1 is already installed and detected, select it.
- If not detected or not yet installed, select `Add SDK` > `JDK`. If Liberica JDK is installed on the system, 
specify the path to its home directory. Otherwise, choose `Download JDK`, select `BellSoft` as the vendor, 
specify `24.0.1` as the version, and select a suitable installation path.
- Ensure that `Liberica JDK 24.0.1` is selected as the Project SDK for all new projects.

The decision to use Liberica Full JDK not only simplifies dependency management but also carries broader 
implications for the application's runtime characteristics. BellSoft emphasizes a "performant and secure 
runtime" for Liberica JDK.3 This suggests that the chosen JDK vendor can influence the application's 
performance and security posture, potentially offering optimizations and enhanced security features compared 
to a generic OpenJDK build. Thus, the JDK selection is not merely a compatibility concern but a strategic 
choice that impacts the application's operational efficiency and resilience.

## 2. Project Creation with Spring Initializr
The most efficient and recommended method for initiating a Spring Boot project is through Spring Initializr. 
This tool, whether accessed via its web interface or integrated directly into IntelliJ IDEA, provides a 
streamlined process for generating a project with the necessary structure and initial dependencies

### 2.1. Generating the Spring Boot Project (Maven)
IntelliJ IDEA's integrated Spring Initializr wizard significantly simplifies the project generation process, 
allowing for direct project creation within the IDE. 

The steps to generate the Spring Boot project are as follows:

**1. Open IntelliJ IDEA.** 

**2. Initiate New Project Creation:**
Go to `File > New > Project` from the main menu.

**3. Select Spring Boot:**
In the left pane of the `New Project` wizard, select `Spring Boot`.

**4. Configure Project Metadata:**
 - **Project SDK:** 
Confirm that `Liberica JDK 24.0.1` is selected, leveraging its bundled JavaFX capabilities.
 - **Project Type:** 
Select `Maven Project`, aligning with the project's build management requirement.
 - **Language:**
Set to `Java`.
 - **Spring Boot Version:**
 - Choose the latest stable version available that is compatible with JDK 24. This 
   ensures access to the most recent features and security updates.
 - **Project Metadata:**
Provide appropriate values for `Group` (e.g., `com.example`), `Artifact` (e.g., 
   `javafx-spring-app`), `Name`, and `Package Name`.

**5. Add Initial Dependencies:** 
This step is critical for establishing the project's core functionalities. 
Select the following starters:
 - `Spring Web`: Although the primary UI is JavaFX, including `Spring Web` is beneficial. It establishes the 
   foundation for exposing REST endpoints, which can be valuable for inter-application communication or for 
   integrating a web-based administrative interface in the future. This implicitly sets up a foundation for a 
   more distributed or hybrid architecture, even if the initial scope is a single desktop application.
 - `Spring Data JPA`: Essential for interacting with the MySQL database for primary data persistence.
 - `MySQL Driver`: Provides the necessary JDBC driver for connecting to MySQL.
 - `Spring Data MongoDB`: Required for integrating MongoDB, specifically for audit logging.
 - `Lombok`: An optional but highly recommended dependency that significantly reduces boilerplate code (e.g., 
   getters, setters, constructors), enhancing code readability and maintainability.
 - `Spring Boot DevTools`: Optional, but highly beneficial for accelerating development cycles by enabling 
   automatic restarts and live reloading of changes.

**6. Generate Project:**
Click `Create`. IntelliJ IDEA will automatically generate the project structure, import 
it, and download all specified dependencies, setting up the initial development environment.

The use of Spring Initializr for project generation is not merely a convenience; it is a best practice for 
ensuring project stability and reducing configuration overhead. The tool automatically manages compatible 
dependency versions, especially when dealing with multiple Spring Boot starters and external libraries. This 
is facilitated by Spring Boot's `spring-boot-starter-parent` and Bill of Materials (BOMs), which handle 
transitive dependencies and minimize version conflicts, allowing developers to concentrate on implementing 
business logic rather than resolving dependency issues.

### 2.2. Core Maven Dependencies for Spring Boot, JPA, and MongoDB
Upon project generation, the `pom.xml` file serves as the central configuration for Maven, managing all 
project dependencies and build configurations. It is imperative to verify that the `pom.xml` contains the 
essential dependencies required for the application's functionality. If any dependencies were missed during 
the initial Spring Initializr step, they can be added manually to the `<dependencies>` block or via IntelliJ 
IDEA's "Add Starters" inlay hint feature.

The following table outlines the core Maven dependencies that should be present in the `pom.xml`:

**Table: Initial Maven Dependencies**

| Dependency                   | GroupIdArtifactId                  | Description                                                                       |
|------------------------------|------------------------------------|-----------------------------------------------------------------------------------|
| `org.springframework.boot`   | `spring-boot-starter-web`          | Enables web capabilities, including embedded Tomcat, for potential REST services. |
| `org.springframework.boot`   | `spring-boot-starter-data-jpa`     | Provides Spring Data JPA support for relational database interaction.             |
| `mysql`                      | `mysql-connector-java`             | JDBC driver for MySQL database connectivity.                                      |
| `org.springframework.boot`   | `spring-boot-starter-data-mongodb` | Integrates Spring Data MongoDB for NoSQL database operations.                     |
| `org.projectlombok`          | `lombok`                           | Reduces boilerplate code with annotations like `@Getter`, `@Setter`.              |
| `org.springframework.boot`   | `spring-boot-devtools`             | Enables development-time features like automatic restarts.                        |
| `org.springframework.boot`   | `spring-boot-starter-test`         | Provides testing utilities for Spring Boot applications.                          |

The `spring-boot-starter-parent` declaration in the `pom.xml` implicitly manages the versions for most Spring 
Boot-related starters, ensuring compatibility and reducing manual version management. For non-Spring 
dependencies or specific version overrides, explicit version numbers may be required.

## 3. Integrating JavaFX with Spring Boot
Integrating JavaFX, a desktop UI framework, with Spring Boot, a backend framework, requires careful 
coordination due to their distinct application lifecycles. This section details the architectural 
considerations and implementation steps for achieving this integration.

### 3.1. Understanding the Dual Main Class Architecture
A fundamental aspect of integrating JavaFX with Spring Boot is recognizing their independent startup 
mechanisms. JavaFX applications typically extend `javafx.application.Application` and launch via 
`Application.launch()`, managing the UI thread and lifecycle within their `start(Stage primaryStage)` method.

Conversely, Spring Boot applications are bootstrapped by a class annotated with `@SpringBootApplication`, 
which contains a `public static void main(String args)` method that calls `SpringApplication.run()` to initialize 
the Spring ApplicationContext. 

These two frameworks each expect to "own" the main entry point and control their respective lifecycles. 
Attempting to run both directly from a single `main()` method without coordination can lead to conflicts, such 
as the UI thread being blocked or the Spring context not initializing correctly. The solution involves a dual 
main class architecture where the JavaFX application class delegates to the Spring Boot context for backend 
initialization, ensuring both frameworks operate harmoniously. 

This approach ensures that the Spring context, responsible for managing beans, services, and repositories, is 
fully operational before the JavaFX UI is rendered.

### 3.2. Adding JavaFX and `javafx-weaver-spring-boot-starter` Maven Dependencies
While Liberica Full JDK bundles JavaFX, simplifying the core JavaFX setup, integrating it seamlessly with 
Spring Boot's dependency injection and bean management capabilities benefits from a dedicated integration 
library. The `javafx-weaver-spring-boot-starter` library is highly recommended for this purpose, as it 
significantly simplifies the process of injecting Spring-managed beans into JavaFX controllers and managing 
FXML views.

Add the following dependency to the `<dependencies>` section of your `pom.xml`:

```XML
<dependency>
    <groupId>net.rgielen</groupId>
    <artifactId>javafx-weaver-spring-boot-starter</artifactId>
    <version>1.3.0</version> 
</dependency>
```
The `javafx-weaver-spring-boot-starter` (and its underlying `javafx-weaver-spring-boot-autoconfigure`) 
simplifies the integration by providing utility classes like `FxWeaver` that can load FXML views and 
automatically inject Spring-managed beans into their controllers.

This abstraction significantly reduces the boilerplate code typically required for manual FXML loading and 
controller instantiation within a Spring context, thereby enhancing developer productivity and maintaining a 
clean separation of concerns.

### 3.3. Structuring JavaFX and Spring Boot Application Classes
To implement the dual main class architecture, two primary application classes are required: one for Spring 
Boot and one for JavaFX.

**1. Spring Boot Main Class:**
This class will be the standard Spring Boot application entry point.
 - Example: `com.example.javafxspringapp.JavafxSpringAppApplication.java`

```Java
package com.example.javafxspringapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavafxSpringAppApplication {
    public static void main(String args) {
    // This main method will not be directly run by IntelliJ IDEA's default Spring Boot run config for JavaFX apps.
    // It's here for standard Spring Boot application structure.
    // The JavaFX application will manage the Spring context lifecycle.
    // SpringApplication.run(JavafxSpringAppApplication.class, args); // Do NOT call directly here for JavaFX integration
    }
}
```

 - The `main` method in this class is typically where `SpringApplication.run()` is called. However, for JavaFX 
integration, the Spring context initialization needs to be managed by the JavaFX application's lifecycle, 
specifically within its `init()` method.

**2. JavaFX Application Class:**
This class extends `javafx.application.Application` and manages the JavaFX UI 
lifecycle.
 - Example: `com.example.javafxspringapp.JavafxApplication.java`

```Java
package com.example.javafxspringapp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class JavafxApplication extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        // Initialize Spring Boot context
        applicationContext = new SpringApplicationBuilder(JavafxSpringAppApplication.class).run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // This method is called after init() and is where the primary JavaFX stage is set up.
        // FxWeaver will be used here to load the initial FXML view.
        applicationContext.publishEvent(new StageReadyEvent(primaryStage));
    }

    @Override
    public void stop() {
        // Close Spring Boot context gracefully
        applicationContext.close();
        Platform.exit(); // Ensure JavaFX platform exits
    }

    public static void main(String args) {
        launch(args); // Launch the JavaFX application
    }
}
```
- A custom StageReadyEvent is introduced to bridge the JavaFX start method with Spring's event system, 
allowing a Spring-managed listener to handle the initial stage setup. This decouples the UI initialization 
from the core JavaFX Application class, making it more Spring-friendly.

**3. `StageReadyEvent` and `StageReadyEventListener`:**
 - Create a simple event class:
   
```Java
package com.example.javafxspringapp;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

public class StageReadyEvent extends ApplicationEvent {
    public StageReadyEvent(Stage stage) {
        super(stage);
    }

    public Stage getStage() {
        return (Stage) getSource();
    }
}
```
 - Create an event listener to handle the stage setup, leveraging `FxWeaver`:

```Java
package com.example.javafxspringapp;

import com.example.javafxspringapp.controller.MainController; // Assuming MainController is in this package
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StageReadyEventListener implements ApplicationListener<StageReadyEvent> {

    private final ApplicationContext applicationContext;
    private final String applicationTitle;

    public StageReadyEventListener(ApplicationContext applicationContext,
                                   @Value("${app.title:JavaFX Spring App}") String applicationTitle) {
        this.applicationTitle = applicationTitle;
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.getStage();
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(MainController.class); // Load the main FXML view
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(this.applicationTitle);
        stage.show();
    }
}
```

 - This listener, managed by Spring, receives the `Stage` object and uses `FxWeaver` to load the primary FXML 
view, injecting Spring-managed dependencies into its controller.
This significantly shortens the `onApplicationEvent` method compared to manual FXML loading and controller 
instantiation.

### 3.4. Basic FXML Setup and Controller Integration
For JavaFX to display content, an FXML file defines the user interface layout, and a corresponding controller 
class handles its logic. `javafx-weaver-spring-boot-starter` simplifies the connection between these components 
and the Spring context.

**1. Create an FXML file:**
In `src/main/resources/com/example/javafxspringapp/controller/`, create `main.fxml`.

```XML
<?xml version="1.0" encoding="UTF-8"?>
<?import javafx.scene.control.*?>
<?import javafx.scene.layout.*?>

<AnchorPane minHeight="150.0" minWidth="300.0" xmlns="http://javafx.com/javafx/11.0.2" xmlns:fx="http://javafx.com/fxml/1" fx:controller="com.example.javafxspringapp.controller.MainController">
    <children>
        <Label layoutX="14.0" layoutY="51.0" text="Welcome to JavaFX Spring Boot App!" />
    </children>
</AnchorPane>
```

**2. Create a JavaFX Controller:**
In `src/main/java/com/example/javafxspringapp/controller/`, create `MainController.java`.

```Java
package com.example.javafxspringapp.controller;

import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("main.fxml") // Links this controller to main.fxml
public class MainController {
    // You can inject Spring beans here, e.g., services, repositories
    // @Autowired private MyService myService;

    // @FXML methods for UI events
    // @FXML public void initialize() { /* Initialization logic */ }
}
```

 - The `@Component` annotation registers `MainController` as a Spring bean, allowing it to be managed by the 
Spring context. The `@FxmlView("main.fxml")` annotation tells `FxWeaver` to associate this controller with the 
specified FXML file, assuming it's in the same package within the resources folder. This setup allows for 
Spring's powerful dependency injection to be utilized within JavaFX controllers, bridging the two frameworks 
effectively.

## 4. Data Persistence with MySQL
MySQL will serve as the primary data persistence layer for the application, handling core business data. 
Spring Data JPA simplifies interaction with relational databases by providing a high-level abstraction over 
JPA.

### 4.1. Setting up Your MySQL Database
Before configuring the Spring Boot application, a MySQL database instance must be running and accessible. 
This involves:

**1. MySQL Server Installation:** 
Ensure a MySQL server is installed and running on the local machine or a network-accessible server.

**2. Database Creation:** 
Create a dedicated database for the application (e.g., `app_db`).

**3. User and Permissions:**
Create a specific user for the application with appropriate permissions to access and modify the `app_db` 
database. This adheres to security best practices by limiting database access to only what is necessary for 
the application.

### 4.2. Configuring Maven Dependencies for MySQL Connector and Spring Data JPA
The project's `pom.xml` must include the necessary dependencies for Spring Data JPA and the MySQL JDBC driver. 
These should have been added during the Spring Initializr step.

Verify the presence of:
 - `spring-boot-starter-data-jpa`: This starter brings in all necessary dependencies for JPA, including 
Hibernate (the default JPA implementation).
 - `mysql-connector-java`: This is the official JDBC driver for MySQL, enabling the Java application to 
communicate with the MySQL database.

Example `pom.xml` snippet for these dependencies:

```XML
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```

The `runtime` scope for `mysql-connector-java` indicates that this dependency is only needed at runtime and 
not for compilation, which is typical for JDBC drivers.

### 4.3. `application.properties` Configuration for MySQL Connection
The `application.properties` file (or `application.yml`) located in `src/main/resources` is central to 
configuring Spring Boot applications. It is used to define various application-related properties, including 
database connectivity.

To connect to the MySQL database, the following properties must be defined:

**Table: MySQL and MongoDB `application.properties` Configuration**

| Property Key                                  | Value Example                          | Description                                                                          | 
|-----------------------------------------------|----------------------------------------|--------------------------------------------------------------------------------------| 
| `spring.datasource.url`                       | `jdbc:mysql://localhost:3306/app_db`   | JDBC URL for the MySQL database. Replace `localhost` and `app_db` as needed.         |
| `spring.datasource.username`                  | `app_user`                             | Username for database access.                                                        |
| `spring.datasource.password`                  | `your_password`                        | Password for database access.                                                        |
| `spring.datasource.driver-class-name`         | `com.mysql.cj.jdbc.Driver`             | Fully qualified class name of the JDBC driver.                                       |
| `spring.jpa.hibernate.ddl-auto`               | `update`                               | Controls Hibernate's schema generation strategy.                                     |
| `spring.jpa.properties.hibernate.dialect`     | `org.hibernate.dialect.MySQL8Dialect`  | Specifies the Hibernate dialect for MySQL 8.                                         |
| `spring.data.mongodb.host`                    | `localhost`                            | Hostname or IP address of the MongoDB instance.                                      |
| `spring.data.mongodb.port`                    | `27017`                                | Port number for MongoDB connection (default is 27017).                               |
| `spring.data.mongodb.database`                | `audit_db`                             | Name of the MongoDB database for audit logs.                                         |
| `spring.data.mongodb.username`                | `audit_user`                           | Username for MongoDB authentication (if enabled).                                    |
| `spring.data.mongodb.password`                | `audit_password`                       | Password for MongoDB authentication (if enabled).                                    |
| `spring.data.mongodb.authentication-database` | `admin`                                | Database to authenticate against (if different from `spring.data.mongodb.database`). |
| `app.title`                                   | `My JavaFX Spring Boot App`            | Custom application title for JavaFX window.                                          |

The `spring.jpa.hibernate.ddl-auto` property is crucial for database schema management during development. 
Setting it to `update` allows Hibernate to update the schema incrementally without dropping existing data, 
which is suitable for iterative development.

Other options include `create` (drops and recreates schema, useful for fresh development environments), 
`create-drop` (drops schema on shutdown), `validate` (validates schema without modification), and `none` 
(disables Hibernate schema management, requiring manual database management). 

The choice of `ddl-auto` directly impacts data integrity and development workflow. For production environments, 
`validate` or `none` is typically preferred, with schema changes managed through dedicated migration tools.

### 4.4. Defining JPA Entities and Repositories
With the database connection configured, the next step involves defining the data model using JPA entities 
and creating repositories for data access.

**1. JPA Entities:**
Create Java classes annotated with `@Entity` to represent tables in the MySQL database. These classes will 
define the structure of the data and their relationships.

```Java
package com.example.javafxspringapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data; // From Lombok dependency

@Entity
@Data // Generates getters, setters, equals, hashCode, toString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;

    // Constructors, if not using @NoArgsConstructor and @AllArgsConstructor from Lombok
}
```

 - The `@Data` annotation from Lombok automatically generates boilerplate code, improving code conciseness.

**2. Spring Data JPA Repositories:**
Create interfaces that extend Spring Data JPA's `JpaRepository`. This automatically provides CRUD 
(Create, Read, Update, Delete) operations and allows for defining custom query methods by simply declaring 
their method signatures.

```Java
package com.example.javafxspringapp.repository;

import com.example.javafxspringapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Data JPA automatically generates implementation for methods like:
    // List<User> findByUsername(String username);
}
```

 - Spring Data JPA dynamically creates an implementation of this interface at runtime, eliminating the need 
to write manual repository implementations. This significantly accelerates data access layer development.

## 5. Audit Logging with MongoDB
MongoDB will be used as a NoSQL database for audit logging, providing a flexible schema suitable for storing 
diverse log entries without strict structural constraints. Spring Data MongoDB simplifies interaction with 
MongoDB.

### 5.1. Setting up Your MongoDB Instance
Similar to MySQL, a MongoDB instance must be running and accessible before application configuration.

**1. MongoDB Installation:**
Install MongoDB Community Server on the local machine or a network server.

**2. Start MongoDB:**
Ensure the MongoDB daemon (`mongod`) is running.

**3. Database Creation:**
While MongoDB can create databases on first use, it is good practice to identify the target database for audit
logs (e.g., `audit_db`).

**4. User and Authentication (Recommended):**
For production or multi-user environments, configure authentication and create a dedicated user with 
appropriate roles for the `audit_db`. This enhances security.

### 5.2. Configuring Maven Dependencies for Spring Data MongoDB
The `spring-boot-starter-data-mongodb` dependency, added during project creation, provides all necessary 
components for integrating Spring Boot with MongoDB.

Verify the presence of:

 - `spring-boot-starter-data-mongodb`: This starter pulls in spring-data-mongodb and the MongoDB Java driver.

Example `pom.xml` snippet:

```XML
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```

The Spring Data Release Train BOM (Bill of Materials) is implicitly used by Spring Boot to manage compatible 
versions of Spring Data modules like MongoDB, ensuring consistency across the ecosystem.

### 5.3. `application.properties` Configuration for MongoDB Connection
The `application.properties` file is used to configure the connection to the MongoDB instance.Refer to the 
"MySQL and MongoDB `application.properties` Configuration" table in Section 4.3 for the relevant properties:
 - `spring.data.mongodb.host`
 - `spring.data.mongodb.port`
 - `spring.data.mongodb.database`
 - `spring.data.mongodb.username` (if authentication is enabled)
 - `spring.data.mongodb.password` (if authentication is enabled)
 - `spring.data.mongodb.authentication-database` (if authentication is enabled and the authentication database 
is different) 

These properties direct the Spring Data MongoDB client to the correct MongoDB instance and database for 
storing audit logs.

### 5.4. Enabling Spring Data MongoDB Auditing
Spring Data provides robust auditing capabilities that automatically capture creation and modification 
timestamps, and user information for entities. This is particularly useful for audit logging.

**1. Enable Auditing:** 
Add the `@EnableMongoAuditing` annotation to a Spring `@Configuration` class. This annotation activates 
Spring Data MongoDB's auditing features.

```Java
package com.example.javafxspringapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing // Activates MongoDB auditing
public class MongoAuditConfig {
   // Optionally, define an AuditorAware bean if you need to capture current user
   // @Bean
   // public AuditorAware<String> auditorAware() {
   //     return () -> Optional.of("system"); // Replace with actual user retrieval logic
   // }
}
```

**2. Define Auditable Entities:** 
Create or modify MongoDB document classes (entities) to include auditing fields. These fields will be 
automatically populated by Spring Data MongoDB when documents are saved or updated.

```Java
package com.example.javafxspringapp.audit;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "audit_logs") // Specifies the collection name
@Data
public class AuditLogEntry {
   @Id
   private String id;
   private String eventType;
   private String description;
   private String userId; // Manually set or use @CreatedBy if AuditorAware is configured

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime lastModifiedAt;

    // Additional fields specific to audit events
}
```

 - The `@CreatedDate` and `@LastModifiedDate` annotations instruct Spring Data MongoDB to automatically populate 
these fields with the creation and last modification timestamps, respectively. This mechanism provides a 
robust and automated way to maintain audit trails within the MongoDB database, separating audit concerns from 
primary data persistence.

## 6. Configuring for LAN Accessibility
For the application to function over a Local Area Network (LAN), allowing other devices on the same network 
to connect to its backend services (e.g., if the JavaFX client connects to a Spring Boot REST API on a server 
machine), specific network configurations are required.

### 6.1. Spring Boot Server Configuration for Network Access
By default, a Spring Boot application's embedded server (like Tomcat) typically binds to `localhost` 
(127.0.0.1), making it accessible only from the machine it is running on. To enable access from other devices 
on the LAN, the server must bind to a network interface accessible to other machines.

This is configured in `application.properties`:

```Properties
server.port=8080
server.address=0.0.0.0
```

 - `server.port`: Specifies the port on which the Spring Boot application will listen for incoming connections. 
Port `8080` is a common default.
 - `server.address`: Setting this property to `0.0.0.0` instructs the embedded server to bind to all available 
network interfaces on the host machine. This allows the application to be accessible from any IP address 
assigned to the host, including its local network IP address (e.g., `192.168.1.X`). If this property is 
omitted, Spring Boot typically binds to `localhost`.

Once configured, the application can be accessed by other devices on the LAN using the host machine's local 
IP address and the configured port (e.g., `http://192.168.1.100:8080`).

### 6.2. Firewall Configuration for Windows and Linux
Even with the Spring Boot server configured to listen on all interfaces, the operating system's firewall can 
block incoming connections, preventing other devices on the LAN from reaching the application. Firewall 
rules must be adjusted to permit inbound traffic on the specified port (e.g., 8080).

#### Windows Firewall Configuration:
**1. Open Windows Defender Firewall with Advanced Security:**
Search for "Windows Defender Firewall with Advanced Security" in the Start menu.

**2. Create New Inbound Rule:** 
In the left pane, select `Inbound Rules`, then in the right pane, click `New Rule`....

**3. Rule Type:**
Select `Port` and click `Next`.

**4. Protocol and Port:**
Choose `TCP` and specify the `Specific local ports` as `8080` (or your chosen port). Click `Next`.

**5. Action:**
Select `Allow the connection`. Click `Next`.

**6. Profile:**
Choose the network profiles where the rule should apply (e.g., `Domain`, `Private`, `Public`). For a typical 
home/office LAN, Private is often sufficient. Click `Next`.

**7. Name and Finish:**
Give the rule a descriptive name (e.g., "Spring Boot App 8080") and click `Finish`.

#### Linux Firewall Configuration (using `firewalld` or `ufw`):
 - **For `firewalld` (e.g., CentOS, Fedora, RHEL):**

```Bash
sudo firewall-cmd --permanent --zone=public --add-port=8080/tcp
sudo firewall-cmd --reload
```

This command permanently opens TCP port 8080 in the `public` zone.

 - **For `ufw` (Uncomplicated Firewall, e.g., Ubuntu, Debian):**

```Bash
sudo ufw allow 8080/tcp
sudo ufw enable # if ufw is not already enabled
sudo ufw status
```

This allows incoming TCP connections on port 8080.

Failure to configure the firewall correctly is a common reason why applications are not accessible over the 
network, even if the application itself is running correctly.

### 6.3. General Network Considerations for LAN Deployment
Beyond basic port opening, several network considerations are important for robust and secure LAN deployment:
 - **IP Addressing:**
Ensure the host machine has a stable IP address on the LAN. While DHCP assigns dynamic IPs, a static IP or a 
DHCP reservation is recommended for servers to ensure consistent accessibility.
 - **Subnet and Routing:**
Verify that other devices on the LAN are on the same subnet or that routing is correctly configured between 
subnets if the application is intended to be accessed across different network segments.
 - **Security Best Practices:**
    - **Least Privilege:**
   Only open ports that are absolutely necessary for the application's operation. Limiting port exposure 
   drastically reduces the attack surface.
    - **IP Whitelisting:**
   For enhanced security, consider restricting access to the application's port to specific known IP 
   addresses within the LAN, rather than allowing connections from any IP. This "whitelist" approach prevents 
   unauthorized access from unknown sources.
    - **Application Layer Inspection:**
   While basic firewalls operate at the network layer, for more advanced security, consider application-layer 
   firewalls that can inspect traffic based on application behavior, offering protection against attacks 
   that might bypass port-based rules.
    - **Logging and Monitoring:**
   Implement robust logging and monitoring for network traffic and application access attempts. Regularly 
   reviewing these logs can help identify unusual patterns or potential security breaches.
    - **Updates and Patching:**
   Regularly update the operating system, JDK, and all application dependencies to patch known 
   vulnerabilities.

 These measures contribute to a more secure and reliable application deployment within a LAN environment, 
 mitigating risks associated with network exposure.

### 7. Running and Verifying the Application
After configuring the environment, project, and network settings, the application is ready to be run and 
verified within IntelliJ IDEA.

### 7.1. Creating a Custom Run Configuration in IntelliJ IDEA for JavaFX/Spring Boot
Due to the dual main class architecture (JavaFX's `launch()` and Spring Boot's context initialization), a 
custom run configuration is necessary to correctly start the application. The default Spring Boot run 
configuration typically targets the `@SpringBootApplication` class's `main` method, which is not the primary 
entry point for a JavaFX application.

To create a custom run configuration:

**1. Open Run/Debug Configurations:**
Go to `Run > Edit Configurations`... from the main menu.

**2. Add New Application Configuration:**
Click the `+` icon and select `Application`.

**3. Configure Settings:**
 - **Name:** 
Provide a descriptive name, e.g., `JavaFX Spring Boot App`.
 - **Main class:** 
Specify the fully qualified name of your **JavaFX Application class** (e.g., 
`com.example.javafxspringapp.JavafxApplication`). This is the class containing 
`public static void main(String args) { launch(args); }`.
 - **JRE:** 
Ensure `Liberica JDK 24.0.1` is selected.
 - **Use classpath of module:** 
Select your project's main module (e.g., `javafx-spring-app.main`).
 - **VM options:** 
No specific VM options are typically required for basic JavaFX/Spring Boot integration 
with Liberica Full JDK, as it bundles JavaFX. However, if issues arise or specific JavaFX modules are needed,
`--module-path <path_to_javafx_sdk_lib> --add-modules javafx.controls,javafx.fxml` might be used, though less 
likely with Liberica Full.
 - **Before launch:**
Ensure Build (or Build Project) is selected to compile the project before running.

**4. Apply and Run:**
Click `Apply`, then `OK`. Now, select this new run configuration from the dropdown menu in the toolbar and 
click the `Run` button.

IntelliJ IDEA will execute the JavaFX application's `main` method, which in turn initializes the Spring Boot 
context and then launches the JavaFX UI. The application logs will appear in the Run tool window.

### 7.2. Testing Database Connectivity and Application Functionality
Once the application is running, it is crucial to verify that all components are functioning as expected, 
particularly the database connections and the core application logic.

**1. Observe Console Output:**
The IntelliJ IDEA Run tool window will display Spring Boot's startup logs. Look for messages indicating 
successful connections to both MySQL and MongoDB, such as "Started JavafxSpringAppApplication" and no 
`Connection refused` or `MongoTimeoutException` errors.

**2. Test MySQL Persistence:**
Implement a simple UI interaction in your JavaFX application (e.g., a button click) that triggers a service 
call to save or retrieve data from MySQL via your `UserRepository`. Verify that data is correctly persisted 
and retrieved in the MySQL database using a database client.

**3. Test MongoDB Audit Logging:**
Implement a mechanism to trigger an audit log entry (e.g., a user login event, a data modification). Verify 
that corresponding documents are created in your audit_logs collection in the MongoDB audit_db database, 
with `@CreatedDate` and `@LastModifiedDate` fields automatically populated. Use a MongoDB client to inspect 
the collection.

**4. Test LAN Accessibility:**
From another computer on the same LAN, attempt to access any exposed Spring Boot REST endpoints using the 
host machine's IP address and port (e.g., `http://<Host_IP>:8080/api/users`). If the JavaFX application 
itself is designed for multi-user access over LAN (e.g., if it's a client-server model where the JavaFX UI 
is a client to the Spring Boot backend), verify client connections from other machines.

### 7.3. Common Issues and Troubleshooting Tips
During development and deployment, various issues can arise. Understanding common pitfalls and their 
resolutions is crucial for efficient troubleshooting.

**Table: Common Issues and Troubleshooting Steps**

| Issue                                   | Description                                                            | Troubleshooting Steps                                                                                                                                                                      | Source Snippets                                                                                                                                                                   |                                                                                                                                                                                                                                                              |                                                                                                                                                                                                                                                                                                    |                                                                                                                                               |
|-----------------------------------------|------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------| 
| **Database Connection Failures**        | `java.sql.SQLException`, `MongoTimeoutException`, `Connection refused` | **1. Verify Credentials:** Double-check `spring.datasource.username`, `password` (MySQL) and `spring.data.mongodb.username`, `password` (MongoDB) in `application.properties`.             | **2. Check JDBC/MongoDB URI:** Ensure `spring.datasource.url` (MySQL) and `spring.data.mongodb.host`, `port`, `database` (MongoDB) are correct. Typographical errors are common.  | **3. Network Connectivity:** Use `ping <DB_Host_IP>` and `telnet <DB_Host_IP> <DB_Port>` (e.g., `telnet localhost 3306` for MySQL, telnet `localhost 27017` for MongoDB) from the application host to verify network reachability and port openness.         | **4. Database Server Status:** Ensure MySQL and MongoDB services are running on their respective hosts. Check database server logs for errors.<br/>**5. Firewall:** Verify that the database ports (e.g., 3306 for MySQL, 27017 for MongoDB) are open in the firewall on the database server host. | **6. Driver Compatibility:** Ensure the MySQL Connector/J and MongoDB Java Driver versions are compatible with your database server versions. |
| **Application Not Accessible Over LAN** | Cannot access application from other machines using host IP.           | 1. `server.address`: Confirm `server.address=0.0.0.0` (or omitted) in `application.properties` to bind to all interfaces.                                                                  | **2. Firewall:** Verify that the application's port (e.g., 8080) is open in the firewall of the *host machine* where the Spring Boot application is running.                      | **3. IP Address:** Ensure other devices are using the correct network IP address of the host machine, not `localhost`.<br/>**4. Network Configuration:** Check subnet, router settings, and any network segmentation that might be preventing communication. |                                                                                                                                                                                                                                                                                                    |                                                                                                                                               |
| **JavaFX UI Not Displaying**            | Blank window, application crash on startup.                            | **1. JDK Configuration:** Ensure Liberica Full JDK 24.0.1 is correctly set as the Project SDK in IntelliJ IDEA.4                                                                           | **2. Run Configuration:** Verify the custom run configuration correctly points to the JavaFX `Application` class as the main class.                                               | **3. FXML Path:** Check that `@FxmlView` annotation in controllers correctly specifies the FXML file path relative to the resources folder.                                                                                                                  | **4. Controller Issues:** Ensure JavaFX controllers are annotated with `@Component` and that required Spring beans are correctly injected.                                                                                                                                                         |                                                                                                                                               |
| **Spring Context Not Initialized**      | `NullPointerException` on Spring-managed beans in JavaFX controllers.  | 1. `SpringApplicationBuilder`: Confirm `applicationContext = new SpringApplicationBuilder(JavafxSpringAppApplication.class).run()`; is correctly called in the JavaFX `init()` method.     | 2. `@Component`: Ensure JavaFX controllers that require Spring injection are annotated with `@Component`.                                                                         | **3. FxWeaver Usage:** Verify `FxWeaver` is used to load FXML views and controllers, as it handles Spring bean injection into JavaFX controllers.                                                                                                            |                                                                                                                                                                                                                                                                                                    |                                                                                                                                               |

Systematic troubleshooting, beginning with basic connectivity checks and progressing to application-specific 
configurations, is critical for resolving issues efficiently. Leveraging detailed logs from both the Spring 
Boot application and the database servers provides invaluable diagnostic information.
## Conclusion
This report has provided a comprehensive, step-by-step guide for initiating a robust JavaFX and Spring Boot 
application within IntelliJ IDEA Community Edition, incorporating Maven for build management, MySQL for 
primary data persistence, and MongoDB for audit logging, all while ensuring LAN accessibility and leveraging 
Liberica Full JDK 24.0.1.

The foundational steps involved meticulous environment setup, including the selection
and configuration of Liberica Full JDK, which significantly simplifies JavaFX dependency management by 
bundling the necessary components. Project creation via Spring Initializr, integrated within IntelliJ IDEA, 
proved to be an efficient method for generating a stable project structure with essential dependencies, 
minimizing configuration overhead and ensuring version compatibility.

A key architectural consideration addressed was the integration of JavaFX and Spring Boot, necessitating a 
dual main class approach. The JavaFX application manages its UI thread and lifecycle while delegating Spring 
context initialization, a pattern effectively streamlined by the `javafx-weaver-spring-boot-starter` library. 
This library facilitates the injection of Spring-managed beans into JavaFX controllers, bridging the two 
frameworks seamlessly.

For data persistence, MySQL was configured with Spring Data JPA, providing robust relational database 
capabilities. The `application.properties` file served as the central point for database connection settings, 
including critical `ddl-auto` options that manage schema evolution during development. Concurrently, MongoDB 
was integrated for audit logging, leveraging its flexible NoSQL schema and Spring Data MongoDB's auditing 
features to automatically capture event metadata.

Ensuring LAN accessibility required specific Spring Boot server configurations to bind to all network 
interfaces and careful adjustment of operating system firewalls to permit incoming connections on the 
application's designated port. This aspect highlighted the importance of network security considerations, 
such as port limitation and IP whitelisting, for secure deployment.

Finally, the report detailed the creation 
of a custom run configuration in IntelliJ IDEA, tailored for the JavaFX/Spring Boot application, and provided 
a structured approach to verifying functionality and troubleshooting common issues. The systematic diagnosis 
of database connectivity problems and network access challenges emphasized the importance of methodical 
debugging techniques.

The successful implementation of this multi-technology stack provides a solid foundation for developing 
scalable and maintainable desktop applications with robust backend services. Future development efforts could
focus on implementing specific business logic, expanding the user interface, enhancing security features 
(e.g., user authentication and authorization), and optimizing database performance for production 
environments.