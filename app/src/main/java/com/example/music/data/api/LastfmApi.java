package com.example.music.data.api;

import androidx.lifecycle.LiveData;

import com.example.music.data.model.albumsinfo.Album;
import com.example.music.data.model.artistsearch.ArtistSearch;

import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LastfmApi {


 //   http://ws.audioscrobbler.com/2.0/?method=artist.search&artist=cher&api_key=YOUR_API_KEY&format=json
    @GET("?method=artist.search&format=json")
    Observable<Response<ArtistSearch>> getArtists(@Query("artist") String artistName,
                                                 @Query("api_key") String apiKey);

   // ?method=album.getinfo&api_key=YOUR_API_KEY&artist=Cher&album=Believe&format=json
    @GET("?method=album.getinfo&format=json")
    Call<List<Album>> getAlbums(@Query("api_key") String apiKey,
                                @Query("artist") String artistName,
                                @Query("album") String albumName);

}
