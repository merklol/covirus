package com.bilingoal.virustracker.di.modules;

import com.bilingoal.virustracker.details.DetailsFragment;
import com.bilingoal.virustracker.main.MainFragment;
import com.bilingoal.virustracker.countries.CountriesFragment;
import com.bilingoal.virustracker.summary.SummaryFragment;
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
