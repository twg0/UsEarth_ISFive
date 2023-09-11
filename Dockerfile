FROM openjdk:17-jdk-slim

WORKDIR /home/ubuntu/

COPY demo-0.0.1-SNAPSHOT.jar .

CMD java -jar demo-0.0.1-SNAPSHOT.jar