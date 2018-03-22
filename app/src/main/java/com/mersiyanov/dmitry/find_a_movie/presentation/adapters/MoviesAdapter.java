package com.mersiyanov.dmitry.find_a_movie.presentation.adapters;

import android.content.Context;
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

public class
MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private Context context;
    private RealmList<MovieEntity> movies = new RealmList<>();

    public MoviesAdapter(Context context) {
        this.context = context;
    }

    public void addMovie(MovieEntity movie) {

//        if(movies.contains(movie)) {
//            movies.remove(movie);
//            movies.add(0, movie);
//        } else movies.add(0, movie);

        movies.add(0, movie);
        notifyDataSetChanged();

    }

    public MovieEntity getMovie(int position) {
        return movies.get(position);
    }

    public void addMovies(RealmResults<MovieEntity> realmResults) {
        movies.addAll(realmResults);
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
            holder.addToFavorites.setImageDrawable(context.getResources().getDrawable(R.drawable.delete_favorite));
        } else
            holder.addToFavorites.setImageDrawable(context.getResources().getDrawable(R.drawable.add_favorite));

        holder.addToFavorites.setTag(R.string.TAG_POSITION, position);

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
}
