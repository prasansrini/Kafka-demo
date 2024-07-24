#FROM openjdk:21-jdk-alpine
FROM openjdk:21

WORKDIR /kafka-demo

COPY target/OUTPUT.jar /kafka-demo/kafka-demo.jar

EXPOSE 8090

#ENV JAVA_OPTS="spring.profiles.active=local"
ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /kafka-demo/kafka-demo.jar"]