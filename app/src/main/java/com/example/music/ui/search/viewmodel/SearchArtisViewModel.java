package com.example.music.ui.search.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.music.data.model.artistsearch.Artist;
import com.example.music.data.repository.MusicRepository;
import com.example.music.utils.Resource;

import java.util.List;

public class SearchArtisViewModel extends AndroidViewModel {

    private MediatorLiveData<Resource<List<Artist>>> artists = new MediatorLiveData<>();
    private MusicRepository repository;

    public SearchArtisViewModel(@NonNull Application application) {
        super(application);
        repository = MusicRepository.getInstance(application);
    }

    public LiveData<Resource<List<Artist>>> getArtists() {
        return artists;
    }


    public void getArtistsApi(String artistName) {
        final LiveData<Resource<List<Artist>>> repositorySource = repository.getArtists(artistName);

        artists.addSource(repositorySource, (listResource) -> {
            // react to the data
            artists.setValue(listResource);
        });

    }


}
