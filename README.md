# Task_Rest_Spring_API

Docker is used for the DB (SQL) 
The configruation of docker can be found in docker-compose.yml
Note: spring.jpa.hibernate.ddl-auto=create
should be used only for the first run of and this line "spring.jpa.hibernate.ddl-auto=create"
should be commented in application.properties because this will create every time the app 
runs a new DB and the old records will be deleted

#Running the app 
It's a spring boot application and StockMangerApplication is the main class that should be used for running
the app
