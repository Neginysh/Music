package com.example.music.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.music.R;
import com.example.music.data.model.albumsinfo.Track;
import com.example.music.data.model.topalbums.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Album> albumsList;
    private OnAlbumListener onAlbumListener;
    private Context context;

    private static final int TOP_ALBUMS_TYPE = 1;
    private static final int ALBUM_INFO_TYPE = 2;
    private static final int LOADING_TYPE = 3;
    private com.example.music.data.model.albumsinfo.Album albumInfo;


    public AlbumsRecyclerAdapter(OnAlbumListener onAlbumListener, Context context) {
        this.onAlbumListener = onAlbumListener;
        this.context = context;
    }

    public void setTopAlbumsList(List<Album> albums) {
        albumsList = albums;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TOP_ALBUMS_TYPE: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topalbums_list_item, parent, false);
                return new TopAlbumsViewHolder(view, onAlbumListener, context);
            }
            case ALBUM_INFO_TYPE: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_info, parent, false);
                return new AlbumInfoViewHolder(view);
            }
            case LOADING_TYPE: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_item, parent, false);
                return new LoadingVewiHolder(view);
            }

            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topalbums_list_item, parent, false);
                return new TopAlbumsViewHolder(view, onAlbumListener, context);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);

        if (itemViewType == TOP_ALBUMS_TYPE) {
            ((TopAlbumsViewHolder) holder).albumsName.setText(albumsList.get(position).getName());
        } else if (itemViewType == ALBUM_INFO_TYPE) {
            if (albumInfo != null) {
                ((AlbumInfoViewHolder) holder).albumTitle.setText(albumInfo.getName());
                ((AlbumInfoViewHolder) holder).albumArtist.setText(albumInfo.getArtist());
                ((AlbumInfoViewHolder) holder).albumReleasedDate.setText(albumInfo.getWiki().getPublished());


                StringBuilder builder = new StringBuilder();
                List<Track> tracks = albumInfo.getTracks().getTrack();
                for (Track track : tracks) {
                    builder.append(track.getName() + "\n");
                }
                ((AlbumInfoViewHolder) holder).albumTracks.setText(builder.toString());


                Glide.with(context).load(albumInfo.getImage().get(2).getText()).into(((AlbumInfoViewHolder) holder).albumImage);


            }
        }
    }

    @Override
    public int getItemCount() {
        if (albumsList != null)
            return albumsList.size();
        return 0;
    }


    public Album getSelectedAlbum(int position) {
        if (albumsList.size() > 0) {
            return albumsList.get(position);
        }
        return null;
    }


    public void setAlbumInfo(com.example.music.data.model.albumsinfo.Album albumInfo) {
        if (albumInfo != null)
            this.albumInfo = albumInfo;
        notifyDataSetChanged();
    }

    public void displayAlbumInfo() {
        Album album = new Album();
        album.setName("Show album info...");
        List<Album> loadingList = new ArrayList<>();
        loadingList.add(album);
        albumsList = loadingList;
        notifyDataSetChanged();
//        notifyDataSetChanged();


    }

    @Override
    public int getItemViewType(int position) {
        if (albumsList.get(position).getName().equals("It is loading...")) {
            return LOADING_TYPE;
        } else if (albumsList.get(position).getName().equals("Show album info...")) {
            return ALBUM_INFO_TYPE;
        } else return TOP_ALBUMS_TYPE;
    }

    public void displayLoading() {
        if (!isLoading()) {
            Album album = new Album();
            album.setName("It is loading...");
            List<Album> loadingList = new ArrayList<>();
            loadingList.add(album);
            albumsList = loadingList;
            notifyDataSetChanged();
        }
    }

    private boolean isLoading() {
        if (albumsList != null) {
            if (albumsList.size() > 0) {
                if (albumsList.get(albumsList.size() - 1).getName().equals("It is loading..."))
                    return true;
            }
        }

        return false;
    }
}
