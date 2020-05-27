package com.example.android_programming_application;

import java.util.List;

public interface OnGetMovieCallback {

    void onSuccess(Movie movie);

    void onError();
}
