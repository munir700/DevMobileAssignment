package assignment.cleancode.mobiledevassignment.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class SpokenLanguages implements Parcelable {
    private String name;
    @SerializedName("iso_639_1")
    private String isoLang;

    protected SpokenLanguages(Parcel in) {
        name = in.readString();
        isoLang = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(isoLang);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SpokenLanguages> CREATOR = new Creator<SpokenLanguages>() {
        @Override
        public SpokenLanguages createFromParcel(Parcel in) {
            return new SpokenLanguages(in);
        }

        @Override
        public SpokenLanguages[] newArray(int size) {
            return new SpokenLanguages[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsoLang() {
        return isoLang;
    }

    public void setIsoLang(String isoLang) {
        this.isoLang = isoLang;
    }

    @Override
    public String toString() {
        return "ClassPojo [name = " + name + ", isoLang = " + isoLang + "]";
    }
}
