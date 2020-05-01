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

import retrofit2.http.GET;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface AlbumDao {

    @Insert(onConflict = IGNORE)
    long[] insertAlbum(AlbumModel... album);

    @Insert(onConflict = REPLACE)
    void insertAlbum(AlbumModel album);


    @Query("UPDATE album_table SET name = :name, artist = :artist, imgUrl = :imgUrl, trackNames =:tracks " +
            "WHERE albumId = :id")
    void updateAlbums(int id, String name, String artist, String imgUrl, List<String> tracks);

    @Query("SELECT * FROM album_table WHERE favorite = 1")
    LiveData<List<AlbumModel>> getFavoriteAlbums(boolean favorite);





//    //  @TypeConverters({Converters.class})
//    @Query("SELECT * FROM album_table")
//    LiveData<List<AlbumModel>> getAlbums();
//





//    @Update
//    void update(AlbumModel album);
//
//    @Delete
//    void delete(AlbumModel album);




}
