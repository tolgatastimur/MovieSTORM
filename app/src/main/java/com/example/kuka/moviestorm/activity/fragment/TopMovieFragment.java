package com.example.kuka.moviestorm.activity.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kuka.moviestorm.R;
import com.example.kuka.moviestorm.activity.activity.MainActivity;
import com.example.kuka.moviestorm.activity.adapter.MoviesAdapter;
import com.example.kuka.moviestorm.activity.model.MoviesResponse;
import com.example.kuka.moviestorm.activity.service.ServiceConnector;
import com.example.kuka.moviestorm.activity.utilities.ProgressDialogMovie;
import com.example.kuka.moviestorm.activity.utilities.ProgressDialogMovieHelper;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopMovieFragment extends Fragment {
    private MoviesResponse moviesResponse;
    public MoviesAdapter adapter;
    public RecyclerView n_movieViewer;
    private GridLayoutManager lLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies_top, container, false);
        ButterKnife.bind(this, view);
        n_movieViewer = (RecyclerView) view.findViewById(R.id.movieViewer);
        populateMovies();

        return view;
    }

    public void populateMovies() {
        ProgressDialogMovieHelper.showCircularProgressDialogMovie();
        ServiceConnector.movieAPI.getTopRatedMovies("b155b3b83ec4d1cbb1e9576c41d00503", "tr").enqueue(new Callback<MoviesResponse>() {

            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                if (response != null) {
                    ProgressDialogMovieHelper.dismiss();
                    moviesResponse = response.body();
                    adapter = new MoviesAdapter(moviesResponse.results);
                    lLayout = new GridLayoutManager(MainActivity.activity, 2);
                    n_movieViewer.setLayoutManager(lLayout);
                    n_movieViewer.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Error ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
