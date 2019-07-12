package assignment.cleancode.mobiledevassignment.viewmodels;

import android.util.Log;

import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import assignment.cleancode.mobiledevassignment.BR;
import assignment.cleancode.mobiledevassignment.base.BaseViewModel;
import assignment.cleancode.mobiledevassignment.models.ArrayListWithTotalResultCount;
import assignment.cleancode.mobiledevassignment.models.MovieListing;
import assignment.cleancode.mobiledevassignment.preferences.PreferenceHandler;
import assignment.cleancode.mobiledevassignment.repositories.MoviesRepository;
import assignment.cleancode.mobiledevassignment.utils.ErrorResponse;
import retrofit2.Call;

public class MovieViewModel extends BaseViewModel {

    private ObservableField<String> movieCount = new ObservableField<>();
    private ObservableField<String> playType = new ObservableField<>();

    public List<MovieListing> listMovies = new ArrayList<>();
    public MutableLiveData<List<MovieListing>> mutableLiveData = new MutableLiveData<>();
    private ObservableInt pageNo = new ObservableInt(0);
    private ObservableInt totalPages = new ObservableInt(0);

    private ErrorResponse errorResponse;

    Call<ArrayListWithTotalResultCount<MovieListing>> listCall;

    boolean isToClearLastLoadedContent = true;


    @Inject
    PreferenceHandler preferenceHandler;

    @Inject
    MoviesRepository moviesRepository;

    @Inject
    public MovieViewModel() {

    }


    public boolean isToClearLastLoadedContent() {
        return isToClearLastLoadedContent;
    }

    public void setToClearLastLoadedContent(boolean toClearLastLoadedContent) {
        isToClearLastLoadedContent = toClearLastLoadedContent;
    }


    public ObservableField<String> getMovieCount() {
        return movieCount;
    }

    public void setMovieCount(String movieCount) {
        this.movieCount.set(movieCount);
    }


    public ObservableField<String> getPlayType() {
        return playType;
    }

    public void setPlayType(String playType) {
        this.playType.set(playType);
    }

    public PreferenceHandler getPreferenceHandler() {
        return preferenceHandler;
    }

    public String getLastSelectedSortTitle() {
        return preferenceHandler.getLastSelectedSortTitle();
    }

    @Bindable
    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
        notifyPropertyChanged(BR.errorResponse);
    }

    public MutableLiveData<ArrayListWithTotalResultCount<MovieListing>> getMovies() {
        calls.add(listCall);
        int pageSize;
        if (isToClearLastLoadedContent)
            pageSize = 1;
        else
            pageSize = pageNo.get() + 1;
        return moviesRepository.getMoviesList(this, listCall, preferenceHandler.getLastSelectedSort(), pageSize);
    }

    public List<MovieListing> removeAdultMovies(ArrayListWithTotalResultCount<MovieListing> movies) {
        for (Iterator<MovieListing> iterator = movies.iterator(); iterator.hasNext(); ) {
            MovieListing movie = iterator.next();
            if (movie.getAdult()) {
                Log.e("removed movie", movie.getTitle());
                iterator.remove();
            }
        }
        Log.e("pageNo", "Print Page No " + movies.getPage());
        pageNo.set(movies.getPage());
        totalPages.set(movies.getTotalNumberOfPages());
        if (isToClearLastLoadedContent) {
            listMovies.addAll(new ArrayList<MovieListing>());
            listMovies.addAll(movies);
        } else {
            listMovies.addAll(movies);
        }
        return listMovies;
    }


    public boolean isLastPageLoaded() {
        if (listMovies != null && listMovies.size() > 0)
            return totalPages.get() != 0 && (totalPages.get() > pageNo.get());
        return true;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        for (Call call : calls) {
            if (call != null) {
                call.cancel();
                call = null;
            }
        }
    }
}
