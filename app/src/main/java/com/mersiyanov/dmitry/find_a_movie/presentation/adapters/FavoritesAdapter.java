package com.mersiyanov.dmitry.find_a_movie.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mersiyanov.dmitry.find_a_movie.R;
import com.mersiyanov.dmitry.find_a_movie.domain.MovieEntity;
import com.squareup.picasso.Picasso;

import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Dmitry on 17.03.2018.
 */

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private RealmList<MovieEntity> favoriteMovies = new RealmList<>();

    public MovieEntity deleteFavorite(int position) {
        MovieEntity movieEntity = favoriteMovies.get(position);
        favoriteMovies.remove(position);
        notifyDataSetChanged();
        return movieEntity;
    }

    public void addFavoriteMovies(RealmResults<MovieEntity> realmResults) {
        favoriteMovies.addAll(realmResults);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolder viewHolder = new ViewHolder(layoutInflater.inflate(R.layout.movie_item_view, parent, false));
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final MovieEntity movieEntity = favoriteMovies.get(position);

        holder.movieTitle.setText(movieEntity.getTitle());
        holder.movieYear.setText(movieEntity.getYear());
        Picasso.get().load(movieEntity.getPoster()).resize(350, 350).centerInside().into(holder.moviePoster);
        holder.addToFavorites.setImageDrawable(holder.addToFavorites.getContext().getResources().getDrawable(R.drawable.delete_favorite));

        holder.addToFavorites.setTag(R.string.TAG_TITLE, movieEntity.getTitle());
        holder.addToFavorites.setTag(R.string.TAG_POSITION, position);

    }

    @Override
    public int getItemCount() {
        return favoriteMovies.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView movieTitle;
        private final TextView movieYear;
        private final ImageView moviePoster;
        private final ImageView addToFavorites;

        public ViewHolder(View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movie_name);
            movieYear = itemView.findViewById(R.id.movie_year);
            moviePoster = itemView.findViewById(R.id.movie_poster);
            addToFavorites = itemView.findViewById(R.id.favorite_icon);

        }
    }
}
