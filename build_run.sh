#!/usr/bin/env bash

if [ "$1" == "" ]
    then
    echo "Usage: './build_run.sh <DOCKER_HUB_PASSWORD>' to build and push image to Docker hub."
    echo "[OR]"
    echo "Usage: './build_run.sh --host' to run locally."
    echo "[OR]"
    echo "Usage: './build_run.sh --dockerize' to run locally as a container."
    exit
fi

mvn clean install || exit

if [ "$1" == "--dockerize" ]
    then
      docker build -t kafka-docker-demo . || exit
      docker run -p 8090:8090 kafka-docker-demo
      echo "Running Kafka-demo"
      exit
elif [ "$1" == "--host" ]
    then
      java -Dspring.profiles.active=local -jar target/OUTPUT.jar
      exit
else
  docker build -t kafka-docker-demo . || exit
fi

docker tag kafka-docker-demo prasansrini29/kafka-docker-demo || exit
docker login -u prasansrini29 -p "$1" || exit
docker push prasansrini29/kafka-docker-demo || exit

# Enable this if Docker compose is not used.
#docker run --name kafka-docker-demo -p 8090:8090 kafka-docker-demo || exit