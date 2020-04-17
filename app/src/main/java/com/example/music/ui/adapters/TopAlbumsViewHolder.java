package com.example.music.ui.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class TopAlbumsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView albumsName;
    public CircleImageView favImage;
    public TextView tracks;
    public CircleImageView albumImg;
    private OnArtistListerner onArtistListerner;

    public TopAlbumsViewHolder(@NonNull View itemView, OnArtistListerner onArtistListerner) {
        super(itemView);
        albumsName = itemView.findViewById(R.id.album_name);
        favImage = itemView.findViewById(R.id.fav_image);
        tracks = itemView.findViewById(R.id.tracks);
        albumImg = itemView.findViewById(R.id.album_image);
        this.onArtistListerner = onArtistListerner;

        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        onArtistListerner.onAlbumClick(getAdapterPosition());
    }
}
