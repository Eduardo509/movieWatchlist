package com.eduardo.movie_watchlist_api.repository;

import com.eduardo.movie_watchlist_api.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitle(String Title);
}
