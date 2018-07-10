package com.example.kuka.moviestorm.activity.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kuka.moviestorm.R;
import com.example.kuka.moviestorm.activity.activity.MainActivity;
import com.example.kuka.moviestorm.activity.adapter.CategoriesAdapter;
import com.example.kuka.moviestorm.activity.model.MoviesResponse;
import com.example.kuka.moviestorm.activity.service.ServiceConnector;
import com.example.kuka.moviestorm.activity.utilities.ProgressDialogMovieHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieCategoriesFragment extends Fragment {
    MoviesResponse genreResponse;
    CategoriesAdapter categoriesAdapter;
    @BindView(R.id.movieCategoriesViewer)
    RecyclerView movieCategoriesViewer;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_categories, container, false);
        ButterKnife.bind(this, view);
        populateCategoriesDetail();
        return view;
    }

    public void populateCategoriesDetail() {

        ProgressDialogMovieHelper.showCircularProgressDialogMovie();

        ServiceConnector.movieAPI.getGenreList("b155b3b83ec4d1cbb1e9576c41d00503", "tr").enqueue(new Callback<MoviesResponse>() {

            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                if (response != null) {
                    ProgressDialogMovieHelper.dismiss();
                    genreResponse = response.body();
                    categoriesAdapter = new CategoriesAdapter(genreResponse.genres);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                    movieCategoriesViewer.setLayoutManager(mLayoutManager);
                    movieCategoriesViewer.setAdapter(categoriesAdapter);
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Toast.makeText(MainActivity.activity, "", Toast.LENGTH_SHORT).show();
            }
        });

    }
}