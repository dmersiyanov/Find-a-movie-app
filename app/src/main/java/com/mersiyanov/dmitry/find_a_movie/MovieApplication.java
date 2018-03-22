package com.mersiyanov.dmitry.find_a_movie;

import android.app.Application;

import com.mersiyanov.dmitry.find_a_movie.di.AppComponent;
import com.mersiyanov.dmitry.find_a_movie.di.DaggerAppComponent;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Dmitry on 17.03.2018.
 */

public class MovieApplication extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.create();
        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);

    }

    public static AppComponent getComponent() {
        return component;
    }
}