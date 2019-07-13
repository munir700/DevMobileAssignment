package assignment.cleancode.mobiledevassignment.views;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import assignment.cleancode.mobiledevassignment.R;
import assignment.cleancode.mobiledevassignment.adapter.PhotoSliderAdapter;
import assignment.cleancode.mobiledevassignment.base.BaseActivity;
import assignment.cleancode.mobiledevassignment.base.BaseViewModel;
import assignment.cleancode.mobiledevassignment.databinding.ActivityImageSliderBinding;
import assignment.cleancode.mobiledevassignment.models.Movie;
import assignment.cleancode.mobiledevassignment.models.ProductionCompanies;

public class ImageSliderActivity extends BaseActivity<BaseViewModel, ActivityImageSliderBinding> implements View.OnClickListener {

    private final static String TAG = ImageSliderActivity.class.getCanonicalName();
    public static final String MOVIE = "movies";


    private PhotoSliderAdapter mySliderPagerAdapter;
    int pagerPosition;
    private Movie movie;


    @Override
    public Class<BaseViewModel> getViewModel() {
        return BaseViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_image_slider;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }


    private void initUI() {
        try {

            Toolbar toolbar = binding.layoutToolbar.toolbar;
            toolbar.getBackground().mutate();
            toolbar.getBackground().setAlpha(0);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            getSupportActionBar().setTitle("");

            findViewById(R.id.next_ib).setOnClickListener(this);
            findViewById(R.id.previous_ib).setOnClickListener(this);


            Bundle intent = getIntent().getExtras();
            if (intent != null) {
                movie = intent.containsKey(MOVIE) ? (Movie) intent.getParcelable(MOVIE) : null;
                setPhotoSlider();
            }
            if (mySliderPagerAdapter.getCount() <= 1) {
                binding.previousIb.setVisibility(View.GONE);
                binding.nextIb.setVisibility(View.GONE);
            }

        } catch (Exception e) {
            Log.e(TAG, "Error while initialize UI components and message =" + e.getMessage());
        }

    }

    private void setPhotoSlider() {
        final ProductionCompanies[] productionCompanies = movie != null && movie.getProductionCompanies() != null ? movie.getProductionCompanies() : new ProductionCompanies[0];
        if (mySliderPagerAdapter == null) {
            mySliderPagerAdapter = new PhotoSliderAdapter(this, productionCompanies);
            binding.viewPager.setAdapter(mySliderPagerAdapter);
            binding.viewPager.setOffscreenPageLimit(4);
        } else {
            mySliderPagerAdapter.setPhotos(productionCompanies);
        }

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setEnableDisable(position, productionCompanies.length);
            }

            @Override
            public void onPageSelected(int position) {
                pagerPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        binding.viewPager.setCurrentItem(0);
    }

    private void setEnableDisable(int position, int totalSize) {
        if (position == 0) {
            binding.previousIb.setColorFilter(ContextCompat.getColor(this, R.color.text_color_1));
        } else {
            binding.previousIb.setColorFilter(ContextCompat.getColor(this, R.color.white));
        }
        if (position == totalSize - 1) {
            binding.nextIb.setColorFilter(ContextCompat.getColor(this, R.color.text_color_1));
        } else {
            binding.nextIb.setColorFilter(ContextCompat.getColor(this, R.color.white));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_ib:
                binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() + 1, true);
                break;
            case R.id.previous_ib:
                binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() - 1, true);
                break;
            default:
                break;
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    public void onBackButtonPressed(View view) {
        onSupportNavigateUp();
    }


}
