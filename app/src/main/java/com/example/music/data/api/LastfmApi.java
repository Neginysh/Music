package com.example.music.data.api;

import androidx.lifecycle.LiveData;

import com.example.music.data.model.topalbums.Album;
import com.example.music.data.model.topalbums.Artist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LastfmApi {

    @GET()
    Call<List<Album>> getAlbums();

    @GET()
    Call<List<Artist>> getArtists();


}
