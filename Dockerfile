# Build stage
FROM maven:3-openjdk-17 AS build
WORKDIR /app

COPY . .
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:17-jdk-slim
WORKDIR /app

# Sửa lỗi cú pháp ở dòng dưới: bỏ khoảng trắng sau `--`
COPY --from=build /app/target/Note-0.0.1-SNAPSHOT.war Note.war

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "Note.war"]
