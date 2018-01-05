# Distributed Systems - server (example app)

This is a demo web application, which uses Spring Integration for the implementation of one of its endpoints.
For a Spring Integration-based console client for this server, see [Distributed Systems - console client] (https://github.com/bresalio/distributed-systems-console-client).

## Built With

* [Eclipse Kepler](https://www.eclipse.org/) - the IDE used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring Integration](https://projects.spring.io/spring-integration/)

## Deployment on Tomcat

To deploy the app, the first step is to clone it from here (GitHub).

There are multiple ways to deploy it on a Tomcat instance then; the three most important ways are
* importing the project into an IDE (e.g. Eclipse, IntelliJ) and applying embedded deployment;
* deploying with Maven;
* deploying the .war (Web Archive) file directly.

The following steps are going to describe the third option (based on [this] (http://www.baeldung.com/tomcat-deploy-war) article).
We assume that you already have a deployable .war file, and an installed Tomcat instance.
(For seeing one way of generating the .war from the source code, e.g. with Eclipse, follow [this] (http://www.codejava.net/ides/eclipse/eclipse-create-deployable-war-file-for-java-web-application) guide.
For help in Tomcat setup and installation, read [this] (http://www.baeldung.com/tomcat) article.)

* Find where the $CATALINA_HOME variable of your Tomcat instance points to, and open "webapps" folder in the directory.
* Drag and drop the .war archive here.
* The server will deploy the project the next time it is started. (You should restart Tomcat.)

From now on, you should be able to get access to the endpoints like this:
```
http://<host:port>/distributed-systems-server/<endpoint>
```
<host:port> describes the host and the server's configured HTTP port (e.g. "localhost:8081"; default HTTP port: 8080).
(And "distributed-systems-server" is the context path: it is in connection with the name under which the app was put into Tomcat's "webapps".)

## Endpoints

The app has 10 endpoints, the first 9 ones of which operate on locally stored dummy data (containing only 3 films).
The 10th endpoint (getOmdbFilmData) queries information from a huge open movie database, [OMDb] (http://www.omdbapi.com/).
This endpoint is implemented with Spring Integration.

All endpoints wait for GET requests, and they return data in JSON format (except for addRating returning boolean, and getAverageRatingOfFilm returning String).
The endpoints that return JSON lists, return an empty list if no data was found.
If a parameter (e.g. name or title) contains space, it should be URL-encoded ("%20").
The parameters are case-insensitive.

An example request and response (showing a film description JSON):

```
http://<host:port>/distributed-systems-server/getFilmData?title=django%20unchained

{"title":"Django Unchained",
"genre":"western",
"director":"Quentin Tarantino",
"mainActors":["Jamie Foxx","Christoph Waltz","Leonardo DiCaprio"],
"minutes":165,
"publicationYear":2012}
```

* getAllFilms

Returns the descriptions of all dummy films in a list.

* getAllFilmsOfGenre, param: genre

Returns the decriptions of all dummy films in a list, where the genre is equal to the specified param (e.g. "western", "drama").

* getAllFilmsOfDirector, param: director

Returns the decriptions of all dummy films in a list, where the director is equal to the specified param.
One film has exactly one director, but one director might have multiple films.

* getAllFilmsOfActor, param: actor

Returns the decriptions of all dummy films in a list, where an actor is equal to the specified param.
One actor might have played in multiple films, and one film might have multiple actors (although only the main characters are listed).

* getAllFilmsBetweenLength

Returns the decriptions of all dummy films in a list, that are between the specified min and max length values (inclusive, in minutes).
If the given min value is accidentally greater than the given max value, the values are reverted.

Required request headers: "min", "max" - integers

* getFilmData, param: title

Returns the decription of the given film if it exists in the dummy list. If not, the fields indicate this with null values.

* addRating/{title}

Adds a rating to the film (the ratings are stored locally in memory).
Returns a boolean value indicating whether the operation was successful; it's false if the film is not found.

Required request header: "opinion" - its value must be one of the following options:
"VERY_BAD", "RATHER_BAD", "NEUTRAL", "RATHER_GOOD", "VERY_GOOD"

* getRatingsOfFilm, param: title

Returns the list of the ratings added to the film so far. (Each rating is one of the options described above.)

* getAverageRatingOfFilm, param: title

Calculates and returns the average rating of a film (which can be one of the above described options).
If no ratings belong to the film, the response is "NOT_FOUND".

* getOmdbFilmData, param: title

This endpoint returns film data in the same format as getFilmData, but queries the information from OMDb.

### Endpoint dependency

Endpoint "getOmdbFilmData" depends on a third party, [OMDb] (http://www.omdbapi.com/) (the Open Movie Database API).
Before using this endpoint, please check whether the following GET request is served with HTTP 200 response code ("OK").

```
http://www.omdbapi.com/?apikey=ef99003c&t=Titanic
```

Among others fields, the response body (JSON) should contain the following ones: "Title", "Genre", "Director", "Actors", "Runtime", "Year".