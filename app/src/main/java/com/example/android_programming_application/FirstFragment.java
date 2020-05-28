package com.example.android_programming_application;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FirstFragment extends Fragment {
    private RecyclerView recyclerView;
    private int page = 1;
    private MovieAdaptor movieAdaptor;
    private CatagoryEnum categorySelected;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        Bundle arguments = getArguments();
        if(arguments != null) {
            Log.d("Click", "Arguments was not null ");
            categorySelected = (CatagoryEnum) arguments.get("category");
        } else {
            categorySelected = CatagoryEnum.POPULAR;
        }
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        initializeSpinner(view);

        setMovieList();

        return view;
    }

    private void setMovieList() {
        MovieRepository.getInstance().getMovies(page, categorySelected, new OnGetMoviesCallback() {
            @Override
            public void onSuccess(int page, List<Movie> movies) {
                movieAdaptor = new MovieAdaptor(movies, categorySelected);
                recyclerView.setAdapter(movieAdaptor);
            }

            @Override
            public void onError() {

            }
        });
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initializeSpinner(View view) {
        final Spinner spinner = view.findViewById(R.id.spinner_fragment_1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch (spinner.getSelectedItem().toString()) {
                    case "Release Date":
                        categorySelected = CatagoryEnum.UPCOMING;
                        break;
                    case "Rating":
                        categorySelected = CatagoryEnum.TOP_RATED;
                        break;
                    default:
                        categorySelected = CatagoryEnum.POPULAR;
                        break;
                }
                setMovieList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }
        });
    }


}
