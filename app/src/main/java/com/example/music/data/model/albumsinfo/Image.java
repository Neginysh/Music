
package com.example.music.data.model.albumsinfo;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @ColumnInfo(name = "imageUrl")
    @SerializedName("#text")
    @Expose
    private String imageUrl;

    @Ignore
    @SerializedName("size")
    @Expose
    private String size;

    public String getText() {
        return imageUrl;
    }

    public void setText(String text) {
        this.imageUrl = text;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
