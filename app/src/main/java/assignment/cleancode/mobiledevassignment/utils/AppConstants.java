package assignment.cleancode.mobiledevassignment.utils;
;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import java.util.ArrayList;
import java.util.List;

import assignment.cleancode.mobiledevassignment.R;
import assignment.cleancode.mobiledevassignment.models.SortModel;

public class AppConstants {
    public static final String POPULARITY = "popular";
    public static final String UPCOMING = "upcoming";
    public static final String NOWPLAYING = "now_playing";
    public static final String TOPRATED = "top_rated";


    // SORT Data
    public static final int INDEX_POPULARITY = 0;
    public static final int INDEX_NOW_PLAYING = 1;
    public static final int INDEX_UP_COMING = 2;
    public static final int INDEX_TOP_RATED = 3;

    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        try {
            imageView.setImageResource(resource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static final List<SortModel> moviesSortModelList = new ArrayList<SortModel>() {
        {
            add(new SortModel(INDEX_POPULARITY, R.string.STR_POPULAR, R.drawable.ic_popular_24, "", ""));
            add(new SortModel(INDEX_NOW_PLAYING, R.string.STR_NOW_PLAYING, R.drawable.ic_popular_24, "", ""));
            add(new SortModel(INDEX_UP_COMING, R.string.STR_UPCOMING, R.drawable.ic_popular_24,"", ""));
            add(new SortModel(INDEX_TOP_RATED, R.string.STR_TOP_RATED, R.drawable.ic_popular_24, "", ""));
        }
    };
}
