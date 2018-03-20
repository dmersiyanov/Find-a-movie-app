package com.mersiyanov.dmitry.find_a_movie.presentation.presenter;

import android.support.v7.app.AppCompatActivity;

import com.mersiyanov.dmitry.find_a_movie.data.DataManager;

/**
 * Created by Dmitry on 20.03.2018.
 */

public class BasePresenter<V extends AppCompatActivity> {

    private V view;
    private DataManager mDataManager;


    public BasePresenter(DataManager dataManager){
        mDataManager = dataManager;
    }

    public void onAttach(V view) {
        this.view = view;
    }

    public V getView() {
        return view;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public void detachView() {
        view = null;
    }


}
