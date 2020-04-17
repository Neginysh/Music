package com.example.music.data.repository;


import androidx.lifecycle.LiveData;

import com.example.music.data.model.artistsearch.Artist;
import com.example.music.data.requests.ArtistsApiClient;


import java.util.List;


public class ArtistsRepository {

    private static ArtistsRepository instance;
    private ArtistsApiClient artistsApiClient;

    public static ArtistsRepository getInstance() {
        if (instance == null) {
            instance = new ArtistsRepository();
        }
        return instance;
    }

    private ArtistsRepository() {
        artistsApiClient = ArtistsApiClient.getInstance();
    }


    public LiveData<List<Artist>> getAllArtists() {
        return artistsApiClient.getAllArtists();

    }

    public void getArtistsApi(String artistName){
        artistsApiClient.getArtistsApi(artistName);
    }




}
