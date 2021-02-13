package eg.kabooo.tvshows.veiwmodels;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import eg.kabooo.tvshows.R;
import eg.kabooo.tvshows.adapters.WatchListAdapter;
import eg.kabooo.tvshows.databinding.ActivityTvShowDetailsBinding;
import eg.kabooo.tvshows.pojo.TvShow;
import eg.kabooo.tvshows.repositories.WatchListRepo;
import eg.kabooo.tvshows.ui.TvShowDetailsActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WatchListViewModel extends AndroidViewModel {

    private WatchListRepo repo;
    private static final String TAG = "WatchListViewModel";

    public WatchListViewModel(Application application) {
        super(application);
        repo = new WatchListRepo(application);
    }

    @SuppressLint("CheckResult")
    public void addToWatchList(TvShow tvShow, ActivityTvShowDetailsBinding b, Context context) {
        repo.addToWatchList(tvShow)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    b.fabImage.setImageResource(R.drawable.ic_baseline_check_24);
                    Toast.makeText(context, "Added To WatchList", Toast.LENGTH_SHORT).show();
                }, e -> Log.d(TAG, "Ahmed addToWatchList: " + e.getMessage()));
    }

    @SuppressLint("CheckResult")
    public LiveData<List<TvShow>> getWatchList() {
        MutableLiveData<List<TvShow>> mutableLiveData = new MutableLiveData<>();

        repo.getWatchList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mutableLiveData::setValue);

        return mutableLiveData;
    }

    @SuppressLint("CheckResult")
    public void deleteWatchList(TvShow tvShow, Context context, List<TvShow> tvShowList, WatchListAdapter adapter, int position) {
        repo.deleteFromWatchList(tvShow)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    Toast.makeText(context, "Removed From WatchList", Toast.LENGTH_SHORT).show();
                    tvShowList.remove(position);
                    adapter.notifyItemRemoved(position);
                    adapter.notifyItemRangeChanged(position, adapter.getItemCount());
                });
    }

    @SuppressLint("CheckResult")
    public LiveData<TvShow> getTvShowFromWatchList(String tvShowId) {
        MutableLiveData<TvShow> mutableLiveData = new MutableLiveData<>();
        repo.getTvShowFromWatchList(tvShowId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mutableLiveData::setValue);
        return mutableLiveData;
    }


    @SuppressLint("CheckResult")
    public void delete(TvShow tvShow, ActivityTvShowDetailsBinding b, Context context) {
        repo.deleteFromWatchList(tvShow)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    b.fabImage.setImageResource(R.drawable.ic_baseline_visibility_24);
                    Toast.makeText(context, "Removed From WatchList", Toast.LENGTH_SHORT).show();
                });
    }
}
