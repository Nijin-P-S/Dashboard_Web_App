# Dashboard Web App
## Table of Contents
### Overview
### Features
### Technologies Used
### Prerequisites
### Getting Started
### Application Structure
### Deployment
### Contributing
### License

## Overview
Welcome to the Dashboard Web App project! This web application is built using Java, Spring Boot, and various other technologies. It provides a user-friendly dashboard interface for managing customer information. Whether you want to register, log in, create, delete, or update customer records, this app has got you covered.

The project was created as part of a learning journey through different stages of the software development lifecycle. Feel free to fork this repository and make your own version or contribute to its development.

## Features
User Registration and Authentication
Create, Read, Update, and Delete (CRUD) Customer Records
Secure API Endpoints with Spring Security
Containerized Deployment with Docker
Continuous Integration and Continuous Deployment (CI/CD) with GitHub Actions
Database Storage using PostgreSQL

## Technologies Used
Java
Spring Boot
Spring Security
Lombok
Maven
PostgreSQL
Docker
GitHub Actions (CI/CD)

## Prerequisites
Before you begin, ensure you have met the following requirements:

Java Development Kit (JDK)
Maven
Docker
PostgreSQL Database
Getting Started

To get started with this project, follow these steps:

-> Clone the repository:

git clone https://github.com/iamnijin/dashboard-web-app.git

-> Change to the project directory:

cd dashboard-web-app

-> Build the application using Maven:

mvn clean install

-> Run the Docker container:

docker run -d -p 8080:8080 iamnijin/dashboard:3.0

Access the dashboard web app at http://localhost:8080 in your web browser.


Contributing
We welcome contributions from the community. If you'd like to contribute to this project, please follow these guidelines:

Fork the project.
Create a new branch.
Make your changes.
Test your changes.
Create a pull request.
We'll review your contribution and merge it if it adds value to the project.

License
This project is licensed under the MIT License - see the LICENSE file for details.
