package eg.kabooo.tvshows.responses;

import com.google.gson.annotations.SerializedName;

import eg.kabooo.tvshows.pojo.TvShowsDetails;

public class TvShowDetailsResponse {

    @SerializedName("tvShow")
    private TvShowsDetails tvShowsDetails;

    public TvShowsDetails getTvShowsDetails() {
        return tvShowsDetails;
    }

}
