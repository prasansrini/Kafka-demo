#!/usr/bin/env bash

if [ "$1" == "" ]
    then
    echo "Usage: ./build_run.sh <DOCKER_HUB_PASSWORD>"
    exit
fi

mvn clean install || exit
docker build -t kafka-docker-demo . || exit

docker tag kafka-docker-demo prasansrini29/kafka-docker-demo || exit
docker login -u prasansrini29 -p $1 || exit
docker push prasansrini29/kafka-docker-demo || exit

# Enable this if Docker compose is not used.
#docker run --name kafka-docker-demo -p 8090:8090 kafka-docker-demo || exit