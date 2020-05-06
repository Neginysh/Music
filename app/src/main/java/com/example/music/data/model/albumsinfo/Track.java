
package com.example.music.data.model.albumsinfo;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Track {

    @ColumnInfo(name = "trackName")
    @SerializedName("name")
    @Expose
    private String name;

    @Ignore
    @SerializedName("url")
    @Expose
    private String url;
    @Ignore
    @SerializedName("duration")
    @Expose
    private String duration;
    @Ignore
    @SerializedName("streamable")
    @Expose
    private Streamable streamable;
    @Ignore
    @SerializedName("artist")
    @Expose
    private Artist artist;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Streamable getStreamable() {
        return streamable;
    }

    public void setStreamable(Streamable streamable) {
        this.streamable = streamable;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

}
