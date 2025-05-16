# Use official JDK base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR into the container
COPY target/txn-service-0.0.1-SNAPSHOT.jar txn-service.jar

# Expose service port
EXPOSE 8081

# Run the JAR
ENTRYPOINT ["java", "-jar", "txn-service.jar"]
