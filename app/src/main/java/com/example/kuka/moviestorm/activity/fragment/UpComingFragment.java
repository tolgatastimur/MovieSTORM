package com.example.kuka.moviestorm.activity.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kuka.moviestorm.R;
import com.example.kuka.moviestorm.activity.adapter.UpComingAdapter;
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

public class UpComingFragment extends Fragment {
    UpComingAdapter upComingAdapter;
    private static final String TAG = "MainActivity";

    private GridLayoutManager gridLayoutManager;

    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    // limiting to 5 for this tutorial, since total pages in actual API is very large. Feel free to modify.
    private int TOTAL_PAGES = 5;
    private int currentPage = PAGE_START;

    private MovieAPI movieService;
    @BindView(R.id.movieUpComingViewer)
    RecyclerView movieUpComingViewer;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_movies_nextcoming,container,false);
        ButterKnife.bind(this,view);
        upComingAdapter=new UpComingAdapter(getContext());
        populatePage();
        return view;
    }
    private void populatePage(){
        gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(),2);
        movieUpComingViewer.setLayoutManager(gridLayoutManager);
        movieUpComingViewer.setItemAnimator(new DefaultItemAnimator());
        movieUpComingViewer.setAdapter(upComingAdapter);

        movieUpComingViewer.addOnScrollListener(new PaginationScrollListener(gridLayoutManager) {
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
                List<Movie> results = fetchResults(response);
                upComingAdapter.addAll(results);

                if (currentPage <= TOTAL_PAGES) upComingAdapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                t.printStackTrace();

            }
        });

    }

    private List<Movie> fetchResults(Response<MoviesResponse> response) {
        MoviesResponse topRatedMovies = response.body();
        return topRatedMovies.getResults();
    }

    private void loadNextPage() {
        Log.d(TAG, "loadNextPage: " + currentPage);

        callTopRatedMoviesApi().enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                upComingAdapter.removeLoadingFooter();
                isLoading = false;

                List<Movie> results = fetchResults(response);
                upComingAdapter.addAll(results);

                if (currentPage != TOTAL_PAGES) upComingAdapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                t.printStackTrace(); }
        });
    }
    private Call<MoviesResponse> callTopRatedMoviesApi() {
        return movieService.getNextProgram(
                "b155b3b83ec4d1cbb1e9576c41d00503","tr",currentPage,"tr"
        );
    }

}
