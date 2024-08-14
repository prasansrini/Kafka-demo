FROM openjdk:21-slim

WORKDIR /kafka-demo

COPY target/OUTPUT.jar /kafka-demo/kafka-demo.jar

EXPOSE 8090

ENV JAVA_OPTS="-Dspring.profiles.active=local"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /kafka-demo/kafka-demo.jar"]

# First stage: build the application
# FROM maven:3.8.3-jdk-21 AS build
# COPY . /kafka-demo
# WORKDIR /kafka-demo
# RUN mvn package -DskipTests

# # Second stage: create a slim image
# FROM openjdk:21-slim

# COPY --from=build kafka-demo/target/OUTPUT.jar /kafka-demo/kafka-demo.jar

# ENV JAVA_OPTS="-Dspring.profiles.active=local"

# ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /kafka-demo/kafka-demo.jar"]
