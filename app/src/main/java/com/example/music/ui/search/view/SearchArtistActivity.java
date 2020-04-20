package com.example.music.ui.search.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;

import com.example.music.R;
import com.example.music.ui.adapters.ArtistsRecyclerAdapter;
import com.example.music.ui.adapters.OnArtistListerner;
import com.example.music.ui.albums.view.TopAlbumsActivity;
import com.example.music.ui.search.viewmodel.SearchArtisViewModel;
import com.example.music.utils.VerticalItemDecorator;


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

    private void setupRecyclerView() {
        adapter = new ArtistsRecyclerAdapter(this);
        VerticalItemDecorator decorator = new VerticalItemDecorator(30);
        recyclerView.addItemDecoration(decorator);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
             //   adapter.displayLoading();
                artisViewModel.getArtistsApi(artistName);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        subscribeOberver();
    }

    @Override
    public void onArtistClick(String artistName) {
        adapter.displayLoading();
        //adapter.displayTopAlbums();
        //To do: show top albums
        Intent i = new Intent(this, TopAlbumsActivity.class);
        i.putExtra("name", artistName);
        startActivity(i);
    }


}
