package com.ervin.dicoding_movie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

/**
 * Created by ervin on 12/2/2017.
 */

public class MovieListAdapter extends ArrayAdapter<Movie> {
    private ArrayList<Movie> dataMovie;
    Context mContext;
    TextView tvTitle;
    TextView tvSynopsis;
    TextView tvRelease;
    ImageView ivPoster;


    public MovieListAdapter(ArrayList<Movie> data, Context context) {
        super(context, R.layout.movie_item, data);
        dataMovie=data;
        mContext = context;
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        Movie movie = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.movie_item, parent, false);
        tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
        tvSynopsis = (TextView) convertView.findViewById(R.id.tv_synopsis);
        tvRelease = (TextView) convertView.findViewById(R.id.tv_release);
        ivPoster = (ImageView) convertView.findViewById(R.id.iv_poster);
        tvTitle.setText(movie.getTitle());
        tvSynopsis.setText(movie.getSynopsis());
        tvRelease.setText(movie.getRelease());
        String url = "http://image.tmdb.org/t/p/w185"+movie.getPath_image();
        Glide.with(getContext()).load(url).apply(new RequestOptions().placeholder(R.mipmap.ic_launcher)).into(ivPoster);
        return convertView;
    }
}
