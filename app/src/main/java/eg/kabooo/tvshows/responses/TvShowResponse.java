package eg.kabooo.tvshows.responses;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import eg.kabooo.tvshows.pojo.TvShow;

public class TvShowResponse {

    @SerializedName("tv_shows")
    private List<TvShow> tvShows;

    @SerializedName("total")
    private String total;

    @SerializedName("pages")
    private int pages;

    @SerializedName("page")
    private int page;

    public List<TvShow> getTvShows() {
        return tvShows;
    }

    public String getTotal() {
        return total;
    }

    public int getPages() {
        return pages;
    }

    public int getPage() {
        return page;
    }

}