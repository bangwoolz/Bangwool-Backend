FROM openjdk:17-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENV SPRING_PROFILES_ACTIVE=prod
#ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=prod", "/app.jar"]
ENTRYPOINT ["java","-jar", "/app.jar"]