package com.example.kuka.moviestorm.activity.adapter;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kuka.moviestorm.R;
import com.example.kuka.moviestorm.activity.activity.MainActivity;
import com.example.kuka.moviestorm.activity.fragment.MovieDetailFragment;
import com.example.kuka.moviestorm.activity.model.Movie;
import com.example.kuka.moviestorm.activity.utilities.CustomItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VizyonAdapter extends RecyclerView.Adapter<VizyonAdapter.ViewHolder> {
    private ArrayList<Movie> movies;

    public VizyonAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public VizyonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_movie_image, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VizyonAdapter.ViewHolder holder, int position) {

        final Movie article = movies.get(position);
        holder.movieTitle.setText(article.title.toUpperCase());
        holder.movieRating.setText("IMDB :" + String.valueOf(article.voteAverage) + "/10");
        holder.movieReleaseDate.setText("Yatın Tarihi: "+article.releaseDate);

        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/" + article.posterPath)
                .resize(350, 480)
                .into(holder.movieImage);

        holder.movieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.activity.setNewFragment(MovieDetailFragment.newInstance(article), R.id.contentFL, "MovieDetailFragment", true, true, false, false);
            }
        });


    }


    @Override
    public int getItemCount() {
        return movies.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.moviePoster)
        ImageView movieImage;
        @BindView(R.id.movieTitle)
        TextView movieTitle;
        @BindView(R.id.movieRating)
        TextView movieRating;
        @BindView(R.id.movieReleaseDate)
        TextView movieReleaseDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}