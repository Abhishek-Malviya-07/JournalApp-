# Journal Application

This project is a Journal Application built with Spring Boot that allows users to create and manage journal entries. It includes user authentication and authorization using Spring Security.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup Instructions](#setup-instructions)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Features

- User Registration and Login
- Create, Read, Update, and Delete (CRUD) operations for journal entries
- Role-based access control
- Password encoding

## Technologies Used

- Spring Boot
- Spring Security
- Spring Data JPA
- MongoDB
- Maven

## Setup Instructions

### Prerequisites

- Java 11 or higher
- Maven
- MongoDB

### Steps

1. **Clone the repository:**

   ```bash
   git clone https://github.com/yourusername/journalApp.git
   cd journalApp
   
2. **Configure MongoDB:**

   -Ensure MongoDB is running on localhost:27017 or configure the application properties if it's running on a different host/port.
   
3. **Build the project:**
    ```bash
    mvn clean install

4.  **Run the application:**
     ```bash
     mvn spring-boot:run
      



