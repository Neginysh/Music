
package com.example.music.data.model.albumsinfo;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.TypeConverters;

import java.util.List;

import com.example.music.data.persistence.Converters;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tracks {

    //@Embedded
    @TypeConverters(Converters.class)
    @ColumnInfo(name = "trackNames")
    @SerializedName("track")
    @Expose
    private List<Track> track = null;

    public List<Track> getTrack() {
        return track;
    }

    public void setTrack(List<Track> track) {
        this.track = track;
    }

}
