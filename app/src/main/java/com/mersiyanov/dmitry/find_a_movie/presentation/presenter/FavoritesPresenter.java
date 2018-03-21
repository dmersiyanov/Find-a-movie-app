package com.mersiyanov.dmitry.find_a_movie.presentation.presenter;

import android.support.v7.app.AppCompatActivity;

import com.mersiyanov.dmitry.find_a_movie.data.DataManager;
import com.mersiyanov.dmitry.find_a_movie.domain.MovieEntity;
import com.mersiyanov.dmitry.find_a_movie.presentation.view.FavoritesActivity;

import io.realm.RealmResults;

/**
 * Created by Dmitry on 20.03.2018.
 */

public class FavoritesPresenter extends BasePresenter {

    private FavoritesActivity activity;

    public FavoritesPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onAttach(AppCompatActivity view) {
        super.onAttach(view);
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
        super.detachView();
        activity = null;
    }

    public RealmResults<MovieEntity> getFavoritesFromDB() {
        RealmResults<MovieEntity> movieEntityRealmResults = getDataManager().getWithCondition("isFavorite", true);
        if (movieEntityRealmResults.size() == 0) {
            activity.showNoFavoritesPic();
        }
        return movieEntityRealmResults;
    }

    public void setFavorite(boolean isFavorite, MovieEntity movieEntity) {
        getDataManager().updateFlag(isFavorite, movieEntity);
    }






}
