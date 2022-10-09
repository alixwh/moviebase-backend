## Moviebase
Team members: Brigite Kerge, Häli Hinno  
### Project description
Movie recommendation site. The visitor can get suggestions based on their answers to some questions. They can also sort movies based on categories etc and find information about the movies. It's possible to create a user where they can add ratings to movies and then get recommendations based on this data. Additionally users can create a watchlist. 
### Technologies used
Gradle  
Java 17   
Docker Desktop

For frontend:  
NPM (npm install -g @vue/cli)
### Config properties
Make the application-local.properties file at src\main\resources\application-local.properties and then add:  
>server.port=8080
spring.datasource.url=jdbc:postgresql://localhost/postgres
spring.datasource.username=postgres
spring.datasource.password=docker
spring.datasource.driver-class-name=org.postgresql.Driver

This sets the local database as datasource.
### Local development
Clone backend repository:  
`git clone https://gitlab.cs.ttu.ee/hahinn/iti0302-2022-webproject-backend.git`  
Clone frontend repository:  
`git clone https://gitlab.cs.ttu.ee/hahinn/iti0302-2022-webproject-frontend.git`  
In terminal:  
`npm install`  
`npm run serve`
### How to build a jar for deployment
File -> Project Structure -> Project Settings -> Artifacts -> Click green plus sign -> Jar -> From modules with dependencies...

Select ee.taltech.iti0302.webproject.WebprojectApplication.

To actually build and save it do the following:

Extract to the target Jar

Build | Build Artifact | Build

Extract the .jar file from:

ProjectName | out | artifacts | ProjectName_jar | ProjectName.jar
### How to build a docker container for deployment
Open Docker Desktop.  
In backend project terminal:  
`docker-compose up`
### Website
Visit our website:  
http://moviebase.tk