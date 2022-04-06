# Garage
*Simple Parking Lot Project - Developed by Kaan Erol*





## About
This garage project is allowing you to find empty closest parking slot at garage and you can park/unpark the vehicle or status can be seen from endpoints.  
Techs used in this project ;
- Java 11
- Spring Boot
- JUnit
- Maven

## Install
After downloading project folder, run maven update. After all configurations and downloads done by maven then you can use the project however you want.

## Usage
The project has an endpoint prefix which is "/v1". This can be changed in application.properties file. In this project we have an endpoint which allows us to check empty slots and park in the garage. Can be used by "/garage/park" .This path requires a request body for post mapping. Also we have an endpoint for unparking. Can be used by "/garage/leave/{tickedId}". This path requires a path variable "ticketId". Finally we have one more endpoint for tracing status of garage. This can be used by "/garage/status". This requires nothing. For eazy usage there is a postman json file in project folder.