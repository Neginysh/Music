package com.example.music.ui.search.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.music.data.model.topalbums.Artist;
import com.example.music.data.repository.ArtistsRepository;

import java.util.List;

public class SearchArtisViewModel extends ViewModel {
    private ArtistsRepository repository;
    private LiveData<List<Artist>> allArtists;

    public SearchArtisViewModel() {
        repository = new ArtistsRepository();
        allArtists = repository.getAllArtists();
    }

    public LiveData<List<Artist>> getAllArtists(){
        return allArtists;
    }


}
