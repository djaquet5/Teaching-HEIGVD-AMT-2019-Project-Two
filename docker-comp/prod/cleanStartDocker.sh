#!/bin/bash
docker-compose down
docker rmi docker-comp_mysql:latest
docker rmi $(docker images -f "dangling=true" -q)

docker-compose up --build