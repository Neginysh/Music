package com.example.music.ui.albums.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.music.R;

import com.example.music.data.model.topalbums.Album;
import com.example.music.ui.adapters.AlbumsRecyclerAdapter;
import com.example.music.ui.adapters.ArtistsRecyclerAdapter;
import com.example.music.ui.adapters.OnArtistListerner;
import com.example.music.ui.albums.viewmodel.TopAlbumsViewModel;
import com.example.music.utils.VerticalItemDecorator;

import java.util.List;


public class TopAlbumsActivity extends AppCompatActivity implements OnArtistListerner {

    private RecyclerView recyclerView;
    private AlbumsRecyclerAdapter adapter;
    private TopAlbumsViewModel topAlbumsViewModel;
    private static final String TAG = "TopAlbumsActivity";
    private String artistName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_albums);
        recyclerView = findViewById(R.id.recycler_view_albums);
        topAlbumsViewModel = new ViewModelProvider(this).get(TopAlbumsViewModel.class);




        setupRecyclerView();
        subscribeOberver();
        initTopAlbums();


    }


    private void setupRecyclerView() {
        VerticalItemDecorator decorator = new VerticalItemDecorator(30);
        recyclerView.addItemDecoration(decorator);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        adapter = new AlbumsRecyclerAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void initTopAlbums() {
        Intent intent = getIntent();
        artistName = intent.getStringExtra("name");
        topAlbumsViewModel.getTopAlbumsApi(artistName);
    }


    private void subscribeOberver() {
        topAlbumsViewModel.getTopAlbums().observe(TopAlbumsActivity.this, (albums) -> {
            if (albums != null) {
                Log.d(TAG, "subscribeOberver: " + albums.toString());
                adapter.setTopAlbumsList(albums);
            }
        });

    }


    @Override
    public void onArtistClick(String artistName) {
        //nothing
    }

    @Override
    public void onAlbumClick(int postition) {
        // 1. on image: make favourite, 2. on layout: expand view with albums.getInfo api

    }


}
