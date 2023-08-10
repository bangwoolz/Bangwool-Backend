FROM openjdk:17-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
#ENV SPRING_PROFILES_ACTIVE=
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=dev", "/app.jar"]
#ENTRYPOINT ["java","-jar", "/app.jar"]