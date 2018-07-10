package com.example.kuka.moviestorm.activity;

import android.app.Application;

import com.example.kuka.moviestorm.activity.service.ServiceConnector;
import com.example.kuka.moviestorm.activity.utilities.FavListHelper;
import com.orhanobut.hawk.Hawk;

public class MovieStormClientApp extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        ServiceConnector.init();
        Hawk.init(this).build();
        FavListHelper.init();
    }
}
