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

    FavoritesAdapter adapter = new FavoritesAdapter(this);
    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        Toolbar toolbar = findViewById(R.id.favorites_toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.favorites_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        mRealm = Realm.getDefaultInstance();
        mRealm.beginTransaction();
        RealmResults<MovieInfo> movieInfoRealmResults = mRealm.where(MovieInfo.class).equalTo("isFavorite", true).findAll();
        mRealm.commitTransaction();

        adapter.addFavoriteMovies(movieInfoRealmResults);

//        Toast.makeText(getApplicationContext(), movieInfo.getTitle(), Toast.LENGTH_LONG).show();




    }
}
