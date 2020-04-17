package com.example.music.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.R;
import com.example.music.data.model.topalbums.Album;

import java.util.List;

public class AlbumsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Album> albumsList;
    private OnArtistListerner onArtistListerner;

    public AlbumsRecyclerAdapter(OnArtistListerner onArtistListerner) {
        this.onArtistListerner = onArtistListerner;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topalbums_list_item, parent, false);
        return new TopAlbumsViewHolder(view, onArtistListerner);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((TopAlbumsViewHolder) holder).albumsName.setText(albumsList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (albumsList != null)
            return albumsList.size();
        return 0;
    }


    public void setTopAlbumsList(List<Album> albums) {
        albumsList = albums;
        notifyDataSetChanged();
    }
}