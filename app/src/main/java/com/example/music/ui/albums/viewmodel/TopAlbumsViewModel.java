package com.example.music.ui.albums.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.music.data.model.topalbums.Album;
import com.example.music.data.repository.Repository;

import java.util.List;

public class TopAlbumsViewModel extends ViewModel {
    private Repository repository;

    public TopAlbumsViewModel() {
        repository = Repository.getInstance();
    }

    public LiveData<List<Album>> getTopAlbums(){
        return repository.getTopAlbums();
    }

    public void getTopAlbumsApi(String artistName){
        repository.getTopAlbumsApi(artistName);
    }


    public LiveData<com.example.music.data.model.albumsinfo.Album> getSingleAlbum(){
        return repository.getSingleAlbum();
    }

    public void getSingleAlbumById(String albumId){
        repository.getSingleAlbumById(albumId);
    }
}
