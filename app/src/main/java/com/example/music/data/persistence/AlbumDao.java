package com.example.music.data.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.example.music.data.model.AlbumModel;
import com.example.music.data.model.ArtistModel;
import com.example.music.data.model.TopAlbumModel;
import com.example.music.data.model.topalbums.Album;

import java.util.List;

import retrofit2.http.GET;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface AlbumDao {

    @Insert(onConflict = IGNORE)
    long[] insertAlbumInfo(AlbumModel... album);

    @Insert(onConflict = REPLACE)
    void insertAlbumInfo(AlbumModel album);

    @Query("SELECT * FROM album_info_table WHERE albumId = :albumId")          //for the albumInfo
    LiveData<AlbumModel> getAlbumInfo(String albumId);

    @Query("UPDATE album_info_table SET name = :name, artist = :artist, imgUrl = :imgUrl, trackNames =:tracks " +
            "WHERE albumId = :id")
    void updateAlbums(String id, String name, String artist, String imgUrl, List<String> tracks);





    @Insert(onConflict = IGNORE)
    long[] insertTopAlbum(TopAlbumModel... album);

    @Insert(onConflict = REPLACE)
    void insertTopAlbum(TopAlbumModel album);

    @Query("SELECT * FROM top_album_table WHERE artistName = :artistName")          //for the topAlbum
    LiveData<List<TopAlbumModel>> getTopAlbums(String artistName);

    @Query("UPDATE top_album_table SET topAlbumNamee = :name, artistName = :artist " +          // do not change the favorite info
            "WHERE topAlbumId = :id")
    void updateTopAlbum(String id, String name, String artist);



//    @Query("INSERT INTO top_album_table (favorite) VALUES (:fav)") //!?
//    void insertToFav(int fav);

    @Query("SELECT * FROM top_album_table WHERE favorite = 1")          //for the fav ones
    LiveData<List<TopAlbumModel>> getFavoriteAlbums(boolean favorite);

    @Query("UPDATE top_album_table SET favorite = :fav " +
            "WHERE topAlbumId = :id")
    void updateTopAlbum(String id, boolean fav);




    @Insert(onConflict = IGNORE)
    long[] insertArtist(ArtistModel... artist);

    @Insert(onConflict = REPLACE)
    void insertArtist(ArtistModel artist);

    @Query("SELECT * FROM artist_table WHERE artistName LIKE '%' || :name || '%'")          //for the artist
    LiveData<List<ArtistModel>> getArtists(String name);

    @Query("UPDATE artist_table SET artistName = :name " +
            "WHERE artistId = :id")
    void updateAlbums(String id, String name);





//    @Update
//    void update(AlbumModel album);
//
//    @Delete
//    void delete(AlbumModel album);




}
