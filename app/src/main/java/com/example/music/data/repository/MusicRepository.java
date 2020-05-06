package com.example.music.data.repository;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.music.data.model.albumsinfo.AlbumInfos;
import com.example.music.data.model.artistsearch.Artist;
import com.example.music.data.model.artistsearch.ArtistSearch;
import com.example.music.data.model.topalbums.Album;
import com.example.music.data.model.topalbums.ArtistsTopAlbums;
import com.example.music.data.persistence.AlbumDao;
import com.example.music.data.persistence.AlbumsDatabase;
import com.example.music.data.requests.AppExecutors;
import com.example.music.data.requests.api.RetrofitService;
import com.example.music.data.requests.responses.ApiResponse;
import com.example.music.utils.NetworkBoundResource;
import com.example.music.utils.Resource;

import java.util.List;

import static com.example.music.utils.Constants.API_KEY;

public class MusicRepository {
    private static final String TAG = "MusicRepository";
    private static MusicRepository instance;
    private AlbumDao dao;

    public MusicRepository(Context context) {
        dao = AlbumsDatabase.getInstance(context).getAlbumDao();
    }

    public static MusicRepository getInstance(Context context) {
        if (instance == null) {
            instance = new MusicRepository(context);
        }
        return instance;
    }


    public LiveData<Resource<List<Artist>>> getArtists(final String artistName) {
        return new NetworkBoundResource<List<Artist>, ArtistSearch>(AppExecutors.getInstance()) {

            @Override
            protected void saveCallResult(@NonNull ArtistSearch item) {
                if (item.getResults().getArtistmatches().getArtist() != null) {

                    Artist[] artists = new Artist[item.getResults().getArtistmatches().getArtist().size()];
                    dao.insertArtist((Artist[]) item.getResults().getArtistmatches().getArtist().toArray(artists));
                }

            }

            @Override
            protected boolean shouldFetch(@Nullable List<Artist> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<Artist>> loadFromDb() {
                return dao.getArtists(artistName);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<ArtistSearch>> createCall() {
                return RetrofitService.getApi().getArtists(artistName, API_KEY);
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<com.example.music.data.model.albumsinfo.Album>> getAlbumInfo(final String id) {
        return new NetworkBoundResource<com.example.music.data.model.albumsinfo.Album, AlbumInfos>(AppExecutors.getInstance()) {

            @Override
            protected void saveCallResult(@NonNull AlbumInfos item) {
                if (item.getAlbum() != null) {
                    dao.insertAlbumInfo(item.getAlbum());

//                    dao.updateAlbums(item.getAlbum().getAlbumId(), item.getAlbum().getAlbumName(), item.getAlbum().getAlbumArtist(),
//                            item.getAlbum().getImage(), item.getAlbum().getTracks().getTrack(), item.getAlbum().getWiki().getPublished());

                }

            }

            @Override
            protected boolean shouldFetch(@Nullable com.example.music.data.model.albumsinfo.Album data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<com.example.music.data.model.albumsinfo.Album> loadFromDb() {
                return dao.getAlbumInfo(id);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<AlbumInfos>> createCall() {
                return RetrofitService.getApi().getAlbumInfo(API_KEY, id);
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<List<Album>>> getTopAlbums(final String artistName) {
        return new NetworkBoundResource<List<Album>, ArtistsTopAlbums>(AppExecutors.getInstance()) {

            @Override
            protected void saveCallResult(@NonNull ArtistsTopAlbums item) {
                if (item.getTopalbums().getAlbum() != null) {
                    Album[] albums = new Album[item.getTopalbums().getAlbum().size()];

                    int index = 0;
                    for (long rowid : dao.insertTopAlbum((Album[]) (item.getTopalbums().getAlbum().toArray(albums)))) {
                        if (rowid == -1) {
                            Log.d(TAG, "saveCallResult: CONFLICT. This top album is already in the cache");
                            // if the topAlbum already exists, do not change the favorite info or timestamp bcs they will be erased
                            dao.updateTopAlbum(albums[index].getTopAlbumId(),
                                    albums[index].getTopAlbumName(),
                                    albums[index].getArtist().getArtistName());
                        }
                        index++;
                    }


                }

            }

            @Override
            protected boolean shouldFetch(@Nullable List<Album> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<Album>> loadFromDb() {
                return dao.getTopAlbums(artistName);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<ArtistsTopAlbums>> createCall() {
                return RetrofitService.getApi().getTopAlbums(artistName, API_KEY);
            }
        }.getAsLiveData();
    }


}
