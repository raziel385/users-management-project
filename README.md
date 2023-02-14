# Getting Started

Users management demo

# users management spring boot 
### docker build:
    docker build --tag=users-management:latest 
### docker run:
    docker run -p8084:8084 users-management:latest

# users managment ui
### docker build:
    docker build --tag users-managment-ui .
### clean build clean cache:
    docker build --no-cache -t users-managment-ui .
### docker run:
    docker run -d -p 1200:80 users-managment-ui

# docker compose
### docker-compose build:
    docker-compose up --build
### docker-compose start:
    docker-compose start
### docker-compose stop:
    docker-compose stop
### docker-compose reset:
    docker-compose down

# USE
after docker compose build and start in terminal window: 
 - docker-compose up --build
 - docker-compose start
### Flutter web app:
    go to http://localhost:1200/#/ in a browser
### Consume ReST api:
    http://localhost:8084/ui/api/users/ base url in postman

