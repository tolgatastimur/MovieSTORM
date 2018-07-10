package com.example.kuka.moviestorm.activity.service;

import android.text.Editable;

import com.example.kuka.moviestorm.activity.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieAPI {
    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey, @Query("language") String language,@Query("page") int page);

    @GET("movie/now_playing")
    Call<MoviesResponse> getNowPlayingMovies(@Query("api_key") String apiKey,
                                             @Query("language") String language,
                                             @Query("page") int page);

    @GET("discover/movie")
    Call<MoviesResponse> getMoviesByGenre(@Query("api_key") String apiKey,
                                             @Query("language") String language,
                                             @Query("sort_by") String sortBy,
                                             @Query("include_adult") String includeAdult,
                                             @Query("include_video") String includeVideo,
                                             @Query("page") int page,
                                             @Query("with_genres") int genreID

    );

    @GET("genre/movie/list")
    Call<MoviesResponse> getGenreList(@Query("api_key") String apiKey,
                                     @Query("language") String language);

    @GET("movie/upcoming")
    Call<MoviesResponse> getNextProgram(@Query("api_key") String apiKey,
                                     @Query("language") String language,
                                       @Query("page") int page
                                       );
    @GET("search/movie")
    Call<MoviesResponse> getSearch(@Query("api_key") String apiKey,
                                      @Query("language") String language,
                                   @Query("query") Editable query,
                                   @Query("page") int page
                                   );


}
