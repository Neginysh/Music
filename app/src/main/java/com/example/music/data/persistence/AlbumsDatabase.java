package com.example.music.data.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.music.data.model.albumsinfo.Album;
import com.example.music.data.model.artistsearch.Artist;


@Database(entities = {Album.class, com.example.music.data.model.topalbums.Album.class, Artist.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AlbumsDatabase extends RoomDatabase {

    public abstract AlbumDao getAlbumDao();

    public static final String DB_NAME = "album_database";

    private static AlbumsDatabase instance;

    public static AlbumsDatabase getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AlbumsDatabase.class,
                    DB_NAME)
                    //  .fallbackToDestructiveMigration()
                    //  .addCallback(callback)
                    .build();
        }
        return instance;
    }






  /*  private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
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
                    new ArrayList<String>(Arrays.asList("track1", "track2", "track3"))));
            albumDao.insert(new AlbumModel("album2", "artist2", "image",
                    new ArrayList<String>(Arrays.asList("track1", "track2", "track3"))));
            return null;
        }
    }*/


}

