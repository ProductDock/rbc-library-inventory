#!/bin/bash
sudo usermod -a -G docker ${USER}
docker-credential-gcr configure-docker

docker stop rbc-library-inventory
docker rm rbc-library-inventory
docker rmi $(docker images | grep "rbc-library-inventory")

docker run -dp 8084:8080 -v $INVENTORY_JWT_PUBLIC_KEY:/app/app.pub:ro -e KAFKA_SERVER_URL=$KAFKA_SERVER_URL --env-file /home/pd-library/.inventory-service_env --name=rbc-library-inventory gcr.io/prod-pd-library/rbc-library-inventory:$1
docker container ls -a
