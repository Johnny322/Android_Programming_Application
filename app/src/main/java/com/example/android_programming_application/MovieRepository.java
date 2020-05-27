package com.example.android_programming_application;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {

    public static String BASE_URL =  "https://api.themoviedb.org/3/";
    public static String LANGUAGE = "en-US";
    private static String API_KEY = "c68223005f95b693874f303a4e877274";

    private static MovieRepository repository;

    private MovieDatabaseAPI api;

    private MovieRepository(MovieDatabaseAPI api) {
        this.api = api;
    }

    /*
    Standard singleton to grant other classes access to this classes functionality
     */
    public static MovieRepository getInstance() {
        if (repository == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            repository = new MovieRepository(retrofit.create(MovieDatabaseAPI.class));
        }

        return repository;
    }

    /*
    Method which first creates a call which will be queued to movie/{CatagoryEnum].
    Takes a callback as a parameter, which will can be used to define what to do with the page
    number and the list of movies gotten
     */

    public void getMovies(int page, CatagoryEnum sortBy, final OnGetMoviesCallback callback) {
        Callback<MovieResults> call = new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                if (response.isSuccessful()) {
                    MovieResults moviesResults = response.body();
                    if (moviesResults  != null && moviesResults .getMovies() != null) {
                        callback.onSuccess(moviesResults .getPage(), moviesResults.getMovies());
                    } else {
                        callback.onError();
                    }
                } else {
                    callback.onError();
                }
            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                callback.onError();
            }
        };

        switch (sortBy) {
            case TOP_RATED:
                api.getMovies("top_rated",API_KEY , LANGUAGE, page)
                        .enqueue(call);
                break;
            case UPCOMING:
                api.getMovies("upcoming",  API_KEY, LANGUAGE, page)
                        .enqueue(call);
                break;
            case POPULAR:
            default:
                api.getMovies("popular" ,API_KEY, LANGUAGE, page)
                        .enqueue(call);
                break;
        }
    }

    /*
    Method which queues to genre/movie/list api to get the list of genres.
    Has a callback method which cam be used to define what to do with the list of genres when invoked
     */
    public void getGenres(final OnGetGenresCallback callback) {
        api.getGenres(API_KEY, LANGUAGE)
                .enqueue(new Callback<GenreResults>() {
                    @Override
                    public void onResponse(Call<GenreResults> call, Response<GenreResults> response) {
                        if (response.isSuccessful()) {
                            GenreResults genreResults = response.body();
                            if (genreResults != null && genreResults.getGenres() != null) {
                                callback.onSuccess(genreResults.getGenres());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<GenreResults> call, Throwable t) {
                        callback.onError();
                    }
                });
    }

    /*
    Method which queues to movie/{movieId] api to get information about the specific movie id.
    Has a callback method which cam be used to define what to do with the movie information when invoked
     */
    public void getMovie(int movieId, final OnGetMovieCallback callback) {
        api.getMovie(movieId, API_KEY, LANGUAGE)
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        if (response.isSuccessful()) {
                            Movie movie = response.body();
                            if (movie != null) {
                                callback.onSuccess(movie);
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        callback.onError();
                    }
                });
    }
}
