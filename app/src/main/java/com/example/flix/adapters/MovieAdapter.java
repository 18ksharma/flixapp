package com.example.flix.adapters;

import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flix.R;
import com.example.flix.models.Movie;

import java.util.List;
import android.content.Context;
import com.bumptech.glide.request.target.Target;
import android.content.Intent;
import android.widget.Toast;

import org.parceler.Parcels;
import com.example.flix.MovieDetailsActivity;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    //Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    //Involves populating data into the item through the holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Movie movie = movies.get(position);
        //Bind the movie data into the VH
        holder.bind(movie);
    }

    //Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            itemView.setOnClickListener(this);
        }

        public void bind(Movie movie) {
            int radius = 30, margin = 10;
            //Loads placeholder images

            //Checks if landscape
            if ( context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                // then image URL = backdrop image
                Glide.with(context).load("https://courses.codepath.org/course_files/android_university_fast_track/assets/flicks_backdrop_placeholder.gif").override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).into(ivPoster);
            }
            else{
                Glide.with(context).load("https://courses.codepath.org/course_files/android_university_fast_track/assets/flicks_movie_placeholder.gif").override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).into(ivPoster);
            }

            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageUrl;
            //If the phone is in landscape
            if ( context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                // then image URL = backdrop image
                imageUrl = movie.getBackdropPath();
            }

            //else image URL = poster image
            else{
                imageUrl = movie.getPosterPath();
            }
            Glide.with(context).load(imageUrl).transform(new RoundedCornersTransformation(radius, margin)).into(ivPoster);
        }


        @Override
        public void onClick(View v) {
            //gets position
            int position = getAdapterPosition();

            //checks if position is valid
            if (position != RecyclerView.NO_POSITION){
                // gets movie at position
                Movie movie = movies.get(position);

                Toast.makeText(context, "Movie selected", Toast.LENGTH_SHORT).show();
                // creates intent for  new activity
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                // uses parceler
                intent.putExtra(Movie.class.getSimpleName(), Parcels.wrap(movie));
                // shows activity
                context.startActivity(intent);
            }
        }
    }
}
