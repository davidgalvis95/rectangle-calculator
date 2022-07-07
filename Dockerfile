FROM eclipse-temurin:17-jdk-focal
WORKDIR /app
#COPY target/rectangle-calculator-0.0.1-SNAPSHOT.jar rectangle-calculator-1.0.0.jar
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]