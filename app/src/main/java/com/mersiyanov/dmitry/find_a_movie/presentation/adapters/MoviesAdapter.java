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

import java.util.Collections;
import java.util.Comparator;

import javax.annotation.Nullable;

import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Dmitry on 17.03.2018.
 */

public class
MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private RealmList<MovieEntity> movies = new RealmList<>();
    private OnFavoriteClickListener onFavoriteClickListener;

    public void setOnFavoriteClickListener(@Nullable OnFavoriteClickListener onFavoriteClickListener) {
        this.onFavoriteClickListener = onFavoriteClickListener;
    }

    public void addMovie(MovieEntity movie) {
        if(movies.contains(movie)) {
            movies.remove(movie);
            movies.add(0, movie);
        } else movies.add(0, movie);
        notifyDataSetChanged();

    }

    public void addMovies(RealmResults<MovieEntity> realmResults) {
        movies.addAll(realmResults);
        Collections.sort(movies, new Comparator<MovieEntity>() {
            @Override
            public int compare(MovieEntity o1, MovieEntity o2) {
                return o2.getDateTime().compareTo(o1.getDateTime());
            }
        });
        notifyDataSetChanged();
    }

    public void clearAdapter() {
        movies.clear();
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
        final MovieEntity movieEntity = movies.get(position);

        holder.movieTitle.setText(movieEntity.getTitle());
        holder.movieYear.setText(movieEntity.getYear());
        Picasso.get().load(movieEntity.getPoster()).resize(350, 350).centerInside().into(holder.moviePoster);

        if(movieEntity.getFavorite()) {
            holder.addToFavorites.setImageDrawable(holder.addToFavorites.getContext().getResources().getDrawable(R.drawable.delete_favorite));
        } else
            holder.addToFavorites.setImageDrawable(holder.addToFavorites.getContext().getResources().getDrawable(R.drawable.add_favorite));

        holder.addToFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onFavoriteClickListener != null) {
                    onFavoriteClickListener.onFavoriteClick(movieEntity);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
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

    public interface OnFavoriteClickListener {
        void onFavoriteClick(MovieEntity item);
    }
}
