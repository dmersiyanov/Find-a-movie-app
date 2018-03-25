package com.mersiyanov.dmitry.find_a_movie.di;

import com.mersiyanov.dmitry.find_a_movie.data.DataManager;
import com.mersiyanov.dmitry.find_a_movie.data.network.RetrofitHelper;
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
    RetrofitHelper provideRetrofitHelper() {
        return new RetrofitHelper();
    }

    @Provides
    DataManager provideDataManager(RetrofitHelper retrofitHelper) {
        return new DataManager(retrofitHelper);
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
