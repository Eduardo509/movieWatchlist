# Movie Watchlist API

A REST API built with Spring Boot to manage a movie watchlist and integrate with the OMDb API.

---

## Features

* Create movies manually
* List all movies
* Get a movie by ID
* Update movie information
* Delete movies
* Fetch movie data from the OMDb API
* Import movies from OMDb into the database

---

## Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Docker
* Maven

---

## Setup

### Clone the repository

```bash
git clone https://github.com/your-username/movie-watchlist-api.git
cd movie-watchlist-api
```

### Run PostgreSQL with Docker

```bash
docker run --name movie-postgres \
-e POSTGRES_DB=movie_watchlist \
-e POSTGRES_USER=postgres \
-e POSTGRES_PASSWORD=postgres \
-p 5432:5432 \
-d postgres
```

### Configure OMDb API Key

In `application.properties`:

```properties
omdb.api.url=http://www.omdbapi.com/
omdb.api.key=YOUR_API_KEY
```

### Run the application

```bash
mvn spring-boot:run
```

The API will be available at:

```
http://localhost:8080
```

---

## API Endpoints

### Movie CRUD

| Method | Endpoint       | Description     |
| ------ | -------------- | --------------- |
| POST   | `/movies`      | Create a movie  |
| GET    | `/movies`      | Get all movies  |
| GET    | `/movies/{id}` | Get movie by ID |
| PUT    | `/movies/{id}` | Update a movie  |
| DELETE | `/movies/{id}` | Delete a movie  |

### OMDb Integration

Search a movie externally:

```
GET /movies/search-external?title=Interstellar
```

Import a movie into the database:

```
POST /movies/import?title=Interstellar
```

---

## Project Structure

```
controller   handles HTTP requests  
service      business logic  
repository   database access  
client       external API integration  
dto          data transfer objects  
model        database entities  
```

---

## Example

```
POST /movies/import?title=Interstellar
```

Response:

```json
{
  "id": 1,
  "title": "Interstellar",
  "genre": "Adventure, Drama, Sci-Fi",
  "rating": 8.6,
  "notes": "...",
  "status": "TO_WATCH"
}
```

---

## Future Improvements

* Prevent duplicate entries
* Improve error handling
* Add validation
* Add pagination
* Add authentication

---

## Author

Eduardo

