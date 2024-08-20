FROM alpine/java:21.0.4-jre
ARG JAR_FILE=build/libs/play-with-micronaut-0.1.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]