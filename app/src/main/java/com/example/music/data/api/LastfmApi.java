package com.example.music.data.api;

import androidx.lifecycle.LiveData;

import com.example.music.data.model.albumsinfo.Album;
import com.example.music.data.model.topalbums.Artist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LastfmApi {

   // "?method=artist.gettopalbums&artist=cher&api_key=YOUR_API_KEY&format=json"
    @GET("?method=artist.gettopalbums")
    Call<List<Artist>> getArtists(@Query("artist") String artistName,
                                    @Query("api_key") String apiKey,
                                  @Query("format") String format);

   // ?method=album.getinfo&api_key=YOUR_API_KEY&artist=Cher&album=Believe&format=json
    @GET("?method=album.getinfo")
    Call<List<Album>> getAlbums(@Query("api_key") String apiKey,
                                @Query("artist") String artistName,
                                @Query("album") String albumName,
                                @Query("format") String format);

}
