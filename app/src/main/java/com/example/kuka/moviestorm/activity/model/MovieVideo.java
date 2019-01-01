package com.example.kuka.moviestorm.activity.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MovieVideo  implements Serializable {
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("key")
    public String videokey;
    @SerializedName("type")
    public String trailer;
    @SerializedName("site")
    public String youtube;


}
