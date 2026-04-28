package com.eduardo.movie_watchlist_api.controller;

import com.eduardo.movie_watchlist_api.dto.OmdbMovieResponse;
import com.eduardo.movie_watchlist_api.model.Movie;
import com.eduardo.movie_watchlist_api.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    private final MovieService movieService;

    @PostMapping ("/movies")
    public Movie addMovie(@RequestBody Movie movie){
        return movieService.saveMovie(movie);
    }

    @GetMapping ("/movies")
    public List<Movie> showAllMovies (){
        return movieService.getAllMovies();
    }

    @GetMapping ("/movies/{id}")
    public Movie getMovieById (@PathVariable Long id){
        return movieService.getMovieById(id);
    }

    @GetMapping("/movies/title/{title}")
    public Movie getMovieByTitle(@PathVariable String title){
        return movieService.getMovieByTitle(title);
    }

    @DeleteMapping ("/movies/{id}")
    public void deleteById(@PathVariable Long id){
        movieService.deleteMovieById(id);
    }

    @PutMapping("/movies/{id}")
    public Movie updateMovie(@PathVariable Long id, @RequestBody Movie movie){
        return movieService.updateMovieById(id, movie);
    }

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies/search-external")
    public OmdbMovieResponse getExternalMovieByTitle(@RequestParam String title){
        return movieService.getExternalMovieByTitle(title);
    }

    @PostMapping("/movies/import")
    public Movie saveExternalMovie(@RequestParam String title) {
        return movieService.saveExternalMovieByTitle(title);
    }

}
