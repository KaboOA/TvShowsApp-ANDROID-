package eg.kabooo.tvshows.repositories;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import eg.kabooo.tvshows.database.WatchListDatabase;
import eg.kabooo.tvshows.pojo.TvShow;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public class WatchListRepo {

    private WatchListDatabase watchListDatabase;

    public WatchListRepo(Context context) {
        watchListDatabase = WatchListDatabase.getWatchListDatabase(context);
    }

    public Completable addToWatchList(TvShow tvShow) {
        return watchListDatabase.tvShowDao().addToWatchList(tvShow);
    }

    public Flowable<List<TvShow>> getWatchList() {
        return watchListDatabase.tvShowDao().getWatchList();
    }

    public Completable deleteFromWatchList(TvShow tvShow) {
        return watchListDatabase.tvShowDao().deleteFromWatchList(tvShow);
    }

    public Flowable<TvShow> getTvShowFromWatchList(String tvShowId) {
        return watchListDatabase.tvShowDao().getTvShowFromWatchList(tvShowId);
    }
}
