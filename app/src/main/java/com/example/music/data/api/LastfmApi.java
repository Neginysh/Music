package com.example.music.data.api;

import com.example.music.data.model.topalbums.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LastfmApi {

    @GET()
    Call<List<Album>> getAlbums();


}
