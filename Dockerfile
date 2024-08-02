# Stage 1: Build
FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /app

# Copy pom.xml and install dependencies separately to leverage Docker cache
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the application code and build it
COPY . .
RUN mvn clean package -DskipTests

# List files in the target directory to verify .jar creation
RUN ls -l /app/target

# Stage 2: Runtime
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/holistic-daily-care-0.0.1-SNAPSHOT.jar app.jar

# Run the application
CMD ["java", "-jar", "app.jar"]
