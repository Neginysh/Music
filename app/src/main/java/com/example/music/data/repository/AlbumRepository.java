package com.example.music.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.music.data.model.AlbumDao;
import com.example.music.data.model.AlbumModel;
import com.example.music.data.model.AlbumsDatabase;
import com.example.music.data.model.topalbums.Artist;

import java.util.List;

public class AlbumRepository {

    private AlbumDao albumDao;
    private LiveData<List<AlbumModel>> allAlbums;


    public AlbumRepository(Application application) {
        AlbumsDatabase albumsDatabase = AlbumsDatabase.getInstance(application);
        albumDao = albumsDatabase.albumDao();
        allAlbums = albumDao.getAlbums();
    }

    public void insert(AlbumModel album) {
        new InsertToAlbumAsynTast(albumDao).execute(album);

    }

    public void delete(AlbumModel album) {
        new DeleteAlbumAsynTast(albumDao).execute(album);

    }

    public void update(AlbumModel album) {
        new UpdateAlbumAsynTast(albumDao).execute(album);

    }

    public LiveData<List<AlbumModel>> getAllAlbums() {
        return allAlbums;
    }



    private static class InsertToAlbumAsynTast extends AsyncTask<AlbumModel, Void, Void> {
        AlbumDao albumDao;

        public InsertToAlbumAsynTast(AlbumDao albumDao) {
            this.albumDao = albumDao;
        }

        @Override
        protected Void doInBackground(AlbumModel... albums) {
            albumDao.insert(albums[0]);
            return null;
        }
    }

    private static class DeleteAlbumAsynTast extends AsyncTask<AlbumModel, Void, Void> {
        AlbumDao albumDao;

        public DeleteAlbumAsynTast(AlbumDao albumDao) {
            this.albumDao = albumDao;
        }

        @Override
        protected Void doInBackground(AlbumModel... albums) {
            albumDao.delete(albums[0]);
            return null;
        }
    }

    private static class UpdateAlbumAsynTast extends AsyncTask<AlbumModel, Void, Void> {
        AlbumDao albumDao;

        public UpdateAlbumAsynTast(AlbumDao albumDao) {
            this.albumDao = albumDao;
        }

        @Override
        protected Void doInBackground(AlbumModel... albums) {
            albumDao.update(albums[0]);
            return null;
        }
    }




}
