package com.example.music.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.R;
import com.example.music.data.model.artistsearch.Artist;
import com.example.music.data.model.topalbums.Album;

import java.util.ArrayList;
import java.util.List;

public class ArtistsRecyclerAdapter extends RecyclerView.Adapter<
        RecyclerView.ViewHolder> {

    private static final int ARTISTS_TYPE = 1;
    private static final int LOADING_TYPE = 2;
    private static final int TOP_ALBUMS_TYPE = 3;

    private List<Artist> artistList;
//    private List<Album> albumsList;
    private OnArtistListerner onArtistListerner;

    public ArtistsRecyclerAdapter(OnArtistListerner onArtistListerner) {
        this.onArtistListerner = onArtistListerner;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case ARTISTS_TYPE: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_list_item, parent, false);
                return new ArtistsViewHolder(view, onArtistListerner);
            }
            case LOADING_TYPE: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loding_artists_list_item, parent, false);
                return new LoadingVewiHolder(view);
            }
//            case TOP_ALBUMS_TYPE: {
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topalbums_list_item, parent, false);
//                return new TopAlbumsViewHolder(view, onArtistListerner);
//            }
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_list_item, parent, false);
                return new ArtistsViewHolder(view, onArtistListerner);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == ARTISTS_TYPE) {
            ((ArtistsViewHolder) holder).tv_artist.setText(artistList.get(position).getName());
        }
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



    @Override
    public int getItemViewType(int position) {
        if (artistList.get(position).getName().equals("It is loading...")) {
            return LOADING_TYPE;
        }else return ARTISTS_TYPE;
    }


    public void displayLoading() {
        if (!isLoading()) {
            Artist artist = new Artist();
            artist.setName("It is loading...");
            List<Artist> loadingList = new ArrayList<>();
            loadingList.add(artist);
            artistList = loadingList;
            notifyDataSetChanged();
        }
    }

    private boolean isLoading() {
        if (artistList != null) {
            if (artistList.size() > 0) {
                if (artistList.get(artistList.size() - 1).getName().equals("It is loading..."))
                    return true;
            }
        }

        return false;
    }


//    public void displayTopAlbums(){
////        Artist artist = new Artist();
////        artist.setName("Show Top Albums...");
////        List<Artist> albumsLoaderList = new ArrayList<>();
////        albumsLoaderList.add(artist);
////        artistList = albumsLoaderList;
////        notifyDataSetChanged();
//        albumsList=new ArrayList<>();
//        Album album = new Album();
//        album.setName("Show Top Albums...");
//        List<Album> albumsLoaderList = new ArrayList<>();
//        albumsLoaderList.add(album);
//        albumsList = albumsLoaderList;
//        notifyDataSetChanged();
//    }

}
