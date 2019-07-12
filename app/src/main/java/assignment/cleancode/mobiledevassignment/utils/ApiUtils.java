package assignment.cleancode.mobiledevassignment.utils;


import assignment.cleancode.mobiledevassignment.BuildConfig;

public class ApiUtils {
    public static final String INVALID_AUTH_TOKEN = "Invalid Auth Token";
    public static final String MALFORMED_JSON = "malformed JSON";
    public static final String CANCELED = "Canceled";
    public static final String SOCKET = "Socket";

    public static String getApiBaseUrl() {
        return BuildConfig.API_BASE_URL;
    }


    public class ApiActions {
        public static final String MOVIE_DETAIL = "movie_detail";
        public static final String MOVIE_LIST = "movies_list";

    }

    public class ApiController {

        public static final String MOVIE = "movie";

    }
}
