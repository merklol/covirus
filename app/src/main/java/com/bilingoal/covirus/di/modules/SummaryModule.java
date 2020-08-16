package com.bilingoal.covirus.di.modules;

import com.bilingoal.covirus.R;
import com.bilingoal.covirus.adapters.ListAdapter;
import com.bilingoal.covirus.di.qualifiers.SummaryList;
import com.bilingoal.covirus.di.scopes.ActivityScope;
import com.bilingoal.covirus.dto.Country;
import com.bilingoal.covirus.summary.SummaryPresenter;
import com.bilingoal.covirus.models.StateManager;
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
