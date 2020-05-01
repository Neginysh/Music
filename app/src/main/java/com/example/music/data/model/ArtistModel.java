package com.example.music.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "artist_table")
public class ArtistModel {

    @PrimaryKey
    private String artistId;

    @ColumnInfo(name = "artistName")
    private String artistName;

    @ColumnInfo(name = "timestamp")
    private int timestamp;

    public ArtistModel(String artistId, String artistName, int timestamp) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.timestamp = timestamp;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
