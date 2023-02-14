# Getting Started

Users management demo

## users management spring boot 
docker build:
    docker build --tag=users-management:latest 
docker run:
    docker run -p8084:8084 users-management:latest

## users managment ui
docker build:
    docker build --tag users-managment-ui .
clean cache:
    docker build --no-cache -t users-managment-ui .
docker run:
    docker run -d -p 1200:80 users-managment-ui

## docker compose
docker-compose build:
    docker-compose up --build
docker-compose start:
    docker-compose start
docker-compose stop:
    docker-compose stop
docker-compose reset:
    docker-compose down

