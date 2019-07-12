package assignment.cleancode.mobiledevassignment.adapter;

import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import assignment.cleancode.mobiledevassignment.BuildConfig;
import assignment.cleancode.mobiledevassignment.R;
import assignment.cleancode.mobiledevassignment.databinding.RowItemPhotosSliderBinding;
import assignment.cleancode.mobiledevassignment.models.ProductionCompanies;


public class PhotoSliderAdapter extends PagerAdapter {



    private Activity context;
    private ProductionCompanies[] images;

    private RequestOptions imageOptions;


    public void setPhotoSliderCallBackListener() {
    }

    public void setPhotos(ProductionCompanies[] images) {
        this.images = images;
        this.notifyDataSetChanged();
    }


    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public PhotoSliderAdapter(Activity context,
                              ProductionCompanies[] images) {
        this.context = context;
        this.images = images;
        imageOptions = new RequestOptions()
                .placeholder(R.drawable.img_loading_pics)
                .error(R.drawable.img_loading_pics)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH).override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
    }


    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup parent, final int position) {


        RowItemPhotosSliderBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.row_item_photos_slider,
                parent, false);

        binding.getRoot().setTag("" + position);
        initializeView(parent, binding, position);


        return binding.getRoot();
    }


    private void initializeView(final ViewGroup container, final RowItemPhotosSliderBinding binding, final int position) {

        ProductionCompanies productionCompany = images[position];

        binding.ivPlaceholder.setScaleType(ImageView.ScaleType.CENTER_CROP);

        String imgUrl = BuildConfig.IMG_BASE_URL_LARGE + productionCompany.getLogoPath();
        Log.e("imgUrl", imgUrl);

        RequestOptions imageOptions = new RequestOptions()
                .placeholder(R.drawable.img_loading_pics)
                .error(R.drawable.img_loading_pics)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH).override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
        Glide.with(context)
                .load(imgUrl)
                .apply(imageOptions)
                .into(binding.ivPlaceholder);

        container.addView(binding.getRoot());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding.ivPlaceholder.setTransitionName("PROP_DETAILS_TRANSITION" + position);
        }


    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);

    }


}