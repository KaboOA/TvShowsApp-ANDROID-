package eg.kabooo.tvshows.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import eg.kabooo.tvshows.dao.TvShowDao;
import eg.kabooo.tvshows.pojo.TvShow;

@Database(entities = TvShow.class, version = 1, exportSchema = false)
public abstract class WatchListDatabase extends RoomDatabase {

    private static WatchListDatabase watchListDatabase;

    public abstract TvShowDao tvShowDao();

    public static synchronized WatchListDatabase getWatchListDatabase(Context context) {
        if (watchListDatabase == null) {
            watchListDatabase = Room.databaseBuilder(context, WatchListDatabase.class, "watch_list_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return watchListDatabase;
    }

}
