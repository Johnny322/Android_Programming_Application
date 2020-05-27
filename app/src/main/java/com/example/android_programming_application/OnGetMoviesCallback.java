package com.example.android_programming_application;

import java.util.List;

public interface OnGetMoviesCallback {

    void onSuccess(int page , List<Movie> movies );

    void onError();
}
