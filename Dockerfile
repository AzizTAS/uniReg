FROM openjdk:17 AS build
COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve
COPY src src
RUN ./mvnw package -DskipTests

FROM openjdk:17
WORKDIR unireg
COPY --from=build target/*.jar unireg.jar
ENTRYPOINT ["java", "-jar", "unireg.jar"]