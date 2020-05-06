package com.example.music.data.persistence;

import androidx.room.TypeConverter;

import com.example.music.data.model.albumsinfo.Image;
import com.example.music.data.model.albumsinfo.Track;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Converters {
    @TypeConverter
    public static List<String> fromString(String value) {
        Type listType = new TypeToken<List<String>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromList(List<String> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }


    @TypeConverter
    public String fromImageList(List<Image> images) {
        if (images == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Image>>() {
        }.getType();
        String json = gson.toJson(images, type);
        return json;
    }

    @TypeConverter
    public List<Image> toImageList(String imageStr) {
        if (imageStr == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Image>>() {
        }.getType();
        List<Image> imageList = gson.fromJson(imageStr, type);
        return imageList;
    }

    @TypeConverter
    public String fromTrackList(List<Track> tracks) {
        if (tracks == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Track>>() {
        }.getType();
        String json = gson.toJson(tracks, type);
        return json;
    }

    @TypeConverter
    public List<Track> toTrackList(String trackStr) {
        if (trackStr == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Track>>() {
        }.getType();
        List<Track> trackList = gson.fromJson(trackStr, type);
        return trackList;
    }
}
