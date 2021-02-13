package eg.kabooo.tvshows.veiwmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import eg.kabooo.tvshows.repositories.MostPopularTvShowDetailsRepo;
import eg.kabooo.tvshows.responses.TvShowDetailsResponse;

public class MostPopularTvShowDetailsViewModel extends ViewModel {

    private MostPopularTvShowDetailsRepo repo;

    public MostPopularTvShowDetailsViewModel() {
        repo = new MostPopularTvShowDetailsRepo();
    }

    public LiveData<TvShowDetailsResponse> getMostPopularTvShows(String tvShowId) {
        return repo.getTvShowDetailsResponse(tvShowId);
    }

}
