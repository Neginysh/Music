package com.example.music.ui.main.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.music.data.model.Album;
import com.example.music.data.repository.AlbumRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private AlbumRepository repository;
    private LiveData<List<Album>> allAlbums;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new AlbumRepository(application);
        allAlbums = repository.getAllAlbums();
    }

    public void deleteAlbum(Album album){
        repository.delete(album);
    }
    public void insertAlbum(Album album){
        repository.delete(album);
    }
    public void updateAlbum(Album album){
        repository.delete(album);
    }

    public LiveData<List<Album>> getAllAlbums(){
        return allAlbums;
    }
}
