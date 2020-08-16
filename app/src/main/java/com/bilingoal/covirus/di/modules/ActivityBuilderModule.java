package com.bilingoal.covirus.di.modules;

import com.bilingoal.covirus.MainActivity;
import com.bilingoal.covirus.di.scopes.ActivityScope;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

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
