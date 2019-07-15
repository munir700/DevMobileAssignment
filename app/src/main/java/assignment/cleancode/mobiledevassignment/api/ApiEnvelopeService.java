package assignment.cleancode.mobiledevassignment.api;


import assignment.cleancode.mobiledevassignment.models.ArrayListWithTotalResultCount;
import assignment.cleancode.mobiledevassignment.models.MovieListing;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEnvelopeService {

    //http://api.themoviedb.org/3/discover/movie?api_key=328c283cd27bd1877d9080ccb1604c91&primary_release_date.lte=2016-12-31&sort_by=release_date.desc&page=1
    @GET("discover/movie")
    Call<ArrayListWithTotalResultCount<MovieListing>> getDiscoverMovieList(@Query("api_key") String apiKey, @Query("primary_release_date.lte") String primaryReleaseDate,@Query("sort_by") String releaseDateDesc, @Query("page") int page);




}
