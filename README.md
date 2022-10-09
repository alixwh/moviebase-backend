## Moviebase
Team members: Brigite Kerge, Häli Hinno  
### Project description
Movie recommendation site. The visitor can get suggestions based on their answers to some questions. They can also sort movies based on categories etc and find information about the movies. It's possible to create a user where they can add ratings to movies and then get recommendations based on this data. Additionally users can create a watchlist. 
### Technologies used
Gradle  
Java 17   
Docker Desktop

### Config properties
>server.port=8080  # server HTTP port  
spring.datasource.url=jdbc:postgresql://localhost/postgres  # JDBC url of the database   
spring.datasource.username=${username}  # login username of the database  
spring.datasource.password=${password}  #  password for authentication with Elasticsearch  
spring.datasource.driver-class-name=org.postgresql.Driver  #  name of the JDBC driver  

This sets the local database as datasource.
### Local development
Clone backend repository:  
`git clone https://gitlab.cs.ttu.ee/hahinn/iti0302-2022-webproject-backend.git`  
Add lines from Config properties step to src\main\resources\application.properties and replace ${username) and ${password} with the ones of local docker.

`docker-compose up`

In the Database tool window (View | Tool Windows | Database), click the Data Source Properties icon.

On the Data Sources tab in the Data Sources and Drivers dialog, click the Add icon and select PostgreSQL.

At the bottom of the data source settings area, click the Download missing driver files link.

Login using:  
>User: postgres   
Password: docker

To ensure that the connection to the data source is successful, click the Test Connection link.
Press OK button.  

Run WebprojectApplication in src/main/java/ee/taltech/iti0302/webproject.  

open http://localhost:8080/
### How to build a jar for deployment
`./gradlew build`
### How to build a docker container for deployment
`./gradlew build`  
`docker build -t ${tag_name} .`
### Website
Visit our website:  
http://moviebase.tk
