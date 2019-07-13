package assignment.cleancode.mobiledevassignment.repositories;


import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import assignment.cleancode.mobiledevassignment.BuildConfig;
import assignment.cleancode.mobiledevassignment.R;
import assignment.cleancode.mobiledevassignment.api.ApiEnvelopeService;
import assignment.cleancode.mobiledevassignment.api.ApiService;
import assignment.cleancode.mobiledevassignment.base.BaseNetworkCallBack;
import assignment.cleancode.mobiledevassignment.base.BaseViewModel;
import assignment.cleancode.mobiledevassignment.enums.ViewModelEventsEnum;
import assignment.cleancode.mobiledevassignment.models.ArrayListWithTotalResultCount;
import assignment.cleancode.mobiledevassignment.models.Movie;
import assignment.cleancode.mobiledevassignment.models.MovieListing;
import assignment.cleancode.mobiledevassignment.models.Videos;
import assignment.cleancode.mobiledevassignment.utils.NetworkUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository {

    @Inject
    ApiService apiService;

    @Inject
    ApiEnvelopeService apiEnvelopeService;

    @Inject
    NetworkUtils networkUtils;

    @Inject
    public MoviesRepository() {

    }

    public MutableLiveData<ArrayListWithTotalResultCount<MovieListing>> getMoviesList(final BaseViewModel viewModel, Call<ArrayListWithTotalResultCount<MovieListing>> listCall, final String sortBy, int pageSize) {

        final MutableLiveData<ArrayListWithTotalResultCount<MovieListing>> moviesLiveData = new MutableLiveData<>();
        if (networkUtils.isConnectedToInternet()) {
            if (listCall != null)
                listCall.cancel();
            listCall = apiEnvelopeService.getDiscoverMovieList(BuildConfig.API_KEY, "2016-12-31", sortBy, pageSize);
            listCall.enqueue(new BaseNetworkCallBack<ArrayListWithTotalResultCount<MovieListing>>(viewModel) {
                @Override
                public void onResponse(Call<ArrayListWithTotalResultCount<MovieListing>> call, Response<ArrayListWithTotalResultCount<MovieListing>> response) {
                    super.onResponse(call, response);
                    if (!call.isCanceled() && response.isSuccessful()) {
                        moviesLiveData.postValue(response.body());
                    }
                }
            });

        } else {
            viewModel.notifyObserver(ViewModelEventsEnum.NO_INTERNET_CONNECTION, viewModel.getAppManager().getContext().getString(R.string.NO_INTERNET_CONNECTIVITY));
        }
        return moviesLiveData;
    }


    public MutableLiveData<Movie> getMovieDetail(final BaseViewModel viewModel, Call<Movie> listCall, String movieId) {

        final MutableLiveData<Movie> moviesLiveData = new MutableLiveData<>();
        if (networkUtils.isConnectedToInternet()) {
            if (listCall != null)
                listCall.cancel();

            listCall = apiService.getMovieDetail(Integer.valueOf(movieId), BuildConfig.API_KEY);
            listCall.enqueue(new BaseNetworkCallBack<Movie>(viewModel) {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    super.onResponse(call, response);
                    if (!call.isCanceled() && response.isSuccessful()) {
                        moviesLiveData.postValue(response.body());
                    }
                }
            });

        } else {
            viewModel.notifyObserver(ViewModelEventsEnum.NO_INTERNET_CONNECTION, viewModel.getAppManager().getContext().getString(R.string.NO_INTERNET_CONNECTIVITY));
        }
        return moviesLiveData;
    }

}
