
package com.example.music.data.model.artistsearch;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "artist_table")
public class Artist {

    @SerializedName("artistId")
    @Expose
    private String artistId;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "artistName")
    @SerializedName("name")
    @Expose
    private String name;

    @ColumnInfo(name = "timestamp")
    private int timestamp;

    public Artist(String mbid, String name, int timestamp) {
        this.artistId = mbid;
        this.name = name;
        this.timestamp = timestamp;
    }

    public Artist() {
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }



}
