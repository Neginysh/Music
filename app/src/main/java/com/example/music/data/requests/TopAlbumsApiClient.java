package com.example.music.data.requests;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.music.data.model.albumsinfo.AlbumInfos;
import com.example.music.data.model.topalbums.Album;
import com.example.music.data.model.topalbums.ArtistsTopAlbums;
import com.example.music.data.requests.api.LastfmApi;
import com.example.music.data.requests.api.RetrofitService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

import static com.example.music.utils.Constants.API_KEY;
import static com.example.music.utils.Constants.NETWORK_TIMEOUT;

public class TopAlbumsApiClient {
    private static TopAlbumsApiClient topAlbumsApiClient;
    private MutableLiveData<List<Album>> topAlbums;
    private RetrieveTopAlbusRunnable retrieveTopAlbusRunnable;
    private static final String TAG = "TopAlbumsApiClient";

    private MutableLiveData<com.example.music.data.model.albumsinfo.Album> singleAlbum;
    private RetrieveSingleAlbusRunnable retrieveSingleAlbusRunnable;


    public static TopAlbumsApiClient getInstance() {
        if (topAlbumsApiClient == null)
            topAlbumsApiClient = new TopAlbumsApiClient();
        return topAlbumsApiClient;
    }

    private TopAlbumsApiClient() {
        topAlbums = new MutableLiveData<>();
        singleAlbum = new MutableLiveData<>();
    }


    public LiveData<List<Album>> getTopAlbums() {
        return topAlbums;

    }

    public void getTopAlbumsApi(String artistName) {

        if (retrieveTopAlbusRunnable != null) {
            retrieveTopAlbusRunnable = null;
        }
        retrieveTopAlbusRunnable = new RetrieveTopAlbusRunnable(artistName);

        final Future handler = AppExecutors.getInstance().getmNetworkIO().submit(retrieveTopAlbusRunnable);

        AppExecutors.getInstance().getmNetworkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //let the user know it's timed out
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);

    }


    public LiveData<com.example.music.data.model.albumsinfo.Album> getSingleAlbum(){
        return singleAlbum;
    }


    public void  getSingleAlbumById(String albumId){

        if (retrieveSingleAlbusRunnable != null) {
            retrieveSingleAlbusRunnable = null;
        }
        retrieveSingleAlbusRunnable = new RetrieveSingleAlbusRunnable(albumId);

        final Future handler = AppExecutors.getInstance().getmNetworkIO().submit(retrieveSingleAlbusRunnable);

        AppExecutors.getInstance().getmNetworkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //let the user know it's timed out
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);


    }



    private class RetrieveTopAlbusRunnable implements Runnable {
        private String artistName;
        private boolean cancelRequest;
        private LastfmApi api;

        public RetrieveTopAlbusRunnable(String artistName) {
            this.artistName = artistName;
            cancelRequest = false;
            api = RetrofitService.create(LastfmApi.class);
        }

        @Override
        public void run() {

            try {
                Response response = getTopAlbums(artistName).execute();
                if (cancelRequest) {
                    return;
                }

                if (response.code() == 200) {
                    List<Album> albumList = new ArrayList<>(((ArtistsTopAlbums) (response.body())).getTopalbums().getAlbum());
                    topAlbums.postValue(albumList);
                } else {
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: " + error);
                    topAlbums.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                topAlbums.postValue(null);

            }


        }


        private Call<ArtistsTopAlbums> getTopAlbums(String artistName) {
            return api.getTopAlbums(artistName, API_KEY);
        }

        private void cancelRequest() {
            Log.d(TAG, "cancelRequest: cancelling the search request");
            cancelRequest = true;
        }
    }





    private class RetrieveSingleAlbusRunnable implements Runnable {
        private String albumId;
        private boolean cancelRequest;
        private LastfmApi api;

        public RetrieveSingleAlbusRunnable(String albumId) {
            this.albumId = albumId;
            cancelRequest = false;
            api = RetrofitService.create(LastfmApi.class);
        }

        @Override
        public void run() {

            try {
                Response response = getSingleAlbumById(albumId).execute();
                if (cancelRequest) {
                    return;
                }

                if (response.code() == 200) {
                    com.example.music.data.model.albumsinfo.Album album = (((AlbumInfos) (response.body())).getAlbum());
                    singleAlbum.postValue(album);
                } else {
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: " + error);
                    topAlbums.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                topAlbums.postValue(null);

            }


        }


        private Call<AlbumInfos> getSingleAlbumById(String albumId) {
            return api.getAlbumInfo(API_KEY, albumId);
        }

        private void cancelRequest() {
            Log.d(TAG, "cancelRequest: cancelling the search request");
            cancelRequest = true;
        }
    }


}
