package com.example.kuka.moviestorm.activity.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kuka.moviestorm.R;
import com.example.kuka.moviestorm.activity.activity.MainActivity;
import com.example.kuka.moviestorm.activity.model.Movie;

import com.example.kuka.moviestorm.activity.utilities.FavListHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

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

    ArrayList<String> genrelist;
    ArrayList<Integer> genrelistID;

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
        genreHelper();
        populateMovieDetail();
        if (FavListHelper.isAlreadyInFavList(movie)) {
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
        movieDetailCategory.setText(String.valueOf(genrelist));
    }

    @OnClick(R.id.movieAddFavButton)
    public void likeButton() {
        if (FavListHelper.isAlreadyInFavList(movie)) {
            FavListHelper.removeFromFav(movie);
            movieAddFavButton.setBackgroundResource(R.drawable.ic_favorite);

        } else {
            FavListHelper.addToFav(movie);
            movieAddFavButton.setBackgroundResource(R.drawable.ic_favorite_fill);

        }
    }

    public void genreHelper() {
        genrelist = new ArrayList<>();
        Log.e("genreid", String.valueOf(movie.genreID));
        if (movie.genreID.contains(28)) {
            genrelist.add("Aksiyon");
        }if (movie.genreID.contains(12)) {
            genrelist.add("Macera");
        }if (movie.genreID.contains(16)) {
            genrelist.add("Animasyon");
        }if (movie.genreID.contains(35)) {
            genrelist.add("Komedi");
        }if (movie.genreID.contains(80)) {
            genrelist.add("Suç");
        }if (movie.genreID.contains(99)) {
            genrelist.add("Belgesel");
        }if (movie.genreID.contains(18)) {
            genrelist.add("Dram");
        }if (movie.genreID.contains(10751)) {
            genrelist.add("Aile");
        }if (movie.genreID.contains(14)) {
            genrelist.add("Fantastik");
        }if (movie.genreID.contains(36)) {
            genrelist.add("Tarih");
        }if (movie.genreID.contains(27)) {
            genrelist.add("korku");
        }if (movie.genreID.contains(10402)) {
            genrelist.add("Müzik");
        }if (movie.genreID.contains(9648)) {
            genrelist.add("Gizem");
        }if (movie.genreID.contains(10749)) {
            genrelist.add("Romantik");
        }if (movie.genreID.contains(878)) {
            genrelist.add("Bilim-Kurgu");
        }if (movie.genreID.contains(10770)) {
            genrelist.add("TV");
        }if (movie.genreID.contains(53)) {
            genrelist.add("Gerilim");
        }if (movie.genreID.contains(10752)) {
            genrelist.add("Savaş");
        }if (movie.genreID.contains(37)) {
            genrelist.add("Vahşi Batı");
        }

        Log.e("genreittitle==", String.valueOf(genrelist));

    }
}





