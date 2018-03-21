package com.mersiyanov.dmitry.find_a_movie.data.network;

import com.mersiyanov.dmitry.find_a_movie.domain.MovieEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Dmitry on 17.03.2018.
 */

public interface ImdbService {

    @GET(".")
    Observable<MovieEntity> getMovieInfo(@Query("apikey") String key, @Query("t") String title);

}


