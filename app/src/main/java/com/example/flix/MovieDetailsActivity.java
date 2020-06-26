package com.example.flix;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.flix.models.Movie;

import org.parceler.Parcels;
import org.w3c.dom.Text;


public class MovieDetailsActivity extends AppCompatActivity {
    //Specific Movie
    Movie movie;

    //Objects
    TextView tvTitle;
    TextView tvOverview;
    RatingBar rbVoteAverage;
    TextView tvreleaseDate;
    ImageView image_view;
    String url;

    @Override
    protected void onCreate( Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        //resolve objects
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvOverview = (TextView) findViewById(R.id.tvOverview);
        rbVoteAverage = (RatingBar) findViewById(R.id.rbVoteAverage);
        tvreleaseDate = (TextView) findViewById(R.id.tvreleaseDate);
        image_view = (ImageView) findViewById(R.id.imageView);
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s", movie.getTitle()));

        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        tvreleaseDate.setText(movie.getReleaseDate());
        Glide.with(this).load(movie.getPosterPath()).override(600, 1200).into(image_view);
        float voteAvg = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAvg = voteAvg > 0 ? voteAvg / 2.0f : voteAvg);
    }
}
