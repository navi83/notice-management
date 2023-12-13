# notice-management
A notice management application based on Spring Boot

## Table of contents
- [Features](#features)
- [Technologies](#technoloies)
- [Getting Started](#getting-started)

<a id="features"></a>
## Features
#### Authentication
- Register new user with difference roles
- Login with username/password
#### Notice
- Get all notices
- Get a notice by noticeId
- Create/Update/Delete a notice
##### Note: must be logged in with role admin to be able to Create/Update/Delete notices
#### Attachment
- Get attachment by noticeId
- Create/Delete attachments
##### Note: must be logged in with role admin to be able to Create/Delete attachment
#### Notice View
##### Prerequisite: must be logged in and have access token ready in Authorization header
<a id="technoloies"></a>
## Technologies
- Spring Boot 3 
- Spring Security
- JSON Web Tokens (JWT)
- Maven 3
- MS SQL Server
- Flyway
- Junit

<a id="getting-started"></a>
## Getting Started
To get started with this project, you will need to have the following installed on your local machine:

- JDK 17+
- Maven 3+ 
- Add database "notice-management" to MS SQL Server

After successfully run the application, the Swagger UI is available at http://localhost:8080/swagger-ui/index.html

