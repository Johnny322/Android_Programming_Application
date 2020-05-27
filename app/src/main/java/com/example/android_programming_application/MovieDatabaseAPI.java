package com.example.android_programming_application;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDatabaseAPI {

    // https://api.themoviedb.org/3/movie/popular?api_key=c68223005f95b693874f303a4e877274&language=en-US&page=1

    @GET("movie/{category}")
    Call<MovieResults> getMovies (
        @Path("category") String category,
        @Query("api_key") String apiKey,
        @Query("language") String languagey,
        @Query("page") int page
    );

    @GET("genre/movie/list")
    Call<GenreResults> getGenres(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/{movie_id}")
    Call<Movie> getMovie(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
