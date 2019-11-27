# GameTracker-Server
[![Build Status](https://travis-ci.com/swagslash/GameTracker-Server.svg?branch=master)](https://travis-ci.com/swagslash/GameTracker-Server) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=swagslash_GameTracker-Server&metric=alert_status)](https://sonarcloud.io/dashboard?id=swagslash_GameTracker-Server)

GameTracker is an application where users can import games and then find out which games they have in common.

## Setup
The backend is configured to either use a local Postgres DB or use a Docker container powered Postgres DB

> The application needs an application.yaml to run properly. A dummy file can be found in ```src/main/resources/ application.yaml.dummy```.

### Local Database
If you are using a local database just start your DB and adapt your connection in the application.yaml file
```
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/<<DatabaseName>>
    username: <<DatabaseUser>>
    password: <<DatabasePassword>>
```
### Docker Database
The application.yaml dummy file is preconfigured for use with docker 
Execute the following command and you are good to go
```
docker run --name postgres-db -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 -d postgres
```
If wanted the password can be changed, just make sure it matches the password inside ```applicaiton.yaml```
