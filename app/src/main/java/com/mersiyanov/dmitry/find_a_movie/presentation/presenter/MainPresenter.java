package com.mersiyanov.dmitry.find_a_movie.presentation.presenter;

import android.support.v7.app.AppCompatActivity;

import com.mersiyanov.dmitry.find_a_movie.data.DataManager;
import com.mersiyanov.dmitry.find_a_movie.data.MovieInfo;

import io.realm.Realm;

/**
 * Created by dmitrymersiyanov on 21/03/2018.
 */

public class MainPresenter extends BasePresenter {

    private Realm mRealm;


    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onAttach(AppCompatActivity view) {
        super.onAttach(view);
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public AppCompatActivity getView() {
        return super.getView();
    }

    @Override
    public DataManager getDataManager() {
        return super.getDataManager();
    }

    @Override
    public void detachView() {
        mRealm.close();
        super.detachView();
    }
    public void setFavorite(boolean isFavorite, MovieInfo movieInfo) {
        mRealm.beginTransaction();
        movieInfo.setFavorite(isFavorite);
        mRealm.commitTransaction();
    }

}
