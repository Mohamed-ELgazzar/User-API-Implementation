# User-API-Implementation
This is a simple User Management System that provides RESTful API endpoints for user registration and retrieval. The system is built using Spring Boot, Spring Security, and MySQL.

## Table of contents
* [Description](#description)
* [Database](#database)
* [Technologies](#technologies)
* [Setup](#setup)
* [Usage](#usage)
* [API Endpoints](#api-endpoints)
* [Frontend](#frontend)
* [Postman Collection](#postman-collection)
* [Output Website](#output-website)
* [Contributing](#contributing)
* [License](#license)

## Description
The User Management System is a web application built with Spring Boot, Spring Security, and MySQL. It provides a set of RESTful API endpoints to manage user registration and retrieval. The system focuses on simplicity and security, utilizing JWT (JSON Web Token) for user authentication.

## Database
![db](https://github.com/Mohamed-ELgazzar/User-API-Implementation/assets/122599973/aabba3fb-4f92-4dbe-b9c3-5f1d43356c6c)
#### User


* **id** attribute.
* **email** attribute.
* **first_name** attribute.
* **last_name** attribute.
* **marketing_consent** attribute.


## Technologies
The project uses the following technologies:

* Spring Boot: The core framework for building the application, providing a simplified and convention-over-configuration approach.
* Spring Security: Ensures secure authentication and authorization of users. JWT is used for token-based authentication.
* MySQL Database: Stores user information, including first name, last name, email, and marketing consent.
* JWT (JSON Web Token): Used for secure authentication. Tokens are generated upon user registration and required for user retrieval.
* Maven: Manages project dependencies and builds the application.

## Setup
* To set up the project on your local machine, follow these steps:

1. Clone the repository from GitHub.
  git clone https://github.com/Mohamed-ELgazzar/User-API-Implementation.git
2. Import the project into your preferred IDE (e.g., IntelliJ, Eclipse).
3. Configure the database connection in the application.properties file with your MySQL credentials.
4. Run the Spring Boot application to start the backend server.

## Usage
* To use the employee filter criteria application, follow these steps:
1. Send a POST request to /user with user details in the request body.
    Receive a unique user ID and a JWT token in the response.
    User Retrieval:

2. Send a GET request to /user/{id} with the user's ID in the path variable.
    Include the JWT token in the Authorization header as a Bearer token.
    Receive user details in the response, with optional email omission based on marketing consent..

* The search results will be displayed in a table below the form.

## API Endpoints
* The backend API provides the following endpoint:

* User Registration:

** Endpoint: POST /user

Request Body:
{
    "firstName": "MohamedAtef",
    "lastName": "elgazzar",
    "email": "MohamedATef.elgazzar@example.com",
    "marketingConsent": false
}

Response:
{
    "id": "0e1a93c3687572c7ccd4e1bae2433998568f8474",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNb2hhbWVkQVRlZi5lbGdhenphckBleGFtcGxlLmNvbSIsImlhdCI6MTcwMDQzODAwNSwiZXhwIjoxNzAwNDc0MDA1fQ.TovUzwtIO2CkPmPsO2skHK7g-nzgV4y9GzLGL4pWpp4"
}.

** User Retrieval:

Endpoint: GET /user/{id}

Request Header:
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNb2hhbWVkQVRlZi5lbGdhenphckBleGFtcGxlLmNvbSIsImlhdCI6MTcwMDQzODAwNSwiZXhwIjoxNzAwNDc0MDA1fQ.TovUzwtIO2CkPmPsO2skHK7g-nzgV4y9GzLGL4pWpp4

Response:
{
    "id": "0e1a93c3687572c7ccd4e1bae2433998568f8474",
    "firstName": "MohamedAtef",
    "lastName": "elgazzar",
    "email": null,
    "marketingConsent": false
}



## Postman Collection
You can test the API endpoints using Postman. Import the following Postman collection to access the API endpoints:

* Run the application using Maven:
     mvn spring-boot:run
* Post User: registeration
![postUser](https://github.com/Mohamed-ELgazzar/User-API-Implementation/assets/122599973/695f22ad-20ae-4c6f-b67c-2b0acdf751bb)

*Get User: with valid id and valid Token, but email is appear "marketingConsent": true
![image_2023-11-20_031314609](https://github.com/Mohamed-ELgazzar/User-API-Implementation/assets/122599973/89b02e61-9391-452a-9e94-fb73b23806cd)

![GetUserAuthTokenMarketTrue](https://github.com/Mohamed-ELgazzar/User-API-Implementation/assets/122599973/20af7800-8d9d-4a2f-b735-1f91cce56029)

*Get User: with valid id and valid Token, but email is null because "marketingConsent": false
![image](https://github.com/Mohamed-ELgazzar/User-API-Implementation/assets/122599973/647b5962-cb7e-4821-84b5-7322660cac82)

![GetUserAuthTokenMarketFalse](https://github.com/Mohamed-ELgazzar/User-API-Implementation/assets/122599973/91f99693-981a-45a1-a7f7-aecb231a1467)

* Error when get User beacause no authorization and token applied.
![GetUserNoAuth](https://github.com/Mohamed-ELgazzar/User-API-Implementation/assets/122599973/a49c07dd-bc8a-42f5-8a0e-79f72ce3f71b)

*Error when get User beacause non valid Token.
![GetUserAuthTokenNoValid](https://github.com/Mohamed-ELgazzar/User-API-Implementation/assets/122599973/bcba528c-dee3-411c-a3b7-b44e0dfb42a6)


## Contributing
Contributions to this project are welcome. If you find any issues or have suggestions for improvements, feel free to open a GitHub issue or submit a pull request.

## License
This project is licensed under the MIT License. You are free to modify and distribute the code as long as you include the original license in your distribution.
Java Spring Boot (Backend API)
MySQL (Database)
