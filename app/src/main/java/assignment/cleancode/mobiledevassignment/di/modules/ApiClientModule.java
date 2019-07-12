package assignment.cleancode.mobiledevassignment.di.modules;


import java.util.HashMap;

import javax.inject.Singleton;

import assignment.cleancode.mobiledevassignment.api.ApiEnvelopeService;
import assignment.cleancode.mobiledevassignment.api.ApiHttpClient;
import assignment.cleancode.mobiledevassignment.api.ApiService;
import assignment.cleancode.mobiledevassignment.api.converter.ApiConverterFactory;
import assignment.cleancode.mobiledevassignment.api.converter.ApiEnvelopeConverterFactory;
import assignment.cleancode.mobiledevassignment.utils.ApiUtils;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiClientModule {
    @Provides
    @Singleton
    public ApiService getApiService() {

        Retrofit apiClient = new Retrofit.Builder()
                .baseUrl(ApiUtils.getApiBaseUrl())
                .addConverterFactory(new ApiConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new ApiHttpClient().getHTTPClient(new HashMap<String, String>()))
                .build();

        return apiClient.create(ApiService.class);

    }

    @Provides
    @Singleton
    public ApiEnvelopeService getEnvelopeApiService() {

        Retrofit apiClient = new Retrofit.Builder()
                .baseUrl(ApiUtils.getApiBaseUrl())
                .addConverterFactory(new ApiEnvelopeConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new ApiHttpClient().getHTTPClient(new HashMap<String, String>()))
                .build();

        return apiClient.create(ApiEnvelopeService.class);

    }
}
