package assignment.cleancode.mobiledevassignment.api;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class ResponseEnvelope<T> implements Serializable {

    int id;
    public int page;

    @SerializedName("status_code")
    public int status;
    @SerializedName("status_message")
    public String message;

    @SerializedName("total_pages")
    public int totalPages;

    @SerializedName("total_results")
    public int totalResults;

    @SerializedName("results")
    public T listItem;


    public static ExclusionStrategyResponse getExclusionStrategy() {
        return new ExclusionStrategyResponse();
    }

}


class ExclusionStrategyResponse implements ExclusionStrategy {

    public boolean shouldSkipClass(Class<?> arg0) {
        return false;
    }

    public boolean shouldSkipField(FieldAttributes f) {
        return (f.getName().equals("response"));
    }
}