package com.example.kuka.moviestorm.activity.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kuka.moviestorm.R;
import com.example.kuka.moviestorm.activity.activity.MainActivity;
import com.example.kuka.moviestorm.activity.model.FavoriteItem;
import com.example.kuka.moviestorm.activity.model.Movie;

import com.example.kuka.moviestorm.activity.model.MovieList;
import com.example.kuka.moviestorm.activity.model.MyFavoriteItems;
import com.example.kuka.moviestorm.activity.utilities.FavListHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieDetailFragment extends Fragment {

    private static final String ARTICLE = "article";
    @BindView(R.id.movieDetailImage)
    ImageView movieDetailImage;
    @BindView(R.id.movieDetailTitle)
    TextView movieDetailTitle;
    @BindView(R.id.movieDetailOverview)
    TextView movieDetailOverview;
    @BindView(R.id.movieDetailReleaseDate)
    TextView movieDetailReleaseDate;
    @BindView(R.id.movieDetailOriginalLanguage)
    TextView movieDetailOriginalLanguage;
    @BindView(R.id.movieDetailIMBDB)
    TextView movieDetailIMBDB;
    @BindView(R.id.movieDetailCategory)
    TextView movieDetailCategory;
    @BindView(R.id.movieAddFavButton)
    ImageButton movieAddFavButton;
    Movie movie;
    Context context;

    DatabaseReference databaseReference;


    public static MovieDetailFragment newInstance(Movie article) {

        Bundle args = new Bundle();
        args.putSerializable(ARTICLE, article);

        MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, view);
        movie = (Movie) getArguments().getSerializable(ARTICLE);
        context = MainActivity.activity;
        databaseReference = FirebaseDatabase.getInstance().getReference("Movies");
        populateMovieDetail();
        if (FavListHelper.isAlreadyInFavList(movie)){
            movieAddFavButton.setBackgroundResource(R.drawable.ic_favorite_fill);
        }

        return view;
    }

    public void populateMovieDetail() {
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/" + movie.posterPath)
                .resize(360, 520)
                .into(movieDetailImage);

        movieDetailTitle.setText(movie.title);
        movieDetailOverview.setText(movie.overview);
        movieDetailReleaseDate.setText(movie.releaseDate);
        movieDetailOriginalLanguage.setText(movie.originsalLanguage);
        movieDetailIMBDB.setText(String.valueOf(movie.voteAverage) + "/10");
    }

    @OnClick(R.id.movieAddFavButton)
    public void likeButton() {
        if (FavListHelper.isAlreadyInFavList(movie)){
            FavListHelper.removeFromFav(movie);
            movieAddFavButton.setBackgroundResource(R.drawable.ic_favorite);

        }

        else {
            FavListHelper.addToFav(movie);
            movieAddFavButton.setBackgroundResource(R.drawable.ic_favorite_fill);

        }
    }
}


