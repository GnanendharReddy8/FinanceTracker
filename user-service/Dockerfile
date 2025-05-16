# Use official JDK base image
FROM openjdk:17-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the jar file
COPY target/user-service-0.0.1-SNAPSHOT.jar user-service.jar

# Expose the port
EXPOSE 8082

# Run the jar
ENTRYPOINT ["java", "-jar", "user-service.jar"]
