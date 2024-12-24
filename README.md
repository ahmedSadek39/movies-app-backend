# Back-End Documentation (Spring Boot)

#### Technologies:
- Java 17
- Spring Boot 3.4.1
- MySQL (Cloud Database)

---------------------------
#### Prerequisites:
1. Java 17 installed (`java -version`).
2. Maven Wrapper included in the project.
3. A MySQL cloud database instance with the following credentials:
   - Host
   - Port
   - Username
   - Password
   - Database Name

---------------------------
#### Configuration:
1. Update the `application.properties` file with the database configuration:

   spring.datasource.url=jdbc:mysql://<your-cloud-host>:<port>/<database-name>
   spring.datasource.username=<username>
   spring.datasource.password=<password>
   spring.jpa.hibernate.ddl-auto=update

   Replace placeholders (`<your-cloud-host>`, `<username>`, etc.) with actual MySQL credentials.

---------------------------
#### Running the Application:
1. Navigate to the back-end project directory:
   cd movies-app-backend

2. Build the application:
   ./mvnw clean package

3. Run the application:
   java -jar target/<your-application-name>.jar

4. Access the API on `http://localhost:8081`.

5. Access Swagger on `http://localhost:8081/swagger-ui/index.html#/`
