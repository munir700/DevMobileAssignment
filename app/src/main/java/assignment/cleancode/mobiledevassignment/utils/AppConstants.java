package assignment.cleancode.mobiledevassignment.utils;
;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import java.util.ArrayList;
import java.util.List;

import assignment.cleancode.mobiledevassignment.R;
import assignment.cleancode.mobiledevassignment.models.SortModel;

public class AppConstants {
    public static final String DATE_DESC = "\"release_date.desc\"";
    public static final String DATE_ASC = "\"popularity.asc\"";


    // SORT Data
    public static final int INDEX_DATE_DESC = 0;
    public static final int INDEX_DATE_ASC = 1;

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
            add(new SortModel(INDEX_DATE_DESC, R.string.STR_DATE_DESC, R.drawable.ic_popular_24, "", ""));
            add(new SortModel(INDEX_DATE_ASC, R.string.STR_DATE_ASC, R.drawable.ic_popular_24, "", ""));
        }
    };
}
