package assignment.cleancode.mobiledevassignment.di;


import android.app.Application;

import javax.inject.Singleton;

import assignment.cleancode.mobiledevassignment.AppApplication;
import assignment.cleancode.mobiledevassignment.di.modules.ActivityBuilderModule;
import assignment.cleancode.mobiledevassignment.di.modules.AndroidWorkerInjectionModule;
import assignment.cleancode.mobiledevassignment.di.modules.ApiClientModule;
import assignment.cleancode.mobiledevassignment.di.modules.AppModule;
import assignment.cleancode.mobiledevassignment.di.modules.SharedPreferencesModule;
import assignment.cleancode.mobiledevassignment.di.modules.ViewModelModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AppModule.class,
        ApiClientModule.class,
        AndroidInjectionModule.class,
        ActivityBuilderModule.class,
        AndroidSupportInjectionModule.class,
        AndroidWorkerInjectionModule.class,
        ViewModelModule.class,
        SharedPreferencesModule.class
})
public interface NetComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        NetComponent build();
    }

    void inject(AppApplication app);
}