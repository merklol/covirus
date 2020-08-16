package com.bilingoal.virustracker.di.modules;

import com.bilingoal.virustracker.models.VersionLoader;
import com.bilingoal.virustracker.network.NetworkInterface;
import com.bilingoal.virustracker.utils.schedulerproviders.AppSchedulerProvider;
import com.bilingoal.virustracker.utils.Constants;
import com.bilingoal.virustracker.utils.schedulerproviders.SchedulerProvider;
import com.bilingoal.virustracker.network.NetworkClient;
import com.bilingoal.virustracker.models.StateManager;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public abstract class AppModule {

    @Singleton
    @Provides
    static String provideBaseUrl(){
        return Constants.BASE_URL;
    }

    @Singleton
    @Provides
    static NetworkInterface provideRequestApi(String url){
        return new NetworkClient(url).getRequestApi();
    }

    @Singleton
    @Provides
    static SchedulerProvider provideSchedulerProvider(){
        return new AppSchedulerProvider();
    }

    @Singleton
    @Provides
    static StateManager provideStateManger() {
        return StateManager.getInstance();
    }

    @Singleton
    @Provides
    static VersionLoader provideVersionChecker() {
        return new VersionLoader.Builder()
                .setCollectionName(Constants.APP_VERSION_COLLECTION)
                .setDocumentId(Constants.APP_VERSION_DOCUMENT_ID)
                .build();
    }
}