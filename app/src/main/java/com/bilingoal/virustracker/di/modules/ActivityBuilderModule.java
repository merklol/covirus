package com.bilingoal.virustracker.di.modules;

import com.bilingoal.virustracker.MainActivity;
import com.bilingoal.virustracker.di.scopes.ActivityScope;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import javax.inject.Singleton;

@Module
public abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = {
            FragmentBuilderModule.class,
            SummaryModule.class,
            CountriesModule.class,
            DetailsModule.class
    })
    abstract MainActivity contributeMainActivity();
}
