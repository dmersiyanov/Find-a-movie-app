package com.mersiyanov.dmitry.find_a_movie.di;

import com.mersiyanov.dmitry.find_a_movie.data.DataManager;
import com.mersiyanov.dmitry.find_a_movie.presentation.presenter.FavoritesPresenter;
import com.mersiyanov.dmitry.find_a_movie.presentation.presenter.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dmitry on 21.03.2018.
 */

@Module
public class PresenterModule {

    @Provides
    DataManager provideDataManager() {
        return new DataManager();
    }

    @Provides
    MainPresenter provideMainPresenter(DataManager dataManager) {
        return new MainPresenter(dataManager);
    }

    @Provides
    FavoritesPresenter provideFavoritesPresenter(DataManager dataManager) {
        return new FavoritesPresenter(dataManager);
    }

}
