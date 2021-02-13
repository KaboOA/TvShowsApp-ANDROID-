package eg.kabooo.tvshows.repositories;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import eg.kabooo.tvshows.network.ApiClient;
import eg.kabooo.tvshows.network.ApiService;
import eg.kabooo.tvshows.responses.TvShowDetailsResponse;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MostPopularTvShowDetailsRepo {

    private ApiService apiService;
    private static final String TAG = "MostPopularTvShowDetail";

    public MostPopularTvShowDetailsRepo() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    @SuppressLint("CheckResult")
    public LiveData<TvShowDetailsResponse> getTvShowDetailsResponse(String tvShowId) {
        MutableLiveData<TvShowDetailsResponse> mutableLiveData = new MutableLiveData<>();

        apiService.getMostPopularTvShowDetails(tvShowId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mutableLiveData::setValue, e -> Log.d(TAG, "Ahmed getTvShowDetailsResponse: " + e.getMessage()));

        return mutableLiveData;
    }

}
