package com.example.music.data.requests;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.music.data.model.artistsearch.Artist;
import com.example.music.data.model.artistsearch.ArtistSearch;
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

public class ArtistsApiClient {
    private static ArtistsApiClient artistsApiClient;
    private MutableLiveData<List<Artist>> allArtists;
    private RetrieveArtistsRunnable retrieveArtistsRunnable;
    private static final String TAG = "ArtistsApiClient";

    public static ArtistsApiClient getInstance() {
        if (artistsApiClient == null)
            artistsApiClient = new ArtistsApiClient();
        return artistsApiClient;
    }

    private ArtistsApiClient() {
        allArtists = new MutableLiveData<>();
        // api = RetrofitService.create(LastfmApi.class);

    }

    public LiveData<List<Artist>> getAllArtists() {
        return allArtists;

    }

    public void getArtistsApi(String artistName) {

        if (retrieveArtistsRunnable != null) {
            retrieveArtistsRunnable = null;
        }
        retrieveArtistsRunnable = new RetrieveArtistsRunnable(artistName);

        final Future handler = AppExecutors.getInstance().getmNetworkIO().submit(retrieveArtistsRunnable);

        AppExecutors.getInstance().getmNetworkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //let the user know it's timed out
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);

    }


    private class RetrieveArtistsRunnable implements Runnable {
        private String artistName;
        private boolean cancelRequest;
        private LastfmApi api;

        public RetrieveArtistsRunnable(String artistName) {
            this.artistName = artistName;
            cancelRequest = false;
            api = RetrofitService.create(LastfmApi.class);
        }

        @Override
        public void run() {

            try {
                Response response = getArtists(artistName).execute();
                if (cancelRequest) {
                    return;
                }

                if (response.code() == 200) {
                    List<Artist> artists = new ArrayList<>(((ArtistSearch) (response.body())).getResults().getArtistmatches().getArtist());
                    allArtists.postValue(artists);
                } else {
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: " + error);
                    allArtists.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                allArtists.postValue(null);

            }


        }


        private Call<ArtistSearch> getArtists(String artistName) {
            return api.getArtists(artistName, API_KEY);
        }

        private void cancelRequest() {
            Log.d(TAG, "cancelRequest: cancelling the search request");
            cancelRequest = true;
        }
    }

}
