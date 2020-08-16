package com.bilingoal.virustracker.di.modules;

import com.bilingoal.virustracker.R;
import com.bilingoal.virustracker.adapters.FilterableCountryListAdapter;
import com.bilingoal.virustracker.countries.CountriesPresenter;
import com.bilingoal.virustracker.details.DetailsPresenter;
import com.bilingoal.virustracker.di.qualifiers.CountriesList;
import com.bilingoal.virustracker.di.scopes.ActivityScope;
import com.bilingoal.virustracker.models.StateManager;
import com.bilingoal.virustracker.network.NetworkInterface;
import com.bilingoal.virustracker.utils.schedulerproviders.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class CountriesModule {

    @ActivityScope
    @CountriesList
    @Provides
    static int provideRecyclerViewLayoutResId(){
        return R.layout.country_list_item;
    }

    @ActivityScope
    @Provides
    static FilterableCountryListAdapter provideRecyclerViewAdapter(@CountriesList int layoutResId){
        return new FilterableCountryListAdapter(layoutResId, true);
    }

    @ActivityScope
    @Provides
    static CountriesPresenter provideCountriesPresenter(StateManager stateManager) {
        return new CountriesPresenter(stateManager);
    }
}
