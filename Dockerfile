FROM openjdk:17-jdk-slim-buster
WORKDIR /todo-list-system
COPY build/libs/todo_list_system-0.0.1-SNAPSHOT.jar.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]