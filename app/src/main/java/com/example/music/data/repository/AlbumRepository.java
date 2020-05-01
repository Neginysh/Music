package com.example.music.data.repository;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.music.data.model.AlbumModel;
import com.example.music.data.model.ArtistModel;
import com.example.music.data.model.TopAlbumModel;
import com.example.music.data.model.albumsinfo.AlbumInfos;
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

public class AlbumRepository {
    private static final String TAG = "AlbumRepository";
    private static AlbumRepository instance;
    private AlbumDao dao;

    public AlbumRepository(Context context) {
        dao = AlbumsDatabase.getInstance(context).getAlbumDao();
    }

    private static AlbumRepository getInstance(Context context) {
        if (instance == null) {
            instance = new AlbumRepository(context);
        }
        return instance;
    }


    public LiveData<Resource<List<ArtistModel>>> getArtists(final String artistName) {
        return new NetworkBoundResource<List<ArtistModel>, ArtistSearch>(AppExecutors.getInstance()) {

            @Override
            protected void saveCallResult(@NonNull ArtistSearch item) {
                if (item.getResults().getArtistmatches().getArtist() != null) {

                }

            }

            @Override
            protected boolean shouldFetch(@Nullable List<ArtistModel> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<ArtistModel>> loadFromDb() {
                return dao.getArtists(artistName);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<ArtistSearch>> createCall() {
                return RetrofitService.getApi().getArtists(artistName, API_KEY);
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<AlbumModel>> getAlbumInfo(final String id) {
        return new NetworkBoundResource<AlbumModel, AlbumInfos>(AppExecutors.getInstance()) {

            @Override
            protected void saveCallResult(@NonNull AlbumInfos item) {
                if (item.getAlbum() != null) {

                }

            }

            @Override
            protected boolean shouldFetch(@Nullable AlbumModel data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<AlbumModel> loadFromDb() {
                return dao.getAlbumInfo(id);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<AlbumInfos>> createCall() {
                return RetrofitService.getApi().getAlbumInfo(API_KEY, id);
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<List<TopAlbumModel>>> getTopAlbums(final String artistName) {
        return new NetworkBoundResource<List<TopAlbumModel>, ArtistsTopAlbums>(AppExecutors.getInstance()) {

            @Override
            protected void saveCallResult(@NonNull ArtistsTopAlbums item) {
                if (item.getTopalbums().getAlbum() != null) {
                    TopAlbumModel[] albums = new TopAlbumModel[item.getTopalbums().getAlbum().size()];

                    int index = 0;
                    for (long rowid : dao.insertTopAlbum((TopAlbumModel[]) (item.getTopalbums().getAlbum().toArray(albums)))) {
                        if (rowid == -1) {
                            Log.d(TAG, "saveCallResult: CONFLICT. This top album is already in the cache");
                            // if the topAlbum already exists, do not change the favorite info or timestamp bcs they will be erased
                            dao.updateTopAlbum(albums[index].getTopAlbumId(),
                                    albums[index].getTopAlbumName(),
                                    albums[index].getArtistName());
                        }
                        index++;
                    }


                }

            }

            @Override
            protected boolean shouldFetch(@Nullable List<TopAlbumModel> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<TopAlbumModel>> loadFromDb() {
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
