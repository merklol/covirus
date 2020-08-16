package com.bilingoal.covirus.di.modules;

import com.bilingoal.covirus.models.VersionLoader;
import com.bilingoal.covirus.network.NetworkInterface;
import com.bilingoal.covirus.utils.schedulerproviders.AppSchedulerProvider;
import com.bilingoal.covirus.utils.Constants;
import com.bilingoal.covirus.utils.schedulerproviders.SchedulerProvider;
import com.bilingoal.covirus.network.NetworkClient;
import com.bilingoal.covirus.models.StateManager;
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