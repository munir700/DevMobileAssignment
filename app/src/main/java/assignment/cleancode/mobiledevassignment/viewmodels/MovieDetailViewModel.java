package assignment.cleancode.mobiledevassignment.viewmodels;

import android.util.Log;

import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import assignment.cleancode.mobiledevassignment.BR;
import assignment.cleancode.mobiledevassignment.base.BaseViewModel;
import assignment.cleancode.mobiledevassignment.models.Movie;
import assignment.cleancode.mobiledevassignment.models.MovieListing;
import assignment.cleancode.mobiledevassignment.models.Videos;
import assignment.cleancode.mobiledevassignment.repositories.MoviesRepository;
import assignment.cleancode.mobiledevassignment.utils.ErrorResponse;
import retrofit2.Call;

public class MovieDetailViewModel extends BaseViewModel {


    private ErrorResponse errorResponse;

    Call<Movie> movieCall;
    Call<Videos> videosCall;

    @Inject
    MoviesRepository moviesRepository;

    @Inject
    public MovieDetailViewModel() {

    }

    @Bindable
    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
        notifyPropertyChanged(BR.errorResponse);
    }

    public MutableLiveData<Movie> getMovieDetail(String moviezId) {
        return moviesRepository.getMovieDetail(this, movieCall, moviezId);
    }

    public MutableLiveData<Videos> getMovieVideo(String moviezId) {
        return moviesRepository.getMovieVideo(this, videosCall, moviezId);
    }

    public List<MovieListing> removeAdultMovies(List<MovieListing> listData) {
        for (MovieListing movie : listData) {
            if (!movie.getAdult()) {
                Log.e("removed movie", movie.getTitle());
                listData.remove(movie);
            }
        }
        return listData;
    }

}
