package com.mersiyanov.dmitry.find_a_movie.di;

import com.mersiyanov.dmitry.find_a_movie.presentation.view.FavoritesActivity;
import com.mersiyanov.dmitry.find_a_movie.presentation.view.MainActivity;

import dagger.Component;

/**
 * Created by dmitrymersiyanov on 22/03/2018.
 */

@Component(modules = PresenterModule.class)
public interface AppComponent {

    void injectsMainPresenter(MainActivity mainActivity);
    void injectsFavoritesPresenter(FavoritesActivity favoritesActivity);



}
