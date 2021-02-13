package eg.kabooo.tvshows.network;

import eg.kabooo.tvshows.responses.TvShowDetailsResponse;
import eg.kabooo.tvshows.responses.TvShowResponse;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("most-popular")
    Single<TvShowResponse> getMostPopularTvShows(@Query("page") int page);

    @GET("show-details")
    Single<TvShowDetailsResponse> getMostPopularTvShowDetails(@Query("q") String tvShowId);

    @GET("search")
    Single<TvShowResponse> getSearchedResult(@Query("q") String search, @Query("page") int page);

}
