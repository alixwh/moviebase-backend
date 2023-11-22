## Moviebase
Team members: Brigite Kerge, Häli Hinno  
### Project description
Movie recommendation site. The visitor can get suggestions based on their answers to some questions. They can also filter and sort movies based on categories, release dates etc. and find information about the movies. It's possible to create a user and add movies to watchlists. 
### Technologies used
Gradle  
Java 17   
Docker Desktop

### Config properties
```
server.port=8080  # server HTTP port  
spring.datasource.url=jdbc:postgresql://localhost/postgres  # JDBC url of the database   
spring.datasource.username=${username}  # login username of the database  
spring.datasource.password=${password}  #  password for authentication with Elasticsearch  
spring.datasource.driver-class-name=org.postgresql.Driver  #  name of the JDBC driver  
spring.liquibase.change-log=classpath:db/changelog/changelog-master.xml  #  liquibase
```
This sets the local database as datasource.
### Local development
Clone backend repository:  
```
git clone https://gitlab.cs.ttu.ee/hahinn/iti0302-2022-webproject-backend.git
```
In docker-compose.yml and src\main\resources\application.properties replace ${username) and ${password}.  

Install Docker Desktop from https://www.docker.com/products/docker-desktop/ and open it.

Open terminal and run command:
```
docker-compose up -d
```

In the Database tool window (View | Tool Windows | Database), click the plus icon.

Select PostgreSQL as the datasource.

At the bottom of the data source settings area, click the Download missing driver files link.

Login using the credentials you entered before.

To ensure that the connection to the data source is successful, click the Test Connection link.
Press OK button.  

Run WebprojectApplication in src/main/java/ee/taltech/iti0302/webproject.  

Open http://localhost:8080/
### How to build a jar for deployment

```
./gradlew build
```
### How to build a docker container for deployment
```
./gradlew build  
docker build -t ${tag_name} .
```

### Server
In the directory where the docker-compose.yml is run the command
```
docker-compose up
```

### Adding movies to the database
If you want to add movies to the database, then open Postman  
and do a post request to http://localhost:8080/api/save/genres        -to first add all the genres  
and then do a post request to http://localhost:8080/api/save/movies/1          - the last number being the number of pages of movies you'd like to be added to the database  

If you type in a movie in the searchbar that does not yet exist in the database, then it is automatically added to the database.
