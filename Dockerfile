# Build stage
FROM maven:3-openjdk-17 AS build
WORKDIR /app

COPY . .
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:17-jdk-slim
WORKDIR /app

# Nếu WAR được build đúng, dòng này phải đúng tên file
COPY --from=build /app/target/Note-0.0.1-SNAPSHOT.war Note.war

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Note.war"]
