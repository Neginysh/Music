package com.example.music.ui.main.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.music.data.model.AlbumModel;
import com.example.music.data.repository.AlbumRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private AlbumRepository repository;
    private LiveData<List<AlbumModel>> allAlbums;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new AlbumRepository(application);
        allAlbums = repository.getAllAlbums();
    }

    public void deleteAlbum(AlbumModel album){
        repository.delete(album);
    }
    public void insertAlbum(AlbumModel album){
        repository.delete(album);
    }
    public void updateAlbum(AlbumModel album){
        repository.delete(album);
    }

    public LiveData<List<AlbumModel>> getAllAlbums(){
        return allAlbums;
    }
}
