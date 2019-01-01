package com.example.kuka.moviestorm.activity.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kuka.moviestorm.R;
import com.example.kuka.moviestorm.activity.adapter.MovieCategoriesDetailAdapter;
import com.example.kuka.moviestorm.activity.model.Genre;
import com.example.kuka.moviestorm.activity.model.Movie;
import com.example.kuka.moviestorm.activity.model.MoviesResponse;
import com.example.kuka.moviestorm.activity.service.MovieAPI;
import com.example.kuka.moviestorm.activity.service.ServiceConnector;
import com.example.kuka.moviestorm.activity.utilities.PaginationScrollListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieCategoriesDetailFragment extends Fragment {
    View view;


    private static final String TAG = "MainActivity";

    MovieCategoriesDetailAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 5;
    private int currentPage = PAGE_START;

    private MovieAPI movieService;
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
        adapter=new MovieCategoriesDetailAdapter(getContext());
        populatePage();

        return view;
    }
    private void populatePage(){
        linearLayoutManager = new GridLayoutManager(getContext(),2);
        movieDetailByGenre.setLayoutManager(linearLayoutManager);

        movieDetailByGenre.setItemAnimator(new DefaultItemAnimator());

        movieDetailByGenre.setAdapter(adapter);

        movieDetailByGenre.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                // mocking network delay for API call
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNextPage();
                    }
                }, 1000);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
        movieService = ServiceConnector.getClient().create(MovieAPI.class);

        loadFirstPage();

    }

    private void loadFirstPage() {
        Log.d(TAG, "loadFirstPage: ");

        callTopRatedMoviesApi().enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                // Got data. Send it to adapter

                List<Movie> results = fetchResults(response);
                adapter.addAll(results);

                if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                t.printStackTrace();

            }
        });

    }



    private void loadNextPage() {
        Log.d(TAG, "loadNextPage: " + currentPage);

        callTopRatedMoviesApi().enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                adapter.removeLoadingFooter();
                isLoading = false;

                List<Movie> results = fetchResults(response);
                adapter.addAll(results);

                if (currentPage != TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                t.printStackTrace(); }
        });
    }


    private List<Movie> fetchResults(Response<MoviesResponse> response) {
        MoviesResponse topRatedMovies = response.body();
        return topRatedMovies.getResults();
    }
    private Call<MoviesResponse> callTopRatedMoviesApi() {
        return movieService.getMoviesByGenre(
                "b155b3b83ec4d1cbb1e9576c41d00503", "tr", "popularity.desc", "false", "false", currentPage, genre.id
        );
    }
}
