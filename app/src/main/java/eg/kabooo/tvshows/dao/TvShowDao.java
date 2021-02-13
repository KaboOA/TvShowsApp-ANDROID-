package eg.kabooo.tvshows.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import eg.kabooo.tvshows.pojo.TvShow;
import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface TvShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addToWatchList(TvShow tvShow);

    @Delete
    Completable deleteFromWatchList(TvShow tvShow);

    @Query("select * from tvShows")
    Flowable<List<TvShow>> getWatchList();

    @Query("select * from tvShows where id = :tvShowId")
    Flowable<TvShow> getTvShowFromWatchList(String tvShowId);

}
