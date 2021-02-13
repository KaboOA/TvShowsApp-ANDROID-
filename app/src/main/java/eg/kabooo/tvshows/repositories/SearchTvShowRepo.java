package eg.kabooo.tvshows.repositories;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import eg.kabooo.tvshows.network.ApiClient;
import eg.kabooo.tvshows.network.ApiService;
import eg.kabooo.tvshows.responses.TvShowResponse;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class SearchTvShowRepo {

    private ApiService apiService;

    public SearchTvShowRepo() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    @SuppressLint("CheckResult")
    public LiveData<TvShowResponse> liveData(String search, int page) {
        MutableLiveData<TvShowResponse> mutableLiveData = new MutableLiveData<>();

        apiService.getSearchedResult(search, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mutableLiveData::setValue);
        return mutableLiveData;
    }

}
