FROM openjdk:21-slim

WORKDIR /kafka-demo

COPY target/OUTPUT.jar /kafka-demo/kafka-demo.jar

EXPOSE 8090

ENV JAVA_OPTS="-Dspring.profiles.active=local"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /kafka-demo/kafka-demo.jar"]
