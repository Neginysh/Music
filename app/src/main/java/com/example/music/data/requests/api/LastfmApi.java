package com.example.music.data.requests.api;

import com.example.music.data.model.albumsinfo.Album;
import com.example.music.data.model.albumsinfo.AlbumInfos;
import com.example.music.data.model.artistsearch.ArtistSearch;
import com.example.music.data.model.topalbums.ArtistsTopAlbums;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LastfmApi {


    //   http://ws.audioscrobbler.com/2.0/?method=artist.search&artist=cher&api_key=YOUR_API_KEY&format=json
    @GET("?method=artist.search&format=json")
    Call<ArtistSearch> getArtists(@Query("artist") String artistName,
                                  @Query("api_key") String apiKey);

    // ?method=album.getinfo&api_key=YOUR_API_KEY&artist=Cher&album=Believe&format=json
    //?method=album.getinfo&api_key=7da8db4082844f11d8d41b516c75ffa3&mbid=e4e7db8a-2622-47d3-80a5-91534d523ac5&format=json
    @GET("?method=album.getinfo&format=json")
    Call<AlbumInfos> getAlbumInfo(@Query("api_key") String apiKey,
                               @Query("mbid") String albumId);

    //?method=artist.gettopalbums&artist=cher&api_key=YOUR_API_KEY&format=json
    @GET("?method=artist.gettopalbums&format=json")
    Call<ArtistsTopAlbums> getTopAlbums(@Query("artist") String artistName,
                                        @Query("api_key") String apiKey);

}
