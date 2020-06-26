package com.example.flix;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flix.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import okhttp3.internal.http2.Header;


public class MovieDetailsActivity extends AppCompatActivity {
    public final static String API_BASE_URL = "https://api.themoviedb.org/3";
    public final static String API_KEY_PARAM = "api_key";
    public final static String TAG = "MovieDetailsActivity";
    AsyncHttpClient client;
    String videoId;

    //Specific Movie
    Movie movie;


    //Objects
    TextView tvTitle;
    TextView tvOverview;
    RatingBar rbVoteAverage;
    TextView tvreleaseDate;
    ImageView image_view;

    //client = new AsyncHttpClient();

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        int radius = 30, margin = 10;
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
        Glide.with(this).load(movie.getBackdropPath()).transform(new RoundedCornersTransformation(radius, margin)).override(300, 600).into(image_view);
        float voteAvg = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAvg = voteAvg > 0 ? voteAvg / 2.0f : voteAvg);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        /*client.get("https://api.themoviedb.org/3/movie/"+movie.getId()+"/videos?api_key="+"AIzaSyDicWVHZnY9XPa0ftRP1WILP1zL6xjRBNQ", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray results = response.getJSONArray("results");
                    if (results != null) {
                        videoId = results.getJSONObject(0).getString("key");
                    }
                    Log.i(TAG, String.format("Loaded trailer video"));
                } catch (JSONException e) {
                    log.e("Failed to parse trailer video", e, true);
                }
            });*/
        image_view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(MovieDetailsActivity.this, MovieTrailerActivity.class);
                startActivity(i);
            }
        });

    }
}
