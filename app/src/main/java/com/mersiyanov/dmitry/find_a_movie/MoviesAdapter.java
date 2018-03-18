package com.mersiyanov.dmitry.find_a_movie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mersiyanov.dmitry.find_a_movie.POJO.MovieInfo;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Dmitry on 17.03.2018.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private Context context;
    private RealmList<MovieInfo> movies = new RealmList<>();
    private Realm mRealm;


    public MoviesAdapter(Context context) {
        mRealm = Realm.getDefaultInstance();
        this.context = context;

    }

//test

    public void addMovie(MovieInfo movie) {
        movies.add(movie);
        notifyDataSetChanged();
    }

    public void addMovies(RealmResults<MovieInfo> realmResults) {
        movies.addAll(realmResults);
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolder viewHolder = new ViewHolder(layoutInflater.inflate(R.layout.movie_item_view, parent, false));
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final MovieInfo movieInfo = movies.get(position);
        holder.movieTitle.setText(movieInfo.getTitle());
        holder.movieYear.setText(movieInfo.getYear());
        Picasso.get().load(movieInfo.getPoster()).resize(300, 300).centerInside().into(holder.moviePoster);

        if(movieInfo.getFavorite()) {
            holder.addToFavorites.setImageDrawable(context.getResources().getDrawable(R.drawable.favorite_movie));
        }


        holder.addToFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movieInfo.setFavorite(true);

                mRealm.beginTransaction();
                mRealm.copyToRealm(movieInfo);
                mRealm.commitTransaction();


                Intent intent = new Intent(context, FavoritesActivity.class);
                context.startActivity(intent);

//                Toast.makeText(context, "Выбран элемент ==" + holder.getAdapterPosition(), Toast.LENGTH_LONG).show();
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

}
