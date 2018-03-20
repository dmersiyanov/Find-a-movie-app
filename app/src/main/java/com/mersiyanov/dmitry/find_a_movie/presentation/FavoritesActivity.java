package com.mersiyanov.dmitry.find_a_movie.presentation;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.mersiyanov.dmitry.find_a_movie.FavoritesAdapter;
import com.mersiyanov.dmitry.find_a_movie.R;
import com.mersiyanov.dmitry.find_a_movie.data.DataManager;

public class FavoritesActivity extends AppCompatActivity {

    private FavoritesPresenter presenter;
    private FavoritesAdapter adapter;
    private RecyclerView recyclerView;
    private ImageView noFavsPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        DataManager dataManager = new DataManager();
        presenter = new FavoritesPresenter(dataManager);

        initUI();

        adapter.addFavoriteMovies(presenter.getFavoritesFromDB());

    }

    private void initRecyclerView() {
        adapter = new FavoritesAdapter(this);
        recyclerView = findViewById(R.id.favorites_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void initUI() {
        initToolbar();
        initRecyclerView();
        noFavsPic = findViewById(R.id.no_movies_img);
        presenter.onAttach(this);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.favorites_toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public void onFavoriteClick(View view) {
        String title = String.valueOf(view.getTag(R.string.TAG_TITLE));
        int position = (int) view.getTag(R.string.TAG_POSITION);
        presenter.setFavorite(false, adapter.deleteFavorite(position));
        Toast.makeText(this, "Movie " + title + " deleted from favorites", Toast.LENGTH_LONG).show();
        if(adapter.getItemCount() == 0) {
            showNoFavoritesPic();
        }
    }

    public void showNoFavoritesPic() {
        recyclerView.setVisibility(View.GONE);
        noFavsPic.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
