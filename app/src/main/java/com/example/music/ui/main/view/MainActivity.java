package com.example.music.ui.main.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.music.R;
import com.example.music.ui.main.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        viewModel= new ViewModelProvider(this).get(MainViewModel.class);
//        viewModel.getAllAlbums().observe(this, new Observer<List<AlbumModel>>() {
//            @Override
//            public void onChanged(List<AlbumModel> albums) {
//            }
//        });



    }


}
