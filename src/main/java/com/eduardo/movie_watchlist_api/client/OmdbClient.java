package com.eduardo.movie_watchlist_api.client;

import com.eduardo.movie_watchlist_api.dto.OmdbMovieResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class OmdbClient {

    private final RestClient restClient;

    @Value("${omdb.api.url}")
    private String apiUrl;

    @Value("${omdb.api.key}")
    private String apiKey;

    public OmdbClient() {
        this.restClient = RestClient.create();
    }

    public OmdbMovieResponse getMovieByTitle(String title) {
        return restClient.get()
                .uri(apiUrl + "?t=" + title + "&apikey=" + apiKey)
                .retrieve()
                .body(OmdbMovieResponse.class);
    }
}
