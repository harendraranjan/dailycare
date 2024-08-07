# Build stage
FROM maven:3.8.1-openjdk-17-slim as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /app/target/Holistic-Daily-Care-0.0.1-SNAPSHOT.jar /app/app.jar
CMD ["java", "-jar", "/app/app.jar"]
