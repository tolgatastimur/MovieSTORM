package com.example.kuka.moviestorm.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kuka.moviestorm.R;
import com.example.kuka.moviestorm.activity.activity.MainActivity;
import com.example.kuka.moviestorm.activity.fragment.MovieDetailFragment;
import com.example.kuka.moviestorm.activity.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder > {
    private ArrayList<Movie> movies;
    public MoviesAdapter( ArrayList<Movie> movies) {
        this.movies =  movies;
    }

    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_movie_image,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(MoviesAdapter.ViewHolder holder, int position) {

        final Movie article = movies.get(position);

        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/" + article.posterPath)
                .resize(350,480)
                .into(holder.n_image);

        holder.n_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.activity.setNewFragment(MovieDetailFragment.newInstance(article), R.id.mainFrame, "MovieDetailFragment", true, true, false, false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

       // @BindView(R.id.movieTitle)
        //TextView movieTitle;
        public ImageView n_image;

        public ViewHolder(View itemView) {
            super(itemView);

            n_image     = itemView.findViewById(R.id.moviePoster);
            ButterKnife.bind(this,itemView);

        }
    }
}