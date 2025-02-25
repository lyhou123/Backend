#build file jar file here
FROM openjdk:21-jdk-slim  AS builder

COPY . .
# Ensure Gradle Wrapper is executable
RUN chmod +x ./gradlew

# Build the project and ignore tests
RUN ./gradlew build --no-daemon -x test

# Use a base image with JDK 21
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the local machine to the container
COPY --from=builder build/libs/*.jar  app.jar

# Create a directory for configuration files
RUN mkdir -p /app/config

# Copy the application.yml file
COPY src/main/resources/application-prod.yml /app/config/

# Expose the port your application will run on
EXPOSE 8080

# Define the command to run your application
ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=prod", "app.jar", "--spring.config.location=file:/app/config/application-prod.yml"]