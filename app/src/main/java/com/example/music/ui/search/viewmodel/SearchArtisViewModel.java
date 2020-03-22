package com.example.music.ui.search.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.music.data.model.artistsearch.Artist;
import com.example.music.data.repository.ArtistsRepository;

import java.util.List;

public class SearchArtisViewModel extends ViewModel {
    private ArtistsRepository repository;
    private LiveData<List<Artist>> allArtists;

    public void init(String artistName, String apiKey) {
        if (allArtists != null){
            return;
        }
        repository = ArtistsRepository.getInstance();
        allArtists = repository.getAllArtists(artistName, apiKey);
    }

    public LiveData<List<Artist>> getAllArtists(){
        return allArtists;
    }


}
