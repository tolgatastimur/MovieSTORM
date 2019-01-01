package com.example.kuka.moviestorm.activity.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kuka.moviestorm.R;
import com.example.kuka.moviestorm.activity.activity.MainActivity;
import com.example.kuka.moviestorm.activity.model.Genre;
import com.example.kuka.moviestorm.activity.model.Movie;
import com.example.kuka.moviestorm.activity.model.MovieVideo;
import com.example.kuka.moviestorm.activity.model.MoviesResponse;
import com.example.kuka.moviestorm.activity.model.VideoResponse;
import com.example.kuka.moviestorm.activity.service.ServiceConnector;
import com.example.kuka.moviestorm.activity.utilities.FavListHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailFragment extends Fragment {

    private static final String ARTICLE = "article";
    private static final String YOUTUBE_PATH = "https://www.youtube.com/watch?v=";
    @BindView(R.id.trailerButton)
    Button trailerButton;
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
    VideoResponse videoResponse;
    MoviesResponse moviesResponse;
    ArrayList<MovieVideo> result;
    ArrayList<Genre> genre;
    ArrayList<String> getGenrelist;

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
        //serviceRequest();
        //setMovieDetail();
        populateMovieDetail();
        if (FavListHelper.isAlreadyInFavList(movie)) {
            movieAddFavButton.setBackgroundResource(R.drawable.ic_favorite_fill);
        }

        return view;
    }
//    public void setMovieDetail(){
//        genre=new ArrayList<>();
//        getGenrelist=new ArrayList<>();
//        ServiceConnector.movieAPI.getGenre(movie.id, "b155b3b83ec4d1cbb1e9576c41d00503", "tr").enqueue(new Callback<MoviesResponse>() {
//
//            @Override
//            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
//                if (response != null) {
//                    moviesResponse = response.body();
//                     genre= moviesResponse.genres;
//                    for (int i=0;i<moviesResponse.genres.size();i++){
//                        getGenrelist.add(genre.get(i).name);
//                    }
//                    movieDetailCategory.setText(String.valueOf(getGenrelist));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MoviesResponse> call, Throwable t) {
//                Toast.makeText(MainActivity.activity, "Servis Bağlantısı yok.", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }

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
        if (FavListHelper.isAlreadyInFavList(movie)) {
            FavListHelper.removeFromFav(movie);
            movieAddFavButton.setBackgroundResource(R.drawable.ic_favorite);

        } else {
            FavListHelper.addToFav(movie);
            movieAddFavButton.setBackgroundResource(R.drawable.ic_favorite_fill);

        }
    }

//    public void serviceRequest() {
//        result = new ArrayList<>();
//        ServiceConnector.movieAPI.getTrailer(movie.id, "b155b3b83ec4d1cbb1e9576c41d00503", "en").enqueue(new Callback<VideoResponse>() {
//
//            @Override
//            public void onResponse(@NonNull Call<VideoResponse> call, @NonNull Response<VideoResponse> response) {
//                if (response != null) {
//                    videoResponse = response.body();
//                    result = videoResponse.results;
//                    Log.e("is empty == ",result.toString());
//
//                    trailerButton.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            if (result.size()==0) {
//                                Toast.makeText(MainActivity.activity, "Mevcut Trailer Bulunmamaktadır.", Toast.LENGTH_SHORT).show();
//
//                            } else {
//                                Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(YOUTUBE_PATH + result.get(0).videokey));
//                                startActivity(browse);
//                            }
//                        }
//                    });
//                }
//            }
//            @Override
//            public void onFailure(Call<VideoResponse> call, Throwable t) {
//                Toast.makeText(MainActivity.activity, "Servis Bağlantısı yok.", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}





