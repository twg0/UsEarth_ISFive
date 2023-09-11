FROM openjdk:17-jdk-slim
ARG JAR_FILE=UsEarth-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} UsEarth.jar
ENTRYPOINT ["java","-jar","/UsEarth.jar"]