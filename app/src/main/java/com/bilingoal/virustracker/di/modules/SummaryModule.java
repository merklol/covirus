package com.bilingoal.virustracker.di.modules;

import com.bilingoal.virustracker.R;
import com.bilingoal.virustracker.adapters.ListAdapter;
import com.bilingoal.virustracker.di.qualifiers.SummaryList;
import com.bilingoal.virustracker.di.scopes.ActivityScope;
import com.bilingoal.virustracker.dto.Country;
import com.bilingoal.virustracker.summary.SummaryPresenter;
import com.bilingoal.virustracker.models.StateManager;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class SummaryModule {

    @ActivityScope
    @Provides
    static SummaryPresenter provideSummaryPresenter(StateManager stateManager){
        return new SummaryPresenter(stateManager);
    }

    @ActivityScope
    @SummaryList
    @Provides
    static int provideRecyclerViewLayoutResId(){
        return R.layout.recycler_view_item;
    }

    @ActivityScope
    @Provides
    static ListAdapter<Country> provideRecyclerViewAdapter(@SummaryList int layoutResId){
        return new ListAdapter<>(layoutResId, true);
    }
}
