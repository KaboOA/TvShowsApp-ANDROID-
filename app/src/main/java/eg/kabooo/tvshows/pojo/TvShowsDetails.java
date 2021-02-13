package eg.kabooo.tvshows.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowsDetails {


    @SerializedName("description")
    private String description;

    @SerializedName("runtime")
    private int runtime;

    @SerializedName("url")
    private String url;

    @SerializedName("episodes")
    private List<Episode> episodes;

    @SerializedName("image_path")
    private String imagePath;

    @SerializedName("rating")
    private String rating;

    @SerializedName("genres")
    private String[] genres;

    @SerializedName("pictures")
    private String[] pictures;

    public String[] getPictures() {
        return pictures;
    }

    public String getRating() {
        return rating;
    }

    public String[] getGenres() {
        return genres;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getDescription() {
        return description;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getUrl() {
        return url;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

}