//package com.example.music.data.repository;
//
//
//import androidx.lifecycle.LiveData;
//
//import com.example.music.data.model.artistsearch.Artist;
//import com.example.music.data.model.topalbums.Album;
//import com.example.music.data.requests.ArtistsApiClient;
//import com.example.music.data.requests.TopAlbumsApiClient;
//
//
//import java.util.List;
//
//
//public class Repository {
//
//    private static Repository instance;
//    private ArtistsApiClient artistsApiClient;
//    private TopAlbumsApiClient topAlbumsApiClient;
//
//    public static Repository getInstance() {
//        if (instance == null) {
//            instance = new Repository();
//        }
//        return instance;
//    }
//
//    private Repository() {
//        artistsApiClient = ArtistsApiClient.getInstance();
//        topAlbumsApiClient = TopAlbumsApiClient.getInstance();
//    }
//
//
//    public LiveData<List<Artist>> getAllArtists() {
//        return artistsApiClient.getAllArtists();
//
//    }
//    public void getArtistsApi(String artistName) {
//        artistsApiClient.getArtistsApi(artistName);
//    }
//
//
//
//    public LiveData<List<Album>> getTopAlbums() {
//        return topAlbumsApiClient.getTopAlbums();
//
//    }
//    public void getTopAlbumsApi(String artistName) {
//        topAlbumsApiClient.getTopAlbumsApi(artistName);
//    }
//
//
//    public LiveData<com.example.music.data.model.albumsinfo.Album> getSingleAlbum() {
//        return topAlbumsApiClient.getSingleAlbum();
//
//    }
//    public void getSingleAlbumById(String albumId) {
//        topAlbumsApiClient.getSingleAlbumById(albumId);
//    }
//
//
//
//}
