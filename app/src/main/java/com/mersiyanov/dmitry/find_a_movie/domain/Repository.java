package com.mersiyanov.dmitry.find_a_movie.domain;

import com.mersiyanov.dmitry.find_a_movie.POJO.MovieInfo;

/**
 * Created by Dmitry on 20.03.2018.
 */

public interface Repository {


    boolean insert(MovieInfo movieInfo);

    boolean update(MovieInfo movieInfo);

    MovieInfo get(Object id);

    boolean delete(MovieInfo movieInfo);

}
