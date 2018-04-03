package com.mersiyanov.dmitry.find_a_movie.presentation.presenter;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.mersiyanov.dmitry.find_a_movie.data.DataManager;
import com.mersiyanov.dmitry.find_a_movie.domain.MovieEntity;
import com.mersiyanov.dmitry.find_a_movie.presentation.view.MainActivity;

import java.util.Date;

import io.realm.RealmResults;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dmitrymersiyanov on 21/03/2018.
 */

public class MainPresenter extends BasePresenter {

    private MainActivity mainActivity;

    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onAttach(AppCompatActivity view) {
        super.onAttach(view);
        mainActivity = (MainActivity) view;
    }

    @Override
    public AppCompatActivity getView() {
        return mainActivity;
    }

    @Override
    public DataManager getDataManager() {
        return super.getDataManager();
    }

    @Override
    public void detachView() {
        super.detachView();
        mainActivity = null;
    }
    public void setFavorite(boolean isFavorite, MovieEntity movieEntity) {
        getDataManager().updateFlag(isFavorite, movieEntity);
    }

    public void getMovieFromImdb(String title) {
        getDataManager().getMovieFromImdb(title).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieEntity>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(mainActivity.getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(MovieEntity movieEntity) {
                        if(movieEntity.getResponse().equals("False"))
                            Toast.makeText(mainActivity.getApplicationContext(), movieEntity.getError(), Toast.LENGTH_LONG).show();
                        else  {
                            movieEntity.setDateTime(new Date());
                            mainActivity.getMoviesAdapter().addMovie(getDataManager().insert(movieEntity));
                        }
                    }
                });
    }

    public RealmResults<MovieEntity> getAllMoviesFromDB() {
        return getDataManager().getAll();
    }

    public void deleteFromDb(String key, boolean value) {
        getDataManager().deleteWithCondition(key, value);
    }



}
