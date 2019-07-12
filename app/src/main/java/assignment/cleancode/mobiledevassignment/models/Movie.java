package assignment.cleancode.mobiledevassignment.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

public class Movie extends BaseObservable implements Parcelable {

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("imdb_id")
    private String imdbId;

    private String video;

    private String title;

    @SerializedName("backdrop_path")
    private String backdropPath;

    private String revenue;

    private Genres[] genres;

    private String popularity;

    @SerializedName("production_countries")
    private ProductionCountries[] productionCountries;

    private String id;

    @SerializedName("vote_count")
    private String voteCount;

    private String budget;

    private String overview;

    @SerializedName("original_title")
    private String originalTitle;

    private String runtime;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("spoken_languages")
    private SpokenLanguages[] spokenLanguages;

    @SerializedName("production_companies")
    private ProductionCompanies[] productionCompanies;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("vote_average")
    private String voteAverage;

    @SerializedName("belongs_to_collection")
    private String belongsToCollection;

    private String tagline;

    private String adult;

    private String homepage;

    private String status;

    public Movie(){

    }

    protected Movie(Parcel in) {
        originalLanguage = in.readString();
        imdbId = in.readString();
        video = in.readString();
        title = in.readString();
        backdropPath = in.readString();
        revenue = in.readString();
        popularity = in.readString();
        productionCountries = in.createTypedArray(ProductionCountries.CREATOR);
        id = in.readString();
        voteCount = in.readString();
        budget = in.readString();
        overview = in.readString();
        originalTitle = in.readString();
        runtime = in.readString();
        posterPath = in.readString();
        spokenLanguages = in.createTypedArray(SpokenLanguages.CREATOR);
        productionCompanies = in.createTypedArray(ProductionCompanies.CREATOR);
        releaseDate = in.readString();
        voteAverage = in.readString();
        belongsToCollection = in.readString();
        tagline = in.readString();
        adult = in.readString();
        homepage = in.readString();
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(originalLanguage);
        dest.writeString(imdbId);
        dest.writeString(video);
        dest.writeString(title);
        dest.writeString(backdropPath);
        dest.writeString(revenue);
        dest.writeString(popularity);
        dest.writeTypedArray(productionCountries, flags);
        dest.writeString(id);
        dest.writeString(voteCount);
        dest.writeString(budget);
        dest.writeString(overview);
        dest.writeString(originalTitle);
        dest.writeString(runtime);
        dest.writeString(posterPath);
        dest.writeTypedArray(spokenLanguages, flags);
        dest.writeTypedArray(productionCompanies, flags);
        dest.writeString(releaseDate);
        dest.writeString(voteAverage);
        dest.writeString(belongsToCollection);
        dest.writeString(tagline);
        dest.writeString(adult);
        dest.writeString(homepage);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public Genres[] getGenres() {
        return genres;
    }

    public void setGenres(Genres[] genres) {
        this.genres = genres;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public ProductionCountries[] getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(ProductionCountries[] productionCountries) {
        this.productionCountries = productionCountries;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public SpokenLanguages[] getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(SpokenLanguages[] spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public ProductionCompanies[] getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(ProductionCompanies[] productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getelongsToCollection() {
        return belongsToCollection;
    }

    public void setBelongs_to_collection(String belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductionCounties() {
        StringBuilder builder = new StringBuilder();
        if (productionCountries != null) {
            int length = productionCountries.length;
            for (int index = 0; index < length; index++) {
                builder.append(productionCountries[index].getName());
                if (index != length - 1) {
                    builder.append(", ");
                }
            }
        }
        return builder.toString();
    }

    public String getSpokenLanguage() {
        StringBuilder builder = new StringBuilder();
        if (spokenLanguages != null) {
            int length = spokenLanguages.length;
            for (int index = 0; index < length; index++) {
                builder.append(spokenLanguages[index].getName());
                if (index != length - 1) {
                    builder.append(", ");
                }
            }
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        return "ClassPojo [originalLanguage = " + originalLanguage + ", imdbId = " + imdbId + ", video = " + video + ", title = " + title + ", backdropPath = " + backdropPath + ", revenue = " + revenue + ", genres = " + genres + ", popularity = " + popularity + ", productionCountries = " + productionCountries + ", id = " + id + ", voteCount = " + voteCount + ", budget = " + budget + ", overview = " + overview + ", originalTitle = " + originalTitle + ", runtime = " + runtime + ", posterPath = " + posterPath + ", spokenLanguages = " + spokenLanguages + ", productionCompanies = " + productionCompanies + ", releaseDate = " + releaseDate + ", voteAverage = " + voteAverage + ", belongsToCollection = " + belongsToCollection + ", tagline = " + tagline + ", adult = " + adult + ", homepage = " + homepage + ", status = " + status + "]";
    }

    public Movie toMovie(MovieListing movieListing){
        setId(movieListing.getId());
        setTitle(movieListing.getTitle());
        setOriginalTitle(movieListing.getOriginalTitle());
        setOverview(movieListing.getOverview());
        setOriginalLanguage(movieListing.getOriginalLanguage());
        setReleaseDate(movieListing.getReleaseDate());
        setPosterPath(movieListing.getPosterPath());
        return this;
    }
}
