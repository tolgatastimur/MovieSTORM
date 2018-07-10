package com.example.kuka.moviestorm.activity.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kuka.moviestorm.R;
import com.example.kuka.moviestorm.activity.activity.MainActivity;
import com.example.kuka.moviestorm.activity.adapter.WatchListAdapter;
import com.example.kuka.moviestorm.activity.model.Movie;
import com.example.kuka.moviestorm.activity.utilities.FavListHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WatchListFragment extends Fragment {


    @BindView(R.id.watchListViewer)
    RecyclerView watchListViewer;
    WatchListAdapter watchListAdapter;
    private GridLayoutManager gridLayoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_watch_list, container, false);
        ButterKnife.bind(this, view);

        Log.e("fav list size === ", FavListHelper.getFavs().size() + "");

        listFavs();
        return view;
    }

    public void listFavs() {
        watchListAdapter = new WatchListAdapter(FavListHelper.getFavs());
        gridLayoutManager = new GridLayoutManager(MainActivity.activity, 2);
        watchListViewer.setLayoutManager(gridLayoutManager);
        watchListViewer.setAdapter(watchListAdapter);
    }

}
