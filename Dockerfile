# ---------------------------------------------------------------
# Dockerfile for D387 Advanced Java - Landon Hotel Application
#
# This Dockerfile builds a single image containing the full Spring Boot
# back end and Angular front end. The Maven build process invokes the
# Angular CLI to compile the front end, then packages the resulting
# static assets into the Spring Boot JAR.
#
# Per rubric requirement C1: includes all code and modifications from B1-B3.
# ---------------------------------------------------------------

# Stage 1: Build stage - Maven + JDK 17 + Node.js for Angular
FROM maven:3.8.6-eclipse-temurin-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Install Node.js (required by exec-maven-plugin to build the Angular front end)
RUN apt-get update && apt-get install -y curl \
    && curl -fsSL https://deb.nodesource.com/setup_18.x | bash - \
    && apt-get install -y nodejs \
    && npm install -g @angular/cli@14 \
    && rm -rf /var/lib/apt/lists/*

# Copy the Maven project descriptor first (allows Docker to cache dependencies)
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

# Copy the rest of the source code
COPY src ./src

# Install Angular dependencies
RUN cd src/main/UI && npm install

# Build the application (compiles Angular, then packages the Spring Boot JAR)
RUN mvn clean package -DskipTests

# ---------------------------------------------------------------
# Stage 2: Runtime stage - lightweight JRE-only image
# ---------------------------------------------------------------
FROM eclipse-temurin:17-jre

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the Spring Boot port
EXPOSE 8080

# Define how to start the application
ENTRYPOINT ["java", "-jar", "app.jar"]