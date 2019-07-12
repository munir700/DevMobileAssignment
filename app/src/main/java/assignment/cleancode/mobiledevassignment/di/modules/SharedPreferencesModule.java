package assignment.cleancode.mobiledevassignment.di.modules;

import android.app.Application;

import javax.inject.Singleton;

import assignment.cleancode.mobiledevassignment.preferences.PreferenceHandler;
import dagger.Module;
import dagger.Provides;

@Module
public class SharedPreferencesModule {

    @Provides
    @Singleton
    PreferenceHandler provideSharedPreferences(Application application) {
        PreferenceHandler preferenceHandler = new PreferenceHandler(application);
        return preferenceHandler;
    }
}