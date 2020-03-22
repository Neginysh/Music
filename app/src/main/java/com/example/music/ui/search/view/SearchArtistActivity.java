package com.example.music.ui.search.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.music.R;
import com.example.music.data.model.artistsearch.Artist;
import com.example.music.ui.search.adapter.ArtistsRecyclerAdapter;
import com.example.music.ui.search.viewmodel.SearchArtisViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchArtistActivity extends AppCompatActivity implements ArtistsRecyclerAdapter.ItemClickListener {

    private RecyclerView recyclerView;
    private ArtistsRecyclerAdapter adapter;
    private ProgressBar progressBar;
    private SearchArtisViewModel artisViewModel;
    private EditText et_artist;
    private Button search;
    String artistname = "Cher";
    private List<Artist> artistList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_artist);
        et_artist = findViewById(R.id.et_artist);
        search = findViewById(R.id.search_artist);


        initViewModel();

    }

    private void initViewModel() {
        artistList= new ArrayList<>();
        artisViewModel = ViewModelProviders.of(this).get(SearchArtisViewModel.class);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                artistname=et_artist.getText().toString();
                artisViewModel.init(artistname, "7da8db4082844f11d8d41b516c75ffa3");
                artistList = artisViewModel.getAllArtists().getValue();
                setupRecyclerView();

                artisViewModel.getAllArtists().observe(SearchArtistActivity.this, new Observer<List<Artist>>() {
                    @Override
                    public void onChanged(List<Artist> artists) {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });




    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view_artists);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ArtistsRecyclerAdapter(this, artistList);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {

        //To do: show top albums
    }
}
