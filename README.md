# notice-management
A notice management application based on Spring Boot

## Table of contents
- [Features](#features)
- [Technologies](#technoloies)
- [Getting Started](#getting-started)
- [Key problem solving strategies](#key-problem-solving-strategies)

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

<a id="technoloies"></a>
## Technologies
- Spring Boot 3 
- Spring Security
- Spring Data JPA
- Maven 3
- MS SQL Server
- Flyway, Lombok, Junit, JSON Web Tokens (JWT)

<a id="getting-started"></a>
## Getting Started
To get started with this project, follow these steps:

- Install JDK 17+, Maven 3+ 
- Add database "notice-management" to MS SQL Server
- Update database configuration in resource/application.yml
- Build and run the application

After successfully run the application, the Swagger UI is available at http://localhost:8080/swagger-ui/index.html

<a id="key-problem-solving-strategies"></a>
## Key problem-solving strategies

- The uploaded file is store in database with BLOB type, it is recommended to save the attachment files on the separate File Server (for instance AWS S3, Cloudinary, Google Cloud Storage)
- To handle with high volume traffic, I would like to recommend using ElasticSearch to reduce DB connections. 
- To robust the API response speed, I would like to recommend implement caching mechanisms to store frequently accessed data, for instance Spring Boot Cache or Redis, and using indexes in the database.