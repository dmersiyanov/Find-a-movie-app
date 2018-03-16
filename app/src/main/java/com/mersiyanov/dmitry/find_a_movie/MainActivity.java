
package com.mersiyanov.dmitry.find_a_movie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mersiyanov.dmitry.find_a_movie.POJO.MovieInfo;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<MovieInfo> responseMovies = MovieApp.getApi().getMovieInfo("d160bbfc", "die+hard");

        responseMovies.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.getLocalizedMessage());

                    }

                    @Override
                    public void onNext(MovieInfo movieInfo) {

                        String movieTitle = movieInfo.getGenre();
                        System.out.println(movieTitle);

                    }
                });


    }
}
