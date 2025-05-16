FROM openjdk:17-jdk-slim

# Install fonts + headless rendering dependencies
RUN apt-get update && apt-get install -y \
    fonts-dejavu-core \
    fontconfig \
    libfreetype6 \
    libxrender1 \
    libxtst6 \
    libxi6 \
    libxext6 \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY target/report-service-1.0-SNAPSHOT.jar report-service.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "report-service.jar"]
