package com.example.kuka.moviestorm.activity.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kuka.moviestorm.R;
import com.example.kuka.moviestorm.activity.activity.MainActivity;
import com.example.kuka.moviestorm.activity.adapter.MoviesAdapter;
import com.example.kuka.moviestorm.activity.adapter.VizyonAdapter;
import com.example.kuka.moviestorm.activity.model.Movie;
import com.example.kuka.moviestorm.activity.model.MoviesResponse;
import com.example.kuka.moviestorm.activity.service.ServiceConnector;
import com.example.kuka.moviestorm.activity.utilities.CustomItemClickListener;
import com.example.kuka.moviestorm.activity.utilities.ProgressDialogMovieHelper;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VizyonFragment extends Fragment {
    private MoviesResponse moviesResponse;
    public VizyonAdapter adapter;

    @BindView(R.id.vizyonViewer)
     RecyclerView vizyonViewer;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies_vizyon, container, false);
        //vizyonViewer = (RecyclerView) view.findViewById(R.id.vizyonViewer);
        populateVizyon();
        ButterKnife.bind(this,view);
        return view;
    }


    public void populateVizyon() {
        ProgressDialogMovieHelper.showCircularProgressDialogMovie();


        ServiceConnector.movieAPI.getNowPlayingMovies("b155b3b83ec4d1cbb1e9576c41d00503", "tr", 1).enqueue(new Callback<MoviesResponse>() {

            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                if (response != null) {
                    ProgressDialogMovieHelper.dismiss();
                    moviesResponse = response.body();
                    ArrayList<Movie> results = moviesResponse.results;
                    adapter = new VizyonAdapter(results);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                    vizyonViewer.setLayoutManager(mLayoutManager);
                    vizyonViewer.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Error ", Toast.LENGTH_SHORT).show();
            }
        });
    }


}


