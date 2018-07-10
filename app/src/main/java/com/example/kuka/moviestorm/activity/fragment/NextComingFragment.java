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
import android.widget.Toast;

import com.example.kuka.moviestorm.R;
import com.example.kuka.moviestorm.activity.activity.MainActivity;
import com.example.kuka.moviestorm.activity.adapter.UpComingAdapter;
import com.example.kuka.moviestorm.activity.adapter.VizyonAdapter;
import com.example.kuka.moviestorm.activity.model.Movie;
import com.example.kuka.moviestorm.activity.model.MoviesResponse;
import com.example.kuka.moviestorm.activity.service.ServiceConnector;
import com.example.kuka.moviestorm.activity.utilities.ProgressDialogMovieHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NextComingFragment extends Fragment {
    MoviesResponse moviesResponse;
    UpComingAdapter upComingAdapter;
    @BindView(R.id.movieUpComingViewer)
    RecyclerView movieUpComingViewer;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_movies_nextcoming,container,false);
        ButterKnife.bind(this,view);
        populateVizyon();
        return view;
    }

    public void populateVizyon() {
        ProgressDialogMovieHelper.showCircularProgressDialogMovie();


        ServiceConnector.movieAPI.getNextProgram("b155b3b83ec4d1cbb1e9576c41d00503", "en",5).enqueue(new Callback<MoviesResponse>() {

            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                if (response != null) {
                    ProgressDialogMovieHelper.dismiss();
                    moviesResponse = response.body();
                    ArrayList<Movie> results = moviesResponse.results;
                    upComingAdapter = new UpComingAdapter(results);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.activity, 2);
                    movieUpComingViewer.setLayoutManager(mLayoutManager);
                    movieUpComingViewer.setAdapter(upComingAdapter);
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Error ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
