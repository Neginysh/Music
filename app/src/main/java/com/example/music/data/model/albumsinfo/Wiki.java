
package com.example.music.data.model.albumsinfo;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wiki {

    @ColumnInfo(name = "releasedDate")
    @SerializedName("published")
    @Expose
    private String releasedDate;

    @Ignore
    @SerializedName("summary")
    @Expose
    private String summary;
    @Ignore
    @SerializedName("content")
    @Expose
    private String content;

    public String getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(String published) {
        this.releasedDate = published;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
