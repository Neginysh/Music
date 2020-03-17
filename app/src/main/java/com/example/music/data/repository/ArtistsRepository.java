package com.example.music.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.music.data.api.LastfmApi;
import com.example.music.data.api.RetrofitService;
import com.example.music.data.model.topalbums.Artist;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ArtistsRepository {

   // private LiveData<List<Artist>> allArtists;
    private static ArtistsRepository instance;
    private LastfmApi api;


    public static ArtistsRepository getInstance() {
        if (instance == null) {
            instance = new ArtistsRepository();
        }
        return instance;
    }

    public ArtistsRepository() {
        api = RetrofitService.cteateService(LastfmApi.class);

    }


    public LiveData<List<Artist>> getAllArtists(String artistName, String apiKey) {

        Call<List<Artist>> calls = api.getArtists(artistName, apiKey);
        final MutableLiveData<List<Artist>> allArtists = new MutableLiveData<>();

        calls.enqueue(new Callback<List<Artist>>() {
            @Override
            public void onResponse(Call<List<Artist>> call, Response<List<Artist>> response) {

                allArtists.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Artist>> call, Throwable t) {

                allArtists.setValue(null);
            }
        });
        return allArtists;

    }



}
