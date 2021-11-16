# CLIENT SPRING BOOT API CHALLENGE
## _Crud api rest client maintainer_

This back end application is a challenge about the creation of a crud api rest in Java.

#### try the application with the following link:

[client-api-challenge.herokuapp.com](https://client-api-challenge.herokuapp.com/)
_(the loading time until the page loads may take long if the application has not been deployed recently)_

This application implements spring-boot-starter-security authentication, which means that we need to authenticate as user,
In this heroku instance we can access with the following credentials
- User: usumaki
- Password: spiral

## Features

- Swagger-ui interface
- Api rest Crud
- Code with unit testing
- Docker Containerization
- Cloud deployment with Heroku server
- Authentication system

## Tech:
- Java 8
- Maven 3.8.3
- Spring Boot v2.5.6
- Swagger2 3.0.0
- Jackson 2.13.0
- Junit 4.13.2
- Docker 20.10
- Heroku 7.59.1

## Installation


Clone the source code
```sh
git clone https://github.com/Sevalas/client-api-challenge.git
cd client-api-challenge/springApp
```
### To execute the application from spring app directly:
Resolve/install maven dependencies and run spring-boot start command
```sh
mvn install or mvn package
mvn spring-boot:run
```
### To execute the application from Docker image:
With Docker installed in the system, build the image with Dockerfile instructions and then run it
```sh
docker build -t spring-app/client-api-challenge .
docker run -p 8080:8080 spring-app/client-api-challenge
```


## Usage

The deployment default host is http://localhost:8080/.
While the application is running, we can use five different endpoints:
- [GET]{{host}}/get-list-of-all-clients
- [GET]{{host}}/get-client-by-email/{{email}}
- [GET]{{host}}/get-client-by-id/{{id}}
- [POST]{{host}}/create-client
- [PUT]{{host}}/update-client-by-email/{{email}}
- [PUT]{{host}}/update-client-by-id/{{id}}
- [DELETE]{{host}}/delete-client-by-email/{{email}}
- [DELETE]{{host}}/delete-client-by-id/{{id}}

✨**With Swagger we can use the documentation-ui through this url**✨
[{{host}}/swagger-ui.html](http://localhost:8080/swagger-ui.html)

The Interface of a Client in this application is:
- String id
- String email
- String names
- String lastNames
- String type
- String phone
- String country

Example of json body request to create or update a Client:
```sh
{
  "email": "Jhon@test.com",
  "names": "Jhon",
  "lastNames": "Test",
  "type": "Costumer",
  "phone": "+66-6666666",
  "country": "Bangladesh"
}
```

## Thanks for use this application, All feedback is highly appreciated

Sebastian Valencia Lasprilla, Application developer