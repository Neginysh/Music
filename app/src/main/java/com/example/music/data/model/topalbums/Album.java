
package com.example.music.data.model.topalbums;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "top_album_table")
public class Album {

    @PrimaryKey
    @NonNull
    @SerializedName("mbid")
    @Expose
    private String topAlbumId;

    @ColumnInfo(name = "topAlbumName")
    @SerializedName("name")
    @Expose
    private String topAlbumName;


    @SerializedName("artist")
    @Expose
    @Embedded
    private Artist artist;

    @ColumnInfo(name = "favorite")
    private boolean favorite;

    @ColumnInfo(name = "timestamp")
    private int timestamp;

    public Album() {
    }

    public Album(String topAlbumId, String topAlbumName, Artist artist, boolean favorite, int timestamp) {
        this.topAlbumId = topAlbumId;
        this.topAlbumName = topAlbumName;
        this.artist = artist;
        this.favorite = favorite;
        this.timestamp = timestamp;
    }

    public String getTopAlbumId() {
        return topAlbumId;
    }

    public void setTopAlbumId(String topAlbumId) {
        this.topAlbumId = topAlbumId;
    }

    public String getTopAlbumName() {
        return topAlbumName;
    }

    public void setTopAlbumName(String topAlbumName) {
        this.topAlbumName = topAlbumName;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
