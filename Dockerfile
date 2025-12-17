# Stage 1: Build the jar using Maven + JDK
FROM maven:3.9.2-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom and source code
COPY pom.xml .
COPY src ./src

# Build the jar
RUN mvn clean package -DskipTests

# Stage 2: Use smaller JDK image for running
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port
EXPOSE 8080

# Run the Spring Boot jar
ENTRYPOINT ["java","-jar","app.jar"]
