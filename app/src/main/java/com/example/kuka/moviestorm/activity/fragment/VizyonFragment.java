package com.example.kuka.moviestorm.activity.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.kuka.moviestorm.R;
import com.example.kuka.moviestorm.activity.adapter.PaginationAdapter;
import com.example.kuka.moviestorm.activity.adapter.VizyonAdapter;
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

public class VizyonFragment extends Fragment {
//    private MoviesResponse moviesResponse;
//    public VizyonAdapter adapter;
//    @BindView(R.id.vizyonViewer)
//    RecyclerView vizyonViewer;
//

    private static final String TAG = "MainActivity";

    PaginationAdapter adapter;
    LinearLayoutManager linearLayoutManager;


    ProgressBar progressBar;

    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    // limiting to 5 for this tutorial, since total pages in actual API is very large. Feel free to modify.
    private int TOTAL_PAGES = 5;
    private int currentPage = PAGE_START;

    private MovieAPI movieService;
    @BindView(R.id.vizyonViewer)
    RecyclerView rv;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies_vizyon, container, false);
        ButterKnife.bind(this, view);
        adapter = new PaginationAdapter(getContext());

        // rv.setLayoutManager(new GridLayoutManager(this, 2));
        //linearLayoutManager = new GridLayoutManager(this, 2);

        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rv.setLayoutManager(linearLayoutManager);

        rv.setItemAnimator(new DefaultItemAnimator());

        rv.setAdapter(adapter);

        rv.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
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

        //init service and load data
        movieService = ServiceConnector.getClient().create(MovieAPI.class);

        loadFirstPage();

        return view;
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

    /**
     * @param response extracts List<{@link Movie>} from response
     * @return
     */
    private List<Movie> fetchResults(Response<MoviesResponse> response) {
        MoviesResponse topRatedMovies = response.body();
        return topRatedMovies.getResults();
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


    /**
     * Performs a Retrofit call to the top rated movies API.
     * Same API call for Pagination.
     * As {@link #currentPage} will be incremented automatically
     * by @{@link PaginationScrollListener} to load next page.
     */
    private Call<MoviesResponse> callTopRatedMoviesApi() {
        return movieService.getNowPlayingMovies(
                "b155b3b83ec4d1cbb1e9576c41d00503","tr",currentPage
        );
    }


}

//    public void populateVizyon() {
//        ProgressDialogMovieHelper.showCircularProgressDialogMovie();
//
//
//        ServiceConnector.movieAPI.getNowPlayingMovies("b155b3b83ec4d1cbb1e9576c41d00503", "tr", PAGE_START).enqueue(new Callback<MoviesResponse>() {
//
//            @Override
//            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
//                if (response != null) {
//                    ProgressDialogMovieHelper.dismiss();
//                    moviesResponse = response.body();
//                    ArrayList<Movie> results = moviesResponse.results;
//                    adapter = new VizyonAdapter(results);
//                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
//                    vizyonViewer.setLayoutManager(mLayoutManager);
//                    vizyonViewer.setAdapter(adapter);
//                }
//            }
//            @Override
//            public void onFailure(Call<MoviesResponse> call, Throwable t) {
//                Toast.makeText(getActivity().getApplicationContext(), "Error ", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }





