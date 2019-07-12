package assignment.cleancode.mobiledevassignment.di.modules;


import com.facebook.stetho.inspector.protocol.module.Worker;

import java.util.Map;

import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.Multibinds;

@Module
public abstract class AndroidWorkerInjectionModule {
    @Multibinds
    abstract Map<Class<? extends Worker>, AndroidInjector.Factory<? extends Worker>>
    workerInjectorFactories();
}
