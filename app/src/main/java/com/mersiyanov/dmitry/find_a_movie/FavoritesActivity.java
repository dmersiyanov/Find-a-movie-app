package com.mersiyanov.dmitry.find_a_movie;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.mersiyanov.dmitry.find_a_movie.POJO.MovieInfo;

import io.realm.Realm;
import io.realm.RealmResults;

public class FavoritesActivity extends AppCompatActivity {

    MoviesAdapter adapter = new MoviesAdapter(this);
    private Realm mRealm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.favorites_toolbar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.favorites_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

//        Toast.makeText(getApplicationContext(), movieInfo.getTitle(), Toast.LENGTH_LONG).show();


        mRealm = Realm.getDefaultInstance();

        mRealm.beginTransaction();

        RealmResults<MovieInfo> movieInfoRealmResults = mRealm.where(MovieInfo.class).equalTo("isFavorite", true).findAll();

        mRealm.commitTransaction();

        adapter.addMovies(movieInfoRealmResults);

//        Toast.makeText(getApplicationContext(), movieInfo.getTitle(), Toast.LENGTH_LONG).show();




    }
}
