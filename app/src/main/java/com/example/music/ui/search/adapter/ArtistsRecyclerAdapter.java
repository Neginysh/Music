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

public class ArtistsRecyclerAdapter extends RecyclerView.Adapter<ArtistsRecyclerAdapter.BaseViewHolder> {

    private Context context;
    private List<Artist> artistList;
    private ItemClickListener mClickListener;

    public ArtistsRecyclerAdapter(Context context, List<Artist> artistList) {
        this.context = context;
        this.artistList = new ArrayList<>(artistList);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_list_item, parent, false);
        BaseViewHolder vh = new BaseViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        String name = artistList.get(position).getName();
        holder.tv_artist.setText(name);
    }

    @Override
    public int getItemCount() {
        return artistList.size();
    }

    public Artist getItem(int id) {
        return artistList.get(id);
    }



    public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tv_artist;

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_artist=itemView.findViewById(R.id.list_item_artist);
            tv_artist.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) mClickListener.onItemClick(v, getAdapterPosition());

        }
    }


    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
