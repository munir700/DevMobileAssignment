package assignment.cleancode.mobiledevassignment.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProductionCountries implements Parcelable {
    @SerializedName("iso_3166_1")
    private String isoCountry;

    private String name;

    protected ProductionCountries(Parcel in) {
        isoCountry = in.readString();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(isoCountry);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductionCountries> CREATOR = new Creator<ProductionCountries>() {
        @Override
        public ProductionCountries createFromParcel(Parcel in) {
            return new ProductionCountries(in);
        }

        @Override
        public ProductionCountries[] newArray(int size) {
            return new ProductionCountries[size];
        }
    };

    public String getIsoCountry() {
        return isoCountry;
    }

    public void setIsoCountry(String isoCountry) {
        this.isoCountry = isoCountry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ClassPojo [isoCountry = " + isoCountry + ", name = " + name + "]";
    }
}
