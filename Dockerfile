# Use Eclipse Temurin Java 17 JDK
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy the built jar
COPY target/*.jar app.jar

# Expose port (Render will map its dynamic port)
EXPOSE 8080

# Run the Spring Boot jar
CMD ["java", "-jar", "app.jar"]
