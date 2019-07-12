package assignment.cleancode.mobiledevassignment.api;


import assignment.cleancode.mobiledevassignment.models.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("movie/{movie_id}")
    Call<Movie> getMovieDetail(@Path("movie_id") int movieId, @Query("api_key") String apiKey, @Query("language") String lang);

}
