package com.example.android_programming_application;

import android.app.Activity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MovieAdaptor extends BaseAdapter {
    private int amount;
    private List<String> titles;
    private List<String> information;

    public MovieAdaptor(int amount, List<String> titles, List<String> information) {
        this.amount = amount;
        this.titles = titles;
        this.information = information;
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

        titleView.setText(titles.get(position));
        infoView.setText(information.get(position));

        return view;
    }
}
