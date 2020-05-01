package com.example.music.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "top_album_table")
public class TopAlbumModel {

    @SerializedName("mbid")
    @Expose
    @PrimaryKey
    private String topAlbumId;

    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "topAlbumNamee")
    private String topAlbumName;

    @ColumnInfo(name = "artistName")
    private String artistName;

    @ColumnInfo(name = "favorite")
    private boolean favorite;

    @ColumnInfo(name = "timestamp")
    private int timestamp;

    public TopAlbumModel(String topAlbumId, String topAlbumName, String artistName, boolean favorite, int timestamp) {
        this.topAlbumId = topAlbumId;
        this.topAlbumName = topAlbumName;
        this.artistName = artistName;
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

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
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
