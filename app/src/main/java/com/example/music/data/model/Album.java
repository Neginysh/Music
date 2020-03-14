package com.example.music.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "album_table")
public class Album {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String artist;
    private String imgUrl;

    @TypeConverters(Converters.class)
    private List<String> trackNames = new ArrayList<>();

    public Album(String name, String artist, String imgUrl, List<String> trackNames) {
        this.name = name;
        this.artist = artist;
        this.imgUrl = imgUrl;
        this.trackNames.addAll(trackNames);
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public List<String> getTrackNames() {
        return trackNames;
    }
}
