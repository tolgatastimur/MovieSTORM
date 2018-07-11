package com.example.kuka.moviestorm.activity.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Serializable {
    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("adult")
    public boolean adult;

    @SerializedName("overview")
    public String overview;

    @SerializedName("release_date")
    public String releaseDate;

    @SerializedName("genre_ids")
    public List<Integer> genreID = new ArrayList<Integer>();

    @SerializedName("id")
    public Integer id;

    @SerializedName("original_title")
    public String orginalTitle;

    @SerializedName("original_language")
    public String originsalLanguage;

    @SerializedName("title")
    public String title;

    @SerializedName("backdropPath")
    public String backdropPath;

    @SerializedName("popularity")
    public Double popularity;

    @SerializedName("vote_count")
    public Integer voteCount;

    @SerializedName("video")
    public Boolean video;

    @SerializedName("vote_average")
    public Double voteAverage;


    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Movie)) return false;
        Movie otherMovie = (Movie) other;

        return otherMovie.id.equals(this.id);
    }


    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Integer> getGenreID() {
        return genreID;
    }

    public void setGenreID(List<Integer> genreID) {
        this.genreID = genreID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrginalTitle() {
        return orginalTitle;
    }

    public void setOrginalTitle(String orginalTitle) {
        this.orginalTitle = orginalTitle;
    }

    public String getOriginsalLanguage() {
        return originsalLanguage;
    }

    public void setOriginsalLanguage(String originsalLanguage) {
        this.originsalLanguage = originsalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }


    public String getBaseImageUrl() {
        return baseImageUrl;
    }

    public void setBaseImageUrl(String baseImageUrl) {
        this.baseImageUrl = baseImageUrl;
    }

    public Movie() {
        this.posterPath = posterPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.genreID = genreID;
        this.id = id;
        this.orginalTitle = orginalTitle;
        this.originsalLanguage = originsalLanguage;
        this.title = title;
        this.backdropPath = backdropPath;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.video = video;
    }

    String baseImageUrl = "https://image.tmdb.org/t/p/w500";

    public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w500" + posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;

    }


}
