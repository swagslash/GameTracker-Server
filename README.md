# GameTracker-Server
Backend of the GameTracker Application

## Setup
The backend is configured to either use a local Postgres DB or use a Docker container powered Postgres DB

> The application needs an application.yaml which can be found on your GoggleDrive. Copy and replace your local application.yaml.dummy with it.

### Local DB
In case of local DB just start your DB and adapt your connection in the application.yaml file
```
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/<<DatabaseName>>
    username: <<DatabaseUser>>
    password: <<DatabasePassword>>
```
### Docker DB
The application.yaml file is preconfigured for use with docker 
Execute the following command and you are good to go
```
docker run --name postgres-db -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 -d postgres
```
if wanted the password can be changed it only has to match the password inside applicaiton.yaml