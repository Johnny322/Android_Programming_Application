package com.example.android_programming_application;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdaptor extends RecyclerView.Adapter<MovieAdaptor.MovieViewHolder> {
    private int amount;
    private List<Movie> movies;
    private String URL = "https://image.tmdb.org/t/p/original";
    private CatagoryEnum adapterCategory;
    //private final View.OnClickListener mOnClickListener = new MyOnClickListener();

    public MovieAdaptor(List<Movie> movies, CatagoryEnum catagoryEnum) {
        this.movies = movies;
        amount = movies.size();
        adapterCategory = catagoryEnum;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.moviescroller, parent, false);
        return new MovieViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.movie = movies.get(position);
        Picasso.get().load(URL + movies.get(position).getBackdrop()).resize(100, 100).into(holder.imageView);
        holder.titleView.setText(movies.get(position).getTitle());
        holder.infoView.setText("Release date: " + movies.get(position).getReleaseDate());
        holder.category = adapterCategory;
    }

    @Override
    public int getItemCount() {
        return amount;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView titleView;
        TextView infoView;
        Movie movie;
        CatagoryEnum category;

        public MovieViewHolder(@NonNull View itemView, final Context context) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleView = itemView.findViewById(R.id.textViewTitle);
            infoView = itemView.findViewById(R.id.textViewInfo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SecondFragment fragment = new SecondFragment();

                    Bundle movieArgument = new Bundle();
                    movieArgument.putInt("secondFragment" , movie.getId());
                    movieArgument.putSerializable("category", category);
                    fragment.setArguments(movieArgument);

                    FragmentTransaction ft = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_container, fragment);
                    ft.commit();
                }
            });
        }


    }

    /*@Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.moviescroller, null);
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView titleView = view.findViewById(R.id.textViewTitle);
        TextView infoView = view.findViewById(R.id.textViewInfo);
        Picasso.get().load(URL+movies.get(position).getBackdrop()).resize(100, 100).into(imageView);

        titleView.setText(movies.get(position).getTitle());
        infoView.setText("Release date: " + movies.get(position).getReleaseDate());

        return view;
    }*/


}
