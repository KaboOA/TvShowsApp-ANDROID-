package eg.kabooo.tvshows.pojo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "tvShows")
public class TvShow implements Serializable {

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("country")
    private String country;

    @SerializedName("network")
    private String network;

    @SerializedName("rating_count")
    private String ratingCount;

    @SerializedName("image_thumbnail_path")
    private String imageThumbnailPath;

    @SerializedName("description_source")
    private String descriptionSource;

    @SerializedName("name")
    private String name;

    @SerializedName("permalink")
    private String permalink;

    @SerializedName("start_date")
    private String startDate;

    @SerializedName("status")
    private String status;

    public String getCountry() {
        return country;
    }

    public String getNetwork() {
        return network;
    }

    public String getRatingCount() {
        return ratingCount;
    }

    public String getImageThumbnailPath() {
        return imageThumbnailPath;
    }

    public String getDescriptionSource() {
        return descriptionSource;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getPermalink() {
        return permalink;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getStatus() {
        return status;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public void setRatingCount(String ratingCount) {
        this.ratingCount = ratingCount;
    }

    public void setImageThumbnailPath(String imageThumbnailPath) {
        this.imageThumbnailPath = imageThumbnailPath;
    }

    public void setDescriptionSource(String descriptionSource) {
        this.descriptionSource = descriptionSource;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}