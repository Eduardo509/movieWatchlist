package com.eduardo.movie_watchlist_api.service;

import com.eduardo.movie_watchlist_api.client.OmdbClient;
import com.eduardo.movie_watchlist_api.dto.OmdbMovieResponse;
import com.eduardo.movie_watchlist_api.model.Movie;
import com.eduardo.movie_watchlist_api.model.MovieStatus;
import com.eduardo.movie_watchlist_api.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final OmdbClient omdbClient;

    public MovieService(MovieRepository movieRepository, OmdbClient omdbClient){
        this.movieRepository = movieRepository;
        this.omdbClient = omdbClient;
    }

    public OmdbMovieResponse getExternalMovieByTitle(String title){
        return omdbClient.getMovieByTitle(title);
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public Movie saveMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public void deleteMovieById(Long id){
        movieRepository.deleteById(id);
    }

    public Movie getMovieById(Long id){
        return movieRepository.getReferenceById(id);
    }

    public Movie getMovieByTitle(String title){
        Optional<Movie> movie = movieRepository.findByTitle(title);
        return movie.orElse(null);
    }

    public Movie updateMovieById(Long id, Movie newmovie){
        Movie existingMovie = getMovieById(id);

        if (existingMovie != null){
            existingMovie.setGenre(newmovie.getGenre());
            existingMovie.setNotes(newmovie.getNotes());
            existingMovie.setRating(newmovie.getRating());
            existingMovie.setTitle(newmovie.getTitle());
            existingMovie.setStatus(newmovie.getStatus());

            return movieRepository.save(existingMovie);
        }
        return null;
    }

    public Movie saveExternalMovieByTitle(String title){
        OmdbMovieResponse externalMovie = omdbClient.getMovieByTitle(title);

        if (externalMovie != null){
            Movie movie = new Movie();
            movie.setTitle(externalMovie.getTitle());
            movie.setGenre(externalMovie.getGenre());
            movie.setNotes(externalMovie.getPlot());

            String ratingStr = String.valueOf(externalMovie.getImdbRating());
            if (ratingStr != null && !ratingStr.equals("N/A")) {
                movie.setRating(Double.parseDouble(ratingStr));
            }

            movie.setStatus(MovieStatus.TO_WATCH);

            return movieRepository.save(movie);
        }return null;

    }


}
