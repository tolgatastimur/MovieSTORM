package com.example.kuka.moviestorm.activity.adapter;

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

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopularMoviesAdapter extends RecyclerView.Adapter<PopularMoviesAdapter.ViewHolder> {
    private ArrayList<Movie> movies;

    public PopularMoviesAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public PopularMoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_popular_movies, parent, false);

        return new PopularMoviesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PopularMoviesAdapter.ViewHolder holder, int position) {

        final Movie article = movies.get(position);

        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/" + article.posterPath)
                .resize(350, 480)
                .into(holder.moviePopularImage);

        holder.moviePopularImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // MainActivity.activity.setNewFragment(MovieDetailFragment.newInstance(article), R.id.contentFL, "MovieDetailFragment", true, true, false, false);
            }
        });


    }


    @Override
    public int getItemCount() {
        return movies.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.moviePopularImage)
        ImageView moviePopularImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}