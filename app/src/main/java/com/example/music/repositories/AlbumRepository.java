package com.example.music.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.music.models.Album;
import com.example.music.models.AlbumDao;
import com.example.music.models.AlbumsDatabase;

import java.util.List;

public class AlbumRepository {

    private AlbumDao albumDao;
    private LiveData<List<Album>> allAlbums;

    public AlbumRepository(Application application) {
        AlbumsDatabase albumsDatabase = AlbumsDatabase.getInstance(application);
        albumDao = albumsDatabase.albumDao();
        allAlbums = albumDao.getAlbums();
    }

    public void insert(Album album) {
        new InsertToAlbumAsynTast(albumDao).execute(album);

    }

    public void delete(Album album) {
        new DeleteAlbumAsynTast(albumDao).execute(album);

    }

    public void update(Album album) {
        new UpdateAlbumAsynTast(albumDao).execute(album);

    }

    public LiveData<List<Album>> getAllAlbums() {
        return allAlbums;
    }

    private static class InsertToAlbumAsynTast extends AsyncTask<Album, Void, Void> {
        AlbumDao albumDao;

        public InsertToAlbumAsynTast(AlbumDao albumDao) {
            this.albumDao = albumDao;
        }

        @Override
        protected Void doInBackground(Album... albums) {
            albumDao.insert(albums[0]);
            return null;
        }
    }

    private static class DeleteAlbumAsynTast extends AsyncTask<Album, Void, Void> {
        AlbumDao albumDao;

        public DeleteAlbumAsynTast(AlbumDao albumDao) {
            this.albumDao = albumDao;
        }

        @Override
        protected Void doInBackground(Album... albums) {
            albumDao.delete(albums[0]);
            return null;
        }
    }

    private static class UpdateAlbumAsynTast extends AsyncTask<Album, Void, Void> {
        AlbumDao albumDao;

        public UpdateAlbumAsynTast(AlbumDao albumDao) {
            this.albumDao = albumDao;
        }

        @Override
        protected Void doInBackground(Album... albums) {
            albumDao.update(albums[0]);
            return null;
        }
    }
}
