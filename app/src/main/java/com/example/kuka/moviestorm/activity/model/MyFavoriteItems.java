package com.example.kuka.moviestorm.activity.model;

import java.util.ArrayList;

public class MyFavoriteItems {

    ArrayList<FavoriteItem> favoriteItems = new ArrayList<>();

    private static final MyFavoriteItems ourInstance = new MyFavoriteItems();

    public static MyFavoriteItems getInstance() {
        return ourInstance;
    }

    private MyFavoriteItems() {
    }

    public ArrayList<FavoriteItem> getFavoriteItems() {
        return favoriteItems;
    }

    public void setFavoriteItems(ArrayList<FavoriteItem> favoriteItems) {
        this.favoriteItems = favoriteItems;
    }
}
