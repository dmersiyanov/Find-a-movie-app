package com.mersiyanov.dmitry.find_a_movie.data;

import com.mersiyanov.dmitry.find_a_movie.domain.MovieEntity;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

import static com.mersiyanov.dmitry.find_a_movie.data.network.RetrofitHelper.getApi;

/**
 * Created by Dmitry on 20.03.2018.
 */

public class DataManager  {

    private final String API_KEY = "d160bbfc";
    private Realm mRealm;

    public Observable<MovieEntity> getMovieFromImdb(String title) {
        return getApi().getMovieInfo(API_KEY, title);
    }

    public MovieEntity insert(MovieEntity movieEntity) {
        mRealm.beginTransaction();
        MovieEntity movie = mRealm.copyToRealmOrUpdate(movieEntity);
        mRealm.commitTransaction();
        return movie;
    }

    public void updateFlag(boolean isFavorite, MovieEntity movieEntity) {
        mRealm.beginTransaction();
        movieEntity.setFavorite(isFavorite);
        mRealm.commitTransaction();
    }


    public RealmResults<MovieEntity> getAll() {
        mRealm.beginTransaction();
        RealmResults<MovieEntity> movieEntityRealmResults = mRealm.where(MovieEntity.class).findAll();
        mRealm.commitTransaction();
        return movieEntityRealmResults;
    }

    public RealmResults<MovieEntity> getWithCondition(String fieldName, boolean value) {
        mRealm.beginTransaction();
        RealmResults<MovieEntity> movieEntityRealmResults = mRealm.where(MovieEntity.class).equalTo(fieldName, value).findAll();
        mRealm.commitTransaction();
        return movieEntityRealmResults;
    }

    public void deleteWithCondition(String fieldName, boolean value) {
        mRealm.beginTransaction();
        mRealm.where(MovieEntity.class).equalTo(fieldName, value).findAll().deleteAllFromRealm();
        mRealm.commitTransaction();
    }


    public void initDB() {
        mRealm = Realm.getDefaultInstance();
    }

    public void closeDB() {
        mRealm.close();
    }
}
