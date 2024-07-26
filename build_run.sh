#!/usr/bin/env bash

mvn clean package || exit
docker build -t kafka-docker-demo . || exit

# Enable this if Docker compose is not used.
#docker run --name kafka-docker-demo -p 8090:8090 kafka-docker-demo || exit