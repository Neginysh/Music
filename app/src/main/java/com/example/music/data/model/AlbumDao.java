package com.example.music.data.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.example.music.data.model.topalbums.Album;

import java.util.List;

@Dao
public interface AlbumDao {

    @Insert
    void insert(AlbumModel album);

    @Update
    void update(AlbumModel album);

    @Delete
    void delete(AlbumModel album);

    @TypeConverters({Converters.class})
    @Query("SELECT * FROM album_table")
    LiveData<List<AlbumModel>> getAlbums(); //livedata to observe changes


}
