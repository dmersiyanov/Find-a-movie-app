package com.mersiyanov.dmitry.find_a_movie.presentation;

import android.support.v7.app.AppCompatActivity;

import com.mersiyanov.dmitry.find_a_movie.POJO.MovieInfo;
import com.mersiyanov.dmitry.find_a_movie.data.DataManager;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Dmitry on 20.03.2018.
 */

public class FavoritesPresenter extends BasePresenter {

    private FavoritesActivity activity;
    private Realm mRealm;

    public FavoritesPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onAttach(AppCompatActivity view) {
        activity = (FavoritesActivity) view;
    }


    @Override
    public AppCompatActivity getView() {
        return activity;
    }

    @Override
    public DataManager getDataManager() {
        return super.getDataManager();
    }

    @Override
    public void detachView() {
        activity = null;
    }

    public RealmResults<MovieInfo> getFavoritesFromDB() {
        mRealm = Realm.getDefaultInstance();
        mRealm.beginTransaction();
        RealmResults<MovieInfo> movieInfoRealmResults = mRealm.where(MovieInfo.class).equalTo("isFavorite", true).findAll();
        mRealm.commitTransaction();
        if (movieInfoRealmResults.size() == 0) {
            activity.showNoFavoritesPic();
        }
        return movieInfoRealmResults;
    }

    public void setFavorite(boolean isFavorite, MovieInfo movieInfo) {
        mRealm.beginTransaction();
        movieInfo.setFavorite(isFavorite);
        mRealm.commitTransaction();
    }






}
