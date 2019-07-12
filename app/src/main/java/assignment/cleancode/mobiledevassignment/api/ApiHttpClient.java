package assignment.cleancode.mobiledevassignment.api;



import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import assignment.cleancode.mobiledevassignment.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


public class ApiHttpClient {

    public OkHttpClient getHTTPClient(final Map<String, String> headers) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addNetworkInterceptor(new StethoInterceptor());
        httpClient.connectTimeout(2, TimeUnit.MINUTES);
        httpClient.readTimeout(75, TimeUnit.SECONDS);
        httpClient.writeTimeout(45, TimeUnit.SECONDS);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (BuildConfig.API_LOGGING)
            httpClient.addInterceptor(logging);

        return httpClient.build();

    }
}