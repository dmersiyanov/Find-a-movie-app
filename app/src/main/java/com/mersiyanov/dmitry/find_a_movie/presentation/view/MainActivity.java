
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
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.mersiyanov.dmitry.find_a_movie.R;
import com.mersiyanov.dmitry.find_a_movie.data.DataManager;
import com.mersiyanov.dmitry.find_a_movie.data.MovieInfo;
import com.mersiyanov.dmitry.find_a_movie.presentation.adapters.MoviesAdapter;
import com.mersiyanov.dmitry.find_a_movie.presentation.presenter.MainPresenter;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.mersiyanov.dmitry.find_a_movie.data.network.RetrofitHelper.getApi;

public class MainActivity extends AppCompatActivity {

    private MainPresenter presenter;

    private final String API_KEY = "d160bbfc";
    SearchView searchView;
    MoviesAdapter moviesAdapter;
    private Realm mRealm;
    private ImageView addToFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DataManager dataManager = new DataManager();
        presenter = new MainPresenter(dataManager);

        mRealm = Realm.getDefaultInstance();
        moviesAdapter = new MoviesAdapter(this, mRealm);

        initUI();

        mRealm.beginTransaction();
        RealmResults<MovieInfo> movieInfoRealmResults = mRealm.where(MovieInfo.class).findAll();
        mRealm.commitTransaction();

        moviesAdapter.addMovies(movieInfoRealmResults);

        presenter.onAttach(this);


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

    private void initUI() {
        RecyclerView recyclerView = findViewById(R.id.movies_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(moviesAdapter);
        searchView = findViewById(R.id.search_bar);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        addToFavorites = findViewById(R.id.favorite_icon);

    }

    private void getMovieFromImdb(String title) {

        Observable <MovieInfo> responseMovies = getApi().getMovieInfo(API_KEY, title);
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

//        mRealm.beginTransaction();
//        mRealm.where(MovieInfo.class).equalTo("isFavorite", false).findAll().deleteAllFromRealm();
//        mRealm.commitTransaction();

        mRealm.close();
    }

    public void onFavoriteClick(View view) {

        String title = String.valueOf(view.getTag(R.string.TAG_TITLE));
        int position = (int) view.getTag(R.string.TAG_POSITION);
        boolean isFavorite = (boolean) view.getTag(R.string.TAG_FAVORITE);

        if(isFavorite) {
            presenter.setFavorite(false, moviesAdapter.getMovie(position));
//            addToFavorites.setImageDrawable(this.getResources().getDrawable(R.drawable.add_favorite));
//            addToFavorites.setImageResource(R.drawable.add_favorite);
            moviesAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Movie " + title + " deleted from favorites", Toast.LENGTH_LONG).show();

        } else {
            presenter.setFavorite(true, moviesAdapter.getMovie(position));

//            mRealm.beginTransaction();
//            mRealm.copyToRealm(moviesAdapter.getMovie(position));
//            mRealm.commitTransaction();
//            addToFavorites.setImageDrawable(this.getResources().getDrawable(R.drawable.delete_favorite));
//            addToFavorites.setImageResource(R.drawable.delete_favorite);
            moviesAdapter.notifyDataSetChanged();


            Toast.makeText(this, "Movie " + title + " added to favorites", Toast.LENGTH_LONG).show();
        }



    }

}
