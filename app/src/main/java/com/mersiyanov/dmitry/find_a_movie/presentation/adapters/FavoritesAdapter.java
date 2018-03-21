package com.mersiyanov.dmitry.find_a_movie.presentation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mersiyanov.dmitry.find_a_movie.R;
import com.mersiyanov.dmitry.find_a_movie.data.MovieInfo;
import com.squareup.picasso.Picasso;

import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Dmitry on 17.03.2018.
 */

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private Context context;
    private RealmList<MovieInfo> favoriteMovies = new RealmList<>();

    public FavoritesAdapter(Context context) {
        this.context = context;

    }

    public MovieInfo deleteFavorite(int position) {
        MovieInfo movieInfo = favoriteMovies.get(position);
        favoriteMovies.remove(position);
        notifyDataSetChanged();
        return movieInfo;
    }

    public void addFavoriteMovies(RealmResults<MovieInfo> realmResults) {
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
        final MovieInfo movieInfo = favoriteMovies.get(position);

        holder.movieTitle.setText(movieInfo.getTitle());
        holder.movieYear.setText(movieInfo.getYear());
        Picasso.get().load(movieInfo.getPoster()).resize(350, 350).centerInside().into(holder.moviePoster);
        holder.addToFavorites.setImageDrawable(context.getResources().getDrawable(R.drawable.delete_favorite));

        holder.addToFavorites.setTag(R.string.TAG_TITLE, movieInfo.getTitle());
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