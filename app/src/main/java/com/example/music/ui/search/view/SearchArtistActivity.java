package com.example.music.ui.search.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.music.R;
import com.example.music.data.model.topalbums.Artist;
import com.example.music.ui.search.viewmodel.SearchArtisViewModel;

import java.util.List;

public class SearchArtistActivity extends AppCompatActivity {

    SearchArtisViewModel artisViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_artist);

        artisViewModel = ViewModelProviders.of(this).get(SearchArtisViewModel.class);
        artisViewModel.init("", "", "json");
        artisViewModel.getAllArtists().observe(this, new Observer<List<Artist>>() {
            @Override
            public void onChanged(List<Artist> artists) {
                // to do :
                // adapter.notifyDataSetChanged;
            }
        });
    }
}
