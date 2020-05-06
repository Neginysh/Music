package com.example.music.data.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.example.music.data.model.albumsinfo.Image;
import com.example.music.data.model.albumsinfo.Track;
import com.example.music.data.model.artistsearch.Artist;
import com.example.music.data.model.topalbums.Album;

import java.util.List;

import retrofit2.http.GET;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface AlbumDao {

    // albumInfo

    @Insert(onConflict = REPLACE)
    void insertAlbumInfo(com.example.music.data.model.albumsinfo.Album album);

    @Query("SELECT * FROM album_info_table WHERE albumId = :albumId")
    LiveData<com.example.music.data.model.albumsinfo.Album> getAlbumInfo(String albumId);

    @Query("UPDATE album_info_table " +
            "SET albumName = :name, abumArtist = :artist, image = :imgUrl, trackNames = :tracks, releasedDate = :releasedDate " +
            "WHERE albumId = :id")
    void updateAlbums(String id, String name, String artist, List<Image> imgUrl, List<Track> tracks, String releasedDate);



    // topAlbums

    @Insert(onConflict = IGNORE)
    long[] insertTopAlbum(Album... album);

    @Insert(onConflict = REPLACE)
    void insertTopAlbum(Album album);

    @Query("SELECT * FROM top_album_table WHERE artistName = :artistName")
    LiveData<List<Album>> getTopAlbums(String artistName);

    @Query("UPDATE top_album_table SET topAlbumName = :name, artistName = :artist " +          // do not change the favorite info
            "WHERE topAlbumId = :id")
    void updateTopAlbum(String id, String name, String artist);

//    @Query("INSERT INTO top_album_table (favorite) VALUES (:fav)") //!?
//    void insertToFav(int fav);

//    @Query("SELECT * FROM top_album_table WHERE favorite = 1")
//        //for the fav ones
//    LiveData<List<Album>> getFavoriteAlbums(boolean favorite);
//
//    @Query("UPDATE top_album_table SET favorite = :fav " +                  // change the favorite info
//            "WHERE topAlbumId = :id")
//    void updateTopAlbum(String id, boolean fav);



    // artist

    @Insert(onConflict = REPLACE)
    void insertArtist(Artist... artist);

    @Query("SELECT * FROM artist_table WHERE artistName LIKE '%' || :name || '%'")
    LiveData<List<Artist>> getArtists(String name);

    @Query("UPDATE artist_table SET artistName = :name " +
            "WHERE artistId = :id")
    void updateArtists(String id, String name);


//    @Delete
//    void delete(AlbumModel album);


}
