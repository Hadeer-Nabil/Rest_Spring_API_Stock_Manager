# Task_Rest_Spring_API

This Service is a Restful Web service using Spring Boot

# Configuration Info

Docker is used for the DB (SQL)
The configruation of docker can be found in docker-compose.yml
Note: spring.jpa.hibernate.ddl-auto=create
should be used only for the first run of and this line "spring.jpa.hibernate.ddl-auto=create"
should be commented in application.properties because this will create every time the app 
runs a new DB and the old records will be deleted

#Running the app

1)Run Docker

2) Run Spring application

StockMangerApplication
It's a spring boot application and StockMangerApplication is the main class that should be used for running
the app

3)http://localhost:8080/stocks/swagger-ui.html#

The endpoints will be listed by swagger documentation


#Technologies used

Java 1.8
Spring MVC with Spring Boot
Spring Data
Spring Security
Swagger-ui
Hibernate
Docker
Maven
