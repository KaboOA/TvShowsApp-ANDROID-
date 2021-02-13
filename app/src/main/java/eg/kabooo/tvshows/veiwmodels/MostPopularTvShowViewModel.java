package eg.kabooo.tvshows.veiwmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import eg.kabooo.tvshows.repositories.MostPopularTvShowRepo;
import eg.kabooo.tvshows.responses.TvShowResponse;

public class MostPopularTvShowViewModel extends ViewModel {

    private MostPopularTvShowRepo repo;

    public MostPopularTvShowViewModel() {
        repo = new MostPopularTvShowRepo();
    }

    public LiveData<TvShowResponse> getMostPopularTvShows(int page) {
        return repo.getTvShowResponse(page);
    }

}
