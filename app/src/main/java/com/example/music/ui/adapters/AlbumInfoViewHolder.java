package com.example.music.ui.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.R;

public class AlbumInfoViewHolder extends RecyclerView.ViewHolder {
    public ImageView albumImage;
    public TextView albumTitle;
    public TextView albumArtist;
    public TextView albumTracks;
    public TextView albumReleasedDate;


    public AlbumInfoViewHolder(@NonNull View itemView) {
        super(itemView);
        albumImage = itemView.findViewById(R.id.album_image);
        albumTitle = itemView.findViewById(R.id.album_title);
        albumArtist = itemView.findViewById(R.id.album_artist);
        albumTracks = itemView.findViewById(R.id.album_tracks);
        albumReleasedDate = itemView.findViewById(R.id.album_released_date);
    }
}
