package com.example.music.ui.albums.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.music.R;
import com.example.music.data.requests.api.LastfmApi;
import com.example.music.data.requests.api.RetrofitService;
import com.example.music.data.model.topalbums.Album;
import com.example.music.data.model.topalbums.ArtistsTopAlbums;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.music.data.utils.Constants.API_KEY;

public class TopAlbumsActivity extends AppCompatActivity {


    LastfmApi api;
    Button test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_albums);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRetrofit();
            }
        });




    }


    public void testRetrofit(){
        api = RetrofitService.create(LastfmApi.class);
        Call<ArtistsTopAlbums> topAlbumsCall = api.getTopAlbums("cher", API_KEY);
        topAlbumsCall.enqueue(new Callback<ArtistsTopAlbums>() {
            @Override
            public void onResponse(Call<ArtistsTopAlbums> call, Response<ArtistsTopAlbums> response) {
                Log.d("TopAlbums", "onResponse: " + response.toString());
                List<Album> albums = new ArrayList<>(response.body().getTopalbums().getAlbum());
                for (Album album: albums){
                    Log.d("TopAlbums", "onResponse: " + album.getName());
                }
            }

            @Override
            public void onFailure(Call<ArtistsTopAlbums> call, Throwable t) {

            }
        });
    }
}
