package assignment.cleancode.mobiledevassignment.api;


import assignment.cleancode.mobiledevassignment.models.ArrayListWithTotalResultCount;
import assignment.cleancode.mobiledevassignment.models.MovieListing;
import assignment.cleancode.mobiledevassignment.models.Videos;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEnvelopeService {

    @GET("movie/{playing_type}/")
    Call<ArrayListWithTotalResultCount<MovieListing>> getMovieList(@Path("playing_type") String playingType, @Query("api_key") String apiKey, @Query("language") String lang, @Query("page") int page);


    @GET("movie/{movie_id}/videos")
    Call<Videos> getMovieVideo(@Path("movie_id") int movieId, @Query("api_key") String apiKey, @Query("language") String lang);



}
