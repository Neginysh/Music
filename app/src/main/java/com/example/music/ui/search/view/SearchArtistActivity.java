package com.example.music.ui.search.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.music.R;
import com.example.music.ui.search.adapter.ArtistsRecyclerAdapter;
import com.example.music.ui.search.adapter.OnArtistListerner;
import com.example.music.ui.search.viewmodel.SearchArtisViewModel;


public class SearchArtistActivity extends AppCompatActivity implements OnArtistListerner {

    private RecyclerView recyclerView;
    private ArtistsRecyclerAdapter adapter;
    private ProgressBar progressBar;
    private SearchArtisViewModel artisViewModel;
    private EditText et_artist;
    private Button search;
    String artistname = "Cher";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_artist);
        et_artist = findViewById(R.id.et_artist);
        search = findViewById(R.id.search_artist);
        recyclerView = findViewById(R.id.recycler_view_artists);
        artisViewModel = new ViewModelProvider(this).get(SearchArtisViewModel.class);

        setupRecyclerView();

        subscribeOberver();

        search.setOnClickListener((v) -> {
            artistname = et_artist.getText().toString();
            getArtistsApi(artistname);
        });


    }

    private void subscribeOberver() {
        artisViewModel.getArtists().observe(SearchArtistActivity.this, (artists) -> {
            if (artists != null)
                adapter.setArtistList(artists);
        });

    }


    private void getArtistsApi(String artistName) {
        artisViewModel.getArtistsApi(artistName);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ArtistsRecyclerAdapter(this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onArtistClick(int position) {
        //To do: show top albums

    }

    @Override
    public void onAlbumClick(int postition) {

    }
}
