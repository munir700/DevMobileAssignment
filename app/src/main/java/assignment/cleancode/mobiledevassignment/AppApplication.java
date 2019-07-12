package assignment.cleancode.mobiledevassignment;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.facebook.stetho.DumperPluginsProvider;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import javax.inject.Inject;

import assignment.cleancode.mobiledevassignment.di.DaggerNetComponent;
import assignment.cleancode.mobiledevassignment.extendedmodels.CustomDumperPlugin;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class AppApplication extends Application implements HasActivityInjector {

    private RefWatcher refWatcher;

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingInjector;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG)
            refWatcher = LeakCanary.install(this);
        initStetho();
        DaggerNetComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }

    private void initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(new DumperPluginsProvider() {
                    @Override
                    public Iterable<DumperPlugin> get() {
                        return new Stetho.DefaultDumperPluginsBuilder(getApplicationContext())
                                .provide(new CustomDumperPlugin())
                                .finish();
                    }
                })
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(getApplicationContext()))
                .build());
    }


    public RefWatcher getRefWatcher() {
        return refWatcher;
    }

    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingInjector;
    }
}
