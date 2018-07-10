package com.example.kuka.moviestorm.activity.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kuka.moviestorm.R;
import com.example.kuka.moviestorm.activity.adapter.CategoriesAdapter;
import com.example.kuka.moviestorm.activity.adapter.MoviesAdapter;
import com.example.kuka.moviestorm.activity.adapter.VizyonAdapter;
import com.example.kuka.moviestorm.activity.model.Genre;
import com.example.kuka.moviestorm.activity.model.Movie;
import com.example.kuka.moviestorm.activity.model.MoviesResponse;
import com.example.kuka.moviestorm.activity.service.ServiceConnector;
import com.example.kuka.moviestorm.activity.utilities.ProgressDialogMovieHelper;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieCategoriesDetailFragment extends Fragment {
    View view;
    MoviesResponse moviesResponse;
    MoviesAdapter moviesAdapter;


    @BindView(R.id.movieDetailByGenre)
    RecyclerView movieDetailByGenre;


    private static final String GENRE = "genre";
    Genre genre;

    public static MovieCategoriesDetailFragment newInstance(Genre article) {

        Bundle args = new Bundle();
        args.putSerializable(GENRE, article);

        MovieCategoriesDetailFragment fragment = new MovieCategoriesDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_categorries_detail, container, false);
        ButterKnife.bind(this, view);

        genre = (Genre) getArguments().getSerializable(GENRE);
        populateMovieDetail();

        return view;
    }




        public void populateMovieDetail() {
            ProgressDialogMovieHelper.showCircularProgressDialogMovie();


            ServiceConnector.movieAPI.getMoviesByGenre("b155b3b83ec4d1cbb1e9576c41d00503", "tr", "popularity.desc","false","false",1,genre.id).enqueue(new Callback<MoviesResponse>() {

                @Override
                public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                    if (response != null) {
                        ProgressDialogMovieHelper.dismiss();
                        moviesResponse = response.body();
                        ArrayList<Movie> results = moviesResponse.results;
                        moviesAdapter = new MoviesAdapter(results);
                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(),2);
                        movieDetailByGenre.setLayoutManager(mLayoutManager);
                        movieDetailByGenre.setAdapter(moviesAdapter);
                    }
                }

                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {
                    Toast.makeText(getActivity().getApplicationContext(), "Error ", Toast.LENGTH_SHORT).show();
                }
            });
        }



}
