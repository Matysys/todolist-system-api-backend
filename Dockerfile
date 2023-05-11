FROM openjdk:17-jdk-slim-buster
WORKDIR /todo-list-system
COPY arquivoJar/todo-list-system-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]