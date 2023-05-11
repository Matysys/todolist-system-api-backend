FROM openjdk:17-jdk-slim-buster
WORKDIR /todo-list-system
COPY out/artifacts/todo_list_system_jar.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]