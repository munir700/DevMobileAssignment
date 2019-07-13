package assignment.cleancode.mobiledevassignment.di.modules;


import assignment.cleancode.mobiledevassignment.views.ImageSliderActivity;
import assignment.cleancode.mobiledevassignment.views.MovieDetailActivity;
import assignment.cleancode.mobiledevassignment.views.MoviesActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract MoviesActivity moviesActivity();

    @ContributesAndroidInjector
    abstract MovieDetailActivity movieDetailActivity();

    @ContributesAndroidInjector
    abstract ImageSliderActivity imageSliderActivity();
}
