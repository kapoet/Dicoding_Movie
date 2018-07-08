package com.ervin.dicoding_movie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class DetailMovieActivity extends AppCompatActivity {
    public static final String DATA_MOVE = "data";
    ImageView ivDetailImage;
    TextView tvDetailTitle,tvDetailsynopsis,tvDetailrelease;
    Button btnKembali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ivDetailImage = (ImageView) findViewById(R.id.iv_detail_poster);
        tvDetailTitle = (TextView) findViewById(R.id.tv_detail_title);
        tvDetailrelease = (TextView) findViewById(R.id.tv_detail_release);
        tvDetailsynopsis = (TextView) findViewById(R.id.tv_detail_synopsis);
        btnKembali = (Button) findViewById(R.id.btn_kembali);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Movie movie = getIntent().getParcelableExtra(DATA_MOVE);
        tvDetailTitle.setText(movie.getTitle());
        tvDetailsynopsis.setText(movie.getSynopsis());
        tvDetailrelease.setText(movie.getRelease());
        String url = "http://image.tmdb.org/t/p/w500"+movie.getPath_image();
        Glide.with(this).load(url).apply(new RequestOptions().placeholder(R.mipmap.ic_launcher)).into(ivDetailImage);
    }
}
