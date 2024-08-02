Journal Application
This project is a Journal Application built with Spring Boot that allows users to create and manage journal entries. It includes user authentication and authorization using Spring Security.

Table of Contents
Features
Technologies Used
Setup Instructions
Usage
API Endpoints
Contributing
License
Features
User Registration and Login
Create, Read, Update, and Delete (CRUD) operations for journal entries
Role-based access control
Password encoding
Technologies Used
Spring Boot
Spring Security
Spring Data JPA
MongoDB
Maven
Setup Instructions
Prerequisites
Java 11 or higher
Maven
MongoDB
Steps
Clone the repository:

bash
Copy code
git clone https://github.com/yourusername/journalApp.git
cd journalApp
Configure MongoDB:

Ensure MongoDB is running on localhost:27017 or configure the application properties if it's running on a different host/port.

Build the project:

bash
Copy code
mvn clean install
Run the application:

bash
Copy code
mvn spring-boot:run
Application Properties
Ensure your application.properties (or application.yml) is correctly set up for your MongoDB configuration:

properties
Copy code
spring.data.mongodb.uri=mongodb://localhost:27017/yourdbname
spring.security.user.name=admin
spring.security.user.password=adminpassword
Usage
Creating a User
You can create a user using the following endpoint:

URL: http://localhost:8080/public/createUser

Method: POST

Headers: Content-Type: application/json

Body:

json
Copy code
{
    "userName": "Abhi",
    "password": "password123"
}
Updating a User
URL: http://localhost:8080/user

Method: PUT

Headers:

Content-Type: application/json
Authorization: Basic Auth (Username and Password of the authenticated user)
Body:

json
Copy code
{
    "userName": "Abhi",
    "password": "newPassword123"
}
Accessing Journal Entries
URL: http://localhost:8080/journal/Abhi
Method: GET
Headers: Authorization: Basic Auth (Username and Password of the authenticated user)
API Endpoints
Method	Endpoint	Description
POST	/public/createUser	Create a new user
PUT	/user	Update the authenticated user
GET	/journal/{username}	Get journal entries for a user
POST	/journal	Create a new journal entry
PUT	/journal/{id}	Update a journal entry
DELETE	/journal/{id}	Delete a journal entry
Contributing
Contributions are welcome! Please fork this repository and submit pull requests for any features, bug fixes, or enhancements.
