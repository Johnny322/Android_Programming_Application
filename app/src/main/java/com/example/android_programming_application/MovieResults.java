package com.example.android_programming_application;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResults {

    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("total_results")
    @Expose
    private int totalResults;

    @SerializedName("results")
    @Expose
    private List<Movie> movies;

    @SerializedName("total_pages")
    @Expose
    private int totalPages;

    public int getPage() {
        return page;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
