# Rebind

Rebind is a Spring Boot web application for running scenario-based escape game sessions. The project manages users, scenarios and file uploads while using a separate Node.js service for handling uploads.

## Tech Stack

- **Java 8** & **Spring Boot 2.7**
- **JSP** views served by an embedded Tomcat container
- **MyBatis** for database access
- **MariaDB** & **Redis** provided via Docker
- **Node.js** upload microservice with Express
- **Nginx** container for serving uploaded files

## Architecture

```
[Client] <--HTTP--> [Spring Boot App]
                          |
                          | REST call
                          v
                  [upload-server (Node)] --writes--> [./uploads]
                          |
                          v
                  [file-server (Nginx)] <----- serves files

MariaDB and Redis containers provide persistence and caching.
```

The main Spring Boot application uses Spring Security for authentication. MyBatis mappers handle persistence to MariaDB. Redis is configured for caching and other data structures. Uploaded files are stored under `./uploads` and are reachable via the Nginx file server at `http://localhost:8081/uploads`.

## Setup

1. **Build the application**

   ```bash
   ./mvnw clean package
   ```

   This produces `target/Rebind-0.0.1-SNAPSHOT.war`.

2. **Start the services**

   Docker Compose builds and starts all supporting containers and the upload server.

   ```bash
   docker compose up -d
   ```

   Services started:

   - `mariadb` on port `3306`
   - `redis` on port `6379`
   - `file-server` (Nginx) on port `8081`
   - `upload-server` (Node.js) on port `8082`

3. **Run the Spring Boot app**

   After the database and other services are running, start the application:

   ```bash
   ./mvnw spring-boot:run
   ```

   The app will be available at `http://localhost:8080`.

## File Uploads via `upload-server`

`upload-server` is a simple Express application used to receive raw file uploads. Files are written to `/uploads` which is shared with the Nginx `file-server` container.

- Endpoint: `PUT /upload?subdir=YYYY/MM/DD&filename=<storedName>`
- Request body: raw file bytes
- Response: JSON with success message or error

The Spring Boot service sends a PUT request to this endpoint when uploading scenario images. Uploaded files become available through Nginx at:

```
http://localhost:8081/uploads/<subdir>/<storedName>
```

See `upload-server/app.js` for the server implementation and `FileUploadService` for how the Spring application uses it.
