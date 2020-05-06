
package com.example.music.data.model.topalbums;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Artist {

    @ColumnInfo(name = "artistName")
    @SerializedName("name")
    @Expose
    private String artistName;

    @Ignore
    @SerializedName("mbid")
    @Expose
    private String mbid;

    @Ignore
    @SerializedName("url")
    @Expose
    private String url;

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String name) {
        this.artistName = name;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
