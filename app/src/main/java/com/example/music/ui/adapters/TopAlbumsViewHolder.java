package com.example.music.ui.adapters;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class TopAlbumsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView albumsName;
    public CircleImageView favImage;
    private OnAlbumListener onAlbumListener;
    private Context context;

    public TopAlbumsViewHolder(@NonNull View itemView, OnAlbumListener onAlbumListener, Context context) {
        super(itemView);
        albumsName = itemView.findViewById(R.id.album_name);
        favImage = itemView.findViewById(R.id.fav_image);
        this.onAlbumListener = onAlbumListener;

        this.context = context;
        itemView.setOnClickListener(this);
        favImage.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fav_image) {

            if (favImage.getDrawable().getConstantState() == context.getResources().getDrawable(R.drawable.ic_favorite_black_24dp).getConstantState()) {
                favImage.setImageResource(R.drawable.ic_favorite_border_black_24dp);

            } else
                favImage.setImageResource(R.drawable.ic_favorite_black_24dp);

        } else
            onAlbumListener.onAlbumClick(getAdapterPosition());
    }
}
