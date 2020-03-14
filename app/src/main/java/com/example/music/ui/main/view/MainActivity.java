package com.example.music.ui.main.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Toast;

import com.example.music.R;
import com.example.music.data.model.Album;
import com.example.music.ui.main.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel= ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getAllAlbums().observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(List<Album> albums) {
                Toast.makeText(MainActivity.this, "onChnaged", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
