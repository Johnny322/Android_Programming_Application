package com.example.android_programming_application;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class SecondFragment extends Fragment {

    private MovieRepository movieRepository;
    private String URL = "https://image.tmdb.org/t/p/original";
    private ImageView mImageView;
    private TextView mTitleView;
    private TextView mReleaseView;
    private TextView mGenreView;
    private TextView mDescriptionView;
    private int movieId;
    private CatagoryEnum category;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        Bundle arguments = getArguments();
        movieId = arguments.getInt("secondFragment");
        category = (CatagoryEnum) arguments.get("category");

        movieRepository = MovieRepository.getInstance();
        //Log.d("Click", movieNumber + " is movieNumber");
        setMovieView(movieId);
        mImageView = view.findViewById(R.id.imageView);
        mTitleView = view.findViewById(R.id.textViewTitle);
        mReleaseView = view.findViewById(R.id.textViewYear);
        mGenreView = view.findViewById(R.id.textViewGenre);
        mDescriptionView = view.findViewById(R.id.textViewDescription);

        Button returnButton = view.findViewById(R.id.button_second);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                FirstFragment firstFragment = new FirstFragment();
                Bundle categoryArgument = new Bundle();
                categoryArgument.putSerializable("category", category);
                firstFragment.setArguments(categoryArgument);

                ft.replace(R.id.fragment_container, firstFragment);
                ft.commit();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    private void setMovieView(int movieId) {
        movieRepository.getMovie(movieId, new OnGetMovieCallback() {
            @Override
            public void onSuccess(Movie movie) {
                Picasso.get().load(URL+ movie.getBackdrop()).resize(240, 200).into(mImageView);

                mTitleView.setText(movie.getTitle());

                mReleaseView.setText(movie.getReleaseDate());

                String genres = "";
                for(Genre genre : movie.getGenres()) {
                    genres = genres + genre.getName() + ", ";
                }
                genres = genres.replaceAll(", $", "");
                mGenreView.setText(genres);

                mDescriptionView.setText(movie.getOverview());

            }

            @Override
            public void onError() {
                // getActivity().finish();
            }
        });
    }

}
