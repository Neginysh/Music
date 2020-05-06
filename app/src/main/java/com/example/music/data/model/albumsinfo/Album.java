
package com.example.music.data.model.albumsinfo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

import com.example.music.data.persistence.Converters;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "album_info_table")
public class Album {

    @PrimaryKey
    @SerializedName("mbid")
    @Expose
    @NonNull
    private String albumId;

    @ColumnInfo(name = "albumName")
    @SerializedName("name")
    @Expose
    private String albumName;

    @ColumnInfo(name = "abumArtist")
    @SerializedName("artist")
    @Expose
    private String albumArtist;

    @SerializedName("image")
    @Expose
    private List<Image> image;

    @Embedded
    @SerializedName("tracks")
    @Expose
    private Tracks tracks;

    @Embedded
    @SerializedName("wiki")
    @Expose
    private Wiki wiki;

    @ColumnInfo(name = "timestamp")
    private int timestamp;


    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

    public Wiki getWiki() {
        return wiki;
    }

    public void setWiki(Wiki wiki) {
        this.wiki = wiki;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public Album(String albumId, String albumName, String albumArtist, List<Image> image, Tracks tracks, Wiki wiki, int timestamp) {
        this.albumId = albumId;
        this.albumName = albumName;
        this.albumArtist = albumArtist;
        this.image = image;
        this.tracks = tracks;
        this.wiki = wiki;
        this.timestamp = timestamp;
    }
}
