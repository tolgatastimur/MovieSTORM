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

public class UpComingAdapter extends RecyclerView.Adapter<UpComingAdapter.ViewHolder> {
    private ArrayList<Movie> movies;

    public UpComingAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public UpComingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_upcoming, parent, false);

        return new UpComingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UpComingAdapter.ViewHolder holder, int position) {

        final Movie article = movies.get(position);
        holder.movieUpComingReleaseDate.setText("Yayın Tarihi: "+article.releaseDate);

        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/" + article.posterPath)
                .resize(350, 480)
                .into(holder.movieUpComingImage);

        holder.movieUpComingImage.setOnClickListener(new View.OnClickListener() {
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


        ImageView movieUpComingImage;
        TextView movieUpComingReleaseDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            movieUpComingImage = (ImageView) itemView.findViewById(R.id.movieUpComingImage);
            movieUpComingReleaseDate = (TextView) itemView.findViewById(R.id.movieUpComingReleaseDate);
        }
    }
}