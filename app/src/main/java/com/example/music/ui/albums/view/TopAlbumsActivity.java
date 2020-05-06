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
import android.view.View;
import android.widget.ImageView;

import com.example.music.R;

import com.example.music.data.model.topalbums.Album;
import com.example.music.ui.adapters.AlbumsRecyclerAdapter;
import com.example.music.ui.adapters.OnAlbumListener;
import com.example.music.ui.albums.viewmodel.TopAlbumsViewModel;
import com.example.music.utils.VerticalItemDecorator;


public class TopAlbumsActivity extends AppCompatActivity implements OnAlbumListener {


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

        adapter = new AlbumsRecyclerAdapter(this, this);
        recyclerView.setAdapter(adapter);
    }


    private void subscribeOberver() {
        topAlbumsViewModel.getTopAlbums().observe(TopAlbumsActivity.this, (albums) -> {
            if (albums != null) {

                Log.d(TAG, "subscribeOberver status: " + albums.status.toString());
                if (albums.data != null) {
                    for (Album album : albums.data)
                        Log.d(TAG, "subscribeOberver: data: " + album.getTopAlbumName());

                    adapter.setTopAlbumsList(albums.data);
                }

            }
        });

    }

    private void initTopAlbums() {
        Intent intent = getIntent();
        artistName = intent.getStringExtra("name");
        topAlbumsViewModel.getTopAlbumsApi(artistName);
    }


    @Override
    public void onAlbumClick(int postition) {


        Album album = adapter.getSelectedAlbum(postition);
        String albumId = album.getTopAlbumId();
        adapter.displayLoading();

        subscribeAlbumInfoObserver();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.displayAlbumInfo();
        topAlbumsViewModel.getAlbumInfoApi(albumId);

    }

    private void subscribeAlbumInfoObserver() {

        topAlbumsViewModel.getAlbumInfo().observe(TopAlbumsActivity.this, (singleAlbum) -> {
            if (singleAlbum != null) {
                Log.d(TAG, "onAlbumClick: " + singleAlbum.status);
                if (singleAlbum.data != null) {
                    Log.d(TAG, "subscribeOberver: data: " + singleAlbum.data.getAlbumName());

                    adapter.setAlbumInfo(singleAlbum.data);
                }

            }
        });
    }


}
