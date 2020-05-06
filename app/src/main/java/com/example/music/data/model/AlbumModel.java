//package com.example.music.data.model;
//
//import androidx.annotation.NonNull;
//import androidx.room.ColumnInfo;
//import androidx.room.Entity;
//import androidx.room.PrimaryKey;
//import androidx.room.TypeConverters;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity(tableName = "album_info_table")
//public class AlbumModel {
//
//    @PrimaryKey
//    @NonNull
//    private String albumId;
//
//    @ColumnInfo(name = "name")
//    private String name;
//
//    @ColumnInfo(name = "artist")
//    private String artist;
//
//    @ColumnInfo(name = "imgUrl")
//    private String imgUrl;
//
//    @ColumnInfo(name = "trackNames")
//    private List<String> trackNames;
//
//    @ColumnInfo(name = "timestamp")
//    private int timestamp;
//
//    public AlbumModel(@NonNull String albumId, String name, String artist, String imgUrl, List<String> trackNames, int timestamp) {
//        this.albumId = albumId;
//        this.name = name;
//        this.artist = artist;
//        this.imgUrl = imgUrl;
//        this.trackNames = trackNames;
//        this.timestamp = timestamp;
//    }
//
//    @NonNull
//    public String getAlbumId() {
//        return albumId;
//    }
//
//    public void setAlbumId(@NonNull String albumId) {
//        this.albumId = albumId;
//    }
//
//    public String getArtistName() {
//        return name;
//    }
//
//    public void setArtistName(String name) {
//        this.name = name;
//    }
//
//    public String getArtist() {
//        return artist;
//    }
//
//    public void setArtist(String artist) {
//        this.artist = artist;
//    }
//
//    public String getImgUrl() {
//        return imgUrl;
//    }
//
//    public void setImgUrl(String imgUrl) {
//        this.imgUrl = imgUrl;
//    }
//
//    public List<String> getTrackNames() {
//        return trackNames;
//    }
//
//    public void setTrackNames(List<String> trackNames) {
//        this.trackNames = trackNames;
//    }
//
//    public int getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(int timestamp) {
//        this.timestamp = timestamp;
//    }
//}
