package com.bilingoal.covirus.di.modules;

import com.bilingoal.covirus.R;
import com.bilingoal.covirus.adapters.FilterableCountryListAdapter;
import com.bilingoal.covirus.countries.CountriesPresenter;
import com.bilingoal.covirus.di.qualifiers.CountriesList;
import com.bilingoal.covirus.di.scopes.ActivityScope;
import com.bilingoal.covirus.models.StateManager;
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
