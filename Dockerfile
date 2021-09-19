FROM maven:3.6.0-jdk-11-slim as build
WORKDIR /backend
COPY src ./src
COPY pom.xml ./
RUN mvn clean install



FROM adoptopenjdk/openjdk11:jdk-11.0.10_9-alpine as dev
ARG JAR_FILE=/backend/target/backend-0.0.1-SNAPSHOT.jar
COPY --from=build ${JAR_FILE} backend-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","backend-0.0.1-SNAPSHOT.jar"]