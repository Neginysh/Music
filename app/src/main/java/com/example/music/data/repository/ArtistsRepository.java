package com.example.music.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.music.data.api.LastfmApi;
import com.example.music.data.api.RetrofitService;
import com.example.music.data.model.artistsearch.ArtistSearch;
import com.example.music.data.model.artistsearch.Artist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


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
         api = RetrofitService.create(LastfmApi.class);
    }


    public LiveData<List<Artist>> getAllArtists(String artistName, String apiKey){

        Call<ArtistSearch> calls = api.getArtists(artistName, apiKey);

        final MutableLiveData<List<Artist>> allArtists = new MutableLiveData<>();

       /* calls.enqueue(new Callback<ArtistSearch>() {
            @Override
            public void onResponse(Call<ArtistSearch> call, Response<ArtistSearch> response) {
                allArtists.setValue(response.body().getResults().getArtistmatches().getArtist());

            }

            @Override
            public void onFailure(Call<ArtistSearch> call, Throwable t) {
                allArtists.setValue(null);

            }
        });
*/
     /*   Response<ArtistSearch> response= calls.execute();
        ArtistSearch artistSearch = response.body();*/



        return allArtists;

    }


}
