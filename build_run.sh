#!/usr/bin/env bash

mvn clean install -f ./build/Kafka-demo-app/pom.xml || exit
docker build -t kafka-docker-demo . || exit

if [ $# -eq 0 ]
    then
        docker tag kafka-docker-demo prasansrini29/kafka-docker-demo || exit
        docker login -u prasansrini29 || exit
        docker push prasansrini29/kafka-docker-demo || exit
fi

# Enable this if Docker compose is not used.
#docker run --name kafka-docker-demo -p 8090:8090 kafka-docker-demo || exit