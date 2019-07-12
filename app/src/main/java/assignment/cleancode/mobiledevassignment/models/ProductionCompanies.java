package assignment.cleancode.mobiledevassignment.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProductionCompanies implements Parcelable {
    @SerializedName("logo_path")
    private String logoPath;

    private String name;

    private String id;
    @SerializedName("origin_country")
    private String originCountry;

    protected ProductionCompanies(Parcel in) {
        logoPath = in.readString();
        name = in.readString();
        id = in.readString();
        originCountry = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(logoPath);
        dest.writeString(name);
        dest.writeString(id);
        dest.writeString(originCountry);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductionCompanies> CREATOR = new Creator<ProductionCompanies>() {
        @Override
        public ProductionCompanies createFromParcel(Parcel in) {
            return new ProductionCompanies(in);
        }

        @Override
        public ProductionCompanies[] newArray(int size) {
            return new ProductionCompanies[size];
        }
    };

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    @Override
    public String toString() {
        return "ClassPojo [logoPath = " + logoPath + ", name = " + name + ", id = " + id + ", originCountry = " + originCountry + "]";
    }

}
