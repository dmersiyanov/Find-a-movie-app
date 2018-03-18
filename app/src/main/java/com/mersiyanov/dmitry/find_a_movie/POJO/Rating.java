package com.mersiyanov.dmitry.find_a_movie.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Dmitry on 17.03.2018.
 */

public class Rating extends RealmObject {
    @SerializedName("Source")
    @Expose
    private String source;
    @SerializedName("Value")
    @Expose
    private String value;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
