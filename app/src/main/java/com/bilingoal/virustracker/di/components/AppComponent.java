package com.bilingoal.virustracker.di.components;

import android.app.Application;
import com.bilingoal.virustracker.base.BaseApp;
import com.bilingoal.virustracker.countries.CountriesFragment;
import com.bilingoal.virustracker.di.modules.ActivityBuilderModule;
import com.bilingoal.virustracker.di.modules.AppModule;
import com.bilingoal.virustracker.summary.SummaryFragment;
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
