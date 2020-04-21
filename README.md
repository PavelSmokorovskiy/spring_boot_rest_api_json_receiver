# spring_boot_rest_api_json_receiver
Spring Boot based service with one rest endpoint that receives some json and stores it into a DB, with unit tests

This application service receives any complexity JSON and Save it to the Database as a flat JSON.

# Web UI Swagger
* http://localhost:8080/
* http://localhost:8080/swagger-ui.html

# Endpoints: 
* http://localhost:8080/json - GET
* http://localhost:8080/json/{id} - GET
* http://localhost:8080/json - POST
* http://localhost:8080/json - DELETE

# MySql
Bash command from the root directory:
* docker-compose up -d
