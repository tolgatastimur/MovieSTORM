package com.example.kuka.moviestorm.activity.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kuka.moviestorm.R;
import com.example.kuka.moviestorm.activity.activity.MainActivity;
import com.example.kuka.moviestorm.activity.adapter.MoviesAdapter;
import com.example.kuka.moviestorm.activity.model.Movie;
import com.example.kuka.moviestorm.activity.model.MoviesResponse;
import com.example.kuka.moviestorm.activity.service.ServiceConnector;
import com.example.kuka.moviestorm.activity.utilities.ProgressDialogMovieHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {
    MoviesResponse moviesResponse;
    MoviesAdapter moviesAdapter;
    @BindView(R.id.movieSearchText)
    EditText movieSearchText;
    @BindView(R.id.movieSearchViewer)
    RecyclerView movieSearchViewer;
    @BindView(R.id.movieSearchButton)
    Button movieSearchButton;
    String movieSearchTextContent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this,view);
        search();
        return view;
    }
public void search(){
    movieSearchText.addTextChangedListener(new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {

            // put a debug statement to check
            if(movieSearchText.getText().length()!=0){
                ProgressDialogMovieHelper.showCircularProgressDialogMovie();


                ServiceConnector.movieAPI.getSearch("b155b3b83ec4d1cbb1e9576c41d00503", "tr", movieSearchText.getText(), 1).enqueue(new Callback<MoviesResponse>() {

                    @Override
                    public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                        if (response != null) {
                            ProgressDialogMovieHelper.dismiss();
                            moviesResponse = response.body();
                            ArrayList<Movie> results = moviesResponse.results;
                            moviesAdapter = new MoviesAdapter(results);
                            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
                            movieSearchViewer.setLayoutManager(mLayoutManager);
                            movieSearchViewer.setAdapter(moviesAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        Toast.makeText(getActivity().getApplicationContext(), "Error ", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else{
                Toast.makeText(MainActivity.activity, "Arama alanı boş.", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }



    });}
    @OnClick(R.id.movieSearchButton)
    public void populateVizyon() {
        if(movieSearchText.getText().length()!=0){
        ProgressDialogMovieHelper.showCircularProgressDialogMovie();


        ServiceConnector.movieAPI.getSearch("b155b3b83ec4d1cbb1e9576c41d00503", "tr", movieSearchText.getText(), 1).enqueue(new Callback<MoviesResponse>() {

            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                if (response != null) {
                    ProgressDialogMovieHelper.dismiss();
                    moviesResponse = response.body();
                    ArrayList<Movie> results = moviesResponse.results;
                    moviesAdapter = new MoviesAdapter(results);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
                    movieSearchViewer.setLayoutManager(mLayoutManager);
                    movieSearchViewer.setAdapter(moviesAdapter);
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Error ", Toast.LENGTH_SHORT).show();
            }
        });
    }
    else{
            Toast.makeText(MainActivity.activity, "Arama alanı boş.", Toast.LENGTH_SHORT).show();
        }
    }
}
