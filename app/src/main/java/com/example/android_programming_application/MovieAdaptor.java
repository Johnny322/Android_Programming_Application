package com.example.android_programming_application;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdaptor extends BaseAdapter {
    private int amount;
    private List<Movie> movies;
    private String imageURL = "https://image.tmdb.org/t/p/original";

    public MovieAdaptor(List<Movie> movies) {
        this.movies = movies;
        amount = movies.size();
    }

    @Override
    public int getCount() {
        return amount;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.moviescroller, null);
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView titleView = view.findViewById(R.id.textViewTitle);
        TextView infoView = view.findViewById(R.id.textViewInfo);
        Picasso.get().load(imageURL + movies.get(position).getBackdrop()).into(imageView);


        titleView.setText(movies.get(position).getTitle());
        infoView.setText("Release date : " + movies.get(position).getReleaseDate());

        return view;
    }
}
