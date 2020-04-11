package com.example.music.ui.search.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.music.data.model.artistsearch.Artist;
import com.example.music.data.model.artistsearch.ArtistSearch;
import com.example.music.data.repository.ArtistsRepository;

import java.util.List;

import retrofit2.Call;

public class SearchArtisViewModel extends ViewModel {
    private ArtistsRepository repository;

    public SearchArtisViewModel() {
        repository = ArtistsRepository.getInstance();
    }

    public LiveData<List<Artist>> getArtists(){
        return repository.getAllArtists();
    }

    public void getArtistsApi(String artistName){
        repository.getArtistsApi(artistName);
    }


}
