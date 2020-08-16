package com.bilingoal.virustracker.di.modules;

import com.bilingoal.virustracker.di.scopes.ActivityScope;
import com.bilingoal.virustracker.main.MainPresenter;
import com.bilingoal.virustracker.models.VersionLoader;
import com.bilingoal.virustracker.network.NetworkInterface;
import com.bilingoal.virustracker.models.StateManager;
import com.bilingoal.virustracker.utils.schedulerproviders.SchedulerProvider;
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