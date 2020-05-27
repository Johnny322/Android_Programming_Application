package com.example.android_programming_application;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.List;

public class FirstFragment extends Fragment {

    private ListView listView;
    private int page = 1;

    @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle
        savedInstanceState){
            View view = inflater.inflate(R.layout.fragment_first, container, false);
            listView = view.findViewById(R.id.listView);
            MovieRepository.getInstance().getMovies(page, CatagoryEnum.POPULAR, new OnGetMoviesCallback() {
                @Override
                public void onSuccess(int page, List<Movie> movies) {
                    MovieAdaptor movieAdaptor = new MovieAdaptor(movies);
                    listView.setAdapter(movieAdaptor);
                }

                @Override
                public void onError() {

                }
            });

            return inflater.inflate(R.layout.fragment_first, container, false);
        }
        public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
        }
    }