package com.mersiyanov.dmitry.find_a_movie.POJO;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by Dmitry on 17.03.2018.
 */

public class MovieInfo extends RealmObject implements Parcelable {


    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public boolean getFavorite() {
        return isFavorite;
    }

    private boolean isFavorite;

    @Required
    @SerializedName("Title")
    @Expose
    private String title;

    @SerializedName("Error")
    @Expose
    private String error;

    @SerializedName("Year")
    @Expose
    private String year;
    @SerializedName("Rated")
    @Expose
    private String rated;
    @SerializedName("Released")
    @Expose
    private String released;
    @SerializedName("Runtime")
    @Expose
    private String runtime;
    @SerializedName("Genre")
    @Expose
    private String genre;
    @SerializedName("Director")
    @Expose
    private String director;
    @SerializedName("Writer")
    @Expose
    private String writer;
    @SerializedName("Actors")
    @Expose
    private String actors;
    @SerializedName("Plot")
    @Expose
    private String plot;
    @SerializedName("Language")
    @Expose
    private String language;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("Awards")
    @Expose
    private String awards;
    @SerializedName("Poster")
    @Expose
    private String poster;

//    @SerializedName("Ratings")
//    @Expose
//    private List<Rating> ratings = null;

    @SerializedName("Metascore")
    @Expose
    private String metascore;
    @SerializedName("imdbRating")
    @Expose
    private String imdbRating;
    @SerializedName("imdbVotes")
    @Expose
    private String imdbVotes;
    @SerializedName("imdbID")
    @Expose
    private String imdbID;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("DVD")
    @Expose
    private String dVD;
    @SerializedName("BoxOffice")
    @Expose
    private String boxOffice;
    @SerializedName("Production")
    @Expose
    private String production;
    @SerializedName("Website")
    @Expose
    private String website;
    @SerializedName("Response")
    @Expose
    private String response;

    public MovieInfo(Parcel in) {
        title = in.readString();
        error = in.readString();
        year = in.readString();
        rated = in.readString();
        released = in.readString();
        runtime = in.readString();
        genre = in.readString();
        director = in.readString();
        writer = in.readString();
        actors = in.readString();
        plot = in.readString();
        language = in.readString();
        country = in.readString();
        awards = in.readString();
        poster = in.readString();
        metascore = in.readString();
        imdbRating = in.readString();
        imdbVotes = in.readString();
        imdbID = in.readString();
        type = in.readString();
        dVD = in.readString();
        boxOffice = in.readString();
        production = in.readString();
        website = in.readString();
        response = in.readString();
    }

    public MovieInfo(){

    }

    public static final Creator<MovieInfo> CREATOR = new Creator<MovieInfo>() {
        @Override
        public MovieInfo createFromParcel(Parcel in) {
            return new MovieInfo(in);
        }

        @Override
        public MovieInfo[] newArray(int size) {
            return new MovieInfo[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

//    public List<Rating> getRatings() {
//        return ratings;
//    }
//
//    public void setRatings(List<Rating> ratings) {
//        this.ratings = ratings;
//    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDVD() {
        return dVD;
    }

    public void setDVD(String dVD) {
        this.dVD = dVD;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(error);
        dest.writeString(year);
        dest.writeString(rated);
        dest.writeString(released);
        dest.writeString(runtime);
        dest.writeString(genre);
        dest.writeString(director);
        dest.writeString(writer);
        dest.writeString(actors);
        dest.writeString(plot);
        dest.writeString(language);
        dest.writeString(country);
        dest.writeString(awards);
        dest.writeString(poster);
        dest.writeString(metascore);
        dest.writeString(imdbRating);
        dest.writeString(imdbVotes);
        dest.writeString(imdbID);
        dest.writeString(type);
        dest.writeString(dVD);
        dest.writeString(boxOffice);
        dest.writeString(production);
        dest.writeString(website);
        dest.writeString(response);
    }
}
