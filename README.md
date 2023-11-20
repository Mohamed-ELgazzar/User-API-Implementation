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

1. Access the frontend interface by opening the index.html file in a web browser.

2. Enter the search criteria in the form provided on the page.

3. Click the "View" button to trigger a search based on the entered criteria.

* The search results will be displayed in a table below the form.

## API Endpoints
* The backend API provides the following endpoint:

* User Registration:

** Endpoint: POST /user

Request Body:
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "marketingConsent": true
}
Response:
{
  "id": "553ae7da92f5505a92bbb8c9d47be76ab9f65bc2",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
}.

** User Retrieval:

Endpoint: GET /user/{id}

Request Header:
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
Response:


{
  "id": "553ae7da92f5505a92bbb8c9d47be76ab9f65bc2",
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "marketingConsent": true
}



## Postman Collection
You can test the API endpoints using Postman. Import the following Postman collection to access the API endpoints:

* Run the application using Maven:
     mvn spring-boot:run
* Post User: registeration
![postUser](https://github.com/Mohamed-ELgazzar/User-API-Implementation/assets/122599973/695f22ad-20ae-4c6f-b67c-2b0acdf751bb)

*Get User: with valid id and valid Token
![GetUser](https://github.com/Mohamed-ELgazzar/User-API-Implementation/assets/122599973/bf033c7c-0ba9-4a1b-a484-685fd2ea47ae)

* Error when get User beacause no authorization and token applied.
![GetUserNoAuth](https://github.com/Mohamed-ELgazzar/User-API-Implementation/assets/122599973/a49c07dd-bc8a-42f5-8a0e-79f72ce3f71b)

*Error when get User beacause non valid Token.
![GetUserAuthTokenNoValid](https://github.com/Mohamed-ELgazzar/User-API-Implementation/assets/122599973/bcba528c-dee3-411c-a3b7-b44e0dfb42a6)


## Contributing
Contributions to this project are welcome. If you find any issues or have suggestions for improvements, feel free to open a GitHub issue or submit a pull request.

## License
This project is licensed under the MIT License. You are free to modify and distribute the code as long as you include the original license in your distribution.
Java Spring Boot (Backend API)
HTML, CSS, JavaScript (Frontend Interface)
MySQL (Database)
