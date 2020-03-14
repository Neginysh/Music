package com.example.music.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.music.models.Album;
import com.example.music.repositories.AlbumRepository;

import java.util.List;

public class AlbumViewModel extends AndroidViewModel {
    private AlbumRepository repository;
    private LiveData<List<Album>> allAlbums;

    public AlbumViewModel(@NonNull Application application) {
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
