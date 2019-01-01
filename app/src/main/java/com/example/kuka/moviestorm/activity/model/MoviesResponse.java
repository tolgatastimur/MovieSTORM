package com.example.kuka.moviestorm.activity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MoviesResponse {
    @SerializedName("page")
    @Expose
    public int page;

    public ArrayList<Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }

    @SerializedName("results")
    @Expose
    public ArrayList<Movie> results;

    @SerializedName("total_results")
    @Expose
    public int total_results;

    @SerializedName("total_pages")
    @Expose
    public int total_pages;

    @SerializedName("genres")
    public ArrayList<Genre> genres;










   /* public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }



    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public MoviesResponse(int page, List<Movie> results, int total_results, int total_pages) {
        this.page = page;
        this.results = results;
        this.total_results = total_results;
        this.total_pages = total_pages;
    }*/
}
