package com.example.music.data.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.music.data.model.topalbums.Album;

import java.util.ArrayList;
import java.util.Arrays;


@Database(entities = {AlbumModel.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AlbumsDatabase extends RoomDatabase {

    private static AlbumsDatabase instance;

    public abstract AlbumDao albumDao();

    public static synchronized AlbumsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AlbumsDatabase.class, "album_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDB(instance).execute();
        }
    };

    private static class PopulateDB extends AsyncTask<Void, Void, Void> {
        private AlbumDao albumDao;

        public PopulateDB(AlbumsDatabase database) {
            this.albumDao = database.albumDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            albumDao.insert(new AlbumModel("album1", "artist1", "image",
                    new ArrayList<String>(Arrays.asList("track1", "track2","track3"))));
            albumDao.insert(new AlbumModel("album2", "artist2", "image",
                    new ArrayList<String>(Arrays.asList("track1", "track2","track3"))));
            return null;
        }
    }





}

