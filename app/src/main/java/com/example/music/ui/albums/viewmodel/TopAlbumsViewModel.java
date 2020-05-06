package com.example.music.ui.albums.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.music.data.model.topalbums.Album;
import com.example.music.data.repository.MusicRepository;
import com.example.music.utils.Resource;

import java.util.List;

public class TopAlbumsViewModel extends AndroidViewModel {

    private MediatorLiveData<Resource<List<Album>>> topAlbums = new MediatorLiveData<>();
    private MusicRepository repository;

    private MediatorLiveData<Resource<com.example.music.data.model.albumsinfo.Album>> albumInfo = new MediatorLiveData<>();

    public TopAlbumsViewModel(@NonNull Application application) {
        super(application);
        repository = MusicRepository.getInstance(application);

    }

    public LiveData<Resource<List<Album>>> getTopAlbums() {
        return topAlbums;
    }

    public void getTopAlbumsApi(String artistName) {
        final LiveData<Resource<List<Album>>> repositorySource = repository.getTopAlbums(artistName);
        topAlbums.addSource(repositorySource, (listResource) -> {
            topAlbums.setValue(listResource);

        });

    }

    public LiveData<Resource<com.example.music.data.model.albumsinfo.Album>> getAlbumInfo() {
        return albumInfo;
    }

    public void getAlbumInfoApi(String albumId) {
        final LiveData<Resource<com.example.music.data.model.albumsinfo.Album>> repositorySource = repository.getAlbumInfo(albumId);
        albumInfo.addSource(repositorySource, (listResource) -> {
            albumInfo.setValue(listResource);
        });

    }


}
