package com.bilingoal.covirus.di.modules;

import com.bilingoal.covirus.di.scopes.ActivityScope;
import com.bilingoal.covirus.main.MainPresenter;
import com.bilingoal.covirus.models.VersionLoader;
import com.bilingoal.covirus.network.NetworkInterface;
import com.bilingoal.covirus.models.StateManager;
import com.bilingoal.covirus.utils.schedulerproviders.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @ActivityScope
    @Provides
    static MainPresenter provideMainPresenter(
            NetworkInterface networkInterface, SchedulerProvider schedulerProvider, StateManager stateManager,
            VersionLoader versionLoader) {
        return new MainPresenter(networkInterface, schedulerProvider, stateManager, versionLoader);
    }
}