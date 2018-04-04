package com.mersiyanov.dmitry.find_a_movie.data;

import com.mersiyanov.dmitry.find_a_movie.data.network.RetrofitHelper;
import com.mersiyanov.dmitry.find_a_movie.domain.MovieEntity;

import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

/**
 * Created by Dmitry on 20.03.2018.
 */

@Singleton
public class DataManager  {

    private final String API_KEY = "d160bbfc";
    private Realm mRealm;
    private RetrofitHelper retrofitHelper;

    public DataManager(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }

    public Observable<MovieEntity> getMovieFromImdb(String title) {
        return retrofitHelper.getApi().getMovieInfo(API_KEY, title);
    }

    // TODO: add realm listeners instead of returning objects after insertion

    public MovieEntity insert(final MovieEntity movieEntity) {
        mRealm.beginTransaction();
        MovieEntity movie = mRealm.copyToRealmOrUpdate(movieEntity);
        mRealm.commitTransaction();
        return movie;
    }

    public void updateFlag(final boolean isFavorite, final MovieEntity movieEntity) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                movieEntity.setFavorite(isFavorite);
            }
        });
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

    public RealmResults<MovieEntity> getWithCondition(String fieldName, String value) {
        mRealm.beginTransaction();
        RealmResults<MovieEntity> movieEntityRealmResults = mRealm.where(MovieEntity.class).equalTo(fieldName, value).findAll();
        mRealm.commitTransaction();
        return movieEntityRealmResults;
    }

    public void deleteWithCondition(final String fieldName, final boolean value) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mRealm.where(MovieEntity.class).equalTo(fieldName, value).findAll().deleteAllFromRealm();
            }
        });
    }

    public void initDB() {
        mRealm = Realm.getDefaultInstance();
    }

    public void closeDB() {
        mRealm.close();
    }
}
