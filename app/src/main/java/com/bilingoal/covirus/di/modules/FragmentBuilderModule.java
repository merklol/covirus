package com.bilingoal.covirus.di.modules;

import com.bilingoal.covirus.details.DetailsFragment;
import com.bilingoal.covirus.main.MainFragment;
import com.bilingoal.covirus.countries.CountriesFragment;
import com.bilingoal.covirus.summary.SummaryFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract MainFragment contributeMainFragment();

    @ContributesAndroidInjector
    abstract SummaryFragment contributeSummaryFragment();

    @ContributesAndroidInjector
    abstract CountriesFragment contributeCountriesFragment();

    @ContributesAndroidInjector
    abstract DetailsFragment contributeDetailsFragment();
}
