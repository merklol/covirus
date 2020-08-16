package com.bilingoal.covirus.di.components;

import android.app.Application;
import com.bilingoal.covirus.base.BaseApp;
import com.bilingoal.covirus.di.modules.ActivityBuilderModule;
import com.bilingoal.covirus.di.modules.AppModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import javax.inject.Singleton;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        ActivityBuilderModule.class,
        AppModule.class
})
public interface AppComponent extends AndroidInjector<BaseApp> {

//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        Builder application(Application application);
//        AppComponent build();
//    }
    @Component.Factory
    interface  Factory {
        AppComponent create(@BindsInstance Application application);
    }
}
