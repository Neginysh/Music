package com.example.music.ui.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.R;

public class ArtistsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView tv_artist;
    private OnArtistListerner onArtistListerner;

    public ArtistsViewHolder(@NonNull View itemView, OnArtistListerner onArtistListerner) {
        super(itemView);
        tv_artist=itemView.findViewById(R.id.list_item_artist);
        tv_artist.setOnClickListener(this);
        this.onArtistListerner = onArtistListerner;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onArtistListerner.onArtistClick(tv_artist.getText().toString());
    }
}
