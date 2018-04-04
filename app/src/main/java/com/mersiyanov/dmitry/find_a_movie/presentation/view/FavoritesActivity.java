package com.mersiyanov.dmitry.find_a_movie.presentation.view;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.mersiyanov.dmitry.find_a_movie.MovieApplication;
import com.mersiyanov.dmitry.find_a_movie.R;
import com.mersiyanov.dmitry.find_a_movie.domain.MovieEntity;
import com.mersiyanov.dmitry.find_a_movie.presentation.adapters.FavoritesAdapter;
import com.mersiyanov.dmitry.find_a_movie.presentation.presenter.FavoritesPresenter;

import javax.inject.Inject;

public class FavoritesActivity extends AppCompatActivity {

    @Inject
    FavoritesPresenter presenter;
    private FavoritesAdapter adapter;
    private RecyclerView recyclerView;
    private ImageView noFavsPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        MovieApplication.getComponent().injectsFavoritesPresenter(this);
        initUI();
        adapter.addFavoriteMovies(presenter.getFavoritesFromDB());

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.setOnFavoriteClickListener(onFavoriteClickListener);
    }

    @Override
    protected void onStop() {
        adapter.setOnFavoriteClickListener(null);
        super.onStop();
    }

    private void initUI() {
        initToolbar();
        initRecyclerView();
        noFavsPic = findViewById(R.id.no_movies_img);
        presenter.onAttach(this);
    }

    private void initRecyclerView() {
        adapter = new FavoritesAdapter();
        recyclerView = findViewById(R.id.favorites_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.favorites_toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }


    public void showNoFavoritesPic() {
        recyclerView.setVisibility(View.GONE);
        noFavsPic.setVisibility(View.VISIBLE);
    }


    private final FavoritesAdapter.OnFavoriteClickListener onFavoriteClickListener = new FavoritesAdapter.OnFavoriteClickListener() {
        @Override
        public void onFavoriteClick(MovieEntity item, int position) {
            presenter.setFavorite(false, adapter.deleteFavorite(position));
            Toast.makeText(getApplicationContext(), "Movie " + item.getTitle() + " deleted from favorites", Toast.LENGTH_LONG).show();
            if(adapter.getItemCount() == 0) {
                showNoFavoritesPic();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
