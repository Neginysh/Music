
package com.example.music.data.model.albumsinfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlbumInfos {

    @SerializedName("album")
    @Expose
    private Album album;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

}
