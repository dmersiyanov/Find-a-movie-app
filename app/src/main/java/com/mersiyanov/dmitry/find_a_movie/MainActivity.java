
package com.mersiyanov.dmitry.find_a_movie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.mersiyanov.dmitry.find_a_movie.POJO.MovieInfo;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final String API_KEY = "d160bbfc";
    SearchView searchView;
    MoviesAdapter moviesAdapter;
    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.movies_rv);

        mRealm = Realm.getDefaultInstance();
        moviesAdapter = new MoviesAdapter(this, mRealm);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(moviesAdapter);

        searchView = findViewById(R.id.search_bar);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        mRealm.beginTransaction();
        RealmResults<MovieInfo> movieInfoRealmResults = mRealm.where(MovieInfo.class).findAll();
        mRealm.commitTransaction();

        moviesAdapter.addMovies(movieInfoRealmResults);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               getMovieFromImdb(query);
               return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });



    }

    private void getMovieFromImdb(String title) {

        Observable <MovieInfo> responseMovies = MovieApp.getApi().getMovieInfo(API_KEY, title);
        responseMovies.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onNext(MovieInfo movieInfo) {
                        if(movieInfo.getResponse().equals("False"))
                            Toast.makeText(getApplicationContext(), movieInfo.getError(), Toast.LENGTH_LONG).show();
                        else  {
                            moviesAdapter.addMovie(movieInfo);

                            mRealm.beginTransaction();
                            mRealm.copyToRealm(movieInfo);
                            mRealm.commitTransaction();
                        }
                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                Intent intent = new Intent(this, FavoritesActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}
