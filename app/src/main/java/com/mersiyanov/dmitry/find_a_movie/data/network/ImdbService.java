package com.mersiyanov.dmitry.find_a_movie.data.network;

import com.mersiyanov.dmitry.find_a_movie.data.MovieInfo;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Dmitry on 17.03.2018.
 */

public interface ImdbService {

    @GET(".")
    Observable<MovieInfo> getMovieInfo(@Query("apikey") String key, @Query("t") String title);

}


