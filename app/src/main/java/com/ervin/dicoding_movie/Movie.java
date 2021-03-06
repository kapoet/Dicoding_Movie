package com.ervin.dicoding_movie;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ervin on 12/2/2017.
 */

public class Movie implements Parcelable{
    private String title, release, synopsis, path_image;

    public Movie(String title, String release, String synopsis, String path_image) {
        this.title = title;
        this.release = release;
        this.synopsis = synopsis;
        this.path_image = path_image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getPath_image() {
        return path_image;
    }

    public void setPath_image(String path_image) {
        this.path_image = path_image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.release);
        dest.writeString(this.synopsis);
        dest.writeString(this.path_image);
    }

    protected Movie(Parcel in) {
        this.title = in.readString();
        this.release = in.readString();
        this.synopsis = in.readString();
        this.path_image = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
