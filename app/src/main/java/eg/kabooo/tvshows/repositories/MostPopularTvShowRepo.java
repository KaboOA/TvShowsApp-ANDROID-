package eg.kabooo.tvshows.repositories;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import eg.kabooo.tvshows.network.ApiClient;
import eg.kabooo.tvshows.network.ApiService;
import eg.kabooo.tvshows.responses.TvShowResponse;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MostPopularTvShowRepo {

    private ApiService apiService;
    private static final String TAG = "MostPopularTvShowRepo";

    public MostPopularTvShowRepo() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    @SuppressLint("CheckResult")
    public LiveData<TvShowResponse> getTvShowResponse(int page) {
        MutableLiveData<TvShowResponse> mutableLiveData = new MutableLiveData<>();

        apiService.getMostPopularTvShows(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mutableLiveData::setValue, e -> Log.d(TAG, "Ahmed getTvShowResponse: " + e.getMessage()));

        return mutableLiveData;
    }

}
