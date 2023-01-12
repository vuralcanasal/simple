FROM openjdk:19 AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:19
WORKDIR simple
COPY --from=build target/*.jar simple.jar
ENTRYPOINT ["java", "-jar", "simple.jar"]