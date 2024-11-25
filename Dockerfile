# Step 1: Use a lightweight OpenJDK image as the base
FROM openjdk:21-jdk-slim

# Step 2: Add a label for metadata
LABEL maintainer="daali.22.ss@gmail.com"

# Step 3: Add application JAR
ARG JAR_FILE=target/demo-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} app.jar

# Step 4: Expose port 8080
EXPOSE 8084

# Step 5: Define the command to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
