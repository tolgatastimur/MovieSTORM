package com.example.kuka.moviestorm.activity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VideoResponse {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("results")
    @Expose
    public ArrayList<MovieVideo> results;


}
