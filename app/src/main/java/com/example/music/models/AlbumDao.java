package com.example.music.models;

import androidx.annotation.RequiresPermission;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AlbumDao {

    @Insert
    void insert(Album album);

    @Update
    void update(Album album);

    @Delete
    void delete(Album album);

    @TypeConverters({Converters.class})
    @Query("SELECT * FROM album_table")
    LiveData<List<Album>> getAlbums(); //livedata to observe changes


}
