package com.example.flix;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.flix.models.Movie;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


public class MovieDetailsActivity extends AppCompatActivity {
    //Specific Movie
    Movie movie;


    //Objects
    TextView tvTitle;
    TextView tvOverview;
    RatingBar rbVoteAverage;
    TextView tvreleaseDate;
    ImageView image_view;

    @Override
    protected void onCreate( Bundle savedInstanceState){
        int radius=30, margin = 10;
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
        Glide.with(this).load(movie.getPosterPath()).transform(new RoundedCornersTransformation(radius, margin)).override(300, 600).into(image_view);
        float voteAvg = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAvg = voteAvg > 0 ? voteAvg / 2.0f : voteAvg);
    }
}
