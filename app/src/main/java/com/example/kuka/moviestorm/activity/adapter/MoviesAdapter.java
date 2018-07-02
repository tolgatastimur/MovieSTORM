package com.example.kuka.moviestorm.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kuka.moviestorm.R;
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

        Movie article = movies.get(position);
      //  holder.n_overview.setText( article.overview);
       // holder.n_title.setText(article.title);
      // holder.n_posterpath.setText(article.posterPath);

        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/" + article.posterPath)
                .into(holder.n_image);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView n_image;

        public ViewHolder(View itemView) {
            super(itemView);

            n_image     = itemView.findViewById(R.id.moviePoster);

        }
    }
}