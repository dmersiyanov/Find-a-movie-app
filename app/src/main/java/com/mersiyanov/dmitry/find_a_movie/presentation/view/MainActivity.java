
package com.mersiyanov.dmitry.find_a_movie.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.mersiyanov.dmitry.find_a_movie.MovieApplication;
import com.mersiyanov.dmitry.find_a_movie.R;
import com.mersiyanov.dmitry.find_a_movie.domain.MovieEntity;
import com.mersiyanov.dmitry.find_a_movie.presentation.adapters.MoviesAdapter;
import com.mersiyanov.dmitry.find_a_movie.presentation.presenter.MainPresenter;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {

    @Inject
    MainPresenter presenter;
    private SearchView searchView;
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieApplication.getComponent().injectsMainPresenter(this);
        moviesAdapter = new MoviesAdapter();

        initUI();

        presenter.onAttach(this);
        moviesAdapter.addMovies(presenter.getAllMoviesFromDB());

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               presenter.getMovieFromImdb(query);
               return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void initUI() {
        RecyclerView recyclerView = findViewById(R.id.movies_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(moviesAdapter);
        searchView = findViewById(R.id.search_bar);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
            case R.id.action_delete_search:
                presenter.deleteFromDb("isFavorite", false);
                moviesAdapter.clearAdapter();
                moviesAdapter.addMovies(presenter.getAllMoviesFromDB());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
//
//        mRealm.beginTransaction();
//        mRealm.where(MovieEntity.class).equalTo("isFavorite", false).findAll().deleteAllFromRealm();
//        mRealm.commitTransaction();

    }

    public void onFavoriteClick(View view) {
        System.out.println("click");

        int position = (int) view.getTag(R.string.TAG_POSITION);
        MovieEntity movieEntity = moviesAdapter.getMovie(position);
        String title = movieEntity.getTitle();
        boolean isFavorite = movieEntity.getFavorite();

        if(isFavorite) {
            presenter.setFavorite(false, movieEntity);
            moviesAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Movie " + title + " deleted from favorites", Toast.LENGTH_LONG).show();

        } else {
            presenter.setFavorite(true, movieEntity);
            moviesAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Movie " + title + " added to favorites", Toast.LENGTH_LONG).show();
        }
    }

    public MoviesAdapter getMoviesAdapter() {
        return moviesAdapter;
    }

}
