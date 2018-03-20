package com.mersiyanov.dmitry.find_a_movie;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by Dmitry on 17.03.2018.
 */

public class MovieApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}