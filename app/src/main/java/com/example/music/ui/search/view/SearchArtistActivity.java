package com.example.music.ui.search.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.music.R;
import com.example.music.ui.search.adapter.ArtistsRecyclerAdapter;
import com.example.music.ui.search.adapter.OnArtistListerner;
import com.example.music.ui.search.viewmodel.SearchArtisViewModel;


public class SearchArtistActivity extends AppCompatActivity implements OnArtistListerner {

    private RecyclerView recyclerView;
    private ArtistsRecyclerAdapter adapter;
    private SearchArtisViewModel artisViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_artist);
        recyclerView = findViewById(R.id.recycler_view_artists);
        artisViewModel = new ViewModelProvider(this).get(SearchArtisViewModel.class);

        setupRecyclerView();

        subscribeOberver();

        initSearchView();


    }

    private void subscribeOberver() {
        artisViewModel.getArtists().observe(SearchArtistActivity.this, (artists) -> {
            if (artists != null)
                adapter.setArtistList(artists);
        });

    }

    private void initSearchView() {
        final SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String artistName) {
                adapter.displayLoading();
                artisViewModel.getArtistsApi(artistName);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

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
