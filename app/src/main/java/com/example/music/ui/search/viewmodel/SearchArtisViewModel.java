package com.example.music.ui.search.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.music.data.model.artistsearch.Artist;
import com.example.music.data.repository.Repository;

import java.util.List;

public class SearchArtisViewModel extends ViewModel {
    private Repository repository;

    public SearchArtisViewModel() {
        repository = Repository.getInstance();
    }

    public LiveData<List<Artist>> getArtists(){
        return repository.getAllArtists();
    }

    public void getArtistsApi(String artistName){
        repository.getArtistsApi(artistName);
    }


}
