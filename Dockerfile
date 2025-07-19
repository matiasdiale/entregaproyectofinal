FROM openjdk:17
COPY target/proyecto.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
