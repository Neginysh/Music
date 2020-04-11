package com.example.music.ui.search.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.R;
import com.example.music.data.model.artistsearch.Artist;

import java.util.ArrayList;
import java.util.List;

public class ArtistsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Artist> artistList;
    private OnArtistListerner onArtistListerner;

    public ArtistsRecyclerAdapter(OnArtistListerner onArtistListerner) {
        this.onArtistListerner = onArtistListerner;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_list_item, parent, false);
        return new ArtistsViewHolder(view, onArtistListerner);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ArtistsViewHolder) holder).tv_artist.setText(artistList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (artistList != null)
            return artistList.size();
        return 0;
    }

    public void setArtistList(List<Artist> artists) {
        artistList = artists;
        notifyDataSetChanged();
    }
}
