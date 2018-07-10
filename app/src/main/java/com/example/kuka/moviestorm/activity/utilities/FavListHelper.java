package com.example.kuka.moviestorm.activity.utilities;

import com.example.kuka.moviestorm.activity.model.Movie;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

public class FavListHelper {

    private final static String MOVIE_LIST = "FAVOURITED_MOVIE_LIST";
    private static ArrayList<Movie> favs;

    public static void init() {
        favs = Hawk.get(MOVIE_LIST, new ArrayList<Movie>());
    }

    public static ArrayList<Movie> getFavs() {
        return favs;
    }

    public static void addToFav(Movie movie) {

        if (!favs.contains(movie))
            favs.add(movie);

        save();
    }

    public static void removeFromFav(Movie movie) {
        if (favs.contains(movie))
            favs.remove(movie);

        save();
    }

    private static void save() {
        Hawk.put(MOVIE_LIST, favs);
    }

    public static boolean isAlreadyInFavList(Movie movie) {
        return favs.contains(movie);
    }
}
