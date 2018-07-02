package com.example.kuka.moviestorm.activity;

import android.app.Application;

import com.example.kuka.moviestorm.activity.service.ServiceConnector;

public class MovieStormClientApp extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        ServiceConnector.init();
    }
}
