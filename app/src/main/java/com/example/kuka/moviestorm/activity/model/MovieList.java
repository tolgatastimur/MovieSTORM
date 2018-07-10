package com.example.kuka.moviestorm.activity.model;

import com.google.gson.annotations.SerializedName;

public class MovieList {




    public Integer id;
    public String movie_name;

    public MovieList() {
    }

    public String getLogo_path() {
        return logo_path;
    }

    public void setLogo_path(String logo_path) {
        this.logo_path = logo_path;
    }

    public String logo_path;

    public MovieList(Integer id, String movie_name,String logo_path) {
        this.id = id;
        this.movie_name = movie_name;
        this.logo_path=logo_path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }
}
