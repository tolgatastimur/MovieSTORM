package com.example.kuka.moviestorm.activity.adapter;

import android.content.Context;
import android.media.Image;
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

public class WatchListAdapter extends RecyclerView.Adapter<WatchListAdapter.ViewHolder> {
    private ArrayList<Movie> movies;

    public WatchListAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public WatchListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_watchlist_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WatchListAdapter.ViewHolder holder, int position) {

        final Movie article = movies.get(position);

        holder.movieWatchListTitle.setText(article.getTitle());

        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/" + article.posterPath)
                .resize(350, 480)
                .into(holder.movieWatchListImage);

        holder.movieWatchListImage.setOnClickListener(new View.OnClickListener() {
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


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView movieWatchListImage;

        TextView movieWatchListTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            movieWatchListTitle = (TextView) itemView.findViewById(R.id.movieWatchListTitle);
            movieWatchListImage=(ImageView) itemView.findViewById(R.id.movieWatchListImage);
            ButterKnife.bind(this, itemView);

        }
    }
}

