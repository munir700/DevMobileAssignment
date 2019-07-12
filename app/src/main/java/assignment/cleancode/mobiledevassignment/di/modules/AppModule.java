package assignment.cleancode.mobiledevassignment.di.modules;

import android.app.Application;

import javax.inject.Singleton;

import assignment.cleancode.mobiledevassignment.managers.AppManager;
import assignment.cleancode.mobiledevassignment.preferences.PreferenceHandler;
import assignment.cleancode.mobiledevassignment.utils.NetworkUtils;
import dagger.Module;
import dagger.Provides;

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    AppManager providesAppManager(Application application, PreferenceHandler preferenceHandler) {
        AppManager appManager = new AppManager(application, preferenceHandler);
        return appManager;
    }


    @Provides
    @Singleton
    NetworkUtils providesNetworkUtils(Application application) {
        NetworkUtils networkUtils = new NetworkUtils(application);
        return networkUtils;
    }

}
