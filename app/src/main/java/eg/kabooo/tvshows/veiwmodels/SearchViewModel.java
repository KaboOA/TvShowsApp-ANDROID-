package eg.kabooo.tvshows.veiwmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import eg.kabooo.tvshows.repositories.SearchTvShowRepo;
import eg.kabooo.tvshows.responses.TvShowResponse;

public class SearchViewModel extends ViewModel {

    private SearchTvShowRepo searchTvShowRepo;

    public SearchViewModel() {
        searchTvShowRepo = new SearchTvShowRepo();
    }

    public LiveData<TvShowResponse> getSearchedResult(String search, int page) {
        String searchResult = "%" + search + "%";
       return searchTvShowRepo.liveData(searchResult, page);
    }
}
